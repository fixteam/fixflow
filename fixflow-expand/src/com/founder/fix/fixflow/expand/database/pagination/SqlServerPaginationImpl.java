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
package com.founder.fix.fixflow.expand.database.pagination;


import com.founder.fix.fixflow.core.db.pagination.Pagination;

public class SqlServerPaginationImpl implements Pagination {

	public String getPaginationSql(String sql, int firstResult, int maxResults, String fields) {
		sql = trim(sql);
    	String inFiled = fields;
    	if(fields==null||fields.equals("")){
    		fields="*";
    		inFiled="A.*";
    	}
        StringBuffer sb = new StringBuffer(sql.length() + 20);
        sb.append("SELECT "+fields+" FROM (SELECT "+inFiled+", ROW_NUMBER() OVER(order by tt_) as RN_ FROM ( select ts.*,'1' as tt_ from (");
        sb.append(sql);
        sb.append(" ) ts )A )b WHERE b.RN_ <=");
        sb.append(maxResults);
        sb.append(" and b.RN_ >=");
        if (firstResult >= 0) {
           sb.append(firstResult);
        } 
        return sb.toString();
	}
	
	
    protected static final String SQL_END_DELIMITER = ";";

   

	private String trim(String sql) {
        sql = sql.trim();
        if (sql.endsWith(SQL_END_DELIMITER)) {
            sql = sql.substring(0, sql.length() - 1
                    - SQL_END_DELIMITER.length());
        }
        return sql;
    }



	public String getIsNullLocalismSql(String sql) {
		return "ISNULL("+sql+")";
	}



	public String getDateSql() {
		return "datetime";
	}



	public String getCurrentDateSql() {
		return "getdate()";
	}



	public String getCastConvertSql(String sql, String type) {
		return "convert("+sql+")";
	}



	public String getLocalismSql(String localismKey, String localismValue) {
		
		if(localismKey.equals("processperformance")){
			return "ISNULL(DATEDIFF(hour,start_time,end_time),0)";
		}
		
		if(localismKey.equals("datediffconvert")) {
			return "avg(DATEDIFF(hour,create_time,end_time))";
		}
		
		if(localismKey.equals("datediffconvertorg")) {
			return "avg(DATEDIFF(hour,x.create_time,x.end_time))";
		}
		
		return null;
	}

}
