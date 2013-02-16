package com.founder.fix.fixflow.designer.modeler.ui.property.message;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FilterConditions;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.ProcessInstanceVariable;
import com.founder.fix.bpmn2extensions.fixflow.ReceiveMessage;
import com.founder.fix.bpmn2extensions.fixflow.TokenVariable;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;

public class ReceiveMessageCommonPropertiesComposite extends
		AbstractFixFlowBpmn2PropertiesComposite {
	private Text idtext;
	private ExpressionComboViewer expressionComboViewer;
	private BaseElement operEle;
	private Combo combo;
	private ExpressionComboViewer expressionComboViewer_1;
	private ExpressionComboViewer expressionComboViewer_2;

	public ReceiveMessageCommonPropertiesComposite(
			AbstractBpmn2PropertySection section) {
		super(section);
	}

	public ReceiveMessageCommonPropertiesComposite(Composite parent, int style) {
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

		Label idLabel = new Label(this, SWT.NONE);
		idLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		idLabel.setText("消息编号");

		toolkit.adapt(idLabel, true, true);

		idtext = new Text(this, SWT.BORDER);
		GridData gd_idtext = new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1);
		gd_idtext.widthHint = 240;
		idtext.setLayoutData(gd_idtext);

		toolkit.adapt(idtext, true, true);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("流程实例");
		
		toolkit.adapt(lblNewLabel, true, true);
		
		expressionComboViewer_1 = new ExpressionComboViewer(this);
		Combo combo_1 = expressionComboViewer_1.getCombo();
		GridData gd_combo_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo_1.widthHint = 240;
		combo_1.setLayoutData(gd_combo_1);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("流程令牌");
		
		toolkit.adapt(lblNewLabel_1, true, true);
		
		expressionComboViewer_2 = new ExpressionComboViewer(this);
		Combo combo_2 = expressionComboViewer_2.getCombo();
		GridData gd_combo_2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo_2.widthHint = 240;
		combo_2.setLayoutData(gd_combo_2);

		Label nameLabel = new Label(this, SWT.NONE);
		GridData gd_nameLabel = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_nameLabel.widthHint = 28;
		nameLabel.setLayoutData(gd_nameLabel);
		nameLabel.setText("过滤条件");

		toolkit.adapt(nameLabel, true, true);

		expressionComboViewer = new ExpressionComboViewer(this);
		combo = expressionComboViewer.getCombo();
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1,
				1);
		gd_combo.widthHint = 240;
		combo.setLayoutData(gd_combo);
		
	}

	@Override
	public void createUIBindings(EObject eObject) {
		if(be instanceof CatchEvent) {
			for (EventDefinition eventDefinition : ((CatchEvent)be).getEventDefinitions()) {
				if(eventDefinition instanceof MessageEventDefinition) {
					operEle = eventDefinition;
					break;
				}
			}
		}else if(be instanceof ReceiveTask){
			operEle = (BaseElement) be;
		}
		
		final BaseElement baseElement = (BaseElement) operEle;
		
		if (baseElement.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();
				if(extensionElements.size()<1) {
					
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							ReceiveMessage receiveMessage = FixFlowFactory.eINSTANCE.createReceiveMessage();
							FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RECEIVE_MESSAGE, receiveMessage);

							if (baseElement.getExtensionValues().size() > 0) {
								baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
							} else {
								ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
								extensionElement.getValue().add(extensionElementEntry); 
								baseElement.getExtensionValues().add(extensionElement);
							}
							bindAttributeText(idtext, receiveMessage, FixFlowPackage.Literals.RECEIVE_MESSAGE__MESSAGE_ID);
						}
					});
				}
				
				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof ReceiveMessage) {
						ReceiveMessage receiveMessage = (ReceiveMessage) entry.getValue();
						bindAttributeText(idtext, receiveMessage, FixFlowPackage.Literals.RECEIVE_MESSAGE__MESSAGE_ID);
						
						if(receiveMessage.getFilterConditions()!=null) {
							FilterConditions formUri = receiveMessage.getFilterConditions();
							ExpressionTo expressionTo = new ExpressionTo();
							expressionTo.setName(formUri.getExpression().getName());
							expressionTo.setExpressionText(formUri.getExpression().getValue());
							expressionComboViewer.setDefaultExpressionInput(expressionTo);
						}
						
						if(receiveMessage.getProcessInstanceVariable()!=null) {
							ProcessInstanceVariable processInstanceVariable = receiveMessage.getProcessInstanceVariable();
							ExpressionTo expressionTo1 = new ExpressionTo();
							expressionTo1.setName(processInstanceVariable.getExpression().getName());
							expressionTo1.setExpressionText(processInstanceVariable.getExpression().getValue());
							expressionComboViewer_1.setDefaultExpressionInput(expressionTo1);
						}
						
						
						if(receiveMessage.getTokenVariable()!=null) {
							TokenVariable tokenVariable = receiveMessage.getTokenVariable();
							ExpressionTo expressionTo2 = new ExpressionTo();
							expressionTo2.setName(tokenVariable.getExpression().getName());
							expressionTo2.setExpressionText(tokenVariable.getExpression().getValue());
							expressionComboViewer_2.setDefaultExpressionInput(expressionTo2);
						}
					}

				}

			}

		}else{
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					ReceiveMessage receiveMessage = FixFlowFactory.eINSTANCE.createReceiveMessage();
					FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RECEIVE_MESSAGE, receiveMessage);

					if (baseElement.getExtensionValues().size() > 0) {
						baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
					} else {
						ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
						extensionElement.getValue().add(extensionElementEntry); 
						baseElement.getExtensionValues().add(extensionElement);
					}
					bindAttributeText(idtext, receiveMessage, FixFlowPackage.Literals.RECEIVE_MESSAGE__MESSAGE_ID);
				}
			});
		}
		
		expressionComboViewer.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setFormUriExtensionExpression(baseElement, event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}
				});

			}
		});
		
		expressionComboViewer_1.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setProcessInstanceVariableExtensionExpression(baseElement, event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}
				});

			}
		});
		
		expressionComboViewer_2.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setTokenVariableExtensionExpression(baseElement, event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}
				});

			}
		});
	}
	
	private void setFormUriExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {


		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof ReceiveMessage) {
					if(expressionTo==null)
					{
						extensionElements.remove(entry);
					}
					else{
						ReceiveMessage receiveMessage = (ReceiveMessage) entry.getValue();
						if(receiveMessage.getFilterConditions()!=null) {
							FilterConditions formUri = receiveMessage.getFilterConditions();
							formUri.getExpression().setName(expressionTo.getName());
							formUri.getExpression().setValue(expressionTo.getExpressionText());
						}else {
							FilterConditions formUri = FixFlowFactory.eINSTANCE.createFilterConditions();

							Expression expression = FixFlowFactory.eINSTANCE.createExpression();
							expression.setName(expressionTo.getName());
							expression.setValue(expressionTo.getExpressionText());
							formUri.setExpression(expression);
							
							receiveMessage.setFilterConditions(formUri);

							FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RECEIVE_MESSAGE, receiveMessage);

							if (baseElement.getExtensionValues().size() > 0) {
								baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
							} else {
								ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
								extensionElement.getValue().add(extensionElementEntry); 
								baseElement.getExtensionValues().add(extensionElement);
							}
						}
					}

					return;
				}
			}

		}
		if(expressionTo!=null)
		{
			ReceiveMessage receiveMessage = FixFlowFactory.eINSTANCE.createReceiveMessage();
			FilterConditions formUri = FixFlowFactory.eINSTANCE.createFilterConditions();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			formUri.setExpression(expression);
			
			receiveMessage.setFilterConditions(formUri);

			FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RECEIVE_MESSAGE, receiveMessage);

			if (baseElement.getExtensionValues().size() > 0) {
				baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
			} else {
				ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
				extensionElement.getValue().add(extensionElementEntry); 
				baseElement.getExtensionValues().add(extensionElement);
			}
		}
	}
	
	private void setProcessInstanceVariableExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {


		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof ReceiveMessage) {
					if(expressionTo==null)
					{
						extensionElements.remove(entry);
					}
					else{
						ReceiveMessage receiveMessage = (ReceiveMessage) entry.getValue();
						if(receiveMessage.getProcessInstanceVariable()!=null) {
							ProcessInstanceVariable processInstanceVariable = receiveMessage.getProcessInstanceVariable();
							processInstanceVariable.getExpression().setName(expressionTo.getName());
							processInstanceVariable.getExpression().setValue(expressionTo.getExpressionText());
						}else {

							ProcessInstanceVariable processInstanceVariable = FixFlowFactory.eINSTANCE.createProcessInstanceVariable();

							Expression expression = FixFlowFactory.eINSTANCE.createExpression();
							expression.setName(expressionTo.getName());
							expression.setValue(expressionTo.getExpressionText());
							processInstanceVariable.setExpression(expression);
							
							receiveMessage.setProcessInstanceVariable(processInstanceVariable);

							FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RECEIVE_MESSAGE, receiveMessage);

							if (baseElement.getExtensionValues().size() > 0) {
								baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
							} else {
								ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
								extensionElement.getValue().add(extensionElementEntry); 
								baseElement.getExtensionValues().add(extensionElement);
							}
						}
					}

					return;
				}
			}

		}
		if(expressionTo!=null)
		{
			ReceiveMessage receiveMessage = FixFlowFactory.eINSTANCE.createReceiveMessage();
			ProcessInstanceVariable processInstanceVariable = FixFlowFactory.eINSTANCE.createProcessInstanceVariable();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			processInstanceVariable.setExpression(expression);
			
			receiveMessage.setProcessInstanceVariable(processInstanceVariable);

			FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RECEIVE_MESSAGE, receiveMessage);

			if (baseElement.getExtensionValues().size() > 0) {
				baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
			} else {
				ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
				extensionElement.getValue().add(extensionElementEntry); 
				baseElement.getExtensionValues().add(extensionElement);
			}
		}
	}
	
	private void setTokenVariableExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {


		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof ReceiveMessage) {
					if(expressionTo==null)
					{
						extensionElements.remove(entry);
					}
					else{
						ReceiveMessage receiveMessage = (ReceiveMessage) entry.getValue();
						if(receiveMessage.getTokenVariable()!=null) {
							TokenVariable tokenVariable = receiveMessage.getTokenVariable();
							tokenVariable.getExpression().setName(expressionTo.getName());
							tokenVariable.getExpression().setValue(expressionTo.getExpressionText());
						}else {
							TokenVariable tokenVariable = FixFlowFactory.eINSTANCE.createTokenVariable();

							Expression expression = FixFlowFactory.eINSTANCE.createExpression();
							expression.setName(expressionTo.getName());
							expression.setValue(expressionTo.getExpressionText());
							tokenVariable.setExpression(expression);
							
							receiveMessage.setTokenVariable(tokenVariable);

							FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RECEIVE_MESSAGE, receiveMessage);

							if (baseElement.getExtensionValues().size() > 0) {
								baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
							} else {
								ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
								extensionElement.getValue().add(extensionElementEntry); 
								baseElement.getExtensionValues().add(extensionElement);
							}
						}
					}

					return;
				}
			}

		}
		if(expressionTo!=null)
		{
			ReceiveMessage receiveMessage = FixFlowFactory.eINSTANCE.createReceiveMessage();
			TokenVariable tokenVariable = FixFlowFactory.eINSTANCE.createTokenVariable();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			tokenVariable.setExpression(expression);
			
			receiveMessage.setTokenVariable(tokenVariable);

			FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__RECEIVE_MESSAGE, receiveMessage);

			if (baseElement.getExtensionValues().size() > 0) {
				baseElement.getExtensionValues().get(0).getValue().add(extensionElementEntry);
			} else {
				ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
				extensionElement.getValue().add(extensionElementEntry); 
				baseElement.getExtensionValues().add(extensionElement);
			}
		}
	}
}
