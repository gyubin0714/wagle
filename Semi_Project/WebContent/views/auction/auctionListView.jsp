<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.auction.model.vo.Auction, com.kh.product.model.vo.ProductPageInfo" %>
<%
	ArrayList<Auction> list = (ArrayList)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>경매게시판</title>
<style>

    /* 상품 리스트 */
    .list-area{text-align : center;}  
    .a_thumbnail{display: inline-block;width : 250px;margin : 20px;background-color: rgb(245, 244, 242);}
    .a_thumbnail:hover{cursor:pointer;background-color:rgb(202, 234, 247);}
    .p_img{width : 250px; height : 250px; padding : 10px;}

   /*검색*/
    table{display:inline-block;}
    #p_search{text-align:center;}
    #p_searchSelect{height: 40px;width:150px;border-radius: 4px; border: 1px solid #bbb;}
    #p_searchInput{
        height: 40px;
        width:400px;
        border : 1px solid #bbb;
        border-radius:4px;
        padding : 10px 12px;
        font-size: 14px;
    }
    #p_btn-search{
    border : none;
    background-color: white;
    float: right;
    overflow: hidden;
    }
    #p_btn-search:hover{transform: scale(1.1);}

    /*상품 등록 버튼 */
    #p_enroll{ height: 50px;text-align: right;margin-right: 15px; }
    #p_btn-enroll{
        height: 40px; 
        width:150px; 
        border-radius: 4px;
        color: black; 
        background-color: rgb(185, 228, 245); 
        border:none;
        overflow:hidden;  
    }
    #p_btn-enroll:hover{
        transform: scale(1.1);
    }

     /* 더보기 버튼 */
   #p_addBtn{
        height: 40px; 
        width:150px; 
        border-radius: 4px; 
        background-color: rgb(216, 216, 216); 
        color:black; 
        border:none;
        overflow:hidden;   
    }
    #p_addBtn:hover{
        transform: scale(1.1);
    }
    
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

    <div class="wrap">
        <br>
        <div id="p_search">
            <form action="<%= contextPath %>/search.au" name="search">
                <table>
                    <tr>
                        <td>
                            <select name="searchSelect" id="p_searchSelect">
                            <option value="0">상품이름+작성자</option>
                            <option value="title">상품이름</option>
                            <option value="memId">작성자</option>
                            </select>
                        </td>
                        <td>
                            <input id="p_searchInput" type="text" placeholder="검색어 입력" name="searchText" maxlength="200">&nbsp;
                            <button type="submit" id="p_btn-search"><img src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" style="width:35px;height:35px;"></button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <br>
            <div class="list-area">
                <!--로그인-->
                <% if(loginUser != null){ %>
                    <div id="p_enroll">
                        <button id="p_btn-enroll" onclick="location.href='<%= contextPath %>/enrollForm.po'">상품 등록하기</button>
                    </div>
                <% } %>
                
                <!--상품이 없을 경우-->
                <% if(list.isEmpty()) { %>
                    <h3>등록된 상품이 없습니다.</h3>
                <% } else {%>
                    <!--상품 있을 경우-->
                    <% for(Auction a : list){ %>
                        <div class="a_thumbnail" align="left" >
                            <input type="hidden" value="<%= a.getProductNo() %>">
                            <img class="p_img"src="<%= a.getTitleimg() %>" >
                                상품 이름 : <%= a.getProductName() %><br>
                                현재 가격 : <%= a.getPriceNow() %><br>
                                등록일 : <%= a.getCreateDt() %><br>
                        </div>
                    <% } %>
                <% } %>
            </div>
            <br>
        </div>
        <div align="center"><button id="p_addBtn" onclick="more();">더보기</button></div>
    
        <script>
    
            function more(){
                $.ajax({
                    url : 'moreList.au',
                    type : 'post',
                    data : {
                        startNum : $('.thumbnail').length
                    },
                    success : function(result){
                        if($('.thumbnail').length < 12){
                            $('#p_addBtn').remove();
                        } else if(result.length > 0) {
                            var list = '';
                            for(var i=1; i<result.length; i++){
                                list += '<div class="a_thumbnail" align="left" >'
                                    +'<input type="hidden" value="'+ result[i].productNo + '">'
                                    +'<img class="p_img" src="'+ result[i].titleimg +'" >'
                                        +'상품 이름 : ' + result[i].productName + '<br>'
                                        +'현재 가격 : ' + result[i].productPrice + '<br>'
                                        +'등록일 : ' + result[i].createDt + '<br>'
                                    +'</div>';
                            }
                            $('.list-area').append(list);
                            $(function(){
                                $('.thumbnail').click(function(){
                                    location.href = '<%= contextPath %>/detail.au?pno=' + $(this).children().eq(0).val();
                                });
                            });
                            if(result.length <= 2){
                                $('#p_addBtn').remove();
                            }
                        }
                    },
                    error : function(){
                        console.log('실패');
                    }
                });
            
            }

            $(function(){ 
                if($('.thumbnail').length < 12){
                    $('#p_addBtn').remove();
                }
            });  
            $(function(){
                $('.a_thumbnail').click(function(){
                    location.href = '<%= contextPath %>/detail.au?pno=' + $(this).children().eq(0).val();
                });
            });

    </script>

</body>
</html>