var itemJson={"item": 
					[{"itemNumber": 1111, "itemName": "item1", "unit": "台", "unitPrice": 500}, 
					{"itemNumber": 1112, "itemName": "item2", "unit": "台", "unitPrice": 1000},
					{"itemNumber": 1113, "itemName": "item3", "unit": "台", "unitPrice": 2000},
					{"itemNumber": 1114, "itemName": "item4", "unit": "台", "unitPrice": 3000},
					{"itemNumber": "", "itemName": "", "unit": "", "unitPrice": 0}]
			};
var itemJsonString=JSON.stringify(itemJson);

function serializeEstimateItemsJsonObject(estimateItemsJsonObject){
	var jsonObject = new Object();
	for(var i = 0 ; i < estimateItemsJsonObject.length ; i++){
		jsonObject["itemName"+(i+1)] = estimateItemsJsonObject[i].itemName
		jsonObject["itemNum"+(i+1)] = estimateItemsJsonObject[i].itemNum
		jsonObject["amount"+(i+1)] = estimateItemsJsonObject[i].amount
		jsonObject["unit"+(i+1)] = estimateItemsJsonObject[i].unit
		jsonObject["unitPrice"+(i+1)] = estimateItemsJsonObject[i].unitPrice
		jsonObject["price"+(i+1)] = estimateItemsJsonObject[i].price
		jsonObject["note"+(i+1)] = estimateItemsJsonObject[i].note
	}
	return jsonObject;
}


//input: 폼에서 받은 제이슨
//output: 견적서 VO에 맞는 제이슨과  견적아이템들VO에 맞는 제이슨을 포함한 제이슨
//견적서 VO는 컨트롤러에서 VO로 받을수있게. 견적아이템들VO는 컨트롤러에서 String 으로 받아 제이슨화 하여 VO리스트화 작업을 거쳐야함.
// "￥1,000"형식을 반드시  1000으로 만들어야함. unitPrice1~13,price1~13,sum,sumWithTax,sumWithTax2 해당.
// 또한 reportNum 에 아무 숫자나 넣어줘야  컨트롤러에서 에러가 안난다.  아니면 컨트롤러에서 받을때 default값을 설정하던가.
function getJsonToSend(inputJson){
	console.log("getJsonToSend함수 실행");
	inputJson.reportNum=0;
	inputJson["sum"] = inputJson["sum"].replace(/,/gi, "").replace(/￥/gi, "");
	inputJson["sumWithTax"] = inputJson["sumWithTax"].replace(/,/gi, "").replace(/￥/gi, "");
	inputJson["sumWithTax2"] = inputJson["sumWithTax2"].replace(/,/gi, "").replace(/￥/gi, "");
	
	var estimateItems = new Array();
	for(var i=1 ; i <=13 ; i++){
		if(inputJson["itemName"+i] != ""){
			var item = new Object();
			item.itemNum = inputJson["itemNum"+i];
			item.itemName = inputJson["itemName"+i];
			item.amount = inputJson["amount"+i];
			item.unit = inputJson["unit"+i];
			
			inputJson["unitPrice"+i] =inputJson["unitPrice"+i].replace(/,/gi, "").replace(/￥/gi, "");
			item.unitPrice = inputJson["unitPrice"+i];
			//console.log("변환:"+item.unitPrice);
			
			inputJson["price"+i] = inputJson["price"+i].replace(/,/gi, "").replace(/￥/gi, "");
			item.price = inputJson["price"+i];
			//console.log("변환:"+item.price);
			
			inputJson["amount"+i] = inputJson["amount"+i].replace(/,/gi, "").replace(/￥/gi, "");
			item.price = inputJson["amount"+i];
			//console.log("변환:"+item.amount);
			
			item.note = inputJson["note"+i];
			estimateItems.push(item);
		}
	}
	var estimateItemsJson = new Object();
	estimateItemsJson.itemArray=estimateItems;
	inputJson.estimateItemsString = JSON.stringify(estimateItemsJson);
	console.log(inputJson.estimateItemsString);
	return inputJson;
}


//input: 가공된 inputJson , 보낼 주소
//output: 없음.
// 가공된 json을 address에 POST 방식으로 ajax로 보낸다.
function sendInputJson(processedInputJson,address){
	console.log("sendInputJson함수 실행");
	console.log("address:"+address);
	console.log(processedInputJson);
	$.ajax(
			{
				url: address,
				type: 'POST',
				data: processedInputJson,
				success: function(data){
					alert("見積書を作成しました。");
					location.href="memberMain";
				},
				error: function(e){
					console.log(JSON.stringify(e));
					alert('エラー！');
				}
			}		
	);
}