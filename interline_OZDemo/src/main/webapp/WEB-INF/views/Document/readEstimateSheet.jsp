<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% GetProperties properties= new GetProperties(); %>
<!DOCTYPE html>
<html style="height:97.6%">
<head>
<meta charset="UTF-8">
<title>Read EstimateSheet</title>
</head>

<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.css" type="text/css"/>
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/OZJSSVGViewer.js" charset="utf-8"></script>
<!-- If you want to run the HTML5SVG viewer please change the OZJSViewer.js to OZJSSVGViewer.js.
<script type="text/javascript" src="http://192.168.0.103:8888/oz80/ozhviewer/OZJSSVGViewer.js" charset="utf-8"></script>   OZJSViewer.js   OZJSSVGViewer.js
-->

<script src="<c:url value = '../resources/js/estimateSheet.js?ver=1'/>"></script>
<link href="../resources/css/Font-Style.css?ver=7" rel="stylesheet">
<style>
        .mainMenuButton{	
			display:inline-block;
			width:100px;
		}
		.mainMenuTd{
			width: 100px;
		}
	</style>
<body style="width:99.5%;height:97.6%">
<div id="menuBar" style="position:relative; left: 0px; z-index: 1000; text-align: center; width:100%;">
	<table style="text-align: center; margin: auto;">
		<tr>
			<td class="mainMenuTd">
				<span id="writeNewEstimateSheet" class="pc_font_button1 mainMenuButton" onclick="cancelButton()">戻る</span>
			</td>
		</tr>
	</table>
</div>
<div id="OZViewer" style="width:99.5%;height:97.6%"></div>
<script type="text/javascript" >
$(document).ready(function(){
	 isMobile(); 
	function isMobile() {
	    var filter = "win16|win32|win64|mac|macintel";
	    if( navigator.platform  ){
	      if( filter.indexOf(navigator.platform.toLowerCase())<0 ){
	      }else{
	    	  $("#menuBar").addClass('pc_body');
		      $("#title").addClass("pc_font_title");
		      $(".btn").addClass("pc_font_button2");
	      }
	    }
	  }
	
});
	//세션으로부터 유저인폼 jsonString을 받는다.
	var userNum = "${userNum}";
	var reportNum = "${reportNum}";
	var stamp = "${stamp}"
	//var userNum = $('#userNum').html();
	//var reportNum = $('#reportNum').html();
	console.log("userNum:"+userNum);
	console.log("reportNum:"+reportNum);
	console.log("stamp:"+stamp);
	var now = new Date();
	var fileName=reportNum+"_OZDemo_"+now.getFullYear() + numFormat((now.getMonth()+1)) + numFormat(now.getDate()) + "_" + numFormat(now.getHours()) + numFormat(now.getMinutes()) + numFormat(now.getSeconds());
	console.log("filename:"+fileName);
	function SetOZParamters_OZViewer(){
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("viewer.ignore_disable_color_inputcomponent","true");
		oz.sendToActionScript("viewer.external_functions_path","ozp://OZDemo/JS/estimateSheet.js");
		oz.sendToActionScript("connection.servlet","http://<%out.print(properties.getOzIP());%>/oz80/server");
		oz.sendToActionScript("connection.reportname","OZDemo/readEstimateSheet.ozr");
		oz.sendToActionScript("connection.pcount","1");
		oz.sendToActionScript("connection.args1","stamp="+stamp);
		oz.sendToActionScript("global.language", "ja");
		oz.sendToActionScript("odi.odinames","readEstimateSheet");
		oz.sendToActionScript("odi.readEstimateSheet.pcount","2");
		oz.sendToActionScript("odi.readEstimateSheet.args1","userNum="+userNum);
		oz.sendToActionScript("odi.readEstimateSheet.args2","reportNum="+reportNum);
		oz.sendToActionScript("export.filename",reportNum+"_OZDemo_"+now.getFullYear() + (now.getMonth()+1).toString().padStart(2,'0') + now.getDate().toString().padStart(2,'0') + "_" + now.getHours().toString().padStart(2,'0') + now.getMinutes().toString().padStart(2,'0') + now.getSeconds().toString().padStart(2,'0'));
		oz.sendToActionScript("pdf.fontembedding","true");
		return true;
	}
	var opt = [];
	opt["print_exportfrom"] = "server"; //인쇄 PDF 익스포트 작업을 서버와 통신하여 동작
	opt["save_exportfrom"] = { "pdf" : "server" }; //PDF 익스포트 작업을 서버와 통신하여 동작 
	start_ozjs("OZViewer","http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/", opt);
	
	function OZUserEvent_OZViewer(inputJsonString, param2, param3) {
		var inputJson=JSON.parse(inputJsonString);
		var processedInputJson = getJsonToSend(inputJson);
		var address="saveEstimate";
		sendInputJson(processedInputJson,address);
	}
	function numFormat(variable) { 
		variable = Number(variable).toString(); 
		if(Number(variable) < 10 && variable.length == 1) variable = "0" + variable; 
		return variable; 
	}
	function cancelButton(){
		var userInformJsonString = $('#userInformJsonString').html();
		//alert("userInformJsonString:"+userInformJsonString);
		location.href="memberMain";
	}

</script>
</body>
</html>