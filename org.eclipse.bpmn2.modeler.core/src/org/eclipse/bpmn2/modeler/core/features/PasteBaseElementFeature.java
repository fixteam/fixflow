package org.eclipse.bpmn2.modeler.core.features;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.utils.BpmnObjUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.AbstractPasteFeature;

public class PasteBaseElementFeature extends AbstractPasteFeature {

	 

    public PasteBaseElementFeature(IFeatureProvider fp) {

        super(fp);

    }

 

    public boolean canPaste(IPasteContext context) {

        // only support pasting directly in the diagram (nothing else selected)

        PictogramElement[] pes = context.getPictogramElements();

        if (pes.length != 1 || !(pes[0] instanceof Diagram)) {

            return false;

        }

 

        // can paste, if all objects on the clipboard are EClasses

        Object[] fromClipboard = getFromClipboard();

        if (fromClipboard == null || fromClipboard.length == 0) {

            return false;

        }

        for (Object object : fromClipboard) {

            if (!(object instanceof BaseElement)) {

                return false;

            }

        }

        return true;

    }

 

    public void paste(IPasteContext context) {

        // we already verified, that we paste directly in the diagram

        PictogramElement[] pes = context.getPictogramElements();

        Diagram diagram = (Diagram) pes[0];
        
        // get the EClasses from the clipboard without copying them

        // (only copy the pictogram element, not the business object)

        // then create new pictogram elements using the add feature

        Object[] objects = getFromClipboard();

        for (Object object : objects) {

            AddContext ac = new AddContext();
            
            int x = getDiagramEditor().getCurrentMouseLocation().getX()-70;
            int y = getDiagramEditor().getCurrentMouseLocation().getY()-50;
            
          /*  EStructuralFeature feature = ((EObject)object).eClass().getEStructuralFeature("id");
            ((EObject)object).eSet(feature, "Copy_of_" +  ((EObject)object).eGet(feature).toString());*/
            
            
            ac.setLocation(x+100, y+100); // for simplicity paste at (0, 0)

            ac.setTargetContainer(diagram);
            
            Process process = BpmnObjUtil.getProcess((EObject) object);
            
            EObject result = EcoreUtil.copy((EObject)object);
            
            EStructuralFeature feature = ((EObject)object).eClass().getEStructuralFeature("id");
            
            result.eSet(feature, null);
            
            ModelUtil.setID(result, process.eResource());

            process.getFlowElements().add((FlowElement) result);

            addGraphicalRepresentation(ac, (FlowElement) result);

        }

    }

}

