package com.founder.fix.fixflow.test.util;

import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

/**
 * 引擎工具测试类
 * @author kenshin
 *
 */
public class UtilTest extends AbstractFixFlowTestCase{
	
	
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
	
	/**
	 * 反射工具类测试
	 */
	public void testReflectUtil()
	{
		Pagination pagination=(Pagination)ReflectUtil.instantiate("com.founder.fix.fixflow.core.impl.db.pagination.OraclePaginationImpl");
		assertNotNull(pagination);
	}


}
