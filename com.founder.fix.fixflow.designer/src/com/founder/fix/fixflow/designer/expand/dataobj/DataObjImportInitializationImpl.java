package com.founder.fix.fixflow.designer.expand.dataobj;

import java.util.List;

import com.founder.fix.fixflow.designer.modeler.ui.property.common.DataObjImport;
import com.founder.fix.fixflow.designer.modeler.ui.property.common.DataObjImportInitialization;

public class DataObjImportInitializationImpl implements DataObjImportInitialization {

	@Override
	public List<DataObjImport> init() {
		// TODO Auto-generated method stub
		return DataObjCache.getDataObjImports();
	}

}
