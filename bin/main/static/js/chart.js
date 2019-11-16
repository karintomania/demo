$(document).ready(function(){
	$("#searchStock").submit(function(event){
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(mainChart);
		event.preventDefault();

	});



function mainChart(){

	var stockCode = $("#stockCodetext").val();

	// testDraw();
	
	if(stockCode != ""){
		$.ajax({
			type : "POST",
			url : "/getPriceList",
			data : {stockCode: stockCode},
			success : function(data) {
				console.log("SUCCESS: ", data);
				displayCompanyInfo(data.stock);
				drawCandleStickChart(data.priceList);
				drawBarChart(data.priceList);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		})
	}else{
		alert(1);
	}

}


// ロウソク足チャートの描画
function drawCandleStickChart(stockData){

	//チャートに描画するための最終的なデータを入れる
    var chartData = new google.visualization.DataTable();
		//日付ようにString型のカラムを一つ、チャート描画用に数値型のカラムを７つ作成
        chartData.addColumn('string');
        for(var x = 0;x < 4; x++){
            chartData.addColumn('number');
		}
		
		//チャート描画用の配列の中に、insertingDataの値を入れ込む
		// [日付, 安値, 始値, 終値, 高値]
        for (var i = 0; i < stockData.length; i++){
            chartData.addRow([String(stockData[i].date).substr(5,5).replace("-","/"),parseFloat(stockData[i].lowPrice),parseFloat(stockData[i].openPrice),parseFloat(stockData[i].closePrice),parseFloat(stockData[i].highPrice)]);
		}
		

	var chartContainer = $("#chart_container")[0];
        var options = {
            legend: {
                position: 'none',
            },
            vAxis:{
                viewWindowMode:'maximized'
            },
            bar: { 
                groupWidth: '50%' 
			},
			chartArea:{
				left: 50,
				width: chartContainer.offsetWidth * 0.9
			},
			height: screen.height *0.5,
		};
		
		//描画の処理
        var chart = new google.visualization.CandlestickChart(document.getElementById('appendMain'));
		chart.draw(chartData, options);
}		

//出来高の方
function drawBarChart(stockData){
		
	//チャートに描画するための最終的なデータを入れる
    var chartData_volume = new google.visualization.DataTable();
		//日付ようにString型のカラムを一つ、チャート描画用に数値型のカラムを７つ作成
        chartData_volume.addColumn('string');
		chartData_volume.addColumn('number');
		//チャート描画用の配列の中に、insertingDataの値を入れ込む
		// [日付, 出来高]
        for (var i = 0; i < stockData.length; i++){
            chartData_volume.addRow([String(stockData[i].date).substr(5,5).replace("-","/"),parseFloat(stockData[i].volume)]);
		}

	var chartContainer = $("#chart_container")[0];
	var options = {
		legend: { position: 'none', },
		bar: { groupWidth: '50%' },
		chartArea:{
			left: 50,
			width: chartContainer.offsetWidth * 0.9
		},
		height: chartContainer.offsetWidth * 0.2,
		hAxis: { textPosition: 'none' }
	};

		//描画の処理
        var chart = new google.visualization.ColumnChart(document.getElementById('appendVolume'));
		chart.draw(chartData_volume, options);
}

function displayCompanyInfo(companyInfo){
	var companyInfo = companyInfo.stockCode + " : " +companyInfo.name;
	$("#companyInfo").text(companyInfo);
}

function testDraw(){

	var data = [{"stockCode":1234,"date":"2019-07-12 09:00:00","highPrice":3068,"lowPrice":3030,"openPrice":3065,"closePrice":3060,"volume":22200},
					{"stockCode":1234,"date":"2019-07-11 09:00:00","highPrice":3095,"lowPrice":3020,"openPrice":3030,"closePrice":3065,"volume":21100},
					{"stockCode":1234,"date":"2019-07-12 09:00:00","highPrice":3068,"lowPrice":3030,"openPrice":3065,"closePrice":3060,"volume":22200},
					{"stockCode":1234,"date":"2019-07-13 09:00:00","highPrice":3095,"lowPrice":3010,"openPrice":3030,"closePrice":3085,"volume":21100},
					{"stockCode":1234,"date":"2019-07-14 09:00:00","highPrice":3290,"lowPrice":3000,"openPrice":3085,"closePrice":3251,"volume":21100},
					{"stockCode":1234,"date":"2019-07-15 09:00:00","highPrice":3080,"lowPrice":2800,"openPrice":3030,"closePrice":2840,"volume":21100},
					{"stockCode":1234,"date":"2019-07-16 09:00:00","highPrice":2800,"lowPrice":2400,"openPrice":2800,"closePrice":2500,"volume":21100},
					{"stockCode":1234,"date":"2019-07-16 09:00:00","highPrice":3000,"lowPrice":2400,"openPrice":2420,"closePrice":3000,"volume":21500},
					{"stockCode":1234,"date":"2019-07-11 09:00:00","highPrice":3095,"lowPrice":3020,"openPrice":3030,"closePrice":3065,"volume":21100},
					{"stockCode":1234,"date":"2019-07-12 09:00:00","highPrice":3068,"lowPrice":3030,"openPrice":3065,"closePrice":3060,"volume":22200},
					{"stockCode":1234,"date":"2019-07-13 09:00:00","highPrice":3095,"lowPrice":3010,"openPrice":3030,"closePrice":3085,"volume":21100},
					{"stockCode":1234,"date":"2019-07-14 09:00:00","highPrice":3290,"lowPrice":3000,"openPrice":3085,"closePrice":3251,"volume":22100},
					{"stockCode":1234,"date":"2019-07-15 09:00:00","highPrice":3080,"lowPrice":2800,"openPrice":3030,"closePrice":2840,"volume":21100},
					{"stockCode":1234,"date":"2019-07-16 09:00:00","highPrice":2800,"lowPrice":2400,"openPrice":2800,"closePrice":2500,"volume":21100},
					{"stockCode":1234,"date":"2019-07-16 09:00:00","highPrice":3000,"lowPrice":2400,"openPrice":2420,"closePrice":3000,"volume":21100},
					{"stockCode":1234,"date":"2019-07-11 09:00:00","highPrice":3095,"lowPrice":3020,"openPrice":3030,"closePrice":3065,"volume":23100},
					{"stockCode":1234,"date":"2019-07-12 09:00:00","highPrice":3068,"lowPrice":3030,"openPrice":3065,"closePrice":3060,"volume":22200},
					{"stockCode":1234,"date":"2019-07-13 09:00:00","highPrice":3095,"lowPrice":3010,"openPrice":3030,"closePrice":3085,"volume":21100},
					{"stockCode":1234,"date":"2019-07-14 09:00:00","highPrice":3290,"lowPrice":3000,"openPrice":3085,"closePrice":3251,"volume":21700},
					{"stockCode":1234,"date":"2019-07-15 09:00:00","highPrice":3080,"lowPrice":2800,"openPrice":3030,"closePrice":2840,"volume":21100},
					{"stockCode":1234,"date":"2019-07-16 09:00:00","highPrice":2800,"lowPrice":2400,"openPrice":2800,"closePrice":2500,"volume":21100},
					{"stockCode":1234,"date":"2019-07-16 09:00:00","highPrice":3000,"lowPrice":2400,"openPrice":2420,"closePrice":3000,"volume":21100}];

	drawCandleStickChart(data);
	drawBarChart(data);
}

});