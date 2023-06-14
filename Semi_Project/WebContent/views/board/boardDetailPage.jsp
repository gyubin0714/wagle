<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,com.kh.board.model.vo.Board, com.kh.common.model.vo.Attachment" %>
<% ArrayList<Board> listDetail = (ArrayList)request.getAttribute("listDetail"); %>
<% Attachment at = (Attachment)request.getAttribute("at"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세 페이지</title>
<!-- 모달 API -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
    #b_wrap{
    margin: auto;
    width: 1200px;
    height: 1400px;
    }
    #b_height1{
        width: 15%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_height2{
        width: 85%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_header{
        width: 100%;
        height: 20%;
    }
    #b_header_1{
        width: 100%;
        height: 20%;
        box-sizing: border-box;
    }
    #b_header_2{
        font-size: 30px;
        padding-left: 20px;
        padding-top: 40px;
        width: 100%;
        height: 30%;
        box-sizing: border-box;
    }
    #b_header_3{
        font-size: 18px;
        padding-left: 20px;
        padding-top: 20px;
        width: 30%;
        height: 50%;
        box-sizing: border-box;
        float: left;
    }
    #b_header_4{
        width: 70%;
        height: 50%;
        box-sizing: border-box;
        float: left;
    }
    #b_content{
        width: 100%;
        height: 40%;
        box-sizing: border-box;
    }
    #b_footer{
        width: 100%;
        height: 40%;
        box-sizing: border-box;
    }
    #b_header_1_1{
        width: 50%;
        height: 100%;
        box-sizing: border-box;
        float: left;
        font-size: 18px;
        padding-left: 20px;
        padding-top: 5px;
    }
    #b_header_1_2{
        width: 50%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_header_1_2_1{
        width: 30%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_header_1_2_2{
        width: 70%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #b_footer_2{
        width: 100%;
        height: 25%;
    }
    #b_footer_2_1{
        width: 80%;
        height: 100%;
        float: left;
    }
    #b_footer_2_2{
        width: 20%;
        height: 100%;
        float: left;
        padding-top: 40px;
    }
    #b_textarea1{
        resize: none;
        margin-left: 200px;
        margin-top: 15px;
        width: 70%;
        height: 80%;
    }
    #reply-area{
        width: 1000px;
        height: 100%;
        padding: 20px;
    }
    #reply-area{
        height: 350px;
        overflow-y: scroll;
    }
    #reply-area::-webkit-scrollbar {
        display: none;
    }
    #td_nickName{
        width: 500px;
        font-weight: bold;
    }
    .td_content{
        width: 500px;
        height: auto;
    }
    #td_date{
        width: 100px;
        font-size: 14px;
        color: rgb(131, 131, 131);
    }
    #td_comment{
        width: 70px;
    }
    .replyBtn{
        font-size: 14px;
        color: rgb(131, 131, 131);
    }
    .replyBtn:hover{
        cursor: pointer;
    }
    .deleteBtn{
        font-size: 14px;
        border: none;
        color: rgb(131, 131, 131);
    }
    .deleteBtn:hover{
        cursor: pointer;
    }
    .closeBtn:hover{
        cursor: pointer;
    }
    .insertBtn:hover{
        cursor: pointer;
    }
    .reply{
        resize: none;
        margin: auto;
        width: 500px;
        height: 70px;
    }
    .closeBtn{
        margin-left: 650px;
    }
    #b_commentBtn{
        border: 1px solid rgb(145, 145, 145);
        background-color: white;
        width: 100px;
        height: 50px;
    }
    #userInformaiton{
        font-size: 16px;
        color: rgb(0, 0, 0);
        background-color: rgb(185, 228, 245);
        border: 1px solid black;
        margin-left: 10px;
    }
    #chat{
        font-size: 16px;
        color: rgb(0, 0, 0);
        background-color: rgb(185, 228, 245);
        border: 1px solid black;
        margin-left: 5px;
    }
</style>
</head>
<!-- overflow y -->
<body>
	<%@ include file="../common/menubar.jsp" %>
    <div id="b_wrap">
        <div id="b_height1">
            <%@ include file="../board/boardCategoryMenubar.jsp" %>
        </div>
        <div id="b_height2">
            <div id="b_header">
                <div id="b_header_1">
                    <div id="b_header_1_1">카테고리 [ <%=listDetail.get(0).getCategoryNo()%> ]</div>
                    <div id="b_header_1_2">
                        <div id="b_header_1_2_1">
                            <button id="b_updateBoard_btn">게시글 수정</button>
                        </div>
                        <div id="b_header_1_2_2">
                            <button type="button" id="b_deleteBoard_btn1" data-toggle="modal" data-target="#myModal" style="display: block;">
                                게시글 삭제
                            </button>
                        </div>
                          <!-- The Modal -->
                        <div class="modal fade" id="myModal">
                            <div class="modal-dialog">
                                <div class="modal-content">  
                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                    <h4 class="modal-title">게시글을 삭제하시겠습니까?</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>
                                    
                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        <div>게시글을 삭제하면 복구안되는데 그래도 할거야?</div>
                                        <div>그럼 삭제버튼 눌러</div>
                                        <br><br>
                                        <button id="b_deleteBoard_btn2">삭제하기</button>
                                    </div>
                                    
                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">창 닫기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="b_header_2"><%=listDetail.get(0).getBoardTitle()%> <hr></div>
                <div id="b_header_3">
                <div hidden id="boardBno"><%=listDetail.get(0).getBoardNo()%></div>
                <div hidden><%=listDetail.get(0).getMemNo()%></div>
                    <br>
                    <%=listDetail.get(0).getMemberNo()%>
                    <span id="userInformaiton">&nbsp;프로필&nbsp;</span>
                    <span id="chat">&nbsp;채팅&nbsp;</span>
                    <div style="font-size: 16px; color: rgb(143, 143, 143);">
                        <%=listDetail.get(0).getBoardDate()%>
                        조회 <%=listDetail.get(0).getBoardHits()%>
                    </div>
                    <div style="font-size: 16px;">
                        파일 : <%if(at == null) { %>
                            첨부파일이 없습니다.
                      <% } else { %>
                          <a href="<%=contextPath%>/<%=at.getFilePath()%>/<%=at.getChangeName() %>" download><%= at.getOriginName() %></a>
                      <% } %>   
                    </div>
                </div>
                <div id="b_header_4">

                </div>
            </div>
            <div id="b_content" style="padding: 20px;">
                <textarea style="resize: none; width: 970px; height: 520px;" readonly> <%= listDetail.get(0).getBoardWriting() %></textarea>
            </div>
            <div id="b_footer">
                <div id="b_footer_2">
                    <div id="b_footer_2_1">
                        <textarea id="b_textarea1" name="boardText" required></textarea>
                    </div>
                    <div id="b_footer_2_2">
                        <button id="b_commentBtn" onclick="check_commentBtn();">댓글달기</button>
                    </div>
                </div>
                <hr>
                <div style="margin-left: 200px; font-size: 20px; font-weight: bold;">댓글</div>
                <div id="reply-area">
                    <!-- 들어가는곳 댓글 AJAX -->
                </div>
            </div>
        </div>
    </div>
    <script>
    	// 게시글 삭제 (로그인 여부)
	    let b_deleteBoard_btn1 = document.getElementById('b_deleteBoard_btn1');
        
	    if(<%=loginUser != null && loginUser.getMemNo() == listDetail.get(0).getMemNo()%>){
	        b_deleteBoard_btn1.style.display = 'block';
            $('#b_deleteBoard_btn2').click(function(){
            	location.href = '<%=contextPath%>/deleteBoard.bo?bno='+ $('#boardBno').text();
                alert('성공적으로 삭제되었습니다!')
            });
	    } else {
	        b_deleteBoard_btn1.style.display = 'none';
	    }
        
	    // 게시글 수정 (로그인 여부)
        let b_updateBoard_btn = document.getElementById('b_updateBoard_btn');

        if(<%=loginUser != null && loginUser.getMemNo() == listDetail.get(0).getMemNo()%>){
	        b_updateBoard_btn.style.display = 'block';
	        $('#b_updateBoard_btn').click(function(){
	        	location.href = '<%=contextPath%>/updateSelectBoard.bo?bno='+ $('#boardBno').text();
	        })
	    } else {
	        b_updateBoard_btn.style.display = 'none';
	    }

        let b_textarea1 = document.getElementById('b_textarea1');
        let b_commentBtn = document.getElementById('b_commentBtn');

        if(<%=loginUser != null%>){
            b_textarea1.style.display = 'block';
            b_commentBtn.style.display = 'block';
        } else {
            b_textarea1.style.display = 'none';
            b_commentBtn.style.display = 'none';
        }

        function selectReplyList(){
            $.ajax({
                url : 'rlist.bo',
                data : {bno : <%=listDetail.get(0).getBoardNo()%>},
                success : function(list){
                	let result = '';
                	for(let i in list){
                		result += '<table style="margin: auto; margin-top: 20px">'
                                    + '<tbody>'
                                        + '<tr>'
                                            + '<td hidden>' + list[i].groupNum + '</td>'
                		                    + '<td colspan="3" id="td_nickName">' + list[i].nickName + '</td>'
                                        + '</tr>'
                                        + '<tr>'
                		                    + '<td colspan="4" class="td_content">' + list[i].content + '</td>'
                                        + '</tr>'
                                        + '<tr>'
                		                    + '<td id="td_date">' + list[i].commentDate + '</td>';
                                <%if(loginUser != null){%>
                                    if(<%=loginUser.getMemNo()%> == list[i].memNo){
                                        result += '<td id="td_comment"><span class="replyBtn">수정하기</span></td>';
                                    } else {
                                        result += '<td> </td>';
                                    }
                                <%}%>
                                        result += '<td class="cno" hidden>' + list[i].commentNo + '</td>';
                                <%if(loginUser != null){%>
                                	if(<%=loginUser.getMemNo()%> == list[i].memNo){
                                		result += '<td id="td_delete"><span class="deleteBtn">삭제하기</span></td>';
                                	} else {
                                		result += '<td> </td>';
                                	}
                                <%}%>
                                result += '<td> </td>';
                		result += '</tr>'
                                + '</tbody>'
                                + '</table>'
                                + '<textarea class="reply" style="display:none"></textarea>'
                                + '<span class="closeBtn" style="display:none">닫기/</span>'
                                + '<span class="insertBtn" style="display:none">수정하기</span>'
                	};
                	$('#reply-area').html(result);
                },
                error : function(){
                }
            });
        };
        $(function(){
        	selectReplyList();
        })
        
        function check_commentBtn(){
        	
        	if($.trim($('#b_textarea1').val()) == ""){
        		alert('빈칸 입력 안됩니다!!');
        	} else {
        		commentBtn();
        	}
        }
        
		// 댓글 등록 기능(작성후 댓글달기 버튼 클릭시 값전달)
        function commentBtn(){
            $.ajax({
                url : 'rinsert.bo',
                data : {
                    bno : <%= listDetail.get(0).getBoardNo() %>,
            		content : $.trim($('#b_textarea1').val())
                },
                type : 'post',
                success : function(result){
                    if(result > 0){
                        selectReplyList();
                    }
                },
                error : function(){
                }
            });
            $('#b_textarea1').val('');
        };
        // 댓글 삭제 기능(삭제하기 버튼 클릭시 이벤트 발생)
        $('#reply-area').on('click','.deleteBtn',function(){
          $.ajax({
	          url : 'rdelete.bo',
	          data : {
	          bno : <%=listDetail.get(0).getBoardNo()%>,
	          cno : $(this).parent().prev().text()
	          },
		      success : function(result){
                  if(result > 0){
                      selectReplyList();
                  }
		      },
		      error : function(){
			  }
            });
        });
        // 로그인 유무 및 본인의 댓글인지 식별하는 기능
        $('#reply-area').on('click','.replyBtn',function(){
            // 선택한 해당 요소로 접근후 변수에 담기
            var $p = $(this).parents('table').next();
            var $p1 = $(this).parents('table').next().next()
            var $p2 = $(this).parents('table').next().next().next()
            // display 스타일을 통해 식별
            if($p.css('display') == 'none'){
                $p.css('display','block');
                $p1.css('display','inline-block');
                $p2.css('display','inline-block');
                $p.siblings('.reply').css('display','none');
                $p1.siblings('.closeBtn').css('display','none');
                $p2.siblings('.insertBtn').css('display','none');
            } else {
                $p.css('display','none');
                $p1.css('display','none');
                $p2.css('display','none');
            }

            // 댓글 열린창 닫기 기능
            $('.closeBtn').click(function(){
                $(this).siblings('.reply').css('display','none');
                $(this).siblings('.insertBtn').css('display','none');
                $(this).css('display','none');
                $(this).siblings('.closeBtn').css('display','none');
            })
            let a = $(this).parents('table').find('.td_content').text();
            $(this).parents('table').next().text(a);
        });

        // 댓글 수정기능(AJAX)
        $('#reply-area').on('click','.insertBtn',function(){

            if($.trim($('.reply').val()) == ""){
                alert('빈칸 입력 안됩니다!!');
            } else {
                $.ajax({
                url : 'insertReplyAndReply.bo',
                data : {
                    replyText : $.trim($(this).prev().prev().val()),
                    commentNo : $(this).prev().prev().prev().find('.cno').text()
                },
                success : function(result){
                    if(result > 0){
                        selectReplyList();
                    }
                },
                error : function(){
                }
            });
            }
        });

        $(function(){
            $('#b_category_main').click(function(){ <!-- 카테고리 메인으로 이동-->
                location.href = '<%=contextPath%>/board.bo';
            })
        })
        $(function(){
            $('#b_category_all').click(function(){ <!-- 카테고리 전체게시판 이동-->
                location.href = '<%=contextPath%>/allBoard.bo?aPage=1';
            })
        })
        $(function(){
            $('#b_category_free').click(function(){ <!-- 카테고리 자유게시판 이동-->
                location.href = '<%=contextPath%>/freeBoard.bo?aPage=1';
            })
        })
        $(function(){
            $('#b_category_best').click(function(){ <!-- 카테고리 인기글게시판 이동-->
                location.href = '<%=contextPath%>/bestBoard.bo?aPage=1';
            })
        })
        $(function(){
            $('#b_category_notice').click(function(){ <!-- 카테고리 공지사항 이동-->
                location.href = '<%=contextPath%>/noticeList.no'; <!-- 추가 예정 -->
            })
        })
        $(function(){
            $('#b_category_product').click(function(){ <!-- 카테고리 상품게시판 이동-->
                location.href = '<%=contextPath%>/productBoard.bo?aPage=1'; <!-- 추가 예정 -->
            })
        })
    </script>
</body>
</html>