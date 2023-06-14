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
        height: 100px;
        padding-left: 445px;
        font-size: 18px;

    }

    .Q td:hover{
        cursor: pointer;
    }

    #faq_table_area {
        width:1200px;
        padding-left: 125px;
    }

    #faq_thead_area {
    background-color: rgb(179, 221, 238);
    }    
    

    #faq_table_area thead { height: 50px; background-color:rgb(185, 228, 245);}

    #faq_table_area tr { font-size: 17px; text-align: center;}

    #faq_table_area td { height: 45px;}



</style>
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>

    <div class="wrap">
        
        <!-- 
            카테고리에서 FAQ클릭 시 -> 구분 없이 1번부터 전체 조회

            검색어를 입력 시 -> 구분 없이 1번부터  [검색어 포함] 조회

            a태그 카테고리 클릭 시 -> 구분에 해당하는 글만 1번부터  조회

        -->
        <div id="header">
            <div id="header1" align="center">
                <%@ include file="../common/csCategory.jsp" %> 
            </div>
        </div>
  
        
        <div id="content">
            
            <div id="content2">
                <div class="content2_1">
                    <h2><b>FAQ</h2></b><br>
                </div><hr><br>
                <div class="content2_2">
                    <h4><b>FAQ 자주묻는 질문</b></h4>
                    <form action="<%=contextPath%>/faqSearchList.no">
                        <input type="text" name="searchWord" placeholder="검색어를 입력해주세요">
                        <input type="submit" class="n_search" value="검색">
                    </form>
                    <p style="font-size: 14px; padding-top: 7px;">찾으시는 답변이 없으면 1:1 문의하기를 이용해주세요</p>
                    <br>
                    <!-- 카테고리를 눌렀을 때, 카테고리별로 조회-->
                    <form action="<%=contextPath%>/faqListCategory1.no" method="get" id="n_category_area">
                        <input type="submit" name="category" value="회원정보"> &nbsp;
                        <input type="submit" name="category" value="경매/물물교환"> &nbsp;
                        <input type="submit" name="category" value="거래"> &nbsp;
                        <input type="submit" name="category" value="게시판">
                    </form>
                    <!-- 로그인이 관리자로 되어있을 경우 작성하기 버튼 생성 -->
                <% if(loginUser != null && loginUser.getMemId().equals("admin")) {%>
                    <div style="padding-left: 980px;">
                    <a href="<%= contextPath %>/faqEnrollForm.no"  class="n_write_btn">작성하기</a>
                    </div>
                <% } %>
                <br>
                </div>
                <div id="faq_table_area">
                    <div id="faq_thead_area">
                        <table>
                            <td width="70px" height="40px" align="center">번호</td>
                            <td width="170px" align="center">구분</td>
                            <td width="420px">제목</td>
                        </table>
                    </div>
                     <div>
                        <% if(list.isEmpty()) { %> 
                           <div> FAQ문의 내역이 존재하지 않습니다.</div>
                        <% } else {%>
                            <% for(Notice n : list) {%>
                                <div class="Q">
                                    <table>
                                        <td width="70px" height="40px" align="center"><%= n.getFaqNo() %></td>
                                        <td width="180px" align="center"><%= n.getCsCategoryName() %></td>
                                        <td width="500px">Q.  <%= n.getTitle() %></td>
                                        <% if(loginUser != null && loginUser.getMemId().equals("admin")) {%>
                                        <td><a href="<%= contextPath %>/faqUpdateForm.no?fno=<%= n.getFaqNo() %>" class="btn btn-sm btn-info">수정하기</a></td>
                                        <td><a href="<%= contextPath %>/faqDelete.no?fno=<%= n.getFaqNo() %> " class="btn btn-sm btn-info">삭제하기</a></td>
                                        <% } %>
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