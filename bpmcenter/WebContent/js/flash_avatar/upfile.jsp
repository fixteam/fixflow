<%@page import="java.io.*,sun.misc.*"%>
<%
String pic=request.getParameter("pic");
String pic1=request.getParameter("pic1");
String pic2=request.getParameter("pic2");
String pic3=request.getParameter("pic3");

String path = request.getSession().getServletContext().getRealPath("/");


if(!pic.equals("")&&pic!=null){
	//原图
	File file = new File(path+"src.png");
	FileOutputStream fout = null;
	fout = new FileOutputStream(file);
	fout.write(new BASE64Decoder().decodeBuffer(pic));
	fout.close();
}

//图1
File file1 = new File(path+"1.png");
FileOutputStream fout1 = null;
fout1 = new FileOutputStream(file1);
fout1.write(new BASE64Decoder().decodeBuffer(pic1));
fout1.close();

//图2
File file2 = new File(path+"2.png");
FileOutputStream fout2 = null;
fout2 = new FileOutputStream(file2);
fout2.write(new BASE64Decoder().decodeBuffer(pic2));
fout2.close();

//图3
File file3 = new File(path+"3.png");
FileOutputStream fout3 = null;
fout3 = new FileOutputStream(file3);
fout3.write(new BASE64Decoder().decodeBuffer(pic3));
fout3.close();

out.println("{\"status\":1}");
%>