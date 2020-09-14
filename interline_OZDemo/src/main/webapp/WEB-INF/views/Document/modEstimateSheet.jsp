<%@ page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% GetProperties properties= new GetProperties(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>estimateSheet</title>
</head>

<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.css" type="text/css"/>
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/OZJSViewer.js" charset="utf-8"></script>

<script src="<c:url value = '../resources/js/estimateSheet.js?ver=4'/>"></script>

<body>
<div id="estimateSheetJsonString" style="display: none;">${estimateSheetJsonString}</div>
<div id="estimateItemsJsonString" style="display: none;">${estimateItemsJsonString}</div>
<div id="OZViewer" style="width:98%;height:98%"></div>
<script type="text/javascript" >
	//세션으로부터 견적서 정보에대한 jsonString을 받는다.
	function getEstimateSheetInform(){
		var estimateSheetJsonString = $('#estimateSheetJsonString').html();		
		var estimateItemsJsonString = $('#estimateItemsJsonString').html();
		console.log("estimateSheetJsonString:"+estimateSheetJsonString);
		console.log("estimateItemsJsonString:"+estimateItemsJsonString);
		var estimateSheetJsonObject = JSON.parse(estimateSheetJsonString);
		//아이템들이 존재하면 인덱스 붙여 1차원 배열로 만든다.
		if (estimateItemsJsonString != ""){
			var estimateItemsJsonObject = JSON.parse(estimateItemsJsonString);
			var estimateItemsJsonObjectSerialized = serializeEstimateItemsJsonObject(estimateItemsJsonObject);
			Object.assign(estimateSheetJsonObject,estimateItemsJsonObjectSerialized);
		}
		
		return estimateSheetJsonObject;
	}
	console.log(JSON.stringify(getEstimateSheetInform()));

	function SetOZParamters_OZViewer(){
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("viewer.ignore_disable_color_inputcomponent","true");
		oz.sendToActionScript("viewer.external_functions_path","ozp://OZDemo/JS/estimateSheet.js");
		oz.sendToActionScript("connection.servlet","http://<%out.print(properties.getOzIP());%>/oz80/server");
		oz.sendToActionScript("connection.reportname","OZDemo/writeEstimateSheet.ozr");
		oz.sendToActionScript("connection.inputjson", JSON.stringify(getEstimateSheetInform()));
		oz.sendToActionScript("connection.pcount","2");
		oz.sendToActionScript("connection.args1","repeat=10");
		oz.sendToActionScript("connection.args2","itemJson="+itemJsonString);
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
		$('#wrapper').css('z-index',2);
		$('#wrapper').css('visibility','visible');
		$('body').css('cursor','progress');
		sendInputJson(processedInputJson,address);
	}
</script>
<div id="wrapper" style="z-index: -1; display: table; background-color:rgb(225,225,225); position: absolute; left: 0%; top: 0%; visibility: hidden; width: 100%; height:170%; opacity: 0.5;"></div>
</body>
</html>