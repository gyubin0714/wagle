<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객의소리 작성하기</title>
<style>
   
       
        #n_enroll-area{
            height: 500px;
            width: 800px;
            height: 600px;
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

        
        #inputVocId {
            font-size: 10px;
        }

        #vocMember {
            display: none;
        }


        #vocCategory {
            height:35px;
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
                    <h2><b>고객의소리</h2></b><br>
                </div><hr><br><br>
                <div class="content2_2" style="padding-left: 250px;">
                    <h4><b>고객의소리 접수</b></h4> 
                    <p> 와글을 이용하시면서 불편사항이나 개선사항을 알려주세요. <br>
                        상품에 대한 정보는 1:1문의로 해주세요.
                    </p>
                </div>
                <br>
                <form  action="<%= contextPath %>/vocInsert.no" id="enroll-form" method="post">
                    <input type="hidden" name="memNo" value="<%=loginUser.getMemNo()%>">
                    <table align="center" id="n_enroll-area">
                        <tr>
                            <th style="height: 100px;">구분</th>
                            <td>

                            <select id="vocCategory" name="vocCategory">
                                <option value="voc_choice">---- 선택 ----</option>
                                <option value="회원신고">회원신고</option>
                                <option value="시스템신고">시스템신고</option>
                            </select>
                                   
                            </td>
                        </tr>
                        <div><tr id="vocMember">
                            <th style="height: 100px;">신고 회원 <br> <p id="inputVocId"></p> </th>
                            <td>
                                <input type="text" name="vocMember" style="width: 600px; height: 45px;" placeholder="ID 또는 이름을 입력해주세요">
                            </td>
                        </tr>
                        </div>
                        <tr>
                            <th style="height: 100px;">제목</th>
                            <td>
                                <input name="text" style="resize:none; width: 600px; height: 45px;" required></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="height: 350px;">내용</th>
                            <td>
                                <textarea name="content" style="resize:none; width: 600px;" rows="9" required></textarea>
                            </td>
                        </tr>
                        <br>
                    </table>

                    <br><br>

                    <div align="center">
                        <button type="button" class="n_back_btn" onclick="history.back();">뒤로가기</button> &nbsp;&nbsp;
                        <button type="submit" onclick="return insertVoc();" class="n_enroll_btn">등록하기</button>
                    </div>

                </form>
                
                


            </div>
        </div>
    </div>

    <script>

        $(function() {

            $("#vocCategory").change(function(){

                // 선택한 옵션 값이 [회원신고]일 때, 보여주기
                var vocCategory = $("#vocCategory option:selected").val();

                if(vocCategory == '회원신고' ) {
                    $("#vocMember").show();
                } else {
                    $("#vocMember").hide();
                }
            })

        })

        function insertVoc(){

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

    

    </script>

    
    
    
    
    

    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>