<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%-- itemList.jsp --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>itemList.jsp</title>
  <link rel="stylesheet" href="style.css" type="text/css">
  <script type="text/javascript">
  function productDetail(craft_pro_no) {
		document.detail.craft_pro_no.value=craft_pro_no;
		document.detail.submit();
	}
  </script>
</head>
<body>
<c:if test="${empty list}">
  <br>
  <h3>공예품 소개 </h3>
  <br>
  <table width="80%" class="top">
   <tr>
    <td>
      <br>
      <br>
      <h3>등록된 상품이 없습니다</h3>
    </td>
   </tr>
  </table>
  <br>
  <br>
</c:if>

<c:if test="${!empty list}">
  <br>
  <h3>공예품 소개</h3>
  <br>
  <center>현재 ${count}개의 공예품이 전시중에 있습니다</center>
  <br>
  <table width="80%" align="center" class="top">
    <tr>
     <td>
      <c:set var="i" value="${0}"/>
      <table border="0" align="center" cellspacing="20" cellpadding="20">
        <tr>
		  <c:forEach var="dto" items="${list}">
		    <td align="center">
		      <a href="${ctxpath}/craft_item/itemDetail.do?craft_pro_no=${dto.craft_pro_no}&craft_pageNum=${craft_pageNum}">
		       <img src="${ctxpath }/imgs/${dto.craft_image}" width="150" height="150">
		      </a>
			  <br>
		      <font size="2px">
		        ${dto.craft_name }
		      </font>
		      <br>
		      <font size="2px" color="#bfbfbf">
		        ${dto.craft_regdate }
		      </font>
		    </td>
		  <c:set var="i" value="${i+1}"/>
		  <c:if test="${i%4==0}">
		    </tr>
		    <tr>
		  </c:if>
		  </c:forEach>
        </tr>
      </table>
     </td>
    </tr>
  </table>
</c:if>

<c:if test="${!empty list}">
 <table align="center">
  <tr>
   <td>
    <!-- 에러방지 -->
    <c:if test="${endPage>pageCount}">
     <c:set var="endPage" value="${pageCount}"/>
    </c:if>
    
    <!-- 이전블럭  -->
    <c:if test="startPage>10">
     <a href="${ctxpath}/craft_item/itemList.do?craft_pageNum=${startPage-10}">
     [이전블럭]
     </a>
    </c:if>    
    
    <!-- 페이지처리 -->
    <c:forEach var="i" begin="${startPage}" end="${endPage}">
      <a href="${ctxpath}/craft_item/itemList.do?craft_pageNum=${i}">
       [${i}]
      </a>
    </c:forEach>
    
    <!-- 다음블럭 -->
    <c:if test="${endPage<pageCount}">
     <a href="${ctxpath}/craft_item/itemList.do?craft_pageNum=${startPage+10}">
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