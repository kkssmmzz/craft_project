<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="craft_member.*"
    %>

<%
String craft_id=request.getParameter("craft_id");
MemberDAO dao=MemberDAO.getInstance();//dao객체얻기
int x=dao.confirmID(craft_id);//dao메서드 호출, id를 인수로 넘긴다.

//x=1;//사용중인 ID
//x=-1;//사용가능한 ID
%>

{"x":<%=x%>}