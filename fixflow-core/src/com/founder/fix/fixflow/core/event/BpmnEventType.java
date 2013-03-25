package com.founder.fix.fixflow.core.event;

public enum BpmnEventType {
	
	None,
	Message,
	Timer,
	Escalation,
	Conditional,
	Link,
	Error,
	Cancel,
	Compensation,
	Signal,
	Multiple,
	ParallelMultiple,
	Terminate

}
