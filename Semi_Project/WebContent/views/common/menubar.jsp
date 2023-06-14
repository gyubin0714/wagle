<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="com.kh.member.model.vo.Member" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser"); // 멤버 만들기 전까지는 각자 대입해서 넣기
	String alertMsg = (String)session.getAttribute("alertMsg");
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
<title>메뉴바</title>


  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

  <style>
    #head-area{
      float: right;
    }

    #head-area a:hover{

      background-color: none;
        color: #71C7EC;

    }

    
    #user-info a{
         text-decoration : none;
         color : black;
         font-size : 12px;
     }

     
    #mainlogo{
      float: left;
      margin-left: 5%;
    }
    .menu{
        display:inline;
        width: 150px;
        height: 50px;
    }
    .menu a{
        text-decoration: none;
        color: black;
        font-size: 18px;
        width: 100%;
        height: 100%;
        line-height: 15px;
    }
    .menu a:hover{
        background-color: none;
        color: #71C7EC;
    }
    .fixed_menu_border{
      position: fixed;
      bottom: 150px;
      right: 10px;
      width: 150px;
      height: 75px;
      color: black;
      text-align: center;
    }

    #chatMove{
      height: 100%;
      width: 50%;
      padding: 5px;
    }
    #topMove{
      height: 100%;
      width: 50%;
      padding: 5px;
    }
    #chatMove_1:hover{
      cursor: pointer;
      background-color: rgb(219, 219, 219);
    }
    #topMove_1:hover{
      cursor: pointer;
      background-color: rgb(219, 219, 219);
    }
    #topMove_1{
      background-color: #ffffff;
      box-shadow: 2px 2px 2px 2px rgb(219, 219, 219);
      border-radius: 60px;
      width: 100%;
      height: 100%;  
      text-align: center;
      padding-top: 20px;
      font-weight: bold;
      font-size: 18px;
    }
    #chatMove_1{
      background-color: #ffffff;
      box-shadow: 1px 1px 1px 2px rgb(219, 219, 219);
      border-radius: 60px;
      width: 100%;
      height: 100%; 
      text-align: center;
      padding-top: 20px;
      font-weight: bold;
      font-size: 18px;
    }
    .wrap{
      margin: auto;
      width: 1300px;
    }
    #mem-info a{
      text-decoration : none;
         color : black;
         float: right;
    }
    #searchbtn{
      width: 50px;
      height: 50px;
    }
    #search{
      margin-left: 35%;
    }
  </style>


  </head>
  <body>
    <div class="fixed_menu_border">
      <%if(loginUser != null){%>
        <div id="chatMove">
          <div id="chatMove_1">채팅</div>
        </div>
      <%}%>
      <div id="topMove">
        <div id="topMove_1">맨위로</div>
      </div>
    </div>
    
    <img src="<%= contextPath %>/resources/mainpage/wagle.png" alt="와글와글메인로고" id="mainlogo" width="250" height="150">

    <div id="search">
      <form action="searchList.main" method="get">
      <input type="text" name="query" style="border-radius: 25px; width: 600px; height:50px; margin-top: 5%;">
      <a id="main_search" ><img src="https://cdn1.iconfinder.com/data/icons/jumpicon-basic-ui-glyph-1/32/-_Magnifier-Search-Zoom--256.png" alt="" id="searchbtn"></a>
      <button hidden id="main_search_btn"></button>
      </form>
    </div>

    <script>
      // 채팅화면으로
      $('#chatMove_1').click(function(){
        location.href = '<%= contextPath %>/listView.ch'
      });
      // 맨위로
      $('#topMove_1').click(function(){
        location.href = 'javascript:window.scrollTo(0,0)'
      });
      // 메인 로고 클릭시 메인화면으로 돌아가기
      $('#mainlogo').click(function(){
        location.href = '<%= contextPath %>';
      });
      // 클릭시 검색어 보내기
      $('#main_search').click(function(){
        $('#main_search_btn').click();
      });
      // 엔터 쳤을때 검색어 보내기
      $('#main_search').keydown(function(){
        if(event.keyCode == 13){
          $('#main_search_btn').click();
        }
      });
    </script>

     <!--  사용자가 로그인 전 보게될 화면 -->
	<% if(loginUser == null ) { %>
    <div id="head-area">
         <a href="<%= contextPath %>/loginForm.me" style="color:black;">로그인</a> |
         <a href="<%= contextPath %>/enrollForm.me" style="color:black;">회원가입</a> |
         <a href="<%=contextPath%>/noticeList.no?cpage=1" style="color:black;">고객센터</a>
    </div>
    <!--  사용자가 로그인 후 보게 될 화면 -->
    <% } else { %>
      <div id="mem-info" align="center">
        <input type="hidden" value="<%= loginUser.getMemNo() %>">
        <a href="<%= contextPath %>/myPage.me?memNo=<%= loginUser.getMemNo() %>" >마이페이지 </a> 
        <a href="<%= contextPath %>/logout.me">로그아웃 | </a>  
         <a href="<%=contextPath%>/noticeList.no?cpage=1" style="color:black;">고객센터 |</a>

      </div>

     <% } %>
    <br><br>

    <br clear="both">

    <div class="nav1-area" align="center">
      <div class="menu"><a href="<%=contextPath%>/list.po?cpage=1">중고거래</a></div> |
      <div class="menu"><a href="<%=contextPath%>/list.au?cpage=1">경매</a></div> |
      <div class="menu"><a href="<%=contextPath%>/list.di?page=1">물물교환/무료나눔</a></div> |
      <div class="menu"><a href="<%=contextPath%>/board.bo">커뮤니티</a></div>
    </div>

    <hr>

  </body>
  

</html>