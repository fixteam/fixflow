package com.founder.fix.fixflow.designer.modeler.ui.property.message;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.SendTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.MessageObj;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.util.ImageUtil;

public class SendMessageCommonPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private Button btnNewButton;
	private Button btnNewButton_1;
	private Button btnNewButton_2;
	private TreeViewer treeViewer;
	private BaseElement operEle;

	public SendMessageCommonPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public SendMessageCommonPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		((GridData) attributesSection.getLayoutData()).grabExcessHorizontalSpace = false;


	}

	@Override
	public void createUI() {
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = 10;
		gridLayout.verticalSpacing = 10;
		gridLayout.marginLeft = 5;
		setLayout(gridLayout);
		
		treeViewer = new TreeViewer(this, SWT.BORDER | SWT.MULTI);
		Tree tree = treeViewer.getTree();
		GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_tree.heightHint = 130;
		gd_tree.widthHint = 245;
		tree.setLayoutData(gd_tree);
		treeViewer.setLabelProvider(new ViewerLabelProvider());
		treeViewer.setContentProvider(new TreeContentProvider());
		
		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.verticalSpacing = 1;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton.setText("创建...");
		toolkit.adapt(btnNewButton, true, true);
		
		btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton_1.setText("编辑...");
		toolkit.adapt(btnNewButton_1, true, true);
		
		btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton_2.setText("移除");
		toolkit.adapt(btnNewButton_2, true, true);
	}

	@Override
	public void createUIBindings(EObject eObject) {
		
		if(be instanceof ThrowEvent) {
			for (EventDefinition eventDefinition : ((ThrowEvent)be).getEventDefinitions()) {
				if(eventDefinition instanceof MessageEventDefinition) {
					operEle = eventDefinition;
					break;
				}
			}
		}else if(be instanceof SendTask){
			operEle = (BaseElement) be;
		}
		
		
		treeViewer.setInput(getMessageObjs());
		
		btnNewButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				AddMessageDialog amd = new AddMessageDialog(getShell(), getDiagramEditor());
				amd.setBlockOnOpen(true);
				if(amd != null && amd.open() == InputDialog.OK){
					((List<MessageObj>)treeViewer.getInput()).add(amd.getMessageObj());
					treeViewer.refresh();
					addMessageObj(amd.getMessageObj());
				}
			}
		});
		
		btnNewButton_1.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if(!treeViewer.getSelection().isEmpty()){
					IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
					MessageObj messageObj  = (MessageObj) selection.getFirstElement();
					AddMessageDialog amd = new AddMessageDialog(getShell(), messageObj, getDiagramEditor());
					amd.setBlockOnOpen(true);
					if(amd != null && amd.open() == InputDialog.OK){
						MessageObj newmessageObj = amd.getMessageObj();
						editMessageObj(messageObj, newmessageObj);
						int index = ((List<MessageObj>)treeViewer.getInput()).indexOf(messageObj);
						((List<MessageObj>)treeViewer.getInput()).remove(messageObj);
						((List<MessageObj>)treeViewer.getInput()).add(index, newmessageObj);
						treeViewer.refresh();
					}
				}
			}
		});
		
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
					MessageObj col = (MessageObj) objs[i];
					deleteMessageObj(col);
					((List<MessageObj>)treeViewer.getInput()).remove(col);
				}
				treeViewer.refresh();
			}
		});
	}
	
	private static class ViewerLabelProvider extends StyledCellLabelProvider implements ILabelProvider {
		public Image getImage(Object element) {
			Image image = ImageUtil.getImageFromURL("/messagesend_16.png");
			if(element instanceof MessageObj){
				return image;
			}
			return null;
		}
		public String getText(Object element) {
			MessageObj treeElement = (MessageObj) element;
			return treeElement.getName()/* + " -- "*/;
		}
		
		@Override
		public void update(ViewerCell cell) {
			// TODO Auto-generated method stub
			if (cell.getElement() instanceof MessageObj) {
				MessageObj d = (MessageObj) cell.getElement();
				StyledString styledString = new StyledString();
				String decoration = ""/*" -- " + getType(d.getCommandType())*/;
				styledString.append(d.getName()==null?"":d.getName());
				styledString.append(decoration, StyledString.DECORATIONS_STYLER);
				cell.setText(styledString.getString());
				cell.setImage(getImage(d)) ;
				cell.setStyleRanges(styledString.getStyleRanges());
			}
		}
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
	
	/**
	 * EMF添加消息
	 * @param messageObj
	 */
	private void addMessageObj(final MessageObj messageObj){
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				BaseElement baseElement = (BaseElement) operEle;
				
				if(baseElement.getExtensionValues().size()>0){
					
					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
						
						FeatureMap extensionElements=extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__MESSAGE_OBJ, true);
						if(objectElement != null){
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
							        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__MESSAGE_OBJ, messageObj);
							extensionElements.add(extensionElementEntry);
						}
						
					}
				}
				else{
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE
					        .createExtensionAttributeValue();
					baseElement.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
					        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__MESSAGE_OBJ, messageObj);
					extensionElement.getValue().add(extensionElementEntry);
				}
			}
		});
	}
	
	/**
	 * EMF编辑消息
	 * @param oldmessageObj
	 * @param newmessageObj
	 */
	private void editMessageObj(final MessageObj oldmessageObj, final MessageObj newmessageObj){
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				BaseElement baseElement = (BaseElement) operEle;
				
				if(baseElement.getExtensionValues().size()>0){
					
					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
						
						FeatureMap.Entry oldextensionElementEntry = new SimpleFeatureMapEntry(
						        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__MESSAGE_OBJ, oldmessageObj);
						FeatureMap.Entry newextensionElementEntry = new SimpleFeatureMapEntry(
						        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__MESSAGE_OBJ, newmessageObj);
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						extensionElements.remove(oldextensionElementEntry);
						extensionElements.add(newextensionElementEntry);
					}
				}
			}
		});
	}
	
	/**
	 * EMF删除消息
	 * @param messageObj
	 */
	private void deleteMessageObj(final MessageObj messageObj){
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				BaseElement baseElement = (BaseElement) operEle;
				
				if(baseElement.getExtensionValues().size()>0){
					
					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
						
						FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
						        (org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__MESSAGE_OBJ, messageObj);
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						extensionElements.remove(extensionElementEntry);
						
					}
				}
			}
		});
	}
	
	/**
	 * 拿到流程定义里面的消息
	 * @return
	 */
	private List<MessageObj> getMessageObjs(){
		BaseElement baseElement = (BaseElement) operEle;
		List<MessageObj> messageObjs = new ArrayList<MessageObj>();
		
		if(baseElement.getExtensionValues().size()>0)
		{
			for(ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()){
				FeatureMap extensionElements = extensionAttributeValue.getValue();
				Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__MESSAGE_OBJ, true);
				if(objectElement != null){
					for (MessageObj messageObj : (List<MessageObj>) objectElement) {
						messageObjs.add(messageObj);
					}
				}
			}
		}
		
		return messageObjs;
	}
}
