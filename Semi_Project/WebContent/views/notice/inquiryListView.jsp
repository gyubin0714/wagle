<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice, com.kh.common.model.vo.PageInfo" %>
<% 
    ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");

    String memNo = (String)request.getAttribute("memNo");

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
<title>1:1 문의 내역</title>
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
                    <h2><b>1:1 문의</h2></b><br>
                </div><hr><br>
                <div class="content2_2">
                    <h4><b>1:1 문의 내역</b></h4>
                    <p>- 평일 9:00 - 18:00 <br>
                    - 토,일, 공휴일 휴무
                    </p>
                    <p style="font-size: 14px;"> 답변시간 이후 문의건은 운영시간 내 순차적으로 답변해드립니다  
                </div>
                <!-- 회원이 로그인이 되어있을 경우  작성하기 버튼 생성 -->
                <% if(loginUser != null && !(loginUser.getMemId().equals("admin"))) {%>
                <div style="padding-left: 1045px;">
                 <a href="<%= contextPath %>/inquiryEnrollForm.no"  class="n_write_btn">1:1문의하기</a>
                </div>
                <% } %>

                <table id="n_list-area" align="center">
                    <thead>
                        <tr>
                            <td width="100px">작성번호</td>
                            <td width="500px">제목</td>
                            <td width="100px">작성자</td>
                            <td width="100px">작성일</td>
                            <td width="100px">답변유무</td>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <% if(list.isEmpty()) { %> 
                            <!-- 1:1문의내역이 없을 경우-->
                            <tr>
                                <td colspan="5">1:1 문의내역이 존재하지 않습니다.</td>
                                
                            </tr>

                        <% } else { %> 
                        	<!-- 1:1문의 내역이 있을 경우 -->

	                        <% for(int i=0; i<list.size(); i++) { %>
                                    <!-- 회원 조회 -->
	                        		<tr>
                                        <td><%= list.get(i).getInquiryNo() %></td>
                                        <td><%= list.get(i).getTitle() %></td>
                                        <td><%= list.get(i).getWriter() %></td>
                                        <td><%= list.get(i).getWriteDate() %></td>
                                           <% if(list.get(i).getAnswer() != null) {%>
                                               <!-- 답변이 있을 때 -->
                                               <td><b>답변완료</b></td>
                                           <% } else { %>
                                               <td>답변예정</td>
                                           <% } %>
	                                </tr>
	                              <% } %>
	                         <% } %>
	                         
                        
                    </tbody>
                </table><br><br>
                <div align="center" class="paging-area">
					
					
                    <!-- 이전 -->
                    <% if(currentPage != 1) { %>
                    <button onclick="location.href='<%= contextPath%>/inquiryList.no?cpage=<%= currentPage - 1 %>&memNo=<%= loginUser.getMemNo() %>'" class="btn btn-sm btn-info">&lt;</button>
                    <% } %>
                    
                    <!-- 페이지 -->
                    <% for(int i=startPage; i <= endPage; i++) { %>
                        <% if(currentPage != i) { %>
                        <button onclick="location.href='<%= contextPath %>/inquiryList.no?cpage=<%= i %>&memNo=<%= loginUser.getMemNo() %> '" class="btn btn-sm btn-info"><%= i %></button>
                        <% } else { %>
                        <button class="btn btn-sm btn-info" disabled><%= i %></button>
                        <% } %>
                    <% } %>
                    
                    <!-- 다음 -->
                    <% if(currentPage != maxPage) { %>
                    <button onclick="location.href='<%= contextPath%>/inquiryList.no?cpage=<%= currentPage + 1 %>&memNo=<%= loginUser.getMemNo() %> '" class="btn btn-sm btn-info">&gt;</button>
                    <% } %>
                    
                </div>
            </div>
            
        </div>
    </div>
    
    <script>
        // 1:1문의 상세보기
        
        $(function() {

            $('#n_list-area>tbody>tr').click(function(){
                location.href = '<%=contextPath%>/inquiryDetail.no?ino=' + $(this).children().eq(0).text();
            })
        })
        
    </script>

    <br>
    <br>
    <br>

    

    <br><br><br>

</div>


    
   

    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>