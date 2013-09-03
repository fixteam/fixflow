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
package com.founder.fix.fixflow.core.impl.cache;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.cache.CacheHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;

public class CacheObject {

	protected Map<String, Object> transientCachedObjects = new HashMap<String, Object>();
	protected CacheHandler cachedObjects;

	public CacheObject(CacheHandler cachedObjects) {
		this.cachedObjects = cachedObjects;
	}

	public Object getCachedObject(String persistentObjectName) {
		Object returnTransientCachedObjects = transientCachedObjects.get(persistentObjectName);
		if (returnTransientCachedObjects == null) {

			if (cachedObjects.getCacheData(persistentObjectName + "_access") != null) {

				if (cachedObjects.getCacheData(persistentObjectName + "_access").toString().equals("use")) {
					throw new FixFlowException("当前流程实例正在被另一个用户处理中!请稍后再试!");
				}

				if (cachedObjects.getCacheData(persistentObjectName + "_access").toString().equals("dirty")) {
					cachedObjects.putCacheData(persistentObjectName + "_access", "use");
					transientCachedObjects.put(persistentObjectName, null);
					return null;
				}

				if (cachedObjects.getCacheData(persistentObjectName + "_access").toString().equals("security")) {
					cachedObjects.putCacheData(persistentObjectName + "_access", "use");
					Object returnCachedObjects = cachedObjects.getCacheData(persistentObjectName);
					transientCachedObjects.put(persistentObjectName, returnCachedObjects);

					return returnCachedObjects;

				}
			}

			return null;

		} else {
			return returnTransientCachedObjects;
		}
	}

	public Object getTransientCachedObject(String persistentObjectName) {
		if (transientCachedObjects.get(persistentObjectName) != null) {
			return transientCachedObjects.get(persistentObjectName);
		}
		return null;
	}

	public void putTransientCachedObject(String persistentObjectName, Object persistentObject) {
		transientCachedObjects.put(persistentObjectName, persistentObject);
	}

	public void putCachedObject(String persistentObjectName, Object persistentObject) {
		cachedObjects.putCacheData(persistentObjectName, persistentObject);
	}

	public void syncCache() {
		for (String transientCachedObjectsKey : transientCachedObjects.keySet()) {

			cachedObjects.putCacheData(transientCachedObjectsKey, transientCachedObjects.get(transientCachedObjectsKey));
			cachedObjects.putCacheData(transientCachedObjectsKey + "_access", "security");
		}
		transientCachedObjects=new HashMap<String, Object>();
	}

	public void clearCache() {

		if (cachedObjects.getIsCopy()) {
			for (String transientCachedObjectsKey : transientCachedObjects.keySet()) {

				if (cachedObjects.getCacheData(transientCachedObjectsKey) != null) {
					cachedObjects.putCacheData(transientCachedObjectsKey + "_access", "security");
				}
			}
		} else {
			for (String transientCachedObjectsKey : transientCachedObjects.keySet()) {
				if (cachedObjects.getCacheData(transientCachedObjectsKey) != null) {
					cachedObjects.putCacheData(transientCachedObjectsKey + "_access", "dirty");
				}

			}
		}
		transientCachedObjects=new HashMap<String, Object>();

	}

}
