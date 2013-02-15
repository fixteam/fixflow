package com.founder.fix.fixflow.core.impl.flowgraphics.svg;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;

public class SvgBench {
	private static String templetePath = "/svgcomponent/svgTemplate.xml";
	
	private static String childrenRep = "{children}";
	
	private static String edgeRep = "{edge}";
	
	private static String bwidth ="{width}";
	
	private static String bhight ="{height}";
	
	private static String minhight ="{minhight}";
	
	private static String minwidth ="{minwidth}";
	
	private Document doc;

	private List<String> children = new ArrayList<String>();
	
	private List<String> edge = new ArrayList<String>();
	
	private float width;

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHight() {
		return hight;
	}

	public void setHight(float hight) {
		this.hight = hight;
	}

	private float hight;
	
	private float mhight;
	
	private float mwidth;
	
	public float getMwidth() {
		return mwidth;
	}

	public void setMwidth(float mwidth) {
		this.mwidth = mwidth;
	}

	public float getMhight() {
		return mhight;
	}

	public void setMhight(float mhight) {
		this.mhight = mhight;
	}

	public SvgBench(){
		try {
			InputStream in = SvgBench.class.getResourceAsStream(templetePath);
			doc = XmlUtil.read(in);
			
		} catch (DocumentException e) {
			throw new FixFlowException("",e);
		}
	}
	
	public void addChildren(String ele){
		children.add(ele);
	}
	
	public void addEdge(String ele){
		edge.add(ele);
	}
	
	public String release(){
		String document = doc.asXML();
		StringBuffer crep = new StringBuffer();
		for(String tmp : children){
			crep.append(tmp);
		}
		
		StringBuffer erep = new StringBuffer();
		for(String tmp : edge){
			erep.append(tmp);
		}
		
		document = FlowSvgUtil.replaceAll(document, childrenRep, crep.toString());
		document = FlowSvgUtil.replaceAll(document, edgeRep, erep.toString());
		document = FlowSvgUtil.replaceAll(document, bwidth, StringUtil.getString(getWidth()));
		document = FlowSvgUtil.replaceAll(document, bhight, StringUtil.getString(getHight()));
		document = FlowSvgUtil.replaceAll(document, minhight, StringUtil.getString(getMhight()));
		document = FlowSvgUtil.replaceAll(document, minwidth, StringUtil.getString(getMwidth()));
		
		return document;
	}
}
