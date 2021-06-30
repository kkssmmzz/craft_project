<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%-- template.jsp   Layout작업을 함 --%>
<html>
 <body>
  <table border="0" width="100%" cellspacing="0" cellpadding="0">
   <tr height="40" valign="bottom">
    <td align="center">
     <jsp:include page="/module/top.jsp" flush="false" />
    </td>
   </tr>
   
   <tr>
    <td width="80%" valign="top" cellpadding="0">
     <jsp:include page="${CONTENT}" flush="false" />
    </td>
   </tr>
   
   <tr height="10">
    <td align="center">
     <jsp:include page="/module/bottom.jsp" flush="false" />
    </td>
   </tr>   
  </table>
 </body>
</html>
