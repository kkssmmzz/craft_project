<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <h3>게시판</h3>
  <table width="700">
   <tr>
    <td align="right">
     <a href="${ctxpath}/craft_board/writeForm.do">글쓰기</a>&nbsp;
     (전체 : ${count} 개)
    </td>
   </tr>
  </table>
  
  <c:if test="${count==0}">
   <table width="700" height="150" id="con" class="top" cellpadding="4" bgcolor="#f4f4f4">
    <tr>
     <td align="center">
     게시판에 저장된 글이 없습니다.
     </td>
    </tr>
   </table>
   <br>
  </c:if>
  
  <c:if test="${count>0}">
   <table width="700" id="con" class="top" cellpadding="4">
    <tr bgcolor="ivory" class="bot">
     <td id="mid" bgcolor="#f4f4f4" width="50">글번호</td>
     <td id="mid" bgcolor="#f4f4f4" width="300">글제목</td>
     <td id="mid" bgcolor="#f4f4f4" width="100">작성자</td>
     <td id="mid" bgcolor="#f4f4f4" width="100">작성일</td>
     <td id="mid" bgcolor="#f4f4f4" width="50">조회수</td>
     <td id="mid" bgcolor="#f4f4f4" width="100">IP</td>
    </tr>
    
    <c:forEach var="dto" items="${boardList}">
     <tr class="bot">
      <td id="mid">
       <c:out value="${number}"/>
       <c:set var="number" value="${number-1}"/>
      </td>
      
      <%-- 글제목 --%>
      <td>
       <!-- 답글 -->
       <c:if test="${dto.craft_re_level>0}">
        <img src="../imgs/level.gif" width="${5*dto.craft_re_level}" height="16"/>
        <img src="../imgs/re.gif"/>
       </c:if>
       
       <!-- 원글 -->
       <c:if test="${dto.craft_re_level==0}">
        <img src="../imgs/level.gif" width="${5*dto.craft_re_level}" height="16">
       </c:if>
       
       <!-- content.do -->
       <a href="${ctxpath}/craft_board/content.do?craft_num=${dto.craft_num}&craft_pageNum=${craft_pageNum}">
        ${dto.craft_subject}
       </a>
       
       <!-- 20번이상 조회하면 hot.gif표시 -->
       <c:if test="${dto.craft_readcount>20}">
        <img src="../imgs/hot.gif" border="0" height="10"/>
       </c:if>
      </td>
      
      <td id="mid">
       ${dto.craft_writer}
      </td>
      
      <!-- 날짜  -->
      <td id="mid">
       ${dto.craft_regdate}
      </td>
      
      <!-- 조회수  -->
      <td id="mid">
       ${dto.craft_readcount}
      </td>
      
      <!-- ip -->
      <td id="mid">
       ${dto.craft_ip}
      </td>
     </tr>
    </c:forEach>
   </table>
  </c:if>
   
<c:if test="${count>0}">
 <table align="center">
  <tr>
   <td>
    <!-- 에러방지 -->
    <c:if test="${endPage>pageCount}">
     <c:set var="endPage" value="${pageCount}"/>
    </c:if>
    
    <!-- 이전블럭  -->
    <c:if test="startPage>10">
     <a href="${ctxpath}/craft_board/list.do?craft_pageNum=${startPage-10}">[이전블럭]</a>
    </c:if>    
    
    <!-- 페이지처리 -->
    <c:forEach var="i" begin="${startPage}" end="${endPage}">
      <a href="${ctxpath}/craft_board/list.do?craft_pageNum=${i}">
       [${i}]
      </a>
    </c:forEach>
    
    <!-- 다음블럭 -->
    <c:if test="${endPage<pageCount}">
     <a href="${ctxpath}/craft_board/list.do?craft_pageNum=${startPage+10}">
     [다음블럭]
     </a>
    </c:if>
   </td>
  </tr>
 </table>
 <br>
</c:if>
</body>
</html>