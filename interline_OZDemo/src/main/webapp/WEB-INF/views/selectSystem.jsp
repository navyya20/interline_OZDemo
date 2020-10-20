<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select System</title>
<script src='../resources/js/jquery-2.0.3.min.js'></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">

<script>
$(function(){
	 isMobile(); 
	$('.move_btn').click(admin_frameMove);

	function isMobile() {
		var filter = "win16|win32|win64|mac|macintel";
		if( navigator.platform  ){
			if( filter.indexOf(navigator.platform.toLowerCase())<0 ){
				}else{
					$("body").addClass('pc_body');
					$("#title").addClass("pc_font_title");
					$(".btn").addClass("pc_font_button2");
				}
			}
		}
	
	function admin_frameMove(){
		var url = $(this)[0].id;

		location.href=url;
	}
});

</script>

<style>
body{text-align: center;}
#selectMenu{
	margin:100px auto;
}
.mainMenuTr{
	height: 80px;
}
</style>
</head>
<body>
<h1>OZ-DEMO システム</h1>
<div id="selectMenu">
<table style="text-align: center; margin: auto;">
	<tr class="mainMenuTr">
		<td>
			<span class="pc_font_button1 move_btn" id="memberMain">見積書システム</span>
		</td>
	</tr>
	<tr class="mainMenuTr">
		<td>
			<span class="pc_font_button1 move_btn" id="agreementMainMenu">同意書システム</span>
		</td>
	</tr>
</table>
</div>
</body>
</html>