<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kh.product.model.vo.Product" %>
    <%
     Product p = (Product)request.getAttribute("p");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<style>
    .outer{
        width: 1100px;
        margin-left: 500px;
        margin-top: 450px;
        border: 1px solid black;
        
    }
    #review-form{
        width: 80%;
    }
</style>
</head>
<body>
 <%@ include file="../member/myPageBar.jsp" %>
 <div class="outer" align="center">
    <hr style="border: 2px solid black;">
    <h2><b>리뷰 작성</b></h2>
    <form action="<%=contextPath%>/reviewInsert.me" method="post">
    <input type="hidden" name="productNo" value="<%= p.getProductNo() %>">
    <input type="hidden" name="memNo" value="<%= p.getMemNo() %>">
        <table border="1" id="review-form">
            <tr>
                <th>상품이미지</th>
                <td>이미지</td>
            </tr>
            <tr>
                <th>상품명</th>
                <td><%= p.getProductName() %></td>
            </tr>
            <tr>
                <th>판매자</th>
                <td><%= loginUser.getMemId() %></td>
            </tr>
            <tr>
                <th>거래종류</th>
                <%if(p.getMethod()=="A"){ %>
                <td>경매</td>
                <% }else{ %>
                <td>중고거래</td>
                <% } %>
            </tr>
            <tr>
                <th>리뷰평</th>
                <td >
                <textarea name="content" style="resize:none; width: 100%; height: 100%;border: none;" rows="10" required ></textarea>
                </td>

            </tr>
            <tr>
               <td colspan="2">
                   <button type="submit" class="btn btn-info" style="width: 50%; margin-left: 200px;" >리뷰작성 완료</button>
               </td>
            </tr>
        </table>
    </form>
   

 </div>

</body>
</html>