package com.founder.fix.fixflow.designer.modeler.ui.property.connectors;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.bpmn2extensions.connector.InputParameter;
import com.founder.fix.bpmn2extensions.connector.OutputParameter;
import com.founder.fix.bpmn2extensions.connector.Page;
import com.founder.fix.fixflow.designer.util.CreateJavaClassUtil;

public class CreateConnectorJava {

	public static InputStream CreateConnectorJavaClassReturnInputStream(Connector connector) {

		

		InputStream inputStream=(InputStream)(new ByteArrayInputStream(CreateConnectorJavaClassReturnString(connector).getBytes()));

		return inputStream;
	}
	
	public static String CreateConnectorJavaClassReturnString(Connector connector)
	{
		CreateJavaClassUtil createJavaClassUtil = new CreateJavaClassUtil();

		for (Page page : connector.getPages().getPage()) {

			for (InputParameter inputParameter : page.getInputParameter()) {
				createJavaClassUtil.createInputCode(inputParameter.getDataType(), inputParameter.getId());
			}
		}

		for (OutputParameter outputParameter : connector.getOutputs().getOutputParameter()) {
			createJavaClassUtil.createOutputCode(outputParameter.getDataType(), outputParameter.getId());
		}

		createJavaClassUtil.setClassName(connector.getClassName());
		createJavaClassUtil.createExecuteConnector();
		createJavaClassUtil.setPackageName(connector.getPackageName());
		createJavaClassUtil.createImport("com.founder.fix.fixflow.core.runtime.ExecutionContext;");
		createJavaClassUtil.createImport("com.founder.fix.fixflow.core.action.ConnectorHandler;");

		String javaCodeString=createJavaClassUtil.generateJavaCode();
		return javaCodeString;
	}
	
	public static byte[] CreateConnectorJavaClassReturnBytes(Connector connector)
	{
		return CreateConnectorJavaClassReturnString(connector).getBytes();
	}
	
	
	
	
}
