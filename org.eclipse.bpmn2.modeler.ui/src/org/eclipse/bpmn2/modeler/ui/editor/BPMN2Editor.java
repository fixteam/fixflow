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
package org.eclipse.bpmn2.modeler.ui.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.ProxyURIConverterImplExtension;
import org.eclipse.bpmn2.modeler.core.di.DIImport;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerResourceImpl;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.IFileChangeListener;
import org.eclipse.bpmn2.modeler.ui.util.ErrorUtils;
import org.eclipse.bpmn2.modeler.ui.wizards.BPMN2DiagramCreator;
import org.eclipse.bpmn2.modeler.ui.wizards.Bpmn2DiagramEditorInput;
import org.eclipse.bpmn2.util.Bpmn2ResourceImpl;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Lifecycle;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.internal.editor.GFPaletteRoot;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

/**
 * 
 */
@SuppressWarnings("restriction")
public class BPMN2Editor extends DiagramEditor implements IPropertyChangeListener {

	public static final String EDITOR_ID = "org.eclipse.bpmn2.modeler.ui.bpmn2editor";
	public static final String CONTRIBUTOR_ID = "org.eclipse.bpmn2.modeler.ui.PropertyContributor";

	private ModelHandler modelHandler;
	private URI modelUri;
	private URI diagramUri;

	private IFile modelFile;
	private IFile diagramFile;
	
	private IFileChangeListener fileChangeListener;
	private IWorkbenchListener workbenchListener;
	private ISelectionListener selectionListener;
	private boolean workbenchShutdown = false;
	private static BPMN2Editor activeEditor;
	
	private BPMN2EditingDomainListener editingDomainListener;
	
	private Bpmn2Preferences preferences;
	private TargetRuntime targetRuntime;

	protected BPMN2EditorAdapter editorAdapter;

	protected class BPMN2EditorAdapter implements Adapter {
		public Notifier getTarget() { return null; }
		public void setTarget(Notifier newTarget) { }
		public boolean isAdapterForType(Object type) { return (type == BPMN2EditorAdapter.class); }
		public void notifyChanged(Notification notification) { }
		public BPMN2Editor getBPMN2Editor() { return BPMN2Editor.this; }
	}

	/**
	 * Given a ResourceSet, this helper identifies the BPELEditor (if any) that created it
	 */
	public static BPMN2Editor getEditor(EObject object) {
		if (object!=null && object.eResource()!=null)
			return getEditor(object.eResource().getResourceSet());
		return null;
	}
	
	public static BPMN2Editor getActiveEditor() {
		return activeEditor;
	}
	
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		// 将网格设置为不可见
		getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE,
				Boolean.FALSE);
		
		

	}
	
	private void setActiveEditor(BPMN2Editor editor) {
		activeEditor = editor;
		if (activeEditor!=null) {
			Bpmn2Preferences.setActiveProject(modelFile.getProject());
			TargetRuntime.setCurrentRuntime( getTargetRuntime() );
		}
	}

	public static BPMN2Editor getEditor(ResourceSet resourceSet) {
	    Iterator<Adapter> it = resourceSet.eAdapters().iterator();
	    while (it.hasNext()) {
	        Object next = it.next();
	        if (next instanceof BPMN2EditorAdapter) {
	            return ((BPMN2EditorAdapter)next).getBPMN2Editor();
	        }
	    }
	    return null;
	}
	
	protected BPMN2EditorAdapter getEditorAdapter() {
		return editorAdapter;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		try {
			Bpmn2DiagramType diagramType = Bpmn2DiagramType.NONE;
			String targetNamespace = null;
			if (input instanceof IFileEditorInput) {
				modelFile = ((IFileEditorInput) input).getFile();
				loadPreferences(modelFile.getProject());

				input = createNewDiagramEditorInput(diagramType, targetNamespace);

			} else if (input instanceof DiagramEditorInput) {
				getModelPathFromInput((DiagramEditorInput) input);
				loadPreferences(modelFile.getProject());
				if (input instanceof Bpmn2DiagramEditorInput) {
					diagramType = ((Bpmn2DiagramEditorInput)input).getInitialDiagramType();
					targetNamespace = ((Bpmn2DiagramEditorInput)input).getTargetNamespace();
				}
				// This was incorrectly constructed input, we ditch the old one and make a new and clean one instead
				// This code path comes in from the New File Wizard
				input = createNewDiagramEditorInput(diagramType, targetNamespace);
			}
		} catch (CoreException e) {
			Activator.showErrorWithLogging(e);
		}
		
		// add a listener so we get notified if the workbench is shutting down.
		// in this case we don't want to delete the temp file!
		addWorkbenchListener();
		setActiveEditor(this);
		
		// allow the runtime extension to construct custom tasks and whatever else it needs
		// custom tasks should be added to the current target runtime's custom tasks list
		// where they will be picked up by the toolpalette refresh.
		getTargetRuntime().getRuntimeExtension().initialize();
		
		super.init(site, input);
		addSelectionListener();
		addFileChangeListener();
		
		// 工具栏设置到左边
				getPalettePreferences().setDockLocation(PositionConstants.WEST);
				//getPalettePreferences()
	}

	public Bpmn2Preferences getPreferences() {
		if (preferences==null) {
			assert(modelFile!=null);
			IProject project = modelFile.getProject();
			loadPreferences(project);
		}
		return preferences;
	}
	
	private void loadPreferences(IProject project) {
		preferences = Bpmn2Preferences.getInstance(project);
		preferences.load();
		preferences.getGlobalPreferences().addPropertyChangeListener(this);
	}

	/**
	 * ID for tabbed property sheets.
	 * 
	 * @return the contributor id
	 */
	@Override
	public String getContributorId() {
		return CONTRIBUTOR_ID;
	}

	public TargetRuntime getTargetRuntime() {
		if (targetRuntime==null)
			targetRuntime = getPreferences().getRuntime(modelFile);
		return targetRuntime;
	}
	
	private void getModelPathFromInput(DiagramEditorInput input) {
		URI uri = input.getDiagram().eResource().getURI();
		String uriString = uri.trimFragment().toPlatformString(true);
		modelFile = BPMN2DiagramCreator.getModelFile(new Path(uriString));
	}

	/**
	 * Beware, creates a new input and changes this editor!
	 */
	private Bpmn2DiagramEditorInput createNewDiagramEditorInput(Bpmn2DiagramType diagramType, String targetNamespace)
			throws CoreException {
		IPath fullPath = modelFile.getFullPath();
		modelUri = URI.createPlatformResourceURI(fullPath.toString(), true);

		IFolder folder = BPMN2DiagramCreator.getTempFolder(fullPath);
		diagramFile = BPMN2DiagramCreator.getTempFile(fullPath,folder);

		// Create new temporary diagram file
		BPMN2DiagramCreator creator = new BPMN2DiagramCreator();
		creator.setDiagramFile(diagramFile);

		Bpmn2DiagramEditorInput input = creator.createDiagram(diagramType,targetNamespace,false);
		diagramUri = creator.getUri();

		return input;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		modelHandler.save();
		((BasicCommandStack) getEditingDomain().getCommandStack()).saveIsDone();
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		
		// Hook a transaction exception handler so we can get diagnostics about EMF validation errors.
		getEditingDomainListener();
		
		BasicCommandStack basicCommandStack = (BasicCommandStack) getEditingDomain().getCommandStack();

		if (input instanceof DiagramEditorInput) {
			ResourceSet resourceSet = getEditingDomain().getResourceSet();
			getTargetRuntime().setResourceSet(resourceSet);
			
			Bpmn2ResourceImpl bpmnResource = (Bpmn2ResourceImpl) resourceSet.createResource(modelUri,
					Bpmn2ModelerResourceImpl.BPMN2_CONTENT_TYPE_ID);

			resourceSet.setURIConverter(new ProxyURIConverterImplExtension());
			resourceSet.eAdapters().add(editorAdapter = new BPMN2EditorAdapter());

			modelHandler = ModelHandlerLocator.createModelHandler(modelUri, bpmnResource);
			ModelHandlerLocator.put(diagramUri, modelHandler);

			try {
				if (modelFile.exists()) {
					bpmnResource.load(null);
				} else {
					doSave(null);
				}
			} catch (IOException e) {
				Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
				ErrorUtils.showErrorWithLogging(status);
			}
			basicCommandStack.execute(new RecordingCommand(getEditingDomain()) {

				@Override
				protected void doExecute() {
					importDiagram();
				}
			});
		}
		basicCommandStack.saveIsDone();
		basicCommandStack.flush();
	}
	
	private void importDiagram() {
		// make sure this guy is active, otherwise it's not selectable
		Diagram diagram = getDiagramTypeProvider().getDiagram();
		IFeatureProvider featureProvider = getDiagramTypeProvider().getFeatureProvider();
		diagram.setActive(true);
		Bpmn2DiagramEditorInput input = (Bpmn2DiagramEditorInput) getEditorInput();
		Bpmn2DiagramType diagramType = input.getInitialDiagramType();
		String targetNamespace = input.getTargetNamespace();

		if (diagramType != Bpmn2DiagramType.NONE) {
			BPMNDiagram bpmnDiagram = modelHandler.createDiagramType(diagramType, targetNamespace);
			featureProvider.link(diagram, bpmnDiagram);
			BPMN2Editor.this.doSave(null);
		}
		
		DIImport di = new DIImport();
		di.setDiagram(diagram);
		di.setDomain(getEditingDomain());
		di.setModelHandler(modelHandler);
		di.setFeatureProvider(featureProvider);
		di.generateFromDI();

		// this needs to happen AFTER the diagram has been imported because we need
		// to be able to determine the diagram type from the file's contents in order
		// to build the right tool palette for the target runtime and model enablements.
		GFPaletteRoot pr = (GFPaletteRoot)getPaletteRoot();
		pr.updatePaletteEntries();
	}

	@Override
	protected PictogramElement[] getPictogramElementsForSelection() {
		// filter out invisible elements when setting selection
		ArrayList<PictogramElement> visibleList = new ArrayList<PictogramElement>();
		PictogramElement[] pictogramElements = getSelectedPictogramElements();
		for (PictogramElement pe : pictogramElements) {
			if (pe.isVisible())
				visibleList.add(pe);
		}
		return visibleList.toArray(new PictogramElement[visibleList.size()]);
	}
	
	private void addWorkbenchListener() {
		if (workbenchListener==null) {
			workbenchListener = new IWorkbenchListener() {
				@Override
				public boolean preShutdown(IWorkbench workbench, boolean forced) {
					workbenchShutdown = true;
					return true;
				}

				@Override
				public void postShutdown(IWorkbench workbench) {
				}

			};
			PlatformUI.getWorkbench().addWorkbenchListener(workbenchListener);
		}
	}
	
	private void removeWorkbenchListener()
	{
		if (workbenchListener!=null) {
			PlatformUI.getWorkbench().removeWorkbenchListener(workbenchListener);
			workbenchListener = null;
		}
	}
	
	private void addSelectionListener()
	{
		if (selectionListener==null) {
			selectionListener = new ISelectionListener() {

				@Override
				public void selectionChanged(IWorkbenchPart part, ISelection selection) {
					if (part == BPMN2Editor.this) {
						setActiveEditor(BPMN2Editor.this );
					}
				}
				
			};
			getSite().getPage().addSelectionListener(selectionListener);
		}
	}

	private void removeSelectionListener()
	{
		if (selectionListener!=null) {
			getSite().getPage().removeSelectionListener(selectionListener);
			selectionListener = null;
		}
	}

	private void addFileChangeListener() {
		if (fileChangeListener==null) {
			fileChangeListener = new IFileChangeListener() {
				public void deleted(IPath filePath) {
					// close the editor if either the dummy diagramfile (in the .bpmn2 folder)
					// or the model file is deleted
					if (modelFile.getFullPath().equals(filePath)) {
						// Close the editor.
						Display display = getSite().getShell().getDisplay();
						display.asyncExec(new Runnable() {
							public void run() {
								getSite().getPage().closeEditor(BPMN2Editor.this, false);
							}
						});
					}
					else if (diagramFile.getFullPath().equals(filePath)) {
						// already handled by Graphiti
					}
				}
				public void moved(IPath oldFilePath, IPath newFilePath) {
					// handle file move/rename after the fact (i.e. newFile now exists, old file does not)
					if (modelFile.getFullPath().equals(oldFilePath)) {
						// same behavior as Graphiti
						// TODO: if Graphiti behavior changes so that it can handle file rename/move
						// then we need to change this as well.
						deleted(oldFilePath);
					}
					else if (diagramFile.getFullPath().equals(oldFilePath)) {
						// Graphiti handles this as a file delete notification and closes the editor.
						System.out.println("BPMN2Editor diagram file has moved from "+oldFilePath+" to "+newFilePath);
					}
				}
			};
			Activator.getDefault().getResourceChangeListener().addListener(fileChangeListener);
		}
	}

	private void removeFileChangeListener() {
		if (fileChangeListener!=null) {
			Activator.getDefault().getResourceChangeListener().removeListener(fileChangeListener);
			fileChangeListener = null;
		}
	}
	
	public BPMN2EditingDomainListener getEditingDomainListener() {
		if (editingDomainListener==null) {
			TransactionalEditingDomainImpl editingDomain = (TransactionalEditingDomainImpl)getEditingDomain();
			if (editingDomain==null) {
				return null;
			}
			editingDomainListener = new BPMN2EditingDomainListener(this);

			Lifecycle domainLifeCycle = (Lifecycle) editingDomain.getAdapter(Lifecycle.class);
			domainLifeCycle.addTransactionalEditingDomainListener(editingDomainListener);
		}
		return editingDomainListener;
	}
	
	public BasicDiagnostic getDiagnostics() {
		return getEditingDomainListener().getDiagnostics();
	}
	
	@Override
	public void dispose() {
		// clear ID mapping tables if no more instances of editor are active
		int instances = 0;
		IWorkbenchPage[] pages = getEditorSite().getWorkbenchWindow().getPages();
		for (IWorkbenchPage p : pages) {
			IEditorReference[] refs = p.getEditorReferences();
			instances += refs.length;
		}
		ModelUtil.clearIDs(modelHandler.getResource(), instances==0);
		getPreferences().getInstance(modelHandler.getResource()).getGlobalPreferences().removePropertyChangeListener(this);
		
		getResourceSet().eAdapters().remove(getEditorAdapter());
		removeSelectionListener();
		removeFileChangeListener();
		if (instances==0)
			setActiveEditor(null);
		
		super.dispose();
		ModelHandlerLocator.releaseModel(modelUri);
		// get rid of temp files and folders, button only if the workbench is being shut down.
		// when the workbench is restarted, we need to have those temp files around!
		if (!workbenchShutdown)
			BPMN2DiagramCreator.dispose(diagramFile);
		removeWorkbenchListener();
		getPreferences().dispose();
	}

	public IFile getModelFile() {
		return modelFile;
	}

	public IFile getDiagramFile() {
		return diagramFile;
	}
	
	public ModelHandler getModelHandler() {
		return modelHandler;
	}
	
	@Override
	public void setFocus() {
		
		try {
			super.setFocus();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// Graphiti understands multipage editors
		super.selectionChanged(part,selection); // Graphiti's DiagramEditorInternal
		// but apparently GEF doesn't
		updateActions(getSelectionActions()); // usually done in GEF's GraphicalEditor
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getProperty().endsWith(Bpmn2Preferences.PREF_SHAPE_STYLE)) {
			getEditingDomain().getCommandStack().execute(new RecordingCommand(getEditingDomain()) {
				@Override
				protected void doExecute() {
					IPeService peService = Graphiti.getPeService();
					TreeIterator<EObject> iter = getDiagramTypeProvider().getDiagram().eAllContents();
					while (iter.hasNext()) {
						EObject o = iter.next();
						if (o instanceof PictogramElement) {
							PictogramElement pe = (PictogramElement)o;
							BaseElement be = BusinessObjectUtil.getFirstElementOfType(pe, BaseElement.class);
							if (be!=null) {
								TreeIterator<EObject> childIter = pe.eAllContents();
								while (childIter.hasNext()) {
									o = childIter.next();
									if (o instanceof GraphicsAlgorithm) {
										GraphicsAlgorithm ga = (GraphicsAlgorithm)o;
										if (peService.getPropertyValue(ga, Bpmn2Preferences.PREF_SHAPE_STYLE)!=null) {
											StyleUtil.applyStyle(ga, be);
										}
									}
			
								}
							}
						}
					}
				}
			});
		}
	}
}
