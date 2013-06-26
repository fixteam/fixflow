package com.founder.fix.fixflow.expand.database.pagination;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class MySqlPaginationImpl implements Pagination {
	protected static final String SQL_END_DELIMITER = ";";

	public String getPaginationSql(String sql, int firstResult, int maxResults, String fields) {
		sql = trim(sql);
    	if(StringUtil.isEmpty(fields)){
    		fields="*";
    	}
        
    	StringBuffer sb = new StringBuffer(sql.length() + 20);
    	sb.append("(");
        sb.append("SELECT "+fields+" FROM (select A.* from(");
        sb.append(sql);
        sb.append(" )A");
        sb.append(" )c LIMIT ");
        sb.append(firstResult-1);
        sb.append(",");
        sb.append(maxResults-(firstResult-1));
        sb.append(")");
        
        return sb.toString();
	}

	public String getIsNullLocalismSql(String sql) {
		return "ISNULL("+sql+")";
	}

	public String getDateSql() {
		return "datetime";
	}

	public String getCurrentDateSql() {
		return "sysdate()";
	}

	public String getCastConvertSql(String sql, String type) {
		return "convert("+sql+")";
	}

	public String getLocalismSql(String localismKey, String localismValue) {
		if(localismKey.equals("processperformance")){
			return "ISNULL(TIMEDIFF(start_time,end_time))";
		}
		
		if(localismKey.equals("datediffconvert")) {
			return "avg(TIMEDIFF(create_time,end_time))";
		}
		
		if(localismKey.equals("datediffconvertorg")) {
			return "avg(TIMEDIFF(x.create_time,x.end_time))";
		}
		
		return null;
	}

	private String trim(String sql) {
        sql = sql.trim();
        if (sql.endsWith(SQL_END_DELIMITER)) {
            sql = sql.substring(0, sql.length() - 1
                    - SQL_END_DELIMITER.length());
        }
        return sql;
    }
}
