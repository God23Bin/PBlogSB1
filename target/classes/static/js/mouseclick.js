/* 鼠标点击文字特效 */
var f_idx = 0;
var c_idx = 0;
$(function(){
	$("body").click(function(e) {
	    var font = new Array("欢迎哦！", 
							"今晚我会带着月亮的温柔还有星星的光亮，顺着银河飞到你梦里。", 
							"宇宙众生哀苦中，你就是那一星半点的可爱", 
							"你那么可爱 走两步路 风都会甜一些", 
							"你是今天这么帅，还是每天都这么帅啊!", 
							"别灰心 普普通通的你 也值得被万般宠溺", 
							"愿所得皆所期，所失亦无碍。", 
							"你来时冬至，但眉上风止，开口是我来的稍迟。",
							"种自己的花，爱自己的宇宙", 
							"今天是128G的胃", 
							"while(alive) { love++; }", 
							"平安喜乐，万事胜意。", 
							"最是人间留不住 朱颜辞镜花辞树", 
							"凡是过往，皆为序章。");
		var color = new Array('#ff0000','#eb4310','#f6941d','#fbb417','#ffff00','#cdd541','#99cc33','#3f9337','#219167','#239676','#24998d','#1f9baa','#0080ff','#3366cc','#333399','#003366','#800080','#a1488e','#c71585','#bd2158');
	    var $i = $("<span />").text(font[f_idx]);
	    f_idx = (f_idx + 1) % font.length;
		c_idx = (c_idx + 1) % color.length;
	    var x = e.pageX,
	        y = e.pageY;
	    $i.css({
	        "z-index": 99999999999999999999999999999999999999999999999999999999999999999999999999 ,
	        "top": y - 20,
	        "left": x,
	        "position": "absolute",
	        "font-weight": "bold",
	        "color": color[c_idx]
	    });
	    $("body").append($i);
	    $i.animate({
	            "top": y - 180,
	            "opacity": 0
	        },
	        3000,
	        function() {
	            $i.remove();
	        });
	});
});
