<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.Board, com.kh.common.model.vo.PageInfo"%>
<%
    ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	String searchField = (String)request.getAttribute("searchField");
	String searchText = (String)request.getAttribute("searchText");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<style>
    #n_list-area {
        margin-top: 30px;
        width: 1050px;
        border-radius: 5px;
    }

    #n_list-area thead { height: 45px; background-color:rgb(185, 228, 245);}

    #n_list-area tr { font-size: 17px; text-align: center;}

    #n_list-area td { height: 60px;}

    #n_list-area>tbody>tr:hover {
        cursor: pointer;
        background-color: rgb(185, 228, 245);
        color: white;
    }
       
     #n_list-area>tbody>tr:hover {
        cursor: pointer;
        background-color: rgb(179, 221, 238);
        color: white;
    }        

    #scroll_table thead {
        display: block;
        background-color: rgb(179, 221, 238);
    }

    #scroll_table tbody {
        display: block;
        overflow: auto;
        height: 600px;
    }

    #n_search {
        padding-left: 125px;
    }


</style>
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>
    
    <div class="wrap">
        <div id="header1" align="center">
            <%@ include file="../common/csCategory.jsp" %> 
        </div>
        <div id="content">
            
            <div id="content2">
                <div class="content2_1">
                    <h2><b>게시판 조회</h2></b><br>
                </div><hr><br>
                <form action="<%=contextPath%>/searchBoard.ad" method="post" name="search" id="n_search">
                    <select name="searchField">
                        <option value="0">-- 선택 --</option>
                        <option value="board_No">게시판 번호</option>
                        <option value="mem_Id">아이디</option>
                        <option value="mem_Name">이름</option>
                    </select>
                    <input type="text" name="searchText" placeholder="검색어를 입력해주세요">
                    <button type="submit">검색</button>
                </form>
                
                <div id="scroll_table">
                <table id="n_list-area"  align="center">
                    <thead>
                        <tr>
                            <td width="70px" height="40px" align="center">번호</td>
                            <td width="500px" style="padding-left: 30px;">제목</td>
                            <td width="100px" align="center">아이디</td>
                            <td width="100px" align="center" >이름</td>
                            <td width="165px" align="center">작성일</td>
                        </tr> 
                    </thead>
                    <tbody>
                
                        <% if(list.isEmpty()) { %> 
                            <!-- 게시판 없을 경우-->
                            <tr>
                                <td colspan="5">게시판이 존재하지 않습니다.</td>
                            </tr>

                        <% } else { %> 
                        	<!-- 게시판 있을 경우 -->
								
	                        <% for(int i=0; i<list.size(); i++) { %>
	                        		<tr>

                                        <td align="center" width="70px" height="50px"><%= list.get(i).getBoardNo() %></td> 
                                        <td width="500px" style="padding-left: 30px;"><%= list.get(i).getBoardTitle() %></td>
                                        <td width="95px" align="center" ><%= list.get(i).getMemId() %></td>
                                        <td width="95px" align="center"><%= list.get(i).getMemberNo() %></td>
                                        <td width="170px" align="center"><%= list.get(i).getBoardDate() %></td>
                                        <% if(list.get(i).getBoardDelete().equals("N")) { %>
                                            <td style="padding-left: 15px;"><a onclick="return deleteBoard();" href=" <%= contextPath %>/boardDelete.ad?bno=<%= list.get(i).getBoardNo() %>">삭제</a></td>
                                        <% } else { %>
                                            <td style="padding-left: 15px;"><a onclick="return recoverBoard();" href=" <%= contextPath %>/boardRecover.ad?bno=<%= list.get(i).getBoardNo() %>">복구</a></td>
                                        <% } %>
	                                </tr>
	                              <% } %>
	                         <% } %>
	                         
                        
                    </tbody>
                </table><br><br>
                </div>

            </div>
            
        </div>
    </div>
    <script>

        // 게시판 상세보기 
        // $(function() {

        //     $('#n_list-area>tbody>tr').click(function(){
        //         location.href = '<%=contextPath%>/boardDetail.bo?bno=' + $(this).children().eq(0).text();
        //     })
        // })

        function deleteBoard(){

        var insertInquiry = confirm("삭제하시겠습니까?");

        if(insertInquiry == true) {
            alert("삭제가 완료되었습니다.");
            return true;
        } else {
            alert("삭제가 취소되었습니다.");
            return false;
        }
        }

        function recoverBoard(){

        var insertInquiry = confirm("복구하시겠습니까?");

        if(insertInquiry == true) {
            alert("복구가 완료되었습니다.");
            return true;
        } else {
            alert("복구가 취소되었습니다.");
            return false;
        }
        }

    </script>

<br><br>

</div>

</body>
</html>