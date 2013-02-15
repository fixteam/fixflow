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
