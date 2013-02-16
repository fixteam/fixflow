package com.founder.fix.fixflow.designer.modeler.ui.property.usertask;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.HumanPerformer;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy;
import com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.ResourceFilter;
import com.founder.fix.fixflow.designer.modeler.ui.common.CreateShareTypeDialog;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.persistence.GroupInfoPersistence;
import com.founder.fix.fixflow.designer.persistence.GroupInfoTo;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import com.founder.fix.fixflow.designer.util.ImageUtil;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;

import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;

public class UserTaskAssignmentPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private TreeViewer treeViewer;
	private TreeViewer treeViewer_1;
	private Tree tree;
	private Button newButton;
	private Button editButton;
	private Button deleteButton;
	private Button upButton;
	private Button downButton;
	/*
	 * private Button addbutton; private Button deletebutton; private Button
	 * editbutton;
	 */

	ComboViewer comboViewerAssignPolicy;
	Combo comboAssignPolicy;

	ExpressionComboViewer expressionComboViewerBDS;
	Combo comboBDS;

	Text assignActionTest;
	Button btnCheckButton;

	public UserTaskAssignmentPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public UserTaskAssignmentPropertiesComposite(Composite parent, int style) {
		super(parent, style);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUI() {
		setLayout(null);

		Label actorLabel = new Label(this, SWT.NONE);
		actorLabel.setBounds(27, 67, 72, 20);
		actorLabel.setText("完成人选择器");

		toolkit.adapt(actorLabel, true, true);

		treeViewer = new TreeViewer(this, SWT.BORDER);
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setLabelProvider(new ViewerLabelProvider());
		tree = treeViewer.getTree();
		tree.setBounds(27, 91, 192, 132);

		toolkit.adapt(tree, true, true);

		editButton = new Button(this, SWT.NONE);
		editButton.setBounds(225, 132, 55, 22);
		editButton.setText("编辑...");

		toolkit.adapt(editButton, true, true);

		newButton = new Button(this, SWT.NONE);
		newButton.setBounds(225, 108, 55, 22);
		newButton.setText("创建...");

		toolkit.adapt(newButton, true, true);

		deleteButton = new Button(this, SWT.NONE);
		deleteButton.setBounds(225, 155, 55, 22);
		deleteButton.setText("移除");

		toolkit.adapt(deleteButton, true, true);

		upButton = new Button(this, SWT.NONE);
		upButton.setText("向上");
		upButton.setBounds(225, 178, 55, 22);
		upButton.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				ResourceRole resourceRole = (ResourceRole) selection.getFirstElement();
				int idx = ((List<ResourceRole>) treeViewer.getInput()).indexOf(resourceRole);
				if (idx != 0) {
					((List<ResourceRole>) treeViewer.getInput()).remove(resourceRole);
					deleteResourceRole(resourceRole);
					((List<ResourceRole>) treeViewer.getInput()).add(idx - 1, resourceRole);
					addResourceRole(resourceRole, idx - 1);
				}
				treeViewer.refresh();
				updateButtons();
			}
		});

		toolkit.adapt(upButton, true, true);

		downButton = new Button(this, SWT.NONE);
		downButton.setText("向下");
		downButton.setBounds(225, 201, 55, 22);
		downButton.addListener(SWT.Selection, new Listener() {

			@SuppressWarnings("unchecked")
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				ResourceRole resourceRole = (ResourceRole) selection.getFirstElement();
				int idx = ((List<ResourceRole>) treeViewer.getInput()).indexOf(resourceRole);
				if (idx != ((List<ResourceRole>) treeViewer.getInput()).size() - 1) {
					((List<ResourceRole>) treeViewer.getInput()).remove(resourceRole);
					deleteResourceRole(resourceRole);
					((List<ResourceRole>) treeViewer.getInput()).add(idx + 1, resourceRole);
					addResourceRole(resourceRole, idx + 1);
				}
				treeViewer.refresh();
				updateButtons();
			}
		});

		toolkit.adapt(downButton, true, true);

		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(245, 24, 60, 12);
		lblNewLabel.setText("自定义分配");
		lblNewLabel.setVisible(false);

		assignActionTest = new Text(this, SWT.BORDER);

		assignActionTest.setBounds(291, 64, 41, 18);
		assignActionTest.setVisible(false);

		toolkit.adapt(lblNewLabel, true, true);

		toolkit.adapt(assignActionTest, true, true);

		btnCheckButton = new Button(this, SWT.CHECK);

		btnCheckButton.setBounds(287, 62, 60, 16);
		btnCheckButton.setText("禁用");

		btnCheckButton.setVisible(false);

		toolkit.adapt(btnCheckButton, true, true);

		toolkit.adapt(assignActionTest, true, true);

		comboViewerAssignPolicy = new ComboViewer(this, SWT.READ_ONLY);
		comboAssignPolicy = comboViewerAssignPolicy.getCombo();
		comboAssignPolicy.setBounds(27, 34, 192, 23);
		toolkit.adapt(comboAssignPolicy, true, true);

		comboViewerAssignPolicy.setLabelProvider(new policyViewerLabelProvider());
		comboViewerAssignPolicy.setContentProvider(new ArrayContentProvider());
		comboViewerAssignPolicy.setInput(getAssignPolicyList());

		
		List<AssignPolicy> assignPolicies = getAssignPolicyList();
		for (int i = 0; i < assignPolicies.size(); i++) {
			if (assignPolicies.get(i).getId().equals("potentialOwner")) {
				comboAssignPolicy.select(i);
				break;
			}
		}
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(27, 10, 48, 15);
		lblNewLabel_1.setText("分配策略");
		toolkit.adapt(lblNewLabel_1, true, true);

		expressionComboViewerBDS = new ExpressionComboViewer(this, SWT.READ_ONLY);
		Combo comboBDS = expressionComboViewerBDS.getCombo();
		comboBDS.setBounds(225, 34, 192, 23);
		toolkit.adapt(comboBDS, true, true);

		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(225, 10, 61, 15);
		lblNewLabel_2.setText("分配表达式");
		toolkit.adapt(lblNewLabel_2, true, true);

		// 暂时隐藏功能 2012-2-22
		/*
		 * Label filterLabel = new Label(this, SWT.NONE);
		 * filterLabel.setBounds(365, 57, 36, 20); filterLabel.setText("过滤器");
		 * 
		 * widgets.add(filterLabel); toolkit.adapt(filterLabel, true, true);
		 * 
		 * treeViewer_1 = new TreeViewer(this, SWT.BORDER);
		 * treeViewer_1.setContentProvider(new TreeContentProvider());
		 * treeViewer_1.setLabelProvider(new ViewerLabelProvider_1()); Tree
		 * tree_1 = treeViewer_1.getTree(); tree_1.setEnabled(false);
		 * tree_1.setBounds(407, 10, 192, 132);
		 * 
		 * widgets.add(tree_1); toolkit.adapt(tree_1, true, true);
		 * 
		 * addbutton = new Button(this, SWT.NONE); addbutton.setEnabled(false);
		 * 
		 * addbutton.setBounds(605, 73, 55, 22); addbutton.setText("添加...");
		 * 
		 * widgets.add(addbutton); toolkit.adapt(addbutton, true, true);
		 * 
		 * deletebutton = new Button(this, SWT.NONE);
		 * deletebutton.setEnabled(false); deletebutton.setBounds(605, 120, 55,
		 * 22); deletebutton.setText("移除");
		 * 
		 * widgets.add(deletebutton); toolkit.adapt(deletebutton, true, true);
		 * 
		 * editbutton = new Button(this, SWT.NONE);
		 * editbutton.setEnabled(false); editbutton.setBounds(605, 97, 55, 22);
		 * editbutton.setText("编辑...");
		 * 
		 * widgets.add(editbutton); toolkit.adapt(editbutton, true, true);
		 */

	}

	private static class policyViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		public String getText(Object element) {
			AssignPolicy assignPolicy = (AssignPolicy) element;
			return assignPolicy.getName();
		}
	}

	public AssignPolicy getAssignPolicy(String type) {
		for (AssignPolicy assignPolicy : getAssignPolicyList()) {
			if (type.equals(assignPolicy.getId())) {
				return assignPolicy;
			}
		}
		return null;
	}

	private List<AssignPolicy> getAssignPolicyList() {
		return FixFlowConfigUtil.getFixFlowConfig().getAssignPolicyConfig().getAssignPolicy();
	}

	@Override
	public void createUIBindings(EObject eObject) {
		// TODO Auto-generated method stub

		final UserTask userTask = (UserTask) be;
		if (userTask.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {

					if (entry.getValue() instanceof AssignPolicyType) {
						AssignPolicyType assignPolicyType = (AssignPolicyType) entry.getValue();
						//AssignPolicy assignPolicy = getAssignPolicy(assignPolicyType.getId());

						if (assignPolicyType.getExpression() != null) {
							ExpressionTo expressionTo = new ExpressionTo();
							expressionTo.setName(assignPolicyType.getExpression().getName());
							expressionTo.setExpressionText(assignPolicyType.getExpression().getValue());
							expressionComboViewerBDS.setDefaultExpressionInput(expressionTo);
						}

						List<AssignPolicy> assignPolicies = getAssignPolicyList();
						for (int i = 0; i < assignPolicies.size(); i++) {
							if (assignPolicies.get(i).getId().equals(assignPolicyType.getId())) {
								comboAssignPolicy.select(i);
								break;
							}
						}

						break;
					}

				}

			}

		}

		treeViewer.setInput(getResourceRoles());

		expressionComboViewerBDS.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setFormUriViewExtensionExpression(userTask, event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}
				});

			}
		});

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				updateButtons();
			}
		});

		comboViewerAssignPolicy.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				StructuredSelection StructuredSelection = (StructuredSelection) event.getSelection();
				// event.getSelectionProvider();
				final AssignPolicy assignPolicy = (AssignPolicy) StructuredSelection.getFirstElement();
				// comboViewerAssignPolicy.getSelection();

				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						UserTask userTask = (UserTask) be;

						for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {

							FeatureMap extensionElements = extensionAttributeValue.getValue();
							for (Entry entry : extensionElements) {
								if (entry.getValue() instanceof AssignPolicyType) {

									AssignPolicyType assignPolicyType = (AssignPolicyType) entry.getValue();
									assignPolicyType.setId(assignPolicy.getId());
									// assignPolicyType.getExpression().setName(expressionTo.getName());
									// assignPolicyType.getExpression().setValue(expressionTo.getExpressionText());

									return;
								}
							}

						}

						AssignPolicyType assignPolicyType = FixFlowFactory.eINSTANCE.createAssignPolicyType();
						assignPolicyType.setId(assignPolicy.getId());

						FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
								(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE, assignPolicyType);

						if (userTask.getExtensionValues().size() > 0) {
							userTask.getExtensionValues().get(0).getValue().add(extensionElementEntry);
						} else {
							ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
							extensionElement.getValue().add(extensionElementEntry);
							userTask.getExtensionValues().add(extensionElement);
						}

					}

				});

			}
		});

		btnCheckButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				if (btnCheckButton.getSelection()) {
					assignActionTest.setText("");
					assignActionTest.setEnabled(false);
				} else {
					assignActionTest.setEnabled(true);
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// 绑定流程分类
		bindAttributeText(assignActionTest, FixFlowPackage.Literals.DOCUMENT_ROOT__ASSIGN_ACTION);

		if ((assignActionTest.getText() != null) && (!assignActionTest.getText().equals(""))) {
			btnCheckButton.setSelection(false);
			assignActionTest.setEnabled(true);

		} else {
			btnCheckButton.setSelection(true);
			assignActionTest.setEnabled(false);
		}

		// 设置按钮可用性
		updateButtons();

		// treeViewer_1.setInput(getResourceFilters());

		newButton.addListener(SWT.Selection, addListener);

		editButton.addListener(SWT.Selection, editListener);

		deleteButton.addListener(SWT.Selection, deleteListener);

		/*
		 * addbutton.addListener(SWT.Selection, addfilterListener);
		 * 
		 * editbutton.addListener(SWT.Selection, editfilterListener);
		 * 
		 * deletebutton.addListener(SWT.Selection, deletefilterListener);
		 */

		// FeatureMap.Entry extensionAttributeEntry1 = new
		// SimpleFeatureMapEntry((Internal)
		// FixFlowPackage.Literals.DOCUMENT_ROOT__INCLUDE_EXCLUSION,
		// inorexcludecombo.getData(inorexcludecombo.getSelectionIndex()+"").toString());
		// potentialOwner.getAnyAttribute().add(extensionAttributeEntry1);

	}

	private void setFormUriViewExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {

		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof AssignPolicyType) {
					if (expressionTo == null) {
						AssignPolicyType assignPolicyType = (AssignPolicyType) entry.getValue();
						assignPolicyType.setExpression(null);
						// extensionElements.remove(entry);
					} else {
						AssignPolicyType assignPolicyType = (AssignPolicyType) entry.getValue();
						if (assignPolicyType.getExpression() != null) {
							assignPolicyType.getExpression().setName(expressionTo.getName());
							assignPolicyType.getExpression().setValue(expressionTo.getExpressionText());
						} else {
							Expression expression = FixFlowFactory.eINSTANCE.createExpression();
							expression.setName(expressionTo.getName());
							expression.setValue(expressionTo.getExpressionText());
							assignPolicyType.setExpression(expression);
						}

					}

					return;
				}
			}

		}
		if (expressionTo != null) {
			AssignPolicyType assignPolicyType = FixFlowFactory.eINSTANCE.createAssignPolicyType();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			assignPolicyType.setExpression(expression);

			FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
					(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__ASSIGN_POLICY_TYPE, assignPolicyType);

			if (baseElement.getExtensionValues().size() > 0) {
				baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
			} else {
				ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
				extensionElement.getValue().add(extensionElementEntry);
				baseElement.getExtensionValues().add(extensionElement);
			}
		}

	}

	private static class ViewerLabelProvider extends StyledCellLabelProvider implements ILabelProvider {
		public Image getImage(Object element) {
			Image image2 = ImageUtil.getImageFromURL("/user_16_hot.png");
			Image image1 = ImageUtil.getImageFromURL("/group_16.png");
			if (element instanceof PotentialOwner) {
				return image1;
			}
			if (element instanceof HumanPerformer) {
				return image2;
			}
			return null;
		}

		public String getText(Object element) {
			return null;
		}

		public GroupInfoTo getGroupInfoTo(String rstype) {
			for (GroupInfoTo groupInfoTo : GroupInfoPersistence.getGroupInfoTos()) {
				if (rstype.equals(groupInfoTo.getTypeId())) {
					return groupInfoTo;
				}
			}
			return null;
		}

		public String getType(String type) {
			String rstype = "";
			if (type.equals("INCLUDE")) {
				rstype = "包含";
				return rstype;
			}
			if (type.equals("EXCLUSION")) {
				rstype = "排除";
				return rstype;
			}
			return rstype;
		}

		@Override
		public void update(ViewerCell cell) {
			// TODO Auto-generated method stub
			if (cell.getElement() instanceof ResourceRole) {
				ResourceRole d = (ResourceRole) cell.getElement();
				StyledString styledString = new StyledString();
				String decoration = " -- " + getGroupInfoTo(d.getAnyAttribute().get(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, true).toString()).getTypeName() + " -- "
						+ getType(d.getAnyAttribute().get(FixFlowPackage.Literals.DOCUMENT_ROOT__INCLUDE_EXCLUSION, true).toString());
				styledString.append(d.getName());
				styledString.append(decoration, StyledString.DECORATIONS_STYLER);
				cell.setText(styledString.getString());
				cell.setImage(getImage(d));
				cell.setStyleRanges(styledString.getStyleRanges());
			}
		}
	}

	@SuppressWarnings("unused")
	private static class ViewerLabelProvider_1 extends LabelProvider {
		public Image getImage(Object element) {
			@SuppressWarnings("deprecation")
			String image1 = ISharedImages.IMG_OBJS_TASK_TSK;
			if (element instanceof ResourceFilter) {
				return PlatformUI.getWorkbench().getSharedImages().getImage(image1);
			}
			return super.getImage(element);
		}

		@Override
		public String getText(Object element) {
			// TODO Auto-generated method stub
			ResourceFilter treeElement = (ResourceFilter) element;
			return treeElement.getName();
		}
	}

	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
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

	Listener addListener = new Listener() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			CreateShareTypeDialog cstd = new CreateShareTypeDialog(getShell(), treeViewer);
			cstd.setBlockOnOpen(true);
			if (cstd != null && cstd.open() == InputDialog.OK) {
				((List<ResourceRole>) treeViewer.getInput()).add(cstd.getResourceRole());
				treeViewer.refresh();
				addResourceRole(cstd.getResourceRole());
			}
		}
	};

	Listener addfilterListener = new Listener() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			CreateResourceFilterDialog crfd = new CreateResourceFilterDialog(getShell());
			crfd.setBlockOnOpen(true);
			if (crfd != null && crfd.open() == InputDialog.OK) {
				((List<ResourceFilter>) treeViewer_1.getInput()).add(crfd.getResourceFilter());
				treeViewer_1.refresh();
				addResourceFilter(crfd.getResourceFilter());
			}
		}
	};

	Listener deleteListener = new Listener() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			if (!treeViewer.getSelection().isEmpty()) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				ResourceRole resourceRole = (ResourceRole) selection.getFirstElement();
				deleteResourceRole(resourceRole);
				((List<PotentialOwner>) treeViewer.getInput()).remove(resourceRole);
				treeViewer.refresh();
			}
		}
	};

	Listener deletefilterListener = new Listener() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			if (!treeViewer_1.getSelection().isEmpty()) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer_1.getSelection();
				ResourceFilter resourceFilter = (ResourceFilter) selection.getFirstElement();
				deleteResourceFilter(resourceFilter);
				((List<ResourceFilter>) treeViewer_1.getInput()).remove(resourceFilter);
				treeViewer_1.refresh();
			}
		}
	};

	Listener editListener = new Listener() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			if (!treeViewer.getSelection().isEmpty()) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				ResourceRole resourceRole = (ResourceRole) selection.getFirstElement();
				CreateShareTypeDialog cstd = new CreateShareTypeDialog(getShell(), resourceRole, treeViewer);
				cstd.setBlockOnOpen(true);
				if (cstd != null && cstd.open() == InputDialog.OK) {
					ResourceRole newrResourceRole = cstd.getResourceRole();
					editResourceRole(resourceRole, newrResourceRole);
					int index = ((List<ResourceRole>) treeViewer.getInput()).indexOf(resourceRole);
					((List<ResourceRole>) treeViewer.getInput()).remove(resourceRole);
					((List<ResourceRole>) treeViewer.getInput()).add(index, newrResourceRole);
					treeViewer.refresh();
				}
			}
		}
	};

	Listener editfilterListener = new Listener() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(Event event) {
			// TODO Auto-generated method stub
			if (!treeViewer_1.getSelection().isEmpty()) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer_1.getSelection();
				ResourceFilter resourceFilter = (ResourceFilter) selection.getFirstElement();
				CreateResourceFilterDialog crfd = new CreateResourceFilterDialog(getShell(), resourceFilter);
				crfd.setBlockOnOpen(true);
				if (crfd != null && crfd.open() == InputDialog.OK) {
					ResourceFilter newResourceFilter = crfd.getResourceFilter();
					editResourceFilter(resourceFilter, newResourceFilter);
					int index = ((List<ResourceFilter>) treeViewer_1.getInput()).indexOf(resourceFilter);
					((List<ResourceFilter>) treeViewer_1.getInput()).remove(resourceFilter);
					((List<ResourceFilter>) treeViewer_1.getInput()).add(index, newResourceFilter);
					treeViewer_1.refresh();
				}
			}
		}
	};
	private Label lblNewLabel;
	@SuppressWarnings("unused")
	private Text text;

	private List<ResourceRole> getResourceRoles() {
		UserTask userTask = (UserTask) be;
		List<ResourceRole> resourceRoles = new ArrayList<ResourceRole>();
		for (ResourceRole resourceRole : userTask.getResources()) {
			resourceRoles.add(resourceRole);
		}
		return resourceRoles;
	}

	/*
	 * @SuppressWarnings("unchecked") private List<ResourceFilter>
	 * getResourceFilters(){ UserTask userTask=(UserTask)be;
	 * List<ResourceFilter> resourceFilters = new ArrayList<ResourceFilter>();
	 * 
	 * if(userTask.getExtensionValues().size()>0) { for(ExtensionAttributeValue
	 * extensionAttributeValue : userTask.getExtensionValues()){ FeatureMap
	 * extensionElements = extensionAttributeValue.getValue(); Object
	 * objectElement =
	 * extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER
	 * , true); if(objectElement != null){
	 * 
	 * List<ResourceFilter> resourceFiltersList=(List<ResourceFilter>)
	 * objectElement; for (ResourceFilter resourceFilter : resourceFiltersList)
	 * { resourceFilters.add(resourceFilter); }
	 * 
	 * 
	 * } } }
	 * 
	 * return resourceFilters; }
	 */

	/**
	 * EMF添加任务分配
	 */
	private void addResourceRole(final ResourceRole resourceRole) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;

				userTask.getResources().add(resourceRole);
			}
		});
	}

	/**
	 * 向上向下时EMF添加
	 * 
	 * @param resourceRole
	 * @param index
	 */
	private void addResourceRole(final ResourceRole resourceRole, final int index) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;

				userTask.getResources().add(index, resourceRole);
			}
		});
	}

	/**
	 * EMF删除任务分配
	 * 
	 * @param resourceRole
	 */
	private void deleteResourceRole(final ResourceRole resourceRole) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;

				if (userTask.getResources().size() > 0) {
					userTask.getResources().remove(resourceRole);
				}
			}
		});
	}

	/**
	 * EMF编辑任务分配
	 * 
	 * @param oldresourceRole
	 * @param newresourceRole
	 */
	private void editResourceRole(final ResourceRole oldresourceRole, final ResourceRole newresourceRole) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;

				if (userTask.getResources().size() > 0) {
					userTask.getResources().remove(oldresourceRole);
					userTask.getResources().add(newresourceRole);
				}
			}
		});
	}

	/**
	 * EMF添加过滤器
	 * 
	 * @param resourceFilter
	 */
	private void addResourceFilter(final ResourceFilter resourceFilter) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;

				if (userTask.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {

						FeatureMap extensionElements = extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER, true);
						if (objectElement != null) {
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER, resourceFilter);
							extensionElements.add(extensionElementEntry);
						}

					}
				} else {
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
					userTask.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
							(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER, resourceFilter);
					extensionElement.getValue().add(extensionElementEntry);
				}
			}
		});
	}

	/**
	 * EMF添加过滤器
	 * 
	 * @param resourceFilter
	 */
	@SuppressWarnings("unused")
	private void addResourceFilter(final ResourceFilter resourceFilter, final int index) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;

				if (userTask.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {

						FeatureMap extensionElements = extensionAttributeValue.getValue();
						Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER, true);
						if (objectElement != null) {
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER, resourceFilter);
							extensionElements.add(index, extensionElementEntry);
						}

					}
				} else {
					ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
					userTask.getExtensionValues().add(extensionElement);
					FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
							(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER, resourceFilter);
					extensionElement.getValue().add(index, extensionElementEntry);
				}
			}
		});
	}

	/**
	 * EMF编辑过滤器
	 * 
	 * @param oldresourceFilter
	 * @param newresourceFilter
	 */
	private void editResourceFilter(final ResourceFilter oldresourceFilter, final ResourceFilter newresourceFilter) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;

				if (userTask.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {

						FeatureMap.Entry oldextensionElementEntry = new SimpleFeatureMapEntry(
								(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER, oldresourceFilter);
						FeatureMap.Entry newextensionElementEntry = new SimpleFeatureMapEntry(
								(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER, newresourceFilter);
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						extensionElements.remove(oldextensionElementEntry);
						extensionElements.add(newextensionElementEntry);
					}
				}
			}
		});
	}

	/**
	 * EMF删除过滤器
	 * 
	 * @param resourceFilter
	 */
	private void deleteResourceFilter(final ResourceFilter resourceFilter) {
		@SuppressWarnings("restriction")
		TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				UserTask userTask = (UserTask) be;

				if (userTask.getExtensionValues().size() > 0) {

					for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {

						FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
								(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_FILTER, resourceFilter);
						FeatureMap extensionElements = extensionAttributeValue.getValue();
						extensionElements.remove(extensionElementEntry);

					}
				}
			}
		});
	}

	/**
	 * 设置按钮可用性
	 */
	@SuppressWarnings("unchecked")
	public void updateButtons() {
		Object selectedPage = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
		int index = 0;
		while (selectedPage != null && ((List<ResourceRole>) treeViewer.getInput()).get(index) != null
				&& !selectedPage.equals(((List<ResourceRole>) treeViewer.getInput()).get(index))) {
			index++;
		}
		deleteButton.setEnabled(selectedPage != null);
		editButton.setEnabled(selectedPage != null);
		upButton.setEnabled(selectedPage != null && index != 0);
		downButton.setEnabled(selectedPage != null && index != treeViewer.getTree().getItemCount() - 1);
	}
}
