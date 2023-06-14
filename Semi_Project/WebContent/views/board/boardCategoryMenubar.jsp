<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	.hover1{
		background-color:rgb(185, 228, 245);
        color: rgb(0, 0, 0);
        border: 5px solid rgb(255, 255, 255);
        border-radius: 15px;
        font-weight: bold;
	}
    .hover1:hover{
        cursor: pointer;
        background-color: rgb(154, 226, 255);
    }
    #b_table_category{
        margin-top: 30px;
        margin-left: 10px;
        width: 150px;
    }
</style>
</head>
<body>
   <table id="b_table_category" style="height: auto; ">
       <tbody>
            <tr>
                <td id="b_category_main" class="hover1" style="height: 50px; text-align: center;">게시판 메인화면</td>
            </tr>
            <tr>
                <td id="b_category_all" class="hover1" style="height: 50px; text-align: center;">전체게시판</td>
            </tr>
            <tr>
                <td id="b_category_notice" class="hover1" style="height: 50px; text-align: center;">공지사항</td>
            </tr>
            <tr>
                <td id="b_category_best" class="hover1" style="height: 50px; text-align: center;">인기글게시판</td>
            </tr>
            <tr>
                <td id="b_category_free" class="hover1" style="height: 50px; text-align: center;">자유게시판</td>
            </tr>
            <tr>
                <td id="b_category_product" class="hover1" style="height: 50px; text-align: center;">상품게시판</td>
           </tr>
       </tbody>
   </table>
</body>
</html>

