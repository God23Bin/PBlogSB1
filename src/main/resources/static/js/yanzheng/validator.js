$(function() {
	'use strict';
	
	// 这种写法，暴露出去的意思，可以理解成封装一个类，window.类名 = function(){};
	// 之后在main.js就可以调用这里封装的类，来实例化一个对象
	// 如下实例化对象
	// var validator = new Validator();
	window.Validator = function(val, rule) {

		// 这种this.方法名 = function(){}写的方法，是公有的
		this.is_valid = function(new_val) {
			var key;
			if (new_val !== undefined)
				val = new_val;

			/*如果不是必填项且用户未填写任何内容则直接判定为合法*/
			if (!rule.required && !val)
				return true;

			for (key in rule) {
				/*防止重复检查*/
				if (key === 'required')
					continue;

				/*调用rule中相对应的方法*/
				var r = this['validate_' + key]();
				if (!r) return false;
			}

			return true;
		}
		
		/**
		 * 验证最大值
		 */
		this.validate_max = function() {
			pre_max_min();
			return val <= rule.max;
		}
		
		/**
		 * 验证最小值
		 */
		this.validate_min = function() {
			pre_max_min();
			return val >= rule.min;
		}
		
		/**
		 * 验证最大长度
		 */
		this.validate_maxlength = function() {
			pre_length();
			return val.length <= rule.maxlength;
		}

		/**
		 * 验证最小长度
		 */
		this.validate_minlength = function() {
			pre_length();
			return val.length >= rule.minlength;
		}
		
		/**
		 * 验证数字
		 */
		this.validate_numeric = function() {
			return $.isNumeric(val);
		}

		/**
		 * 验证必填
		 */
		this.validate_required = function() {
			var real = $.trim(val);
			if (!real && real !== 0) {
				return false;
			}
			return true;
		}

		/**
		 * 验证正则表达式
		 */
		this.validate_pattern = function() {
			var reg = new RegExp(rule.pattern);
			return reg.test(val);
		}


		// 这种直接写function 方法名(){}的方法是私有的
		/* 用于完成this.validate_max 或
		  this.validate_min的前置工作
		* */
		function pre_max_min() {
			val = parseFloat(val);
		}

		/* 用于完成this.validate_maxlength或
		  this.validate_minlength的前置工作
		* */
		function pre_length() {
			val = val.toString();
		}
	}
})
