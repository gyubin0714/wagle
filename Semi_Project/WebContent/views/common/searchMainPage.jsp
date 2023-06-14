<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.Board" %>
<% ArrayList<Board> searchMainBoardList = (ArrayList)request.getAttribute("searchMainBoardList"); %>
<% String query = (String)request.getAttribute("query"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 메인 화면</title>
<style>
    #wrap{
        width: 1200px;
        height: 2400px;
        margin: auto;
    }
    #head{
        box-sizing: border-box;
        width: 100%;
        height: 1%;
    }
    #product{
        box-sizing: border-box;
        width: 100%;
        height: 35%;
    }
    #product_1{
        box-sizing: border-box;
        width: 100%;
        height: 15%;
    }
    #product_2{
        box-sizing: border-box;
        width: 100%;
        height: 78%;
        padding: 10px;
        padding-left: 20px;
    }
    #product_3{
        border-bottom: 1px solid black;
        width: 100%;
        height: 7%;
    }
    #product_showMore{
        margin-left: 565px;
        margin-right: 550px;
        font-size: 22px;
    }
    #product_showMore:hover{
        cursor: pointer;
        background-color: rgb(201, 201, 201);
    }
    .product_table{
        border: 1px solid black;
        width: 250px;
        height: 280px;
        margin: auto;
        float: left;
    }
    .product_border{
        width: 290px;
        height: 320px;
        padding: 20px;
        float: left;
    }
    #auction{
        box-sizing: border-box;
        width: 100%;
        height: 35%;
    }
    #auction_1{
        box-sizing: border-box;
        width: 100%;
        height: 15%;
    }
    #auction_2{
        box-sizing: border-box;
        width: 100%;
        height: 78%;
        padding: 10px;
        padding-left: 20px;
    }
    #auction_3{
        border-bottom: 1px solid black;
        width: 100%;
        height: 7%;
    }
    #auction_showMore{
        margin-left: 565px;
        margin-right: 550px;
        font-size: 22px;
    }
    #auction_showMore:hover{
        cursor: pointer;
        background-color: rgb(201, 201, 201);
    }
    .auction_table{
        border: 1px solid black;
        width: 250px;
        height: 280px;
        margin: auto;
        float: left;
    }
    .auction_border{
        width: 290px;
        height: 320px;
        padding: 20px;
        float: left;
    }
    #board{
        box-sizing: border-box;
        width: 100%;
        height: 30%;
    }
    #board_1{
        box-sizing: border-box;
        width: 100%;
        height: 15%;
    }
    #board_2{
        box-sizing: border-box;
        width: 100%;
        height: 77%;
    }
    #board_3{
        box-sizing: border-box;
        width: 100%;
        height: 8%;
    }
    #board_table{
        margin: 20px;
    }
    tbody>tr:hover{
        cursor: pointer;
        background-color: rgb(226, 226, 226);
    }
    #board_showMore{
        margin-left: 565px;
        margin-right: 550px;
        font-size: 22px;
    }
    #board_showMore:hover{
        cursor: pointer;
        background-color: rgb(201, 201, 201);
    }
    
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
	<div id="wrap">
        <div id="head"></div>
        <div id="product">
            <div id="product_1">중고거래</div>
            <div id="product_2">
                <div class="product_border">
                    <div class="product_table"></div>
                </div>
                <div class="product_border">
                    <div class="product_table"></div>
                </div>
                <div class="product_border">
                    <div class="product_table"></div>
                </div>
                <div class="product_border">
                    <div class="product_table"></div>
                </div>
                <div class="product_border">
                    <div class="product_table"></div>
                </div>
                <div class="product_border">
                    <div class="product_table"></div>
                </div>
                <div class="product_border">
                    <div class="product_table"></div>
                </div>
                <div class="product_border">
                    <div class="product_table"></div>
                </div>
            </div>
            <div id="product_3">
                <div id="product_showMore">더보기+</div>
            </div>
        </div>
        <div id="auction">
            <div id="auction_1">경매</div>
            <div id="auction_2">
                <div class="auction_border">
                    <div class="auction_table"></div>
                </div>
                <div class="auction_border">
                    <div class="auction_table"></div>
                </div>
                <div class="auction_border">
                    <div class="auction_table"></div>
                </div>
                <div class="auction_border">
                    <div class="auction_table"></div>
                </div>
                <div class="auction_border">
                    <div class="auction_table"></div>
                </div>
                <div class="auction_border">
                    <div class="auction_table"></div>
                </div>
                <div class="auction_border">
                    <div class="auction_table"></div>
                </div>
                <div class="auction_border">
                    <div class="auction_table"></div>
                </div>
            </div>
            <div id="auction_3">
                <div id="auction_showMore">더보기+</div>
            </div>
        </div>
        <div id="board">
            <div id="board_1">게시글</div>
            <div id="board_2">
                <table id="board_table" border="1">
                    <thead>
                        <tr>
                            <th style="width: 140px; height: 35px;">카테고리</th>
                            <th style="width: 600px; height: 35px;">글제목</th>
                            <th style="width: 160px; height: 35px;">작성자</th>
                            <th style="width: 160px; height: 35px;">작성일</th>
                            <th style="width: 100px; height: 35px;">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                  	  <% for(Board b : searchMainBoardList){ %>
                        <tr>
                            <td hidden><%=b.getBoardNo()%></td>
                            <td style="width: 140px; height: 50px;"><%=b.getCategoryNo() %></td>
                            <td style="width: 600px; height: 50px;"><%=b.getBoardTitle()%></td>
                            <td style="width: 160px; height: 50px;"><%=b.getMemberNo() %></td>
                            <td style="width: 160px; height: 50px;"><%=b.getBoardDate() %></td>
                            <td style="width: 100px; height: 50px;"><%=b.getBoardHits() %></td>
                        </tr>
                       <% } %>
                    </tbody>
                </table>
            </div>
            <div id="board_3">
                <div id="board_showMore">더보기+</div>
            </div>
        </div>
    </div>

    <script>
        $('#board_showMore').click(function(){
            location.href = '<%=contextPath%>/search.bo?keyword=title&search='+'<%=query%>'+'&aPage=1'
        })
        $('tbody>tr').click(function(){
            location.href = '<%=contextPath%>/detail.bo?bno='+ $(this).children().eq(0).text();
        })
    </script>
</body>
</html>