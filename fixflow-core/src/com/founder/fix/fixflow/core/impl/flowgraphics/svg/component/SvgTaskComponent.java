/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.flowgraphics.svg.component;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;


import com.founder.fix.fixflow.core.impl.flowgraphics.svg.FlowSvgUtil;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.SvgBench;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.LoopType;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgBaseTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgTaskTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgTaskTo.TaskType;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;

public class SvgTaskComponent implements ISvgComponent {

	private static String comPath = "/svgcomponent/task.xml";
	
	private static String none ="none";
	
	private static String text_x="{text_x}";
	
	private static String text_y="{text_y}";
	
	private static String loop_x ="{loop_x}";
	
	private static String loop_y ="{loop_y}";
	
	private static String task_bus ="{task_bus}";
	
	private static String task_scr ="{task_scr}";
	
	private static String task_usr ="{task_usr}";
	
	private static String task_sev ="{task_sev}";
	
	private static String task_send ="{task_send}";
	
	private static String task_manual ="{task_manual}";
	
	private static String task_receive="{task_receive}";
	
	private static String loop_std ="{loop_std}";
	
	private static String loop_par ="{loop_par}";
	
	private static String loop_seq ="{loop_seq}";
	
	
	public String createComponent(SvgBaseTo svgTo) {
		String result = null;
		try {
			SvgTaskTo taskTo = (SvgTaskTo)svgTo;
			InputStream in = SvgBench.class.getResourceAsStream(comPath);
			Document doc = XmlUtil.read(in);
			String str = doc.getRootElement().asXML();
			String taskBus = none;
			String taskScr = none;
			String taskUsr = none;
			String taskSev = none;
			String taskMeu = none;
			String taskSed = none;
			String taskRec = none;
			
			String loopStd = none;
			String loopPar = none;
			String loopSeq = none;
			
			str = FlowSvgUtil.replaceAll(str, local_x, StringUtil.getString(taskTo.getX()-2));
			str = FlowSvgUtil.replaceAll(str, local_y, StringUtil.getString(taskTo.getY()-2));
			str = FlowSvgUtil.replaceAll(str, id, taskTo.getId());
			str = FlowSvgUtil.replaceAll(str, text, taskTo.getLabel());
			str = FlowSvgUtil.replaceAll(str, text_x, StringUtil.getString(taskTo.getWidth()/2));
			str = FlowSvgUtil.replaceAll(str, text_y, StringUtil.getString(taskTo.getHeight()/2));
			str = FlowSvgUtil.replaceAll(str, width, StringUtil.getString(taskTo.getWidth()));
			str = FlowSvgUtil.replaceAll(str, hight, StringUtil.getString(taskTo.getHeight()));
			str = FlowSvgUtil.replaceAll(str, loop_x, StringUtil.getString(taskTo.getWidth()/2-48));
			str = FlowSvgUtil.replaceAll(str, loop_y, StringUtil.getString(taskTo.getHeight()-80));
			
			if(taskTo.getTaskType().equals(TaskType.BusinessRuleTask)){
				taskBus = "";
			}else if(taskTo.getTaskType().equals(TaskType.ScriptTask)){
				taskScr = "";
			}else if(taskTo.getTaskType().equals(TaskType.UserTask)){
				taskUsr = "";
			}else if(taskTo.getTaskType().equals(TaskType.ServiceTask)){
				taskSev = "";
			}else if(taskTo.getTaskType().equals(TaskType.ManualTask)){
				taskMeu = "";
			}else if(taskTo.getTaskType().equals(TaskType.SendTask)){
				taskSed = "";
			}else if(taskTo.getTaskType().equals(TaskType.ReceiveTask)){
				taskRec = "";
			}
			
			if(taskTo.getLoopType().equals(LoopType.StandardLoop)){
				loopStd = "";
			}else if(taskTo.getLoopType().equals(LoopType.MultiInstanceLoopParallel)){
				loopPar = "";
			}else if(taskTo.getLoopType().equals(LoopType.MultiInstanceLoopSequential)){
				loopSeq = "";
			}
			
			str = FlowSvgUtil.replaceAll(str, task_bus, taskBus);
			str = FlowSvgUtil.replaceAll(str, task_scr, taskScr);
			str = FlowSvgUtil.replaceAll(str, task_usr, taskUsr);
			str = FlowSvgUtil.replaceAll(str, task_sev, taskSev);
			str = FlowSvgUtil.replaceAll(str, task_send, taskSed);
			str = FlowSvgUtil.replaceAll(str, task_manual, taskMeu);
			str = FlowSvgUtil.replaceAll(str, task_receive, taskRec);
			str = FlowSvgUtil.replaceAll(str, loop_std, loopStd);
			str = FlowSvgUtil.replaceAll(str, loop_par, loopPar);
			str = FlowSvgUtil.replaceAll(str, loop_seq, loopSeq);
			
			result = str;
		} catch (DocumentException e) {
			throw new FixFlowException("",e);
		}
		
		return result;
	}

}
