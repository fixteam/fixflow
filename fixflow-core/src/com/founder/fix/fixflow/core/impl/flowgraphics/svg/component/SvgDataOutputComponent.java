package com.founder.fix.fixflow.core.impl.flowgraphics.svg.component;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.FlowSvgUtil;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.SvgBench;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgBaseTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgDataOutputTo;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;

public class SvgDataOutputComponent implements ISvgComponent {

	private static String comPath = "/svgcomponent/DataInput.xml";
	
	private static String input = "{input}";
	
	private static String output = "{output}";
	
	private static String none ="none";
	
	public String createComponent(SvgBaseTo svgTo) {
		String result = null;
		try {
			SvgDataOutputTo stevent = (SvgDataOutputTo)svgTo;
			InputStream in = SvgBench.class.getResourceAsStream(comPath);
			Document doc = XmlUtil.read(in);
			String str = doc.getRootElement().asXML();
			str = FlowSvgUtil.replaceAll(str, local_x, StringUtil.getString(stevent.getX()));
			str = FlowSvgUtil.replaceAll(str, local_y, StringUtil.getString(stevent.getY()));
			str = FlowSvgUtil.replaceAll(str, id, stevent.getId());
			str = FlowSvgUtil.replaceAll(str, text, stevent.getLabel());
			str = FlowSvgUtil.replaceAll(str, input, none);
			str = FlowSvgUtil.replaceAll(str, output, "");
			result = str;
		} catch (DocumentException e) {
			throw new FixFlowException("",e);
		}
		return result;
	}

}
