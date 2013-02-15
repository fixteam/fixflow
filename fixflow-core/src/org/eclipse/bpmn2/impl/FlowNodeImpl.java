/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 */
package org.eclipse.bpmn2.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ComparatorSequence;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Flow Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.bpmn2.impl.FlowNodeImpl#getIncoming <em>Incoming</em>}
 * </li>
 * <li>{@link org.eclipse.bpmn2.impl.FlowNodeImpl#getLanes <em>Lanes</em>}</li>
 * <li>{@link org.eclipse.bpmn2.impl.FlowNodeImpl#getOutgoing <em>Outgoing</em>}
 * </li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class FlowNodeImpl extends FlowElementImpl implements FlowNode {
	/**
	 * The cached value of the '{@link #getIncoming() <em>Incoming</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getIncoming()
	 * @generated
	 * @ordered
	 */
	protected EList<SequenceFlow> incoming;

	/**
	 * The cached value of the '{@link #getLanes() <em>Lanes</em>}' reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLanes()
	 * @generated
	 * @ordered
	 */
	protected EList<Lane> lanes;

	/**
	 * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOutgoing()
	 * @generated
	 * @ordered
	 */
	protected EList<SequenceFlow> outgoing;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected FlowNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bpmn2Package.Literals.FLOW_NODE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<SequenceFlow> getIncoming() {
		if (incoming == null) {
			incoming = new EObjectWithInverseEList<SequenceFlow>(SequenceFlow.class, this, Bpmn2Package.FLOW_NODE__INCOMING,
					Bpmn2Package.SEQUENCE_FLOW__TARGET_REF);
		}
		return incoming;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<Lane> getLanes() {
		if (lanes == null) {
			lanes = new EObjectWithInverseResolvingEList.ManyInverse<Lane>(Lane.class, this, Bpmn2Package.FLOW_NODE__LANES,
					Bpmn2Package.LANE__FLOW_NODE_REFS);
		}
		return lanes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public List<SequenceFlow> getOutgoing() {
		if (outgoing == null) {
			outgoing = new EObjectWithInverseEList<SequenceFlow>(SequenceFlow.class, this, Bpmn2Package.FLOW_NODE__OUTGOING,
					Bpmn2Package.SEQUENCE_FLOW__SOURCE_REF);
			// 创建比较器对象
			ComparatorSequence comparatorSequence = new ComparatorSequence();
			// 调用排序方法
			Collections.sort(outgoing, comparatorSequence);

		}
		return outgoing;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Bpmn2Package.FLOW_NODE__INCOMING:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getIncoming()).basicAdd(otherEnd, msgs);
		case Bpmn2Package.FLOW_NODE__LANES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getLanes()).basicAdd(otherEnd, msgs);
		case Bpmn2Package.FLOW_NODE__OUTGOING:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutgoing()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Bpmn2Package.FLOW_NODE__INCOMING:
			return ((InternalEList<?>) getIncoming()).basicRemove(otherEnd, msgs);
		case Bpmn2Package.FLOW_NODE__LANES:
			return ((InternalEList<?>) getLanes()).basicRemove(otherEnd, msgs);
		case Bpmn2Package.FLOW_NODE__OUTGOING:
			return ((InternalEList<?>) getOutgoing()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Bpmn2Package.FLOW_NODE__INCOMING:
			return getIncoming();
		case Bpmn2Package.FLOW_NODE__LANES:
			return getLanes();
		case Bpmn2Package.FLOW_NODE__OUTGOING:
			return getOutgoing();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Bpmn2Package.FLOW_NODE__INCOMING:
			getIncoming().clear();
			getIncoming().addAll((Collection<? extends SequenceFlow>) newValue);
			return;
		case Bpmn2Package.FLOW_NODE__LANES:
			getLanes().clear();
			getLanes().addAll((Collection<? extends Lane>) newValue);
			return;
		case Bpmn2Package.FLOW_NODE__OUTGOING:
			getOutgoing().clear();
			getOutgoing().addAll((Collection<? extends SequenceFlow>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case Bpmn2Package.FLOW_NODE__INCOMING:
			getIncoming().clear();
			return;
		case Bpmn2Package.FLOW_NODE__LANES:
			getLanes().clear();
			return;
		case Bpmn2Package.FLOW_NODE__OUTGOING:
			getOutgoing().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case Bpmn2Package.FLOW_NODE__INCOMING:
			return incoming != null && !incoming.isEmpty();
		case Bpmn2Package.FLOW_NODE__LANES:
			return lanes != null && !lanes.isEmpty();
		case Bpmn2Package.FLOW_NODE__OUTGOING:
			return outgoing != null && !outgoing.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	// fixflow

	/* *****************FixFlow 扩展属性 扩展方法 非BPMN2.0 原生******************** */

	// Field　字段
	// //////////////////////////////////////////////////////

	// Function　方法
	// //////////////////////////////////////////////////////

	/**
	 * 
	 * @param executionContext
	 * @throws Exception
	 */
	public void enter(ExecutionContext executionContext) {

		TokenEntity token = executionContext.getToken();

		// 把令牌的所在节点设置为当前节点
		token.setFlowNode(this);

		// 触发节点进入事件
		fireEvent(BaseElementEvent.EVENTTYPE_NODE_ENTER, executionContext);

		// 设置令牌进入节点的时间
		token.setNodeEnterTime(new Date());

		// 移除执行内容对象的线条关联
		
		executionContext.clearExecutionContextData();
		//executionContext.setSequenceFlow(null);

		//executionContext.setSequenceFlowSource(null);

		//executionContext.setGroupID(null);

		execute(executionContext);

	}

	protected SubProcess subProcess;

	public void setSubProcess(SubProcess subProcess) {
		this.subProcess = subProcess;
	}

	public SubProcess getSubProcess() {
		return this.subProcess;
	}

	/**
	 * 
	 * @param executionContext
	 */
	public void execute(ExecutionContext executionContext) {

		// 节点离开
		leave(executionContext);

	}

	public void leave(ExecutionContext executionContext) {
		defaultLeave(executionContext);

	}

	private void defaultLeave(ExecutionContext executionContext) {
		TokenEntity token = executionContext.getToken();

		// 发生节点离开事件
		fireEvent(BaseElementEvent.EVENTTYPE_NODE_LEAVE, executionContext);
		
		//离开时数据清理。
		leaveClearData(executionContext);
		
		// kenshin  2013.1.2
		// 用来处理非线条流转令牌,如退回、跳转
	
		
		if(executionContext.getToFlowNode()!=null){
			FlowNode toFlowNode=executionContext.getToFlowNode();
			toFlowNode.enter(executionContext);
			return;
		}
		
		

		List<SequenceFlow> sequenceFlowList = new ArrayList<SequenceFlow>();

		for (SequenceFlow sequenceFlow : getOutgoing()) {

			if (sequenceFlow.isContinue(executionContext)) {
				sequenceFlowList.add(sequenceFlow);
			}

		}

		// 节点后面没有线的处理
		if (sequenceFlowList.size() == 0) {
			if (getOutgoing().size() == 0) {
				throw new FixFlowException(this.getName() + "(" + this.getId() + ") 节点后面没有处理线条！");
			} else {
				throw new FixFlowException(this.getName() + "(" + this.getId() + ") 节点后面的条件都不满足导致节点后面没有处理线条,请检查后续线条条件！");
			}
		}

		// calculationTransitionsExp(executionContext);

		// 节点后面就一条线的处理
		if (sequenceFlowList.size() == 1) {
			leave(executionContext, sequenceFlowList.get(0));
			return;
		}

		// 节点后面大于一条线的处理
		if (sequenceFlowList.size() > 1) {

			// 创建分支令牌集合
			ArrayList<ForkedToken> forkedTokens = new ArrayList<ForkedToken>();

			// 遍历满足条件线条
			for (SequenceFlow sequenceFlow : sequenceFlowList) {
				// 获取线条名称
				String sequenceFlowId = sequenceFlow.getId();
				// 创建分支令牌并添加到集合中
				forkedTokens.add(this.createForkedToken(token, sequenceFlowId));
			}
			// 遍历分支令牌集合
			for (ForkedToken forkedToken : forkedTokens) {
				// 获取令牌
				TokenEntity childToken = forkedToken.token;
				// 获取令牌编号
				String leavingTransitionId = forkedToken.leavingTransitionId;
				// 创建执行内容对象并将里边的令牌赋值为新的分支令牌

				ExecutionContext childExecutionContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(childToken);

				// 执行节点离开方法
				this.leave(childExecutionContext, getOutgoing(leavingTransitionId));

			}
		}
	}

	/**
	 * 根据线条编号从节点的离开线条集合中获取线条对象
	 * 
	 * @param transitionId
	 *             线条编号
	 * @return 所需要的线条对象
	 */
	public SequenceFlow getOutgoing(String outgoingId) {

		return getOutgoingMap().get(outgoingId);

	}

	/**
	 * 离开节点的时候需要清理的数据.
	 * 每个子类需要自己实现.
	 */
	public void leaveClearData(ExecutionContext executionContext) {

		
	}

	/**
	 * 节点的离开事件
	 * 
	 * @param executionContext
	 *            执行内容对象
	 * @param transition
	 *            离开的线条
	 * @throws Exception
	 */
	public void leave(ExecutionContext executionContext, SequenceFlow sequenceFlow) {

		
		
		// 从执行内容对象中获取令牌
		TokenEntity token = executionContext.getToken();
		// 将令牌的当前节点设置为当前节点
		token.setFlowNode(this);
		// 将执行内容对象中的线条指定为将要离开的线条
		executionContext.setSequenceFlow(sequenceFlow);

		// 将线条来源对象设置为当前节点
		executionContext.setSequenceFlowSource(this);

		// 执行线条的进入方法
		sequenceFlow.take(executionContext);
	}

	// 分支处理///////////////////////////////

	public ForkedToken createForkedToken(TokenEntity parent, String transitionId) {
		// 创建一个令牌实例

		TokenEntity childToken = new TokenEntity(parent, transitionId);

		// 创建分支令牌
		ForkedToken forkedToken = null;
		forkedToken = new ForkedToken(childToken, transitionId);
		return forkedToken;
	}

	protected String getTokenName(TokenEntity parent, String transitionName) {
		String tokenName = null;
		if (transitionName != null) {
			if (!parent.hasChild(transitionName)) {
				tokenName = transitionName;
			} else {
				int i = 2;
				tokenName = transitionName + Integer.toString(i);
				while (parent.hasChild(tokenName)) {
					i++;
					tokenName = transitionName + Integer.toString(i);
				}
			}
		} else {
			// 没有线条名称的时候
			int size = (parent.getChildren() != null ? parent.getChildren().size() + 1 : 1);
			tokenName = Integer.toString(size);
		}
		return tokenName;
	}

	public static class ForkedToken {
		public TokenEntity token = null;
		String leavingTransitionId = null;

		public ForkedToken(TokenEntity token, String leavingTransitionId) {
			this.token = token;
			this.leavingTransitionId = leavingTransitionId;
		}
	}

	protected Map<String, SequenceFlow> outgoingMap;

	public Map<String, SequenceFlow> getOutgoingMap() {
		if ((outgoingMap == null) && (outgoing != null)) {

			outgoingMap = new HashMap<String, SequenceFlow>();

			for (SequenceFlow outgoingObj : outgoing) {
				outgoingMap.put(outgoingObj.getId(), outgoingObj);
			}

		}
		return outgoingMap;
	}

} // FlowNodeImpl
