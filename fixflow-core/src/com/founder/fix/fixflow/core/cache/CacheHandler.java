package com.founder.fix.fixflow.core.cache;

public interface CacheHandler {
	
	boolean getIsCopy();
	
	void putCacheData(String cacheKey, Object obj);
	
	void removeCacheData(String cacheKey);
	
	Object getCacheData(String cacheKey);
	
	void cleanCacheData();
	

}
