package com.founder.fix.fixflow.core.impl.flowgraphics.svg.component;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.FlowSvgUtil;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.SvgBench;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgBaseTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgLaneTo;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;

public class SvgLaneComponent implements ISvgComponent {

	private static String comPath = "/svgcomponent/lane.xml";
	
	@SuppressWarnings("unused")
	private static String text_x="{text_x}";
	
	private static String text_y="{text_y}";
	
	public String createComponent(SvgBaseTo svgTo) {
		String result = null;
		try {
			SvgLaneTo lane = (SvgLaneTo)svgTo;
			
			if(lane.isHorizontal()){
				comPath = "/svgcomponent/lane.xml";
			}
			else {
				comPath = "/svgcomponent/lane_h.xml";
			}
			InputStream in = SvgBench.class.getResourceAsStream(comPath);
			Document doc = XmlUtil.read(in);
			String str = doc.getRootElement().asXML();
			str = FlowSvgUtil.replaceAll(str, local_x, StringUtil.getString(lane.getX()));
			str = FlowSvgUtil.replaceAll(str, local_y, StringUtil.getString(lane.getY()));
			str = FlowSvgUtil.replaceAll(str, id, lane.getId());
			str = FlowSvgUtil.replaceAll(str, text, lane.getLabel());
			str = FlowSvgUtil.replaceAll(str, width, StringUtil.getString(lane.getWidth()));
			str = FlowSvgUtil.replaceAll(str, hight, StringUtil.getString(lane.getHeight()));
			if(lane.isHorizontal()){
				str = FlowSvgUtil.replaceAll(str, text_y, StringUtil.getString(lane.getHeight()/2));
			}
			else {
				str = FlowSvgUtil.replaceAll(str, text_x, StringUtil.getString(lane.getWidth()/2));
			}
			
			result = str;
		} catch (DocumentException e) {
			throw new FixFlowException("",e);
		}
		return result;
	}

}
