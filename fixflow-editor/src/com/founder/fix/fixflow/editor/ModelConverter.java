package com.founder.fix.fixflow.editor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.node.ObjectNode;
import com.founder.fix.fixflow.bpmn.converter.FixFlowConverter;

/**
 * Servlet implementation class ModelConverter
 */
public class ModelConverter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static int BUFFER_SIZE = 4096;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModelConverter() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("process_testych.bpmn");
		ObjectNode o = new FixFlowConverter().convertBpmn2Json("process_testych", input);
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
        try 
        {
            out.print(o);
        }catch(Exception ex){
        	ex.printStackTrace();
        }finally{
        	out.flush();
        	out.close();
        }
	}
}
