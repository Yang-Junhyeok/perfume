$(function(){

      var swiper3 = new Swiper(".s1 .mySwiper", {
        slidesPerView: 1,
        loop: true,
        autoplay:{
          delay:5000
        },
        pagination: {
          el: ".s1 .swiper-pagination",
          clickable: true,
        },
      });
      let swiper_sw = 0;
      $(".play-pause").click(function(){
        if(swiper_sw == 0 ){
          swiper_sw = 1;
          swiper3.autoplay.stop();
          $(".play-btn").show();
          $(".pause-btn").hide();
        }else{
          swiper_sw = 0;
          swiper3.autoplay.start();
          $(".play-btn").hide();
          $(".pause-btn").show();
        }
      })



      var swiper4 = new Swiper(".s4 .mySwiper", {
        slidesPerView: 3,
        spaceBetween: 30,
        centeredSlides: true,
        autoplay:{
          delay:4000,
          disableOnInteraction: false,
        },
        loop:true,
        pagination: {
          el: ".s4 .swiper-pagination",
          clickable: true,
        },
        slidesOffsetBefore: -20,
        slidesOffsetAfter : 20,
      });

      $('.s5 .tab-button').hover(function(event) {
        // 모든 탭 내용과 버튼의 'active' 클래스 제거
        $('.s5 .tab-content').removeClass('active');
        $('.s5 .tab-button').removeClass('active');

        // 클릭한 버튼에 해당하는 탭 내용 표시
        const tabName = $(this).attr('onclick').match(/'([^']+)'/)[1];
        $('#' + tabName).addClass('active');

        // 클릭한 버튼에 'active' 클래스 추가
        $(this).addClass('active');
    });

        // 첫 번째 탭을 기본으로 활성화
        $('.s5 .tab-button').first().click();


        var swiper5 = new Swiper(".s7 .mySwiper", {
          autoplay:{
            delay:4000,
            disableOnInteraction: false,
          },
          loop:true,
          pagination: {
            el: ".s7 .swiper-pagination",
            clickable: true,
          },
        });













});