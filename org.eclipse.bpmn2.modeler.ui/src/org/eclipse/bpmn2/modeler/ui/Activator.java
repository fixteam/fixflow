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
package org.eclipse.bpmn2.modeler.ui;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.bpel.wsil.model.inspection.InspectionPackage;
import org.eclipse.bpmn2.di.impl.BpmnDiPackageImpl;
import org.eclipse.bpmn2.impl.Bpmn2PackageImpl;
import org.eclipse.bpmn2.modeler.core.adapters.AdapterRegistry;
import org.eclipse.bpmn2.modeler.ui.adapters.Bpmn2EditorDiItemProviderAdapterFactory;
import org.eclipse.bpmn2.modeler.ui.adapters.Bpmn2EditorItemProviderAdapterFactory;
import org.eclipse.bpmn2.modeler.ui.adapters.Bpmn2WSDLAdapterFactory;
import org.eclipse.bpmn2.modeler.ui.adapters.Bpmn2WSILAdapterFactory;
import org.eclipse.bpmn2.modeler.ui.adapters.Bpmn2XSDAdapterFactory;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.ISaveParticipant;
import org.eclipse.core.resources.ISavedState;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.wst.wsdl.WSDLPackage;
import org.eclipse.xsd.XSDPackage;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.bpmn2.modeler.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	// handles changes to the bpmn file
	private BPMN2ResourceChangeListener resourceChangeListener;
	private ISaveParticipant saveParticipant;
	
	// Adapter Factory registration
	static {
		
		/*
		AdapterRegistry.INSTANCE.registerAdapterFactory(
			    WSDLPackage.eINSTANCE, Bpmn2WSDLAdapterFactory.getInstance());
		
		AdapterRegistry.INSTANCE.registerAdapterFactory(
			    XSDPackage.eINSTANCE, Bpmn2XSDAdapterFactory.getInstance());
		
		AdapterRegistry.INSTANCE.registerAdapterFactory(
			    InspectionPackage.eINSTANCE, Bpmn2WSILAdapterFactory.getInstance() );
		
		// BPMN2 metamodel adapter factories
		AdapterRegistry.BPMN2_ADAPTER_FACTORIES.addAdapterFactory(
				AdapterRegistry.INSTANCE.registerFactory(Bpmn2PackageImpl.eINSTANCE, new Bpmn2EditorItemProviderAdapterFactory()));
		AdapterRegistry.BPMN2_ADAPTER_FACTORIES.addAdapterFactory(
				AdapterRegistry.INSTANCE.registerFactory(BpmnDiPackageImpl.eINSTANCE, new Bpmn2EditorDiItemProviderAdapterFactory()));
	*/
	}

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		initializeResourceChangeListener();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if (workspace != null) {
			workspace.removeResourceChangeListener(this.resourceChangeListener);
		}
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static void logStatus(IStatus status) {
		Platform.getLog(plugin.getBundle()).log(status);
	}

	public static void logError(Exception e) {
		logStatus(createStatus(e));
	}

	private static Status createStatus(Exception e) {
		return new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
	}

	public static void showErrorWithLogging(Exception e){
		Status s = createStatus(e);
		logStatus(s);
		ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "An error occured", null, s);
	}

	/**
	 * Return the dialog settings for a given object. The object may be a string
	 * or any other java object. In that case, the object's class name will be used
	 * to retrieve that section name.
	 * 
	 * @param object 
	 * @return the dialog settings for that object
	 * 
	 */
	public IDialogSettings getDialogSettingsFor ( Object object ) 
	{
	    String name = object.getClass().getName();
	    if (object instanceof String) {
	        name = (String) object;
	    }
	    
	    IDialogSettings main = getDialogSettings();	    
	    IDialogSettings settings = main.getSection( name );
	    if (settings == null) {
	        settings = main.addNewSection(name);
	    }
	    return settings;
	}

	/**
	 * @return
	 */
	public String getID() {
		return getBundle().getSymbolicName();
	}
	
	/**
	 * Initializes the table of images used in this plugin.
	 */
	@Override
	protected ImageRegistry createImageRegistry() {
		ImageRegistry registry = super.createImageRegistry();
		URL baseURL = getBundle().getEntry("/"); //$NON-NLS-1$

		// A little reflection magic ... so that we don't
		// have to add the createImageDescriptor every time
		// we add it to the IConstants ..
		Field fields[] = IConstants.class.getFields();	
		for(int i=0; i < fields.length; i++) {
			Field f = fields[i];
			if (f.getType() != String.class) { 
				continue;
			}
			String name = f.getName();
			if (name.startsWith("ICON_") || name.startsWith("CURSOR_") || name.startsWith("IMAGE_")) {   //$NON-NLS-1$ //$NON-NLS-2$
				try {
					String value = (String) f.get(null);
					createImageDescriptor(registry, value, baseURL);
				} catch (Exception e) {
					logError(e);
				}
			}			
		}
		return registry;
	}

	/**
	 * Creates an image descriptor and places it in the image registry.
	 */
	private void createImageDescriptor(ImageRegistry registry, String id, URL baseURL) {
		URL url = null;
		try {
			url = new URL(baseURL, IConstants.ICON_PATH + id);
		} catch (MalformedURLException e) {
			logError(e);
		}
		ImageDescriptor desc = ImageDescriptor.createFromURL(url);
		registry.put(id, desc);
	}

	public Image getImage(String id) {
		return getImageRegistry().get(id);
	}


	/**
	 * Installs the IResourceChangeListener for this Plugin. Also
	 * checks if there were any changes to bpmn files while the plug-in
	 * was not active.
	 */
	private void initializeResourceChangeListener() throws CoreException {
		this.resourceChangeListener = new BPMN2ResourceChangeListener();
		// Add the save participant in a separate thread
		// to make sure that it doesn't block the UI thread and potentially cause
		// deadlocks with the code that caused our plugin to be started.
		Thread initSaveParticipantThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					IWorkspace workspace = ResourcesPlugin.getWorkspace();
					workspace.addResourceChangeListener(Activator.this.resourceChangeListener, IResourceChangeEvent.POST_BUILD);
					ISavedState savedState = workspace.addSaveParticipant("BPMN2 Modeler", getSaveParticipant());
					if (savedState != null) {
						savedState.processResourceChangeEvents(Activator.this.resourceChangeListener);
					}
				} catch (CoreException e) {
					throw new RuntimeException(e);
				}
			}
		});
		initSaveParticipantThread.setName("BPMN2 Modeler plugin init"); //$NON-NLS-1$
		initSaveParticipantThread.start();
	}

	/**
	 * We are only interested in the resource delta while the plugin was
	 * not active and don't really care about the plug-in save lifecycle.
	 */
	private ISaveParticipant getSaveParticipant() {
		if (this.saveParticipant == null) {
			this.saveParticipant = new ISaveParticipant() {
				@Override
				public void doneSaving(ISaveContext context) {
				}
				@Override
				public void prepareToSave(ISaveContext context) throws CoreException {
				}
				@Override
				public void rollback(ISaveContext context) {
				}
				@Override
				public void saving(ISaveContext context) throws CoreException {
					context.needDelta();
				}
			};
		}
		return this.saveParticipant;
	}

	/**
	 * Returns the resource change listener.
	 */
	public BPMN2ResourceChangeListener getResourceChangeListener() {
		return this.resourceChangeListener;
	}

}
