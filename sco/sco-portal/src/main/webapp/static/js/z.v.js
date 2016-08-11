/**
 * 校验
 */
$(document).ready(function() {

	// 登录
	$("#loginForm").validate({
		rules : {
			userName : {
				required : true,
				maxlength : 20,
				simpleNumABC : true
			},
			password : {
				required : true,
				maxlength : 16
			}
		},
		messages : {
			userName : {
				required : "请输入用户名"
			},
			password : {
				required : "请输入密码"
			}
		},
		errorClass : "error-validate",
		errorElement : "span",
		errorPlacement: function(error, element) {  
			error.appendTo(element.parent().parent())
		}
		/*highlight : function(element, errorClass, validClass) {
			$(element).parents('.form-group').addClass('error');
		}*/
	});

});

//-------------------------------------------------------------------------------------

//校验手机号码
jQuery.validator.addMethod("phoneNumberCheck", function(value, element) {
	var check = /^((13[0-9]{9})|(15[0-9]{9})|((145|147|176|177|178|170)[0-9]{8})|(18[0-9]{9}))$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写您的手机号码或座机");

// 校验时间 格式  HHmmss
jQuery.validator.addMethod("timeHHmmssCheck", function(value, element) {
	var check = /^([0-1][0-9])|(2[0-3])([0-5][0-9]){2}$/;
	return this.optional(element) || (check.test(value));
}, "请输入正确时间");

// 校验金额，精确到小数点2位,不校验长度
jQuery.validator.addMethod("moneyFloatCheck", function(value, element) {
	var check = /^(([1-9]\d*)|([0-9]))(\.[0-9]{1,2})?$/;
	return this.optional(element) || (check.test(value));
}, "请输入正确金额，精确到小数点后两位");

// 校验MAC地址：
jQuery.validator.addMethod("macAddressCheck", function(value, element) {
	var check = /^([a-fA-F0-9]{2})(-[a-fA-F0-9]{2}){5}$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写MAC地址，例:60-D8-19-D0-43-B1");

// 校验IP
jQuery.validator.addMethod("netWorkIpCheck", function(value, element) {
	var check = /^(?:(?:[1-9]?[0-9]|1[0-9]{2}|2(?:[0-4][0-9]|5[0-5]))\.){3}(?:[1-9]?[0-9]|1[0-9]{2}|2(?:[0-4][0-9]|5[0-5]))$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写局域网IP地址，例:192.168.1.1");

// 校验邮箱
jQuery.validator.addMethod("emailAddressCheck", function(value, element) {
	var check = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写email地址 例:scorpio@sina.com");

// 校验格式:字母数字
jQuery.validator.addMethod("simpleNumABC", function(value, element) {
	var check = /^[A-Za-z0-9]*$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写格式:字母数字");

// 校验格式:字母数字下划线
jQuery.validator.addMethod("simpleCheck", function(value, element) {
	var check = /^[A-Za-z0-9\_]*$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写格式:字母数字下划线");

// 校验格式:字母数字下划线-
jQuery.validator.addMethod("simpleTCheck", function(value, element) {
	var check = /^[A-Za-z0-9\_-]*$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写格式:字母数字下划线-");

// 校验格式:字母数字下划线中文
jQuery.validator.addMethod("simpleChsCheck", function(value, element) {
	var check = /^[A-Za-z0-9\_\u4e00-\u9fa5]*$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写格式:字母数字下划线中文");

//校验格式:中文
jQuery.validator.addMethod("chsCheck", function(value, element) {
	var check = /^[\u4e00-\u9fa5]*$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写格式:中文");

// 校验格式:字母数字下划线和点
jQuery.validator.addMethod("simplePoint", function(value, element) {
	var check = /^[A-Za-z0-9\_\.]*$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写格式:字母数字下划线和点");

// 校验格式:数字
jQuery.validator.addMethod("intNumCheck", function(value, element) {
	var check = /^[0-9]*$/;
	return this.optional(element) || (check.test(value));
}, "请正确填写格式: 数字");
