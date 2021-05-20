<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Member</title>
</head>

<script src="<c:url value = '../resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">

<script>

</script>

<style>
</style>
<body class="pc_body">
	<div onclick="parent.location.href='writeEstimate?estimateType=a'">
		<table style="margin: auto;">
			<tr>
				<td colspan="2" style="text-align: center;">
					サンプルタイプA
				</td>
			</tr>
			<tr>
				<td>
					<img src="../resources/image/estimateType/estimateTypeA.jpg" width="248" height="350">
				</td>
				<td>
					<img src="../resources/image/estimateType/billTypeA.jpg" width="248" height="350">
				</td>
			</tr>
		</table>
	</div>
	<br>
	<div onclick="parent.location.href='writeEstimate?estimateType=b'">
		<table style="margin: auto;">
			<tr>
				<td colspan="2" style="text-align: center;">
					サンプルタイプB
				</td>
			</tr>
			<tr>
				<td>
					<img src="../resources/image/estimateType/estimateTypeA.jpg" width="248" height="350">
				</td>
				<td>
					<img src="../resources/image/estimateType/billTypeA.jpg" width="248" height="350">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>