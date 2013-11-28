package org.activiti.editor.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.SignalEventDefinition;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.UserTask;
import org.junit.Test;

import com.founder.fix.fixflow.bpmn.converter.FixFlowConverter;
import com.founder.fix.fixflow.core.impl.util.BpmnModelUtil;

public class CompleteConverterTest extends AbstractConverterTest {
  
  @Test
  public void connvertJsonToModel() throws Exception {
    Definitions bpmnModel = readJsonFile();
    new FixFlowConverter().save(bpmnModel);
    //validateModel(bpmnModel);
  }
  
//  @Test 
//  public void doubleConversionValidation() throws Exception {
//	  Definitions bpmnModel = readJsonFile();
//    bpmnModel = convertToJsonAndBack(bpmnModel);
//    validateModel(bpmnModel);
//  }
  
  protected String getResource() {
    return "org/activiti/editor/language/test.completemodel.json";
  }
  
  private void validateModel(Definitions model) {
	  
	
    FlowElement flowElement = BpmnModelUtil.getElement(model, "userTask1", FlowElement.class);
    assertNotNull(flowElement);
    assertTrue(flowElement instanceof UserTask);
    assertEquals("userTask1", flowElement.getId());
    
    flowElement = BpmnModelUtil.getElement(model, "catchsignal", FlowElement.class);
    assertNotNull(flowElement);
    assertTrue(flowElement instanceof IntermediateCatchEvent);
    assertEquals("catchsignal", flowElement.getId());
    IntermediateCatchEvent catchEvent = (IntermediateCatchEvent) flowElement;
    assertEquals(1, catchEvent.getEventDefinitions().size());
    assertTrue(catchEvent.getEventDefinitions().get(0) instanceof SignalEventDefinition);
    SignalEventDefinition signalEvent = (SignalEventDefinition) catchEvent.getEventDefinitions().get(0);
    assertEquals("testSignal", signalEvent.getSignalRef());
    
    flowElement = BpmnModelUtil.getElement(model, "subprocess", FlowElement.class);//model.getMainProcess().getFlowElement("subprocess");
    assertNotNull(flowElement);
    assertTrue(flowElement instanceof SubProcess);
    assertEquals("subprocess", flowElement.getId());
    SubProcess subProcess = (SubProcess) flowElement;
    
    flowElement =  BpmnModelUtil.getElement(model, "receiveTask", FlowElement.class);
    assertNotNull(flowElement);
    assertTrue(flowElement instanceof ReceiveTask);
    assertEquals("receiveTask", flowElement.getId());
  }
}
