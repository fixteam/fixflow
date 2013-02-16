/**
 * 
 */
package org.eclipse.bpmn2.modeler.ui.adapters;

import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.IConstants;
import org.eclipse.bpmn2.modeler.ui.Messages;
import org.eclipse.swt.graphics.Image;

/**
 * @author mchmiele
 *
 */
public class XSDComplexTypeDefinitionAdapter extends XSDAbstractAdapter implements
		ILabeledElement {
	
	@Override
	public Image getSmallImage(Object object) {		
		return Activator.getDefault().getImage(IConstants.ICON_XSD_COMPLEX_TYPE_DEFINITION_16);
	}
		
	@Override
	public String getTypeLabel(Object object) {
		return Messages.XSDComplexTypeDefinitionAdapter_0; 
	}
	
}
