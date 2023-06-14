<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.product.model.vo.ProductCategory" %>
<%
    ArrayList<ProductCategory> list = (ArrayList<ProductCategory>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
<style>

    /*테이블*/
    #p_top{width : 1300px; border:none;}
    #p_middle{width : 1300px; border:none;}
    #p_bottom{width : 1300px; border:none;}
    #p_top th{ width : 200px; height : 70px; text-align: center; font-size: larger;}
    #p_middle th{ width : 200px; height : 70px; text-align: center; font-size: larger;}
    #p_bottom th{ width : 200px; height : 70px; text-align: center; font-size: larger;}

    .input_style{
        height: 40px;
        width:800px;
        border : 1px solid #bbb;
        border-radius:4px;
        padding : 10px 12px;
        font-size: 15px;
    }
    .inputNumber_style{
        height: 40px;
        width:300px;
        border : 1px solid #bbb;
        border-radius:4px;
        padding : 10px 12px;
        font-size: 15px;
        text-align: right;
    }

    #p_textArea{
        width:800px;
        border : 1px solid #bbb;
        border-radius:4px;
        padding : 10px 12px;
        font-size: 14px;
    }

    /*버튼*/
    #p_btn-insert{
        height: 40px; 
        width:150px; 
        border-radius: 4px; 
        color: black; 
        background-color: rgb(185, 228, 245);
        border:none;
        overflow:hidden; 
    }
    #p_btn-cancel{
        height: 40px; 
        width:150px; 
        border-radius: 4px; 
        background-color: rgb(216, 216, 216); 
        color:black; 
        border:none;
        overflow:hidden; 
    }
    .p_btn-style{
        height: 40px; 
        width:100px; 
        border-radius: 4px; 
        background-color: rgb(230, 230, 230); 
        color:black; 
        border:none;
        overflow:hidden; 
    }
    #p_btn-insert:hover{transform: scale(1.1);}
    #p_btn-cancel:hover{transform: scale(1.1);}
    .p_btn-style:hover{transform: scale(1.1);}

    /*카테고리*/
    .p_category{
        height: 40px;
        width:150px;
        border : 1px solid #bbb;
        border-radius:4px;
        padding : 10px 12px;
        font-size: 14px;
    }

    /*이미지*/
    #p_productImg1{
        width : 200px;
        height : 200px;
    }
    .contentImg{
        width : 200px;
        height : 200px;
    }
    
</style>
</head>
<body>
	
    <%@ include file="../common/menubar.jsp" %>

    <div class="wrap">
        <br>
        <form action="<%= contextPath%>/insert.po" id="p_insert-form" method="post" enctype="multipart/form-data">
            <input type="hidden" name="memNo" value="<%= loginUser.getMemNo()%>">
            <table class="p_table" id="p_top" align="center">
                <tr><th colspan="6" style="text-align: left; border-bottom: 1px solid black; "><h2>상품 등록하기</h2></th></tr>
                <tr> <th style="height: 10px;"></th></tr>
                <tr>
                    <th>상품이미지</th>
                    <td><img id="p_productImg1" src="https://moyeoyou.kr/assets/common/img/user/default_photo.jpg" alt=""></td>  
                        <td><img class="contentImg" id="p_productImg2" src="https://moyeoyou.kr/assets/common/img/user/default_photo.jpg" alt=""></td>
                        <td><img class="contentImg" id="p_productImg3" src="https://moyeoyou.kr/assets/common/img/user/default_photo.jpg" alt=""></td>
                        <td><img class="contentImg" id="p_productImg4" src="https://moyeoyou.kr/assets/common/img/user/default_photo.jpg" alt=""></td>
                        <td><img class="contentImg" id="p_productImg5" src="https://moyeoyou.kr/assets/common/img/user/default_photo.jpg" alt=""></td>
                        <div class="file-area">
                            <input type="file" id="p_file1" name="p_file1" required onchange="loadImg(this, 1);">
                            <input type="file" id="p_file2" name="p_file2" onchange="loadImg(this, 2);">
                            <input type="file" id="p_file3" name="p_file3" onchange="loadImg(this, 3);">
                            <input type="file" id="p_file4" name="p_file4" onchange="loadImg(this, 4);">
                            <input type="file" id="p_file5" name="p_file5" onchange="loadImg(this, 5);"> 
                        </div>
                </tr>
                <tr>
                    <th>제목 </th>
                    <td colspan="5"><input class="input_style" type="text" name="productName" required></td>
                </tr>
                <tr>
                    <th>카테고리 </th>
                    <td colspan="5">
                        <select name="category" class="p_category" id="p_category1" onchange="selectCategory();">
                            <% for(ProductCategory pc : list){ %>
                                <% if(pc.getCateParent() == 0) {%>
                                	<option value="<%=pc.getCateNo()%>"><%=pc.getCateName() %></option>
                           		<% } %>
                            <% } %>
                        </select>
                        <select name="category2" class="p_category" id="p_category2">
                            <option></option>
                        </select>
                    </td>

                    <script>
                        selectCategory();
                        function selectCategory(){
                         var selectCateNo = $("#p_category1 :selected").val();

                         $.ajax({
                                url : 'selectCategory.po',
                                data : { 
                                    cateNo :  selectCateNo
                                },
                                success : function(list){
                                    var result = '';
                                    for(let i=0; i < list.length;i++){
                                        result += '<option value="' + list[i].cateNo + '">' + list[i].cateName + '</option>';
                                    };
                                    $('#p_category2').html(result);
                                },
                                error : function(){
                                    console.log('실패');
                                }

                            });

                        }
                        
                    </script>
                </tr>
                <tr>
                    <th>거래지역</th>
                    <td colspan="5">
                        <input class="input_style" type="text" name="location" id="p_location" placeholder="주소" required>
                        <input type="button" onclick="execDaumPostcode()" value="주소 찾기" class="p_btn-style"><br>
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
                
                                    document.getElementById("p_location").value = addr;
                                }
                            }).open();
                        }
                    </script>
                </tr>
                <tr>
                    <th>상품 상태</th>
                    <td colspan="5">
                        <input type="radio" name="productStatus" value="중고 상품" checked>중고 상품
                        <input type="radio" name="productStatus" value="새 상품">새 상품
                    </td>
                </tr>
                <tr>
                    <th>가격</th>
                    <td colspan="5">
                        <input class="inputNumber_style" type="number" name="productPrice">원
                        <input type="hidden" name="p_method" value="P">
                        <input type="checkbox" id="p_checkAcution" name="a_method" value="A">경매로 판매</label>
                    </td>
                </tr>
            </table>
            <div id="check-auction">
                <table id="p_middle" align="center">
                    <tr>
                        <th>경매시작가</th>
                        <td colspan="5"><input type="number" class="inputNumber_style" name="a_startPrice">원</td>
                    </tr>
                    <tr>
                        <th>즉시 구매가 </th>
                        <td colspan="5"><input type="number" class="inputNumber_style" name="a_nowPrice" id="pa_price">원 <input type="checkbox" id="no_price">즉시구매가 입력 안함</td>
                    </tr>
                    <tr>
                        <th>경매 시작시간 </th>
                        <td colspan="5">
                            <input type="datetime-local" class="inputNumber_style" name="a_startTime" id="p_startTime">
                            <input type="checkbox">현재시간
                        </td>
                    </tr>
                    <tr>
                        <th>경매 마감시간 </th>
                        <td colspan="5">
                            <input type="radio" name="a_deadline" value="3" checked>3일
                            <input type="radio" name="a_deadline" value="5">5일
                            <input type="radio" name="a_deadline" value="7">7일
                        </td>
                    </tr>
                </table>
            </div>
                
            <table id="p_bottom" align="center" >
                <tr>
                    <th>설명</th>
                    <td colspan="5"> <textarea id="p_textArea" name="productDesc" rows="10" cols="50" style="resize: none;" required></textarea></td>
                </tr>
                <tr>
                    <th>배송방법</th>
                    <td colspan="5">
                        <input type="radio" name="productDelv" value="택배" checked>택배
                        <input type="radio" name="productDelv" value="직거래">직거래
                    </td>
                </tr>
            </table>
            
            <div align="center">
                <button type="submit" onclick="return insertProduct();" id="p_btn-insert">등록하기</button>
                <button type="button" onclick="history.back();" id="p_btn-cancel">취소하기</button>
            </div>
            <script>

                $(function(){
                    $('.file-area').hide();

                    $('#p_productImg1').click(function(){
                        $('#p_file1').click();
                    });
                    $('#p_productImg2').click(function(){
                        $('#p_file2').click();
                    });
                    $('#p_productImg3').click(function(){
                        $('#p_file3').click();
                    });
                    $('#p_productImg4').click(function(){
                        $('#p_file4').click();
                    });
                    $('#p_productImg5').click(function(){
                        $('#p_file5').click();
                    });

                })

                function loadImg(inputFile, num){

                    if(inputFile.files.length == 1){
                        let reader = new FileReader();

                        var data = reader.readAsDataURL(inputFile.files[0]);

                        reader.onload = function(e){
                            switch(num){
                                case 1: $('#p_productImg1').attr('src', e.target.result); break;
                                case 2: $('#p_productImg2').attr('src', e.target.result); break;
                                case 3: $('#p_productImg3').attr('src', e.target.result); break;
                                case 4: $('#p_productImg4').attr('src', e.target.result); break;
                                case 5: $('#p_productImg5').attr('src', e.target.result); break;
                            }
                        }
                    } else{
                        let str="https://moyeoyou.kr/assets/common/img/user/default_photo.jpg";
                        switch(num){
                                case 1: $('#p_productImg1').attr('src', str); break;
                                case 2: $('#p_productImg2').attr('src', str); break;
                                case 3: $('#p_productImg3').attr('src', str); break;
                                case 4: $('#p_productImg4').attr('src', str); break;
                                case 5: $('#p_productImg5').attr('src', str); break;
                            }
                    }
                }

                $(function(){
                    $('#check-auction').hide();
                    // 경매 클릭시 경매화면 보이기 및 가격잠금
                    $('#p_checkAcution').click(function(){
                        var checked = $('#p_checkAcution').is(":checked");
                        if(checked == true){
                            $('#check-auction').show();
                            $('input[name=productPrice]').attr('disabled',true);
                        } else{
                            $('#check-auction').hide();
                            $('input[name=productPrice]').attr('disabled',false);
                        }
                    
                    });

                });

                // 경매 추가 controller로 연결
                $('button[type=submit]').click(function(){
                    if($('#p_checkAcution').is(":checked")){
                        $('form').attr('action',"<%= contextPath%>/insert.au");
                    }
                });

                // 즉시구매가 입력여부
                $('#no_price').change(function(){
                    if($('#no_price').is(':checked')){
                        $('#pa_price').attr('disabled',true);
                    }else{
                        $('#pa_price').attr('disabled',false);
                    }
                });

                // 시작시간을 현재시간으로 초기화
                $('#p_startTime + input').change(function(){
                    if($('#p_startTime + input').is(':checked')){
                        let date = new Date(new Date().toString().split('GMT')[0]+' UTC').toISOString().slice(0, -5);
                        $('#p_startTime').val(date);
                        $('#p_startTime').attr('min',date);
                    }
                    console.log(Date($('#p_startTime').val()));
                });

                function insertProduct(){

                    var insertProduct = confirm("작성하시겠습니까?");

                    if(insertProduct == true) {
                        alert("작성이 완료되었습니다.");
                        return true;
                    } else {
                        alert("작성이 취소되었습니다.");
                        return false;
                    }
                }

            </script>
            
        </form>
        <br><br><br><br><br>
    </div>
    
</body>
</html>