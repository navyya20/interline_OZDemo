<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>estimateSheetList</title>
</head>

<script src="<c:url value = '../resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">
<style>
	body{
		text-align: center;
	}
	#title{
		margin-left: auto;
		margin-right: auto;
	}
	#list_table{
		border-collapse: collapse;
		width: 100%;
		padding: 2px 2px 2px 2px;
		margin-left: auto;
		margin-right: auto;
	}
	#list_table td{
		border-style:solid;
		border-width: 1px;
		vertical-align: bottom;
	}
	.lastColum{
		padding-bottom: 3px;
	}
</style>

<script>
$(function(){

	isMobile(); 
		
	function isMobile() {
		var filter = "win16|win32|win64|mac|macintel";
		if( navigator.platform  ){
			if( filter.indexOf(navigator.platform.toLowerCase())<0 ){
				$("body").attr('class','mobile_body');
		    }else{
		    	$("body").addClass("pc_body");
		    	$("#title").addClass("pc_font_title");
		    	$(".btn").addClass("pc_font_button2");
		    }
		}
	}
	
	$('#list_Box').css('min-height',window.innerHeight-100+'px');
	$('#list_Box').attr("src","estimateSheetList");
	
	$('#list_Box').load(function(){
		
		if($(this).contents().find('#OZViewer').html()==null){
         	$(this).css('height',($(this).contents().find('body')[0].scrollHeight)+50+'px');
		}else{
			$(this).css('height',window.innerHeight-40+'px');
		}
		
		if(($(this)[0].contentWindow.location.pathname).indexOf("statistics") > -1){
			$('#list_Box').css('width','1300px');
		}else{
			$('#list_Box').css('width','1100px');
		}
	});
});
function wrtieNewSheet(){
	window.parent.location.href="writeEstimate";
}
</script>

<body>
<div id="title">見積書リスト</div>
<div style="text-align: right; margin-bottom: 5px;"><span class="pc_font_button2" onclick="wrtieNewSheet()">新しく作成</span></div>
<div style="text-align: center;">
<table id="list_table">
	<tr>
		<td style="width: 2%;"><input id='allSelect' type='checkbox' name='selectAll' value='on' onchange="selectAll()"></td>
		<td style="width: 5%;">文書番号</td>
		<td style="width: 9%;">顧客</td>
		<td style="width: 9%;">件名</td>
		<td style="width: 9%;">保存日時</td>
		<td style="width: 5%;">請求書</td>
		<td class="lastColum" style="width: 5%;"><span class="pc_font_button2">修正作成</span></td>
	</tr>
</table>
</div>
</body>
</html>