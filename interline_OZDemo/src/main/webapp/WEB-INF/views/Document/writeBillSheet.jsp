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
function OZUserEvent_OZViewer(param1, param2, param3){

	var billData=JSON.parse(OZViewer.GetInformation("INPUT_JSON_ALL"));

	var processedInputJson = getJsonToSend(billData);

	console.log(billData);
	$.ajax(
			{
				url: "saveBill",
				type: 'POST',
				data: processedInputJson,
				success: function(result){
					alert(result);
				}
			}		
	);
}
</script>

<div id="OZViewer" style="width:98%;height:98%"></div>
<script type="text/javascript" >
	function SetOZParamters_OZViewer(){
		var reportNum = "${reportNum}";
		var id = '<%=session.getAttribute("login_id") %>';
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("connection.servlet","http://127.0.0.1:8888/oz80/server");
		oz.sendToActionScript("connection.reportname","writeBill.ozr");

		oz.sendToActionScript("odi.odinames", "Bill");
 		oz.sendToActionScript("odi.Bill.pcount", "2");
		oz.sendToActionScript("odi.Bill.args1", "reportnum="+reportNum);
		oz.sendToActionScript("odi.Bill.args2", "id="+id);
		return true;
	}
	start_ozjs("OZViewer","http://127.0.0.1:8888/oz80/ozhviewer/");
</script>
<script>
	
</script>
</body>
</html>
