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
package com.founder.fix.fixflow.config.to.bpaconf.datapublisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.config.to.bpaconf.base.FixConfigProcessor;

/**
 * @ClassName: FixConfigDataFeed
 * @Description: TODO
 * @author shao
 *
 */
public class FixConfigDataPublisher extends FixConfigProcessor {
	private List<FixConfigBPAMDX> mdx;
	
	private Map<String,FixConfigBPAMDX> mapItem = new HashMap<String,FixConfigBPAMDX>();

	public List<FixConfigBPAMDX> getMdx() {
		return mdx;
	}

	public void setMdx(List<FixConfigBPAMDX> mdx) {
		this.mdx = mdx;
		for(FixConfigBPAMDX tmp:mdx){
			mapItem.put(tmp.getId(), tmp);
		}
	}

	public Map<String, FixConfigBPAMDX> getMapItem() {
		return mapItem;
	}

	public void setMapItem(Map<String, FixConfigBPAMDX> mapItem) {
		this.mapItem = mapItem;
	}
	
	public FixConfigBPAMDX getMdx(String id){
		return mapItem.get(id);
	}
	
}
