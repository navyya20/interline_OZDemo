<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
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

<script src="<c:url value = '../resources/js/estimateSheet.js?ver=1'/>"></script>

<body>
<div id="userInformJsonString" style="display: none;">${userInformJsonString}</div>
<div id="OZViewer" style="width:98%;height:98%"></div>
<script type="text/javascript" >
	//세션으로부터 유저인폼 jsonString을 받는다.
	function getUserInform(){
		var userInformJsonString = $('#userInformJsonString').html();
		console.log("userInformJsonString:"+userInformJsonString);
		return userInformJsonString;
	}

	function SetOZParamters_OZViewer(){
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("viewer.ignore_disable_color_inputcomponent","true");
		oz.sendToActionScript("viewer.external_functions_path","ozp://OZDemo/JS/estimateSheet.js");
		oz.sendToActionScript("connection.servlet","http://<%out.print(properties.getOzIP());%>/oz80/server");
		oz.sendToActionScript("connection.reportname","OZDemo/writeEstimateSheet.ozr");
		oz.sendToActionScript("connection.inputjson", getUserInform());
		oz.sendToActionScript("connection.pcount","2");
		oz.sendToActionScript("connection.args1","repeat=13");
		oz.sendToActionScript("connection.args2","itemJson="+itemJsonString);
		return true;
	}
	start_ozjs("OZViewer","http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/");
	
	function OZUserEvent_OZViewer(inputJsonString, param2, param3) {
		var inputJson=JSON.parse(inputJsonString);
		var processedInputJson = getJsonToSend(inputJson);
		var address="saveEstimate";
		sendInputJson(processedInputJson,address);
	}
</script>
</body>
</html>