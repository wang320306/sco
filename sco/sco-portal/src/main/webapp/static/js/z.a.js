(function() {
	"use strict";
	$("html").niceScroll({styler:"fb",cursorcolor:"#65cea7", cursorwidth: '6', cursorborderradius: '0px', background: '#424f63', spacebarenabled:false, cursorborder: '0',  zindex: '1000'});
	$(".left-side").niceScroll({
		styler : "fb",
		cursorcolor : "#65cea7",
		cursorwidth : '3',
		cursorborderradius : '0px',
		background : '#424f63',
		spacebarenabled : false,
		cursorborder : '0'
	});

	$(".left-side").getNiceScroll();
	if ($('body').hasClass('left-side-collapsed')) {
	    $(".left-side").getNiceScroll().hide();
	}

	jQuery('.menu-list > a').click(function() {
		var parent = jQuery(this).parent();
		var sub = parent.find('> ul');
		if (!jQuery('body').hasClass('left-side-collapsed')) {// 菜单非压缩条件下处理
			if (sub.is(':visible')) {
				sub.slideUp(200, function() {
					parent.removeClass('nav-active');//移除选中样式
					jQuery('.main-content').css({height : ''});//1.先去除主要内容的高度
					mainContentHeightAdjust();//2.调整主要内容的高度和doc保持一致
				});
			} else {
				visibleSubMenuClose();// 关闭所有菜单并移除active样式
				parent.addClass('nav-active');
				sub.slideDown(200, function() {
					mainContentHeightAdjust();
				});
			}
		}
		return false;
	});

	function visibleSubMenuClose() {
		jQuery('.menu-list').each(function() {
			var t = jQuery(this);
			if (t.hasClass('nav-active')) {
				t.find('> ul').slideUp(200, function() {
					t.removeClass('nav-active');
				});
			}
		});
	}

	function mainContentHeightAdjust() {
		// Adjust main content height
		var docHeight = jQuery(document).height();
		if (docHeight > jQuery('.main-content').height()){
			jQuery('.main-content').height(docHeight);
		}
	}

	//  class add mouse hover
	jQuery('.custom-nav > li').hover(function() {
		jQuery(this).addClass('nav-hover');
	}, function() {
		jQuery(this).removeClass('nav-hover');
	});

	// Menu Toggle
	jQuery('.toggle-btn').click(function() {
		
		$(".left-side").getNiceScroll().hide();

		if ($('body').hasClass('left-side-collapsed')) {
			$(".left-side").getNiceScroll().hide();
		}
		
		var body = jQuery('body');
		var bodyposition = body.css('position');
		
		/*
		 * 当页面小于800进入自适应时，左侧菜单隐藏，这是body会加入position：relative属性
		 * 可根据body的这一属性判断是否进入自适应，从而判断添加哪种效果
		 */

		if (bodyposition != 'relative') { // 表明还没有进入自适应的尺度，应当添加collapsed效果
			if (!body.hasClass('left-side-collapsed')) {
				body.addClass('left-side-collapsed');
				jQuery('.custom-nav ul').attr('style', '');
				jQuery(this).addClass('menu-collapsed');// 这个看不懂为啥要加
			} else {
				body.removeClass('left-side-collapsed chat-view');
				jQuery('.custom-nav li.active ul').css({
					display : 'block'
				});
				jQuery(this).removeClass('menu-collapsed');
			}
		} else { // 表明已经进入自适应的尺度，应当添加left-side-show效果

			if (body.hasClass('left-side-show'))
				body.removeClass('left-side-show');
			else
				body.addClass('left-side-show');

			mainContentHeightAdjust();
		}

	});

	/*searchform_reposition();*/

	jQuery(window).resize(function() {
		if (jQuery('body').css('position') == 'relative') {
			jQuery('body').removeClass('left-side-collapsed');
		} else {
			jQuery('body').css({
				left : '',
				marginRight : ''
			});
		}
		//searchform_reposition();
	});

	/*function searchform_reposition() {
		if (jQuery('.searchform').css('position') == 'relative') {
			jQuery('.searchform').insertBefore('.left-side-inner .logged-user');
		} else {
			jQuery('.searchform').insertBefore('.menu-right');
		}
	}*/

	// panel collapsible
	/*$('.panel .tools .fa').click(function() {
		var el = $(this).parents(".panel").children(".panel-body");
		if ($(this).hasClass("fa-chevron-down")) {
			$(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
			el.slideUp(200);
		} else {
			$(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
			el.slideDown(200);
		}
	});*/

	/*$('.todo-check label').click(
			function() {
				$(this).parents('li').children('.todo-title').toggleClass(
						'line-through');
			});*/

	/*$(document).on('click', '.todo-remove', function() {
		$(this).closest("li").remove();
		return false;
	});*/

	//$("#sortable-todo").sortable();

	// panel close
	/*$('.panel .tools .fa-times').click(function() {
		$(this).parents(".panel").parent().remove();
	});*/

	// tool tips

	//$('.tooltips').tooltip();

	// popovers

	//$('.popovers').popover();

})(jQuery);