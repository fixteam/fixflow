package com.founder.fix.fixflow.core.impl.flowgraphics.svg.component;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;


import com.founder.fix.fixflow.core.impl.flowgraphics.svg.FlowSvgUtil;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.SvgBench;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgBaseTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgEndTo;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;

public class SvgEndComponent implements ISvgComponent {

	private static String comPath = "/svgcomponent/end.xml";
	
	private static String text_local="{text_local}";
	
	@SuppressWarnings("unused")
	private static String cricle ="{cricle}";
	
	public String createComponent(SvgBaseTo svgTo) {
		String result = null;
		try {
			SvgEndTo endTo = (SvgEndTo)svgTo;
			InputStream in = SvgBench.class.getResourceAsStream(comPath);
			Document doc = XmlUtil.read(in);
			String str = doc.getRootElement().asXML();
			str = FlowSvgUtil.replaceAll(str, local_x, StringUtil.getString(endTo.getX()));
			str = FlowSvgUtil.replaceAll(str, local_y, StringUtil.getString(endTo.getY()));
			str = FlowSvgUtil.replaceAll(str, id, endTo.getId());
			str = FlowSvgUtil.replaceAll(str, text, endTo.getLabel());
//			str = FlowSvgUtil.replaceAll(str, cricle, StringUtil.getString((endTo.getHeight()-5)/2));
			str = FlowSvgUtil.replaceAll(str, text_local, StringUtil.getString(endTo.getHeight()/2+14));
			result = str;
		} catch (DocumentException e) {
			throw new FixFlowException("",e);
		}
		
		return result;
	}

}
