function save(reportNum){
	var applicationData=JSON.parse(OZViewer.GetInformation("INPUT_JSON_ALL")); //입력된 값을 전부 받아오기
	console.log(applicationData);

		$.ajax({
			url: "saveApplication",
			type: 'POST',
			data: applicationData,
			success: function(data){
								
				alert("申込書を作成しました。");
				location.href="applicationMainMenu";
			},
			error: function(e){
				console.log(JSON.stringify(e));
				alert('エラー！');
			}
		});

	return false;
}

function back(){
	location.href="applicationMainMenu";
}