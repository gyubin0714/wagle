<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice" %>
<%
    ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .A{
        display: none;
        height: 200px;
        padding-left: 437px;
        font-size: 20px;
    }

    .Q {
        width: 900px;
    }

    .Q td:hover{
        cursor: pointer;
    }

    #voc_category_area>input{
       background: transparent;
       border: 0;
       color: rgb(41, 137, 247);
    }

    #voc_table_area {
        width:1200px;
        padding-left: 125px;
    }


    #voc_thead_area {
        background-color: rgb(179, 221, 238);
    }

    #voc_thead_area thead { height: 50px; background-color:rgb(185, 228, 245);}

    #voc_thead_area tr { font-size: 17px; text-align: center;}

    #voc_thead_area td { height: 45px;}

    

</style>
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>

    <div class="wrap">
        <div id="header">
            <div id="header1" align="center">
                <%@ include file="../common/csCategory.jsp" %> 
            </div>
        </div>

      <div id="n_voc_list">
        
        <div id="content">
            
            <div id="content2">
                <div>
                    <div class="content2_1">
                        <h2><b>고객의소리</h2></b><br>
                    </div><hr><br>
                    <div class="content2_2">
                        <h4><b>고객의소리 조회</b></h4> 
                        <p> 와글을 이용하시면서 불편사항이나 개선사항을 알려주세요. <br>
                            상품에 대한 정보는 1:1문의로 해주세요.
                        </p>
                    
                    <br>
                    <!-- 카테고리를 눌렀을 때, 카테고리별로 조회-->
                    <form action="<%= contextPath %>/selectCategory.no" method="get" id="n_category_area">
                        <input type="submit" name="category" value="회원신고"> | 
                        <input type="submit" name="category" value="시스템신고">
                    </form>
                    <br>
                </div>
                </div>
                <div id="voc_table_area">
                    <div id="voc_thead_area">
                        <table>
                            <td width="70" height="40px" align="center">번호</td>
                            <td width="180" align="center">구분</td>
                            <td width="300">제목</td>
                            <td width="120" align="center">신고회원</td>
                            <td width="120" align="center">작성자</td>
                            <td width="120px"></td>
                        </table>
                    </div>
                     <div>
                        <% if(list.isEmpty()) { %> 
                           <div> 고객의소리 내역이 존재하지 않습니다.</div>
                        <% } else {%>
                            <% for(Notice n : list) {%>
                                <div class="Q">
                                    <table>
                                        <td width="70px" height="40px" align="center"><%= n.getVocNo() %></td>
                                        <td width="180px"  align="center"><%= n.getVocCategory() %></td>
                                        <td width="300px">Q.  <%= n.getTitle() %></td>
                                        <% if( n.getVocMember() != null ) { %>
                                            <td width="120px"align="center"><%= n.getVocMember() %></td>
                                        <% } else { %>
                                            <td width="120px"> </td>
                                        <% } %>
                                        <td width="120px" align="center"><%= n.getWriter() %></td>
                                        <td width="100px" align="center"><a href=" <%= contextPath %>/vocDelete.no?vno=<%= n.getVocNo() %>">삭제</a></td>
                                 
                                        <!-- 회원 정지 -->
                                        
                                    </table>
                                    <hr>
                                </div>
                                <p class="A">A.  <%= n.getContent() %> </p>
                            <% } %>
                        <% } %>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
</div>
    <script>
    // 클릭했을 때 답변(내용=content) 나오기
        $(function(){
            $('.Q').click(function(){
                
                var $p = $(this).next();
                
                if($p.css('display') == 'none'){
                    // display속성값이 none일때 내용 보이기
                    $p.slideDown(0);

                    // 기존에 열려있던 p태그 닫기 (여러개 열려있게하지않음)
                    $p.siblings('p').slideUp(0);
                } else{
                    $p.slideUp(0);
                }
            });
        });

       

       
    </script>


</body>
</html>