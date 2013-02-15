package com.founder.fix.fixflow.core.impl.cache;

import com.founder.fix.fixflow.core.cache.CacheHandler;

public class CacheImpl implements CacheHandler {

	
	
	
	public void putCacheData(String cacheKey, Object obj) {
		Cache.fixCacheData.put(cacheKey,obj);
	}

	public void removeCacheData(String cacheKey) {
		Cache.fixCacheData.remove(cacheKey);
	}

	public Object getCacheData(String cacheKey) {
		return Cache.fixCacheData.get(cacheKey);
	}

	public boolean getIsCopy() {
		// TODO Auto-generated method stub
		return false;
	}

	public void cleanCacheData() {
		// TODO Auto-generated method stub
		Cache.fixCacheData.clear();
	}

}
