<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.common.model.vo.Attachment,com.kh.product.model.vo.ProductCategory, com.kh.divide.model.vo.Divide,java.util.ArrayList"%>
<%
	Divide d = (Divide)request.getAttribute("d");
	ArrayList<Attachment> at = (ArrayList<Attachment>)request.getAttribute("at");
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

	#update-form > table{
        border: 1px solid black;
    }

    #update-form input[type=text], #update-form textarea{
        width: 100%;
       
    }
</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>

    
	<div class="wrap">
	
	 	<br>
        <h3>물물교환/무료나눔글 수정</h3>
        <br>
        

		<form enctype="multipart/form-data" action="<%= contextPath %>/update.di" id="update-form" method="post" >
			<input type="hidden" name="dno" value="<%= d.getdNo()%>">

            <table align="center" border="1">
                <tr>
                    <th width="100" style="text-align: center;">제목</th>
                    <td colspan="4" width="700"><input type="text" required name="title" value="<%= d.getTitle()%>"></td>
                </tr>
                <tr>
                    <th style="text-align: center;">지역</th>
                    <td colspan="5">
                        <input type="text" name="field" id="d_field" value="<%= d.getField() %>" style="width: 70%;">
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
                    <th width="100" style="text-align: center;">카테고리</th>
                    <td colspan="4" width="700" align="right" style="padding-right: 2px">
                        <select name="category">
                            <% for(ProductCategory c : list) {%>
                            	<option value="<%= c.getCateNo()%>"><%= c.getCateName() %></option>
                            <% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                <tr>
                    <th width="100" style="text-align: center;">구분</th>
                    <td colspan="4" width="700">
                        <input type="radio" name="divideYN" value="D" checked>물물교환
                        <input type="radio" name="divideYN" value="F">나눔
                    </td>
                </tr>
                
                    <th style="text-align: center;">내용</th>
                    <td colspan="4">
                        <textarea name="content" style="resize: none ; " rows="10"><%= d.getContent() %></textarea>
                    </td>
                </tr>
                <!-- 미리보기 영역 -->
				<tr>
				    <th style="text-align: center;">대표이미지</th>
				    <td colspan="4" align="center">
				    <% if(at.size() > 0){%>
				        <img id="titleimg" width="250" height="180" src="<%= contextPath%>/<%= at.get(0).getFilePath()%>/<%= at.get(0).getChangeName()%>">
				         <input type="hidden" name="originFileNo0" value="<%= at.get(0).getFileNo() %>">
                         <input type="hidden" name="originFileName0" value="<%= at.get(0).getChangeName() %>">
                          <% } %>
                           <div id="file-area">
			                <input type="file" id="file1" name="refile0" onchange="loadImg(this, 1);">
			                <input type="file" id="file2" name="refile1" onchange="loadImg(this, 2);">
			                <input type="file" id="file3" name="refile2" onchange="loadImg(this, 3);">
			                <input type="file" id="file4" name="refile3" onchange="loadImg(this, 4);">
			                <input type="file" id="file5" name="refile4" onchange="loadImg(this, 5);">
			               
			            	</div>
				    </td>
				</tr>
				<tr>
				    <th rowspan="2"style="text-align: center;">상세이미지</th>
				    
				    <% for(int i = 1; i < at.size(); i++) { %>
                        <td><img id="contentImg<%= i %>" width="150" height="110" src="<%= contextPath %>/<%=at.get(i).getFilePath() %>/<%=at.get(i).getChangeName() %>" alt=""></td>
                            <input type="hidden" name="originFileNo<%= i %>" value="<%= at.get(i).getFileNo() %>">
                            <input type="hidden" name="originFileName<%= i %>" value="<%= at.get(i).getChangeName() %>">
                    <% } %>
                    
                    <% for(int i = at.size(); i < 5; i++) { %>
                        <td><img id="contentImg<%=i%>" width="150" height="110" src="resources/img_none2.gif"></td>
                    <% } %>
           
				</tr>
				
            </table>
            <br>

            
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
                        let str = "resources/img_none2.gif"
                        switch(num){
                            case 1 : $('#titleimg').attr('src', str); break;
                            case 2 : $('#contentImg1').attr('src', str); break;
                            case 3 : $('#contentImg2').attr('src', str); break;
                            case 4 : $('#contentImg3').attr('src', str); break;
                            case 5 : $('#contentImg4').attr('src', str); break;
                        }
                    }
                }
                $(function(){
                    
                    $('#update-form option').each(function(){
                        
                    	if( $(this).text() == '<%= d.getCategory() %>'){
                            $(this).attr('selected','true');
                        };


                    });

                });
                
                var divide = '<%= d.getDivide_YN() %>';
                // 여행,운동,빵,자취,중고거래
                // console.log('여행,운동,빵,자취,중고거래');

                // 모든 체크박스가 배열에 담김
                $('input[type=radio]').each(function(){

                    // 순차적으로 접근한 checkbox의 value 속성값이 interest에 포함되어이을 경우 체크
                    // => checked속성 => attr(속성명, 속성값);

                    // 자바스크립트의 indexOf => 찾고자하는 문자가 없을 경우 -1을 리턴 == 제이쿼리 serach메소드
                    // 제이쿼리에서 value속성값을 리턴해주는 메소드 : val()
                    // 제이쿼리에서 현재 접근한 요소 지칭 : $(this)
                    if(divide.search($(this).val()) != -1){ //포함되어 있을 경우 => checked 부여
                       $(this).attr('checked',true);
                    };
                });
            </script>


            <br>

            <div align="center">
                <button type="submit" class="btn btn-sm btn-primary">수정하기</button>
                <button type="button" class="btn btn-sm btn-danger" onclick="history.back();">취소하기</button>
            </div>

        </form>

        <br><br><br>
    </div>
</body>
</html>