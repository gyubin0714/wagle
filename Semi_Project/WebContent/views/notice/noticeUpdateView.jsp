<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Category, com.kh.notice.model.vo.Notice" %>
<%
    ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
    Notice n = (Notice)request.getAttribute("n");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성하기</title>
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
        <div id="header">
            <div id="header1" align="center">
                <%@ include file="../common/csCategory.jsp" %> 
            </div>
        </div>      
        <div id="n_notice_update_list">
            <div id="content">
            
                <div id="content2">
                    <div class="content2_1">
                        <h2><b>공지사항</h2></b><br>
                    </div><hr><br>
                    <div class="content2_2">
                        <h4><b>공지사항 수정</b></h4>
                        <p>- 평일 9:00 - 18:00 <br>
                        - 토,일, 공휴일 휴무
                        </p>
                    </div>
                <form action="<%= contextPath %>/noticeUpdate.no" id="update-form" method="post">
                    <input type="hidden" name="nno" value="<%= n.getNoticeNo() %>">

                    <table align="center" id="n_update-area">
                        <tr>
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
                            <th>제목</th>
                            <td><input type="text" name="title" style="width:400px" value="<%= n.getTitle() %>" required></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="content" style="resize:none; width: 400px;" rows="10" required><%= n.getContent() %></textarea>
                            </td>
                        </tr>
                        <br>
                    </table>

                    <br>

                    <div align="center">
                        <button type="submit" class="n_enroll_btn" onclick="return updateNotice();">수정하기</button>
                        <button type="button" class="n_back_btn" onclick="history.back();">뒤로가기</button>
                    </div>

                </form>
                
                


            </div>
        </div>
    </div>

    <script>
        function updateNotice(){

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