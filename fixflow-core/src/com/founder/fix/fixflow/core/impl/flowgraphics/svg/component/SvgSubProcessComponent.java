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
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgSubProcessTo;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;

public class SvgSubProcessComponent implements ISvgComponent {

	private static String comPath = "/svgcomponent/subprocess.xml";
	
	private static String none ="none";
	
	private static String text_x="{text_x}";
	
	private static String text_y="{text_y}";
	
	private static String loop_x ="{loop_x}";
	
	private static String loop_y ="{loop_y}";
	
	private static String loop_std ="{loop_std}";
	
	private static String loop_par ="{loop_par}";
	
	private static String loop_seq ="{loop_seq}";
	
	private static String adhoc ="{adhoc}";
	
	private static String callActivity = "{callActivity}";
	
	
	public String createComponent(SvgBaseTo svgTo) {
		String result = null;
		try {
			int loopx = 260;
			int loopy = 281;
			SvgSubProcessTo signavio = (SvgSubProcessTo)svgTo;
			InputStream in = SvgBench.class.getResourceAsStream(comPath);
			Document doc = XmlUtil.read(in);
			String str = doc.getRootElement().asXML();
			
			String loopStd = none;
			String loopPar = none;
			String loopSeq = none;
			
			str = FlowSvgUtil.replaceAll(str, local_x, StringUtil.getString(signavio.getX()-2));
			str = FlowSvgUtil.replaceAll(str, local_y, StringUtil.getString(signavio.getY()-2));
			str = FlowSvgUtil.replaceAll(str, id, signavio.getId());
			str = FlowSvgUtil.replaceAll(str, text, signavio.getLabel());
			str = FlowSvgUtil.replaceAll(str, text_x, StringUtil.getString(signavio.getWidth()/2));
			str = FlowSvgUtil.replaceAll(str, text_y, StringUtil.getString(signavio.getHeight()/2));
			str = FlowSvgUtil.replaceAll(str, width, StringUtil.getString(signavio.getWidth()));
			str = FlowSvgUtil.replaceAll(str, hight, StringUtil.getString(signavio.getHeight()));
			str = FlowSvgUtil.replaceAll(str, loop_x, StringUtil.getString(signavio.getWidth()/2-loopx));
			str = FlowSvgUtil.replaceAll(str, loop_y, StringUtil.getString(signavio.getHeight()-7-loopy));
			str = FlowSvgUtil.replaceAll(str, adhoc, none);
			str = FlowSvgUtil.replaceAll(str, callActivity, none);
			
			if(signavio.getLoopType().equals(LoopType.StandardLoop)){
				loopStd = "";
			}else if(signavio.getLoopType().equals(LoopType.MultiInstanceLoopParallel)){
				loopPar = "";
			}else if(signavio.getLoopType().equals(LoopType.MultiInstanceLoopSequential)){
				loopSeq = "";
			}
			
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
