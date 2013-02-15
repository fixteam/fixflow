package com.founder.fix.fixflow.core.impl.action;



import com.founder.fix.fixflow.core.action.ActionHandler;
import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.instantiation.Delegation;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class Action implements ActionHandler {

	long id = 0;
	protected String name = null;
	protected boolean isPropagationAllowed = true;
	protected boolean isAsync = false;
	protected Action referencedAction = null;
	protected Delegation actionDelegation = null;
	protected String actionExpression = null;
	protected BaseElementEvent baseElementEvent = null;
	protected ProcessDefinitionBehavior processDefinition = null;

	public Action() {
	}

	public Action(Delegation actionDelegate) {
		this.actionDelegation = actionDelegate;
	}



	public void execute(ExecutionContext executionContext) {
		if (referencedAction != null) {
			referencedAction.execute(executionContext);

		} else if (actionExpression != null) {
			// JbpmExpressionEvaluator.evaluate(actionExpression,
			// executionContext);

		} else if (actionDelegation != null) {
			ActionHandler actionHandler = (ActionHandler) actionDelegation.getInstance();
			actionHandler.execute(executionContext);
		}
	}

	public void setName(String name) {

		this.name = name;
	}

	// getters and setters
	// //////////////////////////////////////////////////////

	public boolean acceptsPropagatedEvents() {
		return isPropagationAllowed;
	}

	public boolean isPropagationAllowed() {
		return isPropagationAllowed;
	}

	public void setPropagationAllowed(boolean isPropagationAllowed) {
		this.isPropagationAllowed = isPropagationAllowed;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BaseElementEvent getBaseElementEvent() {
		return this.baseElementEvent;
	}

	public ProcessDefinitionBehavior getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(ProcessDefinitionBehavior processDefinition) {
		this.processDefinition = processDefinition;
	}

	public Delegation getActionDelegation() {
		return actionDelegation;
	}

	public void setActionDelegation(Delegation instantiatableDelegate) {
		this.actionDelegation = instantiatableDelegate;
	}

	public Action getReferencedAction() {
		return referencedAction;
	}

	public void setReferencedAction(Action referencedAction) {
		this.referencedAction = referencedAction;
	}

	public boolean isAsync() {
		return isAsync;
	}

	public String toString() {
		String toString = null;
		if (name != null) {
			toString = "action[" + name + "]";
		} else if ((actionDelegation != null) && (actionDelegation.getClassName() != null)) {
			String className = actionDelegation.getClassName();
			toString = className.substring(className.lastIndexOf('.') + 1);
		} else if (actionExpression != null) {
			toString = actionExpression;
		} else {
			String className = getClass().getName();
			className = className.substring(className.lastIndexOf('.') + 1);
			if (name != null) {
				toString = className + "(" + name + ")";
			} else {
				toString = className + "(" + Integer.toHexString(System.identityHashCode(this)) + ")";
			}
		}
		return toString;
	}

}
