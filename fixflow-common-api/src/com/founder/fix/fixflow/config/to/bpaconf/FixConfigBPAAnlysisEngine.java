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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * @ClassName: FixConfigBPAAnlysisEngine
 * @Description: TODO
 * @author shao
 *
 */
public class FixConfigBPAAnlysisEngine {
	private String id;
	
	private String engine;
	
	private List<FixConfigBPAWorkspace> workspace;
	
	private FixConfigBPATigger tigger;
	
	private FixConfigBPAOutPutDB outPutDB;
	
	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	@XmlElements(value = { @XmlElement (name="workspace",type=FixConfigBPAWorkspace.class)})
	public List<FixConfigBPAWorkspace> getWorkspace() {
		return workspace;
	}

	public void setWorkspace(List<FixConfigBPAWorkspace> workspace) {
		this.workspace = workspace;
	}

	@XmlElement
	public FixConfigBPATigger getTigger() {
		return tigger;
	}

	public void setTigger(FixConfigBPATigger tigger) {
		this.tigger = tigger;
	}

	@XmlElement
	public FixConfigBPAOutPutDB getOutPutDB() {
		return outPutDB;
	}

	public void setOutPutDB(FixConfigBPAOutPutDB outPutDB) {
		this.outPutDB = outPutDB;
	}
	
	
	
}
