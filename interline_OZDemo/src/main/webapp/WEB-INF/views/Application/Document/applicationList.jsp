<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>applicationList</title>
</head>

<script src="<c:url value = '../resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="../resources/css/Font-Style.css?ver=2" rel="stylesheet">
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
		vertical-align: middle;
		word-break: break-all;
	}
	.lastColum{
		padding-bottom: 4px;
		padding-top: 2px;
	}
	#navigator a{
		text-decoration: none;
		color: black;
	}
	.titleRow{
		background-color: rgb(150, 200, 255);
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
				$("#allSelect").attr('class','mobile_checkbox');
				$(".checkbox").attr('class','mobile_checkbox');
				$(".btn").addClass("pc_font_button1");
		    }else{
		    	$("body").addClass("pc_body");
		    	$("#title").addClass("pc_font_title");
		    	$(".btn").addClass("pc_font_button2");
		    }
		}
	} 
});
function formSubmit(page){
	var pp = document.getElementById('page');
	
	pp.value=page;
							
	document.location.href = "applicationList?page=" + pp.value;
}

function deleteSheet(){
	var checkBox=$('input[name="selectedRow"]');
	var reportNumArray=new Array();
	for(var i=0 ; i<checkBox.length ; i++) {
	    if (checkBox[i].checked == true){
	    	reportNumArray.push(parseInt(checkBox[i].value));
	    }
	}
	console.log("reportNumArray:"+reportNumArray);
	if(reportNumArray.length　==　0){alert("削除対象が選択されていません。"); return false;}
	if(!confirm("削除しますか。")){ return false; }
	$.ajax(
			{
				url: "deleteApplication",
				type: 'GET',
				traditional : true,
				data: {"reportNum":reportNumArray},
				success: function(data){
					document.location.href = "applicationList";
				},
				error: function(e){
					console.log(JSON.stringify(e));
					alert('エラー！');
				}
			}		
	);
}
function selectAll(){
	var checkBoxSelectAll=$('input[name="selectAll"]');
	var checkBoxItems=$('input[name="selectedRow"]');
	if (checkBoxSelectAll[0].checked == true){
		checkBoxItems.prop('checked', true) ;
	}else{
		checkBoxItems.prop('checked', false) ;
	}
}

</script>

<body>
<div id="title">文書リスト</div>
<div style="text-align: left; margin-bottom: 5px; margin-top: 5px; "><span class="btn" onclick="deleteSheet()" style="background-color: rgb(255, 204, 0); color: rgb(0,0,0); border-color: rgb(255, 204, 0); padding:1px 20px 1px 20px;">削除</span></div>
<div style="text-align: center;">
<table id="list_table">
	<tr>
		<td class="titleRow" style="width: 0.5%;"><input id='allSelect' type='checkbox' name='selectAll' value='on' onchange="selectAll()"></td>
		<td class="titleRow" style="width: 10%;">名前</td>
		<td class="titleRow" style="width: 5%;">年齢</td>
		<td class="titleRow" style="width: 8%;">日時</td>
		<td class="titleRow" style="width: 10%;">連絡先</td>
	</tr>
	<!-- 문서 리스트 뽑는곳  -->
	<c:forEach var="sheet" items="${applicationArray}" varStatus="status">
	<tr>
		<td><input id='row${status.count}' class="checkbox" type='checkbox' name='selectedRow' value='${sheet.reportNum}'></td>
		<td><a href="${sheet.PDFPath}">${sheet.name}</a></td>
		<td>
			<fmt:parseDate value="${sheet.birth}" var="noticePostDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			<fmt:formatDate value="${noticePostDate}" pattern="yyyyMMdd"/></td>
		<td>
			<fmt:parseDate value="${sheet.systemDate}" var="noticePostDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			<fmt:formatDate value="${noticePostDate}" pattern="yyyyMMdd"/><br><fmt:formatDate value="${noticePostDate}" pattern="HH:mm"/></td>
		<td>${sheet.telephone}<br>${sheet.mobile}</td>		
	</tr>
	</c:forEach>

</table>
</div>


<div id = "navigator">
	<a href="javascript:formSubmit(${pn.currentPage - pn.pagePerGroup})">◁◁</a>&nbsp;
	<a href="javascript:formSubmit(${pn.currentPage-1})">◀</a> &nbsp;&nbsp;
	
	<c:forEach var="counter" begin="${pn.startPageGroup}" end="${pn.endPageGroup}">
	<c:if test="${counter == pn.currentPage}"><b></c:if>
	<a href="javascript:formSubmit(${counter})">${counter}</a>&nbsp;
	<c:if test="${counter == pn.currentPage}"></b></c:if>
	</c:forEach>
	&nbsp;&nbsp;
	<a href="javascript:formSubmit(${pn.currentPage + 1})">▶</a> &nbsp;&nbsp;
	<a href="javascript:formSubmit(${pn.currentPage + pn.pagePerGroup})">▷▷</a>
	<input type="hidden" id="page">
</div>


</body>
</html>