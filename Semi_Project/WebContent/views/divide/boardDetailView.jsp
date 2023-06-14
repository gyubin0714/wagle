<%@page import="com.kh.common.model.vo.Attachment, com.kh.divide.model.vo.Divide,java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Divide d = (Divide)request.getAttribute("d");

	ArrayList<Attachment> at = (ArrayList<Attachment>)request.getAttribute("list");
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

	#detaill-form > table{
        border: 1px solid black;
    }

    #detail-form input, #detail-form textarea{
        width: 100%;
       
    }
    th{
        text-align: center;
    }
</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>

    
	<div class="wrap">
	
	 	<br>
        <h2 align="center">&nbsp</h2>
        <br>
        
        
            <table align="center" border="1" id="detail-form">
            	<tr>
                    <th width="100" style="text-align: center;">제목</th>
                    <td colspan="4" width="700"><%= d.getTitle()%></td>
                </tr>
                <tr>
                	<th width="100" style="text-align: center;">작성자</th>
                	<td colspan="4" width="700"><%= d.getMem_No()%></td>
                </tr>
                <tr>
                	<th width="100" style="text-align: center;">작성일</th>
                	<td colspan="4" width="700"><%= d.getReg_Date()%></td>
                </tr>
                <tr>
                    <th width="100" style="text-align: center;">지역</th>
                    <td colspan="4" width="700"><%= d.getField()%></td>
                </tr>
                <tr>
                    <th width="100" style="text-align: center;">카테고리</th>
                    <td colspan="4" width="700">
                        <%= d.getCategory()%>
                    </td>
                </tr>
                <tr>
                    <th width="100" style="text-align: center;">구분</th>
                    <% if(d.getDivide_YN().equals("F")) {%>
                    <td colspan="4" width="700">무료나눔</td>
                    <%} else{ %>
                    <td colspan="4" width="700">물물교환</td>
                    <%} %>
                </tr>
                <tr>
                    <th style="text-align: center;">내용</th>
                    <td colspan="4" height="246" align="left" style="vertical-align: top;">
                        <%= d.getContent()%>
                    </td>
                </tr>
                <!-- 미리보기 영역 -->
                <tr>
                    <th style="text-align: center;">대표이미지</th>
                    <td colspan="4" align="center">
                        <img id="titleimg" width="250" height="180" src="<%= contextPath%>/<%= at.get(0).getFilePath()%>/<%= at.get(0).getChangeName()%>">
                    </td>
                </tr>
                <tr>
                    <th rowspan="2" style="text-align: center;">상세이미지</th>

                    <% for(int i = 1; i < at.size(); i++) { %>
                    	<td align="center">
                    		<img id="contentImg" width="150" height="110"  src="<%= contextPath%>/<%= at.get(i).getFilePath()%>/<%= at.get(i).getChangeName()%>">
                    	</td>
                   	<% }  %>
                </tr>
                
            </table>
            <br>
            <br>

            <div align="center">
                <button onclick="history.back();" class="btn btn-sm btn-info">뒤로가기</button>
                
                <% if(loginUser != null && loginUser.getMemId().equals(d.getMem_No())) { %>

                <a href="<%= contextPath %>/updateForm.di?dno=<%= d.getdNo() %>" class="btn btn-sm btn-warning">수정하기</a>
                
                <a href="<%= contextPath %>/delete.di?dno=<%= d.getdNo() %>" class="btn btn-sm btn-danger">삭제하기</a>
                
            	<% } %>
            	
            	<br><br><br>
            </div>
			    <div id="reply-area" align="center">

        <table border="1" align="center">
            <thead>
                <% if(loginUser != null) { %>
                <!-- 로그인 O-->
                <tr>
                        <th>댓글작성</th>
                        <td>
                            <textarea id="replyContent" cols="50" style="resize: none;"></textarea>
                        </td>
                        <td><button onclick="insertReply();">댓글등록</button></td>
                </tr>
                <% } else { %>
                <!--로그인 X-->
                <tr>
                    <th>댓글작성</th>
                    <td>
                        <textarea readonly cols="50" style="resize: none;">로그인 후 이용가능한 서비스입니다.</textarea>
                    </td>
                    <td><button >댓글등록</button></td>
            </tr>
                <% } %>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
			

        <br><br><br>
    </div>
    
    <script>

    function selectReplyList(){
        
        $.ajax({
            url : 'rlist.di',
            data : {dno : <%= d.getdNo() %>},
            success : function(list){
				console.log(list);
				
				// 댓글 개수만큼 반복
				let result = '';
				for(let i in list){
					result += '<tr>'
							+ '<td>' + list[i].mNo + '</td>'
							+ '<td>' + list[i].dContent + '</td>'
							+ '<td>' + list[i].createDate + '</td>'
							+ '</tr>'
				};
				
				$('#reply-area tbody').html(result);
            },
            error : function(){
                console.log('댓글 읽어오기 실패~');
            }

        });
    };
	
    // 댓글이 화면이 로딩 되었을때 곧바로 뿌려줘야함 => winfow.onload => $(function(){})
    $(function(){
    	selectReplyList();	
    	
    	
    });

    function insertReply(){
      
        $.ajax({
            url : 'rinsert.di',
            data : {
                dno : <%= d.getdNo() %>,
                content : $('#replyContent').val()
            },
            type : 'post',
            success : function(result){
					
            	//console.log(result);
            	
            	if(result > 0){
            		
            		$('#replyContent').val('')
            		
            		selectReplyList();
            	}
            },
            error : function(){
                console.log('댓글 작성 실패 ㅠㅠ,...');
            }


        });
    };
    </script>
</body>
</html>