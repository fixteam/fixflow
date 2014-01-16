package com.founder.fix.fixflow.core.impl.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class AssigneeUtil {
	
	
	public static List<String> executionExpression(String expression, ExecutionContext executionContext) {

		List<String> resultList = new ArrayList<String>();

		Object result = ExpressionMgmt.execute(expression, executionContext);
		if (result != null) {

			if (result instanceof String) {

				String[] dddStrings = result.toString().split(",");

				for (int i = 0; i < dddStrings.length; i++) {
					resultList.add(dddStrings[i]);
				}

			}

			if (result instanceof Integer) {
				resultList.add(result.toString());
			}
			if (result instanceof BigDecimal) {
				resultList.add(result.toString());
			}
	

			if (result instanceof List<?>) {
				List<?> resultTempList = (List<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

			if (result instanceof Set<?>) {
				Set<?> resultTempList = (Set<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

		}

		return resultList;
	}
	
	
	public static List<String> executionExpressionObj(Object result, ExecutionContext executionContext) {

		List<String> resultList = new ArrayList<String>();


		if (result != null) {

			if (result instanceof String) {

				String[] dddStrings = result.toString().split(",");

				for (int i = 0; i < dddStrings.length; i++) {
					resultList.add(dddStrings[i]);
				}

			}

			if (result instanceof Integer) {
				resultList.add(result.toString());
			}
			if (result instanceof BigDecimal) {
				resultList.add(result.toString());
			}
	

			if (result instanceof List<?>) {
				List<?> resultTempList = (List<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

			if (result instanceof Set<?>) {
				Set<?> resultTempList = (Set<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

		}

		return resultList;
	}


}
