package com.founder.fix.fixflow.designer.modeler.ui.property;

import java.io.IOException;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.TextAnnotation;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.TaskSubject;
import com.founder.fix.fixflow.designer.util.FixBpmnUtil;

public abstract class AbstractFixFlowBpmn2PropertiesComposite extends AbstractBpmn2PropertiesComposite {

	



	int number = 0;

	
	public AbstractFixFlowBpmn2PropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	
	/**
	 * FixFlow 属性页抽象容器对象
	 * 
	 * @param parent
	 * @param style
	 */
	public AbstractFixFlowBpmn2PropertiesComposite(Composite parent, int style) {
		super(parent, style);
		// toolkit.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		// setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		// 创建默认的UI,UI没用执行绑定,直都为空,用做WindowBuilder展现的时候用.
		createUI();
	}

	@Override
	public void createBindings(EObject be) {
		Definitions definitions =null;
		
		
		try {
			definitions = ModelHandlerLocator.getModelHandler(be.eResource()).getDefinitions();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		List<RootElement> rootElementList = definitions.getRootElements();
		
		org.eclipse.bpmn2.Process process = null;
		for (RootElement rootElement : rootElementList) {
			if (rootElement instanceof org.eclipse.bpmn2.Process) {
				process = (org.eclipse.bpmn2.Process) rootElement;
			}
		}
		*/
		
		SectionBpmnElement.definitions=definitions;
		
		
		
		if(be instanceof Participant){
			SectionBpmnElement.sectionElement=((Participant)be).getProcessRef();
			SectionBpmnElement.participant=(Participant)be;
			SectionBpmnElement.process=((Participant)be).getProcessRef();
			be=((Participant)be).getProcessRef();
		}
		else{
			if(be instanceof BPMNDiagram){
				
				
				
				Bpmn2DiagramType bpmn2DiagramType =FixBpmnUtil.getBpmn2DiagramType(modelHandler.getDefinitions());
				if(bpmn2DiagramType==Bpmn2DiagramType.PROCESS){
					
					SectionBpmnElement.sectionElement=(Process)modelHandler.getDefinitions().getRootElements().get(0);
					SectionBpmnElement.definitions=definitions;
					SectionBpmnElement.participant=null;
					SectionBpmnElement.process=(Process)modelHandler.getDefinitions().getRootElements().get(0);
				}
				else{
					SectionBpmnElement.sectionElement=be;
					SectionBpmnElement.definitions=definitions;
					SectionBpmnElement.participant=null;
					SectionBpmnElement.process=null;
				}
				
			}
			else{
				
				
				Participant participant =modelHandler.getParticipant(be);
				if(participant!=null){
					Process processnew=participant.getProcessRef();
					SectionBpmnElement.process=processnew;
					SectionBpmnElement.sectionElement=be;
				}
				else{
					Process processnew=(Process)modelHandler.getDefinitions().getRootElements().get(0);
					SectionBpmnElement.process=processnew;
					SectionBpmnElement.sectionElement=be;
				}
			}
			
			
		}
		// 创建UI对象子类需要实现,绘制出自己的UI,不要在UI绘制里做绑定.
		createUI();
		createUIBindings(be);

		// 对UI对象进行数据绑定.

	}

	/**
	 * 创建UI对象子类需要实现,绘制出自己的UI,不要在UI绘制里做绑定.
	 */
	public abstract void createUI();

	/**
	 * 对UI对象进行数据绑定.
	 */
	public abstract void createUIBindings(EObject be);


	
	
	
	public void bindAttributeText(final Text text,final EStructuralFeature eStructuralFeature) {

		Object eGet = SectionBpmnElement.sectionElement.eGet(eStructuralFeature);

		if (eGet != null) {
			text.setText(eGet.toString());
		}

		IObservableValue textObserver = SWTObservables.observeText(text, SWT.Modify);// (text,
		// SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				if (!text.getText().equals(SectionBpmnElement.sectionElement.eGet(eStructuralFeature))) {
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							SectionBpmnElement.sectionElement.eSet(eStructuralFeature, e.diff.getNewValue());
						}
					});
					if (getDiagramEditor().getDiagnostics() != null) {
						// revert the change and display error status message.
						text.setText((String) SectionBpmnElement.sectionElement.eGet(eStructuralFeature));
						//bpmn2Editor.showErrorMessage(bpmn2Editor.getDiagnostics().getMessage());
					} 
				}
			}
		});
	}

	protected void bind(final EStructuralFeature a, final Text text) {

		Object eGet = SectionBpmnElement.sectionElement.eGet(a);
		if (eGet != null) {
			text.setText(eGet.toString());
		}

		IObservableValue textObserver = SWTObservables.observeText(text, SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {
				//为了美观添加长度验证 2012-07-06 wy
				if(text.getText().length()>20 && !(SectionBpmnElement.sectionElement instanceof TextAnnotation)) {
					MessageDialog.openInformation(getShell(), "提示", "名称长度不能超过20个字符");
					text.setText(e.diff.getOldValue().toString());
					return;
				}

				if (!text.getText().equals(SectionBpmnElement.sectionElement.eGet(a))) {
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							SectionBpmnElement.sectionElement.eSet(a, e.diff.getNewValue());
						}
					});
					if (getDiagramEditor().getDiagnostics()!=null) {
						// revert the change and display error status message.
						text.setText((String) SectionBpmnElement.sectionElement.eGet(a));
						//bpmn2Editor.showErrorMessage(bpmn2Editor.getDiagnostics().getMessage());
					}
					
				}
			}
		});
		
		
	}
	
	protected void bind(final EStructuralFeature a, final Text text,final EObject eObject) {

		Object eGet = eObject.eGet(a);
		if (eGet != null) {
			text.setText(eGet.toString());
		}

		IObservableValue textObserver = SWTObservables.observeText(text, SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				if (!text.getText().equals(eObject.eGet(a))) {
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							eObject.eSet(a, e.diff.getNewValue());
						}
					});
					if (getDiagramEditor().getDiagnostics()!=null) {
						// revert the change and display error status message.
						text.setText((String) eObject.eGet(a));
						//bpmn2Editor.showErrorMessage(bpmn2Editor.getDiagnostics().getMessage());
					}
					
				}
			}
		});
		
		
	}
	
	

	/**
	 * 绑定Documentation数据,所有元素通用,当节点不包含Documentation时则自动创建.
	 * 
	 * @param eReference
	 *            Documentation元素引用
	 * @param text
	 *            绑定的文本框
	 */
	public void bindDocumentation(final EReference eReference, final Text text) {

		// Object eGet = be.eGet(a);

		final BaseElement baseElement = (BaseElement) SectionBpmnElement.sectionElement;

		if (baseElement.getDocumentation().size() == 0 || baseElement.getDocumentation().get(0).getText() == null) {
			text.setText("");
		} else {
			text.setText(baseElement.getDocumentation().get(0).getText());
		}

		IObservableValue textObserver = SWTObservables.observeText(text, SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						// List<Documentation> documentationList =
						// baseElement.getDocumentation();
						if (baseElement.getDocumentation().size() == 0) {
							final Documentation value = Bpmn2Factory.eINSTANCE.createDocumentation();
							ModelUtil.setID(value, SectionBpmnElement.sectionElement.eResource());
							baseElement.getDocumentation().add(value);
						}

						List<Documentation> documentationList = baseElement.getDocumentation();
						if (!text.getText().equals(documentationList.get(0).getText())) {

							documentationList.get(0).setText(e.diff.getNewValue().toString());
						}
					}
				});

			};

		});

	

	}

	public void bindFormUri(final EStructuralFeature a, final Combo text) {
		Object eGet = SectionBpmnElement.sectionElement.eGet(a);
		if (eGet != null) {
			text.setText(eGet.toString());
		}

		IObservableValue textObserver = SWTObservables.observeText(text);// (text,
																			// SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				if (!text.getText().equals(SectionBpmnElement.sectionElement.eGet(a))) {
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							SectionBpmnElement.sectionElement.eSet(a, e.diff.getNewValue());
						}
					});
					if (getDiagramEditor().getDiagnostics() != null) {
						// revert the change and display error status message.
						text.setText((String) SectionBpmnElement.sectionElement.eGet(a));
						//bpmn2Editor.showErrorMessage(bpmn2Editor.getDiagnostics().getMessage());
					} 
				}
			}
		});

	
	}

	public void bindConditionExpression(EReference e, final Text text) {
		final SequenceFlow sequenceFlow = (SequenceFlow) SectionBpmnElement.sectionElement;
		// FormalExpressionImpl
		if (sequenceFlow.getConditionExpression() == null) {
			text.setText("");
		} else {
			text.setText(((FormalExpression) sequenceFlow.getConditionExpression()).getBody());
		}

		IObservableValue textObserver = SWTObservables.observeText(text, SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						// List<Documentation> documentationList =
						// baseElement.getDocumentation();
						if (sequenceFlow.getConditionExpression() == null) {
							final FormalExpression value = Bpmn2Factory.eINSTANCE.createFormalExpression();
							ModelUtil.setID(value, SectionBpmnElement.sectionElement.eResource());
							value.setBody(e.diff.getNewValue().toString());
							sequenceFlow.setConditionExpression(value);
						}
						if (!((FormalExpression) sequenceFlow.getConditionExpression()).getBody().equals(text.getText())) {
							((FormalExpression) sequenceFlow.getConditionExpression()).setBody(text.getText());
						}
					}
				});

			};

		});

		

	}

	public void bindTaskSubject(final Combo text) {

		final UserTask userTask = (UserTask) SectionBpmnElement.sectionElement;

		if (userTask.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof TaskSubject) {
						TaskSubject taskSubject = (TaskSubject) entry.getValue();
						text.setText(taskSubject.getExpression().getValue());
						break;
					}
				}

			}

		}

		IObservableValue textObserver = SWTObservables.observeText(text);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						if (userTask.getExtensionValues().size() > 0) {

							for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {

								FeatureMap extensionElements = extensionAttributeValue.getValue();
								for (Entry entry : extensionElements) {
									if (entry.getValue() instanceof TaskSubject) {
										TaskSubject taskSubject = (TaskSubject) entry.getValue();
										taskSubject.getExpression().setValue(e.diff.getNewValue().toString());
										return;
									}
								}

							}

							Expression rhille = FixFlowFactory.eINSTANCE.createExpression();
							rhille.setValue(e.diff.getNewValue().toString());

							TaskSubject authors = FixFlowFactory.eINSTANCE.createTaskSubject();
							authors.setExpression(rhille);
				
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_SUBJECT,
									authors);
							userTask.getExtensionValues().get(0).getValue().add(extensionElementEntry);
		

						} else {
							Expression rhille = FixFlowFactory.eINSTANCE.createExpression();
							rhille.setValue(e.diff.getNewValue().toString());

							TaskSubject authors = FixFlowFactory.eINSTANCE.createTaskSubject();
							authors.setExpression(rhille);
							ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
							userTask.getExtensionValues().add(extensionElement);
							FeatureMap.Entry extensionElementEntry = new SimpleFeatureMapEntry((org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__TASK_SUBJECT,
									authors);
							extensionElement.getValue().add(extensionElementEntry);

						}

					}
				});

			};

		});

		

	}

	
	/**
	 * 绑定eobject
	 * @param text 文本框
	 * @param eObject be对象
	 * @param eStructuralFeature 需要绑定的属性
	 */
	public void bindAttributeText(final Text text,final EObject eObject, final EStructuralFeature eStructuralFeature) {
		Object eGet = eObject.eGet(eStructuralFeature);

		if (eGet != null) {
			text.setText(eGet.toString());
		}

		IObservableValue textObserver = SWTObservables.observeText(text, SWT.Modify);// (text,
		// SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {

				if (!text.getText().equals(eObject.eGet(eStructuralFeature))) {
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							eObject.eSet(eStructuralFeature, e.diff.getNewValue());
						}
					});
					if (getDiagramEditor().getDiagnostics() != null) {
						// revert the change and display error status message.
						text.setText((String) eObject.eGet(eStructuralFeature));
						//bpmn2Editor.showErrorMessage(bpmn2Editor.getDiagnostics().getMessage());
					} 
				}
			}
		});
	}
}
