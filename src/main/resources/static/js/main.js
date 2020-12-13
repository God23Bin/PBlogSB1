$(function() {
	
	/**
	 * 图库的点击侧边栏关联上动画按钮
	 */
	$('.control__radio').click(function(){
		
		$('button.control__btn').click();
		
	});
	
	/**
	 *  排行手风琴，显示与隐藏
	 */
	$('.panel.panel-default').mouseenter(function(){
		$(this).children('.panel-collapse.collapse').stop().show(500)
			   .parent().siblings('div').children('.panel-collapse.collapse').stop().hide(500);
	});
	
	/**
	 * 水平手风琴，animate
	 */
	var _index = 0;
	$('.box2 ul li').mouseenter(function(){
		$(this).stop().animate({
			width: 580
		},500).siblings('li').stop().animate({
			width: 96
		},500);
	});
	
	/**
	 * 返回顶部，显示与隐藏
	 */
	$(window).scroll(function () {
	    if ($(window).scrollTop() >= 50) {
	        $('#btn_top').fadeIn();
			$('#btn_pay').fadeIn();
	    }
	    else {
	        $('#btn_top').fadeOut();
			$('#btn_pay').fadeOut();
	    }
	});
	
	$('#btn_top').click(function () {
	    $('html,body').animate({ scrollTop: 0 }, 500);
	});
	
	$('#btn_pay').mouseenter(function(){
		$('.container.surpport').fadeIn();
	});
	$('#btn_pay').mouseleave(function(){
		$('.container.surpport').fadeOut();
	});
	
	/**
	 * 加载100%效果
	 */
	var counter = 0;
	var c = 0;
	var i = setInterval(function() {
		$(".loading-page .counter h1").html(c + "%");
		$(".loading-page .counter hr").css("width", c + "%");
		counter++;
		c++;
	
		if (counter == 101) {
			clearInterval(i);
			$('.loading-page').fadeOut();
		}
	}, 5);
	
});


