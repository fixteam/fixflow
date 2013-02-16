package org.eclipse.bpmn2.modeler.ui.property.providers;

import java.util.ArrayList;

import org.eclipse.bpel.wsil.model.inspection.Inspection;
import org.eclipse.bpel.wsil.model.inspection.Link;
import org.eclipse.bpel.wsil.model.inspection.WSILDocument;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerResourceSetImpl;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * WSIIL Content Provider
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @author Edward Gee
 * @date Apr 5, 2007
 *
 */

@SuppressWarnings({"unchecked","nls"})

public class WSILContentProvider implements ITreeContentProvider {

	/**
	 * Flatten the inspection document ( do not return root document )
	 */
	static public final int FLATTEN = 0x1;
	
	int fMode = 0;
	
	/**
	 * Set the mode of operation of this content provider.
	 * @param mode
	 */
	public void setMode ( int mode ) {
		fMode = mode;
	}
	
	
	/** (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	


	public Object[] getChildren (Object arg0) {
		
		ArrayList<Object> items = new ArrayList<Object>();
		
		if (arg0 instanceof WSILDocument) {
			if ((fMode & FLATTEN) == FLATTEN) {
				arg0 = ((WSILDocument)arg0).getInspection() ;
			} else {
				items.add(((WSILDocument)arg0).getInspection());
			}
		}
		
		
		if (arg0 instanceof Inspection) {
			Inspection insp = (Inspection) arg0;
			items.addAll( insp.getServices() );
			items.addAll( insp.getLinks() );			
		}
		
		if (arg0 instanceof Link) {
			
			Link link = (Link) arg0;
			
			Bpmn2ModelerResourceSetImpl resourceSet = ModelUtil.slightlyHackedResourceSet ( link );

			URI linkURI = null;
			
			try {
				linkURI = URI.createURI( link.getLocation() );
				if (linkURI.isRelative()) {				
					// path is relative to me ...
					URI parentURI = link.eResource().getURI();
					linkURI = linkURI.resolve(parentURI);
				}
			} catch (Throwable t) {				
				items.add(t);				
				return items.toArray();				
			}
					
			
			Resource resource = null;
			
			try {
				resource = resourceSet.getResource(linkURI, true, "wsil");
			} catch (Throwable t) {				
				items.add(t);
				return items.toArray();
				
			}
			
			
			if (resource != null) {
				
				Object root = resource.getContents().get(0);
				if (root instanceof WSILDocument) {
					WSILDocument doc = (WSILDocument) root;
					
					items.addAll( doc.getInspection().getLinks() );
					items.addAll( doc.getInspection().getServices() );					
				} else {
					items.add( new Exception("Not a WSIL document") );
				}
			}			
		}
		
		return items.toArray();
	}

	/** (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	
	public Object getParent(Object arg0) {
		
		if (arg0 instanceof EObject) {
			EObject eObj = (EObject) arg0;
			return eObj.eContainer();
		}
		return null;		
	}

	
	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	
	public boolean hasChildren(Object arg0) {
	
		return (arg0 instanceof WSILDocument ||
				arg0 instanceof Inspection ||
				arg0 instanceof Link) ;
		
	}

	/**
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements (Object arg0) {
		return getChildren(arg0);
	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
				
	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				
	}
	
}
