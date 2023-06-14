<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.notice.model.vo.*" %>

<%
    Notice n = (Notice)request.getAttribute("n");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의 상세보기</title>
<style>

    #n_detail-area {
        height: 500px;
        width: 1050px;
        height: 600px;
        font-size: 18px;
        border-bottom: 3px solid grey;
    }

    #n_detail-area tr {
        border-bottom: 3px solid rgb(201, 197, 197);
    }

    #n_detail-area th {
        text-align: center;
        width: 150px;
    }
    

    #answer input {
        width: 300px;
        height: 100px;
    }


</style>
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>
    
    <div class="wrap">
        <div id="header">
            <div id="header1" align="center">
                <%@ include file="../common/csCategory.jsp" %> 
            </div>
        </div>
        
        <div id="content">
            
            <div id="content2">
                <div class="content2_1">
                    <h2><b>공지사항</h2></b><br>
                </div><hr><br>
                <div class="content2_2">
                    <h4><b>공지사항 상세</b></h4>
                    <p>- 평일 9:00 - 18:00 <br>
                    - 토,일, 공휴일 휴무
                    </p>
                </div>

                <table id="n_detail-area" align="center">

                    <tr>
                        <th>문의유형</th>
                        <td colspan="3" width="500" height="50px"><%= n.getCsCategoryName() %></td>
                    </tr>
                    <tr style="background-color: rgb(245, 244, 242)">
                        <th>제목</th>
                        <td colspan="3" height="50px"><%= n.getTitle() %></td>
                    </tr>

                    <tr>
                        <th colspan="3" height="50px">작성일</th>
                        <td><%= n.getWriteDate() %></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td colspan="3">
                            <p style="height: 300px;"><%= n.getContent() %></p>
                        </td>
                    </tr>
                    
                </table>
                <br><br>
                
                <div align="center">
                    <a href="<%=contextPath%>/noticeList.no?cpage=1" class="n_back_btn" >목록으로</a>
                    <!-- 회원 로그인 시 수정하기, 삭제하기 버튼 생성 -->
                    <% if(loginUser != null && loginUser.getMemId().equals("admin")) { %>
                    <a href="<%= contextPath %>/noticeUpdateForm.no?nno=<%= n.getNoticeNo() %>" class="n_enroll_btn">수정하기</a>
                    <a href="<%= contextPath %>/noticeDelete.no?cpage=1&nno=<%=n.getNoticeNo() %>" class="n_back_btn">삭제하기</a>
                    <% }%>
                        
                    
                </div>
            </div>
        </div>
    </div>
    
    
    
    

    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>