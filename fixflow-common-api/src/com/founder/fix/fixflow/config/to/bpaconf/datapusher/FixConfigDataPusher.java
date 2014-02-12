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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.founder.fix.fixflow.config.to.bpaconf.base.FixConfigProcessor;

/**
 * @ClassName: FixConfigDataFeed
 * @Description: TODO
 * @author shao
 *
 */
public class FixConfigDataPusher extends FixConfigProcessor {
	private List<FixConfigBPAMDX> mdx = new ArrayList<FixConfigBPAMDX>();
	
	private Map<String,FixConfigBPAMDX> mapDataPusher = new HashMap<String,FixConfigBPAMDX>();
	
	private List<FixConfigBPAMDX> mdxTree = new ArrayList<FixConfigBPAMDX>();
	
	public List<FixConfigBPAMDX> getMdxTree() {
		return mdxTree;
	}

	public void setMdxTree(List<FixConfigBPAMDX> mdxTree) {
		this.mdxTree = mdxTree;
	}

	public List<FixConfigBPAMDX> getMdx() {
		return mdx;
	}

	public void setMdx(List<FixConfigBPAMDX> mdx) {
		this.mdx = mdx;
		for(FixConfigBPAMDX tmp:mdx){
			mapDataPusher.put(tmp.getId(), tmp);
		}
	}

	@XmlTransient
	public Map<String, FixConfigBPAMDX> getMapDataPusher() {
		return mapDataPusher;
	}

	public void setMapDataPusher(Map<String, FixConfigBPAMDX> mapItem) {
		this.mapDataPusher = mapItem;
	}
	
	@XmlTransient
	public FixConfigBPAMDX getMdx(String id){
		return mapDataPusher.get(id);
	}
	
}
