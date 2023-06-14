<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성란</title>
<style>
    #b_wrap{
    margin: auto;
    width: 1200px;
    height: 1200px;
    }
    #b_height1{
        width: 15%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_height2{
        width: 85%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_header{
        width: 100%;
        height: 5%;
        box-sizing: border-box;
        font-size: 26px;
        font-weight: bold;
        padding: 10px;
    }
    #b_content_title{
        width: 100%;
        height: 15%;
        box-sizing: border-box
    }
    #b_content_text{
        width: 100%;
        height: 50%;
        box-sizing: border-box;
        margin-bottom: 20px;
    }
    #b_footer{
        width: 100%;
        height: 30%;
        box-sizing: border-box;
        padding-left: 450px;
    }
    #b_inputText{
        width: 90%;
        height: 100%;
        box-sizing: border-box;
        margin: auto;
        margin-bottom: 40px;
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
    #b_insertBtn{
        border: 1px solid black;
        background-color: rgb(185, 228, 245);
        width: 120px;
        height: 50px;
        font-size: 18px;
        font-weight: bold;
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
            <div id="b_header">게시글 작성</div>
            <form action="complete.bo" method="post" id="b_content_border" enctype="multipart/form-data">
                <div id="b_content_title">
                    <br>
                    게시판 카테고리 선택 :
                    <select name="Cno">
                        <option value="3">자유게시판</option>
                        <option value="4">상품게시판</option>
                    </select>
                    <br>
                    <br>
                    게시판 제목 입력란 : 
                    <input type="text" name="title" style="width: 500px; height: 40px;" required> 
                    <br>
                    <br>
                    첨부파일 : 
                    <input type="file" id="file1" name="upfile">
                </div>
                <div id="b_content_text">
                    <div id="b_inputText">
                        <textarea id="b_textarea1" name="boardText" required></textarea>
                    </div>
                </div>
                <div id="b_footer">
                    <button type="submit" id="b_insertBtn">게시글 등록</button>
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
                alert('성공적으로 등록이 되었습니다!');
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