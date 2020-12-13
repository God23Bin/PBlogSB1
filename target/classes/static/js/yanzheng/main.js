$(function() {
	'use strict';

	/*选中页面中所有的input[data-rule]*/
	var $inputs = $('[data-rule]'),
		$form = $('#register'),
		inputs = [];



	$inputs.each(function(index, node) {
		/*解析每一个input的验证规则*/
		var tmp = new Input(node);
		inputs.push(tmp);
	})
	
	//确认密码的验证
	$('#confirmpasswd').blur(function(){
		var password = $('#pw');
		var passwordStr = password.val();
		var confirm = $('#confirmpasswd');
		var confirmStr = confirm.val();
		if(passwordStr==confirmStr){
			$('#confirmpassword-input-error').hide();
		}else{
			$('#confirmpassword-input-error').show();
		}
	});

});
