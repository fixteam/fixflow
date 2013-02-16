package com.founder.fix.fixflow.designer.expand.dataobj;

import com.founder.fix.fixflow.designer.modeler.ui.property.common.DataVariableImport;

public class DataVariableImportImpl implements DataVariableImport {
	
	protected String id;
	
	protected String name;
	
	protected String dataType;
	
	protected String variableValue;
	
	

	

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
	public String getDataType() {
		// TODO Auto-generated method stub
		return this.dataType;
	}

	@Override
	public String getVariableValue() {
		// TODO Auto-generated method stub
		return this.variableValue;
	}
	
	
	
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDataType(String dataType) {
		
		
		/* <dataTypeDef name="文本" typeValue="String" id="String"/>
		    <dataTypeDef name="整型" typeValue="int" id="int"/>
		    <dataTypeDef name="Java对象" typeValue="Object" id="Object"/>
		    <dataTypeDef name="布尔类型" typeValue="boolean" id="Boolean"/>
		    <dataTypeDef name="字节类型" typeValue="byte[]" id="byte[]"/>
		    <dataTypeDef name="short" typeValue="short" id="short"/>
		    <dataTypeDef name="高精度数字" typeValue="java.math.BigDecimal" id="BigDecimal"/>
		    <dataTypeDef name="long" typeValue="long" id="long"/>
		    <dataTypeDef name="float" typeValue="float" id="float"/>
		    <dataTypeDef name="double" typeValue="double" id="double"/>
		    <dataTypeDef name="日期类型" typeValue="java.util.Date" id="Date"/>
		    <dataTypeDef name="时间类型" typeValue="java.sql.Time" id="Time"/>
		    <dataTypeDef name="时间戳类型" typeValue="java.sql.Timestamp" id="Timestamp"/>
		    <dataTypeDef name="Blob" typeValue="java.sql.Blob" id="Blob"/>
		    <dataTypeDef name="Clob" typeValue="java.sql.Clob" id="Clob"/>*/
		
		if(dataType.toLowerCase().equals("String".toLowerCase())){
			this.dataType = "java.lang.String";
			return;
		}
		if(dataType.toLowerCase().equals("Object".toLowerCase())){
			this.dataType = "java.lang.Object";
			return;
		}
		if(dataType.toLowerCase().equals("Boolean".toLowerCase())){
			this.dataType = "boolean";
			return;
		}
		if(dataType.toLowerCase().equals("byte[]".toLowerCase())){
			this.dataType = "byte[]";
			return;
		}
		if(dataType.toLowerCase().equals("short".toLowerCase())){
			this.dataType = "short";
			return;
		}
		if(dataType.toLowerCase().equals("BigDecimal".toLowerCase())){
			this.dataType = "java.math.BigDecimal";
			return;
			
		}
		if(dataType.toLowerCase().equals("long".toLowerCase())){
			this.dataType = "long";
			return;
		}
		if(dataType.toLowerCase().equals("float".toLowerCase())){
			this.dataType = "float";
			return;
		}
		if(dataType.toLowerCase().equals("double".toLowerCase())){
			this.dataType = "double";
			return;
		}
		if(dataType.toLowerCase().equals("Date".toLowerCase())){
			this.dataType = "java.util.Date";
			return;
		}
		if(dataType.toLowerCase().equals("Time".toLowerCase())){
			this.dataType = "java.sql.Time";
			return;
			
		}
		if(dataType.toLowerCase().equals("Blob".toLowerCase())){
			this.dataType = "java.sql.Blob";
			return;
		}
		if(dataType.toLowerCase().equals("Clob".toLowerCase())){
			this.dataType = "java.sql.Clob";
			return;
		}
		
		
		this.dataType = dataType;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

}
