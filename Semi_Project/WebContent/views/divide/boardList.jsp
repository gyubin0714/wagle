<%@page import="java.util.ArrayList, com.kh.divide.model.vo.Divide,com.kh.common.model.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Divide> list =(ArrayList<Divide>)request.getAttribute("list");

	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	//페이징바를 만들 때 필요한 변수 미리세팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .thumbnail{
		border: 1px solid rgb(185,228,245);
		width: 250px;
		display: inline-block;
		margin: 30px;
		background-color: white;
	}

    .thumbnail_img{
		width: 250px;
		height: 200px;
		padding: 10px;
	}

    .wrap{
        border: 1px solid black;
        background-color: white;
    }
    .thumbnail:hover{
		background-color: rgb(185,228,245);
        color: white;
	}
</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>

    
	<div class="wrap">

        <br>
        <h2 align="center">물물교환/나눔</h2>
        <br>

       		<% if(loginUser != null){ %>
			<div style="width: 95%;" align="right">
				<a href="<%=contextPath%>/enrollForm.di" class="btn btn-sm btn-info">등록하기</a>
				
			</div>
			<br>
			<% } else { %>
			<div style="width: 95%;" align="right">
				<p> </p>
			</div>
			<br>
			<% } %>
        <div class="list-area">

                <% if(list.isEmpty()) {%>
                
                   	<h2 style="color: red;" align="center">등록된 상품이 없습니다!</h2>

                <% } else { %>
            <% for(Divide d : list) { %>
            <div class="thumbnail" align="center">
                <input type="hidden" value="<%= d.getdNo() %>">
                <p align="left">&nbsp;NO.<%= d.getdNo() %></p>
                <img src="<%= d.getTitleImg() %>" alt="" class="thumbnail_img">
                <p><%= d.getTitle() %></p>
                <% if(d.getDivide_YN().equals("F")) {%>
                <p align="right">무료나눔&nbsp;&nbsp;</p>
                <%} else{ %>
                <p align="right">구분 : 물물교환&nbsp;&nbsp;</p>
                    <%} %>
                <p align="right">조회수 : <%= d.getD_Count() %>&nbsp;&nbsp;</p>
                
            </div>
            <% } %>
		<% } %>	
        </div>
        <div align="center" class="paging-area">
			
			<% if(currentPage != 1) {%>
            <button onclick="location.href='<%= contextPath %>/list.di?page=<%= 1%>'" class="btn btn-sm btn-info">처음</button>
			<% } %>
			
			<% if(currentPage != 1) {%>
            <button onclick="location.href='<%= contextPath %>/list.di?page=<%= currentPage -1%>'" class="btn btn-sm btn-info"><</button>
			<% } %>
			
			<% for(int i = startPage; i <= endPage; i++) { %>
				<% if(currentPage != i) { %>
				<button onclick="location.href='<%= contextPath %>/list.di?page=<%= i %>'" class="btn btn-sm btn-info"><%= i %></button>
				<% } else { %>
				<button class="btn btn-sm btn-info" disabled><%= i %></button>
				<% } %>
			<% } %>
			
			<% if(currentPage != maxPage) {%>
            <button onclick="location.href='<%= contextPath %>/list.di?page=<%= currentPage +1%>'" class="btn btn-sm btn-info">></button>
        	<% } %>
        	
        	<% if(currentPage != maxPage) {%>
        	<button onclick="location.href='<%= contextPath %>/list.di?page=<%= maxPage %>'" class="btn btn-sm btn-info">끝</button>
        	<% } %>
        </div>
        
        
    </div>
    <script>
		$(function(){

			$('.thumbnail').click(function(){
				
				// 클릭할 때마다 url 요청
				let dno = $(this).children().eq(0).val();


				location.href = "<%=contextPath%>/detail.di?dno="+dno;
				
			});
		});


	</script>
	        
	
	
</body>
</html>