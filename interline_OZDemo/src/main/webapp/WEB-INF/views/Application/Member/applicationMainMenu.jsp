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
	isMobile();
	
	function member_frameMove(){
		var url = $(this).attr('id');
		console.log(url);
		list_Box.location.href=url;
	}

	function member_logout(){
		location.href="../logout";
	}

	 
	
	function isMobile() {
		var filter = "win16|win32|win64|mac|macintel";
		if( navigator.platform  ){
			if( filter.indexOf(navigator.platform.toLowerCase())<0 ){
				$("body").attr('class','mobile_body');
				$("#list_Box").attr('class','list_Box_Mobile');
				$(".mainMenuButton").addClass("mainMenuButton_Mobile");
		    	$(".mainMenuTd").addClass("mainMenuTd_Mobile");
				$("#printId").html('');
				$("#printId").attr('class','printId_Mobile');
				$("#writeApplication").html('申込書<br>作成');
				$("#applicationList").html('申込書<br>リスト');
				$("#logout").html('ログ<br>アウト');
		    }else{
		    	$("body").addClass("pc_body");
		    	$("#title").addClass("pc_font_title");
		    	$(".btn").addClass("pc_font_button2");
		    	$(".mainMenuButton").addClass("mainMenuButton_PC");
		    	$(".mainMenuTd").addClass("mainMenuTd_PC");
				$("#list_Box").attr('class','list_Box_PC');
		    }
		}
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
	width: 100%; 
	height: 100%;
	text-align: center;
}
.list_Box_PC{
	width: 50%;
	height: 100%;
	z-index: 1;
}
.list_Box_Mobile{
	width: 100%;
	height: 100%;
	z-index: 1;
}
.mainMenuButton_PC{
	display:inline-block;
	width:100px;
}
.mainMenuTd_PC{
	width: 150px;
}
.mainMenuButton_Mobile{
	display:inline-block;
	width:150px;
}
.mainMenuTd_Mobile{
	width: 180px;
}
.printId{
		width: 0px;
}

</style>
<body>
<div id="menuBar" style="position:relative; left: 0px; z-index: 1000; text-align: center; width:100%;">
<table style="text-align: center; margin: auto;">
	<tr>
		<td id="printId" style="font-weight: bold;">${sessionScope.userInform.userId}様</td>
		<td class="mainMenuTd">
			<span id="writeApplication" class="pc_font_button1 mainMenuButton" onclick="location.href = 'writeApplication'">申込書作成</span>
		</td>
		<td class="mainMenuTd">
			<span id="applicationList" class="pc_font_button1 mainMenuButton move_btn">申込書リスト</span>
		</td>
		<td class="mainMenuTd">
			<span id="logout" class="pc_font_button1 mainMenuButton logout">ログアウト</span>
		</td>
	</tr>
</table>
</div>
<div id="_iframe">
	<iframe name="list_Box" id="list_Box" src="applicationList" frameborder=0 framespacing=0 marginheight=0 marginwidth=0 scrolling=no vspace=0></iframe>
</div>
</body>
</html>