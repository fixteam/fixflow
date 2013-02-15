package com.founder.fix.fixflow.core.impl.flowgraphics.svg.component;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;


import com.founder.fix.fixflow.core.impl.flowgraphics.svg.FlowSvgUtil;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.SvgBench;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgBaseTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgGroupTo;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;

public class SvgGroupComponent implements ISvgComponent {

	private static String comPath = "/svgcomponent/group.xml";
	
	public String createComponent(SvgBaseTo svgTo) {
		String result = null;
		try {
			SvgGroupTo group = (SvgGroupTo)svgTo;
			InputStream in = SvgBench.class.getResourceAsStream(comPath);
			Document doc = XmlUtil.read(in);
			String str = doc.getRootElement().asXML();
			str = FlowSvgUtil.replaceAll(str, local_x, StringUtil.getString(group.getX()));
			str = FlowSvgUtil.replaceAll(str, local_y, StringUtil.getString(group.getY()));
			str = FlowSvgUtil.replaceAll(str, id, group.getId());
			str = FlowSvgUtil.replaceAll(str, text, group.getLabel());
			str = FlowSvgUtil.replaceAll(str, width, StringUtil.getString(group.getWidth()));
			str = FlowSvgUtil.replaceAll(str, hight, StringUtil.getString(group.getHeight()));
			result = str;
		} catch (DocumentException e) {
			throw new FixFlowException("",e);
		}
		return result;
	}

}
