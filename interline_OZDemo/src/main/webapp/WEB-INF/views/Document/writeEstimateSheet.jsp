<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>estimateSheet</title>
</head>

<script src="http://192.168.1.20:8888/oz80/ozhviewer/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://192.168.1.20:8888/oz80/ozhviewer/jquery-ui.css" type="text/css"/>
<script src="http://192.168.1.20:8888/oz80/ozhviewer/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://192.168.1.20:8888/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://192.168.1.20:8888/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://192.168.1.20:8888/oz80/ozhviewer/OZJSViewer.js" charset="utf-8"></script>

<script src="<c:url value = '../resources/js/estimateSheet.js'/>"></script>

<body>
<div id="OZViewer" style="width:98%;height:98%"></div>
<script type="text/javascript" >
	function SetOZParamters_OZViewer(){
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("viewer.ignore_disable_color_inputcomponent","true");
		oz.sendToActionScript("viewer.external_functions_path","ozp://OZDemo/JS/estimateSheet.js");
		oz.sendToActionScript("connection.servlet","http://192.168.1.20:8888/oz80/server");
		oz.sendToActionScript("connection.reportname","OZDemo/writeEstimateSheet.ozr");
		oz.sendToActionScript("connection.pcount","2");
		oz.sendToActionScript("connection.args1","repeat=13");
		oz.sendToActionScript("connection.args2","itemJson="+itemJsonString);
		return true;
	}
	start_ozjs("OZViewer","http://192.168.1.20:8888/oz80/ozhviewer/");
	
	function OZUserEvent_OZViewer(inputJsonString, param2, param3) {
		var inputJson=JSON.parse(inputJsonString);
		var processedInputJson = getJsonToSend(inputJson);
		var address="saveEstimate";
		sendInputJson(processedInputJson,address);
	}
</script>
</body>
</html>