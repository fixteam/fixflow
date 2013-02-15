package com.founder.fix.fixflow.expand.database.pagination;


import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.util.StringUtil;



public class OraclePaginationImpl implements Pagination {

	protected static final String SQL_END_DELIMITER = ";";
	
	private String trim(String sql) {
        sql = sql.trim();
        if (sql.endsWith(SQL_END_DELIMITER)) {
            sql = sql.substring(0, sql.length() - 1
                    - SQL_END_DELIMITER.length());
        }
        return sql;
    }

	
	
	public String getPaginationSql(String sql, int firstResult, int maxResults, String fields) {
		sql = trim(sql);
    	String inFiled = fields;
    	if(StringUtil.isEmpty(fields)){
    		fields="*";
    	}
    	inFiled="A.*";
        StringBuffer sb = new StringBuffer(sql.length() + 20);
        sb.append("SELECT "+fields+" FROM (SELECT "+inFiled+", ROWNUM as RN_ FROM (");
        sb.append(sql);
        sb.append(" )A )b WHERE b.RN_ <=");
        sb.append(maxResults);
        sb.append(" and b.RN_ >=");
        if (firstResult >= 0) {
           sb.append(firstResult);
        } 
        return sb.toString();
	}



	public String getIsNullLocalismSql(String sql) {
		return "nvl("+sql+")";
	}



	public String getDateSql() {
		return "date";
	}



	public String getCurrentDateSql() {
		return "sysdate";
	}



	public String getCastConvertSql(String sql, String type) {
		return "cast("+sql+" AS "+type+")";
	}



	public String getLocalismSql(String localismKey, String localismValue) {
		if(localismKey.equals("processperformance")) {
			return "nvl((nvl(cast(end_time as date),sysdate)-cast(start_time as date))*24,0)";
		}
		
		if(localismKey.equals("datediffconvert")) {
			return "avg(cast(end_time as date)-cast(create_time as date))*24";
		}
		
		if(localismKey.equals("datediffconvertorg")) {
			return "AVG(CAST(x.end_time AS DATE)-CAST(x.create_time AS DATE))*24";
		}
		
		return null;
	}




}
