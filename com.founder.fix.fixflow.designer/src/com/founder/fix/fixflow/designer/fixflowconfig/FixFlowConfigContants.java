package com.founder.fix.fixflow.designer.fixflowconfig;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.bpmn2extensions.coreconfig.TimeUnitType;

public class FixFlowConfigContants {
	public static Map<TimeUnitType, Integer> TIME_UNIT_TYPE = new HashMap<TimeUnitType, Integer>();
	static {
		TIME_UNIT_TYPE.put(TimeUnitType.DAYS, 0);
		TIME_UNIT_TYPE.put(TimeUnitType.HOURS, 1);
		TIME_UNIT_TYPE.put(TimeUnitType.MICROSECONDS, 2);
		TIME_UNIT_TYPE.put(TimeUnitType.MILLISECONDS, 3);
		TIME_UNIT_TYPE.put(TimeUnitType.MINUTES, 4);
		TIME_UNIT_TYPE.put(TimeUnitType.NANOSECONDS, 5);
		TIME_UNIT_TYPE.put(TimeUnitType.SECONDS, 6);
	}
}
