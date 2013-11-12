package com.founder.fix.fixflow.editor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StencilsetServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
		StringBuffer document = new StringBuffer();
		resp.setContentType("application/x-json");   
		PrintWriter out = resp.getWriter();
        try 
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stencilsetStream));
            String line = null;
            while ((line = reader.readLine()) != null)
            	document.append(line + " ");
            reader.close();
            out.print(document.toString());
        }catch(Exception ex){
        	ex.printStackTrace();
        }finally{
        	out.flush();
        	out.close();
        }
       
	}
}
