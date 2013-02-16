/**
 * 
 */
package org.eclipse.bpmn2.modeler.ui.adapters;

import java.util.List;

import org.eclipse.bpel.wsil.model.inspection.Inspection;
import org.eclipse.bpel.wsil.model.inspection.TypeOfAbstract;
import org.eclipse.bpmn2.modeler.core.adapters.AbstractAdapter;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.IConstants;
import org.eclipse.swt.graphics.Image;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 *
 */

public class WSILInspectionAdapter extends AbstractAdapter 
	implements ILabeledElement 
{
	
	/** (non-Javadoc)
	 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getLargeImage(java.lang.Object)
	 */
	public Image getLargeImage(Object object) {
		return null;
	}	

	/**
	 * @see org.eclipse.bpel.ui.adapters.ILabeledElement#getSmallImage(java.lang.Object)
	 */
	public Image getSmallImage(Object object) {
		return Activator.getDefault().getImage(IConstants.ICON_WSIL);
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
		Inspection inspection = getTarget(obj, Inspection.class);		
		if (inspection == null) {
			return getTypeLabel(obj);
		}
		List<?> abs = inspection.getAbstract();
		
		if (abs.size() > 0) {
			TypeOfAbstract iwa = (TypeOfAbstract) abs.get(0);
			return "Inspection - " + iwa.getValue();
		}
		return "Inspection " ;
	}	
	
}