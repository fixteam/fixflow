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
package com.founder.fix.fixflow.expand.connector.FixService;


import java.util.HashMap;

import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;


public class FixService implements ConnectorHandler {

	private java.lang.String serviceID;

	private java.lang.Object params;

	private java.util.HashMap context;

	public void execute(ExecutionContext executionContext) throws Exception {
		//DataView dv = DataView.buildDataView();
		//Object obj  = AdapterProxy.executeForNoPermission(serviceID,dv,params);
		//context.putAll(dv.getReturnData());
	}

	public void  setServiceID(java.lang.String serviceID){
		this.serviceID = serviceID;
	}

	public void  setParams(java.lang.Object params){
		this.params = params;
	}

	public void  setContext(java.util.HashMap context){
		this.context = context;
	}

	public HashMap  getOut(){
		return context ;
	}

}