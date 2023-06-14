<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.kh.auction.model.vo.*, com.kh.common.model.vo.*, java.util.ArrayList" %>
<%
	Auction a = (Auction)request.getAttribute("a");
 	ArrayList<Attachment> list = (ArrayList)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>경매 낙찰</title>
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
                            <img id="titleimg" width="270" height="270" src="<%= contextPath%>/<%= list.get(0).getFilePath()%>/<%= list.get(0).getChangeName()%>" style="padding-left: 30px;">
                            <td style="width: 700px; height: 100px; color: red; font-weight: bolder; font-size: 30px;" align="center"> 결제가 완료되셨습니다.</td>
                        </th>
                        </tr>
                                <td align="center"> 
                                    입금확인후 발송처리 되오며, 자세한 내역은 주문<br>
                                    내역과 고객센터를 이용해주시기 바랍니다.
                                    <br>
                                </td>
                        <tr>       
                        </tr>

                        <tr>
                            
                            <td style="width: 700px; height: 100px; line-height: 50px; padding-left: 20px; padding-bottom: 100px;" rowspan="7" >
                                
                               
                               
                                상품명 : <%= a.getProductName()%><br>
                                판매자 : <%= a.getNickName()%><br>
                                낙찰액 : <%= a.getPriceNow() %><br>



                                <button onclick="location.href='<%=contextPath%>'" class="btn btn-sm btn-info">메인화면으로 가기</button>
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

</body>
</html>