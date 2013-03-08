package com.founder.fix.fixflow.designer.modeler.ui.common;

import java.util.List;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.HumanPerformer;
import org.eclipse.bpmn2.PotentialOwner;
import org.eclipse.bpmn2.ResourceAssignmentExpression;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.emf.ecore.EStructuralFeature.Internal;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;

import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.fixflow.designer.persistence.GroupInfoPersistence;
import com.founder.fix.fixflow.designer.persistence.GroupInfoTo;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.util.StringUtil;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Image;


public class CreateShareTypeDialog extends TitleAreaDialog {
	private TreeViewer treeViewer;
	private Text nametext;
	private Combo celuecombo;
	private ResourceRole resourceRole;
	private String opentype;
	private Combo expcombo;
	private ExpressionComboViewer expressionComboViewer;
	private Combo typecombo;
	private ComboViewer typecomboViewer;
	private Combo inorexcludecombo;
	
	ExpressionComboViewer resourceRangeComboViewer;
	Combo resourceRangeCombo;
	private Button containsubCheckButton;
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public CreateShareTypeDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE|SWT.PRIMARY_MODAL);
	}
	
	public CreateShareTypeDialog(Shell parentShell, TreeViewer treeViewer) {
		super(parentShell);
		setHelpAvailable(false);
		this.treeViewer = treeViewer;
		this.opentype = "open";
		setShellStyle(SWT.RESIZE | SWT.TITLE|SWT.PRIMARY_MODAL);
	}
	
	public CreateShareTypeDialog(Shell parentShell, ResourceRole resourceRole, TreeViewer treeViewer) {
		super(parentShell);
		setHelpAvailable(false);
		this.resourceRole = resourceRole;
		this.treeViewer = treeViewer;
		this.opentype = "edit";
		setShellStyle(SWT.RESIZE | SWT.TITLE|SWT.PRIMARY_MODAL);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("创建资源向导");
		Composite area = (Composite) super.createDialogArea(parent);
		GridLayout gl_area = new GridLayout(1, false);
		gl_area.verticalSpacing = 0;
		gl_area.marginWidth = 0;
		gl_area.marginHeight = 0;
		gl_area.horizontalSpacing = 0;
		area.setLayout(gl_area);
		Composite container = new Composite(area, SWT.NONE);
		GridData gd_container = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_container.heightHint = 195;
		container.setLayoutData(gd_container);
		GridLayout gl_container = new GridLayout(3, false);
		gl_container.marginBottom = 15;
		gl_container.marginHeight = 15;
		gl_container.verticalSpacing = 10;
		gl_container.marginRight = 25;
		gl_container.marginLeft = 25;
		container.setLayout(gl_container);
		
		Label nameLabel = new Label(container, SWT.NONE);
		nameLabel.setText("资源名称");
		
		nametext = new Text(container, SWT.BORDER);
		nametext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label typelabel = new Label(container, SWT.NONE);
		typelabel.setText("资源类型");
		
		typecomboViewer = new ComboViewer(container, SWT.READ_ONLY);
		typecombo = typecomboViewer.getCombo();
		typecombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		typecomboViewer.setLabelProvider(new ViewerLabelProvider());
		typecomboViewer.setContentProvider(new ArrayContentProvider());
		typecomboViewer.setInput(GroupInfoPersistence.getGroupInfoTos());
		typecombo.select(0);
		typecomboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if(typecombo.getText().equals("部门")) {
					containsubCheckButton.setEnabled(true);
				} else {
					containsubCheckButton.setSelection(false);
					containsubCheckButton.setEnabled(false);
				}
			}
		});
		
		containsubCheckButton = new Button(container, SWT.CHECK);
		containsubCheckButton.setText("包含子集");
		containsubCheckButton.setEnabled(false);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("包括或排除");
		
		inorexcludecombo = new Combo(container, SWT.READ_ONLY);
		inorexcludecombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		inorexcludecombo.setItems(new String[] {"包含", "排除"});
		inorexcludecombo.setData("0", "INCLUDE");
		inorexcludecombo.setData("1", "EXCLUSION");
		inorexcludecombo.select(0);
		
		Label explabel = new Label(container, SWT.NONE);
		explabel.setText("表达式");
		
		expressionComboViewer = new ExpressionComboViewer(container, SWT.READ_ONLY | SWT.BORDER);
		expcombo = expressionComboViewer.getCombo();
		expcombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("资源范围");
		label_2.setVisible(false);
		
		resourceRangeComboViewer = new ExpressionComboViewer(container, SWT.READ_ONLY);
		resourceRangeCombo = resourceRangeComboViewer.getCombo();
		resourceRangeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		resourceRangeCombo.setVisible(false);
		
		celuecombo = new Combo(container, SWT.READ_ONLY);
		celuecombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		celuecombo.setItems(new String[] {"共享", "独占"});
		celuecombo.select(0);
		celuecombo.setVisible(false);
		
		Label label_1 = new Label(area, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		init();
		return area;
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
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(470, 325);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		
		if(celuecombo.getText().equals("共享")){
			PotentialOwner potentialOwner = Bpmn2Factory.eINSTANCE.createPotentialOwner();
			
//			FeatureMap.Entry extensionAttributeEntry = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, typecombo.getData(typecombo.getSelectionIndex()+"").toString());
			FeatureMap.Entry extensionAttributeEntry = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, ((GroupInfoTo)typecomboViewer.getElementAt(typecombo.getSelectionIndex())).getTypeId());
			potentialOwner.getAnyAttribute().add(extensionAttributeEntry);
			FeatureMap.Entry extensionAttributeEntry1 = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__INCLUDE_EXCLUSION, inorexcludecombo.getData(inorexcludecombo.getSelectionIndex()+"").toString());
			potentialOwner.getAnyAttribute().add(extensionAttributeEntry1);
			FeatureMap.Entry extensionAttributeEntry2 = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__IS_CONTAINS_SUB, (containsubCheckButton.getSelection()));
			potentialOwner.getAnyAttribute().add(extensionAttributeEntry2);
			
			ResourceAssignmentExpression resourceAssignmentExpression = Bpmn2Factory.eINSTANCE.createResourceAssignmentExpression();
			/*FormalExpression formalExpression = ModelHandler.FACTORY.createFormalExpression();
			
			formalExpression.setBody(expcombo.getText());
			resourceAssignmentExpression.setExpression(formalExpression);*/
			ExpressionTo expressionTo = expressionComboViewer.getExpressionCombo().getExpressionTo();
			if(expressionTo!=null)
			{
				FormalExpression formalExpression = Bpmn2Factory.eINSTANCE.createFormalExpression();
				
				
				formalExpression.setId(expressionTo.getName());
				formalExpression.setBody(expressionTo.getExpressionText());
				resourceAssignmentExpression.setExpression(formalExpression);
			}
			
			
			
			ExpressionTo resourceRangeExpressionTo = resourceRangeComboViewer.getExpressionCombo().getExpressionTo();
			if(resourceRangeExpressionTo!=null)
			{
				FeatureMap.Entry resourceRangeExtensionAttributeEntry = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_RANGE,resourceRangeExpressionTo.getExpressionText());
				potentialOwner.getAnyAttribute().add(resourceRangeExtensionAttributeEntry);
				
			}
			
			potentialOwner.setName(nametext.getText());
			potentialOwner.setResourceAssignmentExpression(resourceAssignmentExpression);
			
			setResourceRole(potentialOwner);
			
		}
		
		if(celuecombo.getText().equals("独占")){
			
			HumanPerformer humanPerformer = Bpmn2Factory.eINSTANCE.createHumanPerformer();
			
//			FeatureMap.Entry extensionAttributeEntry = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, typecombo.getData(typecombo.getSelectionIndex()+"").toString());
			FeatureMap.Entry extensionAttributeEntry = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, ((GroupInfoTo)typecomboViewer.getElementAt(typecombo.getSelectionIndex())).getTypeId());
			FeatureMap.Entry extensionAttributeEntry1 = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__INCLUDE_EXCLUSION, inorexcludecombo.getData(inorexcludecombo.getSelectionIndex()+"").toString());
			FeatureMap.Entry extensionAttributeEntry2 = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__IS_CONTAINS_SUB, containsubCheckButton.getSelection());
			
			humanPerformer.getAnyAttribute().add(extensionAttributeEntry);
			humanPerformer.getAnyAttribute().add(extensionAttributeEntry1);
			humanPerformer.getAnyAttribute().add(extensionAttributeEntry2);
			
			ResourceAssignmentExpression resourceAssignmentExpression = Bpmn2Factory.eINSTANCE.createResourceAssignmentExpression();
			FormalExpression formalExpression = Bpmn2Factory.eINSTANCE.createFormalExpression();
			ExpressionTo expressionTo = expressionComboViewer.getExpressionCombo().getExpressionTo();
			if(expressionTo!=null)
			{
		
				formalExpression.setId(expressionTo.getName());
				formalExpression.setBody(expressionTo.getExpressionText());
				
			}
			
			ExpressionTo resourceRangeExpressionTo = resourceRangeComboViewer.getExpressionCombo().getExpressionTo();
			if(resourceRangeExpressionTo!=null)
			{
				FeatureMap.Entry resourceRangeExtensionAttributeEntry = new SimpleFeatureMapEntry((Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_RANGE,resourceRangeExpressionTo.getExpressionText());
				humanPerformer.getAnyAttribute().add(resourceRangeExtensionAttributeEntry);
				
			}
			
			resourceAssignmentExpression.setExpression(formalExpression);
			
			humanPerformer.setName(nametext.getText());
			humanPerformer.setResourceAssignmentExpression(resourceAssignmentExpression);
			
			setResourceRole(humanPerformer);
			
			
		}
		
		//独占类型唯一性验证
		if(opentype.equals("open") && celuecombo.getText().equals("独占")) {
			for (ResourceRole resourceRole : ((List<ResourceRole>)treeViewer.getInput())) {
				if(resourceRole.getClass().getSimpleName().equals("HumanPerformerImpl")){
					MessageDialog.openWarning(null, "警告", "独占类型有且只能存在一个");
					return;
				}
			}
		}
		
		super.okPressed();
	}
	
	public void init(){
		if(resourceRole != null){
		
			nametext.setText(resourceRole.getName() == null ? "" : resourceRole.getName());
//			expcombo.setText(resourceRole.getResourceAssignmentExpression().getExpression() == null ? "" : ((FormalExpression)resourceRole.getResourceAssignmentExpression().getExpression()).getBody());
			
			containsubCheckButton.setSelection(StringUtil.getBoolean(resourceRole.getAnyAttribute().get(FixFlowPackage.Literals.DOCUMENT_ROOT__IS_CONTAINS_SUB, true)));
			
			if (resourceRole.getResourceAssignmentExpression().getExpression() != null) {
				ExpressionTo expressionTo=new ExpressionTo();
				expressionTo.setName(((FormalExpression)resourceRole.getResourceAssignmentExpression().getExpression()).getId());
				expressionTo.setExpressionText(((FormalExpression)resourceRole.getResourceAssignmentExpression().getExpression()).getBody());
				expressionComboViewer.setDefaultExpressionInput(expressionTo);
			}
			
			typecomboViewer.setSelection(new StructuredSelection(getGroupInfoTo(resourceRole.getAnyAttribute().get(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, true).toString())));
//			typecombo.setText(getType(resourceRole.getAnyAttribute().get(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_TYPE, true).toString()));
			celuecombo.setText(resourceRole.getClass().getSimpleName().equals("HumanPerformerImpl") ? "独占" : "共享");
			inorexcludecombo.setText(getType(resourceRole.getAnyAttribute().get(FixFlowPackage.Literals.DOCUMENT_ROOT__INCLUDE_EXCLUSION, true).toString()));
			
			
			Object object=resourceRole.getAnyAttribute().get(FixFlowPackage.Literals.DOCUMENT_ROOT__RESOURCE_RANGE, true);
			if(object!=null){
				
				ExpressionTo expressionTo=new ExpressionTo();
				expressionTo.setName(object.toString());
				expressionTo.setExpressionText(object.toString());
				resourceRangeComboViewer.setDefaultExpressionInput(expressionTo);

			}

		}
	}
	
	public String getType(String type){
		String rstype = "";
		if(type.equals("INCLUDE")){
			rstype = "包含";
			return rstype;
		}
		if(type.equals("EXCLUSION")){
			rstype = "排除";
			return rstype;
		}
		return rstype;
	}

	public GroupInfoTo getGroupInfoTo(String rstype) {
		for (GroupInfoTo groupInfoTo : GroupInfoPersistence.getGroupInfoTos()) {
			if(rstype.equals(groupInfoTo.getTypeId())) {
				return groupInfoTo;
			}
		}
		return null;
	}
	
	public ResourceRole getResourceRole() {
		return resourceRole;
	}

	public void setResourceRole(ResourceRole resourceRole) {
		this.resourceRole = resourceRole;
	}

	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			GroupInfoTo groupInfoTo = (GroupInfoTo) element;
			return groupInfoTo.getTypeName();
		}
	}
}
