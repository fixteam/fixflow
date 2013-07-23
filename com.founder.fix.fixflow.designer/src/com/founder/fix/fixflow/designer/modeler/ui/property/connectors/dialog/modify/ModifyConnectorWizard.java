/**
 * 
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog.modify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.bpmn2extensions.connector.InputParameter;
import com.founder.fix.bpmn2extensions.connector.OutputParameter;
import com.founder.fix.bpmn2extensions.connector.Page;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs;
import com.founder.fix.bpmn2extensions.fixflow.Documentation;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.SkipComment;
import com.founder.fix.bpmn2extensions.fixflow.TimeExpression;
import com.founder.fix.bpmn2extensions.fixflow.TimeSkipExpression;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree.TreeViewerFactory;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionCombo;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;

/**
 * @author wangzhiwei
 * 
 */
public class ModifyConnectorWizard extends Wizard {

	private ConnectorInstance connectorInstance;

	private ModifyRenameConnectorWizardPage modifyRenameConnectorWizardPage;

	private ModifyOutputConnectorWizardPage modifyOutputConnectorWizardPage;

	private ModifyCommonConnectorWizardPage[] modifyCommonConnectotWizardPages;

	private ModifyCommonConnectorWizardPage modifyCommonConnectotWizardPage;

	private EObject be;

	private BPMN2Editor bpmn2Editor;

	/**
	 * 
	 */
	public ModifyConnectorWizard(EObject be, BPMN2Editor bpmn2Editor, ConnectorInstance connectorInstance) {
		this.be = be;
		this.connectorInstance = connectorInstance;
		this.bpmn2Editor = bpmn2Editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		modifyConnectorInstance(connectorInstance);
		return true;
	}

	@Override
	public boolean canFinish() {
		// 当没有到最后一页时‘完成’按钮不可用
		if (this.getContainer().getCurrentPage() != modifyOutputConnectorWizardPage) { // 最后一个页面
			return false;
		}
		return super.canFinish();
	}

	@Override
	public void addPages() {
		// 实例化向导页面
		modifyRenameConnectorWizardPage = new ModifyRenameConnectorWizardPage("modifyRenameConnectorWizardPage", "给连接器命名", null, connectorInstance);

		addPage(modifyRenameConnectorWizardPage);

		// 获取该connetor
		Connector connector = TreeViewerFactory.getConnector(connectorInstance.getConnectorId());

		if (connector != null) {
			// 获取所有的page
			EList<Page> pages = connector.getPages().getPage();
			if (pages != null && pages.size() > 0) {
				modifyCommonConnectotWizardPages = new ModifyCommonConnectorWizardPage[pages.size()];

				for (int i = 0; i < pages.size(); i++) {
					Page page = pages.get(i);
					modifyCommonConnectotWizardPage = new ModifyCommonConnectorWizardPage("modifyCommonConnectotWizardPage", page.getPageTitle(), page.getPageNote(), null, page, connectorInstance);
					addPage(modifyCommonConnectotWizardPage);

					modifyCommonConnectotWizardPages[i] = modifyCommonConnectotWizardPage;
				}
			}

			// 获取所有的输出参数
			if (connector.getOutputs() != null) {
				EList<OutputParameter> outputs = connector.getOutputs().getOutputParameter();
				modifyOutputConnectorWizardPage = new ModifyOutputConnectorWizardPage("modifyOutputConnectorWizardPage", "输出配置", "映射连接器的输出到流程变量中", null, outputs, connectorInstance);
				addPage(modifyOutputConnectorWizardPage);
			}
		}

		super.addPages();
	}

	/**
	 * 添加连接器实例
	 * 
	 * @param connectorInstance
	 */
	private void modifyConnectorInstance(final ConnectorInstance connectorInstance) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = bpmn2Editor.getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				save();
			}
		});
	}

	/**
	 * 保存
	 */
	private ConnectorInstance save() {
		// 封装连接器页面数据
		String name = modifyRenameConnectorWizardPage.getConnectNameText().getText().trim();
		String description = modifyRenameConnectorWizardPage.getConnectDescriptionText().getText().trim();
		String event = modifyRenameConnectorWizardPage.getLifeCycle().getEvent().trim();
		String exception = modifyRenameConnectorWizardPage.getConnectExceptionCombo().getText();
		String errorName = modifyRenameConnectorWizardPage.getConnectNameErrorText().getText().trim();
		ExpressionTo expressionTo = modifyRenameConnectorWizardPage.getExpressionComboViewer().getExpressionCombo().getExpressionTo();
		String expName = expressionTo == null ? "" : expressionTo.getName();
		String expValue = expressionTo == null ? "" : expressionTo.getExpressionText();

		// 跳过策略
		Expression skipexpression = FixFlowFactory.eINSTANCE.createExpression();
		skipexpression.setName(expName);
		skipexpression.setValue(expValue);
		SkipComment skipComment = FixFlowFactory.eINSTANCE.createSkipComment();
		skipComment.setExpression(skipexpression);

		connectorInstance.setConnectorInstanceName(name);

		Documentation documentation = connectorInstance.getDocumentation();
		documentation.setValue(description);
		connectorInstance.setDocumentation(documentation);

		connectorInstance.setEventType(event);
		connectorInstance.setErrorHandling(exception);
		connectorInstance.setErrorCode(errorName);

		// 增加跳过策略
		if (skipComment.getExpression().getValue() != null && !skipComment.getExpression().getValue().equals("")) {
			connectorInstance.setSkipComment(skipComment);
		} else {
			connectorInstance.setSkipComment(null);
		}

		boolean isTimeExecute = modifyRenameConnectorWizardPage.getCheckButton().getSelection();

		connectorInstance.setIsTimeExecute(isTimeExecute);

		if (isTimeExecute) {
			TimeExpression timeExpression = FixFlowFactory.eINSTANCE.createTimeExpression();

			ExpressionTo expressionToTime = modifyRenameConnectorWizardPage.getTimeExpressionComboViewer().getExpressionCombo().getExpressionTo();
			ExpressionTo expressionToTimeSkip = modifyRenameConnectorWizardPage.getSkipExpressionComboViewer().getExpressionCombo().getExpressionTo();

			Expression expressionTime = FixFlowFactory.eINSTANCE.createExpression();
			expressionTime.setName(expressionToTime == null ? "" : expressionToTime.getName());
			expressionTime.setValue(expressionToTime == null ? "" : expressionToTime.getExpressionText());
			timeExpression.setExpression(expressionTime);
			connectorInstance.setTimeExpression(timeExpression);

			TimeSkipExpression timeExpressionSkip = FixFlowFactory.eINSTANCE.createTimeSkipExpression();
			Expression expressionTimeSkip = FixFlowFactory.eINSTANCE.createExpression();
			expressionTimeSkip.setName(expressionToTimeSkip == null ? "" : expressionToTimeSkip.getName());
			expressionTimeSkip.setValue(expressionToTimeSkip == null ? "" : expressionToTimeSkip.getExpressionText());

			timeExpressionSkip.setExpression(expressionTimeSkip);

			connectorInstance.setTimeSkipExpression(timeExpressionSkip);
		} else {
			connectorInstance.setTimeExpression(null);
			connectorInstance.setTimeSkipExpression(null);
		}

		// 封装一个或多个输入页面数据
		// 获取所有的输入页面
		if (modifyCommonConnectotWizardPages != null && modifyCommonConnectotWizardPages.length > 0) {
			for (int i = 0; i < modifyCommonConnectotWizardPages.length; i++) {
				ModifyCommonConnectorWizardPage modifyCommonConnectotWizardPage = modifyCommonConnectotWizardPages[i];

				ConnectorParameterInputs connectorParameterInputs = null;

				// 获取每页所有的控件control
				List<Control> controls = modifyCommonConnectotWizardPage.getControls();
				if (controls != null && controls.size() > 0) {
					for (int j = 0; j < controls.size(); j++) {
						Control control = controls.get(j);

						Expression expression = FixFlowFactory.eINSTANCE.createExpression();

						// 分类进行数据处理
						if (control instanceof Label) {
							Label label = (Label) control;
							// 如果是第一页正常
							if (i == 0) {
								connectorParameterInputs = connectorInstance.getConnectorParameterInputs().get(j / 2);
							}
							// 如果不是第一页，则得加上之前所有控件的数量再除，不然会更改掉之前页数相对应行的值
							else {
								connectorParameterInputs = connectorInstance.getConnectorParameterInputs()
										.get(((connectorInstance.getConnectorParameterInputs().size() * 2 - controls.size()) + j) / 2);
							}

							connectorParameterInputs.setId(((InputParameter) label.getData()).getId());
							connectorParameterInputs.setName(((InputParameter) label.getData()).getName());
							connectorParameterInputs.setDataType(((InputParameter) label.getData()).getDataType());
							continue;
						}

						if (control instanceof Text) {
							Text text = (Text) control;
							expression.setValue(text.getText().trim());
						}
						/*
						 * else if(control instanceof Combo) { Combo combo =
						 * (Combo) control;
						 * expression.setValue(combo.getText().trim()); }
						 */
						else if (control instanceof ExpressionCombo) {
							ExpressionCombo expressionCombo = (ExpressionCombo) control;
							expression.setValue(expressionCombo.getExpressionTo() == null ? "" : expressionCombo.getExpressionTo().getExpressionText().trim());
							expression.setName(expressionCombo.getExpressionTo() == null ? "" : expressionCombo.getExpressionTo().getName());
						} else if (control instanceof Button) {
							Button button = (Button) control;
							expression.setValue(button.getText().trim());
						}

						connectorParameterInputs.setExpression(expression);

						// 重新实例化
						connectorParameterInputs = connectorInstance.getConnectorParameterInputs().get(j / 2);

						// connectorInstance.getConnectorParameterInputs().add(connectorParameterInputs);
					}
				}
			}
		}

		// 封装输出页面数据
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();

		// 获取所有的输出控件
		List<Combo> combos = new ArrayList<Combo>();
		Control[] controls = modifyOutputConnectorWizardPage.getGridComposite().getChildren();
		if (controls != null && controls.length > 0) {
			for (int i = 0; i < controls.length; i++) {
				Control control = controls[i];
				// 控件必须是combo才有效
				if (control instanceof Combo) {
					Combo combo = (Combo) control;
					combos.add(combo);
				}
			}
		}

		// 重新组织combo控件
		for (int i = 0; i < combos.size(); i = i + 2) {
			Map<String, Object> map = new HashMap<String, Object>();

			Combo combo = (Combo) combos.get(i);
			Combo comboNext = (Combo) combos.get(i + 1);

			if (combo.getData("type").toString().equals("expression")) {
				Expression outExpression = FixFlowFactory.eINSTANCE.createExpression();
				outExpression.setValue(combo.getText().trim());
				map.put("expression", outExpression);
			} else if (combo.getData("type").toString().equals("target")) {
				map.put("target", combo.getText().trim());
			}

			if (comboNext.getData("type").toString().equals("expression")) {
				Expression outExpression = FixFlowFactory.eINSTANCE.createExpression();
				outExpression.setValue(comboNext.getText().trim());
				map.put("expression", outExpression);
			} else if (comboNext.getData("type").toString().equals("target")) {
				map.put("target", comboNext.getText().trim());
			}

			maps.add(map);
		}

		// 先删除掉旧的output
		for (int i = 0; i < connectorInstance.getConnectorParameterOutputs().size();) {
			connectorInstance.getConnectorParameterOutputs().remove(i);
		}

		// 将数据组织封装成为connectorParameterOutputs
		for (int i = 0; i < maps.size(); i++) {
			// 添加新的output
			Map<String, Object> map = maps.get(i);
			ConnectorParameterOutputs connectorParameterOutputs = FixFlowFactory.eINSTANCE.createConnectorParameterOutputs();
			connectorParameterOutputs.setExpression((Expression) map.get("expression"));
			connectorParameterOutputs.setVariableTarget(String.valueOf(map.get("target")));
			connectorInstance.getConnectorParameterOutputs().add(connectorParameterOutputs);
		}

		return connectorInstance;
	}

	public ConnectorInstance getConnectorInstance() {
		return connectorInstance;
	}

	public void setConnectorInstance(ConnectorInstance connectorInstance) {
		this.connectorInstance = connectorInstance;
	}

}
