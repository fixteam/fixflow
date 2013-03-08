package com.founder.fix.fixflow.designer.modeler.ui.property.usertask;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.UserTask; 
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;

import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.TaskCommand;
import com.founder.fix.fixflow.designer.modeler.ui.common.CreateNewTaskDialog;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import com.founder.fix.fixflow.designer.util.ImageUtil;

public class UserTaskUserCommandPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private TreeViewer treeViewer_1;
	private Tree tree_1;
	private Button advanceEditButton;
	private Button advanceDeleteButton;
	private Button advanceNewButton;
	private Button upButton;
	private Button downButton;
	

	public UserTaskUserCommandPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public UserTaskUserCommandPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUI() {
		// TODO Auto-generated method stub 
		setLayout(null);
		
		Label advanceHandlelabel = new Label(this, SWT.NONE);
		advanceHandlelabel.setText("处理命令");
		advanceHandlelabel.setBounds(10, 87, 67, 20);
		
	
		toolkit.adapt(advanceHandlelabel, true, true);
		
		advanceEditButton = new Button(this, SWT.NONE);
		advanceEditButton.setText("编辑...");
		advanceEditButton.setBounds(285, 81, 67, 22);
		

		toolkit.adapt(advanceEditButton, true, true);
		
		advanceDeleteButton = new Button(this, SWT.NONE);
		advanceDeleteButton.setText("移除");
		advanceDeleteButton.setBounds(285, 104, 67, 22);

		toolkit.adapt(advanceDeleteButton, true, true);
		
		advanceNewButton = new Button(this, SWT.NONE);
		advanceNewButton.setText("创建...");
		advanceNewButton.setBounds(285, 57, 67, 22);
		
		
		toolkit.adapt(advanceNewButton, true, true);
		
		treeViewer_1 = new TreeViewer(this, SWT.BORDER);
		treeViewer_1.setLabelProvider(new ViewerLabelProvider());
		treeViewer_1.setContentProvider(new TreeContentProvider());
		
		tree_1 = treeViewer_1.getTree();
		tree_1.setBounds(87, 40, 192, 132);
		
	
		toolkit.adapt(tree_1, true, true);
		
		upButton = new Button(this, SWT.NONE);
		upButton.setText("向上");
		upButton.setBounds(285, 127, 67, 22);
		upButton.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) treeViewer_1.getSelection();
				TaskCommand taskCommand = (TaskCommand) selection.getFirstElement();
				int idx = ((List<TaskCommand>) treeViewer_1.getInput()).indexOf(taskCommand);
				if (idx != 0) {
					((List<TaskCommand>) treeViewer_1.getInput()).remove(taskCommand);
					deleteTaskCommand(taskCommand);
					((List<TaskCommand>) treeViewer_1.getInput()).add(idx - 1, taskCommand);
					addTaskCommand(taskCommand, idx - 1);
				}
				treeViewer_1.refresh();
				updateButtons();
			}
		});
		

		toolkit.adapt(upButton, true, true);
		
		downButton = new Button(this, SWT.NONE);
		downButton.setText("向下");
		downButton.setBounds(285, 150, 67, 22);
		downButton.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) treeViewer_1.getSelection();
				TaskCommand taskCommand = (TaskCommand) selection.getFirstElement();
				int idx = ((List<TaskCommand>) treeViewer_1.getInput()).indexOf(taskCommand);
				if (idx != ((List<TaskCommand>) treeViewer_1.getInput()).size() - 1) {
					((List<TaskCommand>) treeViewer_1.getInput()).remove(taskCommand);
					deleteTaskCommand(taskCommand);
					((List<TaskCommand>) treeViewer_1.getInput()).add(idx + 1, taskCommand);
					addTaskCommand(taskCommand, idx + 1);
				}
				treeViewer_1.refresh();
				updateButtons();
			}
		});
		
	
		toolkit.adapt(downButton, true, true);
	}
	
	@Override
	public void createUIBindings(EObject eObject) {
		// TODO Auto-generated method stub
		//bindTaskCommand(treeViewer);
		treeViewer_1.setInput(getAdvanceTaskCommands());
		
		treeViewer_1.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				updateButtons();
			}
		});

		// 设置按钮可用性
		updateButtons();
		
		advanceNewButton.addListener(SWT.Selection, advanceAddListener);
		advanceDeleteButton.addListener(SWT.Selection, advanceDeleteListener);
		advanceEditButton.addListener(SWT.Selection, advanceEditListener);
	}

	
	
	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput,
				Object newInput) {
			// TODO Auto-generated method stub

		}

		public void dispose() {
			// TODO Auto-generated method stub

		}

		public boolean hasChildren(Object element) {
			// TODO Auto-generated method stub
			return false;
		}

		public Object getParent(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		public Object[] getChildren(Object parentElement) {
			// TODO Auto-generated method stub
			return null;
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
	}
	
	private static class ViewerLabelProvider extends StyledCellLabelProvider implements ILabelProvider {
		public Image getImage(Object element) {
			Image image = ImageUtil.getImageFromURL("/chulimingling_16.png");
			if(element instanceof TaskCommand){
				return image;
			}
			return null;
		}
		public String getText(Object element) {
			TaskCommand treeElement = (TaskCommand) element;
			return treeElement.getName() + " -- " + getType(treeElement.getCommandType());
		}
		public String getType(String type){
			String cntype = "";
			/*
			if(type.equals("general")){
				cntype = "通用";
				return cntype;
			}
			if(type.equals("rollBack")){
				cntype = "退回";
				return cntype;
			}
			if(type.equals("transfer")){
				cntype = "转发";
				return cntype;
			}
			if(type.equals("delegation")){
				cntype = "代理任务";
				return cntype;
			}
			if(type.equals("submit")){
				cntype = "提交";
				return cntype;
			}
			else {*/
				List<TaskCommandDef> nameList = FixFlowConfigUtil.getTaskCommandNames(FixFlowConfigUtil.getFixFlowConfig());
				for(int i = 0; i<nameList.size(); i++) {
					if(type.equals(nameList.get(i).getId())) {
						cntype = nameList.get(i).getName();
						break;
					}
				}
			
			return cntype;
		}
		@Override
		public void update(ViewerCell cell) {
			// TODO Auto-generated method stub
			if (cell.getElement() instanceof TaskCommand) {
				TaskCommand d = (TaskCommand) cell.getElement();
				StyledString styledString = new StyledString();
				String decoration = " -- " + getType(d.getCommandType());
				styledString.append(d.getName());
				styledString.append(decoration, StyledString.DECORATIONS_STYLER);
				cell.setText(styledString.getString());
				cell.setImage(getImage(d)) ;
				cell.setStyleRanges(styledString.getStyleRanges());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<TaskCommand> getAdvanceTaskCommands(){
		UserTask userTask=(UserTask)be;
		List<TaskCommand> taskCommands = new ArrayList<TaskCommand>();
		
		if(userTask.getExtensionValues().size()>0)
		{
			for(ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()){
				FeatureMap extensionElements = extensionAttributeValue.getValue();
				Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, true);
				if(objectElement != null){
					List<TaskCommand> taskCommandList=(List<TaskCommand>) objectElement;
					for (TaskCommand taskCommand : taskCommandList) {
						taskCommands.add(taskCommand);
					}
				}
			}
		}
		
		return taskCommands;
	}
	
	/**
	 * EMF添加处理命令
	 * @param taskC
	 */
	private void addTaskCommand(final TaskCommand taskC){
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;
				
				if(userTask.getExtensionValues().size()>0){
					
					for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {
						
						FeatureMap extensionElements=extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, true);
						if(objectElement != null){
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
							        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, taskC);
							extensionElements.add(extensionElementEntry);
						}
						
					}
				}
				else{
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE
					        .createExtensionAttributeValue();
					userTask.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, taskC);
					extensionElement.getValue().add(extensionElementEntry);
				}
			}
		});
	}
	
	/**
	 * 向上向下时方法
	 * @param taskC
	 */
	private void addTaskCommand(final TaskCommand taskC, final int index){
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;
				
				if(userTask.getExtensionValues().size()>0){
					
					for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {
						
						FeatureMap extensionElements=extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, true);
						if(objectElement != null){
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
							        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, taskC);
							extensionElements.add(index, extensionElementEntry);
						}
						
					}
				}
				else{
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE
					        .createExtensionAttributeValue();
					userTask.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, taskC);
					extensionElement.getValue().add(index, extensionElementEntry);
				}
			}
		});
	}
	
	/**
	 * EMF删除处理命令
	 * @param taskC
	 */
	private void deleteTaskCommand(final TaskCommand taskC){
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;
				
				if(userTask.getExtensionValues().size()>0){
					
					for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {
						
						FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
						        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, taskC);
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						extensionElements.remove(extensionElementEntry);
						
					}
				}
			}
		});
	}
	
	/**
	 * EMF编辑处理命令(不打乱顺序)
	 * @param oldtask
	 * @param newtask
	 */
	private void editTaskCommand(final TaskCommand oldtask, final TaskCommand newtask, final int index){
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;
				
				if(userTask.getExtensionValues().size()>0){
					
					for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {
						
						FeatureMap.Entry oldextensionElementEntry = new SimpleFeatureMapEntry(
						        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, oldtask);
						FeatureMap.Entry newextensionElementEntry = new SimpleFeatureMapEntry(
						        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND, newtask);
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						extensionElements.remove(oldextensionElementEntry);
						extensionElements.add(index, newextensionElementEntry);
					}
				}
			}
		});
	}
	
	Listener advanceAddListener = new Listener() {
		
		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			CreateNewTaskDialog cnd = new CreateNewTaskDialog(getShell(), treeViewer_1);
			cnd.setBlockOnOpen(true);
			if(cnd != null && cnd.open() == InputDialog.OK){
				((List<TaskCommand>) treeViewer_1.getInput()).add(cnd.getTaskCommand());
				treeViewer_1.refresh();
				addTaskCommand(cnd.getTaskCommand());
			}
		}
	};
	
	Listener advanceDeleteListener = new Listener() {
		
		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			if(!treeViewer_1.getSelection().isEmpty()){
				IStructuredSelection selection = (IStructuredSelection) treeViewer_1.getSelection();
				TaskCommand taskCommand  = (TaskCommand) selection.getFirstElement();
				deleteTaskCommand(taskCommand);
				((List<TaskCommand>)treeViewer_1.getInput()).remove(taskCommand);
				treeViewer_1.refresh();
			}
		}
	};
	
	Listener advanceEditListener = new Listener() {
		
		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			if(!treeViewer_1.getSelection().isEmpty()){
				IStructuredSelection selection = (IStructuredSelection) treeViewer_1.getSelection();
				TaskCommand taskCommand  = (TaskCommand) selection.getFirstElement();
				CreateNewTaskDialog cnd = new CreateNewTaskDialog(getShell(), taskCommand, treeViewer_1);
				cnd.setBlockOnOpen(true);
				if(cnd != null && cnd.open() == InputDialog.OK){
					TaskCommand newTaskCommand = cnd.getTaskCommand();
					int index = ((List<TaskCommand>)treeViewer_1.getInput()).indexOf(taskCommand);
					editTaskCommand(taskCommand, newTaskCommand, index);
					((List<TaskCommand>)treeViewer_1.getInput()).remove(taskCommand);
					((List<TaskCommand>)treeViewer_1.getInput()).add(index, newTaskCommand);
					treeViewer_1.refresh();
				}
			}
		}
	};
	
	/**
	 * 设置按钮可用性
	 */
	@SuppressWarnings("unchecked")
	public void updateButtons() {
		Object selectedPage1 = ((IStructuredSelection) treeViewer_1.getSelection()).getFirstElement();
		int index1 = 0;
		while (selectedPage1 != null && ((List<TaskCommand>) treeViewer_1.getInput()).get(index1) != null && !selectedPage1.equals(((List<TaskCommand>) treeViewer_1.getInput()).get(index1))) {
			index1++;
		}
		
		advanceDeleteButton.setEnabled(selectedPage1 != null);
		advanceEditButton.setEnabled(selectedPage1 != null);
		upButton.setEnabled(selectedPage1 != null && index1 != 0);
		downButton.setEnabled(selectedPage1 != null && index1 != treeViewer_1.getTree().getItemCount() - 1);
	}
}
