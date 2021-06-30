<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%-- itemDetail --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
  <br>
  <table id="con" width="600" class="bot">
    <tr>
      <td>
        <h3>상품 설명</h3>
      </td>
    </tr>
  </table>
  <br>
  <table id="con" width="600">
    <tr>
      <td rowspan="3" align="center" valign="top">
      <img src="${ctxpath}/imgs/${dto.craft_image}" width="150" height="150">
      </td>
      <td bgcolor="#f4f4f4">
     	 상품명 : ${dto.craft_name}
      </td>
    </tr>
    
    <tr>
      <td>
   	    <textarea rows="8" cols="63">${dto.craft_detail}</textarea>
      </td>
    </tr>
    
    <tr>
      <td align="right">
 	    <font size="2px" color="#b6b6b6">등록일자 : ${dto.craft_regdate}</font>
      </td>
    </tr>
    
    <tr>
      <td colspan="2" align="right">
        <br>
        <input type="button" value="목록으로" onClick="location='${ctxpath}/craft_item/itemList.do?craft_pageNum=${craft_pageNum}'">
      </td>
    </tr>
  </table>
  <br>
  <center><font size="2px" color="#b6b6b6">※ 나무네 공방에서는 공방에서 직접 상품을 전시, 판매하고 있습니다.</font></center>
  <br>
</body>
</html>