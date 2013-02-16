/**
 * 
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.connector.InputParameter;
import com.founder.fix.bpmn2extensions.connector.OutputParameter;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef;
import com.founder.fix.bpmn2extensions.fixflow.Documentation;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionCombo;

/**
 * @author wangzhiwei
 *
 */
public class AddConnectorWizard extends DynamicPageWizard {
	
	private SelectConnectorWizardPage selectConnectorWizardPage;
	
	private RenameConnectorWizardPage renameConnectorWizardPage;
	
	private EObject be;
	
	private ConnectorInstance connectorInstance;

	/**
	 * 
	 */
	public AddConnectorWizard(EObject be) {
		this.be = be;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		ConnectorInstance connectorInstance = save();
		this.connectorInstance = connectorInstance;
		return true;
	}

	@Override
	public boolean canFinish() {
		//当没有到最后一页时‘完成’按钮不可用
		if(this.getContainer().getCurrentPage() != selectConnectorWizardPage.getOutputConnectorWizardPage()) { //最后一个页面
			return false;
		}
		return super.canFinish();
	}
	
	@Override
	public void addPages() {
		//实例化向导页面
		selectConnectorWizardPage = new SelectConnectorWizardPage("selectConnectorWizardPage", "选择连接器",
				null, this);
		
		renameConnectorWizardPage = new RenameConnectorWizardPage("renameConnectorWizardPage", "给连接器命名", 
				null);
		
		addPage(selectConnectorWizardPage);
		addPage(renameConnectorWizardPage);
		
		super.addPages();
	}
	
	/**
	 * 保存
	 */
	private ConnectorInstance save() {
		//封装连接器页面数据
		String name = renameConnectorWizardPage.getConnectNameText().getText().trim();
		String description = renameConnectorWizardPage.getConnectDescriptionText().getText().trim();
		String event = renameConnectorWizardPage.getLifeCycle().getEvent().trim();
		String exception = renameConnectorWizardPage.getConnectExceptionCombo().getText();
		String errorName = renameConnectorWizardPage.getConnectNameErrorText().getText().trim();
		String className=selectConnectorWizardPage.getConnector().getClassName();
		String packageName=selectConnectorWizardPage.getConnector().getPackageName();
		
		ConnectorInstance connectorInstance = FixFlowFactory.eINSTANCE.createConnectorInstance();
		connectorInstance.setConnectorId(selectConnectorWizardPage.getConnector().getConnectorId());
		connectorInstance.setConnectorInstanceId(UUID.randomUUID().toString());
		connectorInstance.setConnectorInstanceName(name);
		
		connectorInstance.setClassName(className);
		connectorInstance.setPackageName(packageName);
		
		Documentation documentation = FixFlowFactory.eINSTANCE.createDocumentation();
		documentation.setValue(description);
		connectorInstance.setDocumentation(documentation);
		
		connectorInstance.setEventType(event);
		connectorInstance.setErrorHandling(exception);
		connectorInstance.setErrorCode(errorName);
		
		//封装一个或多个输入页面数据
		//获取所有的输入页面
		CommonConnectorWizardPage[] commonConnectotWizardPages = selectConnectorWizardPage.getCommonConnectotWizardPages();
		if(commonConnectotWizardPages != null && commonConnectotWizardPages.length > 0) {
			for (int i = 0; i < commonConnectotWizardPages.length; i++) {
				CommonConnectorWizardPage commonConnectotWizardPage = commonConnectotWizardPages[i];
			
				ConnectorParameterInputs connectorParameterInputs = null;
				
				//获取每页所有的控件control
				List<Control> controls = commonConnectotWizardPage.getControls();
				if(controls != null && controls.size() > 0) {
					for (Iterator iterator = controls.iterator(); iterator
							.hasNext();) {
						Control control = (Control) iterator.next();
						
						Expression expression = FixFlowFactory.eINSTANCE.createExpression();
						
						//分类进行数据处理
						if (control instanceof Label) {
							Label label = (Label) control;
							connectorParameterInputs = FixFlowFactory.eINSTANCE.createConnectorParameterInputs();
							connectorParameterInputs.setId(((InputParameter)label.getData()).getId());
							connectorParameterInputs.setName(((InputParameter)label.getData()).getName());
							connectorParameterInputs.setDataType(((InputParameter)label.getData()).getDataType());
							continue;
						}
						
						if (control instanceof Text) {
							Text text = (Text) control;
							expression.setValue(text.getText().trim());
						}
						else if(control instanceof ExpressionCombo) {
							ExpressionCombo expressionCombo = (ExpressionCombo) control;
							if(expressionCombo.getExpressionTo() != null) {
								expression.setValue(expressionCombo.getExpressionTo() == null ? "" : expressionCombo.getExpressionTo().getExpressionText().trim());
								expression.setName(expressionCombo.getExpressionTo() == null ? "" : expressionCombo.getExpressionTo().getName());
							}
						}
						/*else if(control instanceof Combo) {
							Combo combo = (Combo) control;
							expression.setValue(combo.getText().trim());
						}*/
						else if(control instanceof Button) {
							Button button = (Button) control;
							expression.setValue(button.getText().trim());
						}
						
						connectorParameterInputs.setExpression(expression);
						
						connectorInstance.getConnectorParameterInputs().add(connectorParameterInputs);
						//重新实例化
						connectorParameterInputs = FixFlowFactory.eINSTANCE.createConnectorParameterInputs();
					}
				}
			}
		}
		
		//封装输出页面数据
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		
		OutputConnectorWizardPage outputConnectorWizardPage = selectConnectorWizardPage.getOutputConnectorWizardPage();
		//获取所有的输出控件
		List<Combo> combos = new ArrayList<Combo>();
		Control[] controls = outputConnectorWizardPage.getGridComposite().getChildren();
		if(controls != null && controls.length > 0) {
			for (int i = 0; i < controls.length; i++) {
				Control control = controls[i];
				//控件必须是combo才有效
				if(control instanceof Combo) {
					Combo combo = (Combo) control;
					combos.add(combo);
				}
			}
		}
		
		//重新组织combo控件
		for (int i = 0; i < combos.size(); i = i + 2) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			Combo combo = (Combo) combos.get(i);
			Combo comboNext = (Combo) combos.get(i + 1);
			
			if(combo.getData("type").toString().equals("expression")) {
				Expression outExpression = FixFlowFactory.eINSTANCE.createExpression();
				outExpression.setValue(combo.getText().trim());
				map.put("expression", outExpression);
			} 
			else if(combo.getData("type").toString().equals("target")) {
				map.put("target", combo.getText().trim());
			}
			
			if(comboNext.getData("type").toString().equals("expression")) {
				Expression outExpression = FixFlowFactory.eINSTANCE.createExpression();
				outExpression.setValue(comboNext.getText().trim());
				map.put("expression", outExpression);
			} 
			else if(comboNext.getData("type").toString().equals("target")) {
				map.put("target", comboNext.getText().trim());
			}
			
			maps.add(map);
		}
		
		//将数据组织封装成为connectorParameterOutputs
		for (Iterator iterator = maps.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			ConnectorParameterOutputs connectorParameterOutputs = FixFlowFactory.eINSTANCE.createConnectorParameterOutputs();
			connectorParameterOutputs.setExpression((Expression) map.get("expression"));
			connectorParameterOutputs.setVariableTarget(String.valueOf(map.get("target")));
			connectorInstance.getConnectorParameterOutputs().add(connectorParameterOutputs);
		}
		
		//存输出参数，后来添加
		List<ConnectorParameterOutputsDef> connectorParameterOutputsDefs = new ArrayList<ConnectorParameterOutputsDef>();
		
		List<OutputParameter> outputParameters = selectConnectorWizardPage.getConnector().getOutputs().getOutputParameter();
		
		for(OutputParameter outputParameter : outputParameters){
			ConnectorParameterOutputsDef connectorParameterOutputsDef = FixFlowFactory.eINSTANCE.createConnectorParameterOutputsDef();
			connectorParameterOutputsDef.setId(outputParameter.getId());
			connectorParameterOutputsDef.setName(outputParameter.getName());
			connectorParameterOutputsDef.setDataType(outputParameter.getDataType());
			connectorParameterOutputsDefs.add(connectorParameterOutputsDef);
		}
		
		connectorInstance.getConnectorParameterOutputsDef().addAll(connectorParameterOutputsDefs);
		
		return connectorInstance;
	}

	public ConnectorInstance getConnectorInstance() {
		return connectorInstance;
	}

	public void setConnectorInstance(ConnectorInstance connectorInstance) {
		this.connectorInstance = connectorInstance;
	}
	
}
