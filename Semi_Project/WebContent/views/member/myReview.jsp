<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kh.product.model.vo.Product, java.util.ArrayList, com.kh.review.model.vo.Review" %>
    <%
    
       ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");

       ArrayList<Review> reviewList = (ArrayList<Review>)request.getAttribute("reviewList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 리뷰</title>
<style>
    .outer{
        width: 1100px;
        margin-left: 500px;
        margin-top: 450px;
  

    }
    .methodP{
        width:50px;
        height: 30px;
        background-color: orange;
        color: white;
        border: none;
        border-radius: 25%;

    }
    .methodA{
        width:50px;
        height: 30px;
        background-color:rgb(9, 122, 9);
        color: white;
        border: none;
        border-radius: 25%;

    }
</style>
</head>
<body>
 <%@ include file="../member/myPageBar.jsp" %>

  <div class="outer" align="center">
    <h2><b>내 리뷰 조회</b></h2>
    <hr width="70%" style="border: 1px solid black;">
    <h4>[ 작성가능한 리뷰  ]</h4>
    
    <% if(list.isEmpty()) { %>
    <tr>
         <td colspan="4">작성가능한 상품거래 리뷰가 없습니다. </td>
    </tr>
    
    <% } else {  %>
    
    <% for(Product p:list) { %>
 
    <table border="1" style="width: 80%; height: 200px; border: 2px solid black;">
        <tr>
            <td rowspan="4" width="30%"><img src="<%= contextPath%>/resources/product_upfiles/<%= p.getFileNo() %>"></td>
            <td  width="50%"><b><%= p.getProductName() %></b> </td>
            <td rowspan="4"  width="20%"><a href="<%= contextPath %>/writeReview.me?productNo=<%= p.getProductNo() %>"  class="btn btn-sm btn-info" style="width:100%; height: 40px; font-size: 20px; padding-right: 10px; background-color: rgb(185, 228, 245); color: black; border: none;">리뷰등록하기</a></td>
        </tr>
        <tr>
            <td><%= p.getProductPrice() %>원</td>
        </tr>
        <tr>
           <!-- 경매/ 중고거래 구분 (if문)-->
           <!--  만약 거래가 중고거래라면 -->
             <% if( p.getMethod() == "p") { %>
                <td><div class="methodP">중고거래</div></td>  
                   <%  } else { %>
                   <!-- 거래종류가 경매라면 -->
                 <td><div class="methodA">경매</div></td>
                 <% } %>
        </tr>
        <tr>
            <td><%= p.getCreateDt() %></td>
        </tr>

    </table>
    <br>
       <% } %>
  <% } %>
     

    <hr width="70%" style="border: 1px solid black;">
    <h2><b>전체 리뷰</b></h2>
    <hr width="50%" style="border: 1px solid black;">
    <% if(reviewList.isEmpty()) { %>
        <tr>
             <td colspan="4">작성가능한 상품거래 리뷰가 없습니다. </td>
        </tr>
        
        <% } else {  %>
        
        <% for(Review r : reviewList) { %>
     
        <table border="1" style="width: 80%; height: 200px; border: 2px solid black;">
            <tr>
                <td rowspan="4" width="30%">이미지</td>
                <td  width="50%">상품명</td>
            </tr>
            <tr>
                <td>상품가격</td>
            </tr>
            <tr>
               <!-- 경매/ 중고거래 구분 (if문)-->
               <!--  만약 거래가 중고거래라면 -->
                 <% if( r.getMethod() == "p") { %>
                    <td><div class="methodP">중고거래</div></td>  
                       <%  } else { %>
                       <!-- 거래종류가 경매라면 -->
                     <td><div class="methodA">경매</div></td>
                     <% } %>
            </tr>
            <tr>
                <td>등록일자</td>
            </tr>
    
        </table>
           <% } %>
      <% } %>



   
     <br><br><br><br><br><br><br><br><br><br>

  </div>


  <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>