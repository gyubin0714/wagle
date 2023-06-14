<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList,  com.kh.member.model.vo.Follow, com.kh.member.model.vo.Member, com.kh.common.model.vo.Attachment" %>
    
    <%  
    ArrayList<Follow>list = (ArrayList<Follow>)request.getAttribute("list");
    
    Attachment at = (Attachment)request.getAttribute("at");
    
    Member m = (Member)session.getAttribute("memberSearch");
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팔로잉 목록</title>
<style>
  .outer{
    margin-top: 450px;
    margin-left: 450px;
    width: 1300px;
    
    
  }
  .outer > .followList{
    width: 900px;
    margin: auto;
  }
  
  #verticalLine{
    width: 100%;
  }
  
  #profile{
    margin-left: 10%;
    width:100px;
    height:100px;
  }
  .followList{
     text-align: center;
  }
  
  
  
</style>
</head>
<body>

  <%@ include file="../member/myPageBar.jsp" %>
  
  

  <div class="outer" align="center">
    <b><h2>팔로잉 목록</h2></b>
    <br>
    <hr style="border: 2px solid black;"> 
      <br>
     
      <!-- 팔로잉이 없다면 : isEmpty( ) -->
      <% if(list.isEmpty()){ %>
      <tr>
             <td colspan="7"> 팔로잉이 없습니다 </td>
      </tr>
        <% } else { %>
       <!--  반복 값을 순차적으로 뽑아오기 -->
       
       <% for(Follow f : list) { %>
       
    <table class="followList" align="center">
    <input type="hidden" value="<%= loginUser.getMemNo() %>" name="memNo">
   <input type="hidden" value="<%= f.getFollowing() %>" name="following">
    
      <!-- 팔로잉이 얼마나 늘어날지 모르니까 반복문으로 -->
      <!-- arraylist 배열로 반복문 -->
          <tr>
             <% if(at == null){ %>
             <td td width="20%"><img src="https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male4-256.png" id="profile"></td>
             <% } else {  %>
             <td td width="20%"><img src="<%= at.getFileNo() %>" name="fileNo" alt="프로필" id="profile" style="width: 100px; height: 100px; "></td>
             <% } %>
             <td colspan="2"  width="35%"><%= f.getNickname() %></td>
             
             <td  width="20%"><a href="<%= contextPath %>/deletefollowing.me?memNo=<%= loginUser.getMemNo() %>&following=<%= f.getFollowing() %>" id="followingcancel" class="btn btn-info" style="background-color: gray; border: none;">팔로우 취소</a></td>
              
             <td  width="5%"><img src="https://cdn1.iconfinder.com/data/icons/radix/15/divider-vertical-256.png" alt="" id="verticalLine"></td>
             <td  width="20%"><a href="" style="color: white;  text-decoration: none; " class="btn btn-info">채팅하기</a></td>
          </tr>
        
       <% } %>
      <% } %>
       
    </table>
  
    <br><br><br><br><br>
    <hr style="border: 2px solid black;">
    <b><h2>팔로잉 추가</h2></b>
    <hr style="border: 2px solid black;">

    <form action="<%= contextPath %>/searchMember.me" method="post" id="searchMember">
    <input type="text" placeholder="검색할 회원의 닉네임을 입력하세요" name="nickname" id="nickname">
    <button value="submit" id="memberSearchbtn">조회</button>
    </form>
    <hr style="border: 2px solid black;">
    
    <% if( m !=  null){ %>
    <table>
      <tr>
             <% if(at == null){ %>
             <td><img src="https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male4-256.png" id="profile"></td>
             <% } else {  %>
             <td><img src="<%= at.getFileNo() %>" name="fileNo"></td>
             <td width="20%"><img src="https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male4-256.png" alt="프로필" id="profile" style="width: 100px; height: 100px; "></td>
            <% } %>
             <td colspan="2"  width="35%"><%= m.getNickname() %></td>
             <td  width="20%"><button value="submit" id="followingcancel">팔로우 추가</button></td>
             <td  width="5%"><img src="https://cdn1.iconfinder.com/data/icons/radix/15/divider-vertical-256.png" alt="" id="verticalLine"></td>
             <td  width="20%"><button value="button" id="chat"><a href="" style="color: white; text-decoration: none;">채팅하기</a></button></td>
          </tr>
    </table>
    <% } %>
  
    
    
    
  
  </div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>