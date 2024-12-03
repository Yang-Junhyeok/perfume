$(function(){
    var swiper = new Swiper(".top_banner .mySwiper", {
        direction: "vertical",
        loop: true,
        autoplay: {
            delay: 2500,
            disableOnInteraction: false
        },
        navigation: {
            nextEl: ".top_banner .swiper-button-next",
            prevEl: ".top_banner .swiper-button-prev",
          },
      });

      var toggle = 1;
      $(".top_banner_toggle img").mouseenter(function(){
        switch (toggle) {
          case 1:
              $(".top_banner_toggle p:first-child").hide();
              $(".top_banner_toggle p:last-child").show();
              break;
          case 0:
              $(".top_banner_toggle p:first-child").show();
              $(".top_banner_toggle p:last-child").hide();
              break;
        }
      });

      $(".top_banner_toggle img").mouseleave(function(){
        $(".top_banner_toggle p").hide();
      });

      $(".top_banner_toggle a").click(function(e){
        e.preventDefault();
        if(toggle == 1) {
          toggle = 0;
          $(".top_banner").stop().slideDown(500);
        }
        else{
          toggle = 1;
          $(".top_banner").stop().slideUp(500);
        }
      });

      $(window).scroll(function(){
        //화면 위쪽에서는 top-btn이 안보이다가 500px 정도 스크롤되면
        //top-btn 보임
        if(toggle == 1 ){
          if($(this).scrollTop() > 65){
            $(".top_banner_toggle").addClass("remove");
          }else{
            $(".top_banner_toggle").removeClass("remove");
        }
        }else{
          if($(this).scrollTop() > 80){
            $(".top_banner_toggle").addClass("remove");
          }else{
            $(".top_banner_toggle").removeClass("remove");
        }
        }
      });


      $(".header_right_top_menu").mouseenter(function(){
        $(".header_right_bottom").addClass('active')
      });
      $(".header_right").mouseleave(function(){
        $(".header_right_bottom").removeClass('active')
      },);

      var swiper2 = new Swiper("header .header_bottom .mySwiper", {
        spaceBetween: 0.5,
        slidesPerView: 'auto',
        clickable: true,
        navigation: {
          nextEl: '.header_bottom .swiper-button-next',
          prevEl: '.header_bottom .swiper-button-prev',
		},
      });

      $(window).scroll(function(){
        if(toggle == 1){
          if($(this).scrollTop() > 90){
            $("header .header_bottom").addClass("active");
          }else{
              $("header .header_bottom").removeClass("active");
          }
        }
        else{
          if($(this).scrollTop() > 155){
            $("header .header_bottom").addClass("active");
          }else{
              $("header .header_bottom").removeClass("active");
          }
        }

      });
})
