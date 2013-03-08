package com.founder.fix.fixflow.designer.modeler.ui.property.common;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.SkipAssignee;
import com.founder.fix.bpmn2extensions.fixflow.SkipComment;
import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;
import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;

import com.founder.fix.fixflow.designer.usercontrol.ExpressionChangedEvent;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionComboViewer;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.usercontrol.IExpressionChangedListener;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;

public class ActivitySkipPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {

	public ActivitySkipPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	public ActivitySkipPropertiesComposite(Composite parent, int style) {
		super(parent, style);

		// TODO Auto-generated constructor stub
	}

	SkipStrategy skipStrategy;

	ExpressionComboViewer expressionComboViewer;

	Button btnCheckButton;

	Button btnCheckButton_QY;

	Label label_YJ;

	Label label_TGCL;

	Label label_CLZ;

	ExpressionComboViewer expressionComboViewer_CLZ;

	ExpressionComboViewer expressionComboViewer_CLYJ;

	Group group;

	@Override
	public void createUI() {
		setLayout(null);

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 358, 215);

		toolkit.adapt(composite, true, true);

		group = new Group(composite, SWT.NONE);
		group.setEnabled(false);
		group.setText("策略配置");
		group.setBounds(10, 22, 338, 185);
		toolkit.adapt(group, true, true);

		btnCheckButton = new Button(group, SWT.CHECK);
		btnCheckButton.setEnabled(false);
		btnCheckButton.setBounds(10, 25, 117, 16);
		btnCheckButton.setSelection(true);
		btnCheckButton.setText("生成跳过记录");

		toolkit.adapt(btnCheckButton, true, true);

		label_CLZ = new Label(group, SWT.NONE);
		label_CLZ.setEnabled(false);
		label_CLZ.setBounds(10, 97, 48, 14);
		label_CLZ.setText("处 理 者");
		toolkit.adapt(label_CLZ, true, true);

		label_TGCL = new Label(group, SWT.NONE);
		label_TGCL.setEnabled(false);
		label_TGCL.setBounds(10, 60, 48, 14);
		label_TGCL.setText("跳过策略");

		toolkit.adapt(label_TGCL, true, true);
		expressionComboViewer = new ExpressionComboViewer(group);
		Combo combo = expressionComboViewer.getCombo();
		combo.setEnabled(false);
		combo.setBounds(64, 57, 194, 23);

		toolkit.adapt(combo, true, true);

		expressionComboViewer_CLZ = new ExpressionComboViewer(group);
		Combo combo_1 = expressionComboViewer_CLZ.getCombo();
		combo_1.setEnabled(false);
		combo_1.setBounds(64, 93, 194, 23);
		toolkit.adapt(combo_1, true, true);

		label_YJ = new Label(group, SWT.NONE);
		label_YJ.setEnabled(false);
		label_YJ.setText("跳过意见");
		label_YJ.setBounds(10, 135, 48, 14);
		toolkit.adapt(label_YJ, true, true);

		expressionComboViewer_CLYJ = new ExpressionComboViewer(group);
		Combo combo_2 = expressionComboViewer_CLYJ.getCombo();
		combo_2.setEnabled(false);
		combo_2.setBounds(64, 132, 194, 23);
		toolkit.adapt(combo_2, true, true);

		btnCheckButton_QY = new Button(composite, SWT.CHECK);
		btnCheckButton_QY.setBounds(10, 0, 98, 16);
		btnCheckButton_QY.setText("启用跳过策略");
		toolkit.adapt(btnCheckButton_QY, true, true);

	}

	@Override
	public void createUIBindings(EObject eObject) {

		final Activity activity = (Activity) be;

		btnCheckButton_QY.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				if (btnCheckButton_QY.getSelection()) {

					setQy(true);

					@SuppressWarnings("restriction")
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {

							skipStrategy.setIsEnable(true);

						}
					});

				} else {

					setQy(false);

					@SuppressWarnings("restriction")
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {

							skipStrategy.setIsEnable(false);

						}
					});

				}

			}
		});

		btnCheckButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				if (btnCheckButton.getSelection()) {

					label_YJ.setEnabled(true);

					label_CLZ.setEnabled(true);

					expressionComboViewer_CLZ.getCombo().setEnabled(true);//

					expressionComboViewer_CLYJ.getCombo().setEnabled(true);//

					@SuppressWarnings("restriction")
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {

							skipStrategy.setIsCreateSkipProcess(true);

						}
					});

				} else {
					label_YJ.setEnabled(false);

					label_CLZ.setEnabled(false);

					expressionComboViewer_CLZ.getCombo().setEnabled(false);//

					expressionComboViewer_CLYJ.getCombo().setEnabled(false);//

					@SuppressWarnings("restriction")
					TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {

							skipStrategy.setIsCreateSkipProcess(false);

						}
					});

				}

			}
		});

		int x = 0;
		if (activity.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : activity.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof SkipStrategy) {
						skipStrategy = (SkipStrategy) entry.getValue();
						x=x+1;
						break;
					}

				}

			}

		}
		if (x == 0) {

			skipStrategy = FixFlowFactory.eINSTANCE.createSkipStrategy();

			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {

					FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry(
							(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__SKIP_STRATEGY, skipStrategy);

					if (activity.getExtensionValues().size() > 0) {
						activity.getExtensionValues().get(0).getValue().add(extensionElementEntry);
					} else {
						ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
						extensionElement.getValue().add(extensionElementEntry);
						activity.getExtensionValues().add(extensionElement);
					}
				}
			});

		}

		if (skipStrategy.isIsEnable()) {
			btnCheckButton_QY.setSelection(true);
			setQy(true);
		} else {
			btnCheckButton_QY.setSelection(false);
			setQy(false);
		}

		if (skipStrategy.isIsEnable()) {
			if (skipStrategy.isIsCreateSkipProcess()) {
				btnCheckButton.setSelection(true);
				label_YJ.setEnabled(true);

				label_CLZ.setEnabled(true);

				expressionComboViewer_CLZ.getCombo().setEnabled(true);//

				expressionComboViewer_CLYJ.getCombo().setEnabled(true);//
			} else {

				btnCheckButton.setSelection(false);

				label_YJ.setEnabled(false);

				label_CLZ.setEnabled(false);

				expressionComboViewer_CLZ.getCombo().setEnabled(false);//

				expressionComboViewer_CLYJ.getCombo().setEnabled(false);//
			}
		} else {
			if (skipStrategy.isIsCreateSkipProcess()) {
				btnCheckButton.setSelection(true);
			} else {
				btnCheckButton.setSelection(false);
			}

			label_YJ.setEnabled(false);

			label_CLZ.setEnabled(false);

			expressionComboViewer_CLZ.getCombo().setEnabled(false);//

			expressionComboViewer_CLYJ.getCombo().setEnabled(false);//
		}
		/*
		 * if (skipStrategy.isIsCreateSkipProcess()&&skipStrategy.isIsEnable())
		 * { btnCheckButton.setSelection(true); label_YJ.setEnabled(true);
		 * 
		 * label_CLZ.setEnabled(true);
		 * 
		 * expressionComboViewer_CLZ.getCombo().setEnabled(true);//
		 * 
		 * expressionComboViewer_CLYJ.getCombo().setEnabled(true);// } else {
		 * btnCheckButton.setSelection(false); label_YJ.setEnabled(false);
		 * 
		 * label_CLZ.setEnabled(false);
		 * 
		 * expressionComboViewer_CLZ.getCombo().setEnabled(false);//
		 * 
		 * expressionComboViewer_CLYJ.getCombo().setEnabled(false);// }
		 */

		if (skipStrategy.getExpression() != null) {
			ExpressionTo expressionTo = new ExpressionTo();
			expressionTo.setName(skipStrategy.getExpression().getName());
			expressionTo.setExpressionText(skipStrategy.getExpression().getValue());
			expressionComboViewer.setDefaultExpressionInput(expressionTo);
		}

		if (skipStrategy.getSkipAssignee() != null && skipStrategy.getSkipAssignee().getExpression() != null) {

			ExpressionTo expressionTo = new ExpressionTo();
			expressionTo.setName(skipStrategy.getSkipAssignee().getExpression().getName());
			expressionTo.setExpressionText(skipStrategy.getSkipAssignee().getExpression().getValue());
			expressionComboViewer_CLZ.setDefaultExpressionInput(expressionTo);
		}

		if (skipStrategy.getSkipComment() != null && skipStrategy.getSkipComment().getExpression() != null) {
			ExpressionTo expressionTo = new ExpressionTo();
			expressionTo.setName(skipStrategy.getSkipComment().getExpression().getName());
			expressionTo.setExpressionText(skipStrategy.getSkipComment().getExpression().getValue());
			expressionComboViewer_CLYJ.setDefaultExpressionInput(expressionTo);
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

						setFormUriExtensionExpression(activity, event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}
				});

			}
		});

		expressionComboViewer_CLZ.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setCLZExpression(event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}

				});

			}
		});

		expressionComboViewer_CLYJ.addExpressionChangedListeners(new IExpressionChangedListener() {

			@Override
			public void expressionChanged(final ExpressionChangedEvent event) {
				// TODO Auto-generated method stub
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {

						setCLYJExpression(event.getExpressionTo());
						// be.eSet(a, e.diff.getNewValue());
					}

				});

			}
		});

	}

	private void setCLYJExpression(ExpressionTo expressionTo) {

		if (expressionTo == null) {
			if (skipStrategy.getSkipComment() != null) {
				skipStrategy.setSkipComment(null);
			}
		} else {

			if (skipStrategy.getSkipComment() != null) {
				Expression expression = FixFlowFactory.eINSTANCE.createExpression();
				skipStrategy.getSkipComment().setExpression(expression);
				skipStrategy.getSkipComment().getExpression().setName(expressionTo.getName());
				skipStrategy.getSkipComment().getExpression().setValue(expressionTo.getExpressionText());
			} else {
				SkipComment skipCommentObj = FixFlowFactory.eINSTANCE.createSkipComment();
				skipStrategy.setSkipComment(skipCommentObj);
				Expression expression = FixFlowFactory.eINSTANCE.createExpression();
				skipCommentObj.setExpression(expression);
				skipCommentObj.getExpression().setName(expressionTo.getName());
				skipCommentObj.getExpression().setValue(expressionTo.getExpressionText());
			}

		}

	}

	private void setCLZExpression(ExpressionTo expressionTo) {

		if (expressionTo == null) {
			if (skipStrategy.getSkipAssignee() != null) {
				skipStrategy.setSkipAssignee(null);
			}

		} else {

			if (skipStrategy.getSkipAssignee() != null) {

				Expression expression = FixFlowFactory.eINSTANCE.createExpression();
				skipStrategy.getSkipAssignee().setExpression(expression);
				skipStrategy.getSkipAssignee().getExpression().setName(expressionTo.getName());
				skipStrategy.getSkipAssignee().getExpression().setValue(expressionTo.getExpressionText());
			} else {
				SkipAssignee skipAssigneeObj = FixFlowFactory.eINSTANCE.createSkipAssignee();
				skipStrategy.setSkipAssignee(skipAssigneeObj);
				Expression expression = FixFlowFactory.eINSTANCE.createExpression();
				skipAssigneeObj.setExpression(expression);
				skipAssigneeObj.getExpression().setName(expressionTo.getName());
				skipAssigneeObj.getExpression().setValue(expressionTo.getExpressionText());
			}
		}

	}

	private void setQy(final boolean isQy) {

		if (isQy) {

			expressionComboViewer.getCombo().setEnabled(true);

			btnCheckButton.setEnabled(true);

			label_YJ.setEnabled(true);

			label_TGCL.setEnabled(true);

			label_CLZ.setEnabled(true);

			expressionComboViewer_CLZ.getCombo().setEnabled(true);

			expressionComboViewer_CLYJ.getCombo().setEnabled(true);

			group.setEnabled(true);

		} else {

			expressionComboViewer.getCombo().setEnabled(false);

			btnCheckButton.setEnabled(false);

			label_YJ.setEnabled(false);

			label_TGCL.setEnabled(false);

			label_CLZ.setEnabled(false);

			expressionComboViewer_CLZ.getCombo().setEnabled(false);

			expressionComboViewer_CLYJ.getCombo().setEnabled(false);

			group.setEnabled(false);

		}

	}

	private void setFormUriExtensionExpression(BaseElement baseElement, ExpressionTo expressionTo) {

		for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {

			FeatureMap extensionElements = extensionAttributeValue.getValue();
			for (Entry entry : extensionElements) {
				if (entry.getValue() instanceof SkipStrategy) {
					if (expressionTo == null) {
						SkipStrategy skipStrategy = (SkipStrategy) entry.getValue();
						skipStrategy.setExpression(null);

						// extensionElements.remove(entry);
					} else {
						SkipStrategy skipStrategy = (SkipStrategy) entry.getValue();
						Expression expression = FixFlowFactory.eINSTANCE.createExpression();
						skipStrategy.setExpression(expression);
						skipStrategy.getExpression().setName(expressionTo.getName());
						skipStrategy.getExpression().setValue(expressionTo.getExpressionText());
					}

					return;
				}
			}

		}
		if (expressionTo != null) {
			SkipStrategy formUri = FixFlowFactory.eINSTANCE.createSkipStrategy();

			Expression expression = FixFlowFactory.eINSTANCE.createExpression();
			expression.setName(expressionTo.getName());
			expression.setValue(expressionTo.getExpressionText());
			formUri.setExpression(expression);

			FeatureMap.Entry extensionElementEntry = new org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry(
					(org.eclipse.emf.ecore.EStructuralFeature.Internal) FixFlowPackage.Literals.DOCUMENT_ROOT__SKIP_STRATEGY, formUri);

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
