<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>estimateSheetList</title>
</head>

<script src="<c:url value = '../resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="../resources/css/Font-Style.css?ver=1" rel="stylesheet">
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
				//$("body").attr('class','mobile_body');
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
function writeNewSheet(){
	window.parent.location.href="writeEstimate";
}

function writeBillSheet(reportNum){
	window.parent.location.href = "writeBill?reportNum=" +reportNum;
}

function readBillSheet(reportNum){
	window.parent.location.href = "readBill?reportNum=" +reportNum;
}

function formSubmit(page){
	var pp = document.getElementById('page');
	
	pp.value=page;
							
	document.location.href = "estimateSheetList?page=" + pp.value;
}

function modEstimateSheet(reportNum){
	window.parent.location.href = "modEstimate?reportNum=" + reportNum;
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
				url: "deleteSheet",
				type: 'GET',
				traditional : true,
				data: {"reportNum":reportNumArray},
				success: function(data){
					document.location.href = "estimateSheetList";
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
<div id="title">同意書リスト</div>
<div style="text-align: left; margin-bottom: 5px; margin-top: 5px; "><span class="pc_font_button2" onclick="deleteSheet()" style="background-color: rgb(255, 204, 0); color: rgb(0,0,0); border-color: rgb(255, 204, 0); padding:1px 20px 1px 20px;">削除</span></div>
<div style="text-align: center;">
<table id="list_table">
	<tr>
		<td class="titleRow" style="width: 2%;"><input id='allSelect' type='checkbox' name='selectAll' value='on' onchange="selectAll()"></td>
		<td class="titleRow" style="width: 5%;">分類</td>
		<td class="titleRow" style="width: 9%;">日時</td>
		<td class="titleRow" style="width: 9%;">顧客</td>
	</tr>
	<c:forEach var="sheet" items="${AgreementArray}" varStatus="status">
	<tr>
		<td><input id='row${status.count}' type='checkbox' name='selectedRow' value='${sheet.reportNum}'></td>
		<td>${sheet.sort}</td>
		<td>
			<fmt:parseDate value="${sheet.agreementDate}" var="noticePostDate" pattern="yyyy-MM-dd HH:mm:ss"/>
			<fmt:formatDate value="${noticePostDate}" pattern="yyyyMMdd HH:mm"/></td>
		<td><a href="readAgreement?reportNum=${sheet.reportNum}" target="_parent" style="text-decoration: none;">${sheet.customer}</a></td>
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