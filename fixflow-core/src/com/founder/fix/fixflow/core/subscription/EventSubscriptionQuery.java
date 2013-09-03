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
package com.founder.fix.fixflow.core.subscription;

import com.founder.fix.fixflow.core.query.Query;

public interface EventSubscriptionQuery extends Query<EventSubscriptionQuery, EventSubscription>{
	
	
	EventSubscriptionQuery subscriptionId(String subscriptionId);

	EventSubscriptionQuery processDefinitionId(String processDefinitionId);

	EventSubscriptionQuery tokenId(String tokenId);

	EventSubscriptionQuery subscriptionType(EventSubscriptionType subscriptionType);

	EventSubscriptionQuery messageId(String messageId);

	EventSubscriptionQuery signalId(String signalId);

}
