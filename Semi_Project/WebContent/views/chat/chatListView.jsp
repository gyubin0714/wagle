
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그냥 확인용</title>
<style>
    .wrap{
        box-sizing: border-box;
        height: 1000px;
    }

    /*  공용   */
    .c_top, .c_bot{
        width: 100%;
        height: 10%;
        background-color: rgb(185, 228, 245);
    }
    .c_top{
        background-color: rgb(167, 218, 238);
    }
    .c_top>h2{
        font-size: 25px;
        padding-left: 20px;
        padding-top: 15px;
    }
    h3{
        font-size: 25px;
    }
    .c_mid{
        width: 100%;
        height: 80%;
        overflow-y: scroll;
    }
    .c_mid::-webkit-scrollbar {
        width: 5px;
    }
    .c_mid::-webkit-scrollbar-thumb {
        background-color: rgb(29, 29, 29);
    }
    .c_mid::-webkit-scrollbar-track {
        background-color: rgb(255, 255, 255);
    }
    /*    리스트      */
    .c_info{
        border: 1px solid black;
        width: 500px;
        height: 650px;
        float: left;
    }
    .c_list-content{
        border-radius: 10px;
        background-color: rgb(255, 255, 255);
        width: 100%;
        height: 15%;
        margin-top : 10px;
        margin-left: 20px;
    }

    #chat_list_content{
        width: 200px;
	    overflow:hidden;
	    text-overflow:ellipsis;
	    white-space:nowrap;
    }
    #c_alarm{
        width: 20px;
        margin-right: 10px;
    }
    
    #chat-list_mid{
        background-color: rgb(185, 228, 245);
    }
    #chat-list_mid_1{
        width: 10%;
        float:left;
    }
    #chat-list_mid_1>div{
        margin-top: 10px;
        text-align: center;
        line-height: 50px;
    }
    #chat-list_mid_2{
        width: 80%;
        float:left;
    }

    /* 내용 */
    .myChat, .yourChat{
        width: 60%;
        height: auto;
        font-size: 20px;
        margin-bottom: 15px;
        margin-left: 10px;
        margin-top: 10px;
    }
    .myChat{
        margin-left: 185px;
        text-align: right;
    }
    #c_text-footer>input{
        width: 78%;
        height: 100%;
    }
    #c_text-footer>button{
        width:20%;
        height: 100%;
    }
    #c_text-header-name{
        height: 100%;
        width: 90%;
        float: left;
    }
    #c_text-header-name+button{
        display: block;
    }
    #c_text-header-name>table{
        width: 100%;
    }
    #c_text-header-name td{
        width : 33%
    }
    #c_text-header{
        padding: 15px;
        position: relative;
    }
    .c_chatTime{
        font-size: 14px;
    }
    .c_chatContent{
        border-radius: 10px;
        background-color: white;
        font-size: 16px;
    }
    #c_text-content{
        background-color: rgb(185, 228, 245);
    }
    #c_chatCloseBtn{
        border: 1px solid black;
        background-color: white;
        width: 40px;
        height: 35px;
    }
    #c_close_btn{
        font-size: 30px;
        border: none;
        background-color: rgb(167, 218, 238);
        position: absolute;
        right: 7px;
        top: 4px;
    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
    <button onclick="c_view_list_ro();" class="btn btn-sm btn-secondary">채팅 보기</button>

    <!--------------------------------------내 채팅 목록------------------------------------>
    <div class="wrap">
        <div id="chat-list" class="c_info" hidden>
            <div id="c_list-header" class="c_top">
                <h2>채팅목록</h2>
            </div>
            <div id="chat-list_mid" class="c_mid">
                <div id="chat-list_mid_1"></div>
                <div id="chat-list_mid_2"></div>
           </div>
           <br clear="both" hidden>
            <div id="c_list-footer" class="c_bot" align="center">
                <button id="c_update_btn" onclick="c_room_update();" hidden class="btn btn-sm btn-secondary">선택삭제</button>
                <button onclick="c_update_btnOnOff();" class="btn btn-sm btn-secondary">대화창 편집</button>
            </div>
        </div>
        
        <script>
            // 채팅 목록 불러오기 반복
            function c_view_list_ro(){
                if($('#chat-list').attr('hidden') == 'hidden'){
                    $('#chat-list').removeAttr('hidden');
                    c_view_list();
                    var c_list_view = setInterval(c_view_list,1000);
                } else{
                    $('#chat-list').attr('hidden',true);
                    chatClose();
                }
            }
            // 채팅 목록 불러오기 후 삽입
            function c_view_list(){
                console.log('dd');
                $.ajax({
                    url:'list.ch',
                    data:{memNo:<%=loginUser.getMemNo()%>},
                    success:function(list){
                        let str = '';
                        if(list.length == 0){
                            str += '채팅이 존재하지 않습니다.';
                        }
                        for(let i = 0; i < list.length; i++){
                            // 상품이 있을 때 상품정보 추가
                            let productNo = '';
                            if(list[i].productNo != 0){
                                productNo = list[i].productNo;
                            }
                            str +='<table class="c_list-content" >'
                                    +'<tr>'
                                        +'<td hidden>'+list[i].roomNo+'</td>'
                                        +'<td rowspan="2" width="80">사진</td>'
                                        +'<th>'+list[i].nickName+'</th>'
                                        +'<td width="100">'+ list[i].chatTime+'</td>'
                                    +'</tr>'
                                    +'<tr>'
                                        +'<td><div id="chat_list_content">'+list[i].chatContent+'</div></td>'
                                        +'<td align="right">'
                                            +'<div id="c_alarm" align="right">'
                                                +'<input type="hidden" value="'+list[i].alarmYN+'">';
                                            if(list[i].alarmYN == 'Y') {
                                                str +='<img src="<%=contextPath%>/resources/chat/알람on.png" width="20" height="20">';
                                            } else {
                                                str +='<img src="<%=contextPath%>/resources/chat/알람off.png" width="20" height="20">';
                                            } 
                                            str +='</div>'
                                        +'</td>'
                                    +'</tr>'
                                +'<input type="hidden" name="c_list_productNo" value="'+productNo+'">'
                                +'</table>';
                        }
                        $('#chat-list_mid_2').html(str);
                    },
                    error:function(){
                        alert('리스트 조회 실패');
                    }
                });
            }

            // 삭제버튼 및 체크박스 on off
            function c_update_btnOnOff(){
                if($('#c_update_btn').attr('hidden') == 'hidden'){
                    $('#c_update_btn').attr('hidden',false);
                    c_create_checkbox();
                }else{
                    $('#c_update_btn').attr('hidden',true);
                    $('#chat-list_mid_1').html('');
                }
            }
            // 체크 박스 만들기
            function c_create_checkbox(){
                let str = '';
                let $height = $('#chat-list_mid_2>table').css('height');
                for(let i =0; i < $('#chat-list_mid_2>table').length; i++){
                    str += '<div><input type="checkbox" name="c_update"></div>';
                }
                $('#chat-list_mid_1').html(str);
                
                $('#chat-list_mid_1>div').each(function(){
                    $(this).css('height',$height);
                });
            }
            // 선택된 채팅방 삭제
            function c_room_update(){
                let index = 0;
                let arr = []; 
                $('#chat-list_mid_1 input').each(function(){
                    if($(this).is(':checked')){
                        arr.push(index);
                    }
                    index++;
                });
                for(let i in arr){
                    let $roomNo = $('#chat-list_mid table').eq(arr[i]).find('td').eq(0).text();
                    c_room_update_ajax($roomNo);
                }
            }

            // 채팅방 삭제 ajax
            function c_room_update_ajax($roomNo){
                $.ajax({
                    url:'updateRoom.ch',
                    data:{
                        roomNo: $roomNo
                    },
                    success:function(result){
                        if(result > 0){
                            alert('선택된 채팅창 삭제 성공');
                        }
                        chatClose();
                    },
                    error:function(){
                        alert('채팅창 삭제 실패');
                    },
                    complete:function(){
                        c_view_list();
                        $('#chat-list_mid_1').html('');
                        $('#c_update_btn').attr('hidden',true);
                    }
                });
            }
                
        </script>

<!-- ---------------------------------- 채팅창 내용 ----------------------------------- -->
        <div id="chat-text" class="c_info" hidden>
            <div id="c_text-header" class="c_top">
                <div id="c_text-header-name">
                    <table>
                        <tr>
                            <td rowspan="2"></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button hidden>구매하기</button></td>
                            <input type="hidden" name="c_seller">
                        </tr>
                        <form action="buyProduct.au" method="post">
                            <input type="hidden" name="productNo">
                            <button hidden id="a_buy_product"></button>
                        </form>
                    </table>
                </div>
                <button id="c_close_btn" onclick="chatClose();">&times;</button>
            </div>
            <div id="c_text-content" class="c_mid">
                
            </div>
            <div id="c_text-footer" class="c_bot">
                <input type="hidden" id="c_inputRoomNo">
                <input type="text" id="c_send-message">
                <button id="c_send_btn" onclick="insertChat();" disabled>보내기</button>
            </div>
        </div>
    </div>
        <script>
            // 채팅방 클릭시 불러오는 채팅내용
            $('#chat-list_mid').on('click','table',function(){
                let arr = '';
                let $name =$(this).find('th').eq(0).text();
                let $roomNo =$(this).find('td').eq(0).text();
                let $productNo;
                if($(this).find('input[name=c_list_productNo]').val() != ''){
                    $productNo= $(this).find('input[name=c_list_productNo]').val();
                } else{
                    $productNo = 0;
                }
                
                $('#chat-text').removeAttr('hidden');
                
                $('#c_text-header-name').find('td').eq(0).html('<h3>'+ $name +'</h3>');
                
                $('#c_inputRoomNo').val($roomNo);
                
                c_productList($productNo);
                c_buy_btnOnOff();
                setInterval(c_input_btnOnoff,100);
                loadChat_ro();
            });

            function loadChat_ro(){
                setInterval(loadChat,1000);
            }

            // 상품번호로 상품정보 조회
            function c_productList($productNo){
                $.ajax({
                    url : 'listproduct.ch',
                    data:{
                        productNo:$productNo
                    },
                    success:function(result){
                        if(result != null){
                            $('#c_text-header-name').find('td').eq(1).text(result.productName);
                            $('#c_text-header-name').find('td').eq(2).text(result.productPrice);
                            $('input[name=c_seller]').val(result.memNo);
                        } else{
                            $('#c_text-header-name').find('td').eq(1).text('');
                            $('#c_text-header-name').find('td').eq(2).text('');
                            $('input[name=c_seller]').val('');
                        }
                       
                    },
                    error:function(){
                        $('#c_text-header-name').find('td').eq(1).text('');
                        $('#c_text-header-name').find('td').eq(2).text('');
                        $('input[name=c_seller]').val('');
                    },
                    complete:function(){
                        $('input[name=productNo]').val($productNo);
                        c_buy_btnOnOff();
                    }
                });
            };

            // 채팅방에서의 구매버튼 활성화
            function c_buy_btnOnOff(){
                let $seller = $('input[name=c_seller]').val();

                if($('#c_text-header-name').find('td').eq(2).text() != ''){
                    if($seller != <%=loginUser.getMemNo()%>){
                        $('#c_text-header-name>table button').removeAttr('hidden');
                    }
                    else{
                        $('#c_text-header-name>table button').attr('hidden', true);
                    }
                } else{
                    $('#c_text-header-name>table button').attr('hidden', true);
                }
            };

            // 채팅창 내용 불러오기 
            function loadChat(){
                console.log($('#c_inputRoomNo').val());
                $.ajax({
                    url:'detail.ch',
                    data:{roomNo : $('#c_inputRoomNo').val()},
                    success:function(list){
                        let result = '';
                        for(let i in list){
                            if(list[i].chatContent != '&nbsp;'){
                                if(list[i].memNo == <%=loginUser.getMemNo()%>){
                                    result +='<div class="myChat">'
                                            +   '<div class="c_chatContent">'
                                            +       list[i].chatContent 
                                            +   '</div>'
                                            +   '<div class="c_chatTime">'
                                            +       list[i].chatTime 
                                            +   '</div>'  
                                            +'</div>';
                                } 
                                else {
                                    result +='<div class="yourChat">'
                                            +   '<div class="c_chatContent">'
                                            +       list[i].chatContent 
                                            +   '</div>'
                                            +   '<div class="c_chatTime">'
                                            +       list[i].chatTime 
                                            +   '</div>'
                                            +'</div>';
                                }
                            }
                        }
                        $('#c_text-content').html(result);
                    },
                    error:function(){
                        console.log('채팅읽어오기실패');
                    },
                    complete:function(){
                        $('#c_text-content').scrollTop($('#c_text-content').prop('scrollHeight'));
                    }
                });
            }
            
            // 채팅 보내기
            function insertChat(){

                let $roomNo = $('#c_inputRoomNo').val();
                let $content = $.trim($('#c_send-message').val());

                if($content != ''){
                    $.ajax({
                        url:'insertChat.ch',
                        data:{
                            memNo:<%=loginUser.getMemNo()%>,
                            roomNo:$roomNo,
                            content:$content
                        },
                        type:'post',
                        success:function(result){
                            if(result > 0){
                                $('#c_send-message').val('');
                            }
                            else{
                                $('#c_send-message').val('').focus();
                                alert('채팅 보내기에 실패하였습니다.');
                            }
                        },
                        error:function(){
                            console.log('채팅 보내기 실패');
                        },
                        complete:function(){
                            loadChat($roomNo);
                        }
                    });
                }
            }

            // 채팅창 공백 검사 
            function c_input_btnOnoff(){
                if($('#c_send-message').val() == ''){
                    $('#c_send_btn').attr('disabled',true);
                }
                else{
                    $('#c_send_btn').removeAttr('disabled');
                }
            };

            // 채팅창 끄기
            function chatClose(){
                $('#chat-text').attr('hidden',true);
            }

            // 알람 on off
            $('#chat-list_mid').on('click','#c_alarm img',function(){
                let $roomNo = $(this).parents('tr').prev().children().eq(0).text();
                let $alarm;
                if($(this).prev().val()== 'Y'){
                    $alarm = 'N';
                }else{
                    $alarm = 'Y';
                }
                $.ajax({
                    url:'updateAlarm.ch',
                    data:{
                        memNo:<%=loginUser.getMemNo()%>,
                        roomNo:$roomNo,
                        alarm:$alarm
                    },
                    complete:function(){
                        c_view_list();
                    }
                });
            });

            // 구매하기 버튼 클릭
            $('#c_text-header-name>table button').click(function(){
                a_buy_product();
            });

            // 상품구매 ajax
            function a_buy_product(){
                if(confirm('상품을 구매하시겠습니까?')){
                    $('#a_buy_product').click();
                }
            };

            // 낙찰 메세지에서 결제하기 버튼 클릭
            $('#c_text-content').on('click', '#a_buy_auction',function(){
                $('#c_text-header-name form').attr('action','completeAuction.au');
                $('#a_buy_product').click();
            });
        </script>
    
</body>
</html>