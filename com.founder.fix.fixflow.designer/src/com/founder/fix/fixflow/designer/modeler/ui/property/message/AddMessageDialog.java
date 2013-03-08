package com.founder.fix.fixflow.designer.modeler.ui.property.message;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.Documentation;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage.Literals;
import com.founder.fix.bpmn2extensions.fixflow.MessageObj;
import com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable;
import com.founder.fix.bpmn2extensions.fixflow.TokenVariable;
import com.founder.fix.fixflow.designer.modeler.ui.property.common.DataVariableDialog;
import com.founder.fix.fixflow.designer.util.DataVarUtil;
import com.founder.fix.fixflow.designer.util.ImageUtil;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;

public class AddMessageDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Text text;
	private Text text_1;
	private Text text_2;
	private MessageObj messageObj;
	private Text text_3;
	private Combo combo;
	private Combo combo_1;
	private TreeViewer treeViewer;
	private BPMN2Editor bpmn2Editor;
	private ExpressionComboViewer expressionComboViewer;
	private ExpressionComboViewer expressionComboViewer_1;
	private Combo combo_4;

	/**
	 * Create the dialog.
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public AddMessageDialog(Shell parentShell, BPMN2Editor bpmn2Editor) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		messageObj = FixFlowFactory.eINSTANCE.createMessageObj();
		this.bpmn2Editor = bpmn2Editor;
	}
	
	/**
	 * 修改时构造函数
	 * @param parentShell
	 * @param messageObj
	 */
	public AddMessageDialog(Shell parentShell, MessageObj messageObj, BPMN2Editor bpmn2Editor) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		this.messageObj = messageObj;
		this.bpmn2Editor = bpmn2Editor;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("添加消息");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("ID");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(0, 0, 61, 17);
		lblNewLabel.setText("名称");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblNewLabel_2.setText("描述");
		
		text_1 = new Text(composite, SWT.BORDER | SWT.WRAP);
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_1.heightHint = 72;
		text_1.setLayoutData(gd_text_1);
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		lblNewLabel_3.setText("目标池");
		
		combo = new Combo(composite, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		lblNewLabel_4.setText("目标任务");
		
		combo_1 = new Combo(composite, SWT.NONE);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblNewLabel_8 = new Label(composite, SWT.NONE);
		lblNewLabel_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_8.setText("消息类型");
		
		combo_4 = new Combo(composite, SWT.READ_ONLY);
		combo_4.setItems(new String[] {"消息启动", "信号广播启动", "消息令牌", "信号令牌"});
		combo_4.setData("0", "MessageStartEvent");
		combo_4.setData("1", "SignalStartEvent");
		combo_4.setData("2", "MessageToken");
		combo_4.setData("3", "SignalToken");
		combo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_4.select(0);
		
		Label lblNewLabel_6 = new Label(composite, SWT.NONE);
		lblNewLabel_6.setText("流程实例");
		
		expressionComboViewer = new ExpressionComboViewer(composite);
		Combo combo_2 = expressionComboViewer.getCombo();
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_7 = new Label(composite, SWT.NONE);
		lblNewLabel_7.setText("流程令牌");
		
		expressionComboViewer_1 = new ExpressionComboViewer(composite);
		Combo combo_3 = expressionComboViewer_1.getCombo();
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setLayout(new GridLayout(3, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblNewLabel_5 = new Label(composite_1, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 2));
		lblNewLabel_5.setText("添加数据");
		
		text_2 = new Text(composite_1, SWT.BORDER | SWT.SEARCH);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 2));
		GridLayout gl_composite_3 = new GridLayout(1, false);
		gl_composite_3.verticalSpacing = 1;
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		gl_composite_3.horizontalSpacing = 0;
		composite_3.setLayout(gl_composite_3);
		
		Button btnNewButton = new Button(composite_3, SWT.NONE);
		btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton.setBounds(0, 0, 80, 27);
		btnNewButton.setText("创建...");
		btnNewButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				final DataVariableDialog dvd = new DataVariableDialog(getShell());
				dvd.setBlockOnOpen(true);
				if (dvd != null && dvd.open() == InputDialog.OK) {
					((List<DataVariable>) treeViewer.getInput()).add(dvd.getDataVariable());
					treeViewer.refresh();
					TransactionalEditingDomain editingDomain = bpmn2Editor.getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							messageObj.getDataVariable().add(dvd.getDataVariable());
						}
					});
				}
			}
		});
		
		Button btnNewButton_1 = new Button(composite_3, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton_1.setBounds(0, 0, 80, 27);
		btnNewButton_1.setText("编辑...");
		btnNewButton_1.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if (!treeViewer.getSelection().isEmpty()) {
					IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
					final DataVariable dataVariable = (DataVariable) selection.getFirstElement();
					DataVariableDialog dvd = new DataVariableDialog(getShell(), dataVariable);
					dvd.setBlockOnOpen(true);
					if (dvd != null && dvd.open() == InputDialog.OK) {
						final DataVariable newDataVariable = dvd.getDataVariable();
						final int index = ((List<DataVariable>) treeViewer.getInput()).indexOf(dataVariable);
						
						TransactionalEditingDomain editingDomain = bpmn2Editor.getEditingDomain();
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
							@Override
							protected void doExecute() {
								messageObj.getDataVariable().remove(dataVariable);
								messageObj.getDataVariable().add(index, newDataVariable);
							}
						});
						
						((List<DataVariable>) treeViewer.getInput()).remove(dataVariable);
						((List<DataVariable>) treeViewer.getInput()).add(index, newDataVariable);
						treeViewer.refresh();
					}
				}
			}
		});
		
		Button btnNewButton_2 = new Button(composite_3, SWT.NONE);
		btnNewButton_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton_2.setBounds(0, 0, 80, 27);
		btnNewButton_2.setText("移除");
		btnNewButton_2.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				ISelection sel = treeViewer.getSelection();
				if (sel == null)
					return;
				Object[] objs = ((IStructuredSelection) sel).toArray();
				if (objs == null || objs.length == 0)
					return;
				boolean b = MessageDialog.openConfirm(null, "警告", "你确认要删除吗？");
				if (!b)
					return;

				for (int i = 0; i < objs.length; i++) {
					final DataVariable col = (DataVariable) objs[i];
					TransactionalEditingDomain editingDomain = bpmn2Editor.getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							messageObj.getDataVariable().remove(col);
						}
					});
				
					((List<DataVariable>)treeViewer.getInput()).remove(col);
				}
				treeViewer.refresh();
			}
		});
		
		treeViewer = new TreeViewer(composite_1, SWT.BORDER | SWT.MULTI);
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setLabelProvider(new ViewerLabelProvider());
		Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite_2 = new Composite(container, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		init();
		return area;
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		TransactionalEditingDomain editingDomain = bpmn2Editor.getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				Documentation documentation = FixFlowFactory.eINSTANCE.createDocumentation();
				documentation.setValue(text_1.getText() == null ? "" : text_1.getText());
				messageObj.setDocumentation(documentation);
				
				
				ExpressionTo expressionTo = expressionComboViewer.getExpressionCombo().getExpressionTo();
				if(expressionTo!=null)
				{
					Expression expression=FixFlowFactory.eINSTANCE.createExpression();
					expression.setName(expressionTo.getName());
					expression.setValue(expressionTo.getExpressionText());
					if(messageObj.getProcessInstanceVariable()!=null) {
						messageObj.getProcessInstanceVariable().setExpression(expression);
					}else {
						ProcessInstanceVariable processInstanceVariable = FixFlowFactory.eINSTANCE.createProcessInstanceVariable();
						processInstanceVariable.setExpression(expression);
						messageObj.setProcessInstanceVariable(processInstanceVariable);
					}
				}
				
				
				ExpressionTo expressionTo1 = expressionComboViewer_1.getExpressionCombo().getExpressionTo();
				if(expressionTo1!=null)
				{
					Expression expression=FixFlowFactory.eINSTANCE.createExpression();
					expression.setName(expressionTo1.getName());
					expression.setValue(expressionTo1.getExpressionText());
					if(messageObj.getTokenVariable()!=null) {
						messageObj.getTokenVariable().setExpression(expression);
					}else {
						TokenVariable tokenVariable = FixFlowFactory.eINSTANCE.createTokenVariable();
						tokenVariable.setExpression(expression);
						messageObj.setTokenVariable(tokenVariable);
					}
				}
				
				String messageType = combo_4.getData(String.valueOf(combo_4.getSelectionIndex())).toString();
				messageObj.setMessageType(messageType);
			}
		});
		
		super.okPressed();
	}
	
	private void init() {
		treeViewer.setInput(getDataVariable());
		
		combo_4.setText(messageObj.getMessageType()==null?"":getType(messageObj.getMessageType()));
		
		text_1.setText(messageObj.getDocumentation()==null ? "" : messageObj.getDocumentation().getValue());
		
		if(messageObj.getProcessInstanceVariable() != null){
			if (messageObj.getProcessInstanceVariable().getExpression() != null) {
				ExpressionTo expressionTo=new ExpressionTo();
				expressionTo.setName(messageObj.getProcessInstanceVariable().getExpression().getName());
				expressionTo.setExpressionText(messageObj.getProcessInstanceVariable().getExpression().getValue());
				expressionComboViewer.setDefaultExpressionInput(expressionTo);
			}
		}
		
		if(messageObj.getTokenVariable() != null){
			if (messageObj.getTokenVariable().getExpression() != null) {
				ExpressionTo expressionTo=new ExpressionTo();
				expressionTo.setName(messageObj.getTokenVariable().getExpression().getName());
				expressionTo.setExpressionText(messageObj.getTokenVariable().getExpression().getValue());
				expressionComboViewer_1.setDefaultExpressionInput(expressionTo);
			}
		}
	}
	
	/**
	 * 取得messageObj上的所有数据变量
	 * @return
	 */
	private List<DataVariable> getDataVariable() {
		List<DataVariable> dataVariables = new ArrayList<DataVariable>();

		for (DataVariable dataVariable : messageObj.getDataVariable()) {
			dataVariables.add(dataVariable);
		}

		return dataVariables;
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(510, 585);
	}

	public MessageObj getMessageObj() {
		return messageObj;
	}

	public void setMessageObj(MessageObj messageObj) {
		this.messageObj = messageObj;
	}
	
	private static class ViewerLabelProvider extends StyledCellLabelProvider implements ILabelProvider {
		public Image getImage(Object element) {
			Image image = ImageUtil.getImageFromURL("/datavar_16.png");
			if(element instanceof DataVariable){
				return image;
			}
			return null;
		}

		public String getText(Object element) {
			DataVariable treeElement = (DataVariable) element;
			return treeElement.getId() + " -- " + DataVarUtil.getDataTypeDefByDataVariableDataType(treeElement.getDataType()).getId();
		}

		@Override
		public void update(ViewerCell cell) {
			// TODO Auto-generated method stub
			if (cell.getElement() instanceof DataVariable) {
				DataVariable d = (DataVariable) cell.getElement();
				StyledString styledString = new StyledString();
				String decoration = " -- " + DataVarUtil.getDataTypeDefByDataVariableDataType(d.getDataType()).getName();
				styledString.append(d.getId() == null ? "" : d.getId());
				styledString.append(decoration, StyledString.DECORATIONS_STYLER);
				cell.setText(styledString.getString());
				cell.setImage(getImage(d));
				cell.setStyleRanges(styledString.getStyleRanges());
			}
		}
	}
	
	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub

		}

		public void dispose() {
			// TODO Auto-generated method stub

		}

		public Object[] getElements(Object inputElement) {
			// TODO Auto-generated method stub
			if (inputElement instanceof List) {
				@SuppressWarnings("rawtypes")
				List list = (List) inputElement;
				return list.toArray();
			} else {
				return new Object[0];
			}
		}

		public Object[] getChildren(Object parentElement) {
			return null;
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return false;
		}
	}
	
	public String getType(String type){
		String cntype = "";
		if(type.equals("MessageStartEvent")){
			cntype = "消息启动";
			return cntype;
		}
		if(type.equals("SignalStartEvent")){
			cntype = "信号广播启动";
			return cntype;
		}
		if(type.equals("MessageToken")){
			cntype = "消息令牌";
			return cntype;
		}
		if(type.equals("SignalToken")){
			cntype = "信号令牌";
			return cntype;
		}
		
		return cntype;
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue text_3ObserveTextObserveWidget = SWTObservables.observeText(text_3, SWT.Modify);
		IObservableValue messageObjIdObserveValue = EMFObservables.observeValue(messageObj, Literals.MESSAGE_OBJ__ID);
		bindingContext.bindValue(text_3ObserveTextObserveWidget, messageObjIdObserveValue, null, null);
		//
		IObservableValue textObserveTextObserveWidget = SWTObservables.observeText(text, SWT.Modify);
		IObservableValue messageObjNameObserveValue = EMFObservables.observeValue(messageObj, Literals.MESSAGE_OBJ__NAME);
		bindingContext.bindValue(textObserveTextObserveWidget, messageObjNameObserveValue, null, null);
		//
		IObservableValue comboObserveTextObserveWidget = SWTObservables.observeText(combo);
		IObservableValue messageObjTargetProcessObserveValue = EMFObservables.observeValue(messageObj, Literals.MESSAGE_OBJ__TARGET_PROCESS);
		bindingContext.bindValue(comboObserveTextObserveWidget, messageObjTargetProcessObserveValue, null, null);
		//
		IObservableValue combo_1ObserveTextObserveWidget = SWTObservables.observeText(combo_1);
		IObservableValue messageObjTargetNodeObserveValue = EMFObservables.observeValue(messageObj, Literals.MESSAGE_OBJ__TARGET_NODE);
		bindingContext.bindValue(combo_1ObserveTextObserveWidget, messageObjTargetNodeObserveValue, null, null);
		//
		return bindingContext;
	}
}
