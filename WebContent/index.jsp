<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%-- index.jsp --%>
<%-- web.xml에서 등록된 index.jsp를 만들어 프로젝트자체를 실행시켜도 list.do로 넘어가게 하는것 --%>
<meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_member/main.do">