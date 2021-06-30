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
  <h3 align="center">Q&A</h3>
  <table width="700">
   <tr>
    <td align="right">
     <a href="${ctxpath}/craft_board_qna/writeForm.do">글쓰기</a>&nbsp;
     (전체 : ${qna_count} 개)
    </td>
   </tr>
  </table>
  
  <c:if test="${qna_count==0}">
   <table width="700" height="150" id="con" class="top" cellpadding="4" bgcolor="#f4f4f4">
    <tr>
     <td align="center">
     게시판에 저장된 글이 없습니다.
     </td>
    </tr>
   </table>
   <br>
  </c:if>



<c:if test="${qna_count>0}">
   <table width="700" id="con" class="top" cellpadding="4">
    <tr bgcolor="ivory" class="bot">
     <td id="mid" bgcolor="#f4f4f4" width="50">글번호</td>
     <td id="mid" bgcolor="#f4f4f4" width="250">글제목</td>
     <td id="mid" bgcolor="#f4f4f4" width="100">작성자</td>
     <td id="mid" bgcolor="#f4f4f4" width="150">작성일</td>
     <td id="mid" bgcolor="#f4f4f4" width="50">조회수</td>
     <td id="mid" bgcolor="#f4f4f4" width="100">IP</td>
    </tr>
    
    <c:forEach var="dto" items="${qna_boardList}">
     <tr class="bot">
      <td id="mid">
       <c:out value="${qna_number}"/>
       <c:set var="qna_number" value="${qna_number-1}"/>
      </td>
      
      <%-- 글제목 --%>
      <td id="mid">
       <!-- 답글 -->
       <c:if test="${dto.qna_re_level>0}">
        <img src="../imgs/level.gif" width="${5*dto.qna_re_level}" height="16"/>
        <img src="../imgs/re.gif"/>
       </c:if>
       
       <!-- 원글 -->
       <c:if test="${dto.qna_re_level==0}">
        <img src="../imgs/level.gif" width="${5*dto.qna_re_level}" height="16">
       </c:if>
       
       <!-- content.do -->
       <a href="${ctxpath}/craft_board_qna/content.do?qna_num=${dto.qna_num}&qna_pageNum=${qna_pageNum}">
        ${dto.qna_subject}
       </a>
       
       <!-- 20번이상 조회하면 hot.gif표시 -->
       <c:if test="${dto.qna_readcount>20}">
        <img src="../imgs/hot.gif" border="0" height="10"/>
       </c:if>
      </td>
      
      <!-- 글쓴이에게 메일 보내기 -->
      <td id="mid">
       <a href="mailto:hong@naver.com">${dto.qna_writer}</a>
      </td>
      
      <!-- 날짜  ${dto.qna_regdate} --> 
      <td id="mid">
       ${dto.qna_regdate}
      </td>
     
      
      <!-- 조회수  -->
      <td id="mid">
       ${dto.qna_readcount}
      </td>
      
      <!-- ip -->
      <td id="mid">
       ${dto.qna_ip}
      </td>
     </tr>
    </c:forEach>
   </table>
  </c:if>

   
<c:if test="${qna_count>0}">
 <table align="center">
  <tr>
   <td>
    <!-- 에러방지 -->
    <c:if test="${qna_endPage>qna_pageCount}">
     <c:set var="qna_endPage" value="${qna_pageCount}"/>
    </c:if>
    
    <!-- 이전블럭  -->
    <c:if test="startPage>10">
     <a href="${ctxpath}/craft_board_qna/list.do?qna_pageNum=${qna_startPage-10}">[이전블럭]</a>
    </c:if>    
    
    <!-- 페이지처리 -->
    <c:forEach var="qna_i" begin="${qna_startPage}" end="${qna_endPage}">
      <a href="${ctxpath}/craft_board_qna/list.do?qna_pageNum=${qna_i}">
       [${qna_i}]
      </a>
    </c:forEach>
    
    <!-- 다음블럭 -->
    <c:if test="${qna_endPage<qna_pageCount}">
     <a href="${ctxpath}/craft_board_qna/list.do?qna_pagNum=${qna_startPage+10}">
     [다음블럭]
     </a>
    </c:if>
   </td>
  </tr>
 </table>
</c:if>
</body>
</html>