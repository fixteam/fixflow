package com.founder.fix.fixflow.editor.service;

import java.io.IOException;

import javax.servlet.ServletException;

public interface WebModelService {

	public void loadBPMNJson() throws ServletException, IOException;
	
	public void modelSave() throws ServletException, IOException;
	
	public void reTryModelInfo() throws ServletException, IOException;
	
}
