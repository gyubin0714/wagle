<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.Board, com.kh.board.model.vo.Notice" %>
<!-- 공지사항 -->
<% ArrayList<Notice> noticeList = (ArrayList)request.getAttribute("noticeList"); %>
<!-- 인기글게시판 -->
<% ArrayList<Board> bestList = (ArrayList)request.getAttribute("bestList"); %>
<!-- 전체게시판 -->
<% ArrayList<Board> listAll = (ArrayList)request.getAttribute("listAll"); %>
<!-- 자유게시판 -->
<% ArrayList<Board> freeList = (ArrayList)request.getAttribute("freeList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 메인</title>
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
    }
    #b_header_1{
        /* 보더 없앰 헤드 1영역 */
        width: 70%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_header_2{
        /* 보더 없앰 게시글작성 */
        width: 30%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_header_2_1{
        /* 보더 없앰 */
        width: 100%;
        height: 60%;
        box-sizing: border-box;
        padding-left: 240px;
        padding-top: 20px;
    }
    #b_header_2_2{
        width: 100%;
        height: 40%;
        box-sizing: border-box;
        padding-top: 8px;
        padding-left: 10px;
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
    #b_content_board1_title{
        float: left;
        box-sizing: border-box;
        width: 85%;
        height: 4%;
    }
    #b_content_board1{
        float: left;
        box-sizing: border-box;
        width: 85%;
        height: 27%;
    }
    #b_content_board2_title{
        float: left;
        box-sizing: border-box;
        width: 85%;
        height: 4%;
    }
    #b_content_board2{
        float: left;
        box-sizing: border-box;
        width: 85%;
        height: 27%;
    }
    #b_content_board3_border{
        box-sizing: border-box; 
        float: left; 
        width: 42%;
        height: 38%;
    }
    #b_content_board3_title{
        box-sizing: border-box;
        float: left;
        width: 100%;
        height: 11%;
    }
    #b_content_board3{
        box-sizing: border-box;
        float: left;
        width: 100%;
        height: 89%;
    }
    #b_content_board4_border{
        box-sizing: border-box; 
        float: left; 
        width: 43%;
        height: 38%;
    }
    #b_content_board4_title{
        box-sizing: border-box;
        float: left;
        width: 100%;
        height: 11%;
    }
    #b_content_board4{
        box-sizing: border-box;
        float: left;
        width: 100%;
        height: 89%;
    }
    #b_content_board1_title_1{
        box-sizing: border-box;
        float: left;
        width: 95%;
        height: 100%;
    }
    #b_content_board1_title_2{
        box-sizing: border-box;
        float: left;
        width: 5%;
        height: 100%;
    }
    #b_content_board2_title_1{
        box-sizing: border-box;
        float: left;
        width: 95%;
        height: 100%;
    }
    #b_content_board2_title_2{
        box-sizing: border-box;
        float: left;
        width: 5%;
        height: 100%;
    }
    #b_content_board3_title_1{
        box-sizing: border-box;
        float: left;
        width: 90%;
        height: 100%;
    }
    #b_content_board3_title_2{
        box-sizing: border-box;
        float: left;
        width: 10%;
        height: 100%;
    }
    #b_content_board4_title_1{
        box-sizing: border-box;
        float: left;
        width: 90%;
        height: 100%;
    }
    #b_content_board4_title_2{
        box-sizing: border-box;
        float: left;
        width: 10%;
        height: 100%;
    }
    tbody>tr:hover{
        cursor: pointer;
        background-color: rgb(238, 238, 238);
    }
    tbody>tr>td{
        height: auto;
        width: auto;
        text-overflow:ellipsis;
        overflow:hidden;
        white-space:nowrap;
        max-width: 120px;
    }
    #searchForm{
        width: 100%;
        height: 100%;
    }
    tr>th{
        text-align: center;
        border: 1px solid rgb(0, 0, 0);
        background-color: rgb(185, 228, 245);
        color: rgb(0, 0, 0);
    }
    #b_content_board1_title_1_1{
        margin-left: 20px;
        width: 10%;
        height: 80%;
        border: 1px solid rgb(185, 228, 245);
        background-color: rgb(185, 228, 245);
        color: rgb(0, 0, 0);
        border-radius: 30px;
        text-align: center;
        font-weight: bold;
        font-size: 20px;
        padding-top: 2px;
    }
    #b_content_board2_title_1_1{
        margin-left: 20px;
        width: 15%;
        height: 80%;
        border: 1px solid rgb(185, 228, 245);
        background-color: rgb(185, 228, 245);
        color: rgb(0, 0, 0);
        border-radius: 30px;
        text-align: center;
        font-weight: bold;
        font-size: 20px;
        padding-top: 2px;
    }
    #b_content_board3_title_1_1{
        margin-left: 20px;
        width: 30%;
        height: 80%;
        border: 1px solid rgb(185, 228, 245);
        background-color: rgb(185, 228, 245);
        color: rgb(0, 0, 0);
        border-radius: 30px;
        text-align: center;
        font-weight: bold;
        font-size: 20px;
        padding-top: 2px;
    }
    #b_content_board4_title_1_1{
        margin-left: 20px;
        width: 30%;
        height: 80%;
        border: 1px solid rgb(185, 228, 245);
        background-color: rgb(185, 228, 245);
        color: rgb(0, 0, 0);
        border-radius: 30px;
        text-align: center;
        font-weight: bold;
        font-size: 20px;
        padding-top: 2px;
    }
    #b_search_Btn{
        border: 1px solid rgb(0, 0, 0);
        height: 32px;
        background-color: rgb(185, 228, 245);
    }
    #b_searchOption{
        height: 32px;
    }
    #b_input_text{
        height: 32px;
    }
    #b_boardNotice_btn{
        background-color: rgb(185, 228, 245);
        border: 1px solid black;
    }
    #b_boardBest_btn{
        background-color: rgb(185, 228, 245);
        border: 1px solid black;
    }
    #b_boardFree_btn{
        background-color: rgb(185, 228, 245);
        border: 1px solid black;
    }
    #b_boardAll_btn{
        background-color: rgb(185, 228, 245);
        border: 1px solid black;
    }
    #b_insertBoard_btn{
        width: 100px;
        height: 40px;
        display: none;
        border: 1px solid black;
        background-color: rgb(185, 228, 245);
    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
    <div id="b_wrap">
        <div id="b_header">
            <div id="b_header_1"></div>
            <div id="b_header_2">
                <div id="b_header_2_1">
                    <button id="b_insertBoard_btn">게시글작성</button> <br>
                    <!--<% if(loginUser != null){%>
                    <button>게시글 작성</button>
                    <% } %> 로그인했을때/안했을때 게시글작성버튼 유무 -->
                </div>
                <div id="b_header_2_2">
                    <form action="search.bo" method="get" id="searchForm">
                        <select name="keyword" id="b_searchOption">
                            <option value="title">제목</option>
                            <option value="writer">작성자</option>
                        </select>
                        <input type="text" name="search" id="b_input_text" required>
                        <button type="submit" id="b_search_Btn">검색하기</button>
                        <input type="hidden" name="aPage" value="1">
                    </form>
                </div>
            </div>
        </div>
        <div id="b_content">
            <div id="b_content_category">
                <%@ include file="../board/boardCategoryMenubar.jsp" %>
            </div>
            <div id="b_content_board1_title">
                <div id="b_content_board1_title_1">
                    <div id="b_content_board1_title_1_1">공지사항</div>
                </div>
                <div id="b_content_board1_title_2">
                    <button id="b_boardNotice_btn" style="width: 50px; height: 40px;">+</button>
                </div>
            </div>
            <div id="b_content_board1">
                <div></div>
                <table id="b_board_table" style="width: 100%; border-bottom: 1px solid black;">
                    <thead>
                        <tr style="border-bottom: 1px solid black;">
                            <th style="width: 120px;">카테고리</th>
                            <th>게시글제목</th>
                            <th style="width: 140px;">작성자</th>
                            <th style="width: 120px;">작성일</th>
                            <th style="width: 80px;">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if(noticeList.isEmpty()) { %>
                            <tr>
                                <td colspan="5">조회된 게시글이 없습니다.</td>
                            </tr>
                        <% } else { %>
                            <% for(Notice n : noticeList) { %>
                                <tr>
                                    <td hidden id="hide_nno"><%=n.getNoticeNo()%></td>
                                    <td style="text-align: center;">공지사항</td>
                                    <td style="padding-left: 10px;"><%=n.getNoticeTitle()%></td>
                                    <td style="text-align: center;">관리자</td>
                                    <td style="text-align: center;"><%=n.getNoticeWriteDate()%></td>
                                    <td style="text-align: center;"><%=n.getNoticeCount()%></td>
                                </tr>
                            <% } %>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <div id="b_content_board2_title">
                <div id="b_content_board2_title_1">
                    <div id="b_content_board2_title_1_1">인기글게시판</div>
                </div>
                <div id="b_content_board2_title_2">
                    <button id="b_boardBest_btn" style="width: 50px; height: 42px;">+</button>
                </div>
            </div>
            <div id="b_content_board2">
                <table id="b_board_table1" style="width: 100%; border-bottom: 1px solid black;">
                    <thead>
                        <tr style="border-bottom: 1px solid black;">
                            <th style="width: 120px;">카테고리</th>
                            <th>게시글제목</th>
                            <th style="width: 140px;">작성자</th>
                            <th style="width: 120px;">작성일</th>
                            <th style="width: 80px;">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                         <% if(bestList.isEmpty()) { %>
                             <tr>
                                 <td colspan="5">조회된 게시글이 없습니다.</td>
                             </tr>
                         <% } else {%>
                             <% for(Board b1 : bestList){ %>
                                 <tr>
                                     <td hidden><%=b1.getBoardNo()%></td>
                                     <td style="text-align: center;"><%=b1.getCategoryNo()%></td>
                                     <td style="padding-left: 10px;"><%=b1.getBoardTitle()%></td>
                                     <td style="text-align: center;"><%=b1.getMemberNo()%></td>
                                     <td style="text-align: center;"><%=b1.getBoardDate()%></td>
                                     <td style="text-align: center;"><%=b1.getBoardHits()%></td>
                                 </tr>
                             <% } %>
                         <% } %>
                    </tbody>
                </table>
            </div>
            <div id="b_content_board3_border">
                <div id="b_content_board3_title">
                    <div id="b_content_board3_title_1">
                        <div id="b_content_board3_title_1_1">전체게시판</div>
                    </div>
                    <div id="b_content_board3_title_2">
                        <button id="b_boardAll_btn" style="width: 50px; height: 42px;">+</button>
                    </div>
                </div>
                <div id="b_content_board3">
                    <table id="b_board_table2" style="width: 100%; height: 100%; border-right: 1px solid black;">
                        <thead>
                            <tr style="border-bottom: 1px solid black;">
                                <th>카테고리</th>
                                <th>게시글제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if(listAll.isEmpty()) { %>
                                <tr>
                                    <td colspan="5">조회된 게시글이 없습니다.</td>
                                </tr>
                            <% } else {%>
                                <% for(Board b1 : listAll){ %>
                                    <tr>
                                    	<td hidden><%=b1.getBoardNo()%></td>
                                        <td><%=b1.getCategoryNo()%></td>
                                        <td><%=b1.getBoardTitle()%></td>
                                        <td><%=b1.getMemberNo()%></td>
                                        <td><%=b1.getBoardDate()%></td>
                                        <td><%=b1.getBoardHits()%></td>
                                    </tr>
                                <% } %>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="b_content_board4_border">
                <div id="b_content_board4_title">
                    <div id="b_content_board4_title_1">
                        <div id="b_content_board4_title_1_1">자유게시판</div>
                    </div>
                    <div id="b_content_board4_title_2">
                        <button id="b_boardFree_btn" style="width: 50px; height: 42px;">+</button>
                    </div>
                </div>
                <div id="b_content_board4">
                    <table style="width: 100%; height: 100%; border-right: 1px solid rgb(255, 255, 255);" id="b_board_table3">
                        <thead>
                            <tr style="border-bottom: 1px solid black;">
                                <th>카테고리</th>
                                <th>게시글제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if(freeList.isEmpty()) { %>
                                <tr>
                                    <td colspan="5">조회된 게시글이 없다 이말이야!</td>
                                </tr>
                            <% } else { %>
                                <% for(Board b2 : freeList){ %>
                                    <tr>
                                        <td hidden><%=b2.getBoardNo()%></td>
                                        <td><%=b2.getCategoryNo()%></td>
                                        <td><%=b2.getBoardTitle()%></td>
                                        <td><%=b2.getMemberNo()%></td>
                                        <td><%=b2.getBoardDate()%></td>
                                        <td><%=b2.getBoardHits()%></td>
                                    </tr>
                                <% } %>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script>
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

        <!-- 게시판 메인페이지 디테일 페이지로 이동(게시글 상세페이지)-->
        $(function(){
            $('#b_board_table>tbody>tr').click(function(){ <!-- 공지사항 -->
                location.href = '<%=contextPath%>/noticeDetail.no?nno=' + $(this).children().eq(0).text();
            });
        });
        $(function(){
            $('#b_board_table1>tbody>tr').click(function(){ <!-- 인기글 게시판 -->
                location.href = '<%=contextPath%>/detail.bo?bno=' + $(this).children().eq(0).text();
            });
        });
        $(function(){
            $('#b_board_table2>tbody>tr').click(function(){ <!-- 전체 게시판 -->
                location.href = '<%=contextPath%>/detail.bo?bno=' + $(this).children().eq(0).text();
            });
        });
        $(function(){
            $('#b_board_table3>tbody>tr').click(function(){ <!-- 자유 게시판 -->
                location.href = '<%=contextPath%>/detail.bo?bno=' + $(this).children().eq(0).text();
            });
        });

        <!-- 메인페이지 (+)버튼 이동 -->
        $(function(){/* 메인 +버튼을 통한 페이징 처리 전체게시판 이동 */
            $('#b_boardAll_btn').click(function(){
                location.href = '<%=contextPath%>/allBoard.bo?aPage=1';
            })
        });
        $(function(){/* 메인 +버튼을 통한 페이징 처리 자유게시판 이동 */
            $('#b_boardFree_btn').click(function(){
                location.href = '<%=contextPath%>/freeBoard.bo?aPage=1';
            })
        })
        $(function(){/* 메인 +버튼을 통한 페이징 처리 인기글게시판 이동 */
            $('#b_boardBest_btn').click(function(){
                location.href = '<%=contextPath%>/bestBoard.bo?aPage=1';
            })
        })
        $(function(){ /* 메인 +버튼을 통해 공지사항 게시판으로 이동*/
            $('#b_boardNotice_btn').click(function(){
                location.href = '<%=contextPath%>/noticeList.no?cpage=1';
            })
        })
        $(function(){
            $('#b_insertBoard_btn').click(function(){
                location.href = '<%=contextPath%>/insertboard.bo';
            })
        })
        let insertBoard_btn = document.getElementById('b_insertBoard_btn');
        if(<%=loginUser != null%>){
            insertBoard_btn.style.display = 'block';
        } else {
            insertBoard_btn.style.display = 'none';
        }

    </script>
</body>
</html>