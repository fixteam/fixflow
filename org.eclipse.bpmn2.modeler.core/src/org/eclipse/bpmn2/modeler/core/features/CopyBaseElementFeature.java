package org.eclipse.bpmn2.modeler.core.features;

import org.eclipse.bpmn2.Association;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Conversation;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.Task;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractCopyFeature;

public class CopyBaseElementFeature extends AbstractCopyFeature {

	 

    public CopyBaseElementFeature(IFeatureProvider fp) {

        super(fp);

    }

 

    public boolean canCopy(ICopyContext context) {

        final PictogramElement[] pes = context.getPictogramElements();

        if (pes == null || pes.length == 0 || pes.length >1) {  // nothing selected

            return false;

        }

        

        // return true, if all selected elements are a EClasses

        for (PictogramElement pe : pes) {

            final Object bo = getBusinessObjectForPictogramElement(pe);

           /* if (!(bo instanceof BaseElement)) {

                return false;

            }
            if(isConnection(bo)) {
            	
            	return false;
            	
            }

        return true;*/
            if(bo instanceof Task) {
            	return true;
            }
            if(bo instanceof StartEvent || bo instanceof EndEvent) {
            	return true;
            }
        }
		return false;
    }

 

    public void copy(ICopyContext context) {

        // get the business-objects for all pictogram-elements

        // we already verified, that all business-objets are EClasses

        PictogramElement[] pes = context.getPictogramElements();

        Object[] bos = new Object[pes.length];

        for (int i = 0; i < pes.length; i++) {

            PictogramElement pe = pes[i];

            bos[i] = getBusinessObjectForPictogramElement(pe);

        }

        // put all business objects to the clipboard

        putToClipboard(bos);

    }
    
    public boolean isConnection(Object object) {
		return
				object instanceof SequenceFlow ||
				object instanceof Association ||
				object instanceof MessageFlow ||
				object instanceof DataInputAssociation ||
				object instanceof DataOutputAssociation ||
				object instanceof Conversation;
	}

}

