package com.founder.fix.fixflow.core.impl.flowgraphics.svg.component;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.founder.fix.fixflow.core.impl.flowgraphics.svg.FlowSvgUtil;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.SvgBench;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgAnnotationTo;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgBaseTo;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;

public class SvgAnnotationComponent implements ISvgComponent {

	private static String comPath = "/svgcomponent/textAnnotation.xml";
	
	private static String path = "{path}";
	
	public String createComponent(SvgBaseTo svgTo) {
		String result = null;
		try {
			SvgAnnotationTo annoTo = (SvgAnnotationTo)svgTo;
			InputStream in = SvgBench.class.getResourceAsStream(comPath);
			Document doc = XmlUtil.read(in);
			String str = doc.getRootElement().asXML();
			str = FlowSvgUtil.replaceAll(str, local_x, StringUtil.getString(annoTo.getX()));
			str = FlowSvgUtil.replaceAll(str, local_y, StringUtil.getString(annoTo.getY()));
			str = FlowSvgUtil.replaceAll(str, id, annoTo.getId());
			str = FlowSvgUtil.replaceAll(str, text, annoTo.getLabel());
			StringBuffer sb = new StringBuffer();
			sb.append(" M19 0  L0 0  L0 ");
			sb.append(annoTo.getHeight());
			sb.append("  L19 ");
			sb.append(annoTo.getHeight());
			str = FlowSvgUtil.replaceAll(str, path, sb.toString());
			result = str;
		} catch (DocumentException e) {
			throw new FixFlowException("",e);
		}
		
		return result;
	}

}
