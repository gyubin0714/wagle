<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의 상세보기</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
        
        /* 전체를 감싸는 div태그 */
        .wrap{
            width: 1300px;
            height: 1300px;
            margin:auto;
        }

        /* wrap의 바로 뒤 자식요소 div 가로 길이 100%로 */
        .wrap>div {
            width: 100%; 
        }    
        
        #content{
            height: 100%;
        }

        #content>div{
            height: 100%;
            float: left;
        }
        

        #content2{ width:100%;}

        .content2_1 { padding-top: 50px;}

        .content2_1>h2 {text-align: center;}

        .content2_2 { padding-left: 130px;}



        #admin_category{
            padding-top: 50px;
        }

        

        #admin_category>a {
            color: rgb(92, 90, 90);
            text-decoration: none;
        }

        #cs_category_area { padding-top: 10px; }
        
        .cs_category a {
            color: black;
            margin: 0 auto;
            text-decoration: none;

        }

        .cs_category {
            padding-top: 10px;
            display: inline-block;
            background-color: rgb(245, 244, 242);
            height: 50px;
            width: 200px;
            text-align: center;
            font-size: 19px;
        }

        .cs_category:hover {
            background-color: rgb(185, 228, 245);
        }

        #admin_category_detail{
            display: inline-block;
        }

        .cs_category_area_admin{
            padding-top: 15px;
        }

        .paging-area button{ border: 0; background-color: rgb(185, 228, 245); color: black;}

        /* 작성, 등록, 뒤로가기 버튼 */
        .n_write_btn {
            font-size: 17px;
            border-radius: 5px;
            background-color: rgb(185, 228, 245);
            padding: 10px;
            color: black;
        }

        .n_enroll_btn {
            font-size: 17px;
            border-radius: 5px;
            background-color: rgb(185, 228, 245);
            padding: 7px;
            color: black;
            border: 0;
        }

        .n_back_btn {
            font-size: 17px;
            border-radius: 5px;
            background-color: rgb(215, 218, 219);
            padding: 7px;
            color: black;
            border: 0;
        }
        
        /* 돋보기 */
        .n_search {
            padding: 0;
        }

        .n_search img {
            width: 30px;
            height: 20px;
        }

        /* 카테고리 버튼 */
        #n_category_area input {
        border-radius: 5px;
        border: 0;
        font-size: 16px;
        background-color: (245, 244, 242);
        height: 45px;

        

    }

</style>
</head>
<body>

    <div>

    </div>
    <div id="cs_category_area">
        <div class="cs_category">
        <% if(loginUser != null) {%>
            <!-- 로그인 한 사용자 -> 1:1문의 내역 조회 -->
            <a href="<%=contextPath%>/inquiryList.no?cpage=1&memNo=<%= loginUser.getMemNo() %>"> 1:1문의 내역</a>
        
        <% } else { %>
            <!-- 로그인 안 한 사용자 -> 로그인창으로 이동-->
            <a href="<%= contextPath %>/loginForm.me"> 1:1문의 내역</a>

        <% } %>
        </div>

        <div class="cs_category"><a href="<%=contextPath%>/noticeList.no?cpage=1" >공지사항</a></div>
        <div class="cs_category"><a href="<%=contextPath%>/faqListView.no" >FAQ</a></div>

        <div class="cs_category">
        <% if(loginUser != null) { %>
            <!-- 로그인 O -->
            <% if(loginUser.getMemId().equals("admin")) {  %>
                <!-- 관리자 로그인 -> 고객의 소리 조회하기 -->
                <a href="<%=contextPath%>/vocListView.no?" >고객의소리</a>
            <% } else { %>
                <!-- 사용자 로그인 -> 고객의 소리 작성하기 -->
                <a href="<%=contextPath%>/vocEnrollForm.no" >고객의소리</a>
            <% } %>
            
        <% } else { %>
            <!-- 로그인 X -> 로그인창으로 이동-->
            <a href="<%= contextPath %>/loginForm.me">고객의소리</a>

        <% } %>
        </div>
    </div>

    <div class="cs_category_area_admin">
        <% if(loginUser != null && loginUser.getMemId().equals("admin")) { %>
            <div class="cs_category"><a href="<%= contextPath %>/memList.ad">회원 조회</a></div>
            <div class="cs_category"><a href="<%= contextPath %>/productList.ad">상품/경매 조회</a></div>
            <div class="cs_category"><a href="<%= contextPath %>/BoardList.ad">게시판 조회</a></div>

        <% } %>
      </div>

    
           
    
    
    
    

    
</body>
</html>