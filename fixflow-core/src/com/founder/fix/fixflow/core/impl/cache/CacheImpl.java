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
