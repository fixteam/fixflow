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
 * @author ych
 * @author kenshin
 */
package com.founder.fix.fixflow.editor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

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
		String aa = "{\"name\":\"aaaa\",\"revision\":1,\"description\":\"aaaa\",\"modelId\":\"1925\",\"model\":{\"resourceId\":\"canvas\",\"properties\":{\"name\":\"\",\"documentation\":\"\",\"process_id\":\"process\",\"process_author\":\"\",\"process_executable\":\"Yes\",\"process_version\":\"\",\"process_namespace\":\"http://www.activiti.org/processdef\",\"executionlisteners\":\"\"},\"stencil\":{\"id\":\"BPMNDiagram\"},\"childShapes\":[{\"resourceId\":\"sid-E7B5E8A3-10E3-4C7B-BA2F-F1E9A39BB36F\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"formproperties\":\"\",\"initiator\":\"\",\"formkeydefinition\":\"\",\"executionlisteners\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-7900C6EB-F006-4331-9641-9D900C4ECFAC\"}],\"bounds\":{\"lowerRight\":{\"x\":218,\"y\":179},\"upperLeft\":{\"x\":188,\"y\":149}},\"dockers\":[]},{\"resourceId\":\"sid-F308B282-D98E-43A1-86DF-3A88337FF034\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"usertaskassignment\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\",\"asynchronousdefinition\":\"No\",\"exclusivedefinition\":\"Yes\",\"looptype\":\"None\",\"multiinstance_sequential\":\"Yes\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-2C12EEE7-805B-4D45-B5EF-16BC555E628C\"}],\"bounds\":{\"lowerRight\":{\"x\":475,\"y\":204},\"upperLeft\":{\"x\":375,\"y\":124}},\"dockers\":[]},{\"resourceId\":\"sid-72CA3B79-3F38-4E86-8FE7-4657B0FCFB46\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":\"\"},\"stencil\":{\"id\":\"EndNoneEvent\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":682,\"y\":135},\"upperLeft\":{\"x\":654,\"y\":107}},\"dockers\":[]},{\"resourceId\":\"sid-7900C6EB-F006-4331-9641-9D900C4ECFAC\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"defaultflow\":\"None\",\"conditionalflow\":\"None\",\"showdiamondmarker\":false},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-F308B282-D98E-43A1-86DF-3A88337FF034\"}],\"bounds\":{\"lowerRight\":{\"x\":374.5703125,\"y\":164},\"upperLeft\":{\"x\":218.7421875,\"y\":164}},\"dockers\":[{\"x\":15,\"y\":15},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-F308B282-D98E-43A1-86DF-3A88337FF034\"}},{\"resourceId\":\"sid-2C12EEE7-805B-4D45-B5EF-16BC555E628C\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"defaultflow\":\"None\",\"conditionalflow\":\"None\",\"showdiamondmarker\":false},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-72CA3B79-3F38-4E86-8FE7-4657B0FCFB46\"}],\"bounds\":{\"lowerRight\":{\"x\":653.7262356390621,\"y\":155.09137734559536},\"upperLeft\":{\"x\":475.34407686093783,\"y\":123.52581015440464}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":14,\"y\":14}],\"target\":{\"resourceId\":\"sid-72CA3B79-3F38-4E86-8FE7-4657B0FCFB46\"}}],\"bounds\":{\"lowerRight\":{\"x\":1485,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"stencilset\":{\"url\":\"../stencilsets/bpmn2.0/bpmn2.0.json\",\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"},\"ssextensions\":[]}}";
		resp.setContentType("application/x-json");   
		resp.getWriter().print(aa);
	}
}
