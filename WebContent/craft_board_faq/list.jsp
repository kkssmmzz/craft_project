<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/module/header.jsp" %>

<fmt:requestEncoding value="UTF-8"/>

<%-- list.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list.jsp</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
  <br>
  <h3 align="center">자주묻는 질문(FAQ)</h3>
  <table width="700">
  
   <tr>
    <td align="right">  
    <c:if test="${sessionScope.memId=='admin'}">
     <a href="${ctxpath}/craft_board_faq/writeForm.do">글쓰기</a>&nbsp;
     </c:if>
     (전체 : ${faq_count} 개)
    </td>
   </tr>
   
  </table>
  
  <c:if test="${faq_count==0}">
   <table width="700" height="150" id="con" class="top" cellpadding="4" bgcolor="#f4f4f4">
    <tr>
     <td align="center">
     게시판에 저장된 글이 없습니다.
     </td>
    </tr>
   </table>
   <br>
  </c:if>



<c:if test="${faq_count>0}">
   <table width="700" id="con" class="top" cellpadding="4">
    <tr bgcolor="ivory" class="bot">
     <td id="mid" bgcolor="#f4f4f4" width="50">번호</td>
     <td id="mid" bgcolor="#f4f4f4" width="400">제목</td>
     <td id="mid" bgcolor="#f4f4f4" width="100">작성자</td>
     <td id="mid" bgcolor="#f4f4f4" width="150">작성일</td>
     <td id="mid" bgcolor="#f4f4f4" width="50">조회수</td>
     
    </tr>
    
    <c:forEach var="dto" items="${faq_boardList}">
     <tr class="bot">
      <td id="mid">
       <c:out value="${faq_number}"/>
       <c:set var="faq_number" value="${faq_number-1}"/>
      </td>
      
      <%-- 제목 --%>
      <td id="mid">

       <!-- 원글 -->
       <c:if test="${dto.faq_re_level==0}">
        <img src="../imgs/level.gif" width="${5*dto.faq_re_level}" height="16">
       </c:if>
       
       <!-- content.do -->
       <a href="${ctxpath}/craft_board_faq/content.do?faq_num=${dto.faq_num}&faq_pageNum=${faq_pageNum}">
        ${dto.faq_subject}
       </a>
       
       <!-- 20번이상 조회하면 hot.gif표시 -->
       <c:if test="${dto.faq_readcount>20}">
        <img src="../imgs/hot.gif" border="0" height="10"/>
       </c:if>
      </td>
      
      <!-- 글쓴이에게 메일 보내기 -->
      <td id="mid">
       <a href="mailto:hong@naver.com">${dto.faq_writer}</a>
      </td>
      
      <!-- 날짜  ${dto.faq_regdate} --> 
      <td id="mid">
       ${dto.faq_regdate}
      </td>
     
      
      <!-- 조회수  -->
      <td id="mid">
       ${dto.faq_readcount}
      </td>
      
      
     </tr>
    </c:forEach>
   </table>
  </c:if>

   
<c:if test="${faq_count>0}">
 <table align="center">
  <tr>
   <td>
     <br>
    <!-- 에러방지 -->
    <c:if test="${faq_endPage>faq_pageCount}">
     <c:set var="faq_endPage" value="${faq_pageCount}"/>
    </c:if>
    
    <!-- 이전블럭  -->
    <c:if test="startPage>10">
     <a href="${ctxpath}/craft_board_faq/list.do?faq_pageNum=${faq_startPage-10}">[이전블럭]</a>
    </c:if>    
    
    <!-- 페이지처리 -->
    <c:forEach var="faq_i" begin="${faq_startPage}" end="${faq_endPage}">
      <a href="${ctxpath}/craft_board_faq/list.do?faq_pageNum=${faq_i}">
       [${faq_i}]
      </a>
    </c:forEach>
    
    <!-- 다음블럭 -->
    <c:if test="${faq_endPage<faq_pageCount}">
     <a href="${ctxpath}/craft_board_faq/list.do?faq_pagNum=${faq_startPage+10}">
     [다음블럭]
     </a>
    </c:if>
   </td>
  </tr>
 </table>
</c:if>
<br>
</body>
</html>