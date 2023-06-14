<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.notice.model.vo.*, com.kh.common.model.vo.*" %>

<%
    Notice n = (Notice)request.getAttribute("n");
    Attachment at = (Attachment)request.getAttribute("at");

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

    

</style>
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>
    
    <div class="wrap">
        
        <!-- 
            1:1 문의 내역

            로그인을 한 유저만 보이기
            로그인을 안한 유저 => 로그인창으로 이동

        -->
        <div id="header">
            <div id="header1" align="center">
                <%@ include file="../common/csCategory.jsp" %> 
            </div>
        </div>

    <div id="n_inquiry_detail">
        <div id="content">
            
            <div id="content2">
                <div class="content2_1">
                    <h2><b>1:1 문의</h2></b><br>
                </div><hr><br>
                <div class="content2_2">
                    <h4><b>1:1 문의 상세</b></h4>
                    <p>- 평일 9:00 - 18:00 <br>
                    - 토,일, 공휴일 휴무
                    </p>
                    <p style="font-size: 13px;"> 답변시간 이후 문의건은 운영시간 내 순차적으로 답변해드립니다  
                </div>
                <br>
                <table id="n_detail-area" align="center"  >
                    <tr>
                        <th>문의유형</th>
                        <td colspan="3" width="800" height="50px"><%= n.getCsCategoryName() %></td>
                        <td><%= n.getWriteDate() %></td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td width="180x" height="50px"><%= n.getWriter() %></td>
                        
                    </tr>
                    
                    <tr style="background-color: rgb(245, 244, 242)">
                        <th >제목</th>
                        <td colspan="3" height="50px" ><%= n.getTitle() %></td>
                    </tr>

                    
                    <tr>
                        <th>내용</th>
                        <td colspan="3">
                            <p style="height: 300px;"><%= n.getContent() %></p>
                        </td>
                    </tr>
                    <tr>
                        <th height="250">첨부파일</th>
                        <td colspan="3">
                        	<% if(at != null) { %>
                                <!-- 사진으로보이게 -->
                                <img width="230" height="230" src="<%= contextPath %>/<%= at.getFilePath()%>/<%= at.getChangeName()%>" onclick="window.open(this.src)">
                        	<% } else {  %>
                        		 첨부파일 웨않너?
                        	<% } %>
                        
                        </td>
                    </tr>
                </table>
                <br><br>
                
                <div align="center">
                    <% if(loginUser != null) { %>
                    <!-- 관리자 로그인 -> 답변 form 생성 -->
                    <% if(loginUser.getMemId().equals("admin")) { %>
                        <% if(n.getAnswer() != null) { %>
                            <!-- 답변이 null값이 아니면 답변 보여주기 -->
                            <div id="n_answer_yes">
                                <table>
                                    <tr>
                                        <th width="50px">답변</th>
                                        <td><%= n.getAnswer() %></td>
                                    </tr>
                                </table>
                            </div>
                        <% } else {%>
                            <!-- 답변이 없을 경우 답변 등록 창 보여주기-->
                        <div border="1">
                            <form  action="<%= contextPath %>/answerInsert.no" id="answer-form" method="post">
                                <input type="hidden" name="ino" value="<%= n.getInquiryNo() %>"> 
                                <table>
                                    <tr>
                                        <th width="50px">내용</th>
                                        <td><input type="text" name="answer"></td>
                                        <td><button type="submit">등록</button></td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <%  } %>

                    <% } else { %>
                        <!-- 회원 로그인 시 수정하기, 삭제하기 버튼 생성 -->
                        <% if(n.getAnswer() != null) { %>
                            <!-- 답변이 null값이 아니면 답변 보여주기 -->
                            <div>
                                <table>
                                    <tr>
                                        <th width="50px">답변</th>
                                        <td width="500" height="35px"><%= n.getAnswer() %></td>
                                    </tr>
                                </table>
                            </div>
                        <% }%>
                        <br><br><br>
                        <a href="<%= contextPath %>/inquiryDelete.no?ino=<%=n.getInquiryNo()%>&memNo=<%=loginUser.getMemNo()%>" class="n_back_btn">삭제하기</a> &nbsp;&nbsp;
                        <a href="<%= contextPath %>/inquiryUpdateForm.no?ino=<%= n.getInquiryNo() %>" class="n_enroll_btn">수정하기</a>
                        
                        <% } %>
                        <br><br>
                        <a href="<%=contextPath%>/inquiryList.no?cpage=1&memNo=<%=loginUser.getMemNo()%>" class="n_back_btn">목록으로</a>
                
                    <% }%>
                </div>
            </div>
            
        </div>
    </div>

    </div>   
    
    
    

    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>