<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Category" %>
<%
    ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터 작성하기</title>
<style>
   
   #n_enroll-area{
        height: 500px;
        width: 800px;
        height:500px;
        font-size: 18px;
        border-bottom: 3px solid grey;

    }

    #n_enroll-area th {
        text-align: center;
        width: 150px;
    }

    #n_enroll-area tr {
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
                <form action="<%= contextPath %>/noticeInsert.no?cpage=1" id="enroll-form" method="post">
                    <!-- 카테고리, 제목, 내용, 등록버튼 -->
                    <table align="center" id="n_enroll-area">
                        <tr>
                            <!-- CS_CATEGORY테이블에서 옵션으로 추가-->
                            <th width="150">문의유형</th>
                            <td width="600">
                                <select name="csCategoryName">
                                    <% for(Category c : list) { %>
                                        
                                        <option value="<%= c.getCsCategoryNo() %>"><%= c.getCsCategoryName() %></option>
            
                                     <% } %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><input type="text" name="title" style="width:400px" required></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="content" style="resize:none; width: 400px;" rows="10" required></textarea>
                            </td>
                        </tr>
                        <br>
                    </table>

                    <br>

                    <div align="center">
                        <button type="submit" class="n_enroll_btn" onclick="return insertNotice();">등록하기</button>
                        <button type="button" class="n_back_btn" onclick="history.back();">뒤로가기</button>
                    </div>

                </form>
                
                


            </div>
        </div>
    </div>

    <script>
        function insertNotice(){

            var insertInquiry = confirm("작성하시겠습니까?");

            if(insertInquiry == true) {
                alert("작성이 완료되었습니다.");
                return true;
            } else {
                alert("작성이 취소되었습니다.");
                return false;
            }
        }

    </script>
    
    
    
    

    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>