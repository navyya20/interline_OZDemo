<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Member</title>
</head>

<script src="<c:url value = '../resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">

<script>
$(function(){
	$('.move_btn').click(member_frameMove);
	$('#logout').click(member_logout);

	function member_frameMove(){
		var url = $(this).attr('id');
		console.log(url);
		list_Box.location.href=url;
	}

	function member_logout(){
		location.href="../logout";
	}

	$('#list_Box').load(function(){
		var title = $(this).contents()[0].title;
		
		if( title == "Login"){
			location.href = "../";
		}
		
		if($(this).contents().find('#OZViewer').html()==null){
         	$(this).css('height',($(this).contents().find('body')[0].scrollHeight)+50+'px');
		}else{
			$(this).css('height',window.innerHeight-40+'px');
		}
		
	});
});
</script>

<style>

#_iframe{
	margin:20px 0px 0px 0px;
	width: 80%; 
	height: 100%;
}
#list_Box{
	width: 80%;
	height: 100%;
	z-index: 1;
}
.mainMenuButton{
	width:100%;	
}
.mainMenuTd{
	width: 80%;
}

</style>
<body>
<div id="menuBar" style="position: absolute; left: 0px; z-index: 1000; text-align: center; width:20%;">
<h1>${sessionScope.userInform.userId}様</h1><br></br>
<table style="text-align: center; margin-left: auto;margin-right: auto;">
<tr>
	<td class="mainMenuTd">
		<div id="updateMyProfile" class="pc_font_button1 mainMenuButton move_btn">自社情報修正</div>
	</td>
</tr>
<tr>
	<td class="mainMenuTd">
		<div id="writeNewEstimateSheet" class="pc_font_button1 mainMenuButton" onclick="location.href = 'writeEstimate;'">見積書作成</div>
	</td>
</tr>
<tr>
	<td class="mainMenuTd">
		<div id="estimateSheetList" class="pc_font_button1 mainMenuButton move_btn">見積書リスト</div>
	</td>
</tr>
<tr>
	<td class="mainMenuTd">
		<div id="logout" class="pc_font_button1 mainMenuButton logout">ログアウト</div>
	</td class="mainMenuTd">
</tr>
</table>
</div>
<div id="_iframe" style="position:absolute; left:20%; ">
	<iframe name="list_Box" id="list_Box" src="estimateSheetList" frameborder=0 framespacing=0 marginheight=0 marginwidth=0 scrolling=no vspace=0></iframe>
</div>
</body>
</html>