package com.founder.fix.fixflow.designer.expand.dataobj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;



import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.modeler.ui.property.common.DataObjImport;
import com.founder.fix.fixflow.designer.modeler.ui.property.common.DataVariableImport;
import com.founder.fix.fixflow.designer.util.XmlUtil;

public class DataObjImportImpl implements DataObjImport {
	
	protected String id;
	
	

	protected String name;
	
	protected String type;
	
	protected String filePath;
	
	

	protected List<DataVariableImport> dataVariableImports;
	

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public List<DataVariableImport> getDataVariableList(String type) {
		// TODO Auto-generated method stub
		if(this.dataVariableImports==null)
		{
			this.dataVariableImports=new ArrayList<DataVariableImport>();
					
			getDataVariables(type);

			
		}
		
		return this.dataVariableImports;
	}
	
	
	private void getDataVariables(String type)
	{
		
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document=null;
		try {
			document = XmlUtil.read(fis, "UTF-8");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 一配置文件代表一个数据对象。
		Element dataObj = document.getRootElement();
		
		

		// 获取列信息
		@SuppressWarnings("unchecked")
		List<Element> properties = dataObj.elements("property");
		
		List<Element> propertiesFlow = dataObj.elements("relfixflow");
		String processIdString=SectionBpmnElement.process.getId();
		String bizId=XmlUtil.getAttributeValue(dataObj.attribute("id"));
		//String bizName=XmlUtil.getAttributeValue(dataObj.attribute("name"));
		DataVariableImportImpl column1 = new DataVariableImportImpl();
		column1.setId("Fix_BizName");
		column1.setName("Fix_BizName");
		column1.setDataType("String");
		column1.setVariableValue("\""+bizId+"\"");
		
		this.dataVariableImports.add(column1);
		
		
		
		DataVariableImportImpl column2 = new DataVariableImportImpl();
		column2.setId("Fix_BizKeyFile");
		column2.setName("Fix_BizKeyFile");
		column2.setDataType("String");
		column2.setVariableValue("");
		this.dataVariableImports.add(column2);
		
		for (Element element : propertiesFlow) {
			
			if(XmlUtil.getAttributeValue(element.attribute("processDefinitionKey")).equals(processIdString)){
				column2.setVariableValue("\""+XmlUtil.getAttributeValue(element.attribute("processBusinessField"))+"\"");

			}
			
		}
		
		//
		
		if (properties != null) {
			for (Element tmp : properties) {
				DataVariableImportImpl column = new DataVariableImportImpl();
				Attribute pid = tmp.attribute("id");
				column.setId(XmlUtil.getAttributeValue(pid));
				Attribute pname = tmp.attribute("name");
				column.setName(XmlUtil.getAttributeValue(pname));
				
				Attribute datatype = tmp.attribute("dataType");
				column.setDataType(XmlUtil.getAttributeValue(datatype));
				if(type.equals("db")){
					column.setVariableValue("bizData.getMasterValue(processInfo.getProcessDefinitionKey(),processInfo.getBizKey(),\""+XmlUtil.getAttributeValue(pid)+"\")");
				}
				else{
					column.setVariableValue("formInfo.getData(\""+XmlUtil.getAttributeValue(pid)+"\")");
				}
				
				
				
				this.dataVariableImports.add(column);
			}
		}
		
		//Object bizData.getMasterValue("defkey","bizkey","字段名称");
		//<relfixflow processDefinitionKey="mydemo" processBusinessField="ID" name="Default Process" enable="1"/>
		//(processInfo.getProcessDefinitionKey(),processInfo.getBizKey(),)
		
		
	}

	@Override
	public String getFilePath() {
		// TODO Auto-generated method stub
		return this.filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

}
