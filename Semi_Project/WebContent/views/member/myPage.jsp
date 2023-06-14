<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kh.product.model.vo.Product, com.kh.product.model.vo.Product, java.util.ArrayList" %>
    
    <%
       Member m = (Member)request.getAttribute("loginUser");
    ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
    .outer-left{
        float: left;
        border: 1px solid black;
        height: 900px;
        width: 250px;
        margin-top: 10px;
        margin-left: 100px;
        text-align: center;
        
        
    }
    .outer-left a{
        color: black;
        text-decoration:none;
       
    }
    .outer-top{
        border:1px solid black;
        width: 1300px;
        height: 300px;
        float:left;
        margin-top: 50px;
        margin-left: 100px;

    }
    .outer-top-form{
        width: 100%;
        height: 100%;
    }
    
    .outer-mid1{
        border : 1px solid black;
        margin-top: 70px;
        margin-left: 100px;
        float: left;
        width: 1300px;
       

    }
    .outer-mid2{
        border : 1px solid black;
        margin-top: 70px;
        margin-left: 100px;
        float: left;
        width: 1300px;
        height: 400px;

    }
    .outer-mid3{
        border : 1px solid black;
        margin-top: 70px;
        margin-left: 450px;
        float: left;
        width: 1300px;
        height: 400px;

    }
    #profile{
        margin-left: 15px;
        text-align: center;
        color: black;
        border-radius:80px;
        border:none;
        
    }
    #verticalLine1{
        width: 100%;
        height: 100%;
     
    }
    #verticalLine2{
        width: 100%;
        height: 100%;
    }
    #like{
        width: 100%;
        text-align: center;
        text-decoration: none;
        color: black;

       
    }
    #following{
        width: 100%;
        text-align: center;
        text-decoration: none;
        color: black;
    }
    .deallist{
        width: 80%;
    }
    #detailProduct{
        color: black;
    }
    
</style>
</head>
<body>
 <%@ include file="../common/menubar.jsp" %>
 
    <!-- 마이페이지 메뉴바 -->
    <div class="outer-left">
        <hr>
        <br>
        <h2 style="text-align: center;"><b> 마이페이지</b></h2><br>
           
        <hr>
        <h4><b> 내 정보</b></h4>
        <br><br>
        <h5 ><a href="<%= contextPath %>/memberModify.me?memNo=<%=loginUser.getMemNo() %>">회원 정보 수정</a></h5><br>
        <h5><a href="<%= contextPath %>/myBoard.me?memNo=<%= loginUser.getMemNo() %>">내 게시글 조회</a></h5><br>
        <h5><a href="<%= contextPath %>/myEnrollProduct.me?memNo=<%= loginUser.getMemNo() %>">내 등록상품 조회</a></h5><br>
        <h5><a href="<%= contextPath %>/myReview.me?memNo=<%= loginUser.getMemNo() %>">내 리뷰 조회</a></h5><br>
        <h5><a href="<%=contextPath%>/inquiryList.no?cpage=1">내 문의내역 조회</a></h5><br>
        <h5><a href="<%= contextPath %>/interest.me?memNo=<%= loginUser.getMemNo() %>">내 관심 상품</a></h5><br>
        <h5><a href="<%= contextPath %>/following.me?memNo=<%= loginUser.getMemNo() %>">팔로잉 목록</a></h5><br>
        <br><br><br><br>
        <h4><b>쇼핑 정보</b></h4>
        <br><br>
        <h5><a href="<%= contextPath %>/myDeal.me?memNo=<%= loginUser.getMemNo() %>">내 거래내역 조회</a></h5><br>
    
    </div> 
    <!--내 프로필/정보/팔로잉 등-->
    <div class="outer-top">
        <table class="outer-top-form">


            <tr >
                <% if(loginUser.getFileNo() != 0 ) { %>
                <td width="10%"><img src="<%= loginUser.getFileNo() %>" alt="프로필" id="profile" style="width: 100px; height: 100px;"></td>
                <% } else {  %>
                <td width="10%"><img src="https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male4-256.png" alt="프로필" id="profile" style="width: 100px; height: 100px;"></td>
                <% } %>
                <td width="20%"><br><br><br><b><h2 id="profile"><%= loginUser.getMemId() %>님 </h2></b><a href="<%=contextPath%>/memberModify.me?memNo=<%= loginUser.getMemNo() %>" id="profile"><h5 id="profile" style="color: gray; text-decoration-line: underline;">회원 정보 수정</h5></a></td>
                 
                <td width="5%"> <img src="https://cdn1.iconfinder.com/data/icons/radix/15/divider-vertical-256.png" alt="" id="verticalLine1"></td>
                <td width="10%"><img src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-ios7-heart-256.png" alt="관심상품" id="like" style="width: 100px; height: 100px; margin-left: 20px;"></td>
                <td width="20%"><a href="<%= contextPath %>/interest.me?memNo=<%= loginUser.getMemNo() %>"><h3 id="like">관심상품</h3></a></td>
                <td width="5%"><img src="https://cdn1.iconfinder.com/data/icons/radix/15/divider-vertical-256.png" alt="" id="verticalLine2" ></td>
                <td width="10%"><img src="https://cdn1.iconfinder.com/data/icons/commonmat/24/adduser-256.png" alt="팔로잉"id="following" style="width: 100px; height: 100px; margin-left: 20px;"></td>
                <td width="20%"><a href="<%= contextPath %>/following.me?memNo=<%= loginUser.getMemNo() %>"><h3 id="following">팔로잉</h3></a></td>
            </tr>

            <!--
            <a href=""><h3 id="like">관심상품</h3></a>
            
            <img src="https://cdn1.iconfinder.com/data/icons/commonmat/24/adduser-256.png" alt="팔로잉" style="width: 100px; height: 100px; margin-left: 20px;">
            -->
        </table>
    </div>
    <div class="outer-mid1">
        <h2 style="margin-top: 50px; margin-left: 50px;"><b>내 등록 상품</b></h2>
        <h5 style="float: right; margin-right: 50px; "><a href="<%= contextPath %>/myEnrollProduct.me?memNo=<%= loginUser.getMemNo() %> " style=" color: gray;">더보기</a> </h5>
     <br><br>
        <hr style="border: 1px solid gray; ">   
        
        <% if(list.isEmpty()) { %>
            <tr>
               <td colspan="5"><h3 style="text-align: center;">등록상품이 없습니다.</h3> </td>
            </tr>
        <% } else { %>
         <% for(int i=0; i<3; i++) { %>
           
             <div >
            <table class="deallist" border="1" align="center">
               
                    <tr>
                        <td rowspan="5" width="30%"><a href="<%= contextPath %>/detail.po?productNo=<%=list.get(i).getProductNo() %> " id="detailProduct"><img src="<%= contextPath %>/<%= list.get(i).getFileNo()%>"></a></td>
                        <input type="hidden" value="<%= list.get(i).getProductNo() %> name="productName">
                        <td  width="50%"><a href="<%= contextPath %>/detail.po?productNo=<%=list.get(i).getProductNo() %>" id="detailProduct"><b><%= list.get(i).getProductName() %></b> </a></td>
                    </tr>
                    <tr>
                        <td><%= list.get(i).getProductPrice() %>원</td>
                    </tr>
                    <tr>
                         <!-- 경매/ 중고거래 구분 (if문)-->
                         <!--  만약 거래가 중고거래라면 -->
                         <% if(list.get(i).getMethod()=="p") { %>
                       <td><div class="methodP">중고거래</div></td>  
                          <% } else {%>
                          <!-- 거래종류가 경매라면 -->
                        <td><div class="methodA">경매</div></td>
                       <% } %>       
                   </tr>
                    <tr>
                        <td><%= list.get(i).getCreateDt() %></td>
                    </tr>
                     <tr>
                           <% if(list.get(i).getProductTradeStatus() == "판매완료") { %>
                          <td><b>판매완료</b></td>
                          <% } %>
                          <td><b>판매중</b></td>
                    </tr>
             
                
            </table>
            <br>
          
             <% } %>
           </div>     
       <%} %>
      
      </div>
    
   

    
          
  
</body>
</html>