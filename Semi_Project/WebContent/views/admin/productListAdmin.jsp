<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.product.model.vo.Product, com.kh.common.model.vo.PageInfo"%>
<%
    ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
	String searchField = (String)request.getAttribute("searchField");
	String searchText = (String)request.getAttribute("searchText");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<style>
    #n_list-area {
        margin-top: 30px;
        width: 1050px;
        border-radius: 5px;
    }

    #n_list-area thead { height: 45px; background-color:rgb(185, 228, 245);}

    #n_list-area tr { font-size: 17px; text-align: center;}

    #n_list-area td { height: 60px;}

    #n_list-area>tbody>tr:hover {
        cursor: pointer;
        background-color: rgb(185, 228, 245);
        color: white;
    }
       
     #n_list-area>tbody>tr:hover {
        cursor: pointer;
        background-color: rgb(179, 221, 238);
        color: white;
    }        

    #scroll_table thead {
        display: block;
        background-color: rgb(179, 221, 238);
    }

    #scroll_table tbody {
        display: block;
        overflow: auto;
        height: 600px;
    }

    #n_search {
        padding-left: 125px;
    }
</style>
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>
    
    <div class="wrap">

        <div id="header1" align="center">
            <%@ include file="../common/csCategory.jsp" %> 
        </div>
        <div id="content">
            <div id="content2">
                <div class="content2_1">
                    <h2><b>상품 조회</h2></b><br>
                </div><hr><br>
                <div id="scroll_table">
                <form action="<%=contextPath%>/searchProduct.ad" method="post" name="search" id="n_search">
                    <select name="searchField">
                        <option value="0">------ 선택 ------</option>
                        <option value="product_no">상품 번호</option>
                        <option value="mem_id">회원 아이디</option>
                        <option value="mem_name">회원 이름</option>
                    </select>
                    <input type="text" name="searchText" placeholder="검색어를 입력해주세요">
                    <button type="submit">검색</button>
                </form>

                <table id="n_list-area" align="center">
                    <thead>
                        <tr>
                            <td width="70px" height="40px" align="center">번호</td>
                            <td width="70px" align="center">경매<td>
                            <td width="240px" align="center">상품 이름</td>
                            <td width="120px" align="center">회원 아이디</td>
                            <td width="140px" align="center">회원 이름</td>
                            <td width="120px" align="center">판매여부</td>
                            <td width="140px" align="center">등록일</td>
                            <td> </td>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <% if(list.isEmpty()) { %> 
                            <!-- 상품이 없을 경우-->
                            <tr>
                                <td colspan="5">상품이 존재하지 않습니다.</td>
                            </tr>

                        <% } else { %> 
                        	<!-- 상품이 있을 경우 -->
								
	                        <% for(int i=0; i<list.size(); i++) { %>
                        		<tr>	
                                   <td width="70px" height="50px" align="center"><%= list.get(i).getProductNo() %></td>                                         
                                   
                                   <!-- 경매인 상품 보여주기 -->
                                   <% if(list.get(i).getAuctionNo() != 0) { %>
                                   		<td width="70px" align="center">경매</td>
                                    <% } else { %>
                                       <td></td>
                                    <% } %>
                                   <td width="240px" align="center"><%= list.get(i).getProductName() %></td>
                                   <td width="120px" align="center"><%= list.get(i).getMemId() %></td>
                                   <td width="140px" align="center"><%= list.get(i).getMemName() %></td>
                                   <td width="120px" align="center"><%= list.get(i).getProductTradeStatus() %></td>
                                   <td width="140px" align="center"><%= list.get(i).getCreateDt() %></td>
                                   <% if(list.get(i).getDeleteYN().equals("N")) { %>
                                       <td style="padding-left: 50px;"><a onclick="return deleteProduct();" href=" <%= contextPath %>/productDelete.ad?pno=<%= list.get(i).getProductNo() %>">삭제</a></td>
                                   <% } else { %>
                                       <td style="padding-left: 50px;"><a onclick="return recoverProduct();" href=" <%= contextPath %>/productRecover.ad?pno=<%= list.get(i).getProductNo() %>">복구</a></td>
                                   <% } %>
                                </tr>
	                       <% } %>
	                 	<% } %>
	                         
                        
                    </tbody>
                </table>
                </div>
                <br><br>

                
            </div>
            
        </div>
    </div>

    <script>

        // 상품 상세보기 
        // $(function() {

        //     $('#n_list-area>tbody>tr').click(function(){
        //         location.href = '<%=contextPath%>/productDetail.po?pno=' + $(this).children().eq(0).text();
        //     })
        // })

        function deleteProduct(){

        var insertInquiry = confirm("삭제하시겠습니까?");

        if(insertInquiry == true) {
            alert("삭제가 완료되었습니다.");
            return true;
        } else {
            alert("삭제가 취소되었습니다.");
            return false;
        }
        }

        function recoverProduct(){

        var insertInquiry = confirm("복구하시겠습니까?");

        if(insertInquiry == true) {
            alert("복구가 완료되었습니다.");
            return true;
        } else {
            alert("복구가 취소되었습니다.");
            return false;
        }
        }
    </script>
    
    


</div>

</body>
</html>