<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberList</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">
<script>

$(function(){
	first_MemberList();

	function first_MemberList(){
		 $('#list_Box',parent.document).css('height',$("body")[0].scrollHeight+50+'px');
	}
	
});

function update_member(val){
	location.href="updateMember?userNum="+val;
}

function del_member(val){
	if(confirm("削除しましか？")){
		var userNum = document.getElementById('userNum');
		var form = document.getElementById('delForm_member');
		userNum.value = val;
	
		form.submit();
	}
}
</script>
<style>
body{
text-align:center;
}

table {
  border-collapse: collapse;
}

th,td{
border: 1px solid black;
}

.MemberList_Num,.MemberList_representative,.MemberList_Startdate{
width: 100px;
}

.MemberList_Company,.MemberList_representative{
width: 200px;
}

.MemberList_Id{
width: 150px;
}

.memberList_td_Btn{
border-style: none;
width:50px;
height: 45px;
}

#Member_List{
margin: 34px auto 70px;
width: fit-content;
}
</style>
</head>
<body class="pc_body">
<h1>会員リスト</h1>

<div id="Member_List">
<table class="pc_font_content1">
<tr>
<th class="MemberList_Num" >会員番号</th><th class="MemberList_Id" >会員ID</th><th class="MemberList_Company" >企業名</th><th class="MemberList_representative" >代表者</th><th class="MemberList_Startdate" >入会日時</th>
</tr>
<c:forEach begin="0" var="i" end="${member.size()-1}">
<tr>
<td>${member[i].userNum}</td>
<td>${member[i].userId}</td>
<td>${member[i].companyName}</td>
<td>${member[i].representative}</td>
<td>${member[i].startDate}</td>
<td class="memberList_td_Btn"><button class="pc_font_button2" id="upBtn_member" onclick="update_member(${member[i].userNum})">修正</button></td>
<td class="memberList_td_Btn"><button class="pc_font_button2" id="delBtn_member" onclick="del_member(${member[i].userNum})">削除</button></td>
</tr>
</c:forEach>
</table>
</div>
<form action="deleteMember" method="post" id="delForm_member">
<input type="hidden" name="userNum" id="userNum">
</form>
</body>
</html>