/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author shao
 */
package com.founder.fix.fixflow.config.to.bpaconf.datafeed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.founder.fix.fixflow.config.to.bpaconf.base.FixConfigUnit;

/**
 * @ClassName: FixConfigDataFeed
 * @Description: TODO
 * @author shao
 *
 */
public class FixConfigDataFeeds extends FixConfigUnit {
	private List<FixConfigDataFeed> dataFeed;
	
	private Map<String,FixConfigDataFeed> mapDataFeed = new HashMap<String,FixConfigDataFeed>();
	
	@XmlTransient
	public Map<String, FixConfigDataFeed> getMapDataFeed() {
		return mapDataFeed;
	}

	public void setMapDataFeed(Map<String, FixConfigDataFeed> mapItem) {
		this.mapDataFeed = mapItem;
	}
	
	@XmlTransient
	public FixConfigDataFeed getDataFeed(String key){
		return mapDataFeed.get(key);
	}

	public List<FixConfigDataFeed> getDataFeed() {
		return dataFeed;
	}

	public void setDataFeed(List<FixConfigDataFeed> dataFeed) {
		this.dataFeed = dataFeed;
		for(FixConfigDataFeed tmp:dataFeed){
			mapDataFeed.put(tmp.getId(), tmp);
		}
	}
}
