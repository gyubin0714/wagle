<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style>
    #memId{
        height: 40px;
        width: 400px;
        text-align: center;
    }
    #pwdQ{
        height: 40px;
        width: 400px;
        text-align: center;
    }
    #pwdA{
        height: 40px;
        width: 400px;
        text-align: center;
    }
    #pwdSearch{
        width: 400px;
        height: 350px;
    }
    #idSearchbtn{
        width: 400px;
        height: 50px;
        background-color: black;
        color: white;
        border: none;
    }
</style>
</head>
<body>

<%@ include file="../common/menubar.jsp" %>
 
 <br>
    <h1 align="center" >비밀번호 찾기</h1>

    <hr style="border:2px solid black; width:600px">
    
    <div class="outer" align="center">

    <form action="<%= contextPath %>/pwdSearchResult.me" method="post"  align="center" id="pwdSearh-form">
        <table id="pwdSearch" align="center" >
            <tr>
                <td height="10%" id="m-id"><h4>아이디를 입력하세요</h4></td>
                
            </tr>
            <tr>
                <td ><input type="text" id="memId" name="memId" ></td>
            </tr>
            <tr >
                <td height="10%" id="m-pwdQ"><h4>비밀번호 질문을 선택해주세요</h4></td>
            </tr>
            <tr>
                <td>
                    <select name="pwdQ"  id="pwdQ">
                        <option >회원가입시 선택했던 질문을 선택해주세요</option>
                        <option value="자신이 졸업한 초등학교 이름은?">자신이 졸업한 초등학교 이름은?</option>
                        <option value="자신의 최애 색깔은?">자신의 최애 색깔은?</option>
                        <option value="자신의 소울푸드는?">자신의 소울푸드는?</option>
                        <option value="자신의 고향은?">자신의 고향은?</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><input type="text" id="pwdA" name="pwdA" placeholder="선택한 질문의 답을 입력해주세요"></td>
            </tr>

        </table>
        <br><br>
        <hr style="border:2px solid black; width:600px">
        <br>
        <div align="center">
            <button value="submit" id="idSearchbtn">비밀번호 찾기</button>
        </div>


    </form>
    


</body>
</html>