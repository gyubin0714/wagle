<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.Board"%>
<% ArrayList<Board> list = (ArrayList)request.getAttribute("list"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 페이지</title>
<style>
    #b_wrap{
    border: 1px solid black;
    margin: auto;
    width: 1200px;
    height: 1200px;
    }
    #b_height1{
        border: 1px solid black;
        width: 15%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_height2{
        border: 1px solid black;
        width: 85%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_header{
        border: 1px solid black;
        width: 100%;
        height: 5%;
        box-sizing: border-box;
    }
    #b_content_title{
        border: 1px solid black;
        width: 100%;
        height: 15%;
        box-sizing: border-box
    }
    #b_content_text{
        border: 1px solid black;
        width: 100%;
        height: 50%;
        box-sizing: border-box;
    }
    #b_footer{
        border: 1px solid black;
        width: 100%;
        height: 30%;
        box-sizing: border-box;
    }
    #b_inputText{
        border: 1px solid black;
        width: 90%;
        height: 90%;
        box-sizing: border-box;
        margin: auto;
        margin-top: 30px;
    }
    #b_textarea1{
        resize: none;
        width: 100%;
        height: 100%;
    }
    #b_content_border{
        width: 100%;
        height: 100%;
    }
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>

    <div id="b_wrap">
        <div id="b_height1">
            <%@ include file="../board/boardCategoryMenubar.jsp" %>
        </div>
        <div id="b_height2">
            <div id="b_header">게시글 수정</div>
            <form action="updateBoard.bo" method="post" id="b_content_border">
                <div id="b_content_title">
                    <br>
                    <input type="hidden" name="bno" value="<%=list.get(0).getBoardNo()%>">
                    게시판 카테고리 선택 :
                    <select name="Cno">
                        <option value="3">자유게시판</option>
                        <option value="4">상품게시판</option>
                    </select>
                    <br>
                    <br>
                    수정할 게시판 제목 입력란 : 
                    <input type="text" name="title" value="<%= list.get(0).getBoardTitle() %>" style="width: 500px; height: 40px;" required> 
                    <br>
                    <br>
                    파일첨부 : <input type="file" name="fileUpdate">
                </div>
                <div id="b_content_text">
                    <div id="b_inputText">
                        <textarea id="b_textarea1" name="boardText"><%= list.get(0).getBoardWriting() %></textarea>
                    </div>
                </div>
                <div id="b_footer">
                    <button type="submit" style="height: 40px;" id="b_insertBtn">게시글 수정하기</button>
                </div>
            </form>
        </div>
    </div>

    <script>

        let b_textarea1 = document.getElementById('b_textarea1');

        console.dir(b_textarea1);
        $('#b_insertBtn').click(function(){
            if(b_textarea1.value == ""){
                alert('입력해');
            } else {
                alert('성공적으로 수정이 완료되었습니다!');
            }
        });
        
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