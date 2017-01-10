<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	Calendar calendar = Calendar.getInstance();
	int nowYear = calendar.get(Calendar.YEAR);
	request.setAttribute("nowYear", nowYear);
	
	List<Integer> list = new ArrayList<Integer>();
	for(int i=nowYear-4;i<=nowYear;i++){
		list.add(i);
	}
	request.setAttribute("list", list);
%>

<!DOCTYPE HTML>
<html>
  <head>
    <%@include file="/common/header.jsp"%>
    <title>年度投诉统计图</title>
    <script type="text/javascript" src="${basePath}js/fusioncharts/fusioncharts.js"></script>
    <script type="text/javascript" src="${basePath}js/fusioncharts/fusioncharts.charts.js"></script>
	<script type="text/javascript" src="${basePath}js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>
	<script type="text/javascript">
		$(document).ready(doAnnualStatistic());
		
		function doAnnualStatistic() {
			var year = $("#year option:selected").val();
			if (year == "" || year == undefined) {
				year = "${requestScope.nowYear}"
			}
			$.ajax({
				type : "post",
				url : "complain_getAnnualStatisticData.action",
				data : {"year" : year},
				dataType : "json",
				success : function(data) {
					if(data!=null && data!="" && data!=undefined){
						var revenueChart = new FusionCharts(
						{
							"type" : "line",
							"renderAt" : "chartContainer",
							"width" : "600",
							"height" : "400",
							"dataFormat" : "json",
							"dataSource" : {
								"chart" : {
									"caption" : year+" 年度投诉统计图",
									"xAxisName" : "月  份",
									"yAxisName" : "投  诉  数",
									"theme" : "fint"
								},
								"data" : data.chartData
							}
						});
						revenueChart.render();
					}else{
						alert("获取统计投诉数失败！");
					}
				},
				error : function() {
					alert("获取统计投诉数失败！");
				}
			});
		} 
	</script>
</head>
  
  <body>
  	<br>
    <div style="text-align: center;width: 100%;"><s:select id="year" list="#request.list" value="#request.nowYear" onchange="doAnnualStatistic()"></s:select></div>
    <br>
    <div id="chartContainer" style="text-align: center;width: 100%;"></div>
  </body>
</html>
