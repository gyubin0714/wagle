<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>

<style>
    .outer{
        border: 4px solid black;
        width: 500px;
        height: 250px;
        margin: auto;
        margin-top: 30px;
        text-align: center;
    }
    
    #withdrawal{
        background-color: rgb(209, 64, 64);
        color: white;
        width:400px;
        height: 50px;
        border: none
        

    }
    #cancel{
        background-color: rgb(121, 121, 121);
        color: white;
        width:400px;
        height: 50px;
        border: none;
    }
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
 
 <br>
    <h1 align="center" >회원 탈퇴</h1>

    <hr style="border:2px solid black; width:600px">

    <div class="outer">
        <p>
            <br>
            <h4>탈퇴 후에는 아이디와 데이터는 복구할 수 없습니다.</h4>
            <br><h4>또한 서비스에 남아있는 게시글은 탈퇴 후 삭제할 수 없습니다.</h4>
        </p>
    </div>
    <br><br><br>
    <div align="center" id="button">
    <button value="submit" id="withdrawal" >탈퇴하겠습니다</button>
    <br><br>
    <a href="<%= contextPath%>/wagle"><button value="button" id="cancel">취소합니다</button></a>
    
     </div>

</body>
</html>