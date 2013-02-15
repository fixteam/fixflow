package com.founder.fix.fixflow.core.impl.flowgraphics.svg.component;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;


import com.founder.fix.fixflow.core.impl.flowgraphics.svg.FlowSvgUtil;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.SvgBench;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgBaseTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgStartTo;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;

public class SvgStartComponent implements ISvgComponent {

	private static String comPath = "/svgcomponent/start.xml";
	
	@SuppressWarnings("unused")
	private static String text_local="{text_local}";
	
	@SuppressWarnings("unused")
	private static String cricle ="{cricle}";
	
	public String createComponent(SvgBaseTo svgTo) {
		String result = null;
		try {
			SvgStartTo startTo = (SvgStartTo)svgTo;
			InputStream in = SvgBench.class.getResourceAsStream(comPath);
			Document doc = XmlUtil.read(in);
			String str = doc.getRootElement().asXML();
			str = FlowSvgUtil.replaceAll(str, local_x, StringUtil.getString(startTo.getX()));
			str = FlowSvgUtil.replaceAll(str, local_y, StringUtil.getString(startTo.getY()));
			str = FlowSvgUtil.replaceAll(str, id, startTo.getId());
			str = FlowSvgUtil.replaceAll(str, text, startTo.getLabel());
//			str = FlowSvgUtil.replaceAll(str, cricle, StringUtil.getString((startTo.getHeight()-5)/2));
//			str = FlowSvgUtil.replaceAll(str, text_local, StringUtil.getString(startTo.getHeight()/2+14));
			result = str;
		} catch (DocumentException e) {
			throw new FixFlowException("",e);
		}
		
		return result;
	}

}
