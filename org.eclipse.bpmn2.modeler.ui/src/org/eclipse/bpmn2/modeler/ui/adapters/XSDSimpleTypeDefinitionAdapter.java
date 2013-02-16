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
public class XSDSimpleTypeDefinitionAdapter extends XSDAbstractAdapter  {

	@Override
	public Image getSmallImage(Object object) {		
		return Activator.getDefault().getImage(IConstants.ICON_XSD_SIMPLE_TYPE_DEFINITION_16);
	}
		
	@Override
	public String getTypeLabel(Object object) {
		return Messages.XSDSimpleTypeDefinitionAdapter_0; 
	}	
}
