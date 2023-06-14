<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.product.model.vo.Product" %>
<%
    ArrayList<Product> searchList = (ArrayList<Product>)request.getAttribute("searchList");
    String searchText = (String)request.getAttribute("searchText");
    String searchSelect = (String)request.getAttribute("searchSelect");
    int listCount = (int)request.getAttribute("listCount");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품게시판</title>
<style>

    /* 상품 리스트 */
    .list-area{text-align : center;}  
    .thumbnail{display: inline-block;width : 250px;margin : 20px;background-color: rgb(245, 244, 242);}
    .thumbnail:hover{cursor:pointer;background-color:rgb(202, 234, 247);}
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
                <form action="<%= contextPath%>/search.po" name="search">
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
                <div id="p_searchResult" style="text-align: left;">
                    <%if(searchSelect.equals("title")){ %>
                        <h4>상품이름 <b>'<%= searchText %>'</b> 검색 결과 <%= listCount%>건</h4>
                     <% } else if(searchSelect.equals("memId")) {%>
                         <h4>작성자 ID <b>'<%= searchText %>'</b> 검색 결과 <%= listCount%>건</h4>
                     <% } else { %>
                         <h4><b>'<%= searchText %>'</b> 통합 검색 결과 <%= listCount%>건</h4>
                     <% } %>
                </div>
                
                <!--상품이 없을 경우-->
                <% if(searchList.isEmpty()) { %>
                    <br><br><br>
                    <h3>등록된 상품이 없습니다.</h3>
                <% } else {%>
                    <!--상품 있을 경우-->
                    <div>
                     <% for(Product p : searchList){ %>
                        <div class="thumbnail" id="p_list" align="left" >
                            <input type="hidden" value="<%= p.getProductNo() %>">
                            <img class="p_img" src="<%= p.getTitleimg() %>" >
                                	상품 이름 : <%= p.getProductName() %><br>
                                	상품 가격 : <%= p.getProductPrice() %><br>
   	
                        	</div>
                            <% } %>
                    <% } %>
            </div>


    </div>
    <div align="center"><button id="p_addBtn" onclick="more();">더보기</button></div>

    <script>    
    // 검색된 상품 리스트 12개 보다 작으면 더보기 버튼 삭제
        $(function(){ 
            if($('.thumbnail').length < 12){
                $('#p_addBtn').remove();
                    }
                })  

        // 상품 이미지 클릭시 상세페이지로 이동
        $(function(){
            $('.thumbnail').click(function(){
                location.href = '<%= contextPath %>/detail.po?pno=' + $(this).children().eq(0).val();
            });
        });

        //더보기 버튼
        function more(){
            $.ajax({
                url : 'moreSearchList.po',
                type : 'post',
                data : {
                    startNum : $('.thumbnail').length,
                    searchSelect : '<%= searchSelect %>',
                    searchText : '<%= searchText %>'
                },
                success : function(result){
                    if(result.length > 1) {
                        var list = '';
                        for(var i=1; i<result.length; i++){
                            list += '<div class="thumbnail" align="left" >'
                                +'<input type="hidden" value="'+ result[i].productNo + '">'
                                +'<img class="p_img" src="'+ result[i].titleimg +'" >'
                                    +'상품 이름 : ' + result[i].productName +'<br>'
                                    +'상품 가격 : ' + result[i].productPrice +'<br>'
                                    +'</div>';
                                }
                        $('.list-area').append(list);
                        $(function(){
                            $('.thumbnail').click(function(){
                                location.href = '<%= contextPath %>/detail.po?pno=' + $(this).children().eq(0).val();
                            });
                        });
                    } else {
                           $('#p_addBtn').remove();
                        }
                },
                error : function(){
                    console.log('실패');
                }
            });
        }

    </script>
<br><br><br><br><br>
</body>
</html>