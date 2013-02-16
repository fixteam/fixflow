/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.ui.property.editors;

import java.util.Hashtable;
import java.util.Map.Entry;

import org.eclipse.bpmn2.modeler.core.adapters.AdapterRegistry;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.dialogs.FeatureEditingDialog;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Bob Brodt
 *
 */
public class ComboObjectEditor extends MultivalueObjectEditor {

	protected ComboViewer comboViewer;
	private boolean ignoreComboSelections;
	private boolean keyPressed = false;
	
	/**
	 * @param parent
	 * @param object
	 * @param feature
	 */
	public ComboObjectEditor(AbstractBpmn2PropertiesComposite parent, EObject object, EStructuralFeature feature) {
		super(parent, object, feature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.editors.ObjectEditor#createControl(org.eclipse.swt.widgets.Composite, java.lang.String, int)
	 */
	@Override
	public Control createControl(Composite composite, String label, int style) {
		createLabel(composite, label);

		boolean canEdit = PropertyUtil.canEdit(object,feature);
		boolean canEditInline = PropertyUtil.canEditInline(object,feature);
		boolean canCreateNew = PropertyUtil.canCreateNew(object,feature);
		
		if (!canEditInline)
			style |= SWT.READ_ONLY;
		comboViewer = createComboViewer(composite,
				AdapterRegistry.getLabelProvider(), style);
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, (canEdit || canCreateNew) ? 1 : 2, 1));
		combo.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				comboViewer = null;
			}
			
		});
		
		if (canEditInline) {
			combo.addKeyListener( new KeyListener() {

				@Override
				public void keyPressed(KeyEvent e) {
					keyPressed = true;
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
				
			});
			combo.addFocusListener( new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (keyPressed) {
						keyPressed = false;
						String text = comboViewer.getCombo().getText();
						comboViewer.setSelection(new StructuredSelection(text));
					}
				}
				
			});
		}

		Composite buttons = null;
		if (canEdit || canCreateNew) {
			buttons =  getToolkit().createComposite(composite);
			buttons.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
			buttons.setLayout(new FillLayout(SWT.HORIZONTAL));

			if (canCreateNew) {
				Button createButton = getToolkit().createButton(buttons, "Create New...", SWT.PUSH);
				createButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						// create a new target object
						FeatureEditingDialog dialog = new FeatureEditingDialog(getDiagramEditor(), object, feature, null);							
						if ( dialog.open() == Window.OK) {
							EObject value = dialog.getNewObject();
							updateObject(value);
							fillCombo();
						}
					}
				});
			}
			if (canEdit) {
				Button editButton = getToolkit().createButton(buttons, "Edit...", SWT.PUSH);
				editButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						ISelection selection = comboViewer.getSelection();
						if (selection instanceof StructuredSelection) {
							String firstElement = (String) ((StructuredSelection) selection).getFirstElement();
							if ((firstElement != null && firstElement.isEmpty())) {
								// nothing to edit
								firstElement = null;
							}
							if (firstElement != null && comboViewer.getData(firstElement) instanceof EObject) {
								EObject value = (EObject) comboViewer.getData(firstElement);
								FeatureEditingDialog dialog = new FeatureEditingDialog(getDiagramEditor(),
										object, feature, value);
								if ( dialog.open() == Window.OK) {
									value = dialog.getNewObject();
									updateObject(value);
									fillCombo();
								}
							}
						}
					}
				});
			}
		}
		
		fillCombo();

		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (!ignoreComboSelections) {
					ISelection selection = comboViewer.getSelection();
					if (selection instanceof StructuredSelection) {
						String firstElement = (String) ((StructuredSelection) selection).getFirstElement();
						if(firstElement!=null && comboViewer.getData(firstElement)!=null)
							updateObject(comboViewer.getData(firstElement));
						else {
							if (firstElement!=null && firstElement.isEmpty())
								firstElement = null;
							if (firstElement==null)
								firstElement = comboViewer.getCombo().getText();
							updateObject(firstElement);
							fillCombo();
						}
					}
				}
			}
		});
		
		return combo;
	}
	
	@Override
	protected boolean updateObject(Object result) {
		keyPressed = false;
		return super.updateObject(result);
	}

	protected void fillCombo() {
		if (comboViewer!=null) {
			Object oldValue =  object.eGet(feature);
			// hack to deal with List features: use the first element in the list to
			// determine which item to select as active in the combobox
			if (oldValue instanceof EObjectEList) {
				EObjectEList list = (EObjectEList)oldValue;
				if (list.size()>0)
					oldValue = list.get(0);
			}
	
			ignoreComboSelections = true;
			while (comboViewer.getElementAt(0) != null)
				comboViewer.remove(comboViewer.getElementAt(0));
			ignoreComboSelections = false;
			
			Hashtable<String,Object> choices = getChoiceOfValues(object, feature);
			if (PropertyUtil.canSetNull(object,feature)) {
				// selecting this one will set the target's value to null
				comboViewer.add("");
			}
			// add all other possible selections
			for (Entry<String, Object> entry : choices.entrySet()) {
				comboViewer.add(entry.getKey());
				Object newValue = entry.getValue(); 
				if (newValue!=null) {
					comboViewer.setData(entry.getKey(), newValue);
					if (newValue.equals(oldValue))
						comboViewer.setSelection(new StructuredSelection(entry.getKey()));
				}
			}
		}
	}

	private ComboViewer createComboViewer(Composite parent, AdapterFactoryLabelProvider labelProvider, int style) {
		ComboViewer comboViewer = new ComboViewer(parent, style);
		comboViewer.setLabelProvider(labelProvider);

		Combo combo = comboViewer.getCombo();
		
		return comboViewer;
	}
}
