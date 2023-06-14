<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
    Member m  = (Member)session.getAttribute("pwdSearch");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 결과</title>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>

 <br>
    <h1 align="center" >아이디 찾기</h1>

    <hr style="border:2px solid black; width:600px">
    
    <div class="outer" align="center">
        <p>
            <br>
            <h4><%= m.getMemId()  %> 님의 비밀번호는</h4><br>
            <h4><%= m.getMemPwd() %> 입니다.</h4>
            
            
        </p>
            
        <br><br>
        <hr style="border:2px solid black; width:600px">
        <br><br>
        <div align="center">
            <a href="<%= contextPath %>/loginForm.me"><button value="submit" id="idSearchbtn">로그인 하기</button></a>
        </div>
        

</body>
</html>