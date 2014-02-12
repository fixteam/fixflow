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
package com.founder.fix.fixflow.config.to.bpaconf.datapusher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.founder.fix.fixflow.config.to.bpaconf.base.FixConfigUnit;
import com.founder.fix.fixflow.config.to.bpaconf.datafeed.FixConfigDataFeed;

/**
 * @ClassName: FixConfigDataFeed
 * @Description: TODO
 * @author shao
 *
 */
public class FixConfigDataPushers extends FixConfigUnit {
	private List<FixConfigDataPusher> dataPusher;
	
	private Map<String,FixConfigDataPusher> mapDataPush = new HashMap<String,FixConfigDataPusher>();

	public List<FixConfigDataPusher> getDataPusher() {
		return dataPusher;
	}

	public void setDataPusher(List<FixConfigDataPusher> dataPusher) {
		this.dataPusher = dataPusher;
		for(FixConfigDataPusher tmp:dataPusher){
			mapDataPush.put(tmp.getId(), tmp);
		}
	}
	
	@XmlTransient
	public Map<String, FixConfigDataPusher> getMapDataPush() {
		return mapDataPush;
	}

	public void setMapDataPush(Map<String, FixConfigDataPusher> mapItem) {
		this.mapDataPush = mapItem;
	}
	
	@XmlTransient
	public FixConfigDataPusher getDataPusher(String key){
		return mapDataPush.get(key);
	}
}
