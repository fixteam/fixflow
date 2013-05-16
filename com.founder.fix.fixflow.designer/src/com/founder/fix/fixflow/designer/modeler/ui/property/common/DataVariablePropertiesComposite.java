package com.founder.fix.fixflow.designer.modeler.ui.property.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.swt.widgets.Composite;

import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.util.DataVarUtil;
import com.founder.fix.fixflow.designer.util.FormUtil;
import com.founder.fix.fixflow.designer.util.ImageUtil;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DataVariablePropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {


	private Text text;
	private Button addbutton;
	private Button editbutton;
	private Button removebutton;
	private Button updatebutton;
	private TreeViewer treeViewer;
	private Tree tree;
	private Button buttonImp;

	
	public DataVariablePropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public DataVariablePropertiesComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createUI() {

		setLayout(null);

		treeViewer = new TreeViewer(this, SWT.BORDER | SWT.MULTI);
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setLabelProvider(new ViewerLabelProvider());

		tree = treeViewer.getTree();
		tree.setBounds(10, 34, 245, 128);


		toolkit.adapt(tree, true, true);

		addbutton = new Button(this, SWT.NONE);
		addbutton.setText("添加...");
		addbutton.setBounds(261, 71, 55, 22);

		toolkit.adapt(addbutton, true, true);

		editbutton = new Button(this, SWT.NONE);
		editbutton.setText("编辑...");
		editbutton.setBounds(261, 94, 55, 22);

	
		toolkit.adapt(editbutton, true, true);

		removebutton = new Button(this, SWT.NONE);
		removebutton.setText("移除");
		removebutton.setBounds(261, 117, 55, 22);


		toolkit.adapt(removebutton, true, true);

		updatebutton = new Button(this, SWT.NONE);
		updatebutton.setEnabled(false);
		updatebutton.setText("提升");
		updatebutton.setBounds(261, 140, 55, 22);
		updatebutton.setVisible(false);


		toolkit.adapt(updatebutton, true, true);

		text = new Text(this, SWT.BORDER | SWT.SEARCH);
		text.setBounds(10, 10, 245, 19);


		toolkit.adapt(text, true, true);

		if (SectionBpmnElement.sectionElement instanceof Process) {
			buttonImp = new Button(this, SWT.NONE);
			buttonImp.setText("导入...");
			buttonImp.setBounds(261, 34, 55, 22);

			toolkit.adapt(buttonImp, true, true);

		}

	}

	@Override
	public void createUIBindings(EObject eObject) {
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});
		
		// 设置按钮可用性
		updateButtons();
		
		treeViewer.setInput(getDataVariable());
		addbutton.addListener(SWT.Selection, addlisListener);
		editbutton.addListener(SWT.Selection, editlisListener);
		removebutton.addListener(SWT.Selection, deletelisListener);

		if (SectionBpmnElement.sectionElement instanceof Process) {
			buttonImp.addListener(SWT.Selection, implisListener);
		}
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

	Listener implisListener = new Listener() {

		@Override
		public void handleEvent(Event event) {
			// 弹出操作
			final DataVariableImportDialog dvid = new DataVariableImportDialog(getShell());
			dvid.setBlockOnOpen(true);
			if (dvid != null && dvid.open() == InputDialog.OK) {
				// 返回选择到的业务对象

				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@SuppressWarnings("unchecked")
					@Override
					protected void doExecute() {

						DataObjImport dataObjImport = dvid.getDataObjImport();
						
						List<String> froms = FormUtil.getHtmlFilesRealPathFromBizobjId(dataObjImport.getId(), dataObjImport.getFilePath());
						
						List<DataVariable> dataVariables = new ArrayList<DataVariable>();
						
						List<DataVariable> removeDataVariables = new ArrayList<DataVariable>();
						
						for(String form : froms){
							DataVariable dataVariable = FixFlowFactory.eINSTANCE.createDataVariable();
							dataVariable.setId(FormUtil.getFormNameByFormPath(form).replace(".html", "表单"));
							dataVariable.setDataType("java.lang.String");
							Expression expression = FixFlowFactory.eINSTANCE.createExpression();
							expression.setName(dataVariable.getId());
							expression.setValue("\""+form+"\"");
							dataVariable.setExpression(expression);
							
							dataVariables.add(dataVariable);
							
							//相同的节点需要先删除掉
							replaceElement(removeDataVariables, dataVariable);
							
						}
						
						dataVariables.removeAll(removeDataVariables);
						addDataVariables(dataVariables);
						((List<DataVariable>) treeViewer.getInput()).addAll(dataVariables);
						
						List<DataVariableImport> dataVariableImports = dataObjImport.getDataVariableList(dvid.getImportType());
						
						removeDataVariables = new ArrayList<DataVariable>();
						
						dataVariables = new ArrayList<DataVariable>();
						
						for (DataVariableImport dataVariableImport : dataVariableImports) {

							DataVariable dataVariable = FixFlowFactory.eINSTANCE.createDataVariable();
							dataVariable.setId(dataVariableImport.getName());
							dataVariable.setDataType(dataVariableImport.getDataType());

							Expression expression = FixFlowFactory.eINSTANCE.createExpression();
							expression.setName(dataVariable.getId());
							expression.setValue(dataVariableImport.getVariableValue());
							dataVariable.setExpression(expression);
							
							dataVariables.add(dataVariable);
							//相同的节点需要先删除掉
							replaceElement(removeDataVariables, dataVariable);
							
						}
						
						dataVariables.removeAll(removeDataVariables);
						addDataVariables(dataVariables);
						((List<DataVariable>) treeViewer.getInput()).addAll(dataVariables);
						
						treeViewer.refresh();
					}
				});

			}
		}
	};

	Listener addlisListener = new Listener() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			DataVariableDialog dvd = new DataVariableDialog(getShell());
			dvd.setBlockOnOpen(true);
			if (dvd != null && dvd.open() == InputDialog.OK) {
				((List<DataVariable>) treeViewer.getInput()).add(dvd.getDataVariable());
				treeViewer.refresh();
				addDataVariable(dvd.getDataVariable());
				updateButtons();
			}
		}
	};

	Listener editlisListener = new Listener() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			if (!treeViewer.getSelection().isEmpty()) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				DataVariable dataVariable = (DataVariable) selection.getFirstElement();
				DataVariableDialog dvd = new DataVariableDialog(getShell(), dataVariable);
				dvd.setBlockOnOpen(true);
				if (dvd != null && dvd.open() == InputDialog.OK) {
					DataVariable newDataVariable = dvd.getDataVariable();
					editDataVariable(dataVariable, newDataVariable);
					int index = ((List<DataVariable>) treeViewer.getInput()).indexOf(dataVariable);
					((List<DataVariable>) treeViewer.getInput()).remove(dataVariable);
					((List<DataVariable>) treeViewer.getInput()).add(index, newDataVariable);
					treeViewer.refresh();
				}
			}
		}
	};

	Listener deletelisListener = new Listener() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
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
				DataVariable col = (DataVariable) objs[i];
				deleteDataVariable(col);
				((List<DataVariable>)treeViewer.getInput()).remove(col);
			}
			treeViewer.refresh();
			updateButtons();
			/*if (!treeViewer.getSelection().isEmpty()) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				DataVariable dataVariable = (DataVariable) selection.getFirstElement();
				deleteDataVariable(dataVariable);
				((List<DataVariable>) treeViewer.getInput()).remove(dataVariable);
				treeViewer.refresh();
				updateButtons();
			}*/
		}
	};

	private void addDataVariable(final DataVariable dataVariable) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				BaseElement baseElement = (BaseElement)SectionBpmnElement.sectionElement;

				if (baseElement.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

						FeatureMap extensionElements = extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
						if (objectElement != null) {
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, dataVariable);
							extensionElements.add(extensionElementEntry);
						}

					}
				} else {
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
					baseElement.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE,
							dataVariable);
					extensionElement.getValue().add(extensionElementEntry);
				}
			}
		});
	}

	private void editDataVariable(final DataVariable oldDataVariable, final DataVariable newDataVariable) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				BaseElement baseElement = (BaseElement)SectionBpmnElement.sectionElement;

				if (baseElement.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

						FeatureMap.Entry oldextensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE,
								oldDataVariable);
						FeatureMap.Entry newextensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE,
								newDataVariable);
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						extensionElements.remove(oldextensionElementEntry);
						extensionElements.add(newextensionElementEntry);
					}
				}
			}
		});
	}

	private void deleteDataVariable(final DataVariable dataVariable) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				BaseElement baseElement = (BaseElement)SectionBpmnElement.sectionElement;

				if (baseElement.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

						FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE,
								dataVariable);
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						extensionElements.remove(extensionElementEntry);

					}
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	private List<DataVariable> getDataVariable() {
		BaseElement baseElement = (BaseElement)SectionBpmnElement.sectionElement;
		List<DataVariable> dataVariables = new ArrayList<DataVariable>();

		if (baseElement.getExtensionValues().size() > 0) {
			for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
				FeatureMap extensionElements = extensionAttributeValue.getValue();
				Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
				if (objectElement != null) {
					List<DataVariable> dataVariableList = (List<DataVariable>) objectElement;
					for (DataVariable dataVariable : dataVariableList) {
						dataVariables.add(dataVariable);
					}
				}
			}
		}

		return dataVariables;
	}
	
	/**
	 * 设置按钮可用性
	 */
	@SuppressWarnings("unchecked")
	public void updateButtons() {
		Object[] objs = null;
		ISelection sel = treeViewer.getSelection();
		if(sel != null) {
			objs = ((IStructuredSelection) sel).toArray();
		}
		Object selectedPage = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
		int index = 0;
		while (selectedPage != null && ((List<DataVariable>) treeViewer.getInput()).get(index) != null && !selectedPage.equals(((List<DataVariable>) treeViewer.getInput()).get(index))) {
			index++;
		}
		removebutton.setEnabled(selectedPage != null);
		editbutton.setEnabled(selectedPage != null && objs != null && objs.length<2);
	}
	
	/**
	  * replaceElement(将相同的值删除掉)
	  * @Title: replaceElement
	  * @Description: TODO
	  * @param @param dataVariable    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private void replaceElement(List<DataVariable> removedataVariables, DataVariable dataVariable) {
		//相同的节点需要先删除掉
		List<DataVariable> dataVariables = (List<DataVariable>) treeViewer.getInput();
		if(dataVariables != null && dataVariables.size() > 0) {
			for (int i = 0; i < dataVariables.size(); i++) {
				DataVariable dataVariable2 = dataVariables.get(i);
				if(dataVariable2.getId().equals(dataVariable.getId())) {
					//在EMF中同样删掉--20120903 wy #4840
					removedataVariables.add(dataVariable2);
					dataVariables.remove(i);
					if(i > 0) {
						i--;
					} else {
						i = 0;
					}
				}
			}
			
			deleteDataVariables(removedataVariables);
		}
	}
	
	
	/**
	 * 删除多个EMF元素
	 * @param removedataVariables
	 */
	private void deleteDataVariables(final List<DataVariable> removedataVariables) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				List<FeatureMap.Entry> entries = new ArrayList<FeatureMap.Entry>();
				
				BaseElement baseElement = (BaseElement)SectionBpmnElement.sectionElement;

				if (baseElement.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
						
						for (DataVariable dataVariable2 : removedataVariables) {
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE,
									dataVariable2);
							entries.add(extensionElementEntry);
						}

						
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						extensionElements.removeAll(entries);

					}
				}
			}
		});
	}
	
	/**
	 * 增加多个EMF元素
	 * @param dataVariables
	 */
	private void addDataVariables(final List<DataVariable> dataVariables) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				List<FeatureMap.Entry> entries = new ArrayList<FeatureMap.Entry>();
				
				BaseElement baseElement = (BaseElement)SectionBpmnElement.sectionElement;

				if (baseElement.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

						FeatureMap extensionElements = extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
						if (objectElement != null) {
							for (DataVariable dataVariable2 : dataVariables) {
								FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
										(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, dataVariable2);
								entries.add(extensionElementEntry);
							}
							
							extensionElements.addAll(entries);
						}

					}
				}
			}
		});
	}
}
