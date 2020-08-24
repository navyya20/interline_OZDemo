<!DOCTYPE html>
<html style="height:100%">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css"/>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://127.0.0.1:8888/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://127.0.0.1:8888/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://127.0.0.1:8888/oz80/ozhviewer/OZJSViewer.js" charset="utf-8"></script>

</head>
<body style="width:98%;height:98%">
<script>
function pagechange(){
	window.history.back();
}
</script>
<div id="OZViewer" style="width:98%;height:98%"></div>
<script type="text/javascript" >
	function SetOZParamters_OZViewer(){
		var reportNum = "${reportNum}";
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("connection.servlet","http://127.0.0.1:8888/oz80/server");
		oz.sendToActionScript("connection.reportname","readBill.ozr");

		oz.sendToActionScript("odi.odinames", "readBill");
 		oz.sendToActionScript("odi.readBill.pcount", "2");
		oz.sendToActionScript("odi.readBill.args1", "reportnum="+reportNum);
		return true;
	}
	start_ozjs("OZViewer","http://127.0.0.1:8888/oz80/ozhviewer/");
</script>
<script>
	
</script>
</body>
</html>
