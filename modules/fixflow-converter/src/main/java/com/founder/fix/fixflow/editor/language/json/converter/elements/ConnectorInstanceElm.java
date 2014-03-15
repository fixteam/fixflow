package com.founder.fix.fixflow.editor.language.json.converter.elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.impl.ActivityImpl;
import org.eclipse.bpmn2.impl.BaseElementImpl;

import com.founder.fix.bpmn2extensions.fixflow.ConnectorInstance;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterInputs;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputs;
import com.founder.fix.bpmn2extensions.fixflow.ConnectorParameterOutputsDef;
import com.founder.fix.bpmn2extensions.fixflow.Documentation;
import com.founder.fix.bpmn2extensions.fixflow.Expression;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowFactory;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.fixflow.SkipComment;
import com.founder.fix.bpmn2extensions.fixflow.TimeExpression;
import com.founder.fix.bpmn2extensions.fixflow.TimeSkipExpression;
import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;
import com.founder.fix.fixflow.editor.constants.EditorJsonConstants;
import com.founder.fix.fixflow.editor.constants.StencilConstants;
import com.founder.fix.fixflow.editor.language.json.converter.util.JsonConverterUtil;

public class ConnectorInstanceElm implements EditorJsonConstants, StencilConstants{
	
	public ConnectorInstanceElm(){
		
	}
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public ObjectNode convertElementToJson(BaseElementImpl baseElm){
		  List<ConnectorInstance> connectorInstances = baseElm.getConnectorInstances();
	      if(connectorInstances != null){
	    	  ObjectNode connectorInstanceNode = objectMapper.createObjectNode();
	    	  ArrayNode itemsNode = objectMapper.createArrayNode();
	    	  for(ConnectorInstance connectorInstance: connectorInstances){
	    		  ObjectNode connectorInstanceItemNode = objectMapper.createObjectNode();
	    		  connectorInstanceItemNode.put(PROPERTY_CONNECTORINSTANCE_CONNECTORID, connectorInstance.getConnectorId());
	    		  connectorInstanceItemNode.put(PROPERTY_CONNECTORINSTANCE_PACKAGENAME, connectorInstance.getPackageName());
	    		  connectorInstanceItemNode.put(PROPERTY_CONNECTORINSTANCE_CLASSNAME, connectorInstance.getClassName());
	    		  connectorInstanceItemNode.put(PROPERTY_CONNECTORINSTANCE_CONNECTORINSTANCEID, connectorInstance.getConnectorInstanceId());
	    		  connectorInstanceItemNode.put(PROPERTY_CONNECTORINSTANCE_CONNECTORINSTANCENAME, connectorInstance.getConnectorInstanceName());
	    		  connectorInstanceItemNode.put(PROPERTY_CONNECTORINSTANCE_EVENTTYPE, connectorInstance.getEventType());
	    		  connectorInstanceItemNode.put(PROPERTY_CONNECTORINSTANCE_ERRORHANDLING, connectorInstance.getErrorHandling());
	    		  connectorInstanceItemNode.put(PROPERTY_CONNECTORINSTANCE_ERRORCODE, connectorInstance.getErrorCode());
	    		  connectorInstanceItemNode.put(PROPERTY_CONNECTORINSTANCE_ISTIMEEXECUTE, connectorInstance.isIsTimeExecute());
	    		  
	    		  Documentation documentation = connectorInstance.getDocumentation();
	    		  if(documentation != null){
	    			  ObjectNode documentationItemNode = objectMapper.createObjectNode();
	    			  documentationItemNode.put(PROPERTY_DOCUMENTATION_ID, documentation.getId());
	    			  documentationItemNode.put(PROPERTY_DOCUMENTATION_NAME, documentation.getName());
	    			  documentationItemNode.put(PROPERTY_DOCUMENTATION_VALUE, documentation.getValue());
	    			  
	    			  connectorInstanceItemNode.put(PROPERTY_DOCUMENTATION, documentationItemNode);
	    		  }
	    		  
	    		  List<ConnectorParameterInputs> connectorParameterInputs = connectorInstance.getConnectorParameterInputs();
	    		  if(connectorParameterInputs != null){
	    	    	  ArrayNode parameterItemsNode = objectMapper.createArrayNode();
	    	    	  for(ConnectorParameterInputs connectorParameterInput: connectorParameterInputs){
	    	    		  ObjectNode connectorParameterInputItemNode = objectMapper.createObjectNode();
	    	    		  connectorParameterInputItemNode.put(PROPERTY_CONNECTORPARAMETERINPUTS_ID, connectorParameterInput.getId());
	    	    		  connectorParameterInputItemNode.put(PROPERTY_CONNECTORPARAMETERINPUTS_NAME, connectorParameterInput.getName());
	    	    		  connectorParameterInputItemNode.put(PROPERTY_CONNECTORPARAMETERINPUTS_DATATYPE, connectorParameterInput.getDataType());
	    	    		  connectorParameterInputItemNode.put(PROPERTY_CONNECTORPARAMETERINPUTS_EXPRESSION_ID, connectorParameterInput.getExpression().getId());
	    	    		  connectorParameterInputItemNode.put(PROPERTY_CONNECTORPARAMETERINPUTS_EXPRESSION_NAME, connectorParameterInput.getExpression().getName());
	    	    		  connectorParameterInputItemNode.put(PROPERTY_CONNECTORPARAMETERINPUTS_EXPRESSION_VALUE, connectorParameterInput.getExpression().getValue());
	    	    		  
	    	    		  parameterItemsNode.add(connectorParameterInputItemNode);
	    	    	  }
	    	    	  connectorInstanceItemNode.put(PROPERTY_CONNECTORPARAMETERINPUTS, parameterItemsNode);
	    		  }
	    		  
	    		  List<ConnectorParameterOutputs> connectorParameterOutputs = connectorInstance.getConnectorParameterOutputs();
	    		  if(connectorParameterOutputs != null){
	    	    	  ArrayNode parameterItemsNode = objectMapper.createArrayNode();
	    	    	  for(ConnectorParameterOutputs connectorParameterOutput: connectorParameterOutputs){
	    	    		  ObjectNode connectorParameterInputItemNode = objectMapper.createObjectNode();
	    	    		  connectorParameterInputItemNode.put(PROPERTY_CONNECTORPARAMETEROUTPUTS_VARIABLETARGET, connectorParameterOutput.getVariableTarget());
	    	    		  connectorParameterInputItemNode.put(PROPERTY_CONNECTORPARAMETEROUTPUTS_EXPRESSION_ID, connectorParameterOutput.getExpression().getId());
	    	    		  connectorParameterInputItemNode.put(PROPERTY_CONNECTORPARAMETEROUTPUTS_EXPRESSION_NAME, connectorParameterOutput.getExpression().getName());
	    	    		  connectorParameterInputItemNode.put(PROPERTY_CONNECTORPARAMETEROUTPUTS_EXPRESSION_VALUE, connectorParameterOutput.getExpression().getValue());
	    	    		  
	    	    		  parameterItemsNode.add(connectorParameterInputItemNode);
	    	    	  }
	    	    	  connectorInstanceItemNode.put(PROPERTY_CONNECTORPARAMETEROUTPUTS, parameterItemsNode);
	    		  }
	    		  
	    		  List<ConnectorParameterOutputsDef> connectorParameterOutputsDef = connectorInstance.getConnectorParameterOutputsDef();
	    		  if(connectorParameterOutputsDef != null){
	    	    	  ArrayNode parameterItemsNode = objectMapper.createArrayNode();
	    	    	  for(ConnectorParameterOutputsDef cpod: connectorParameterOutputsDef){
	    	    		  ObjectNode cpodItemNode = objectMapper.createObjectNode();
	    	    		  cpodItemNode.put(PROPERTY_CONNECTORPARAMETEROUTPUTSDEF_ID, cpod.getId());
	    	    		  cpodItemNode.put(PROPERTY_CONNECTORPARAMETEROUTPUTSDEF_NAME, cpod.getName());
	    	    		  cpodItemNode.put(PROPERTY_CONNECTORPARAMETEROUTPUTSDEF_DATATYPE, cpod.getDataType());
	    	    		  
	    	    		  parameterItemsNode.add(cpodItemNode);
	    	    	  }
	    	    	  connectorInstanceItemNode.put(PROPERTY_CONNECTORPARAMETEROUTPUTSDEF, parameterItemsNode);
	    		  }
	    		  
	    		  SkipComment skipComment = connectorInstance.getSkipComment();
	    		  if(skipComment !=null){
	    			  ObjectNode ItemNode = objectMapper.createObjectNode();
	    			  ItemNode.put(PROPERTY_SKIPCOMMENT_EXPRESSION_ID, skipComment.getExpression().getId());
	    			  ItemNode.put(PROPERTY_SKIPCOMMENT_EXPRESSION_NAME, skipComment.getExpression().getName());
	    			  ItemNode.put(PROPERTY_SKIPCOMMENT_EXPRESSION_VALUE, skipComment.getExpression().getValue());
	    			  
	    			  connectorInstanceItemNode.put(PROPERTY_SKIPCOMMENT, ItemNode);
	    		  }
	    		  
	    		  
	    		  TimeExpression timeExpression = connectorInstance.getTimeExpression();
	    		  if(timeExpression != null){
	    			  ObjectNode ItemNode = objectMapper.createObjectNode();
	    			  ItemNode.put(PROPERTY_TIME_EXPRESSION_ID, timeExpression.getExpression().getId());
	    			  ItemNode.put(PROPERTY_TIME_EXPRESSION_NAME, timeExpression.getExpression().getName());
	    			  ItemNode.put(PROPERTY_TIME_EXPRESSION_VALUE, timeExpression.getExpression().getValue());
	    			  connectorInstanceItemNode.put(PROPERTY_TIME_EXPRESSION, ItemNode);
	    		  }
	    		  
	    		  TimeSkipExpression timeSkipExpression = connectorInstance.getTimeSkipExpression();
	    		  if(timeSkipExpression != null){
	    			  ObjectNode ItemNode = objectMapper.createObjectNode();
	    			  ItemNode.put(PROPERTY_TIME_SKIP_EXPRESSION_ID, timeSkipExpression.getExpression().getId());
	    			  ItemNode.put(PROPERTY_TIME_SKIP_EXPRESSION_NAME, timeSkipExpression.getExpression().getName());
	    			  ItemNode.put(PROPERTY_TIME_SKIP_EXPRESSION_VALUE, timeSkipExpression.getExpression().getValue());
	    			  connectorInstanceItemNode.put(PROPERTY_TIME_SKIP_EXPRESSION, ItemNode);
	    		  }
	    		  
	    		  
	    		  itemsNode.add(connectorInstanceItemNode);
	    		  
	    	  }
	    	  connectorInstanceNode.put("totalCount", itemsNode.size());
	    	  connectorInstanceNode.put("items", itemsNode);
	          //propertiesNode.put(PROPERTY_CONNECTORINSTANCE, connectorInstanceNode);
	    	  return connectorInstanceNode;
	      }
	      return objectMapper.createObjectNode();
	}
	
	public List<ConnectorInstance> convertJsonToElement(JsonNode elementNode) {
		JsonNode connectorInstanceNode = JsonConverterUtil.getProperty(PROPERTY_CONNECTORINSTANCE, elementNode);
		if(connectorInstanceNode !=null){
			
			ObjectMapper objectMapper = new ObjectMapper();

			JsonNode objectConnectorNode = null;
			try {
				objectConnectorNode = objectMapper.readTree(connectorInstanceNode.getTextValue());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	JsonNode itemsNode = objectConnectorNode.get("items");
	    	  
			if(itemsNode != null) {
				
				List<ConnectorInstance> listActivity = new ArrayList<ConnectorInstance>();
				
				Iterator<JsonNode> connectorInstanceIterator = itemsNode.getElements();
				while (connectorInstanceIterator.hasNext()) {
					  JsonNode connectorInstanceItemNode = connectorInstanceIterator.next();
					  
					  String connectorId = connectorInstanceItemNode.get(PROPERTY_CONNECTORINSTANCE_CONNECTORID).asText();
					  String packageName = connectorInstanceItemNode.get(PROPERTY_CONNECTORINSTANCE_PACKAGENAME).asText();
					  String className = connectorInstanceItemNode.get(PROPERTY_CONNECTORINSTANCE_CLASSNAME).asText();
					  String connectorInstanceId = connectorInstanceItemNode.get(PROPERTY_CONNECTORINSTANCE_CONNECTORINSTANCEID).asText();
					  String connectorInstanceName = connectorInstanceItemNode.get(PROPERTY_CONNECTORINSTANCE_CONNECTORINSTANCENAME).asText();
					  String eventType = connectorInstanceItemNode.get(PROPERTY_CONNECTORINSTANCE_EVENTTYPE).asText();
					  String errorHandling = connectorInstanceItemNode.get(PROPERTY_CONNECTORINSTANCE_ERRORHANDLING).asText();
					  String errorCode = connectorInstanceItemNode.get(PROPERTY_CONNECTORINSTANCE_ERRORCODE).asText();
					  Boolean isTimeExecute = connectorInstanceItemNode.get(PROPERTY_CONNECTORINSTANCE_ISTIMEEXECUTE).asBoolean(false);
					  
					  ConnectorInstance ConnectorInstance = FixFlowFactory.eINSTANCE.createConnectorInstance();
					  ConnectorInstance.setConnectorId(connectorId);
					  ConnectorInstance.setPackageName(packageName);
					  ConnectorInstance.setClassName(className);
					  ConnectorInstance.setConnectorInstanceId(connectorInstanceId);
					  ConnectorInstance.setConnectorInstanceName(connectorInstanceName);
					  ConnectorInstance.setEventType(eventType);
					  ConnectorInstance.setErrorHandling(errorHandling);
					  ConnectorInstance.setErrorCode(errorCode);
					  ConnectorInstance.setIsTimeExecute(isTimeExecute);
					  
					  JsonNode documentNode = connectorInstanceItemNode.get(PROPERTY_DOCUMENTATION);
				      if(documentNode !=null){
				    	  
				    	  String id = documentNode.get(PROPERTY_DOCUMENTATION_ID).asText();
				    	  String name = documentNode.get(PROPERTY_DOCUMENTATION_NAME).asText();
				    	  String value = documentNode.get(PROPERTY_DOCUMENTATION_VALUE).asText();
				    	  
				    	  Documentation documentationElm = FixFlowFactory.eINSTANCE.createDocumentation();
				    	  documentationElm.setId(id);
				    	  documentationElm.setName(name);
				    	  documentationElm.setValue(value);
				    	  
				    	  ConnectorInstance.setDocumentation(documentationElm);
				      }
				      
				      
				      JsonNode connectorParameterInputsNode = connectorInstanceItemNode.get(PROPERTY_CONNECTORPARAMETERINPUTS);
				      if(connectorParameterInputsNode !=null){
				    	  
						  Iterator<JsonNode> connectorParameterInputsIterator = connectorParameterInputsNode.getElements();
						  while (connectorParameterInputsIterator.hasNext()) {
							  JsonNode connectorParameterInputsItemNode = connectorParameterInputsIterator.next();
							  String id = connectorParameterInputsItemNode.get(PROPERTY_CONNECTORPARAMETERINPUTS_ID).asText();
					    	  String name = connectorParameterInputsItemNode.get(PROPERTY_CONNECTORPARAMETERINPUTS_NAME).asText();
					    	  String dataType = connectorParameterInputsItemNode.get(PROPERTY_CONNECTORPARAMETERINPUTS_DATATYPE).asText();
					    	  String expression_name = connectorParameterInputsItemNode.get(PROPERTY_CONNECTORPARAMETERINPUTS_EXPRESSION_NAME).asText();
					    	  String expression_value = connectorParameterInputsItemNode.get(PROPERTY_CONNECTORPARAMETERINPUTS_EXPRESSION_VALUE).asText();
							  
							  ConnectorParameterInputs connectorParameterInputsElm = FixFlowFactory.eINSTANCE.createConnectorParameterInputs();
							  connectorParameterInputsElm.setId(id);
							  connectorParameterInputsElm.setName(name);
							  connectorParameterInputsElm.setDataType(dataType);

							  Expression expression = FixFlowFactory.eINSTANCE.createExpression();
							  expression.setName(expression_name);
							  expression.setValue(expression_value);
							  connectorParameterInputsElm.setExpression(expression);
							  
							  ConnectorInstance.getConnectorParameterInputs().add(connectorParameterInputsElm);
						  }
						 
				      }
				      
				      
				      JsonNode connectorParameterOutputsNode = connectorInstanceItemNode.get(PROPERTY_CONNECTORPARAMETEROUTPUTS);
				      if(connectorParameterOutputsNode !=null){
				    	  
						  Iterator<JsonNode> connectorParameterOutputsIterator = connectorParameterOutputsNode.getElements();
						  while (connectorParameterOutputsIterator.hasNext()) {
							  JsonNode connectorParameterOutputsItemNode = connectorParameterOutputsIterator.next();
							  String variableTarget = connectorParameterOutputsItemNode.get(PROPERTY_CONNECTORPARAMETEROUTPUTS_VARIABLETARGET).asText();
					    	  String expression_name = connectorParameterOutputsItemNode.get(PROPERTY_CONNECTORPARAMETEROUTPUTS_EXPRESSION_NAME).asText();
					    	  String expression_value = connectorParameterOutputsItemNode.get(PROPERTY_CONNECTORPARAMETEROUTPUTS_EXPRESSION_VALUE).asText();
							  
					    	  ConnectorParameterOutputs connectorParameterOutputsElm = FixFlowFactory.eINSTANCE.createConnectorParameterOutputs();
					    	  connectorParameterOutputsElm.setVariableTarget(variableTarget);

							  Expression expression = FixFlowFactory.eINSTANCE.createExpression();
							  expression.setName(expression_name);
							  expression.setValue(expression_value);
							  connectorParameterOutputsElm.setExpression(expression);
							  
							  ConnectorInstance.getConnectorParameterOutputs().add(connectorParameterOutputsElm);
						  }
						 
				      }
				      
				      
				      JsonNode connectorParameterOutputsDefNode = connectorInstanceItemNode.get(PROPERTY_CONNECTORPARAMETEROUTPUTSDEF);
				      if(connectorParameterOutputsDefNode !=null){
				    	  
						  Iterator<JsonNode> connectorParameterOutputsDefIterator = connectorParameterOutputsDefNode.getElements();
						  while (connectorParameterOutputsDefIterator.hasNext()) {
							  JsonNode connectorParameterOutputsDefItemNode = connectorParameterOutputsDefIterator.next();
							  String id = connectorParameterOutputsDefItemNode.get(PROPERTY_CONNECTORPARAMETEROUTPUTSDEF_ID).asText();
					    	  String name = connectorParameterOutputsDefItemNode.get(PROPERTY_CONNECTORPARAMETEROUTPUTSDEF_NAME).asText();
					    	  String dataType = connectorParameterOutputsDefItemNode.get(PROPERTY_CONNECTORPARAMETEROUTPUTSDEF_DATATYPE).asText();
							  
					    	  ConnectorParameterOutputsDef connectorParameterOutputsDefElm = FixFlowFactory.eINSTANCE.createConnectorParameterOutputsDef();
					    	  connectorParameterOutputsDefElm.setId(id);
					    	  connectorParameterOutputsDefElm.setName(name);
					    	  connectorParameterOutputsDefElm.setDataType(dataType);

							  ConnectorInstance.getConnectorParameterOutputsDef().add(connectorParameterOutputsDefElm);
						  }
				      }
				      
				      
				      JsonNode skipCommentNode = connectorInstanceItemNode.get(PROPERTY_SKIPCOMMENT);
				      if(skipCommentNode !=null){
				    	  
				    	  String id = skipCommentNode.get(PROPERTY_SKIPCOMMENT_EXPRESSION_ID).asText();
				    	  String name = skipCommentNode.get(PROPERTY_SKIPCOMMENT_EXPRESSION_NAME).asText();
				    	  String value = skipCommentNode.get(PROPERTY_SKIPCOMMENT_EXPRESSION_VALUE).asText();
				    	  
				    	  Expression expression = FixFlowFactory.eINSTANCE.createExpression();
				    	  expression.setId(id);
				    	  expression.setName(name);
						  expression.setValue(value);
						  
						  SkipComment skipCommentElm = FixFlowFactory.eINSTANCE.createSkipComment();
						  skipCommentElm.setExpression(expression);

				    	  ConnectorInstance.setSkipComment(skipCommentElm);
				      }
				      
				      
				      JsonNode timeExpressionNode = connectorInstanceItemNode.get(PROPERTY_TIME_EXPRESSION);
				      if(timeExpressionNode !=null){
				    	  
				    	  String id = timeExpressionNode.get(PROPERTY_TIME_EXPRESSION_ID).asText();
				    	  String name = timeExpressionNode.get(PROPERTY_TIME_EXPRESSION_NAME).asText();
				    	  String value = timeExpressionNode.get(PROPERTY_TIME_EXPRESSION_VALUE).asText();
				    	  
				    	  Expression expression = FixFlowFactory.eINSTANCE.createExpression();
				    	  expression.setId(id);
				    	  expression.setName(name);
						  expression.setValue(value);
						  
						  TimeExpression timeExpressionElm = FixFlowFactory.eINSTANCE.createTimeExpression();
						  timeExpressionElm.setExpression(expression);

				    	  ConnectorInstance.setTimeExpression(timeExpressionElm);
				      }
				      
				      
				      JsonNode timeSkipExpressionNode = connectorInstanceItemNode.get(PROPERTY_TIME_SKIP_EXPRESSION);
				      if(timeSkipExpressionNode !=null){
				    	  
				    	  String id = timeSkipExpressionNode.get(PROPERTY_TIME_SKIP_EXPRESSION_ID).asText();
				    	  String name = timeSkipExpressionNode.get(PROPERTY_TIME_SKIP_EXPRESSION_NAME).asText();
				    	  String value = timeSkipExpressionNode.get(PROPERTY_TIME_SKIP_EXPRESSION_VALUE).asText();
				    	  
				    	  Expression expression = FixFlowFactory.eINSTANCE.createExpression();
				    	  expression.setId(id);
				    	  expression.setName(name);
						  expression.setValue(value);
						  
						  TimeSkipExpression timeSkipExpressionElm = FixFlowFactory.eINSTANCE.createTimeSkipExpression();
						  timeSkipExpressionElm.setExpression(expression);

				    	  ConnectorInstance.setTimeSkipExpression(timeSkipExpressionElm);
				      }
				      
				      //BpmnModelUtil.addExtensionElement(activity, FixFlowPackage.Literals.DOCUMENT_ROOT__CONNECTOR_INSTANCE, ConnectorInstance);
				      listActivity.add(ConnectorInstance);
				}
				  
				return listActivity;
			}
	    }
		return null;
	}
}
