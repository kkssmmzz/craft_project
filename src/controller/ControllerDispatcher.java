package controller;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;//properties 파일 읽어 오려고
import java.util.*;//Map, HashMap

import command.*;//인터페이스 
//import action.board.*; 
//import action.member.*; 

//클라이언트를 받는 곳이다 
//컨트롤러는 서블릿으로 만든다 
//컨트롤러는 java문법, servlet문법으로 이루어진다.

public class ControllerDispatcher extends HttpServlet {
	private Map map=new HashMap();//변수
	//key(String type) , value(Object type)
	//init() : 초기화 작업
	public void init(ServletConfig config) throws ServletException{
		String path=config.getServletContext().getRealPath("/");
		//System.out.println("realpath:"+path);
		
		String ffile=path+config.getInitParameter("proFile");
		Properties pr=new Properties();//객체생성
		
		FileInputStream f=null;
		try{
			f=new FileInputStream(ffile);//command.properties 읽기
			pr.load(f);
		}catch(Exception ex){
			System.out.println("파일 읽기 예외 :"+ex);
		}finally{
			try{
				if(f!=null){f.close();}
			}catch(Exception ex2){}
		}//finally---
		
		Iterator keyItor=pr.keySet().iterator();
		while(keyItor.hasNext()){//자료가 있는 동안 반복
 			String key=(String)keyItor.next();// /ch19/list.do
 			String className=pr.getProperty(key);// ch19.action.ListAction
			try{
				Class commandClass=Class.forName(className);//해당문자열을 클래스로 만든다
				Object commandObject=commandClass.newInstance();//클래스를 객체생성
				map.put(key, commandObject);///
				
			}catch(Exception ex3){
				System.out.println("proeprty 파일내용을 클래스 객체로 만드는 중 예외 발생 "+ex3);
			}//catch
			
		}//while end---
	}//init() end----------
		
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws IOException,ServletException{
		 
		reqPro(request,response);//메서드 호출
		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws IOException,ServletException{
		 
		reqPro(request,response);//메서드 호출
		
	}
	
	//사용자 정의 메서드
	private void reqPro(HttpServletRequest request,HttpServletResponse response) 
			throws IOException,ServletException{
		
		String view="";//jsp넣을 변수 
		CommandAction commandAction=null;//Action클래스 넣을 변수
		
		try{
			String uri=request.getRequestURI();//  /프로젝트명/ch19/list.do
		 	if(uri.indexOf(request.getContextPath())==0){
				uri=uri.substring(request.getContextPath().length());
				 
			}//if
			
			commandAction=(CommandAction)map.get(uri);//  /ch19/list.do
			view=commandAction.requestPro(request, response);//메서드 호출 ,view 얻는다
			             //객체.메서드()
		}catch(Throwable ex){
			throw new ServletException(ex);
		}//catch
		
		request.setAttribute("CONTENT", view);
		
		RequestDispatcher rd=request.getRequestDispatcher("/template/template.jsp");
		rd.forward(request, response);//jsp로 포워딩
	}//reqPro() end
}//class end