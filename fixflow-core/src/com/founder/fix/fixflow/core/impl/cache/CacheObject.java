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
