$(function(){
	$(".cover").css({
		"top" : $(".pro_category").outerHeight(true) + $(".header").outerHeight(true) - 1
	}).height(getWrapperMinHeight() - $(".pro_category").outerHeight(true) + 20);
	$("#scrollWrap").height( getWrapperMinHeight() - $('.pro_category').outerHeight(true) );
	$("#pageNo").val(2);
	$(".filter-ul li").click(function(){
		var _idx = $(this).index();
		if( $(".category-detail").eq(_idx).is(":visible") ){
			$(".category-detail").hide();
			$(".cover").hide();
			myscroll.enable();
		} else {
			$(".category-detail").hide();
			$(".category-detail").eq(_idx).show();			
			$(".cover").show();
			myscroll.disable();
		}
	});
	$(".cover").click(function(){
		$(".category-detail").hide();
		$(this).hide();
	});
	$(".header").click(function(){
		$(".category-detail").hide();
		$(".cover").hide();
	});
	
});