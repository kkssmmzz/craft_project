<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<link href="style.css" rel="stylesheet" type="text/css"></style>

	<c:if test="${faq_check==1}">
		<meta http-equiv="Refresh" content="0;url=${ctxpath}/craft_board_faq/list.do?faq_pageNum=${faq_pageNum}" >
	</c:if>
	
	<c:if test="${faq_check==0}">
<br>
   <table width="300" id="con" class="top" cellpadding="4">
	  <tr>
       <td align="center" bgcolor="#f4f4f4">
       <br>
                   암호가 틀렸습니다<br>
         <br>
         <a href="javaScript:history.back()">☞ 돌아가기</a>
	   <br>
         <br>
       </td>
     </tr>
  </table>
  <br>
</c:if>

	
	<%-- <c:if test="${faq_check==2}">
  		<script>
  		alert("암호를 입력하세요.");
  		</script>
  		<Meta http-equiv="Refresh" content="0;url=javascript:history.back()">
	</c:if> --%>
	