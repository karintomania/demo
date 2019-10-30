$(document).ready(function(){

	$("#drawChart").click(function(){
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(mainChart);
	});

});


function mainChart(){

	var stockCode = $("#stockCodetext").val();

	$.ajax({
		type : "POST",
		url : "http://localhost:8080/getPriceList",
		data : {stockCode: stockCode},
		success : function(data) {
			console.log("SUCCESS: ", data);
			drawCandleStickChart(data);
			drawBarChart(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	})

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
            chartData.addRow([String(stockData[i].date).substr(0,10),parseFloat(stockData[i].lowPrice),parseFloat(stockData[i].openPrice),parseFloat(stockData[i].closePrice),parseFloat(stockData[i].highPrice)]);
		}
		
        var options = {
            // chartArea:{left:80,top:10,right:80,bottom:10},
            colors: ['#003A76'],
            legend: {
                position: 'none',
            },
            vAxis:{
                viewWindowMode:'maximized'
            },
            bar: { 
                groupWidth: '50%' 
            },
            width: 1200,
            height: 400,
            lineWidth: 2,
			//チャートのタイプとして、ローソク足を指定
            seriesType: "candlesticks"
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
            chartData_volume.addRow([String(stockData[i].date).substr(0,10),parseFloat(stockData[i].volume)]);
		}

        var options = {
            colors: ['#003A76'],
            legend: {
                position: 'none',
            },
            bar: { 
                groupWidth: '50%' 
            },
            width: 1200,
            height: 400,
            lineWidth: 2,
        };
		//描画の処理
        var chart = new google.visualization.ColumnChart(document.getElementById('appendVolume'));
		chart.draw(chartData_volume, options);
}