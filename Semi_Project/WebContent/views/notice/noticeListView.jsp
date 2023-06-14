<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice, com.kh.common.model.vo.PageInfo" %>
<% 
    ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");

    PageInfo pi = (PageInfo)request.getAttribute("pi");

    // 페이징바 변수
    int currentPage = pi.getCurrentPage();
    int startPage = pi.getStartPage();
    int endPage = pi.getEndPage();
    int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
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
                    <h4><b>공지사항 조회</b></h4>
                    <p>- 평일 9:00 - 18:00 <br>
                    - 토,일, 공휴일 휴무
                    </p>
                </div>
                <!-- 관리자 로그인시, 공지사항 작성하기 생성 -->
                <% if(loginUser != null && loginUser.getMemId().equals("admin")) {%>
                <div style="padding-left: 1010px;">
                 <a href="<%= contextPath %>/noticeEnrollForm.no" class="n_write_btn">공지사항 작성하기</a>
                </div>
                <% } %>

                <table id="n_list-area" align="center">
                    <thead>
                        <tr>
                            <td width="70px" height="40px" align="center">번호</td>
                            <td width="170px">구분</td>
                            <td width="380px">제목</td>
                            <td width="120px">등록일</td>
                            <td width="100px">조회수</td>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <% if(list.isEmpty()) { %> 
                            <!-- 공지사항이 없을 경우-->
                            <tr>
                                <td colspan="5">공지사항이 존재하지 않습니다.</td>
                            </tr>

                        <% } else { %> 
                        	<!-- 공지사항이 있을 경우 -->

	                        <% for(int i=0; i<list.size(); i++) { %>
	                        		<tr>

                                        <td align="center" height="50px"><%= list.get(i).getNoticeNo() %></td> 
                                        <td><%= list.get(i).getCsCategoryName() %></td>
                                        <td><%= list.get(i).getTitle() %></td>
                                        <td><%= list.get(i).getWriteDate() %></td>
                                        <td align="center"><%= list.get(i).getCount() %></td>
	                                </tr>
	                              <% } %>
	                         <% } %>
	                         
                        
                    </tbody>
                </table><br><br>
                <div align="center" class="paging-area">
					
					
                    <!-- 이전 -->
                    <% if(currentPage != 1) { %>
                    <button onclick="location.href='<%= contextPath%>/noticeList.no?cpage=<%= currentPage - 1 %>'" class="btn btn-sm btn-info">&lt;</button>
                    <% } %>
                    
                    <!-- 페이지 -->
                    <% for(int i=startPage; i <= endPage; i++) { %>
                        <% if(currentPage != i) { %>
                        <button onclick="location.href='<%= contextPath %>/noticeList.no?cpage=<%= i %> '" class="btn btn-sm btn-info"><%= i %></button>
                        <% } else { %>
                        <button class="btn btn-sm btn-info" disabled><%= i %></button>
                        <% } %>
                    <% } %>
                    
                    <!-- 다음 -->
                    <% if(currentPage != maxPage) { %>
                    <button onclick="location.href='<%= contextPath%>/noticeList.no?cpage=<%= currentPage + 1 %> '" class="btn btn-sm btn-info">&gt;</button>
                    <% } %>
                    
                </div>
               
            </div>
            
        </div>
    </div>
    
    <script>
        // 공지사항 상세보기
        
        $(function() {

            $('#n_list-area>tbody>tr').click(function(){
                location.href = '<%=contextPath%>/noticeDetail.no?nno=' + $(this).children().eq(0).text();
            })
        })
        
    </script>

    <br>
    <br>
    <br>

    

    <br><br><br>

</div>

</body>
</html>