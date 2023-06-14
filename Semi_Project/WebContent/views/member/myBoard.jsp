<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.ArrayList, com.kh.board.model.vo.Board" %>
    <%
       ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
    
   
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 게시글</title>

<style>
    .outer{
        width: 1200px;
        margin-left: 500px;
        margin-top: 450px;
       
    }
   
    #myBoard tbody tr:hover{
        background-color: rgb(185, 228, 245);
    }
    
</style>
</head>
<body>

    <%@ include file="../member/myPageBar.jsp" %>

    <div class="outer" align="center">
        <br>
        <h2><b>내 게시글 조회</b></h2>
        
        <hr style="border: 2px solid black;"> 
        <table id="myBoard"  style="width: 100%; text-align: center;" >
            <thead>
             <tr>
                <th width="40%">제목</th>
                <th width="10%">작성자</th>
                <th width="20%">작성일</th>
                <th width="10%">조회수</th>
             </tr>
            </thead>
             <tbody>
             <% if(list.isEmpty()) { %>
             
             <tr style="border: 2px solid black;">
                <td colspan="5"> 조회된 게시글이 없습니다. </td>
            </tr>
            <%  } else  {  %>
            <% for(Board b : list) { %>
            <tr style="border: 2px solid black; " >
          
                    <a href="" ><td onclick="detailBoard();" ><%= b.getBoardTitle() %></td></a>
                    <a href=""><td><%= b.getMemNo() %></td></a>
                    <a href=""><td><%= b.getBoardDate() %></td></a>
                    <a href=""><td><%= b.getBoardHits() %></td></a>
            </tr>
                
                <% }  %>
               <% } %> 
             </tbody>
             
        </table>
         <script>
        
           $(function detailBoard()){
        	   location.href="<%= contextPath %>/detail.bo?bno=" +  $(this).children().eq(0).text();
           }
        
        </script>
             
       
        
    </div>
        
        


</body>
</html>