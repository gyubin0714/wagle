<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
<title>메인페이지</title>


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

   <!-- swiper.js 라이브러리추가 -->
   <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
   <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>

  <style>
 /* 이미지 영역 사이즈 조절 */
 .swiper {
        width: 1200px;
        height: 500px;
    }
​
    /* 이미지 사이즈 조절 */
    .swiper-slide>img {
        width : 100%;
        height : 100%;
    }
​
    /* 화살표 버튼색 변경 (기본색은 파란색) */
    div[class^=swiper-button] {
        color : white;
        /* display : none; */ /* 아니면 안보이게 숨기기도 가능 */
    }

    #head-area{
      float: right;
    }
    #user-info a{
         text-decoration : none;
         color : black;
         font-size : 12px;
     }

    
     .menu{
        display:inline;
        width: 150px;
        height: 50px;
    }
    .menu a{
        text-decoration: none;
        color: black;
        font-size: 18px;
        width: 100%;
        height: 100%;
        line-height: 15px;
    }
    .menu a:hover{
        color: rgb(245, 244, 242);
        background-color: none;
    }
    #mainlogo{
      float: left;
    }
    .nav2-area{
      width: 100%;
      height: 330px;
      background-color: antiquewhite;
    }
    #nav3{
      display:inline;
      margin: 70px 70px 50px 70px;
      border-radius: 25%;
      background-color: rgb(185, 228, 245);
      color:black;
      text-align: center;
      font-family: Impact;
      font-size: 30px;
      text-decoration: none;
    }
    .nav4-area{
      width: 100%;
      height: 500px;
      background-color: antiquewhite;
      
    }
    .nav4-area>#newProduct{
      
      width: 100%;
      height: 100%;
    }
    .nav3-area a:hover {
      background-color: rgb(185, 228, 245);
      border: 2px solid black;
      width: 200px;
      height: 55px;
      font-style: italic;
      font-size: 20pt;
      color: black;
    }
    .nav3-area a{
      background-color: black;
      border: none;
      color:rgb(185, 228, 245);
      width: 200px;
      height: 55px;
      font-style: italic;
      font-size: 20pt;
   

    }
    .footer{
      width: 100%;
      height: 500px;
      background-color: rgb(151, 151, 151);
    }
    
    .newProduct,.bestProduct,.auctionProduct{
      float: left;
    }
    
  </style>


  </head>
  <body>
<%@ include file="../common/menubar.jsp" %>

<div id="content_1">
  <!-- Slider main container -->
  <div class="swiper">
      <!-- Additional required wrapper -->
      <div class="swiper-wrapper">
          <!-- Slides -->
          <div class="swiper-slide"><img src="<%= contextPath %>/resources/mainpage/001.png"></div>
          <div class="swiper-slide"><img src="<%= contextPath %>/resources/mainpage/002.png"></div>
          <div class="swiper-slide"><img src="<%= contextPath %>/resources/mainpage/003.png"></div>
         
      </div>
  
      <!-- If we need pagination -->
      <div class="swiper-pagination"></div>
  
      <!-- If we need navigation buttons -->
      <div class="swiper-button-prev"></div>
      <div class="swiper-button-next"></div>
  
      <!-- If we need scrollbar -->
      <div class="swiper-scrollbar"></div>
  </div>
</div>


    

    <br><br><!-- 누르면 해당 상품 상세 페이지로 이동-->
    <div class="nav3-area" align="center">
      <a href="#"  class="btn btn-info">new</a>
      <a href="#"  class="btn btn-info">best</a>
      <a href="#" class="btn btn-info">auction</a>
      <br><br><br>
      
    </div>
    <br>
    
    <br><br><br>
    <div class="nav4-area" >
      <h4 align="center" >NEW</h4>
        <table class="newProduct" ></table>
        <table class="newProduct" ></table>
        <table class="newProduct" ></table>
        <table class="newProduct" ></table>
    </div> 
    <br><br>
    <br><br><br>
    <div class="nav4-area" >
      <h4 align="center" >BEST</h4>
        <table class="bestProduct"></table>
        <table class="bestProduct"></table>
        <table class="bestProduct"></table>
        <table class="bestProduct"></table>
    </div>    
    <br><br>
    <br><br><br>
    <div class="nav4-area" >
      <h4 align="center" >AUCTION</h4>
        <table class="auctionProduct"></table>
        <table class="auctionProduct"></table>
        <table class="auctionProduct"></table>
        <table class="auctionProduct"></table>
    </div>    
    <br><br>


    
     <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
     <div class="footer" align="center">
      <p>
        <br><br><br><br><br>
        <h6>기업정보</h6>
      </p>

     </div>


    <script>
      // 슬라이더 동작 정의
      const swiper = new Swiper('.swiper', {
          autoplay : {
              delay : 3000 // 3초마다 이미지 변경
          },
          loop : true, //반복 재생 여부
          slidesPerView : 1, // 이전, 이후 사진 미리보기 갯수
          pagination: { // 페이징 버튼 클릭 시 이미지 이동 가능
              el: '.swiper-pagination',
              clickable: true
          },
          navigation: { // 화살표 버튼 클릭 시 이미지 이동 가능
              prevEl: '.swiper-button-prev',
              nextEl: '.swiper-button-next'
          }
      }); 
      $(function(){
        selectNewList();
      })
      // newList 조회
      function selectNewList() {
            $.ajax({

                url : 'mainList',
                data : {},
                success : function(list){
                  let newList = list[0];
                  let bestList = list[1];
                  let auctionList = list[2];

                  let str = '';
                  for(let i in newList){
                    str = '<tr style="height:30%">'
                            +'<th><img src="'+newList[i].titleimg+'" alt=""></th>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<input type="hidden" value="'+newList[i].productNo+'">'
                            +'<input type="hidden" value="'+newList[i].method+'">'
                            +'<td>'+newList[i].productName+'</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+newList[i].productPrice+'</td>'
                          +'</tr>'
                          +'<tr style="height:10%">';
                        if(newList[i].method == 'P'){
                            str+='<td>중고거래</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+newList[i].createDt+'</td>';
                        }else{
                          str+='<td>경매</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+newList[i].endTime+'</td>';
                        }
                          str+='</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+newList[i].viewCnt+'</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+newList[i].count+'</td>'
                          +'</tr>';
                    $('.newProduct').eq(i).html(str);
                  }
                  for(let i in bestList){
                    str = '<tr style="height:30%">'
                            +'<th><img src="'+bestList[i].titleimg+'" alt=""></th>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<input type="hidden" value="'+bestList[i].productNo+'">'
                            +'<input type="hidden" value="'+bestList[i].method+'">'
                            +'<td>'+bestList[i].productName+'</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+bestList[i].productPrice+'</td>'
                          +'</tr>'
                          +'<tr style="height:10%">';
                        if(bestList[i].method == 'P'){
                            str+='<td>중고거래</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+bestList[i].createDt+'</td>';
                        }else{
                          str+='<td>경매</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+bestList[i].endTime+'</td>';
                        }
                          str+='</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+bestList[i].viewCnt+'</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+bestList[i].count+'</td>'
                          +'</tr>';
                    $('.bestProduct').eq(i).html(str);
                  }
                  for(let i in auctionList){
                    str = '<tr style="height:30%">'
                            +'<th><img src="'+auctionList[i].titleimg+'" alt=""></th>'
                            +'<input type="hidden" value="'+auctionList[i].productNo+'">'
                            +'<input type="hidden" value="'+auctionList[i].method+'">'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+auctionList[i].productName+'</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+auctionList[i].productPrice+'</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>경매</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+auctionList[i].endTime+'</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+auctionList[i].viewCnt+'</td>'
                          +'</tr>'
                          +'<tr style="height:10%">'
                            +'<td>'+auctionList[i].count+'</td>'
                          +'</tr>';
                    $('.auctionProduct').eq(i).html(str);
                  }
                },
                error : function(){
                    console.log('댓글 읽어오기 실팽');
                }

            });
        };
        
        $('.nav4-area>table').click(function(){
          let productNo = $(this).find('input').eq(0).val();
          let method = $(this).find('input').eq(1).val();

          if(method == 'P'){
            location.href = '<%= contextPath %>/detail.po?pno='+productNo;
          }else{
            location.href = '<%= contextPath %>/detail.au?pno='+productNo;
          }

          
        });


  </script>
​
       
         
         
  </body>
</html>