<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>    
<%-- updatePro.jsp --%>
  <c:if test="${check==1}">
    <script>alert("게시글이 수정되었습니다.")</script>
    <Meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_board_noti/list.do?pageNum=${pageNum}">
  </c:if>
  
  <c:if test="${check==0}">
    <script>
    alert("암호가 올바르지 않습니다.");
    history.back();
    </script>
  </c:if>