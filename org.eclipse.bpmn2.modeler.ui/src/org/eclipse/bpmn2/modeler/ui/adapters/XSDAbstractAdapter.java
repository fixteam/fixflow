/**
 * 
 */
package org.eclipse.bpmn2.modeler.ui.adapters;

import java.util.Map;

import org.eclipse.bpmn2.modeler.core.adapters.AbstractAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.IStatefullAdapter;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.IConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xsd.XSDNamedComponent;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 *
 */

public class XSDAbstractAdapter extends AbstractAdapter 
	implements ILabeledElement, IStatefullAdapter 
{
	
	public Image getLargeImage(Object object) {
		return Activator.getDefault().getImage(IConstants.ICON_PART_32);
	}	

	public Image getSmallImage(Object object) {
		return Activator.getDefault().getImage(IConstants.ICON_PART_16);
	}	

	public String getNamespacePrefix(String namespace) {
		Object context = getContext();
		// if this is 
		if (context instanceof EObject) {
			EObject eObject = (EObject) context;
			return ModelUtil.getNamespacePrefix(eObject, namespace);
		} else if (context instanceof Map) {
			return (String) ((Map)context).get(namespace);
		}
		
		return null;
	}

	public String getTypeLabel ( Object obj ) {
		return obj.getClass().getName();
	}
	
	
	public String getLabel ( Object obj  )
	{	
		XSDNamedComponent component = (XSDNamedComponent) ModelUtil.resolveXSDObject(obj);;
		String name =  component.getName();
		String ns = component.getTargetNamespace();
		
		if (name == null) {
			return getTypeLabel( obj );
		}
		
		if (ns == null) {
			return name;
		}
		
		String prefix = getNamespacePrefix(ns);
		
		if (prefix == null) {
			return "{" + ns + "}" + name; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return prefix + ":" + name; //$NON-NLS-1$
	}	
}