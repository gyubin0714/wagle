<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import="com.kh.product.model.vo.Product, java.util.ArrayList" %>
    <%
    ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 거래 내역</title>
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
        text-align: center;
        font-size: medium;

    }
    .methodA{
        width:50px;
        height: 30px;
        background-color:rgb(9, 122, 9);
        color: white;
        border: none;
        border-radius: 25%;
        text-align: center;
        font-size: medium;

    }
</style>
</head>
<body>
 <%@ include file="../member/myPageBar.jsp" %>

 <div class="outer" align="center">
    <h2><b>거래내역</b></h2>
    
    <hr style="border: 2px solid black;">
    
    <% if(list.isEmpty()) { %>
    <tr>
       <td colspan="5">거래 내역이 없습니다. </td>
    </tr>
    
    <% } else { %>
    
    <% for(Product p  : list) { %>
     <input type="hidden" value="<%= p.getProductNo() %>" name="productNo">
     
    <table border="1" style="width: 80%; height: 200px; border: 2px solid black;">
        <tr>
            <td rowspan="5" width="30%"><img src="<%= p.getFileNo() %>"></td>
            <td  width="50%"><b><%= p.getProductName() %></b> </td>
        </tr>
        <tr>
            <td><%= p.getProductPrice() %>원</td>
        </tr>
        <tr>
             <!-- 경매/ 중고거래 구분 (if문)-->
             <!--  만약 거래가 중고거래라면 -->
             <% if(p.getMethod()=="p") { %>
            <td><div class="methodP">중고거래</div></td>  
              <% } else {%>
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
    <%} %>
    <br><br>
 </div>
 <script>
    // 버튼을 누르면 거래확정 버튼으로 변경하고 데이터 전송 -> 판매자에게 리뷰 작성가능한 폼 전달
    document.getElementById('dealfix').onclick = function(e){
        window.event.target.style.background = 'gray';
        e.target.innerHTML = '거래 확정';
        this.style.color = 'white';

    }
   
    
 </script>
    

</body>
</html>