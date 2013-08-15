package com.founder.fix.fixflow.test.db.pagination;

import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

/**
 * 分页测试
 * @author kenshin
 *
 */
public class PaginationTest extends AbstractFixFlowTestCase{
	
	
	@Override
	protected void setUp() throws Exception {

		// 初始化测试方法
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// 测试完毕清理方法
		super.tearDown();

	}
	
	public void testOraclePagination()
	{
		SqlCommand sqlCommand=new SqlCommand(processEngineConfiguration.createConnection());
		Pagination pagination=processEngineConfiguration.getDbConfig().getPagination();
		String runSqlString="SELECT * FROM AU_USERINFO ORDER BY SSOID";
		String paginationSqlString= pagination.getPaginationSql(runSqlString, 3, 5, "*", null);
		List<Map<String, Object>> dataList=sqlCommand.queryForList(paginationSqlString);
		assertNotNull(dataList);
		return;
		
		
	}
	

}
