package com.founder.fix.fixflow.designer.modeler.ui.property.common;

import java.util.List;

import com.founder.fix.fixflow.designer.expand.dataobj.DataObjImportInitializationImpl;

public class DataObjImportFactory {
	
	
	public static List<DataObjImport> createDataObjImports()
	{

		DataObjImportInitialization dataObjImportInitialization=new DataObjImportInitializationImpl();
		
		
		
		return dataObjImportInitialization.init();
		
		
		

	}
	

}
