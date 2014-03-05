
import java.io.IOException;
import java.util.List;

import org.dom4j.DocumentException;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;

import org.eclipse.bpmn2.StartEvent;

import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.di.impl.BPMNShapeImpl;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.DefinitionsBehavior;


public class test {

	/**
	 * @param args
	 * @throws IOException
	 * @throws DocumentException11
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException, DocumentException {
		// TODO Auto-generated method stub
		Bpmn2ResourceFactoryImpl ddd = new Bpmn2ResourceFactoryImpl();
	
		Resource ddddResource = ddd.createResource(URI.createFileURI("/Users/jiangnan/Documents/MyCode/irondino/fixlogic/app/modules/lhl002/bpmn/testflow.bpmn"));

		ddddResource.load( null);
		Definitions myWeb = (Definitions) ddddResource.getContents().get(0).eContents().get(0);
		Object dddObject = myWeb;

		List<BPMNDiagram> BPMNDiagramList = myWeb.getDiagrams();
		float maxX=0;
		float maxY=0;
		for (BPMNDiagram bpmnDiagram : BPMNDiagramList) {
			for (DiagramElement diagramElement : bpmnDiagram.getPlane().getPlaneElement()) {

				if (diagramElement instanceof BPMNShape) {
					BPMNShapeImpl bpmnShape = (BPMNShapeImpl) diagramElement;
					org.eclipse.emf.ecore.impl.BasicEObjectImpl sssBaseElementImpl=(org.eclipse.emf.ecore.impl.BasicEObjectImpl)bpmnShape.getBpmnElement();
					
					String idString=sssBaseElementImpl.eProxyURI().fragment();
					BaseElement dd=((DefinitionsBehavior)myWeb).getElement(idString);
					
					if (bpmnShape.getBounds().getX() + bpmnShape.getBounds().getWidth() > maxX) {
						maxX = bpmnShape.getBounds().getX() + bpmnShape.getBounds().getWidth();

					}
					if (bpmnShape.getBounds().getY() + bpmnShape.getBounds().getHeight() > maxY) {
						maxY = bpmnShape.getBounds().getY() + bpmnShape.getBounds().getHeight();

					}

					if (dd instanceof StartEvent) {
						
						String jiangnString="";

					}
					if (dd instanceof EndEvent) {
						String jiangnString="";

					}

					if (dd instanceof Task) {

						String jiangnString="";

					}

					

				}
			}
		}

	}

}
