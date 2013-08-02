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
package com.founder.fix.fixflow.expand.connector.ReciveTask;


import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;


public class ReciveTask implements ConnectorHandler {

	private java.lang.Object field0;

	public void execute(ExecutionContext executionContext) throws Exception {
	
		//AdapterProxy.executeArgsForFront("WT_DATA_FILL.subDataCollect", DataView.buildDataView(), field0);
		//Object jiangnanObject=ExpressionMgmt.execute("newforminfo.getData(\"RWCD\")", executionContext);
		//ExpressionMgmt.execute("${lingpai}=newtokenid", executionContext);
		//ExpressionMgmt.execute("${pforminfo}=newforminfo", executionContext);
		//ExpressionMgmt.execute("${pformdata}=newformdata", executionContext);
		//executionContext.getProcessInstance().setBizKey(jiangnanObject.toString());
		
		
	}

	public void  setField0(java.lang.Object field0){
		this.field0 = field0;
	}

}