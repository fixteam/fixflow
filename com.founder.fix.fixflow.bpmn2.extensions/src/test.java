import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.bpmn2extensions.connector.ConnectorFactory;
import com.founder.fix.bpmn2extensions.connector.ConnectorPackage;



public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	

		
		
		// Initialize the model
		ConnectorPackage.eINSTANCE.eClass();
		// Retrieve the default factory singleton
		ConnectorFactory factory = ConnectorFactory.eINSTANCE;

		// Create the content of the model via this program
		Connector myWeb = factory.createConnector();
		myWeb.setConnectorId("dd");
		
		/*Parameter parameter=factory.createParameter();
		parameter.setId("ddd");
		parameter.setName("adasd");
		
		Inputs Inputs=factory.createInputs();
		Inputs.getParameter().add(parameter);
		myWeb.setInputs(Inputs);*/
		
		

		// As of here we preparing to save the model content

		// Register the XMI resource factory for the .website extension

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xml", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Create a resource
		Resource resource = resSet.createResource(URI
				.createURI("website/My2.xml"));
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		resource.getContents().add(myWeb);

		// Now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	public Connector load() {
		// Initialize the model
		ConnectorPackage.eINSTANCE.eClass();
		
		// Register the XMI resource factory for the .website extension

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xml", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Get the resource
		Resource resource = resSet.getResource(URI
				.createURI("website/My2.xml"), true);
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		Connector myWeb = (Connector) resource.getContents().get(0);
		return myWeb;
	}
}
