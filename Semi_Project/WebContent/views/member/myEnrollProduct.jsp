<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kh.product.model.vo.Product, com.kh.common.model.vo.Attachment,  java.util.ArrayList" %>
    <%
    
    Attachment at = (Attachment)request.getAttribute("at");
    ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 등록 상품</title>

<style>
    .outer{
        width: 1100px;
        margin-left:500px;
        margin-top: 500px;
        text-align :center; 
       
    }
    #top{
        width: 800px;
        text-align: center;
        height: 200px;
        margin-left:150px;
        
    }
    #verticalLine{
        width: 100%;
        height: 100%;
    }
    .deallist{
        width: 80%;
        height: 200px;
        text-align: left;
        margin: auto;
        border: 1px solid black;
     
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
    #detailProduct{
       
        color: black;
    }
    #detailProduct a:hover{
        text-decoration-line: underline;
    }

</style>
</head>
<body>

    <%@ include file="../member/myPageBar.jsp" %>

    <div class="outer" align="center">
        <h2><b>내 등록 상품</b> </h2>
        <hr style="border:2px solid black; width:90%">
        
       
        <% if(list.isEmpty()) { %>
		    <tr>
		       <td colspan="5">등록상품이 없습니다.</td>
		    </tr>
        <% } else { %>
 
         <% for(Product p  : list) { %>
        <div >
            <table class="deallist" border="1">
               
                    <tr>
                        <td rowspan="5" width="30%"><a href="<%= contextPath %>/detail.po?productNo=<%=p.getProductNo() %> " id="detailProduct"><img src="<%= contextPath %>/<%= p.getFileNo()%>"></a></td>
                        <input type="hidden" value="<%= p.getProductNo() %> name="productName">
                        <td  width="50%"><a href="<%= contextPath %>/detail.po?productNo=<%=p.getProductNo() %>" id="detailProduct"><b><%= p.getProductName() %></b> </a></td>
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
                     <tr>
                           <% if( p.getProductTradeStatus() == "판매완료") { %>
                          <td><b>판매완료</b></td>
                          <% } %>
                          <td><b>판매중</b></td>
                    </tr>
             
                
            </table>
            <br>
            
             <% } %>
    <%} %>
        </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function  searchenrollproduct() {
                $.datepicker.setDefaults($.datepicker.regional['ko']); 
                $( "#startDate" ).datepicker({
                     changeMonth: true, 
                     changeYear: true,
                     nextText: '다음 달',
                     prevText: '이전 달', 
                     dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                     dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                     monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                     monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                     dateFormat: "yymmdd",
                     maxDate: 0,                       // 선택할수있는 최소날짜, ( 0 : 오늘 이후 날짜 선택 불가)
                     onClose: function( selectedDate ) {    
                          //시작일(startDate) datepicker가 닫힐때
                          //종료일(endDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                         $("#endDate").datepicker( "option", "minDate", selectedDate );
                     }    
     
                });
                $( "#endDate" ).datepicker({
                     changeMonth: true, 
                     changeYear: true,
                     nextText: '다음 달',
                     prevText: '이전 달', 
                     dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                     dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                     monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                     monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                     dateFormat: "yymmdd",
                     maxDate: 0,                       // 선택할수있는 최대날짜, ( 0 : 오늘 이후 날짜 선택 불가)
                     onClose: function( selectedDate ) {    
                         // 종료일(endDate) datepicker가 닫힐때
                         // 시작일(startDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 시작일로 지정
                         $("#startDate").datepicker( "option", "maxDate", selectedDate );
                     }    
     
                });    
        });
    </script>
    
    




    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>