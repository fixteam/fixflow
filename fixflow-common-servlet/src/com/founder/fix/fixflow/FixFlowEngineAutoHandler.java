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
package com.founder.fix.fixflow;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.founder.fix.fixflow.util.CurrentThread;

/**
 * @ClassName: FixFlowEngineAutoHandler
 * @Description: TODO
 * @author shao
 *
 */
public class FixFlowEngineAutoHandler implements Filter {

	/*
	  * <p>Title: destroy</p>
	  * <p>Description: </p>
	  * @see javax.servlet.Filter#destroy()
	  */
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	/*
	  * <p>Title: doFilter</p>
	  * <p>Description: </p>
	  * @param arg0
	  * @param arg1
	  * @param arg2
	  * @throws IOException
	  * @throws ServletException
	  * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	  */
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		CurrentThread.init();
		try{
		arg2.doFilter(arg0, arg1);
		}finally{
			try {
				CurrentThread.clear();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	/*
	  * <p>Title: init</p>
	  * <p>Description: </p>
	  * @param arg0
	  * @throws ServletException
	  * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	  */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根
		
	}

}
