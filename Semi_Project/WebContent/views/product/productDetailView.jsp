<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.product.model.vo.*, com.kh.common.model.vo.*, java.util.ArrayList" %>
<%
	Member m = (Member)request.getAttribute("m");
	Product p = (Product)request.getAttribute("p");
 	ArrayList<Attachment> at = (ArrayList<Attachment>)request.getAttribute("list");
    ArrayList<Product> pList = (ArrayList<Product>)request.getAttribute("pList");
    ArrayList<Attachment> pListAt = (ArrayList<Attachment>)request.getAttribute("pListAt");
    Attachment profile = (Attachment)request.getAttribute("profile");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 조회</title>
<script
        src="https://kit.fontawesome.com/6478f529f2.js"
        crossorigin="anonymous"
></script>
<style>
    /*p_top*/

    #p_top{ text-align : right; height:5%}

    /*p_content*/
    #p_content{height : 600px;}
    #p_content>div{height : 100%;float:left;}

     /*p_content-img*/
    #p_Img{width : 40%;}
    #p_titleImg{ width : 100%;height : 75%;}
    #p_titleImg1{width : 100%;height : 100%;}
    .p_contentImg{ height : 25%; width: 25%; float:left;}
    .p_contentImg1{ height : 100%;width: 100%;}

     /*p_content-table*/
    #p_contentTable{ width : 60%;}
    table{margin: auto; width : 95%; height : 100%; text-align: left; margin-top: 50px;}
    #p_tableCate{font-size : 20px; text-align: left; height: 30px;}
    #p_tableTilte{font-size : 30px; text-align: left; height: 50px;}
    #p_tablePrice{font-size : 40px; text-align: left; height: 80px;}
    #p_tablePrice0{border-bottom: 1px solid grey;}
    #p_tableContent1{font-size : large; width:100px; height:50px; color:rgb(77, 77, 77); font-size:larger}
    .p_tableContent2{font-size : large; width:100px; height:50px; }
    #p_tableBtn{ text-align: center;}
    #p_tableBtn2{ display:inline-block; width:100%; height: 50%; margin: auto;}
    #p_tableBtn2>button{ width:48%; height: 80px; background-color: rgb(185, 228, 245); border:none; font-size: larger; font-weight: 700; margin-top: 20px;}
    #p_tableBtn2>button:hover{ cursor:pointer; opacity: 0.8;}

    /*p_footer*/
    #p_footer{ height : 500px;}
    #p_footer>div{ height:100%;float:left;} 

    /*p_footer1 상품 정보*/
    #p_footer1{ width : 60%;}

    /*p_footer2 판매자 정보*/
    #p_footer2{ width : 40%; height : 100%;}

    #p_footer2_1{ height: 50%;}
    #p_footer2_2{ height : 50%;}

    /* 판매자 정보 */
    #p_seller{ height:40%;}

    /* 판매자 정보 - 프로필 이미지 */
    #p_seller1{ height: 40%; width: 100%;}
    #p_seller1>div{ height: 100%; float:left;}
    #p_sellerImg{ height : 100%; width : 35%;}

    /*판매자 정보 - 회원정보*/
    #p_sellerInfo{ width : 60%; }

    /* 채팅하기, 팔로우 */
    .p_seller2{text-align: center;}
    #p_seller2{ height: 10%; display:inline-block; width:100%; height: 50%;}
    #p_seller2>button{ width : 45%; float: left; background-color: rgb(245, 244, 242); margin-right: 10px; border: none; }
    #p_seller2>button:hover{ cursor:pointer; background-color: rgb(222, 240, 247)}

    /* 판매자 정보 - 판매상품 */
    .saleProduct1{ height:30%;}
    .saleProduct2{ height:70%;}
    .saleProduct2>div{ text-align: center; height:100%; width:33%; float:left;}
    .saleProductImg{ width: 150px; height: 150px; overflow:hidden; }
    .saleProduct2_2:hover{ cursor:pointer; opacity: 0.9; transform: scale(1.1);}

   /*버튼*/
     #p_btn-update{
        height: 30px; 
        width:100px; 
        border-radius: 4px; 
        color: black; 
        background-color: rgb(185, 228, 245);
        border:none;
        overflow:hidden; 
    }
    #p_btn-delete{
        height: 30px; 
        width:100px; 
        border-radius: 4px; 
        background-color: rgb(216, 216, 216); 
        color:black; 
        border:none;
        overflow:hidden; 
    }

</style>
</head>

<body>

	<%@ include file="../common/menubar.jsp" %>
	
    <div class="wrap">
        <% if(loginUser != null && (loginUser.getMemNo() == m.getMemNo())){ %>
            <div id="p_top">
                <button onclick="location.href='<%=contextPath %>/updateForm.po?pno=<%= p.getProductNo() %>'" id="p_btn-update" >수정하기</button>
                <button onclick="location.href='<%=contextPath %>/delete.po?pno=<%= p.getProductNo() %>'" id="p_btn-delete" onclick="return deleteProduct();">삭제하기</a>
            </div>
            <% } %>
        <div id="p_content">
           <div id="p_Img">
                <div id="p_titleImg"><img id="p_titleImg1" src="<%= contextPath %>/<%=at.get(0).getFilePath() %>/<%=at.get(0).getChangeName() %>"></div>
                <% for(int i = 1; i < at.size(); i++) { %>
                    <div class="p_contentImg"><img class="p_contentImg1" src="<%= contextPath %>/<%=at.get(i).getFilePath() %>/<%=at.get(i).getChangeName() %>"></div>
                <% } %>
           </div> 
           <div id="p_contentTable">
            <table>
                <tr>
                    <td colspan="2" id="p_tableCate"><%= p.getProductCate() %></td>
                </tr>
                <tr>
                    <th colspan="2" id="p_tableTilte"><%= p.getProductName() %></th>
                </tr>
                <tr id="p_tablePrice0" >
                    <th colspan="2" id="p_tablePrice"><%= p.getProductPrice() %>원 <br></th>
                </tr>
                <tr>
                    <td colspan="2" id="p_tableContent1" >
                        등록일 : <label> <%= p.getCreateDt() %></label> &nbsp;&nbsp;
                        <span id="p_likes"> <label style="font-size:20px;">♡</label></span>
                        <label id="p_interestCount"></label>&nbsp;&nbsp;
                        <span><i class="fa fa-eye"></i></span> <label> <%= p.getViewCnt() %></label>
                    </td>
                </tr>
                <tr>
                    <th class="p_tableContent2">상품상태</th>
                    <td colspan="2"><%= p.getProductStatus() %></td>
                </tr>
                <tr>
                    <th class="p_tableContent2">배송방법</th>
                    <td colspan="2"><%= p.getProductDelv() %></td>
                </tr>
                <tr>
                    <th class="p_tableContent2">거래지역</th>
                    <td colspan="2"><%= p.getLocation() %></td>
                </tr>
                <tr>
                    <td colspan="2" id="p_tableBtn">
                        <% if(loginUser != null && (m.getMemNo() != loginUser.getMemNo())){%>
                        <div id="p_tableBtn2">
                            <button id="p_interest" onclick="interestUpdate();">찜하기</button>&nbsp;
                            <button id="c_productChat_btn">거래 채팅하기</button>
                        </div>
                        <% } else if(loginUser == null) {%>
                            <div style="background-color: rgb(245, 244, 242); height:100px; width: 100%; line-height:100px;">
                                로그인 한 사용자만 이용할 수 있습니다.
                            </div>

                        <% } %>
                    </td>
                </tr>
            </table>
           </div>
        </div>
        <div>&nbsp;</div>
        <div id="p_footer">
            <div id="p_footer1">
                <br>
                <h3><b>상품 정보</b></h3>
                <hr>
                <p><%= p.getProductDesc() %></p>
                

            </div>
            <div id="p_footer2">
                <div id="p_footer2_1">
                    <div id="p_seller">
                        <br><h3><b>판매자 정보</b></h3><hr>
                    </div>
                    <div id="p_seller1">
                        <div id="p_sellerImg">
							<img src="<%= contextPath %>/<%=profile.getFilePath() %>/<%=profile.getChangeName() %>">
                        </div>
                        <div id="p_sellerInfo">
                            <h4><%= m.getNickname() %></h4>
                            ID : <%=m.getMemId() %> <br>
                            가입일 : <%=m.getEnrollDate() %> <br>
                        </div>
                    </div>

                    <div class="p_seller2">
                        <% if(loginUser != null && (m.getMemNo() != loginUser.getMemNo())){%>
                            <div id="p_seller2">
                                <button id="c_normalChat_btn">채팅하기</button>
                                <button>팔로우</button>
                            </div>
                            <% } %>
                    </div>
                </div>
                <div id="p_footer2_2">
                    <div class="saleProduct1">
                        <br><h5>판매상품 보러가기</h5><hr>
                    </div>
                    <div class="saleProduct2">
                        <% if(pList.size() == 0){ %>
                        	등록된 상품이 없습니다.
                        <% } else if(pList.size() == 1){ %>
                        	<div class="saleProduct2_2" id="p_saleProduct0">
                                <input type="hidden" value="<%= pList.get(0).getProductNo() %>">
                                <img class="saleProductImg" src="<%= contextPath %>/<%=pListAt.get(0).getFilePath() %>/<%=pListAt.get(0).getChangeName() %>"><br>
                                <%= pList.get(0).getProductName() %> 
                            </div>
                        <% } else if(pList.size() == 2) {%>
                        	<% for(int i = 0; i < 2; i++) { %>
	                        	<div class="saleProduct2_2" id="p_saleProduct<%= i %>">
	                  		        <input type="hidden" value="<%= pList.get(i).getProductNo() %>">
                                      <img class="saleProductImg" src="<%= contextPath %>/<%=pListAt.get(i).getFilePath() %>/<%=pListAt.get(i).getChangeName() %>"><br>
                                      <%= pList.get(i).getProductName() %> 
	                  		    </div>
	                  		   <% } %>
                        <% } else {%> 
                        	<% for(int i = 0; i < 3; i++) { %>
                  		    	<div class="saleProduct2_2" id="p_saleProduct<%= i %>">
                  		        	<input type="hidden" value="<%= pList.get(i).getProductNo() %>">
                  		        	<img class="saleProductImg" src="<%= contextPath %>/<%=pListAt.get(i).getFilePath() %>/<%=pListAt.get(i).getChangeName() %>"><br>
                                      <%= pList.get(i).getProductName() %> 
                                </div>
                	    	<% } %>
                	    <% } %>
                </div>
            </div>
        </div>
        
    </div>
    <script>
        interestCount();
        selectInterest();

        function deleteProduct(){

            var deleteProduct = confirm("삭제하시겠습니까?");

            if(deleteProduct == true) {
                alert("삭제되었습니다.");
                return true;
            } else {
                return false;
            }
        }

        
        // loginUser가 찜했는지 판단 0이면 안함/1이면 이미 했음
        function selectInterest(){
            $.ajax({
                    url : 'selectInterest.po',
                    data : {
                        pno : <%= p.getProductNo() %>
                    },
                    success : function(result){
                        if(result > 0){
                        	$('#p_likes').html('<label style="font-size:20px; color:red;">♥</label>');
                        } else{
	                        $('#p_likes').html('<label style="font-size:20px;">♡</label>');
                        	
                        }
                    },
                    error : function(){
                        console.log('실패');
                    }
                })
        }

        function interestUpdate(){
                $.ajax({
                    url : 'interestUpdate.po',
                    data : {
                        pno : <%= p.getProductNo() %>
                    },
                    success : function(result){
                        console.log("update: " + result);
                    	selectInterest();
                    	interestCount();
                    },
                    error : function(){
                        console.log('실패');
                    }
                })
            
        }

        function interestCount(){

                $.ajax({
                    url : 'interestCount.po',
                    data :{
                        pno : <%= p.getProductNo() %>
                    },
                    success : function(count){
                        $('#p_interestCount').html(count);

                    },
                    error : function(){
                        console.log('실패');
                    }
                })
                }
            

        
        $(function(){
            var method = '<%= p.getMethod() %>';
        	$('#p_saleProduct0').click(function(){
                if(method == 'P'){
                    location.href='<%= contextPath %>/detail.po?pno=' + $(this).children().eq(0).val();
                } else {
                    location.href='<%= contextPath %>/detail.ao?pno=' + $(this).children().eq(0).val();
                }
        	});
        	$('#p_saleProduct1').click(function(){
                if(method == 'P'){
                    location.href='<%= contextPath %>/detail.po?pno=' + $(this).children().eq(0).val();
                } else {
                    location.href='<%= contextPath %>/detail.ao?pno=' + $(this).children().eq(0).val();
                }
        	});
        	$('#p_saleProduct2').click(function(){
                if(method == 'P'){
                    location.href='<%= contextPath %>/detail.po?pno=' + $(this).children().eq(0).val();
                } else {
                    location.href='<%= contextPath %>/detail.ao?pno=' + $(this).children().eq(0).val();
                }
        	});
        });
        //----------------------------------------------------------채팅 뿌려줄거---------------------------------------------------------------------
        // 상품 채팅 생성
        <% if(loginUser != null ){ %>
            $('#c_productChat_btn').click(function(){
                $.ajax({
                    url: 'createProductRoom.ch',
                    data : {
                        myMemNo : <%= loginUser.getMemNo() %>,
                        yourMemNo : <%= p.getMemNo() %>,
                        productNo : <%= p.getProductNo() %>
                    },
                    success : function(result){
                        alert(result);
                    }
                });
            });
        <% } %>

        // 일반 채팅 생성
        <% if(loginUser != null ){ %>
            $('#c_normalChat_btn').click(function(){
                $.ajax({
                    url: 'createNormalRoom.ch',
                    data : {
                        myMemNo : <%= loginUser.getMemNo() %>,
                        yourMemNo : <%= p.getMemNo() %>
                    },
                    success : function(result){
                        alert(result);
                    }
                });
            });
        <% } %>
    </script>
	
<br><br><br><br><br>	
</body>
</html>