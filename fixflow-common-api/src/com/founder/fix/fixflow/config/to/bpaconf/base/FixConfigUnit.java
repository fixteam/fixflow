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
package com.founder.fix.fixflow.config.to.bpaconf.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @ClassName: Item
 * @Description: 基础类
 * @author shao
 *
 */
public class FixConfigUnit {
	
	private String id;

	private List<FixConfigItem> item = new ArrayList<FixConfigItem>();
	
	private Map<String,FixConfigItem> mapItem = new HashMap<String,FixConfigItem>();

	@XmlTransient
	public Map<String, FixConfigItem> getMapItem() {
		return mapItem;
	}

	public void setMapItem(Map<String, FixConfigItem> mapItem) {
		this.mapItem = mapItem;
	}

	public List<FixConfigItem> getItem() {
		return item;
	}

	public void setItem(List<FixConfigItem> item) {
		this.item = item;
		for(FixConfigItem tmp:item){
			mapItem.put(tmp.getKey(), tmp);
		}
	}
	
	public FixConfigItem getMapItem(String key){
		return mapItem.get(key);
	}
	
	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
