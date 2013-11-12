package com.founder.fix.fixflow.explorer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * servlet基类
 * 职责：提供公共处理方法
 * 开发者：徐海洋
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public HttpServletRequest requestParm;
	public HttpServletResponse responseParm;
	
	public String getBasePath(){
		return requestParm.getSession().getServletContext().getRealPath("/");
	}
       
    public BaseServlet() {super();}
    
    private HashMap<String,Method> methods = new HashMap<String,Method>();
    
    private Class<?> []types = {};

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       setRequestParm(request);setResponseParm(response);
       String command = request.getParameter("method");
       Method method = null;
       method = getMethod(command);
       Object []args = {};
        try {
			method.invoke(this, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    protected Method getMethod(String name){
       synchronized(methods){
        Method method = (Method)methods.get(name);
        if (method == null){
         try {
          method = this.getClass().getMethod(name, types);
         } catch (Exception e) {
          e.printStackTrace();
         }
         methods.put(name, method);
        }
        return method;
       }
    }
    
	public  String request(String name){
		return requestParm.getParameter(name);
	}
	
	public  String session(String name){
		return requestParm.getSession().getAttribute(name).toString();
	}
	
	public  void error(String desc){
		ResultUtils.getInstance(responseParm).renderJson("{\"state\":\"error\",\"result\":\"" + desc + "\"}");
	}
 
	public  void success(String desc){
		ResultUtils.getInstance(responseParm).renderJson("{\"state\":\"success\",\"result\":\"" + desc + "\"}");
	}
	
	public  String[] requests(String name){
		return requestParm.getParameterValues(name);
	}

	public HttpServletRequest getRequestParm() {
		return requestParm;
	}

	public void setRequestParm(HttpServletRequest requestParm) {
		this.requestParm = requestParm;
	}

	public HttpServletResponse getResponseParm() {
		return responseParm;
	}

	public void setResponseParm(HttpServletResponse responseParm) {
		this.responseParm = responseParm;
	}

}
