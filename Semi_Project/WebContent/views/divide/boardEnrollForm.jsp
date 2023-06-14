<%@page import="com.kh.product.model.vo.ProductCategory, java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    ArrayList<ProductCategory> list = (ArrayList<ProductCategory>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body{
        box-sizing: border-box;
    }

	#enroll-form > table{
        border: 1px solid black;
    }

    #enroll-form input[type=text], #enroll-form textarea{
        width: 100%;
       
    }
</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>

    
	<div class="wrap">
	
	 	<br>
        <h3>물물교환/무료나눔글 등록</h3>
        <br>
        

		<form action="<%= contextPath %>/insert.di" id="enroll-form" method="post" enctype="multipart/form-data">
			<input type="hidden" name="memNo" value="<%= loginUser.getMemNo()%>">

            <table align="center" border="1">
                <tr>
                    <th width="100"style="text-align: center;">제목</th>
                    <td colspan="4" width="700"><input type="text" required name="title"></td>
                </tr>
                <tr>
                    <th width="100"style="text-align: center;">카테고리</th>
                    <td colspan="4" width="700" align="right" style="padding-right: 2px">
                        <select name="category">
                            <% for(ProductCategory c : list) {%>
                            	<option value="<%= c.getCateNo()%>"><%= c.getCateName() %></option>
                            <% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: center;">지역</th>
                    <td colspan="5">
                        <input type="text" name="field" id="d_field" placeholder="주소" style="width: 70%;">
                        <input type="button" onclick="execDaumPostcode()" value="주소 찾기"><br>
                    </td>
                    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
                    <script>
                        function execDaumPostcode() {
                            new daum.Postcode({
                                oncomplete: function(data) {
                                    var addr = ''; // 주소 변수
                    
                                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                                        addr = data.roadAddress;
                                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                                        addr = data.jibunAddress;
                                    }
                
                                    document.getElementById("d_field").value = addr;
                                }
                            }).open();
                        }
                    </script>
                </tr>
                <tr>
                    <th width="100"style="text-align: center;">구분</th>
                    <td colspan="4" width="700">
                        <input type="radio" name="divideYN" value="D" checked>물물교환
                        <input type="radio" name="divideYN" value="F">무료나눔
                    </td>
                </tr>
                <tr>
                    <th style="text-align: center;">내용</th>
                    <td colspan="4">
                        <textarea name="content" style="resize: none ; " rows="10"></textarea>
                    </td>
                </tr>
                <!-- 미리보기 영역 -->
                <tr>
                    <th style="text-align: center;">대표이미지</th>
                    <td colspan="4" align="center" required>
                        <img id="titleimg" width="250" height="180" src="https://static.clickmedi.co.kr/images/module/web/img_none2.gif">
                    </td>
                </tr>
                <tr>
                    <th rowspan="2" style="text-align: center;">상세이미지</th>
                    <td align="center"><img id="contentImg1" width="150" height="110" src="resources/img_none2.gif"></td>
                    <td align="center"><img id="contentImg2" width="150" height="110" src="resources/img_none2.gif"></td>
                    <td align="center"><img id="contentImg3" width="150" height="110" src="resources/img_none2.gif"></td>
                    <td align="center"><img id="contentImg4" width="150" height="110" src="resources/img_none2.gif"></td>
                </tr> 
            </table>
            <br>

            <div id="file-area">
                <input type="file" id="file1" name="file1" required onchange="loadImg(this, 1);">
                <input type="file" id="file2" name="file2" onchange="loadImg(this, 2);">
                <input type="file" id="file3" name="file3" onchange="loadImg(this, 3);">
                <input type="file" id="file4" name="file4" onchange="loadImg(this, 4);">
                <input type="file" id="file5" name="file5" onchange="loadImg(this, 5);">
            </div>
            
            <script>

                $(function(){
                    $('#file-area').hide();

                    $('#titleimg').click(function(){
                        $('#file1').click();
                    });

                    $('#contentImg1').click(function(){
                        $('#file2').click();
                    });

                    $('#contentImg2').click(function(){
                        $('#file3').click();
                    });

                    $('#contentImg3').click(function(){
                        $('#file4').click();
                    });
                    $('#contentImg4').click(function(){
                        $('#file5').click();
                    });

                });

                function loadImg(inputFile, num){

                    //console.log(inputFile.files.length);
                   

                    if(inputFile.files.length == 1){
                        let reader = new FileReader();

                        reader.readAsDataURL(inputFile.files[0]);
                        
                        reader.onload = function(e){

                            switch(num){
                                case 1 : $('#titleimg').attr('src', e.target.result); break;
                                case 2 : $('#contentImg1').attr('src', e.target.result); break;
                                case 3 : $('#contentImg2').attr('src', e.target.result); break;
                                case 4 : $('#contentImg3').attr('src', e.target.result); break;
                                case 5 : $('#contentImg4').attr('src', e.target.result); break;
                            }
                        }
                    }else{
                        let str = "https://static.clickmedi.co.kr/images/module/web/img_none2.gif"
                        switch(num){
                            case 1 : $('#titleimg').attr('src', str); break;
                            case 2 : $('#contentImg1').attr('src', str); break;
                            case 3 : $('#contentImg2').attr('src', str); break;
                            case 4 : $('#contentImg3').attr('src', str); break;
                            case 5 : $('#contentImg4').attr('src', str); break;
                        }
                    }
                }
            </script>


            <br>

            <div align="center">
                <button type="submit">작성하기</button>
                <button type="reset">취소하기</button>
            </div>

        </form>

        <br><br><br>
    </div>
</body>
</html>