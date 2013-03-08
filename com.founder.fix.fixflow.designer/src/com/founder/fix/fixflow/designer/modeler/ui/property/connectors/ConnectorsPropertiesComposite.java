package com.founder.fix.fixflow.designer.modeler.ui.property.connectors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog.AddConnectorWizard;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog.OverrideWizard;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.dialog.modify.ModifyConnectorWizard;
import com.founder.fix.fixflow.designer.util.ConnectorUtil;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class ConnectorsPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	@SuppressWarnings("unused")
	private BPMNShape shape;
	private Text text;
	private TreeViewer treeViewer;
	private Button addbutton;
	private Button editbutton;
	private Button deletebutton;
	private Button upbutton;
	private Button downbutton;
	private Button movebutton;

	
	public ConnectorsPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public ConnectorsPropertiesComposite(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUI() {

		setLayout(null);

		treeViewer = new TreeViewer(this, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		tree.setBounds(10, 54, 245, 128);


		toolkit.adapt(tree, true, true);
		treeViewer.setLabelProvider(new ViewerLabelProvider());
		treeViewer.setContentProvider(new TreeContentProvider());

		addbutton = new Button(this, SWT.NONE);
		addbutton.setText("添加");
		addbutton.setBounds(261, 47, 55, 22);
		addbutton.addListener(SWT.Selection, new Listener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				OverrideWizard dialog = new OverrideWizard(getShell(), new AddConnectorWizard(SectionBpmnElement.sectionElement));
				dialog.setPageSize(-1, 400); // -1代表宽度自适应, 240为高度
				if (dialog.open() == Dialog.OK) {
					// 获取实例
					((List<ConnectorInstance>) treeViewer.getInput()).add(dialog.getAddedValue());
					treeViewer.refresh();
					// 添加实例
					addConnectorInstance(dialog.getAddedValue());
					updateButtons();
				}
			}
		});

	
		toolkit.adapt(addbutton, true, true);

		editbutton = new Button(this, SWT.NONE);
		editbutton.setText("编辑");
		editbutton.setBounds(261, 70, 55, 22);
		editbutton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if (!treeViewer.getSelection().isEmpty()) {
					// 得到当前选中的ConnectorInstance对象
					IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
					ConnectorInstance connectorInstance = (ConnectorInstance) selection.getFirstElement();

					WizardDialog dialog = new OverrideWizard(getShell(), new ModifyConnectorWizard(SectionBpmnElement.sectionElement, getDiagramEditor(), connectorInstance));
					dialog.setPageSize(-1, 400); // -1代表宽度自适应, 240为高度
					if (dialog.open() == Dialog.OK) {
						treeViewer.refresh();
					}
				}
			}
		});

		toolkit.adapt(editbutton, true, true);

		deletebutton = new Button(this, SWT.NONE);
		deletebutton.setText("移除");
		deletebutton.setBounds(261, 93, 55, 22);
		deletebutton.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if (!treeViewer.getSelection().isEmpty()) {
					// 得到当前选中的ConnectorInstance对象
					IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
					ConnectorInstance connectorInstance = (ConnectorInstance) selection.getFirstElement();
					deleteConnectorInstance(connectorInstance);
					((List<ConnectorInstance>) treeViewer.getInput()).remove(connectorInstance);
					treeViewer.refresh();
					updateButtons();
				}
			}
		});

		
		toolkit.adapt(deletebutton, true, true);

		upbutton = new Button(this, SWT.NONE);
		upbutton.setText("向上");
		upbutton.setBounds(261, 116, 55, 22);
		upbutton.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				ConnectorInstance connectorInstance = (ConnectorInstance) selection.getFirstElement();
				int idx = ((List<ConnectorInstance>) treeViewer.getInput()).indexOf(connectorInstance);
				if (idx != 0) {
					((List<ConnectorInstance>) treeViewer.getInput()).remove(connectorInstance);
					deleteConnectorInstance(connectorInstance);
					((List<ConnectorInstance>) treeViewer.getInput()).add(idx - 1, connectorInstance);
					addConnectorInstance(connectorInstance, idx - 1);
				}
				treeViewer.refresh();
				updateButtons();
			}
		});


		toolkit.adapt(upbutton, true, true);

		text = new Text(this, SWT.BORDER | SWT.SEARCH);
		text.setBounds(10, 30, 245, 19);

	
		toolkit.adapt(text, true, true);

		downbutton = new Button(this, SWT.NONE);
		downbutton.setText("向下");
		downbutton.setBounds(261, 139, 55, 22);
		downbutton.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				ConnectorInstance connectorInstance = (ConnectorInstance) selection.getFirstElement();
				int idx = ((List<ConnectorInstance>) treeViewer.getInput()).indexOf(connectorInstance);
				if (idx != ((List<ConnectorInstance>) treeViewer.getInput()).size() - 1) {
					((List<ConnectorInstance>) treeViewer.getInput()).remove(connectorInstance);
					deleteConnectorInstance(connectorInstance);
					((List<ConnectorInstance>) treeViewer.getInput()).add(idx + 1, connectorInstance);
					addConnectorInstance(connectorInstance, idx + 1);
				}
				treeViewer.refresh();
				updateButtons();
			}
		});


		toolkit.adapt(downbutton, true, true);

		movebutton = new Button(this, SWT.NONE);
		movebutton.setEnabled(false);
		movebutton.setText("移动");
		movebutton.setBounds(261, 162, 55, 22);
		movebutton.setVisible(false);

		
		toolkit.adapt(movebutton, true, true);

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 59, 14);
		lblNewLabel.setText("连接器配置");

	
		toolkit.adapt(lblNewLabel, true, true);

	}

	@Override
	public void createUIBindings(EObject eObject) {
		treeViewer.setInput(getConnectorInstances());
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				updateButtons();
			}
		});

		// 设置按钮可用性
		updateButtons();
	}

	

	/**
	 * 添加连接器实例
	 * 
	 * @param connectorInstance
	 */
	private void addConnectorInstance(final ConnectorInstance connectorInstance) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				BaseElement baseElement = (BaseElement) SectionBpmnElement.sectionElement;
				if (baseElement.getExtensionValues().size() > 0) {
					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__CONNECTOR_INSTANCE, true);
						if (objectElement != null) {
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__CONNECTOR_INSTANCE, connectorInstance);
							extensionElements.add(extensionElementEntry);
						}
					}
				} else {
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
					baseElement.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__CONNECTOR_INSTANCE,
							connectorInstance);
					extensionElement.getValue().add(extensionElementEntry);
				}
			}
		});
	}

	/**
	 * 删除连接器实例
	 * 
	 * @param connectorInstance
	 */
	private void deleteConnectorInstance(final ConnectorInstance connectorInstance) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				BaseElement baseElement = (BaseElement) SectionBpmnElement.sectionElement;

				if (baseElement.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

						FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
								(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__CONNECTOR_INSTANCE, connectorInstance);
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						extensionElements.remove(extensionElementEntry);

					}
				}
			}
		});
	}

	/**
	 * 向上向下时添加连接器实例
	 * 
	 * @param connectorInstance
	 */
	private void addConnectorInstance(final ConnectorInstance connectorInstance, final int index) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				BaseElement baseElement = (BaseElement) SectionBpmnElement.sectionElement;
				if (baseElement.getExtensionValues().size() > 0) {
					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__CONNECTOR_INSTANCE, true);
						if (objectElement != null) {
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__CONNECTOR_INSTANCE, connectorInstance);
							extensionElements.add(index, extensionElementEntry);
						}
					}
				} else {
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
					baseElement.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_COMMAND,
							connectorInstance);
					extensionElement.getValue().add(index, extensionElementEntry);
				}
			}
		});
	}

	private static class ViewerLabelProvider extends StyledCellLabelProvider implements ILabelProvider {
		public Image getImage(Object element) {
			@SuppressWarnings("deprecation")
			String image1 = ISharedImages.IMG_OBJS_TASK_TSK;
			if (element instanceof ConnectorInstance) {
				return PlatformUI.getWorkbench().getSharedImages().getImage(image1);
			}
			return null;
		}

		public String getText(Object element) {
			ConnectorInstance treeElement = (ConnectorInstance) element;
			return treeElement.getConnectorInstanceName() + " -- " + getType(treeElement.getEventType()) + "--" + ConnectorUtil.getConnectorByMenuConnectorId(treeElement.getConnectorInstanceId()).getConnectorNote();
		}

		public String getType(String type) {
			String cntype = "";
			if (type.equals("process-start")) {
				cntype = "启动";
				return cntype;
			}
			if (type.equals("process-abort")) {
				cntype = "终止";
				return cntype;
			}
			if (type.equals("process-end")) {
				cntype = "结束";
				return cntype;
			}
			if (type.equals("node-enter")) {
				cntype = "进入";
				return cntype;
			}
			if (type.equals("task-assign")) {
				cntype = "分配";
				return cntype;
			}
			if (type.equals("node-leave")) {
				cntype = "离开";
				return cntype;
			}
			return cntype;
		}

		@Override
		public void update(ViewerCell cell) {
			// TODO Auto-generated method stub
			if (cell.getElement() instanceof ConnectorInstance) {
				ConnectorInstance d = (ConnectorInstance) cell.getElement();
				StyledString styledString = new StyledString();
				String decoration = " -- " + getType(d.getEventType()) + " -- " + (ConnectorUtil.getConnectorByMenuConnectorId(d.getConnectorId()).getConnectorName() == null ? "" : ConnectorUtil.getConnectorByMenuConnectorId(d.getConnectorId()).getConnectorName());
				styledString.append(d.getConnectorInstanceName());
				styledString.append(decoration, StyledString.DECORATIONS_STYLER);
				cell.setText(styledString.getString());
				cell.setImage(getImage(d));
				cell.setStyleRanges(styledString.getStyleRanges());
			}
		}
	}

	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object inputElement) {
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

	@SuppressWarnings("unchecked")
	private List<ConnectorInstance> getConnectorInstances() {
		BaseElement baseElement = (BaseElement) SectionBpmnElement.sectionElement;
		List<ConnectorInstance> connectorInstances = new ArrayList<ConnectorInstance>();

		if (baseElement.getExtensionValues().size() > 0) {
			for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
				FeatureMap extensionElements = extensionAttributeValue.getValue();
				Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__CONNECTOR_INSTANCE, true);
				if (objectElement != null) {

					List<ConnectorInstance> connectorInstanceList = (List<ConnectorInstance>) objectElement;
					for (ConnectorInstance connectorInstance : connectorInstanceList) {
						connectorInstances.add(connectorInstance);
					}

				}
			}
		}

		return connectorInstances;
	}

	/**
	 * 设置按钮可用性
	 */
	@SuppressWarnings("unchecked")
	public void updateButtons() {
		Object selectedPage = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
		int index = 0;
		while (selectedPage != null && ((List<ConnectorInstance>) treeViewer.getInput()).get(index) != null && !selectedPage.equals(((List<ConnectorInstance>) treeViewer.getInput()).get(index))) {
			index++;
		}
		deletebutton.setEnabled(selectedPage != null);
		editbutton.setEnabled(selectedPage != null);
		upbutton.setEnabled(selectedPage != null && index != 0);
		downbutton.setEnabled(selectedPage != null && index != treeViewer.getTree().getItemCount() - 1);
	}
}
