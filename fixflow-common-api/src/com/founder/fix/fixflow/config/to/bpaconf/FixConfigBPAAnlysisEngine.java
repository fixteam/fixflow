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
package com.founder.fix.fixflow.config.to.bpaconf;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.config.to.bpaconf.base.FixConfigBPATigger;
import com.founder.fix.fixflow.config.to.bpaconf.base.FixConfigUnit;
import com.founder.fix.fixflow.config.to.bpaconf.datafeed.FixConfigDataFeeds;
import com.founder.fix.fixflow.config.to.bpaconf.datapusher.FixConfigBPAMDX;
import com.founder.fix.fixflow.config.to.bpaconf.datapusher.FixConfigDataPusher;
import com.founder.fix.fixflow.config.to.bpaconf.datapusher.FixConfigDataPushers;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

/**
 * @ClassName: FixConfigBPAAnlysisEngine
 * @Description: TODO
 * @author shao
 *
 */
public class FixConfigBPAAnlysisEngine extends FixConfigUnit {

	private FixConfigDataFeeds dataFeeds;
	
	private FixConfigDataPushers dataPushers;
	
	private List<FixConfigBPATigger> tigger;

	public FixConfigDataFeeds getDataFeeds() {
		return dataFeeds;
	}

	public void setDataFeeds(FixConfigDataFeeds dataFeeds) {
		this.dataFeeds = dataFeeds;
	}

	public FixConfigDataPushers getDataPushers() {
		return dataPushers;
	}

	public void setDataPushers(FixConfigDataPushers dataPushers) {
		this.dataPushers = dataPushers;
		List<FixConfigDataPusher> pushers= dataPushers.getDataPusher();
		for(FixConfigDataPusher tmp1:pushers){
			List<FixConfigBPAMDX> mdxTree = new ArrayList<FixConfigBPAMDX>();
			List<FixConfigBPAMDX> lessMdx = new ArrayList<FixConfigBPAMDX>();
			List<FixConfigBPAMDX> tmpList = tmp1.getMdx();
			lessMdx.addAll(tmpList);
			
			for(FixConfigBPAMDX tmp2 : tmpList){
				if(StringUtil.isEmpty(tmp2.getParentId())){
					mdxTree.add(tmp2);
					lessMdx.remove(tmp2);
				}
			}
			
			for(FixConfigBPAMDX tmp2 : mdxTree){
				processMDXTree(tmp2,lessMdx);
			}
			
			tmp1.setMdxTree(mdxTree);
		}
	}
	
	public void processMDXTree(FixConfigBPAMDX node,List<FixConfigBPAMDX> list){
		for(int i=list.size()-1;i>=0;i--){
			FixConfigBPAMDX tmp = list.get(i);
			if(node.getId().equals(tmp.getParentId())){
				node.getRelMDX().add(tmp);
				list.remove(i);
				processMDXTree(tmp,list);
			}
		}
	}

	public List<FixConfigBPATigger> getTigger() {
		return tigger;
	}

	public void setTigger(List<FixConfigBPATigger> tigger) {
		this.tigger = tigger;
	}
}
