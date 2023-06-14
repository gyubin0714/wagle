<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String search = (String)request.getAttribute("search"); %>  
<% int listCount = (int)request.getAttribute("listCount"); %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색결과가 없는 페이지</title>
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
            <div style="font-size: 40px;">검색 결과</div>
            <div style="font-size: 18px;">"<%= search %>"검색 <%= listCount %>개의 결과</div>
            <div>조회된 결과가 없습니다.</div>
        </div>
        <div id="b_content">
            <div id="b_content_category">
                <%@ include file="../board/boardCategoryMenubar.jsp" %>
            </div>
            <div id="b_content_board">
                <br><br><br>
                <hr>
                no
                <hr>
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