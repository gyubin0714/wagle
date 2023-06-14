
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Category, com.kh.notice.model.vo.*, com.kh.common.model.vo.*" %>
<%
    Notice n = (Notice)request.getAttribute("n");
    Attachment at = (Attachment)request.getAttribute("at");
    ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의 작성하기</title>
<style>
    
    #n_update-area{
        height: 500px;
        width: 1050px;
        height: 600px;
        font-size: 18px;
        border-bottom: 3px solid grey;

    }

    #n_update-area th {
        text-align: center;
        width: 150px;
    }

    #n_update-area tr {
        border-bottom: 3px solid rgb(201, 197, 197);
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
        <div id="n_inquiry_update_list">
        
            <div id="content">
                <div id="content2">
            
                <div class="content2_1">
                    <h2><b>1:1 문의</h2></b><br>
                </div><hr><br><br>
                <div class="content2_2">
                    <h4><b>1:1 문의 수정</b></h4>
                    <p>- 평일 9:00 - 18:00 <br>
                    - 토,일, 공휴일 휴무
                    </p>
                    <p style="font-size: 13px;"> 답변시간 이후 문의건은 운영시간 내 순차적으로 답변해드립니다  
                </div>
                <br>
                <form enctype="multipart/form-data" action="<%= contextPath %>/inquiryUpdate.no" id="update-form" method="post">
                    <!-- 카테고리, 답변알림(채팅, 이메일), 제목, 내용, 첨부파일, 개인정보체크박스, 등록버튼 -->
                    <input type="hidden" name="ino" value="<%= n.getInquiryNo() %>"> 

                    <table align="center" id="n_update-area">
                        <tr>
                            <!-- CS_CATEGORY테이블에서 옵션으로 추가-->
                            <th width="150">문의유형</th>
                            <td width="600">
                                <select name="csCategoryName">
                                    <% for(Category c : list) { %>
                                        
                                        <option value="<%= c.getCsCategoryNo() %>"><%= c.getCsCategoryName() %></option>
            
                                     <% } %>
                                </select>
                                <script>
                                    $(function(){
                                        $('#update-form option').each(function(){

                                            if($(this).text() == '<%= n.getCsCategoryName() %>') {
                                            $(this).attr('selected', 'true');
                                           }
                                        });
                                    });

                                </script>
                            </td>
                        </tr>
                        <tr>
                            <th>답변알림</th>
                            <td id="answerNotice">
                                
                                <input type="checkbox" name="answerNotice" value="채팅" checked> 채팅창 &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="checkbox" name="answerNotice" value="이메일" checked> 이메일
                                
                            </td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><input type="text" name="title" style="width:400px" required value="<%= n.getTitle() %>"></td>
                        </tr>
                        <tr>
                            <th>문의내용</th>
                            <td>
                                <textarea name="content" style="resize:none; width: 400px;" rows="10"><%= n.getContent() %></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>첨부파일</th>
                            <% if(at != null) { %>
                                <td>
                                    <%= at.getOriginName() %>
                                    
                                    <input type="hidden" name="originFileNo" value="<%= at.getFileNo()%>">
                                    <input type="hidden" name="originFileName" value="<%= at.getChangeName()%>">
                                    <input type="file" name="reUpfile" style="padding-left: 20px;">
                                </td>
                            <% } else { %>
                                <td><input type="file" name="reUpfile"></td>
                            <% } %>    
                            
                        </tr>
                        <br>
                    </table>
                   

                    <br><br>

                    <div align="center">
                        <td><input type="checkbox" name="agree" value="agree" required checked> 개인정보 수집 및 이용에 대한 동의(필수)</td>
                        <br><br><br>
                        <button type="button" class="n_back_btn" onclick="history.back();">뒤로가기</button>
                        <button type="submit" class="n_enroll_btn" onclick="return updateInquiry();">수정하기</button>
                    </div>

                </form>

                
            </div>
            </div>
        </div>
    </div>

    <script>
        function updateInquiry(){

            var insertInquiry = confirm("수정하시겠습니까?");

            if(insertInquiry == true) {
                alert("수정이 완료되었습니다.");
                return true;
            } else {
                alert("수정이 취소되었습니다.");
                return false;
            }
        }

    </script>
    
    
    
    
    

    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>