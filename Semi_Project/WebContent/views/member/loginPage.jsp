<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="com.kh.member.model.vo.Member" %>
    
   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
   
    <title>로그인 페이지</title>
  
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

  <style>
  
    
    
  #m_login{
      width:100%;

    }
    #memId, #memPwd {
      width: 100%;
      height: 60%;
    }

    #login-form{
      width: 400px;
      height: 250px;
 
    }
    #m_login{
      height: 60%;
    }
     
  </style>


  </head>
  <body>
  
 

    <%@ include file="../common/menubar.jsp" %>
    
    <br><br><br>
    <h1 align="center">로그인</h1>

  
    <br>
    <hr width="500px" style="border:2px solid black">
<br>
    <form action="<%= contextPath %>/login.me" method="post">
        <table align="center" id="login-form"  >
            <tr height="20%" >
                <td width="80%" ><input type="text" name="memId" id="memId" maxlength="15" minlength="4" required placeholder="아이디" style="text-align: center;"></td>
            </tr>
            <tr height="20%">
                
                <td width="80%"><input type="password" name="memPwd" maxlength="15" id="memPwd" required placeholder="비밀번호" style="text-align: center;"></td>
            </tr>
            <th colspan="2" height="10%">
                <button type="submit" align="center" id="m_login" style="background-color: black; color: white;">로그인 하기</button>
            </th>
            
        </table>
        
    </form>

    <br><br>
    <hr width="500px" style="border:2px solid black">

    <br>
    <table align="center">
        <tr>
            <td><a href="<%= contextPath %>/idSearchForm.me"  style="color:black;">아이디</a></td>
            <td><a href="<%= contextPath %>/pwdSearchForm.me"  style="color:black;">/ 비밀번호 찾기</a></td>
            <td> | </td>
            <td><a href="<%= contextPath %>/enrollForm.me"  style="color:black;"> 회원가입하기 </a></td>
        </tr>
    </table>
    

<br><br><br><br><br><br><br><br><br><br>
</body>
</html>