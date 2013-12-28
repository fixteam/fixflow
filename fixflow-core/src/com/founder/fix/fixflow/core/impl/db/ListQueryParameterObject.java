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
package com.founder.fix.fixflow.core.impl.db;

import com.founder.fix.fixflow.core.impl.Page;

public class ListQueryParameterObject {

	protected Object parameter;
	protected String databaseType;
	protected Page page;

	public Page getPage() {
		return page;
	}

	public ListQueryParameterObject() {
	}

	public ListQueryParameterObject(Object parameter, Page page) {
		this.parameter = parameter;
		this.page=page;
	}


	public Object getParameter() {
		return parameter;
	}



	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

	public String getOrderBy() {
		// the default order column
		return "RES.ID_ asc";
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public String getDatabaseType() {
		return databaseType;
	}
}
