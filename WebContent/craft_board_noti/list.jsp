<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<fmt:requestEncoding value="UTF-8"/>

<%-- list.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list.jsp</title>
<link rel="stylesheet" type="text/css" href="style.css"></link>
</head>
<body>
 <br>
 <center><h3>공지사항</h3></font></center>
 
  <table width="800" align="center">
   <tr>
    <td align="right">
      <c:if test="${sessionScope.memId=='admin'}">
        <a href="${ctxpath}/craft_board_noti/writeForm.do"><font size=4><b>글쓰기</b></font></a>
      </c:if>
    </td>
   </tr>
  </table>
  
  <c:if test="${noti_count==0}">
   <table id="con" class="top">
    <tr>
     <td align="center">
     게시판에 저장된 글이 없습니다.
     </td>
    </tr>
   </table>
  </c:if>
  
  <c:if test="${noti_count>0}">
   <table id="con" class="top" align="center">
    <tr class="bot">
     <td width="50" align="center" bgcolor="#f4f4f4">글번호</td>
     <td width="300" align="center" bgcolor="#f4f4f4">글제목</td>
     <td width="100" align="center" bgcolor="#f4f4f4">작성자</td>
     <td width="150" align="center" bgcolor="#f4f4f4">작성일</td>
     <td width="50" align="center" bgcolor="#f4f4f4">조회수</td>
     <td width="120" align="center" bgcolor="#f4f4f4">IP</td>
    </tr>
    
    <c:forEach var="dto" items="${boardList}">
     <tr class="bot">
      <td align="center">
       <c:out value="${noti_number}"/>
       <c:set var="noti_number" value="${noti_number-1}"/>
      </td>
      
      <%-- 글제목 --%>
      <td>      
       <!-- content.do -->
       <a href="${ctxpath}/craft_board_noti/content.do?noti_num=${dto.noti_num}&pageNum=${pageNum}">
        &nbsp;&nbsp;${dto.noti_subject}
       </a>
       

      </td>
      
      <!-- 글쓴이에게 메일 보내기 -->
      <td align="center">
       <a href="mailto:admin@naver.com">${dto.noti_writer}</a>
      </td>
      
      <!-- 날짜  -->
      <td align="center">
       ${dto.noti_regdate}
      </td>
      
      <!-- 조회수  -->
      <td align="center">
       ${dto.noti_readcount}
      </td>
      
      <!-- ip -->
      <td align="center">
       ${dto.noti_ip}
      </td>
     </tr>
    </c:forEach>
   </table>
   <br>
  </c:if>

<c:if test="${noti_count>0}">
 <table align="center">
  <tr>
   <td>
    <!-- 에러방지 -->
    <c:if test="${endPage>pageCount}">
     <c:set var="endPage" value="${pageCount}"/>
    </c:if>
    
    <!-- 이전블럭  -->
    <c:if test="startPage>10">
     <a href="${ctxpath}/craft_board_noti/list.do?pageNum=${startPage-10}">[이전블럭]</a>
    </c:if>    
    
    <!-- 페이지처리 -->
    <c:forEach var="i" begin="${startPage}" end="${endPage}">
      <a href="${ctxpath}/craft_board_noti/list.do?pageNum=${i}">
       [${i}]
      </a>
    </c:forEach>
    
    <!-- 다음블럭 -->
    <c:if test="${endPage<pageCount}">
     <a href="${ctxpath}/craft_board_noti/list.do?pagNum=${startPage+10}">
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