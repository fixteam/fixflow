/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.preferences;


import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.bpel.wsil.model.inspection.Inspection;
import org.eclipse.bpel.wsil.model.inspection.InspectionFactory;
import org.eclipse.bpel.wsil.model.inspection.InspectionPackage;
import org.eclipse.bpel.wsil.model.inspection.Link;
import org.eclipse.bpel.wsil.model.inspection.TypeOfAbstract;
import org.eclipse.bpel.wsil.model.inspection.WSILDocument;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerResourceSetImpl;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.Messages;
import org.eclipse.bpmn2.modeler.ui.property.providers.ColumnTableProvider;
import org.eclipse.bpmn2.modeler.ui.property.providers.TableCursor;
import org.eclipse.bpmn2.modeler.ui.property.providers.WSILContentProvider;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date May 2, 2007
 *
 */


@SuppressWarnings({"nls","boxing","unchecked"})

public class WSILPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	Bpmn2ModelerResourceSetImpl resourceSet = new Bpmn2ModelerResourceSetImpl();
		
	Text wsilURL;

	Table linkTable;
	ColumnTableProvider tableProvider;

	TableViewer linkTableViewer;

	TableCursor tableCursor;
	
	Link fLinkSelection;

	WSILDocument fWsilDocument;

	Button addButton;
	Button removeButton;
	Button moveUpButton;
	Button moveDownButton;
	Button openInBrowserButton;
	
	private static String WSIL = "wsil";
	
	// Track the  modification of any parameter in the WSIL model.
	// we don't use commands and stacks here.
	EContentAdapter fContentAdapter = new EContentAdapter() {
			
		/**
		 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		
		@Override
		public void notifyChanged(Notification arg0) {				

			super.notifyChanged(arg0);								
			int eventId = arg0.getEventType();								
			if (eventId == Notification.ADD ||  eventId == Notification.SET || 
				eventId == Notification.MOVE || eventId == Notification.REMOVE ) {
									
				linkTableViewer.refresh();
				updateButtons();					
			}				
		}		
	};

	
		
	@Override
	protected Control createContents (Composite parent) {
		
		Composite result = new Composite(parent, SWT.NONE);	
		
		GridLayout layout = new GridLayout( );
		layout.numColumns = 3;
		layout.verticalSpacing = 15;
		layout.makeColumnsEqualWidth = false;
		
		result.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		// result.setLayoutData(data);

		// WSIL directory
		Label wsilLabel = new Label(result, SWT.NONE);
		wsilLabel.setText(Messages.WSILPreferencePage_WSIL_1);
		wsilLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		
		wsilURL = new Text(result, SWT.BORDER);
		wsilURL.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		wsilURL.addFocusListener( new FocusListener () {


			public void focusGained(FocusEvent e) {
				wsilURL.setData("lastValue",wsilURL.getText());
			}

			public void focusLost(FocusEvent e) {
				String url = wsilURL.getText();
				
				// no change.
				if (url.equals(wsilURL.getData("lastValue"))) {
					return ;
				}						
				attemptLoad( url );				
			}
			
		});
		
		Button browse = new Button(result, SWT.NONE);
		browse.setText(Messages.WSILPreferencePage_WSIL_2);
		data = new GridData(SWT.RIGHT);
		data.widthHint = 100;
		
		browse.setLayoutData(data);
		browse.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				fd.setFilterExtensions(new String[]{"*."+WSIL});
				String fileName = fd.open();
				if ((fileName != null) && (fileName.length() > 0)) {
					// parse to file url
					File file = new File(fileName);
					String uri = file.toURI().toString();
					wsilURL.setText( uri );
					attemptLoad(uri);
					
				}
			}
		}
		);

		
		// 2nd row of the 3 cell grid
		
		Label txt = new Label ( result, SWT.NO_BACKGROUND | SWT.WRAP );
		
		txt.setText( Messages.WSILPreferencePage_WSIL_Description );
		data = new GridData( GridData.GRAB_HORIZONTAL );
		data.horizontalSpan = 3;
		txt.setLayoutData(data);
		
		//
		//

		// create table
		linkTable = new Table(result, SWT.FULL_SELECTION | SWT.V_SCROLL	| SWT.BORDER  );

		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		
		linkTable.setLayoutData(data);
		
		// set up table
		linkTable.setLinesVisible(true);
		linkTable.setHeaderVisible(true);
		
		tableProvider = new ColumnTableProvider();
		tableProvider.add(new IndexColumn());
		tableProvider.add(new AbstractColumn());
		tableProvider.add(new LocationColumn());
		// columnProvider.add(new NamespaceColumn());
		
		linkTableViewer = new TableViewer(linkTable);
		tableProvider.createTableLayout(linkTable);
		linkTableViewer.setLabelProvider(tableProvider);
		linkTableViewer.setCellModifier(tableProvider);
		
		WSILContentProvider wsilContentProvider = new WSILContentProvider();
		wsilContentProvider.setMode( WSILContentProvider.FLATTEN );
		
		linkTableViewer.setContentProvider( wsilContentProvider );
		
		linkTableViewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return element instanceof Link;
			}			
		});
		
		linkTableViewer.setColumnProperties(tableProvider.getColumnProperties());
		linkTableViewer.setCellEditors(tableProvider.createCellEditors(linkTable));
	

		tableCursor = TableCursor.create(linkTable, linkTableViewer);
			
		/// 
		
		
		layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 5;
		
		Composite buttonList = new Composite ( result , SWT.NONE );
		buttonList.setLayout(layout);
		
		data = new GridData( GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_CENTER );		 
		buttonList.setLayoutData( data );
		
		addButton = new Button(buttonList, SWT.NONE);
		addButton.setText(Messages.WSILPreferencePage_WSIL_Add);
		data = new GridData(  GridData.FILL_HORIZONTAL );
	
		addButton.setLayoutData(data);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Link link = InspectionFactory.eINSTANCE.createLink();
				
				link.setLocation(Messages.WSILPreferencePage_WSIL_EnterLocation);
				link.setReferencedNamespace( InspectionPackage.eNS_URI );
				
				TypeOfAbstract toa = InspectionFactory.eINSTANCE.createTypeOfAbstract();
				toa.setValue(Messages.WSILPreferencePage_WSIL_EnterDescription);
				link.getAbstract().add( toa );

				// modify the document
				EList links = fWsilDocument.getInspection().getLinks();
				links.add ( link );				
				
				// notifications of modifications are sent back to us via the EContentAdapter
				// on the WSIL Document resource.
			}
		});
	

		removeButton = new Button(buttonList, SWT.NONE);
		removeButton.setText(Messages.WSILPreferencePage_WSIL_Remove);
		data = new GridData( GridData.FILL_HORIZONTAL );
		
		removeButton.setLayoutData(data);
		
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 if (fLinkSelection == null) {
					 return ;
				 }
				 // modify the document
				 EList links = fWsilDocument.getInspection().getLinks();
				 links.remove(fLinkSelection);				 
			}
		});
	
		
		
		moveUpButton = new Button(buttonList, SWT.NONE);
		moveUpButton.setText(Messages.WSILPreferencePage_WSIL_MoveUp);
		data = new GridData( GridData.FILL_HORIZONTAL );
		
		moveUpButton.setLayoutData(data);
		
		moveUpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 if (fLinkSelection == null) {
					 return ;
				 }				 
				 // modify the document
				 EList links = fWsilDocument.getInspection().getLinks();
				 int idx = links.indexOf(fLinkSelection);
				 if (idx < 0) {
					 return ;
				 }
				 links.move(idx,idx-1);
			}
		});
		
		
		moveDownButton = new Button(buttonList, SWT.NONE);
		moveDownButton.setText(Messages.WSILPreferencePage_WSIL_MoveDown);
		data = new GridData( GridData.FILL_HORIZONTAL );
		
		moveDownButton.setLayoutData(data);
		
		moveDownButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 if (fLinkSelection == null) {
					 return ;
				}				 				
				 // modify the document
				 EList links = fWsilDocument.getInspection().getLinks();
				 int idx = links.indexOf(fLinkSelection);
				 if (idx < 0) {
					 return ;
				 }
				 links.move(idx, idx+1);
			}
		});
	
		openInBrowserButton = new Button(buttonList, SWT.NONE);
		openInBrowserButton.setText(Messages.WSILPreferencePage_WSIL_OpenInBrowser);
		data = new GridData( GridData.FILL_HORIZONTAL );
		
		openInBrowserButton.setLayoutData(data);
		
		openInBrowserButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 if (fLinkSelection == null) {
					 return ;
				 }
				
				 // TODO:
			}
		});
	
		
		
		linkTableViewer.addPostSelectionChangedListener( new ISelectionChangedListener () {


			public void selectionChanged (SelectionChangedEvent event) {				
				ISelection selection = event.getSelection();				
				if (selection instanceof IStructuredSelection) {					
					IStructuredSelection ss = (IStructuredSelection) selection;
					Object obj = ss.getFirstElement();					
					fLinkSelection = obj instanceof Link ? (Link) obj : null;					
				}
				updateButtons();
			}
															
		});
		
		updateButtons();
		
		initializeValues();		
		
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(
//			parent, IHelpContextIds.PREFERENCES_PAGE);
		
		return result;
	}
	

	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		
	}
	
	@Override
	protected void performDefaults() {
		super.performDefaults();
		initializeDefaults();
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		storeValues();
		return true;
	}

	@Override
	protected void performApply() {
		performOk();
	}
	
	/**
	 * Initializes states of the controls using default values in the preference store.
	 */
	private void initializeDefaults() {
		
	}

	/**
	 * Initializes states of the controls from the preference store.
	 */
	private void initializeValues() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		wsilURL.setText(store.getString(Bpmn2Preferences.PREF_WSIL_URL));
		
		attemptLoad (wsilURL.getText() );
	}

	
	
	/**
	 * Stores the values of the controls back to the preference store.
	 */
	private void storeValues() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		store.setValue(Bpmn2Preferences.PREF_WSIL_URL, wsilURL.getText());	

		if (fWsilDocument != null) {
			
			Resource resource = fWsilDocument.eResource();
			
			try {
				resource.save(null);
			} catch (IOException e) {
				Activator.logError(e);
			}
		}
	}
	
	
	void updateButtons ( ) {
				
		List linkList = (fWsilDocument == null ? Collections.EMPTY_LIST : fWsilDocument.getInspection().getLinks());
		
		int idx = linkList.indexOf(fLinkSelection);				
		// -1 if not found ...
		moveUpButton.setEnabled(idx > 0);
		moveDownButton.setEnabled(idx >= 0 && idx < linkList.size() - 1);
		removeButton.setEnabled( fLinkSelection != null );
		openInBrowserButton.setEnabled( fLinkSelection != null );
		addButton.setEnabled( fWsilDocument != null );
	}
	
	
	
	/**
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {
		
		Iterator it = resourceSet.getResources().iterator();
		while (it.hasNext()) {
			Resource r = (Resource) it.next();
			r.eAdapters().clear();
		}
		// TODO Auto-generated method stub
		super.dispose();
	}


	void attemptLoad ( String url ) {
		
		url = url.trim();
		
		if (url.length() < 1) {
			return ;
		}
		if(!url.endsWith(WSIL)){
			setMessage(Messages.WSILPreferencePage_WSIL_NameLimit, ERROR);
			return;
		}
		
		if (fWsilDocument != null) {
			fWsilDocument.eResource().eAdapters().remove( fContentAdapter );
			fWsilDocument = null;
		}		
		
		org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI(url);
		
		Resource resource = null;
		try {
			
			resource = resourceSet.getResource(uri, true, WSIL);
			
			List contents = resource.getContents();
			
			if (contents.size() > 0) {
				if (contents.get(0) instanceof WSILDocument ) {
					fWsilDocument = (WSILDocument ) contents.get(0); 
				}
			}
			setMessage(null);
			
		} catch (Exception e) {
			setMessage(Messages.WSILPreferencePage_WSIL_DocumentNotLoaded, ERROR);
			Activator.logError(e);
		}
		
		linkTableViewer.setInput( fWsilDocument );
		
		// Track the  modification of any parameter in the WSIL model.
		// we don't use commands and stacks here.
		if (fWsilDocument != null) {
			fWsilDocument.eResource().eAdapters().add( fContentAdapter );
		}
		updateButtons();
	}



	/**
	 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
	 * @date May 4, 2007
	 *
	 */
	public class AbstractColumn extends ColumnTableProvider.Column implements
			ILabelProvider, ICellModifier {
		
		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getHeaderText()
		 */
		@Override
		public String getHeaderText() {
			return Messages.WSILPreferencePage_WSIL_Abstract;
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getProperty()
		 */
		@Override
		public String getProperty() {
			return "abstract"; //$NON-NLS-1$
		} 

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getInitialWeight()
		 */
		@Override
		public int getInitialWeight() {
			return 50;
		}

		
		/**
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText (Object element) {
			if (element instanceof Link) {
				Link link = (Link) element;
				List abs = link.getAbstract();
				// TODO: Do this based on language ?
				if (abs.size() > 0) {
					TypeOfAbstract absType = (TypeOfAbstract) abs.get(0);
					return absType.getValue();					
				}				
			}
			return "";
		}
		
		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#createCellEditor(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		public CellEditor createCellEditor (Composite parent) {			
			return new TextCellEditor(parent, SWT.NO_BACKGROUND );
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
		 */
		public boolean canModify(Object element, String property) {
			return true;
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
		 */
		public Object getValue(Object element, String property) {
			return getText(element);
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
		 */
		public void modify(Object element, String property, Object value) {			
			if (element instanceof Link) {
				Link link = (Link) element;
				List abs = link.getAbstract();
				// TODO: Do this based on language ?
				if (abs.size() > 0) {
					TypeOfAbstract absType = (TypeOfAbstract) abs.get(0);
					
					// noop, do not needlessly modify
					if (value.equals(absType.getValue())) {
						return ;
					}
					
					absType.setValue( (String) value);					
				}				
			} 			
		}
	}

	
	
	/**
	 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
	 * @date May 4, 2007
	 *
	 */
	public class LocationColumn extends ColumnTableProvider.Column 
		implements ILabelProvider, ICellModifier {
		
		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getHeaderText()
		 */
		@Override
		public String getHeaderText() {
			return Messages.WSILPreferencePage_WSIL_Location;
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getProperty()
		 */
		@Override
		public String getProperty() {
			return "location"; //$NON-NLS-1$
		}

		
		/** (non-Javadoc)
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getInitialWeight()
		 */
		@Override
		public int getInitialWeight() {
			return 50;
		}

		/**
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText (Object element) {
			if (element instanceof Link) {
				Link link = (Link) element;
				return link.getLocation();
			}
			return "";
		}
		
		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#createCellEditor(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		public CellEditor createCellEditor (Composite parent) {			
			return new TextCellEditor(parent, SWT.NO_BACKGROUND );
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
		 */
		public boolean canModify(Object element, String property) {
			return true;
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
		 */
		public Object getValue(Object element, String property) {
			return getText(element);
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
		 */
		public void modify (Object element, String property, Object value) {
			
			if (element instanceof Link) {
				Link link = (Link) element;
				
				//noop, do not needlessly modify
				if (value.equals(link.getLocation())) {
					return ;
				}
				
				org.eclipse.emf.common.util.URI linkURI = null;
				try {
					
					linkURI = org.eclipse.emf.common.util.URI.createURI( (String) value );
					if (linkURI.isRelative()) {
						// path is relative to me ...
						URI parentURI = link.eResource().getURI();
						linkURI = linkURI.resolve(parentURI);
					}
					
					// so far, so good !
					// VZ: so why not use linkURI? To be reviewed later!
					link.setLocation( (String) value);
					
					// all goes well
					setMessage(null);
					
				} catch (java.lang.IllegalArgumentException ex) {
					
					setMessage(ex.getLocalizedMessage(), ERROR);
					
				}
				
								
			} 			
		}
		
	}

	/**
	 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
	 * @date May 4, 2007
	 */
	public class NamespaceColumn extends ColumnTableProvider.Column implements
			ILabelProvider {
		
		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getHeaderText()
		 */
		
		@Override
		public String getHeaderText() {
			return Messages.WSILPreferencePage_WSIL_Namespace;
		}

		
		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getProperty()
		 */
		@Override
		public String getProperty() {
			return "namespace"; //$NON-NLS-1$
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getInitialWeight()
		 */
		@Override
		public int getInitialWeight() {
			return 30;
		}

		/**
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText (Object element) {
						
			if (element instanceof Link) {
				Link link = (Link) element;
				// TODO: do we need to support namespace templates in modelEnablement?
//				return  NamespaceUtils.convertUriToNamespace( link.getReferencedNamespace() );
				return link.getReferencedNamespace();
			}
			return "";
		}
		
	}
	
	
	/**
	 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
	 * @date May 4, 2007
	 */
	public class IndexColumn extends ColumnTableProvider.Column implements
			ILabelProvider {
		
		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getHeaderText()
		 */
		
		@Override
		public String getHeaderText() {
			return Messages.WSILPreferencePage_WSIL_Index;
		}

		
		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getProperty()
		 */
		@Override
		public String getProperty() {
			return "index"; //$NON-NLS-1$
		}

		/**
		 * @see org.eclipse.bpel.ui.details.providers.ColumnTableProvider.Column#getInitialWeight()
		 */
		@Override
		public int getInitialWeight() {
			return 5;
		}

		/**
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText (Object element) {
						
			if (element instanceof Link) {
				Link link = (Link) element;
				Inspection insp = (Inspection) link.eContainer();
				return Integer.toString( insp.getLinks().indexOf(link) + 1 );
			}
			
			return "";
		}
	}	
		

}
