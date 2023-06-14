<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.auction.model.vo.*, com.kh.common.model.vo.*, java.util.ArrayList, com.kh.product.model.vo.Product" %>
<%
    Member m = (Member)request.getAttribute("m");
	Auction a = (Auction)request.getAttribute("a");
 	ArrayList<Attachment> at = (ArrayList<Attachment>)request.getAttribute("list");
    ArrayList<Product> pList = (ArrayList<Product>)request.getAttribute("pList");
    ArrayList<Attachment> pListAt = (ArrayList<Attachment>)request.getAttribute("pListAt");
    Attachment profile = (Attachment)request.getAttribute("profile");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>경매 정보 조회</title>
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
    #p_contentTable>table{margin: auto; width : 95%; height : 100%; text-align: left; margin-top: 50px;}
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

    /* 모달 */
    .body{
        position: relative;
    }
    .a_modal{ 
        width: 600px; 
        height:600px; 
        background: rgb(109, 194, 237); 
        position: fixed;
        z-index: 50;
        display: none;
        margin: 0px auto;
        top: 15%;
        left: 50%;
        transform: translateX( -50% );
    }
</style>


</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
    <div class="a_modal">
        <%@ include file="auctionModalView.jsp" %>
    </div>
    <div class="wrap">
        <% if(loginUser != null ){ %>
            <% if(loginUser.getMemNo() == Integer.parseInt(a.getMemNo())){ %>
                <div id="p_top">
                    <button onclick="location.href='<%=contextPath %>/updateForm.po?pno=<%= a.getProductNo() %>'" id="p_btn-update" >수정하기</button>
                    <button onclick="location.href='<%=contextPath %>/delete.po?pno=<%= a.getProductNo() %>'" id="p_btn-delete" onclick="return deleteProduct();">삭제하기</a>
                </div>
            <% } %>
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
                        <td colspan="2" id="p_tableCate"><%= a.getCateName() %></td>
                    </tr>
                    <tr>
                        <th colspan="2" id="p_tableTilte"><%= a.getProductName() %></th>
                    </tr>
                    <tr id="p_tablePrice0" >
                        <th colspan="2" id="p_tablePrice"><%= a.getProductPrice() %></th>
                    </tr>
                    <tr>
                        <td colspan="2" id="p_tableContent1" >
                        등록일 : <label><%= a.getCreateDt() %></label> &nbsp;
                        입찰자 수 : <label><%= a.getCount() %> </label> &nbsp;
                        <span id="p_likes"><i class="fa fa-heart-o"></i></span>
                        <label id="p_interestCount"></label>&nbsp;&nbsp;
                        <span><i class="fa fa-eye"></i></span> <label> <%= a.getViewCnt() %></label>
                        </td>
                    </tr>
                    <tr>
                        <th class="p_tableContent2">현재입찰가</th>
                        <td colspan="2"><%= a.getPriceNow() %></td>
                    </tr>
                    <tr>
                        <th class="p_tableContent2">종료시간</th>
                        <td colspan="2"><%= a.getEndTime() %></td>
                    </tr>
                    <tr>
                        <th class="p_tableContent2">상품상태</th>
                        <td colspan="2"><%= a.getProductStatus() %></td>
                    </tr>
                    <tr>
                        <td colspan="2" id="p_tableBtn">
                            <div id="p_tableBtn2">
                                <% if(loginUser != null && (m.getMemNo() != loginUser.getMemNo())){%>
                                    <button id="p_interest" onclick="interestUpdate();">찜하기</button>&nbsp;
                                <% } %>
                                    <button id="a_modalbtn">입찰하기</button>
                            </div>
                        </td>
                    </tr>
                </table>
            </div> 
        </div>
        <div id="p_footer">
            <div id="p_footer1">
                <br>
                <h3>상품 정보</h3>
                <hr>
                <p><%= a.getProductDesc() %></p>
                

            </div>
            <div id="p_footer2">
                <div id="p_footer2_1">
                    <div id="p_seller0">
                        <br><h3>판매자 정보</h3><hr>
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
                    <div id="p_seller2">
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

        var loginMemNo = 0;
        <%if(loginUser != null){%>
            loginMemNo = <%= loginUser.getMemNo() %>;
        <%}%>
        var productMemNo = <%= a.getMemNo() %>;

    $(function(){ 
        // 판매완료 상품일 경우
        if(<%= a.getProductTradeStatus().equals("판매완료") %>){
        	alert('판매가 완료된 상품입니다');
        	location.href='<%=contextPath%>/list.au?cpage=1';
        }
        
        // 내 상품의 경우 입찰 금지
        if(loginMemNo == productMemNo){
            $('.a_none_login').attr('disabled',true);
        };

        // 경매창 열기
        $("#a_modalbtn").click(function(){
            $(".a_modal").css('display','block');
        });
        
        // 경매창 닫기
        $("#a_modal_header_2").click(function(){
            $(".a_modal").css('display','none');
            $('.a_recode_modal').css('display','none');
            $('.a_buyAuction_modal').css('display','none');
        });

        setInterval(a_selectAuction,800);
  
    });

    // 경매 정보 불러오는 ajax
    function a_selectAuction(){
        $.ajax({
            url:'modal.au',
            data:{
                auctionNo : <%= a.getAuctionNo() %>
            },
            success:function(result){

                if(result >= 0){
                    alert('판매가 완료된 상품입니다.');
                    location.href='<%=contextPath%>/list.au?cpage=1';
                } else{

                    // 남은시간 : $('#a_limitTime').text('남은시간')
                    // 입찰자수 : $('#a_modal_count').text('입찰자수')
                    // 현재입찰가 : $('#a_nowPrice>div').eq(1).text('현재입찰가')
                    $('#a_limitTime').text(result.endTime);
                    $('#a_modal_count').text(result.count);
                    $('#a_nowPrice').children().eq(1).text(result.priceNow);

                    let num = result.priceNow-(result.priceNow-Math.floor(result.priceNow/1000)*1000)+2000;

                    $('input[name=a_che_priceNow]').val(num);
                }
            }
        });
    }
    // 로그인 상태 확인 후 입찰 및 구매기능 활성화
    $('.a_none_login').click(function(){
        if('<%=loginUser%>' == 'null'){
            alert('로그인 후 이용가능');
            location.href='<%=contextPath%>/views/member/loginPage.jsp';
        }
    });
    //-----------------------------------------------------------상품-----------------------------------------------------------------------
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

        function selectInterest(){
            $.ajax({
                    url : 'selectInterest.po',
                    data : {
                        pno : <%= a.getProductNo() %>
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
                        pno : <%= a.getProductNo() %>
                    },
                    success : function(result){
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
                        pno : <%= a.getProductNo() %>
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
            var method = '<%= a.getMethod() %>';
        	$('#p_saleProduct0').click(function(){
                if(method == 'P'){
                    location.href='<%= contextPath %>/detail.po?pno=' + $(this).children().eq(0).val();
                } else {
                    location.href='<%= contextPath %>/detail.au?pno=' + $(this).children().eq(0).val();
                }
        	});
        	$('#p_saleProduct1').click(function(){
                if(method == 'P'){
                    location.href='<%= contextPath %>/detail.po?pno=' + $(this).children().eq(0).val();
                } else {
                    location.href='<%= contextPath %>/detail.au?pno=' + $(this).children().eq(0).val();
                }
        	});
        	$('#p_saleProduct2').click(function(){
                if(method == 'P'){
                    location.href='<%= contextPath %>/detail.po?pno=' + $(this).children().eq(0).val();
                } else {
                    location.href='<%= contextPath %>/detail.au?pno=' + $(this).children().eq(0).val();
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
                    yourMemNo : <%= a.getMemNo() %>,
                    productNo : <%= a.getProductNo() %>
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
                    yourMemNo : <%= a.getMemNo() %>
                },
                success : function(result){
                    alert(result);
                }
            });
        });
    <% } %>
  </script>
	
</body>
</html>