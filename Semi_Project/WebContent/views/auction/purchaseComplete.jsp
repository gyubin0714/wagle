<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.product.model.vo.*, com.kh.common.model.vo.*, java.util.ArrayList" %>
<%
    Product p = (Product)request.getAttribute("p");
    ArrayList<Attachment> at = (ArrayList<Attachment>)request.getAttribute("list");
%>    

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>상품 구매 완료</title>
<style>
	body{
        box-sizing: border-box;
    }

	#auction-form{
        border: 1px solid black;
        width: 100%;
    }

</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>

    
	<div class="wrap">
	 <table border="1" id="auction-form">
                <!-- 미리보기 영역 -->
                <tr>
                    <td colspan="4" align="center">
                        <table border="1" style="width:100%; height:900px">
                        <tr>    
                        <th rowspan="6" style="width : 300px; height : 600px;">
                            <img id="titleimg" width="270" height="270" src="<= contextPath>/<= list.get(0).getFilePath()>/<= list.get(0).getChangeName()>" style="padding-left: 30px;">
                            <td style="width: 700px; height: 100px; color: red; font-weight: bolder; font-size: 30px;" align="center"> 구매가 완료되었습니다.</td>
                        </th>
                        </tr>
                                <td align="center"> 
                                   		3일안에 도착되오며, 자세한 내역은 주문<br>
                                    	내역과 고객센터를 이용해주시기 바랍니다.<br>
                                        <div style="font-weight: bolder;">
                                    	*산간 및 도서지역은 도착 시간이 지연될수있습니다.
                                        </div>    
                                </td>
                        <tr>       
                        </tr>

                        <tr>
                            
                            <td style="width: 700px; height: 325px; line-height: 50px; padding-left: 20px; padding-bottom: 100px;" rowspan="7" >
                                
                               
                               <div style="width:677;">
                                상품명 : <%= p.getProductName() %><br>
                                판매자 : <%= p.getNickName() %><br>
                                구매액 : <%= p.getProductPrice() %><br>
                                </div>
                                
                                <button onclick="location.href='<%=contextPath%>'" class="btn btn-sm btn-info">뒤로가기</button>
                                <button class="btn btn-sm btn-warning" onclick="review_write()">리뷰작성</button>
                                <form action="<%= contextPath %>/insertreview.ri" id="review-form" method="post" style="display: none;">
                                <div style="width: 650px; height: 200px;border: 1px solid black;">
                                    <textarea name="review-area" id="review-area" align="left" style="width:100%; height:100%;vertical-align: top; resize: none;" maxlength="1000"></textarea>
                                </div>
                                <div style="text-align:right; padding-right: 30px;">
                                <span id="count">0</span> / 1000    
                                <button type="submit" class="btn btn-sm btn-warning">완료</button>
                                
                                </div>
                                </form>
                                
                            </td>
                            
                        </tr>
                        <tr>
                            
                            
                            
                        </tr>
                        <tr>
                            
                           
                            
                        </tr>
                        <tr>
                            
                            
                            
                        </tr>
                        </table>
                    </td>
                </tr>
                <tr>
	</div>
	<script>
        function review_write(){
            $("#review-form").show();
        }

        $(function(){
            $('#review-area').keyup(function(){
                $('#count').text($(this).val().length);
            })

        })
    </script>
        
        
</body>
</html>