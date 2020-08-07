<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member</title>
</head>

<script src="<c:url value = '../resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">

<script>
$(function(){
	$('button[name="move_btn"]').click(member_frameMove);
	$('#logout').click(member_logout);

	function member_frameMove(){
		var url = $(this)[0].id;
		list_Box.location.href=url;
	}

	function member_logout(){
		location.href="../logout";
	}

	$('#list_Box').load(function(){
		var box_pathname = $(this)[0].contentWindow.location.pathname;
		
		if(box_pathname.indexOf("login") > -1){
			location.href = "../login";
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
}


</style>
<body>
<div id="menuBar" style="position: absolute; left: 0px; z-index: 1000; text-align: center; width:20%;">
<h1>${sessionScope.user_inform.userid}様</h1><br></br>
<p><button name="move_btn" type="button" id="estimateSheetList">見積書リスト</button></p>
<p><button name="move_btn" type="button" id="updateMyProfile">会員情報</button></p>
<p><button name="logout" type="button" id="logout">ログアウト</button></p>	
</div>
<div id="_iframe" style="position:absolute; left:20%;  text-align: left;">
<iframe name="list_Box" id="list_Box" src="estimateSheetList" frameborder=0 framespacing=0 marginheight=0 marginwidth=0 scrolling=no vspace=0></iframe>
</div>
</body>
</html>