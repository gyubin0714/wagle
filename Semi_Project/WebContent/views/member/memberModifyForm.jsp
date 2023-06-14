<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="com.kh.common.model.vo.Attachment" %>
    
    <%
      Member m = (Member)session.getAttribute("m");
      Attachment at = (Attachment)request.getAttribute("at");
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 

<style>
    .outer{
        width: 900px;
        height: 600px;
        margin: auto;
        margin-top: 25%;
        margin-left: 30%;
        
    }
    #m_enroll-form{
        text-align: center;
        height: 700px;
        width: 800;
        

    }

    #m_enroll-form input{
        width: 100%;
        height: 100%;
    }
    
    #m_enroll{
        width:100%;
        height: 100%;
        background-color: black;
        color: white;
    }
    #m_profile{
        height: 100%;
    }
    #m_profile_reset{
        float: left;
        height: 40%;
        width: 40%;
    }
    #upfile{

        height: 100px;
        width:100px;
        border-radius:80px;
        border:none;
    }
    #reupfile{
        height: 100px;
        width:100px;
        border-radius:80px;
        border:none;
    }

</style>
</head>
<body>
 <%@ include file="../member/myPageBar.jsp" %>
 


 
   
    
    <div class="outer" align="center" >
     
    <h1 align="center" style="margin-top: 20%;">회원 정보 수정</h1>

    <hr style="border:2px solid black; width:600px">
    <form  enctype="multipart/form-data"  action="<%=contextPath%>/memberUpdate.me" method="post"  align="center" id="update-form">
   
     <input type="hidden" name="memNo" value="<%= loginUser.getMemNo() %>">
     
        <table  id="m_enroll-form" align="center">
            <tr>
                <td rowspan="2">프로필</td>
                <td rowspan="2">|</td>
                <!--   -->
                <!--  기존에 프로필이 존재했다면  -->
                
               <% if(loginUser.getRefBno() != 0){ %>
                  <td rowspan="2"> <!--  at.getOriginName() -->
                  <%= at.getOriginName() %>
                  <img name="originFileName" id="upfile" src="<%=contextPath%>/<%= at.getFilePath()%>/<%= at.getChangeName()%>"> 
                  <input type="hidden" name="originFileNo" value="<%= at.getFileNo() %>">
		          <input type="hidden" name="originFileName"  value="<%= at.getChangeName() %>">
		          </td>
		          <% } else {  %>
	               <td rowspan="2"><img name="upfile" id="upfile"  src="https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male4-256.png"></td> 
                  <%  } %>
		          <!-- 1개만 가능 -->
		          <input type="file" id="profile" name="reUpfile" onchange="loadImg(this, 1);">
               </div>
                </td>
            </tr>
            <tr>
                <td><button value="reset" id="reset">삭제</button></td>

            </tr>
            <tr >
                <td>아이디</td>
                <td>|</td>
                <td colspan="2"><input type="text" name="memId" id="memId" required minlength="8" maxlength="15" readonly value="<%= loginUser.getMemId() %>"></td>
                <td><button type="button" onclick="idCheck();">중복 확인</button></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td>|</td>
                <td colspan="2"><input type="password" id="memPwd" name="memPwd" required value="<%= loginUser.getMemPwd() %>" minlength="8" maxlength="15"></td>
            </tr>
            <tr>
                <td rowspan="2">비밀번호 찾기 질문</td>
                <td rowspan="2">|</td>
                <td colspan="2">
                   <!--<input type="hidden" name="pwdQ" id="m_pwdQNum"> --> 
                    <select name="pwdQ" onchange="pwdQ_num(this.value)" value="<%= loginUser.getPwdQ() %>">
                        <option >질문을 선택하세요</option>
                        <option value="자신이 졸업한 초등학교 이름은?">자신이 졸업한 초등학교 이름은?</option>
                        <option value="자신의 최애 색깔은?">자신의 최애 색깔은?</option>
                        <option value="자신의 소울푸드는?">자신의 소울푸드는?</option>
                        <option value="자신의 고향은?">자신의 고향은?</option>
                </select></td>
                
            </tr>
            <tr>
                <td colspan="2"><input type="text" id="pwdA" value="<%= loginUser.getPwdA() %>" required> </td>
            </tr>
            <tr>
                <td>이름</td>
                <td>|</td>
                <td colspan="2"><input type="text" id="memName" value="<%= loginUser.getMemName() %>" maxlength="5" required></td>
            </tr>
            <tr>
                <td>닉네임</td>
                <td>|</td>
                <td colspan="2"><input type="text" value="<%= loginUser.getNickname() %>" id="nickname" name="nickname" maxlength="30" required></td>
                <td><button type="button" onclick="nicknameCheck();">중복 확인</button></td>
            </tr>
            <tr>
                <td rowspan="2">주소</td>
                <td rowspan="2">|</td>
                <td colspan="2"><input type="text" id="txtPostCodeC" templatename="TextBox" autocomplete="off" 
                    componentid="txtDINE" class="txtDINE" tabindex="78" placeholder="우편번호" value="<%= loginUser.getAddress() %>"></td>
                <td rowspan="2"><button id="btnSearchAddressC" type="Button" value="우편번호 찾기" templatename="Button" multi-language="true" 
                    language-code="SearchAddress" componentid="PopupBtn" class="popBtn_STD" tabindex="84" translate
                     onclick="findDaumPostcode()" >주소검색</button></td><br>
                
            </tr>
            <tr>
                <td colspan="2"><input type="text" id="txtAddressC" templatename="TextBox" autocomplete="off" 
                    componentid="txtDINE" class="txtDINE" tabindex="81" placeholder="상세 주소"  value="<%= loginUser.getAddress() %>"></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td>|</td>
                <td colspan="2"><input type="email" id="email" value="<%= loginUser.getEmail() %>"></td>
            </tr>
            <tr>
                <td>결제 계좌</td>
                <td>|</td>
                <td>
                    <select name="banklist" id="payAccount" value="<%= loginUser.getPayAccount() %>">
                        <option >은행</option>
                        <option value="농협">농협</option>
                        <option value="우리">우리</option>
                        <option value="신한">신한</option>
                        <option value="기업">기업</option>
                        <option value="하나">하나</option>
                        <option value="카카오뱅크">카카오뱅크</option>
                        <option value="새마을금고">새마을금고</option>
                    </select>
                </td>
                <td colspan="2"><input type="text" id="payAccount" placeholder="-를 제외하여 입력하세요" value="<%= loginUser.getPayAccount() %>"></td>
            </tr>

        </table>
        <br><br>
        <div align="center">
            <button type="submit" class="btn btn-secaondry" style="background-color: #71C7EC; border: none; ">수정하기</button>
            <button type="reset" class="btn btn-secaondry" style="background-color: rgb(77, 77, 77); color: white; ">초기화</button>
        </div>
    </form>

    <br><br><br><br><br><br><br><br><br><br><br><br>
    <script>
        // 다음 주소 API
        function findDaumPostcode() {
            new daum.Postcode({
                oncomplete: function (data) {
                var zoneCodeCompany = data.zonecode;
                var addressCompany = data.address;
                document.getElementById("txtPostCodeC").value = zoneCodeCompany; // zipcode
                document.getElementById("txtAddressC").value = addressCompany; // 주소 넣기
                }
            }).open();
            }

    </script>

    <!-- <script>
        function pwdQ_num(text_value){
            var num_value = '';
            if(text_value == '자신이 졸업한 초등학교 이름은?'){
                num_value = 1;
            }else if(text_value == '자신의 최애 색깔은?'){
                num_value = 2;
            }else if(text_value == '자신의 소울푸드는?'){
                num_value = 3;
            }else if(text_value == '자신의 고향은?'){
                num_value = 4;
            }
            document.querySelector('#m_pwdQNum').value = num_value;

        }
    </script> -->

    <script>
        function idCheck(){
            var $memId = $('#enroll-form input[name=memId]');

            $.ajax({
                url : 'memIdCheck.me',
                data : {checkId : $memId.val()},
                success : function(result){

                    if(result == 'NNNNN'){ // 사용 불가
                        alert('이미 존재하거나 탈퇴한 회원의 아이디입니다. 다시 입력해주세요');
                    
                        // 재입력 요청
                        $memId.val('').focus();
                    }else{ // 중복 아니고 사용가능한 아이디라면?

                       alert('사용가능한 아이디입니다.');

                       // submit활성화
                       $('#enroll-form button[type=submit]').removeAttr("disabled");

                       
                    }
    				
    				
    			}
    		
    	});
    }
        
        
    </script>
    <script>
        function nicknameCheck(){
            var $nickname = $('#enroll-form input[name=nickname]');

            $.ajax({
                url : 'nicknameCheck.me',
                data : {checkNickname : $nickname.val()},
                success : function(result){

                    if(result == 'NNNNN'){ // 사용 불가
                        alert('이미 존재하거나 탈퇴한 회원의 닉네임입니다. 다시 입력해주세요');
                    
                        // 재입력 요청
                        $nickname.val('').focus();
                    }else{ // 중복 아니고 사용가능한 아이디라면?

                       alert('사용가능한 닉네임입니다.');

                       // submit활성화
                       $('#enroll-form button[type=submit]').removeAttr("disabled");

                       
                    }
    				
    				
    			}
    		
    	});
    }
    </script>
     <script>
          $(function(){
        	  $('#file-area').hide();
        	  
        	  $('#upfile').click(function(){
        		 $('#reUpfile').click(); 
        	  });
        	  
        	  
          });
          
          function loadImg(inputFile, num){
        	// inputFile : 현재 변화가 생긴 <input type = "file" > 요소  객체
            // num : 몇번째 input요소인지 확인 후 해당 영역에 미리보기를 하기 위해서 받아줌
        	  
            
             // inputFile.flies.length : 파일을 첨부 1, 파일 선택취소 0
             // => 파일의 존재 유무를 알 수 있다. inputFile.files[0] 에 선택된 파일이 담겨있음
             
             if(inputFile.files.length == 1){  //파일 첨부
            	 
            	// 파일을 읽어들일 FIleReader객체 생성
            	 let reader = new FileReader();
             
            	// FileReader 객체로부터 파일을 읽어들이는 메소드를 호출
            	 reader.readAsDataURL(inputFile.files[0]);
            	
            	// 해당 url을 src속성의 값으로 부여할 것(attr)
            	
            	// 파일 읽기가 완료되면 실행할 삼수 정의
                 reader.onload = function(e){
            		
                	 // e.target.result에 각 파일의 url이 담김
            		switch(num){
            		case 1 :  $('#upfile').attr('src', e.target.result); break;
            		
            		
            		 }
                 }
            }else{
            	let src='https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male4-256.png'
            	switch(num){
            	case 1 :   $('#upfile').attr('src', e.target.result); break;
            			}
            		}
            		
            		
            	}
      
      </script>
 
    


 </div>
 

 
</html>