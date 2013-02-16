package com.founder.fix.fixflow.designer.expand.dataobj;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.designer.modeler.ui.property.common.DataObjImport;

public class DataObjCache {
	
	private static List<DataObjImport> dataObjImports=new ArrayList<DataObjImport>();

	public static List<DataObjImport> getDataObjImports() {
		return dataObjImports;
	}
	
	public static void deleteDataObj (String olderId){

		for (int i = 0; i < dataObjImports.size(); i++) {
			if (dataObjImports.get(i).getId().equals(olderId)) {
				dataObjImports.remove(i);
				break;
			}
		}

	}


	

}
