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
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.modeler.core.adapters.AdapterRegistry;
import org.eclipse.bpmn2.modeler.core.runtime.ModelEnablementDescriptor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.bpmn2.provider.Bpmn2ItemProviderAdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.BasicFeatureMapEntry;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

@SuppressWarnings("unchecked")
public class AdvancedPropertiesComposite extends AbstractBpmn2PropertiesComposite {

	private EObject be;
	private TreeViewer treeViewer;
	private AbstractBpmn2PropertiesComposite detailsPropertiesComposite;
	private ModelEnablementDescriptor modelEnablement;
	private TransactionalEditingDomain domain;
	private DomainListener domainListener;
	private Section treeSection;
	private Section detailsSection;
	private Composite detailsComposite;
	private Button fullDetails;
	private HashSet<Control> myChildren = new HashSet<Control>();
	
	class DomainListener extends ResourceSetListenerImpl {
		@Override
		public void resourceSetChanged(ResourceSetChangeEvent event) {
			List<Notification> notifications = event.getNotifications();
			for (Notification notification : notifications) {
				treeViewer.refresh(notification.getNotifier(), true);
			}
		}
	}

	/**
	 * @param section
	 */
	public AdvancedPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);

		GridData data;
		SashForm sashForm = new SashForm(this, SWT.BORDER);
		sashForm.setSashWidth(5);
		toolkit.adapt(sashForm);
		toolkit.paintBordersFor(sashForm);
		data = new GridData(SWT.FILL,SWT.FILL,true,true,3,1);
		data.widthHint = 800;
		sashForm.setLayoutData(data);

		treeSection = toolkit.createSection(sashForm, ExpandableComposite.TITLE_BAR);
		toolkit.paintBordersFor(treeSection);

		Composite composite = toolkit.createComposite(treeSection, SWT.NONE);
		toolkit.paintBordersFor(composite);
		treeSection.setClient(composite);
		composite.setLayout(new GridLayout(1, false));

		treeViewer = new TreeViewer(composite, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		data = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
//		data.widthHint = 200;
		tree.setLayoutData(data);
		toolkit.paintBordersFor(tree);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				updateDetailsSection();
			}
		});

		treeViewer.setContentProvider(new PropertyTreeContentProvider(this));
		treeViewer.setLabelProvider(AdapterRegistry.getLabelProvider());

		detailsSection = toolkit.createSection(sashForm, ExpandableComposite.TITLE_BAR);
		toolkit.paintBordersFor(detailsSection);
		detailsSection.setText("Properties");
		detailsComposite = toolkit.createComposite(detailsSection);
		detailsComposite.setLayout(new GridLayout(1,false));
		detailsSection.setClient(detailsComposite);


		fullDetails = toolkit.createButton(detailsComposite, "Show advanced details", SWT.CHECK);
		data = new GridData(SWT.LEFT,SWT.TOP,false,false,1,1);
		data.exclude = true;
		fullDetails.setLayoutData(data);
		fullDetails.setVisible(false); // TODO: is the "Show advanced details" useful?
		
		fullDetails.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				EObject obj = getSelectedBaseElement();
				if (obj!=null) {
					updateDetailsSection();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			
		});

		// don't construct the details composite yet because no selection has been
		// made from the tree. We'll construct a details composite using the selected
		// object's type (class) and then consult the PropertiesCompositeFactory for
		// a composite to use to render this object.
		
//		detailsPropertiesComposite = new DefaultPropertiesComposite(detailsSection, SWT.NONE);
//		detailsPropertiesComposite.setPropertySection(propertySection);
//		
//		detailsSection.setClient(detailsPropertiesComposite);
//		toolkit.adapt(detailsPropertiesComposite);
//		toolkit.paintBordersFor(detailsPropertiesComposite);
		
		sashForm.setWeights(new int[] { 1, 2 });
	}

	private void saveChildren(Control parent) {
		myChildren.add(parent);
		if (parent instanceof Composite) {
			Control[] children = ((Composite)parent).getChildren();
			for (Control c : children) {
				myChildren.add(c);
				saveChildren(c);
			}
		}
	}
	
	private void disposeDetailsChildren(Composite parent) {
		// clean up any table widgets that may have been created by the previous
		// incarnation of DefaultPropertiesComposite
		for (Control c : parent.getChildren()) {
			if (!myChildren.contains(c)) {
				// argh! For some reason the Sash in a SashForm is not returned as one of its children
				if (!(c instanceof Sash))
					c.dispose();
			}
			else if (c instanceof Composite)
				disposeDetailsChildren((Composite)c);
		}
	}
	
	private void updateDetailsSection() {
		if (myChildren.size()==0)
			saveChildren(getParent());
		disposeDetailsChildren(getParent());
		
		EObject obj = getSelectedBaseElement();
		if (obj==null) {
			detailsSection.layout(true); // refresh the layout's clientCache!
			detailsSection.setVisible(false);
		}
		else {
			// construct a details composite based on the selected object's class
			if (fullDetails.getSelection()) {
				detailsPropertiesComposite = new DefaultPropertiesComposite(detailsComposite,SWT.NONE);
			}
			else {
				detailsPropertiesComposite = PropertiesCompositeFactory.createComposite(obj.getClass(), detailsComposite, SWT.NONE);
			}
			detailsPropertiesComposite.setLayoutData(new GridData(SWT.FILL,SWT.TOP,true,false,3,1));

			Class cc = PropertiesCompositeFactory.findCompositeClass(obj.getClass());
			if (cc==null||cc==DefaultPropertiesComposite.class)
				fullDetails.setVisible(false);
			else
				fullDetails.setVisible(true);
			
			detailsComposite.layout(true);
			
			detailsPropertiesComposite.setPropertySection(propertySection);
			toolkit.adapt(detailsPropertiesComposite);
			toolkit.paintBordersFor(detailsPropertiesComposite);
			detailsSection.layout(true); // refresh the layout's clientCache!

			detailsPropertiesComposite.setEObject(propertySection.editor, obj);

			String name = PropertyUtil.getText(obj);
			detailsSection.setText(name+" Details");
			detailsSection.setVisible(true);

			PropertyUtil.recursivelayout(AdvancedPropertiesComposite.this);
			propertySection.tabbedPropertySheetPage.resizeScrolledComposite();
		}
	}
	
	protected void addDomainListener(BPMN2Editor editor) {
		removeDomainListener();
		domain = editor.getEditingDomain();
		domainListener = new DomainListener();
		domain.addResourceSetListener(domainListener);
	}

	protected void removeDomainListener() {
		if (domain!=null && domainListener!=null) {
			domain.removeResourceSetListener(domainListener);
			domainListener = null;
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite#createBindings(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void createBindings(EObject be) {
	}

	@Override
	public void dispose() {
		super.dispose();
		removeDomainListener();
	}

	@Override
	public void setEObject(BPMN2Editor diagramEditor, EObject be) {
		this.be = be;
		addDomainListener(diagramEditor);
		
		EObject input = be;
		while (input.eContainer()!=null) {
			if (input instanceof Definitions)
				break;
			input = input.eContainer();
		}
		if (input!=null) {
			treeViewer.setInput(input);
			treeViewer.expandAll();
			treeViewer.setSelection(new StructuredSelection(be),true);
		}
		else
			treeViewer.setInput(be);
		
		modelEnablement = diagramEditor.getTargetRuntime().getModelEnablements(be);
		hookPropertySheetPageMenu();
		treeSection.setText(PropertyUtil.getText(be));

		detailsSection.setVisible(true);
	}

	private void hookPropertySheetPageMenu() {
		MenuManager manager = new MenuManager("#PropertiesMenu");
		manager.setRemoveAllWhenShown(true);
		manager.addMenuListener(new IMenuListener() {

			@Override
			public void menuAboutToShow(IMenuManager manager) {
				AdvancedPropertiesComposite.this.buildMenu((MenuManager) manager);
			}
		});

		Tree tree = treeViewer.getTree();
		Menu menu = manager.createContextMenu(tree);
		tree.setMenu(menu);
		propertySection.tabbedPropertySheetPage.getSite().registerContextMenu("#PropertiesMenu", manager, treeViewer);
	}

	protected void buildMenu(MenuManager manager) {

		EObject selectedElem = getSelectedBaseElement();
		createElementProperties(manager, selectedElem);

		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		if (selectedElem != null) {
			manager.add(createRemoveAction(selectedElem));
		}

		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		createRootProperties(manager);
	}

	private void createRootProperties(MenuManager menuManager) {
		MenuManager manager = new MenuManager("Add Root Property");
		menuManager.add(manager);
		createMenuItems(manager, "", be, true);
	}

	private void createElementProperties(MenuManager manager, EObject baseElement) {
		if (baseElement != null) {
			createMenuItems(manager, "Add ", baseElement, false);
		}
	}

	private void createMenuItems(MenuManager manager, String prefix, EObject baseElement, boolean root) {
		ItemProviderAdapter itemProviderAdapter = (ItemProviderAdapter) new Bpmn2ItemProviderAdapterFactory().adapt(
				baseElement, ItemProviderAdapter.class);
		if (itemProviderAdapter==null)
			return;
		
		Collection<CommandParameter> desc = (Collection<CommandParameter>) itemProviderAdapter.getNewChildDescriptors(
				baseElement, propertySection.editor.getEditingDomain(), null);

		EList<EReference> eAllContainments = baseElement.eClass().getEAllContainments();
		List<Action>items = new ArrayList<Action>();
		
		for (CommandParameter command : desc) {
			EStructuralFeature feature = (EStructuralFeature) command.feature;
			EObject commandValue = null;
			if (command.value instanceof BasicFeatureMapEntry) {
				BasicFeatureMapEntry entry = (BasicFeatureMapEntry)command.value;
				feature = entry.getEStructuralFeature();
				commandValue = (EObject)entry.getValue();
			}
			else if (command.value instanceof EObject)
				commandValue = (EObject) command.value;
			
			if (root) {
				if (eAllContainments.contains(feature) && modelEnablement.isEnabled(commandValue.eClass())) {
					Object value = baseElement.eGet(feature);

					String name = PropertyUtil.deCamelCase(commandValue.eClass().getName());
					if (name.equalsIgnoreCase("Expression") || name.equalsIgnoreCase("Formal Expression")) {
						if (feature.getName() != null && !feature.getName().isEmpty()) {
							String featureName = PropertyUtil.deCamelCase(' ' + feature.getName());
							featureName = featureName.substring(0, 1).toUpperCase() + featureName.substring(1);
							name = featureName + ' ' + name;
						}
					}
					Action item = createMenuItemFor(prefix + name, baseElement, (EReference) feature, command.value);

					item.setEnabled(value == null || value instanceof EList);
					items.add(item);
				}
			} else {
				if (eAllContainments.contains(feature) && modelEnablement.isEnabled(baseElement.eClass(), feature) &&
						 modelEnablement.isEnabled(commandValue.eClass())) {
					Object value = baseElement.eGet(feature);

					String name = PropertyUtil.deCamelCase(commandValue.eClass().getName());
					if (name.equalsIgnoreCase("Expression") || name.equalsIgnoreCase("Formal Expression")) {
						if (feature.getName() != null && !feature.getName().isEmpty()) {
							String featureName = PropertyUtil.deCamelCase(' ' + feature.getName());
							featureName = featureName.substring(0, 1).toUpperCase() + featureName.substring(1);
							name = featureName + ' ' + name;
						}
					}
					Action item = createMenuItemFor(prefix + name, baseElement, (EReference) feature, command.value);

					item.setEnabled(value == null || value instanceof EList);
					items.add(item);
				}
			}
		}
		Collections.sort(items, new Comparator<Action>() {
			@Override
			public int compare(Action a1, Action a2) {
				return a1.getText().compareToIgnoreCase(a2.getText());
			}
		});

		for (Action a : items)
			manager.add(a);
	}

	private Action createMenuItemFor(String prefix, final EObject baseElement, final EReference eReference,
			final Object value) {
		return new Action(prefix) {
			@Override
			public void run() {
				TransactionalEditingDomain domain = propertySection.editor.getEditingDomain();
				domain.getCommandStack().execute(new RecordingCommand(domain) {
					@Override
					protected void doExecute() {
						createNewProperty(baseElement, eReference);
					}

					private void createNewProperty(final EObject baseElement, final EReference eReference) {
						Object eGet = baseElement.eGet(eReference);
						if (value instanceof BaseElement) {
							BaseElement e = ((BaseElement) value);
							if (e.getId() == null) {
//								e.setId(EcoreUtil.generateUUID());
								ModelUtil.setID(e,baseElement.eResource());
							}
						}

						if (eGet instanceof EList) {
							((EList) eGet).add(value);
						} else {
							baseElement.eSet(eReference, value);
						}
						treeViewer.refresh(true);
					}
				});
			}
		};
	}

	private EObject getSelectedBaseElement() {
		ISelection selection = treeViewer.getSelection();
		EObject baseElement = null;

		if (selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection) selection).getFirstElement();

			if (firstElement instanceof EObject) {
				baseElement = (EObject) firstElement;
			}
		}
		return baseElement;
	}

	private Action createRemoveAction(final EObject baseElement) {
		return new Action("Remove") {
			@SuppressWarnings("restriction")
			@Override
			public void run() {

				if (baseElement == null) {
					treeViewer.refresh(true);
					return;
				}

				final EObject container = baseElement.eContainer();
				final Object eGet = container.eGet(baseElement.eContainingFeature());
				TransactionalEditingDomain domain = propertySection.editor.getEditingDomain();

				domain.getCommandStack().execute(new RecordingCommand(domain) {
					@SuppressWarnings("rawtypes")
					@Override
					protected void doExecute() {
						List<PictogramElement> pictogramElements = GraphitiUi.getLinkService().getPictogramElements(
								propertySection.editor.getDiagramTypeProvider().getDiagram(), baseElement);
						if (eGet instanceof EList) {
							((EList) eGet).remove(baseElement);
						} else {
							container.eUnset(baseElement.eContainingFeature());
						}

						for (PictogramElement pictogramElement : pictogramElements) {
							// TODO: check if we need to update the diagram manually or will the autoupdate do
							// it
							// UpdateContext context = new UpdateContext(pictogramElement);
							// IUpdateFeature updateFeature = diagramEditor.getDiagramTypeProvider()
							// .getFeatureProvider().getUpdateFeature(context);
							// context.putProperty("deleted", baseElement);
							// if (updateFeature != null) {
							// updateFeature.update(context);
							// }
							pictogramElement.getLink().getBusinessObjects().clear();
						}
						treeViewer.refresh(true);
					}
				});

			}
		};
	}
}

