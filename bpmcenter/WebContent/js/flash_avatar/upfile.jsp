<%@page import="java.io.*,sun.misc.*"%>
<%@page import="com.founder.fix.fixflow.core.impl.util.StringUtil"%>
<%

String pic=request.getParameter("pic");
String pic1=request.getParameter("pic1");
String pic2=request.getParameter("pic2");
String pic3=request.getParameter("pic3");

String path = request.getSession().getServletContext().getRealPath("/");


String userId = StringUtil.getString(request.getSession()
		.getAttribute("LOGIN_USER_ID"));
path = path+"/icon/";

if(!pic.equals("")&&pic!=null){
	//原图
	File file = new File(path+userId+"_src.png");
	FileOutputStream fout = null;
	fout = new FileOutputStream(file);
	try{
		fout.write(new BASE64Decoder().decodeBuffer(pic));
	}finally{
		fout.close();
	}
}

//图1
File file1 = new File(path+userId+".png");
FileOutputStream fout1 = null;
fout1 = new FileOutputStream(file1);
try{
	fout1.write(new BASE64Decoder().decodeBuffer(pic1));
}finally{
	fout1.close();
}
//图2
File file2 = new File(path+userId+"_small.png");
FileOutputStream fout2 = null;
fout2 = new FileOutputStream(file2);
try{
	fout2.write(new BASE64Decoder().decodeBuffer(pic2));
}finally{
	fout2.close();
}
//图3
File file3 = new File(path+userId+"_smallest.png");
FileOutputStream fout3 = null;
fout3 = new FileOutputStream(file3);
try{
	fout3.write(new BASE64Decoder().decodeBuffer(pic3));
}finally{
	fout3.close();
}
out.println("{\"status\":1}");
%>