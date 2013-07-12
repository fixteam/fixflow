package com.founder.fix.fixflow.test.processPerformance;

import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

public class ProcessPerformance extends AbstractFixFlowTestCase {
	
	
	public void testEee() {
		String[] str = new String[] {"csb","yfb"};
//		String[] str = new String[] {"fixtest","ziliucheng"};
		taskService.processPerformance(str, "2012-11-01", "2012-11-03", "org");
//		runtimeService.processPerformance("2012-11-01", "2012-11-03");
//		runtimeService.processPerformance(str, "2012-11-01", "2012-11-03");
	}
	
	
	
}
