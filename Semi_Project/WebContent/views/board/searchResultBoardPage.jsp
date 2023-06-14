<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.Board, com.kh.common.model.vo.PageInfo" %>
<% ArrayList<Board> pageBoardList = (ArrayList)request.getAttribute("pageBoardList"); %>
<% PageInfo pi = (PageInfo)request.getAttribute("pi"); %>
<% String search = (String)request.getAttribute("search"); %>
<% int listCount = (int)request.getAttribute("listCount"); %>
<% String keyword = (String)request.getAttribute("keyword"); %>
<%
	// 페이징바 만들때 필요한 변수 미리 세팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색결과</title>
<style>
    #b_wrap{
        margin: auto;
        width: 1200px;
        height: 1200px;
    }
    #b_header{
        width: 100%;
        height: 10%;
        box-sizing: border-box;
        padding-top: 60px;
    }
    #b_content{
        width: 100%;
        height: 90%;
        box-sizing: border-box;
    }
    #b_content_category{
        width: 15%;
        height: 100%;
        float: left;
        box-sizing: border-box;
    }
    #b_content_board{
        width: 85%;
        height: 90%;
        float: left;
        box-sizing: border-box;
    }
    #b_footer_page{
        width: 85%;
        height: 10%;
        float: left;
        box-sizing: border-box;
    }
    .pageBtn{
        width: 40px;
        height: 40px;
    }
    tbody>tr>td{
        text-overflow:ellipsis;
        overflow:hidden;
        white-space:nowrap;
        max-width: 500px;
    }
    tr>th{
        background-color: rgb(185, 228, 245);
        text-align: center;
    }
    tbody>tr:hover{
        cursor: pointer;
        background-color: rgb(238, 238, 238);
    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
    <div id="b_wrap">
        <div id="b_header">
            <div style="font-size: 40px;">검색결과</div>
            <div style="font-size: 18px;">"<%= search %>"검색 <%= listCount %>개의 결과</div>
        </div>
        <div id="b_content">
            <div id="b_content_category">
                <%@ include file="../board/boardCategoryMenubar.jsp" %>
            </div>
            <div id="b_content_board">
                <table border="1" style="margin: auto; margin-top: 20px;">
                    <thead>
                        <tr>
                            <th>카테고리</th>
                            <th>게시글제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% if(pageBoardList.isEmpty()){ %>
                        <tr>
                            <td colspan="5">조회된 게시글이 없습니다.</td>
                        </tr>
                        <% } else { %>
                            <% for(Board b : pageBoardList) { %>
                            <tr>
                                <td hidden><%=b.getBoardNo()%></td>
                                <td style="width: 100px; height: 60px;"><%=b.getCategoryNo()%></td>
                                <td style="width: 500px; height: 50px;"><%=b.getBoardTitle()%></td>
                                <td style="width: 150px; height: 50px;"><%=b.getMemberNo()%></td>
                                <td style="width: 150px; height: 50px;"><%=b.getBoardDate()%></td>
                                <td style="width: 60px; height: 50px;"><%=b.getBoardHits()%></td>
                            </tr>
                            <% } %>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <div id="b_footer_page" align="center">
                <button id="pageBtn_before">이전</button>
                <% for(int i = startPage; i <= endPage; i++) { %>
	                <% if(currentPage != i) { %>
	                	<button class="pageBtn" onclick="location.href='<%=contextPath%>/search.bo?keyword='+'<%=keyword%>'+'&search='+'<%=search%>'+'&aPage=<%= i %>'" class="pageBtn"><%= i %></button>
	                <% } else { %>
	                	<button class="pageBtn" disaled><%= i %></button>
	                <% } %>
                <% } %>
                <button id="pageBtn_next">다음</button>
            </div>
        </div>
    </div>

    <script>
        $(function(){
            $('#b_content_board>table>tbody>tr').click(function(){
                location.href = '<%=contextPath%>/detail.bo?bno=' + $(this).children().eq(0).text();
            })
        })
        <!-- 메인페이지 왼쪽 카테고리 이동 -->
        $(function(){
            $('#b_category_main').click(function(){ <!-- 카테고리 메인으로 이동-->
                location.href = '<%=contextPath%>/board.bo';
            })
        })
        $(function(){
            $('#b_category_all').click(function(){ <!-- 카테고리 전체게시판 이동-->
                location.href = '<%=contextPath%>/allBoard.bo?aPage=1';
            })
        })
        $(function(){
            $('#b_category_free').click(function(){ <!-- 카테고리 자유게시판 이동-->
                location.href = '<%=contextPath%>/freeBoard.bo?aPage=1';
            })
        })
        $(function(){
            $('#b_category_best').click(function(){ <!-- 카테고리 인기글게시판 이동-->
                location.href = '<%=contextPath%>/bestBoard.bo?aPage=1';
            })
        })
        $(function(){
            $('#b_category_notice').click(function(){ <!-- 카테고리 공지사항 이동-->
                location.href = '<%=contextPath%>/noticeList.no?cpage=1';
            })
        })
        $(function(){
            $('#b_category_product').click(function(){ <!-- 카테고리 상품게시판 이동-->
                location.href = '<%=contextPath%>/productBoard.bo?aPage=1';
            })
        })
    </script>
</body>
</html>