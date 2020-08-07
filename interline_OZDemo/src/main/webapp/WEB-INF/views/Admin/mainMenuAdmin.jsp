<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<script src="<c:url value = '../resources/js/jquery-2.0.3.min.js'/>"></script>
<script>
$(function(){
	$('button[name="move_btn"]').click(admin_frameMove);
	$('#logout').click(admin_logout);

	function admin_frameMove(){
		var url = $(this)[0].id;
		list_Box.location.href=url;
	}

	function admin_logout(){
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
</head>
<body>
<div id="menuBar" style="position: absolute; left: 0px; z-index: 1000; text-align: center; width:20%;">
<h1>管理者</h1><br></br>
<p><button type="button" name="move_btn" id="registerMember">会員登録</button></p>
<p><button type="button" name="move_btn" id="memberList">会員リスト</button></p>
<p><button type="button" name="logout" id="logout">ログアウト</button></p>	
</div>
<div id="_iframe" style="position:absolute; left:20%;  text-align: left;">
<iframe name="list_Box" id="list_Box" src="memberList" frameborder=0 framespacing=0 marginheight=0 marginwidth=0 scrolling=no vspace=0></iframe>
</div>
</body>
</html>