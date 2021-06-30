<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<style type="text/css">
   a:link,a:visited,a:active {
      text-decoration : none;
      color : black;
   }
   a:hover {
      text-decoration : none;
      color : #6996bd;
   }
</style>
<%-- top.jsp --%>
<table width="100%" height="15">
 <c:if test="${empty sessionScope.memId}">
  <tr width="90%">
    <td align="right">
    <font size="2">
	  <a href="${ctxpath}/craft_member/inputForm.do">회원가입</a>&nbsp;&nbsp;|&nbsp;
	  <a href="${ctxpath}/craft_item/itemList.do">공예품소개</a>&nbsp;&nbsp;|&nbsp;
	  <a href="${ctxpath}/craft_road/roadto.do">오시는길</a>
    </font>
    </td>
    <td width="10%"></td>
  </tr>
 </c:if>
  
 <c:if test="${!empty sessionScope.memId}">
  <tr width="90%" height="15">
    <td align="right">
    <font size="2">
      <a href="${ctxpath}/craft_member/logout.do">로그아웃</a>&nbsp;&nbsp;|&nbsp;
	  <a href="${ctxpath}/craft_member/modify.do">회원정보변경</a>&nbsp;&nbsp;|&nbsp;
	  <a href="${ctxpath}/craft_item/itemList.do">공예품소개</a>&nbsp;&nbsp;|&nbsp;
	  <a href="${ctxpath}/craft_road/roadto.do">오시는길</a>
    </font>
    </td>
    <td width="10%"></td>
  </tr>
 </c:if>
</table>
<hr>
<br>

     <a href="${ctxpath}/craft_member/main.do">
      <img src="${ctxpath}/imgs/logo.gif" width="230" height="90">
     </a>

<br>
<br>
<hr>
<table width="90%" height="40">
  <tr valign="center" align="center">
    <td>
    <font size="4">
      <a href="${ctxpath}/craft_board_noti/list.do">공지사항</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="${ctxpath}/craft_item/itemList.do">공예품 소개</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="${ctxpath}/craft_board/list.do">게시판</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="${ctxpath}/craft_board_qna/list.do">Q&A</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="${ctxpath}/craft_board_faq/list.do">자주묻는 질문</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <a href="${ctxpath}/craft_road/roadto.do">오시는길</a>
    </font>
    </td>
  </tr>
</table>
<hr>