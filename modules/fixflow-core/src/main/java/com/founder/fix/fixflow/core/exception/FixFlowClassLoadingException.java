/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author kenshin
 */
package com.founder.fix.fixflow.core.exception;

import com.founder.fix.fixflow.core.internationalization.ExceptionCode;

/**
 *运行时异常，表明所请求的类没有被发现或发生错误
 * 
 * @author Kenshin
 */
public class FixFlowClassLoadingException extends FixFlowException {

  private static final long serialVersionUID = 1L;
  protected String className;
  
  
  /**
   * @param className 未找到的类名或文件名
   * @param cause
   */
  public FixFlowClassLoadingException(String className) {
	  super(ExceptionCode.FIXFLOW_CLASSLOADINGEXCEPTION_DEFAULT,new Object[]{className});
	  this.className = className;
  }
  
  /**
   * 
   * @param exceptionCode 异常key
   * @param className
   */
  public FixFlowClassLoadingException(String exceptionCode,String className) {
	  super(exceptionCode,new Object[]{className});
	  this.className = className;
  }
  
  
  
  /**
   * @param className 未找到的类名或文件名
   * @param cause
   */
  public FixFlowClassLoadingException(String className, Throwable cause) {
	  super(ExceptionCode.FIXFLOW_CLASSLOADINGEXCEPTION_DEFAULT,new Object[]{className}, cause);
	  this.className = className;
  }
  
  /**
   * @param exceptionCode 相应的国际化资源key
   * @param className 未找到的类名或文件名
   * @param cause
   */
  public FixFlowClassLoadingException(String exceptionCode ,String className, Throwable cause) {
    super(exceptionCode,new Object[]{className}, cause);
    this.className = className;
  }
  
  /**
   * 返回这个异常涉及到的类的名称。
   */
  public String getClassName() {
    return className;
  }

}
