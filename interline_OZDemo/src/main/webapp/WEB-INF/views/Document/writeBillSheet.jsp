<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% GetProperties properties= new GetProperties(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BillSheet</title>
</head>

<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.css" type="text/css"/>
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/OZJSViewer.js" charset="utf-8"></script>

<body style="width:98%;height:98%">

<script>
function OZUserEvent_OZViewer(){
	var billData=JSON.parse(OZViewer.GetInformation("INPUT_JSON_ALL"));
	billData["reportNum"] ="${reportNum}";
	var strData = JSON.stringify(billData);

	$.ajax(
			{
				url: "saveBill",
				type: 'POST',
				data: {jsonStr:strData},
				dataType:'text',
				success: function(data){
					alert("保存成功");
					location.href="memberMain";
				},
				error: function(e){
					console.log(JSON.stringify(e));
					alert('エラー！');
				}
			}		
	);
	
}

function pagechange(){
	window.history.back();
}
</script>

<div id="OZViewer" style="width:98%;height:98%"></div>
<script type="text/javascript" >
	function SetOZParamters_OZViewer(){
		var reportNum = "${reportNum}";
		var id = "${id}";

		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("connection.servlet","http://<%out.print(properties.getOzIP());%>/oz80/server");
		oz.sendToActionScript("connection.reportname","OZDemo_Bill/writeBill.ozr");

		oz.sendToActionScript("global.language", "ja");
		oz.sendToActionScript("odi.odinames", "writeBill");
 		oz.sendToActionScript("odi.writeBill.pcount", "2");
		oz.sendToActionScript("odi.writeBill.args1", "reportnum="+reportNum);
		oz.sendToActionScript("odi.writeBill.args2", "id="+id);
		return true;
	}
	start_ozjs("OZViewer","http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/");
</script>
<script>
	
</script>
</body>
</html>
