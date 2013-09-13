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
package com.founder.fix.fixflow.config;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.founder.fix.fixflow.config.to.FixConfigTo;

/**
 * @ClassName: FixConfigUtil
 * @Description: TODO
 * @author shao
 *
 */
@Scope("singleton")
@Lazy(false)
@Service
public class FixConfigUtil {
	
	private static final String FILE="FixConfig.xml";
	private FixConfigTo fixConfigTo = null;
	
	private static FixConfigUtil thisBean;

	
	@PostConstruct
	public void initFixConfig() throws JAXBException, IOException{
		InputStream is = FixConfigUtil.class.getClassLoader().getResourceAsStream(FILE);
		
		JAXBContext context = JAXBContext.newInstance(FixConfigTo.class);
		// 下面代码演示将上面生成的xml转换为对象
		Unmarshaller um = context.createUnmarshaller();
		FixConfigTo p2 = (FixConfigTo) um.unmarshal(is);
		this.fixConfigTo = p2;
		thisBean = this;
	}
	
	public static String getFixItem(String key){
		return thisBean.fixConfigTo.getItems().getMapItem(key).getValue();
	}

}
