package com.founder.fix.fixflow.designer.modeler.ui.property.common;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.StandardLoopCharacteristics;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.LoopDataInputCollection;
import com.founder.fix.bpmn2extensions.fixflow.LoopDataOutputCollection;
import com.founder.fix.bpmn2extensions.fixflow.LoopMaximum;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionCombo;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;



import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;

public class ActivityAdvancedPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	private Label lblNewLabel;

	private ExpressionCombo comboLoopCondition;
	private ExpressionCombo comboLoopDataInputCollection;
	private ExpressionCombo comboInputDataItem;
	private ExpressionCombo comboLoopDataOutputCollection;
	private ExpressionCombo comboOutputDataItem;
	private ExpressionCombo comboCompletionCondition;
	
	

	private ExpressionComboViewer comboViewerLoopDataInputCollection;
	private ExpressionComboViewer comboViewerLoopCondition;
	private ExpressionComboViewer comboViewerInputDataItem;
	private ExpressionComboViewer comboViewerLoopDataOutputCollection;
	
	private ExpressionComboViewer comboViewerOutputDataItem;
	
	
	private ExpressionComboViewer comboViewerCompletionCondition;
	

	
	private Composite composite;
	private Composite composite_1;
	private Composite composite_2;
	private Composite composite_3;
	@SuppressWarnings("unused")
	private BPMNShape shape;
	private Button noneRadioButton;
	private Button standardRadioButton;
	private Button parallelRadioButton;
	private StackLayout stackLayout;
	private Button beforeRadioButton;
	private Button afterRadioButton;
	private Button btnCheckButton;

	protected Expression expressionLoopDataInputCollection;

	private Combo comboLoopMaximum;
	private ExpressionComboViewer comboViewerLoopMaximum;
	private Label label_2;
	
	public ActivityAdvancedPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public ActivityAdvancedPropertiesComposite(Composite parent, int style) {
		super(parent, style);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUI() {
		setLayout(null);
		noneRadioButton = new Button(this, SWT.RADIO);
		noneRadioButton.setBounds(15, 5, 33, 18);
		noneRadioButton.setText("无");
	
		toolkit.adapt(noneRadioButton, true, true);

		standardRadioButton = new Button(this, SWT.RADIO);
		standardRadioButton.setEnabled(false);
		standardRadioButton.setBounds(131, 5, 55, 18);
		standardRadioButton.setText("循环");
		standardRadioButton.setVisible(false);
	
		toolkit.adapt(standardRadioButton, true, true);

		parallelRadioButton = new Button(this, SWT.RADIO);
		parallelRadioButton.setBounds(67, 5, 58, 18);
		parallelRadioButton.setText("多实例");

		toolkit.adapt(parallelRadioButton, true, true);

		composite = new Composite(this, SWT.NONE);
		composite.setBounds(15, 30, 625, 169);
		stackLayout = new StackLayout();
		composite.setLayout(stackLayout);

		toolkit.adapt(composite, true, true);

		composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		toolkit.adapt(composite_1, true, true);

		composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_2.setLayout(new GridLayout(2, false));
	
		toolkit.adapt(composite_2, true, true);

		beforeRadioButton = new Button(composite_2, SWT.RADIO);
		beforeRadioButton.setText("测试在前");

		afterRadioButton = new Button(composite_2, SWT.RADIO);
		afterRadioButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		afterRadioButton.setText("测试在后");

		Label conditionLabel = new Label(composite_2, SWT.NONE);
		conditionLabel.setText("循环条件");

		composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite_3.setLayout(null);

		lblNewLabel = new Label(composite_3, SWT.NONE);
		lblNewLabel.setBounds(5, 35, 48, 14);
		lblNewLabel.setText("完成策略");

		toolkit.adapt(lblNewLabel, true, true);

		Label Label = new Label(composite_3, SWT.NONE);
		Label.setBounds(5, 67, 63, 14);
		Label.setText("输入数据集");
		

		toolkit.adapt(Label, true, true);

		Label Label_1 = new Label(composite_3, SWT.NONE);
		Label_1.setBounds(5, 136, 63, 14);
		Label_1.setText("完成表达式");
		
		toolkit.adapt(Label_1, true, true);

		Label Label_2 = new Label(composite_3, SWT.NONE);
		Label_2.setBounds(5, 100, 63, 14);
		Label_2.setText("输出数据集");
		
	
		toolkit.adapt(Label_2, true, true);

		Button btnRadioButton = new Button(composite_3, SWT.RADIO);
		btnRadioButton.setSelection(true);
		btnRadioButton.setBounds(69, 33, 44, 18);
		btnRadioButton.setText("所有");
		
		toolkit.adapt(btnRadioButton, true, true);

		Button button = new Button(composite_3, SWT.RADIO);
		button.setEnabled(false);
		button.setText("第一个");
		button.setBounds(124, 33, 57, 18);
		button.setVisible(false);
	
		toolkit.adapt(button, true, true);

		Button button_1 = new Button(composite_3, SWT.RADIO);
		button_1.setEnabled(false);
		button_1.setText("没有");
		button_1.setBounds(187, 33, 44, 18);
		button_1.setVisible(false);
		
	
		toolkit.adapt(button_1, true, true);

		Button button_2 = new Button(composite_3, SWT.RADIO);
		button_2.setEnabled(false);
		button_2.setText("复杂");
		button_2.setBounds(242, 33, 48, 18);
		button_2.setVisible(false);
	
		toolkit.adapt(button_2, true, true);

		btnCheckButton = new Button(composite_3, SWT.CHECK);
		btnCheckButton.setEnabled(false);
		btnCheckButton.setBounds(5, 8, 93, 18);
		btnCheckButton.setText("顺序");
		btnCheckButton.setVisible(false);
		
		
	
		toolkit.adapt(btnCheckButton, true, true);


		Label label = new Label(composite_3, SWT.NONE);
		label.setText("输入项编号");
		label.setBounds(300, 65, 63, 14);

		toolkit.adapt(label, true, true);

		Label label_1 = new Label(composite_3, SWT.NONE);
		label_1.setText("输出项编号");
		label_1.setBounds(300, 98, 63, 14);
	
		toolkit.adapt(label_1, true, true);
		// 标准循环条件
		
		
		comboViewerLoopCondition = new ExpressionComboViewer(composite_2);
		comboLoopCondition = comboViewerLoopCondition.getExpressionCombo();
		comboLoopCondition.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		//最大输入
		label_2 = new Label(composite_2, SWT.NONE);
		label_2.setText("最大循环");
		
		comboViewerLoopMaximum = new ExpressionComboViewer(composite_2);
		comboLoopMaximum = comboViewerLoopMaximum.getExpressionCombo();
		comboLoopMaximum.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
		

		comboLoopCondition = comboViewerLoopCondition.getExpressionCombo();
		comboLoopCondition.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		// 多实例循环输入集合
		comboViewerLoopDataInputCollection = new ExpressionComboViewer(composite_3);
		comboLoopDataInputCollection = comboViewerLoopDataInputCollection.getExpressionCombo();
		comboLoopDataInputCollection.setBounds(74, 67, 216, 20);
		
		
		comboViewerInputDataItem=new ExpressionComboViewer(composite_3);
		comboInputDataItem=comboViewerInputDataItem.getExpressionCombo();
		comboInputDataItem.setBounds(369, 67, 216, 20);
		
		
		comboViewerLoopDataOutputCollection=new ExpressionComboViewer(composite_3);
		comboLoopDataOutputCollection=comboViewerLoopDataOutputCollection.getExpressionCombo();
		comboLoopDataOutputCollection.setBounds(74, 100, 216, 20);
		

		
		comboViewerOutputDataItem=new ExpressionComboViewer(composite_3);
		comboOutputDataItem=comboViewerOutputDataItem.getExpressionCombo();
		comboOutputDataItem.setBounds(369, 100, 216, 20);
		
		
		comboViewerCompletionCondition=new  ExpressionComboViewer(composite_3);
		comboCompletionCondition=comboViewerCompletionCondition.getExpressionCombo();
		comboCompletionCondition.setBounds(74, 136, 511, 20);
		
		

	}

	@Override
	public void createUIBindings(EObject eObject) {

		noneRadioButton.addSelectionListener(noneRadioButtonSelectionAdapter);

		standardRadioButton.addSelectionListener(standardRadioButtonSelectionAdapter);

		parallelRadioButton.addSelectionListener(parallelRadioButtonSelectionAdapter);

		beforeRadioButton.addSelectionListener(beforeRadioButtonSelectionAdapter);

		afterRadioButton.addSelectionListener(afterRadioButtonSelectionAdapter);

		btnCheckButton.addSelectionListener(btnCheckButtonSelectionAdapter);


		comboViewerLoopCondition.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				final Activity activity = (Activity) be;
				LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

				final StandardLoopCharacteristics standardLoopCharacteristicsObj = (StandardLoopCharacteristics) loopCharacteristics;

				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						if(event.getExpressionTo()==null)
						{
							standardLoopCharacteristicsObj.setLoopCondition(null);
							
						}
						else{
							if (standardLoopCharacteristicsObj.getLoopCondition() == null) {
								FormalExpression formalExpression = Bpmn2Factory.eINSTANCE.createFormalExpression();
								standardLoopCharacteristicsObj.setLoopCondition(formalExpression);
								
							}

							FormalExpression formalExpression = (FormalExpression) standardLoopCharacteristicsObj.getLoopCondition();

							formalExpression.setId(event.getExpressionTo().getName());
							formalExpression.setBody(event.getExpressionTo().getExpressionText());
						}
						
						

					}
				});

			}
		});
		
		
		comboViewerLoopMaximum.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				final Activity activity = (Activity) be;
				LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

				final StandardLoopCharacteristics standardLoopCharacteristicsObj = (StandardLoopCharacteristics) loopCharacteristics;

				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						
						if(event.getExpressionTo()==null)
						{
							standardLoopCharacteristicsObj.getExtensionValues().clear();
							return;
						}

						if (standardLoopCharacteristicsObj.getExtensionValues().size() > 0) {

							for (ExtensionAttributeValue extensionAttributeValue : standardLoopCharacteristicsObj.getExtensionValues()) {

								FeatureMap extensionElements = extensionAttributeValue.getValue();
								for (Entry entry : extensionElements) {
									if (entry.getValue() instanceof LoopMaximum) {
										LoopMaximum taskSubject = (LoopMaximum) entry.getValue();
										taskSubject.getExpression().setName(event.getExpressionTo().getName());
										taskSubject.getExpression().setValue(event.getExpressionTo().getExpressionText());
										return;
									}
								}

							}

							Expression rhille = FixFlowFactory.eINSTANCE.createExpression();
							rhille.setName(event.getExpressionTo().getName());
							rhille.setValue(event.getExpressionTo().getExpressionText());

							LoopMaximum authors = FixFlowFactory.eINSTANCE.createLoopMaximum();
							authors.setExpression(rhille);

							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__LOOP_MAXIMUM, authors);
							standardLoopCharacteristicsObj.getExtensionValues().get(0).getValue().add(extensionElementEntry);

						} else {
							Expression rhille = FixFlowFactory.eINSTANCE.createExpression();
							rhille.setName(event.getExpressionTo().getName());
							rhille.setValue(event.getExpressionTo().getExpressionText());

							LoopMaximum authors = FixFlowFactory.eINSTANCE.createLoopMaximum();
							authors.setExpression(rhille);
							ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
							standardLoopCharacteristicsObj.getExtensionValues().add(extensionElement);
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__LOOP_MAXIMUM, authors);
							extensionElement.getValue().add(extensionElementEntry);

						}

					}
				});

			}
		});
		
		
		

		comboViewerLoopDataInputCollection.addExpressionChangedListeners(new IExpressionChangedListener() {

			
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				final Activity activity = (Activity) be;
				LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

				final MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						
						if(event.getExpressionTo()==null)
						{
							multiInstanceLoopCharacteristics.getExtensionValues().clear();
							return;
						}

						if (multiInstanceLoopCharacteristics.getExtensionValues().size() > 0) {

							for (ExtensionAttributeValue extensionAttributeValue : multiInstanceLoopCharacteristics.getExtensionValues()) {

								FeatureMap extensionElements = extensionAttributeValue.getValue();
								for (Entry entry : extensionElements) {
									if (entry.getValue() instanceof LoopDataInputCollection) {
										LoopDataInputCollection taskSubject = (LoopDataInputCollection) entry.getValue();
										taskSubject.getExpression().setName(event.getExpressionTo().getName());
										taskSubject.getExpression().setValue(event.getExpressionTo().getExpressionText());
										return;
									}
								}

							}

							Expression rhille = FixFlowFactory.eINSTANCE.createExpression();
							rhille.setName(event.getExpressionTo().getName());
							rhille.setValue(event.getExpressionTo().getExpressionText());

							LoopDataInputCollection authors = FixFlowFactory.eINSTANCE.createLoopDataInputCollection();
							authors.setExpression(rhille);

							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION, authors);
							multiInstanceLoopCharacteristics.getExtensionValues().get(0).getValue().add(extensionElementEntry);

						} else {
							Expression rhille = FixFlowFactory.eINSTANCE.createExpression();
							rhille.setName(event.getExpressionTo().getName());
							rhille.setValue(event.getExpressionTo().getExpressionText());

							LoopDataInputCollection authors = FixFlowFactory.eINSTANCE.createLoopDataInputCollection();
							authors.setExpression(rhille);
							ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
							multiInstanceLoopCharacteristics.getExtensionValues().add(extensionElement);
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__LOOP_DATA_INPUT_COLLECTION, authors);
							extensionElement.getValue().add(extensionElementEntry);

						}

					}
				});

			}
		});
		
		
		comboViewerInputDataItem.addExpressionChangedListeners(new IExpressionChangedListener() {
			
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				final Activity activity = (Activity) be;
				LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

				final MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						
						if(event.getExpressionTo()==null)
						{
							multiInstanceLoopCharacteristics.setInputDataItem(null);
							return;
						}
						
						if (multiInstanceLoopCharacteristics.getInputDataItem() == null) {

							// Expression expression =
							// FixFlowFactory.eINSTANCE.createExpression();
							DataInput dataInput = Bpmn2Factory.eINSTANCE.createDataInput();
							multiInstanceLoopCharacteristics.setInputDataItem(dataInput);
						}

						setExtensionExpression(multiInstanceLoopCharacteristics.getInputDataItem(), event.getExpressionTo());

					}
				});
				
				
			}
		});
		
		
		comboViewerLoopDataOutputCollection.addExpressionChangedListeners(new IExpressionChangedListener() {
			
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				final Activity activity = (Activity) be;
				LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

				final MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						if(event.getExpressionTo()==null)
						{
							multiInstanceLoopCharacteristics.getExtensionValues().clear();
							return;
						}
						if (multiInstanceLoopCharacteristics.getExtensionValues().size() > 0) {

							for (ExtensionAttributeValue extensionAttributeValue : multiInstanceLoopCharacteristics.getExtensionValues()) {

								FeatureMap extensionElements = extensionAttributeValue.getValue();
								for (Entry entry : extensionElements) {
									if (entry.getValue() instanceof LoopDataOutputCollection) {
										LoopDataOutputCollection taskSubject = (LoopDataOutputCollection) entry.getValue();
										taskSubject.getExpression().setName(event.getExpressionTo().getName());
										taskSubject.getExpression().setValue(event.getExpressionTo().getExpressionText());
										return;
									}
								}

							}

							Expression rhille = FixFlowFactory.eINSTANCE.createExpression();
							rhille.setName(event.getExpressionTo().getName());
							rhille.setValue(event.getExpressionTo().getExpressionText());

							LoopDataOutputCollection authors = FixFlowFactory.eINSTANCE.createLoopDataOutputCollection();
							authors.setExpression(rhille);

							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION, authors);
							multiInstanceLoopCharacteristics.getExtensionValues().get(0).getValue().add(extensionElementEntry);

						} else {
							Expression rhille = FixFlowFactory.eINSTANCE.createExpression();
							rhille.setName(event.getExpressionTo().getName());
							rhille.setValue(event.getExpressionTo().getExpressionText());

							LoopDataOutputCollection authors = FixFlowFactory.eINSTANCE.createLoopDataOutputCollection();
							authors.setExpression(rhille);
							ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
							multiInstanceLoopCharacteristics.getExtensionValues().add(extensionElement);
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry(
									(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__LOOP_DATA_OUTPUT_COLLECTION, authors);
							extensionElement.getValue().add(extensionElementEntry);

						}
					}
				});

				
				
			}
		});
		

		

		comboViewerOutputDataItem.addExpressionChangedListeners(new IExpressionChangedListener() {
		
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				final Activity activity = (Activity) be;
				LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

				final MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						if(event.getExpressionTo()==null)
						{
							multiInstanceLoopCharacteristics.setOutputDataItem(null);
							return;
						}
						if (multiInstanceLoopCharacteristics.getOutputDataItem() == null) {

							// Expression expression =
							// FixFlowFactory.eINSTANCE.createExpression();
							DataOutput dataOutput = Bpmn2Factory.eINSTANCE.createDataOutput();
							multiInstanceLoopCharacteristics.setOutputDataItem(dataOutput);
						}

						setExtensionExpression(multiInstanceLoopCharacteristics.getOutputDataItem(),event.getExpressionTo());

					}
				});
				
			}
		});

		
		
		
		comboViewerCompletionCondition.addExpressionChangedListeners(new IExpressionChangedListener() {
			
			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				final Activity activity = (Activity) be;
				LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

				final MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						if(event.getExpressionTo()==null)
						{
							multiInstanceLoopCharacteristics.setCompletionCondition(null);
							return;
						}
						if (multiInstanceLoopCharacteristics.getCompletionCondition() == null) {

							// Expression expression =
							// FixFlowFactory.eINSTANCE.createExpression();
							org.eclipse.bpmn2.Expression expression = Bpmn2Factory.eINSTANCE.createFormalExpression();

							multiInstanceLoopCharacteristics.setCompletionCondition(expression);
						}

						
						FormalExpression formalExpression = (FormalExpression) multiInstanceLoopCharacteristics.getCompletionCondition();
						
						formalExpression.setId(event.getExpressionTo().getName());
						formalExpression.setBody(event.getExpressionTo().getExpressionText());

					}
				});
				
				
			}
		});
	


		final Activity activity = (Activity) be;
		if (activity.getLoopCharacteristics() != null) {

			LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

			if (loopCharacteristics instanceof StandardLoopCharacteristics) {
				standardRadioButton.setSelection(true);

				stackLayout.topControl = composite_2;
				composite.layout();
				bindStandardLoop();

			}
			if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics) {

				parallelRadioButton.setSelection(true);
				stackLayout.topControl = composite_3;
				composite.layout();
				bindMultiInstanceLoop();

			}
		} else {
			noneRadioButton.setSelection(true);
			stackLayout.topControl = composite_1;
			composite.layout();
		}

	}

	private void bindMultiInstanceLoop() {

		final Activity activity = (Activity) be;
		LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

		final MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) loopCharacteristics;

		if (multiInstanceLoopCharacteristics.isIsSequential()) {
			btnCheckButton.setSelection(true);
		} else {
			btnCheckButton.setSelection(false);
		}

		if (multiInstanceLoopCharacteristics.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : multiInstanceLoopCharacteristics.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof LoopDataInputCollection) {
						LoopDataInputCollection loopDataInputCollection = (LoopDataInputCollection) entry.getValue();
						ExpressionTo expressionTo=new ExpressionTo();
						expressionTo.setName(loopDataInputCollection.getExpression().getName());
						expressionTo.setExpressionText(loopDataInputCollection.getExpression().getValue());
						comboViewerLoopDataInputCollection.setDefaultExpressionInput(expressionTo);
						break;
					}

				}

			}

		}

		if (multiInstanceLoopCharacteristics.getInputDataItem() != null) {

			DataInput dataInput = multiInstanceLoopCharacteristics.getInputDataItem();

			Expression expression = getExtensionExpression(dataInput);
			if (expression != null) {
				ExpressionTo expressionTo=new ExpressionTo();
				expressionTo.setName(expression.getName());
				expressionTo.setExpressionText(expression.getValue());
				comboViewerInputDataItem.setDefaultExpressionInput(expressionTo);
			}
		}

		if (multiInstanceLoopCharacteristics.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : multiInstanceLoopCharacteristics.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof LoopDataOutputCollection) {
						
						LoopDataOutputCollection loopDataOutputCollection = (LoopDataOutputCollection) entry.getValue();
						ExpressionTo expressionTo=new ExpressionTo();
						expressionTo.setName(loopDataOutputCollection.getExpression().getName());
						expressionTo.setExpressionText(loopDataOutputCollection.getExpression().getValue());
						comboViewerLoopDataOutputCollection.setDefaultExpressionInput(expressionTo);
			
						break;
					}

				}

			}

		}

		if (multiInstanceLoopCharacteristics.getOutputDataItem() != null) {

			DataOutput dataOutput = multiInstanceLoopCharacteristics.getOutputDataItem();

			Expression expression = getExtensionExpression(dataOutput);
			if (expression != null) {

				ExpressionTo expressionTo=new ExpressionTo();
				expressionTo.setName(expression.getName());
				expressionTo.setExpressionText(expression.getValue());
				comboViewerOutputDataItem.setDefaultExpressionInput(expressionTo);
	
			}
		}

		if (multiInstanceLoopCharacteristics.getCompletionCondition() != null) {
			FormalExpression formalExpression = (FormalExpression) multiInstanceLoopCharacteristics.getCompletionCondition();
			ExpressionTo expressionTo=new ExpressionTo();
			expressionTo.setName(formalExpression.getId());
			expressionTo.setExpressionText(formalExpression.getBody());
			comboViewerCompletionCondition.setDefaultExpressionInput(expressionTo);
			//text_3.setText(formalExpression.getBody());
		}

	


	}

	private void setExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {
		if (baseElement.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();
				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof Expression) {
						Expression expression = (Expression) entry.getValue();
						expression.setName(expressionTo.getName());
						expression.setValue(expressionTo.getExpressionText());
						return;
					}
				}

			}

			Expression rhille = FixFlowFactory.eINSTANCE.createExpression();
			rhille.setName(expressionTo.getName());
			rhille.setValue(expressionTo.getExpressionText());

			FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.RESOURCE_FILTER__EXPRESSION, rhille);
			baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);

		} else {
			Expression rhille = FixFlowFactory.eINSTANCE.createExpression();
			rhille.setName(expressionTo.getName());
			rhille.setValue(expressionTo.getExpressionText());

			ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
			baseElement.getExtensionValues().add(extensionElement);
			FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.RESOURCE_FILTER__EXPRESSION, rhille);
			extensionElement.getValue().add(extensionElementEntry);

		}
	}

	private Expression getExtensionExpression(BaseElement baseElement) {

		if (baseElement.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof Expression) {
						Expression expression= (Expression) entry.getValue();
						return expression;

					}

				}

			}

		}
		return null;

	}

	private void bindStandardLoop() {

		final Activity activity = (Activity) be;
		LoopCharacteristics loopCharacteristics = activity.getLoopCharacteristics();

		final StandardLoopCharacteristics standardLoopCharacteristicsObj = (StandardLoopCharacteristics) loopCharacteristics;

		if (standardLoopCharacteristicsObj.isTestBefore()) {
			beforeRadioButton.setSelection(true);
			afterRadioButton.setSelection(false);

		} else {
			afterRadioButton.setSelection(true);
			beforeRadioButton.setSelection(false);
		}

		if (standardLoopCharacteristicsObj.getLoopCondition() != null) {
			FormalExpression formalExpression = (FormalExpression) standardLoopCharacteristicsObj.getLoopCondition();
			ExpressionTo expressionTo=new ExpressionTo();
			expressionTo.setName(formalExpression.getId());
			expressionTo.setExpressionText(formalExpression.getBody());
			comboViewerLoopCondition.setDefaultExpressionInput(expressionTo);
			//combo.setText(formalExpression.getBody());
		}
	
	
		
		if (standardLoopCharacteristicsObj.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : standardLoopCharacteristicsObj.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof LoopMaximum) {
						
						LoopMaximum loopDataOutputCollection = (LoopMaximum) entry.getValue();
						ExpressionTo expressionTo=new ExpressionTo();
						expressionTo.setName(loopDataOutputCollection.getExpression().getName());
						expressionTo.setExpressionText(loopDataOutputCollection.getExpression().getValue());
						comboViewerLoopMaximum.setDefaultExpressionInput(expressionTo);
			
						break;
					}

				}

			}

		}


	}



	// 是否串行
	SelectionAdapter btnCheckButtonSelectionAdapter = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {

			final Activity activity = (Activity) be;

			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = (MultiInstanceLoopCharacteristics) activity.getLoopCharacteristics();
					if (multiInstanceLoopCharacteristics.isIsSequential()) {
						// multiInstanceLoopCharacteristics.setIsSequential(false);
						MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristicsNew =Bpmn2Factory.eINSTANCE.createMultiInstanceLoopCharacteristics();
						multiInstanceLoopCharacteristicsNew.setIsSequential(false);
						activity.setLoopCharacteristics(multiInstanceLoopCharacteristicsNew);
						// bindMultiInstanceLoop();
						// bpmn2Editor.refreshRenderingDecorators(SectionBpmnElement.selectedPictogramElement);
					} else {
						MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristicsNew = Bpmn2Factory.eINSTANCE.createMultiInstanceLoopCharacteristics();
						multiInstanceLoopCharacteristicsNew.setIsSequential(true);
						activity.setLoopCharacteristics(multiInstanceLoopCharacteristicsNew);
						// bindMultiInstanceLoop();
					}

				}
			});

		}
	};

	// 条件在前
	SelectionAdapter beforeRadioButtonSelectionAdapter = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {

			final Activity activity = (Activity) be;

			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					StandardLoopCharacteristics standardLoopCharacteristics = (StandardLoopCharacteristics) activity.getLoopCharacteristics();
					standardLoopCharacteristics.setTestBefore(true);
				}
			});

		}
	};

	// 条件在后
	SelectionAdapter afterRadioButtonSelectionAdapter = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {

			final Activity activity = (Activity) be;

			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					StandardLoopCharacteristics standardLoopCharacteristics = (StandardLoopCharacteristics) activity.getLoopCharacteristics();
					standardLoopCharacteristics.setTestBefore(false);
				}
			});

		}
	};

	// 无 按钮被选中的时候触发的事件
	SelectionAdapter noneRadioButtonSelectionAdapter = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			comboViewerLoopCondition.cleanData();
			comboViewerLoopMaximum.cleanData();
			comboViewerLoopDataInputCollection.cleanData();
			comboViewerInputDataItem.cleanData();
			comboViewerLoopDataOutputCollection.cleanData();
			comboViewerOutputDataItem.cleanData();
			comboViewerCompletionCondition.cleanData();
			
			stackLayout.topControl = composite_1;
			composite.layout();
			
			
			
			final Activity activity = (Activity) be;

			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					activity.setLoopCharacteristics(null);
				}
			});

		}
	};

	// 标准循环按钮被选中的时候触发的事件
	SelectionAdapter standardRadioButtonSelectionAdapter = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
	
			comboViewerLoopDataInputCollection.cleanData();
			comboViewerInputDataItem.cleanData();
			comboViewerLoopDataOutputCollection.cleanData();
			comboViewerOutputDataItem.cleanData();
			comboViewerCompletionCondition.cleanData();
			
			stackLayout.topControl = composite_2;
			
			composite.layout();

			final StandardLoopCharacteristics standardLoopCharacteristics = Bpmn2Factory.eINSTANCE.createStandardLoopCharacteristics();

			final Activity activity = (Activity) be;

			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

				@Override
				protected void doExecute() {

					activity.setLoopCharacteristics(standardLoopCharacteristics);

				}
			});

			bindStandardLoop();

		}
	};

	// 并行多实例按钮被选中的时候触发的事件
	SelectionAdapter parallelRadioButtonSelectionAdapter = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			comboViewerLoopCondition.cleanData();
			comboViewerLoopMaximum.cleanData();

			stackLayout.topControl = composite_3;
			composite.layout();

			final Activity activity = (Activity) be;

			final MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = Bpmn2Factory.eINSTANCE.createMultiInstanceLoopCharacteristics();
			// multiInstanceLoopCharacteristics.setIsSequential(false);

			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					activity.setLoopCharacteristics(multiInstanceLoopCharacteristics);
				}
			});
			bindMultiInstanceLoop();
		}
	};
	
}
