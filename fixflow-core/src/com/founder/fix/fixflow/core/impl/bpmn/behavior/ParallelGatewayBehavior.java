/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.Date;
import java.util.Set;

import org.eclipse.bpmn2.impl.ParallelGatewayImpl;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class ParallelGatewayBehavior extends ParallelGatewayImpl {

	// 非BPMN定义文件

	boolean isDiscriminator = false;

	Set<String> tokenIds = null;

	int nOutOfM = -1;

	public void execute(ExecutionContext executionContext) {

		if (this.gatewayDirection == null) {
			throw new FixFlowException("并行网关节点没有选择 GatewayDirection 类型,请检查流程定义文件!");
		}

		if (this.gatewayDirection.getName().equals("Converging")) {
			parallelGatewayConverging(executionContext);
		} else {

			// throw new
			// FixFlowException("并行网关节点,含有未知 GatewayDirection 类型,请检查流程定义文件!");
			parallelGatewayDiverging(executionContext);

		}

	}

	private void parallelGatewayDiverging(ExecutionContext executionContext) {
		super.execute(executionContext);
	}

	private void parallelGatewayConverging(ExecutionContext executionContext) {

		TokenEntity token = executionContext.getToken();

		TokenEntity parentToken = token.getParent();
		// 判断是否有父令牌
		if (parentToken != null) {

			// 判断令牌是否需要重新激活
			if (token.isAbleToReactivateParent()) {

				token.setAbleToReactivateParent(false);

				boolean reactivateParent = true;

				if (isDiscriminator) {

					reactivateParent = true;

				} else if (tokenIds != null) {
					reactivateParent = mustParentBeReactivated(parentToken, tokenIds);

				} else if (nOutOfM != -1) {

					int n = 0;

					for (String tokenKey : parentToken.getChildren().keySet()) {
						TokenEntity concurrentToken = parentToken.getChildren().get(tokenKey);
						if (this.equals(concurrentToken.getFlowNode())) {
							n++;
						}
					}

					if (n < nOutOfM) {
						reactivateParent = false;
					}

				} else {

					// 验证节点是否全部结束
					reactivateParent = mustParentBeReactivated(parentToken, parentToken.getChildren().keySet());
				}

				// 判断是否需要把父令牌移动到下一个节点
				if (reactivateParent) {

					for (String tokenKey : parentToken.getChildren().keySet()) {
						TokenEntity nowToken = parentToken.getChildren().get(tokenKey);
						nowToken.setAbleToReactivateParent(false);
						nowToken.setEndTime(new Date());
					}

					ExecutionContext parentContext = ProcessObjectFactory.FACTORYINSTANCE.createExecutionContext(parentToken);

					leave(parentContext);
				}

			}

		} else {
			// 没有父令牌则直接离开
			leave(executionContext);
		}

	}

	public boolean mustParentBeReactivated(TokenEntity parentToken, Set<String> childTokenId) {
		boolean reactivateParent = true;

		for (String childTokenIdString : childTokenId) {
			if (reactivateParent) {
				String concurrentTokenId = childTokenIdString;

				TokenEntity concurrentToken = parentToken.getChildren().get(concurrentTokenId);

				if (concurrentToken.isAbleToReactivateParent()) {

					reactivateParent = false;
				}
			}
		}

		return reactivateParent;
	}

	public Set<String> getTokenIds() {
		return this.tokenIds;
	}

	public void setTokenNames(Set<String> tokenIds) {
		this.tokenIds = tokenIds;
	}

	public boolean isDiscriminator() {
		return isDiscriminator;
	}

	public void setDiscriminator(boolean isDiscriminator) {
		this.isDiscriminator = isDiscriminator;
	}

	public int getNOutOfM() {
		return nOutOfM;
	}

	public void setNOutOfM(int nOutOfM) {
		this.nOutOfM = nOutOfM;
	}

}
