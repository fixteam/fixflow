package com.founder.fix.fixflow.designer.util;

public class CreateJavaClassUtil {
	
	
	
	protected String attribute = "";
	protected String setter = "";
	protected String getter = "";
	protected String className="";
	protected String executeConnector="";
	protected String packageName="";
	protected String importValue="";
	
	


    public void createAttribute(String type, String name) {
        attribute = attribute+ "\tprivate " + type + " " + name + ";\n\n";
    }

    public void createSetter(String type, String name) {
        setter =setter+ "\tpublic void  set"
                + name.substring(0, 1).toUpperCase()
                + name.substring(1, name.length()) + "(" + type + " " + name
                + "){\n\t\tthis." + name + " = " + name + ";\n\t}\n\n";
    }

    public void createGetter(String type, String name) {
        getter =getter+ "\tpublic " + type + "  get"
                + name.substring(0, 1).toUpperCase()
                + name.substring(1, name.length()) + "(){\n\t\treturn null ;\n\t}\n\n";
    }

    public void createInputCode(String type, String name) {
        createAttribute(type, name);
        createSetter(type, name);
        //+ createGetter(type, name);
    }
    
    public void createOutputCode(String type, String name) {
    	createGetter(type, name);
        //+ createGetter(type, name);
    }
    
    public void createExecuteConnector()
    {
    	this.executeConnector="\tpublic void execute(ExecutionContext executionContext) throws Exception {\n\n\n\t}\n\n";
    }
    
    public void setClassName(String className)
    {
    	this.className=className;
    }
    
    public String generateJavaCode()
    {
    	String codeString=packageName+"\n\n"+this.importValue+"\n\npublic class "+this.className+" implements ConnectorHandler {\n\n"
    	+attribute+executeConnector+setter+getter+"}";
    	return codeString;
    }
    
    public void setPackageName(String packageName)
    {
    	this.packageName="package "+packageName+";";
    }
    
    public void createImport(String importValue)
    {
    	this.importValue=this.importValue+"\nimport "+importValue;
    }
    

    public static void main(String[] args) {

    	
    	CreateJavaClassUtil createJavaClassUtil=new CreateJavaClassUtil();
    	createJavaClassUtil.createInputCode("int", "字段1");
    	createJavaClassUtil.createInputCode("String", "字段2");
    	createJavaClassUtil.createOutputCode("int","输出字段");
    	createJavaClassUtil.setClassName("HelloKenshin");
    	createJavaClassUtil.createExecuteConnector();
    	createJavaClassUtil.setPackageName("com.founder.fix.fixflow.core.action");
    	createJavaClassUtil.createImport("com.founder.fix.fixflow.core.runtime.ExecutionContext;");
    	System.out.print(createJavaClassUtil.generateJavaCode());

    }

}
