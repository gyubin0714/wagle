<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modal</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    
<style>
    .a_modal_content{
        width: 100%;
        height: 100%;
        background: rgb(215, 215, 215);
        box-sizing: border-box;
        padding: 34px;
    }
    .a_modal_header{
        width: 100%;
        box-sizing: border-box;
        height: 13%;
    }
    #a_modal_title{
        width: 85%;
        height: 100%;
        box-sizing: border-box;
        float: left;
        padding: 10px;
    }
    #a_close_btn_div{
        width: 15%;
        height: 100%;
        box-sizing: border-box;
        float: left;
    }
    #a_close_btn{
        background-color: rgb(109, 194, 237);
        border: none;
        width: 100%;
        height: 100%;
        font-size: 35px;
        padding-bottom: 5px;
    }
    .a_modal_header{
        border-top: 1px solid black;
        border-right: 1px solid black;
        border-left: 1px solid black;
    }
    #a_modal_head{
        width: 100%;
    }
    #a_modal_head_1{
        width: 90%;
        height: 100%;
        float: left;
    }
    #a_modal_head_2{
        width: 10%;
        height: 100%;
        float: left;
        text-align: center;
        font-size: 22px;
        padding-bottom: 3px;
        font-weight: bold;
    }
    #a_buyAuction_title{
        width: 100%;
        height: 100%;
        font-size: 20px;
        font-weight: bold;
        padding-left: 10px;
        padding-top: 3px;
    }
    #a_modal_header_1,#a_modal_header_2{
        float:left;
        height:100%;
    }
    #a_modal_header_1{
        width: 90%;
        padding-left: 30px;
    }
    #a_modal_header_1>h2{
        margin-top:17px;
    }
    #a_modal_header_2{
        width: 10%;
        text-align: center;
        line-height: 50px;
        font-size: 50px;
        cursor: pointer;
    }
    .a_modal hr{
        border: 1px solid black;
    }
    #a_modal_table{
        width: 100%;
        height:45%;
    }
    #a_modal_table th{
        width: 30%;
        font-size: 20px;
    }
    #a_modal_table th+td{
        width: 50%;
    }
    #a_modal_table td{
        text-align: center;
        padding: 15px;
    }
    #a_modal_table th,.a_td_time{
        padding: 35px 10px;
    }
    #a_modal_table th>div{
        width: 100%;
        height:100%;
        border-top: none;
        border-bottom: none;
        border-left: none;
        border-right: 1px solid black;
    }
    .a_td{
        background-color: #fff;
        border-radius: 7px;
        border: 1px solid black;
        height: 60%;
        line-height: 37px;
    }
    .a_td_time>div{
        background-color: rgb(66, 66, 66);
        color: #fff;
        border-radius:50px;
        height: 50%;
        line-height: 32px;
    }
    #a_modal_div{
        width: 100%;
        height: 35%;
    }
    .a_price{
        width:100%;
        height: 37%;
        border-radius: 50px;
        background-color: #fff;

    }
    #a_buyPrice{
        margin-top: 30px;
    }
    .a_price>div{
        float: left;
        padding-top: 9px;
        padding-bottom: 9px;
        padding-right: 9px;
        padding-left: 30px;
        height: 100%;
    }
    .a_price_div{
        width: 33%;
        font-size: 23px;
    }
    .a_price_div+div{
        width: 42%;
        text-align: right;
        font-size: 22px;
        line-height: 43px;
    }
    .a_price_div+div+div{
        width: 25%;
        padding: 9px 15px;
    }
    .a_price_div+div+div>button{
        display:block;
        width: 90%;
        height:100%;
        border-radius: 7px;
        font-size: 17px;
    }
    .a_price_div>div{
        width: 100%;
        height: 100%;
        border-right: 1px solid black;
        border-top: none;
        border-bottom: none;
        border-left: none;
        line-height: 43px;
    }
    #a_recode:hover{
        cursor: pointer;
        color: #535353;
    }
    /* 경매기록 모달*/
    .a_recode_modal{ 
        width: 400px; 
        height: 450px; 
        background: rgb(109, 194, 237); 
        position: fixed;
        z-index: 51;
        display: none;
        margin: 0px auto;
        top: 100px;
        left: 620px;
    }
    .a_modal_content{
        border-bottom: 1px solid black;
        border-left: 1px solid black;
        border-right: 1px solid black;
    }
    #a_recode_table{
        overflow-y: scroll;
        text-align: center;
    }
    #a_recode_table>table{
        width: 100%;
    }
    #a_recode_table th{
        width: 33.3%;
    }
    /* 경매 입찰 */
    .a_buyAuction_modal{
        width: 400px; 
        height: 300px; 
        background: rgb(109, 194, 237); 
        position: fixed;
        z-index: 50;
        display: none;
        margin: 0px auto;
        top: 15%;
        left: 50%;
        transform: translateX( -50% );
    }
    #a_input_priceNow{
        width: 250px;
        height: 40px;
        font-size: 14px;
    }
    #a_priceNowBtn{
        border: 1px solid black;
        background-color: rgb(109, 194, 237);
        width: 60px;
        height: 38px;
    }
</style>
</head>
<body>
    <div class="a_modal_header">
        <div id="a_modal_header_1"><h2>경매</h2></div>
        <div id="a_modal_header_2">&times;</div>
    </div>
    <br clear="both" hidden>
    <div class="a_modal_content">
        <h3>▶ <%= a.getProductName() %></h3>
        <hr>
        <input type="hidden" value="<%= a.getAuctionNo() %>">
        <table id="a_modal_table">
            <tr>
                <th><div>시작가</div></th>
                <td>
                    <div class="a_td" id="a_modal_start">
                        <%= a.getPriceMin() %>
                    </div>
                </td>
            </tr>
            <tr>
                <th><div>남은 시간</div></th>
                <td class="a_td_time" id="a_limitTime">
                    <div>
                        *일 **시 **분 **초
                    </div>
                </td>
            </tr>
            <tr>
                <th><div>입찰자 수</div></th>
                <td>
                    <div class="a_td" id="a_modal_count"></div>
                </td>
                <td>
                    <div id="a_recode">경매기록</div>
                </td>
            </tr>
        </table>
        <div id="a_modal_div">
            <div class="a_price" id="a_nowPrice">
                <div class="a_price_div">
                    <div>현재입찰가</div>
                </div>
                <div></div>
                <div>
                    <button class="a_none_login btn btn-sm btn-dark">입찰</button>
                </div>
            </div>
            <div class="a_price" id="a_buyPrice">
                <div class="a_price_div">
                    <div>즉시입찰가</div>
                </div>
                <div><%=a.getProductPrice()%></div>
                <div>
                    <form action="buyProduct.au" method="post">
                        <input type="hidden" name="productNo" value="<%=a.getProductNo()%>">
                        <button hidden></button>
                    </form>
                    <button class="a_none_login btn btn-sm btn-primary" onclick="a_confirm_buyProduct();">즉시구매</button>
                </div>
            </div>
        </div>
    </div>
    <div class="a_recode_modal">
        <div class="a_modal_header">
            <div id="a_modal_title">
                <h2>경매 기록</h2>
            </div>
            <div id="a_close_btn_div">
                <button id="a_close_btn">&times;</button>
            </div>
        </div>
        <div class="a_modal_content">
            <div id="a_recode_table">
                <table style="border: 1px solid black;">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>닉네임</th>
                            <th>누적횟수</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>

        </div>
    </div>
    <div class="a_buyAuction_modal">
        <div class="a_modal_header" id="a_modal_head">
            <div id="a_modal_head_1">
                <div id="a_buyAuction_title">경매 입찰</div>
            </div>
            <div id="a_modal_head_2">
                <div id="a_buyAuction_close">&times;</div>
            </div>
        </div>
        <div class="a_modal_content">
            현재 입력 가능한 최소 입찰 금액은<br>
            <input type="text" disabled name="a_che_priceNow" style="text-align: right;">원 입니다.
            <div id="a_buyAucion_btn">
                <hr>
                <input type="text" id="a_input_priceNow" placeholder="입찰 할 금액을 입력해주세요." name="a_send_priceNow">
                <button id="a_priceNowBtn">입찰</button>
            </div>
        </div>
    </div>
    <script>
        // 경매 기록창 띄우기
        $('#a_recode').click(function(){
            if($('.a_recode_modal').css('display') == 'none'){
                $('.a_recode_modal').css('display','block');
                selectAuctionRecode();
            } else {
                $('.a_recode_modal').css('display','none');
            }
        });
        // 경매 기록을 불러오기
        function selectAuctionRecode(){
            $.ajax({
                url : 'selectRecode.au',
                data : {
                    auctionNo : <%= a.getAuctionNo() %>
                },
                success:function(list){
                    let str ='';

                    for(let i = 0; i < list.length; i++){
                        str +=  '<tr>'
                                    +'<td>'+list[i].rowNum+'</td>'
                                    +'<td>'+list[i].nickname+'</td>'
                                    +'<td>'+list[i].rank+'</td>'
                                +'</tr>';
                    }

                    $('#a_recode_table>table>tbody').html(str);
                }
            });
        }
        // 경매 기록창 닫기
        $('#a_close_btn').click(function(){
            $('.a_recode_modal').css('display','none');
        });

        // 경매 입찰창 열기
        $('#a_nowPrice button').click(() => {
            $('.a_buyAuction_modal').css('display','block');
        });

        // 경매 입찰창 닫기
        $('#a_buyAuction_close').click(function(){
            $('.a_buyAuction_modal').css('display','none');
        });

        // 입찰가 체크
        $('#a_buyAucion_btn button').click(() => {
            if($('input[name=a_che_priceNow]').val()<$('input[name=a_send_priceNow]').val()){
                a_sendPrice();
            }else{
                alert('최소입찰가보다 높게 입력해주세요!');
                $('input[name=a_send_priceNow]').val('');
                $('input[name=a_send_priceNow]').focus();
            }
        });

        // 입찰하기
        function a_sendPrice(){
            $.ajax({
                url:'updatePriceNow.au',
                data : {
                    auctionNo : <%=a.getAuctionNo() %>,
                    priceNow : $('input[name=a_send_priceNow]').val()
                },
                success : function(result){
                    if(result = 0){
                        alert('입찰 실패');
                    }   
                },
                complete : function(){
                    selectAuctionRecode('');
                    $('input[name=a_send_priceNow]').val('');
                    $('.a_buyAuction_modal').css('display','none');
                }
            });
        }
        
        // 즉시구매 버튼 클릭시 confirm 창 띄우기
        function a_confirm_buyProduct(){
            if(confirm('상품을 구매하시겠습니까?')){
                $('#a_buyPrice input[name=productNo]').next().click();
            }
        }

    </script>
</body>
</html>