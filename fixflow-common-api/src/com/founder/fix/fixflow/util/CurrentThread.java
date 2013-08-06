/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author shao
 */
package com.founder.fix.fixflow.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.shell.DBConnection;
import com.founder.fix.fixflow.shell.FixFlowShellProxy;


public class CurrentThread {
	/**
	 * 线程副本 当前Items
	 */
	private static final ThreadLocal<Map> currentItems = new ThreadLocal<Map>();
	
	private static final ThreadLocal<Date> timer = new ThreadLocal<Date>();
	
	private static final ThreadLocal<Map<String,DBConnection>> ThreadDBPool = new ThreadLocal<Map<String,DBConnection>>();
	
	
	
	public static ThreadLocal<Map<String,DBConnection>> getThreadDBPool() {
		return ThreadDBPool;
	}

	public static void setTimer(Date dv){
		timer.set(dv);
	}
	
	public static Date getTimer(){
		return timer.get();
	}
	
	public static void init(){
		getThreadDBPool().set(new HashMap<String,DBConnection>());
	}
	
	public static void clear(){
		currentItems.set(null);
		timer.set(null);
		
		if(FixFlowShellProxy.isPoolConn()){
			Map<String,DBConnection> dbconns = (CurrentThread.getThreadDBPool().get());
			for(Entry<String,DBConnection> tmp:dbconns.entrySet()){
				DBConnection td = tmp.getValue();
				if(td!=null){
					if (td != null) td.close();
				}
			}
			getThreadDBPool().set(null);
		}
		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();
		FixFlowShellProxy.closeProcessEngine(processEngine, false);
	}
	
	/**通过线程副本取当前线程中对应的Items
	 * @return Items
	 */
	public static Map getItems() 
	{
		return (Map) currentItems.get();
	}

	/**设置当前线程的Items
	 * @param hashtable
	 */
	public static void setItems(Map hashtable) 
	{
		currentItems.set(hashtable);
	}
}
