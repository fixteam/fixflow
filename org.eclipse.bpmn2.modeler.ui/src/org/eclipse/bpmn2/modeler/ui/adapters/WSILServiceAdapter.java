/**
 * 
 */
package org.eclipse.bpmn2.modeler.ui.adapters;

import org.eclipse.bpel.wsil.model.inspection.Name;
import org.eclipse.bpel.wsil.model.inspection.Service;
import org.eclipse.bpel.wsil.model.inspection.TypeOfAbstract;
import org.eclipse.bpmn2.modeler.core.adapters.AbstractAdapter;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.IConstants;
import org.eclipse.swt.graphics.Image;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 *
 */


@SuppressWarnings({"nls"})

public class WSILServiceAdapter extends AbstractAdapter 
	implements ILabeledElement 
{
	
	/** (non-Javadoc)
	 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getLargeImage(java.lang.Object)
	 */
	public Image getLargeImage(Object object) {
		// TODO: ?
		return null;
	}	

	/**
	 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getSmallImage(java.lang.Object)
	 */
	public Image getSmallImage(Object object) {
		return Activator.getDefault().getImage(IConstants.ICON_WSIL_SERVICE );
	}	


	/**
	 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getTypeLabel(java.lang.Object)
	 */
	public String getTypeLabel ( Object obj ) {
		return obj.getClass().getSimpleName();
	}
	
	
	/**
	 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getLabel(java.lang.Object)
	 */
	
	public String getLabel ( Object obj  )
	{	
		Service service = getTarget(obj, Service.class);		
		if (service == null) {
			return getTypeLabel(obj);
		}
		
		String label = "";
		Name name = null;
		TypeOfAbstract abs = null;
		
		if (service.getName().size() > 0) {
			name = service.getName().get(0);
			label += name.getValue();
			label += " - "; //$NON-NLS-1$
		}
			
		if  (service.getAbstract().size() > 0) {
			abs = service.getAbstract().get(0);
			label += abs.getValue();
		}			
		return label;
	}	
	
}