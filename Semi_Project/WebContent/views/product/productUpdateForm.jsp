<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.product.model.vo.ProductCategory, com.kh.product.model.vo.*, com.kh.common.model.vo.*" %>
<%
	Product p = (Product)request.getAttribute("p");
    ArrayList<Attachment> titleAt = (ArrayList<Attachment>)request.getAttribute("titleAt");
	ArrayList<Attachment> at = (ArrayList<Attachment>)request.getAttribute("at");
    ArrayList<ProductCategory> list = (ArrayList<ProductCategory>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록 상품 수정하기</title>
<style>
    /*테이블*/
    #p_table{width : 1300px; border:none;}
    #p_table th{ width : 200px; height : 70px; text-align: center; font-size: larger;}

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
    #p_btn-update{
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
    #p_btn-update:hover{transform: scale(1.1);}
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
    #p_titleImg{
        width : 300px;
        height : 300px;
        border: 1px #bbb solid;
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

        <form action="<%= contextPath%>/update.po" id="p_update-form" method="post" enctype="multipart/form-data">
            <input type="hidden" name="pno" value="<%= p.getProductNo() %>" %>
            <table id="p_table" align="center">
                <tr><th colspan="6" style="text-align: left; border-bottom: 1px solid black; "><h2>상품 등록하기</h2></th></tr>
                <tr>
                    <th rowspan="2">상품이미지</th>
                    <td colspan="4" >
                        <h5>*대표이미지</h5>
                        <% if(at.size() > 0){%>
                            <img id="p_titleImg" src="<%= contextPath %>/<%=at.get(0).getFilePath() %>/<%=at.get(0).getChangeName() %>" >
                            <input type="hidden" name="originFileNo0" value="<%= at.get(0).getFileNo() %>">
                            <input type="hidden" name="originFileName0" value="<%= at.get(0).getChangeName() %>">
                            <% } %> 
                            
                            <div id="p_file-area">
                                <input type="file" id="p_reUpfile0" name="p_reUpfile0" onchange="loadImg(this, 1);">
                                <input type="file" id="p_reUpfile1" name="p_reUpfile1" onchange="loadImg(this, 2);">
                                <input type="file" id="p_reUpfile2" name="p_reUpfile2" onchange="loadImg(this, 3);">
                                <input type="file" id="p_reUpfile3" name="p_reUpfile3" onchange="loadImg(this, 4);">
                                <input type="file" id="p_reUpfile4" name="p_reUpfile4" onchange="loadImg(this, 5);">
                            </div>
                           
                    </td>
                </tr>
                <tr>
                    <% for(int i = 1; i < at.size(); i++) { %>
                        <td>
                            <img id="p_contentImg<%= i %>" class="contentImg" src="<%= contextPath %>/<%=at.get(i).getFilePath() %>/<%=at.get(i).getChangeName() %>" alt="">
                        </td>
                            <input type="hidden" name="originFileNo<%= i %>" value="<%= at.get(i).getFileNo() %>">
                            <input type="hidden" name="originFileName<%= i %>" value="<%= at.get(i).getChangeName() %>">
                            


                    <% } %>
                    <% for(int i = at.size(); i < 5; i++) { %>
                        <td><img id="p_contentImg<%= i %>" class="contentImg" src="https://moyeoyou.kr/assets/common/img/user/default_photo.jpg" alt=""></td>
                    <% } %>
                    
                
                </tr>
                <tr>
                    <th>제목 </th>
                    <td colspan="4"><input class="input_style" type="text" name="productName" value="<%= p.getProductName() %>" required></td>
                </tr>
                <tr>
                    <th>카테고리 </th>
                    <td colspan="4">
                        <select name="category" id="p_category1" class="p_category" onchange="selectCategory();">
                            <% for(ProductCategory pc : list){ %>
                                <% if(pc.getCateParent() == 0) {%>
                                	<option value="<%=pc.getCateNo()%>"><%=pc.getCateName() %></option>
                           		<% } %>
                            <% } %>
                        </select>
                        <select name="category2" id="p_category2" class="p_category">
                            <% for(ProductCategory pc : list){ %>
                                	<option value="<%=pc.getCateNo()%>"><%=pc.getCateName() %></option>              
                            <% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>거래지역</th>
                        <td colspan="4">
                            <input class="input_style" type="text" name="location" id="p_location" value="<%= p.getLocation() %>" placeholder="주소">
                            <input type="button" onclick="execDaumPostcode()" value="주소 찾기" class="p_btn-style"><br>
                        </td>
                        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
                        <script>
                           
                        </script>
                </tr>
                <tr>
                    <th>상품 상태</th>
                    <td colspan="4">
                        <input type="radio" name="productStatus" value="중고 상품">중고 상품
                        <input type="radio" name="productStatus" value="새 상품">새 상품
                        <script>
                            var productStatus = '<%= p.getProductStatus() %>';

                            $('input:radio[name=productStatus]').each(function(){
                                if(productStatus.search($(this).val()) != -1){ 
                                    $(this).attr('checked', true);
                                };
                        });
                                        
                        </script>
                    </td>
                </tr>
                <tr>
                    <th>가격</th>
                    <td colspan="4">
                        <input type="number" class="inputNumber_style" name="productPrice" value="<%= p.getProductPrice() %>">원
                        <input type="hidden" name="p_method" value="P">
                    </td>
                </tr>
                <tr>
                    <th>설명</th>
                    <td colspan="5"> <textarea id="p_textArea" name="productDesc" rows="10" cols="50" style="resize: none;" required ><%= p.getProductDesc() %></textarea></td>
                </tr>
                <tr>
                    <th>배송방법</th>
                    <td colspan="4">
                        <input type="radio" name="productDelv" value="택배">택배
                        <input type="radio" name="productDelv" value="직거래">직거래
                        <script>
                            var productDelv = '<%= p.getProductDelv() %>';

                            $('input:radio[name=productDelv]').each(function(){
                                if(productDelv.search($(this).val()) != -1){ 
                                    $(this).attr('checked', true);
                                };
                        });
                                        
                        </script>

                    </td>
                </tr>
            </table>

            <div align="center">
                <button type="submit" id="p_btn-update" onclick="return updateProduct();">수정하기</button>
                <button type="button" id="p_btn-cancel" onclick="history.back();">취소하기</button>
            </div>
            
            <script>
                //지역
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
                    
                function updateProduct(){
                    var deleteProduct = confirm("수정하시겠습니까?");

                    if(deleteProduct == true) {
                        alert("수정되었습니다.");
                        return true;
                    } else {
                        return false;
                    }
                }
                

                // 카테고리
                 $(function(){
                                $('#p_category2 option').each(function(){
                                    if($(this).text() == '<%= p.getProductCate() %>'){
                                        $(this).attr('selected', 'true');
                                    };
                                });
                                $('#p_category1 option').each(function(){
                                    if($(this).val() == '<%= p.getCateParent() %>'){
                                        $(this).attr('selected', 'true');
                                    };
                                });
                            })

                            function selectCategory(){
                            var selectCateNo = $("#p_category1 :selected").val();

                            $.ajax({
                                url : 'selectCategory.po',
                                data : { 
                                    cateNo :  selectCateNo
                                },
                                success : function(list){
                                    var result = '';
                                    for(let i in list){
                                        result += '<option value="' + list[i].cateNo + '">' + list[i].cateName + '</option>';
                                    };
                                    $('#p_category2').html(result);
                                },
                                error : function(){
                                    console.log('실패');
                                }

                            });

                        }

                 $(function(){
                    $('#p_file-area').hide();

                    $('#p_titleImg').click(function(){
                        $('#p_reUpfile0').click();
                    });

                    $('#p_contentImg1').click(function(){
                        $('#p_reUpfile1').click();
                    });

                    $('#p_contentImg2').click(function(){
                        $('#p_reUpfile2').click();
                    });

                    $('#p_contentImg3').click(function(){
                        $('#p_reUpfile3').click();
                    });

                    $('#p_contentImg4').click(function(){
                        $('#p_reUpfile4').click();
                    });
                })

                function loadImg(inputFile, num){
                    if(inputFile.files.length == 1){
                        let reader = new FileReader();

                        reader.readAsDataURL(inputFile.files[0]);

                        reader.onload = function(e){
                            switch(num){
                                case 1: $('#p_titleImg').attr('src', e.target.result); break;
                                case 2: $('#p_contentImg1').attr('src', e.target.result); break;
                                case 3: $('#p_contentImg2').attr('src', e.target.result); break;
                                case 4: $('#p_contentImg3').attr('src', e.target.result); break;
                                case 5: $('#p_contentImg4').attr('src', e.target.result); break;
                            }
                        }
                    } else{
                        let str="https://moyeoyou.kr/assets/common/img/user/default_photo.jpg";
                        switch(num){
                                case 1: $('#p_titleImg').attr('src', str); break;
                                case 2: $('#p_contentImg1').attr('src', str); break;
                                case 3: $('#p_contentImg2').attr('src', str); break;
                                case 4: $('#p_contentImg3').attr('src', str); break;
                                case 5: $('#p_contentImg4').attr('src', str); break;
                            }
                    }
                }


            </script>
	

</body>
</html>