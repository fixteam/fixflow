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

/**
 *运行时异常，表明所请求的类没有被发现或发生错误
 * 
 * @author Kenshin
 */
public class FixFlowClassLoadingException extends FixFlowException {

  private static final long serialVersionUID = 1L;
  protected String className;
  
  public FixFlowClassLoadingException(String className, Throwable cause) {
    super(getExceptionMessageMessage(className, cause), cause);
    this.className = className;
  }
 
  /**
   * 返回这个异常涉及到的类的名称。
   */
  public String getClassName() {
    return className;
  }
  
  private static String getExceptionMessageMessage(String className, Throwable cause) {
    if(cause instanceof ClassNotFoundException) {
      return "Class not found: " + className;
    } else {
      return "Could not load class: " + className;
    }
  }

}
