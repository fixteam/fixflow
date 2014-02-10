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

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import com.founder.fix.fixflow.config.to.bpaconf.base.FixConfigBPATigger;
import com.founder.fix.fixflow.config.to.bpaconf.base.FixConfigUnit;
import com.founder.fix.fixflow.config.to.bpaconf.datafeed.FixConfigDataFeeds;
import com.founder.fix.fixflow.config.to.bpaconf.datapublisher.FixConfigDataPublishers;

/**
 * @ClassName: FixConfigBPAAnlysisEngine
 * @Description: TODO
 * @author shao
 *
 */
public class FixConfigBPAAnlysisEngine extends FixConfigUnit {

	private FixConfigDataFeeds dataFeeds;
	
	private FixConfigDataPublishers dataPublishers;
	
	private List<FixConfigBPATigger> tigger;

	public FixConfigDataFeeds getDataFeeds() {
		return dataFeeds;
	}

	public void setDataFeeds(FixConfigDataFeeds dataFeeds) {
		this.dataFeeds = dataFeeds;
	}

	public FixConfigDataPublishers getDataPublishers() {
		return dataPublishers;
	}

	public void setDataPublishers(FixConfigDataPublishers dataPublishers) {
		this.dataPublishers = dataPublishers;
	}

	public List<FixConfigBPATigger> getTigger() {
		return tigger;
	}

	public void setTigger(List<FixConfigBPATigger> tigger) {
		this.tigger = tigger;
	}
}
