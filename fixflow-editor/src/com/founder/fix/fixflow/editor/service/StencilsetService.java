package com.founder.fix.fixflow.editor.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StencilsetService {
	public void getStencilsetJson(HttpServletRequest request,HttpServletResponse response) throws IOException;
}
