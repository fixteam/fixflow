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
package com.founder.fix.fixflow.core.impl.util;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.Document;

public class XmlUtil {
	
	public static void getAllFileFromPath(File file,List<File> fileList,String ed){
		if(file!=null){
			if(file.isDirectory()){
				File[] files = file.listFiles();
				if(files!=null){
					for(File tmp:files){
						getAllFileFromPath(tmp,fileList,ed);
					}
				}
			}else if(file.getName().toLowerCase().endsWith(ed)){
				fileList.add(file);
			}
		}
	}
	
	public static Document read(String fileName)
			throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));

		return document;
	}
	
	public static String getAttributeValue(Attribute attribute){
		String result = null;
		if(attribute!=null)
			result = attribute.getValue();
		return result;
	}
	
	public static String getDefaultAttributeValue(Element element){
		return getElementAttributeValue(element,"value");
	}
	
	public static String getElementAttributeValue(Element element,String value){
		if(element!=null)
			return getAttributeValue(element.attribute(value));
		else
			return "";
	}
	
	public static String getElementText(Element element){
		String result = null;
		if(element!=null)
			result = element.getText();
		return result;
	}
	
	public static Document read(File file)
	throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		
		return document;
	}

	public static Document read(InputStream is)
			throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(is);

		return document;
	}

	public static Document read(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);

		return document;
	}

	public static Element getRootElement(Document document) {

		return document.getRootElement();
	}

	@SuppressWarnings("rawtypes")
	public static List getChildElements(Element parent) {
		return parent.elements();
	}

	public static boolean hasChild(Element element) {

		return element.nodeCount() > 0;

	}

	public static Document createDocument() {
		Document document = DocumentHelper.createDocument();
		return document;
	}

	public static Element addAttribute(org.dom4j.Element element, String name,
			String value) {
		return element.addAttribute(name, value);

	}

	public static Document parseText(String text)
			throws DocumentException {
		return DocumentHelper.parseText(text);
	}

	public static void save(Document document, String fileName,
			String encoding) throws Exception {
		XMLWriter writer = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();

			if (!"".equals(encoding))
				format.setEncoding(encoding);
			else
				format.setEncoding("UTF-8");

			writer = new XMLWriter(new FileWriter(fileName), format);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			throw e;
		} finally {
			if (writer != null)
				writer.close();

		}

	}

}
