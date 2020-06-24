$(function(){
	var myChart = echarts.init(document.querySelector("#product_number_chart"));
		
	var option = {
		tooltip: {
	        trigger: 'axis',
	        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
	            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis: [
	        {
	            type: 'category',
	            axisTick: {
	                alignWithLabel: true
	            },
	            axisLine : {
	            	lineStyle : {
	            		color : "skyblue",
	            		width : 1
	            	}
	            },
	            axisLabel : {
	            	fontSize : 15,
	            	fontWeight : "bolder",
	            	color : "black"
	            }
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            axisLine : {
	            	lineStyle : {
	            		color : "skyblue",
	            		width : 1
	            	}
	            },
	        	splitLine : {
	        		lineStyle : {
	            		color : "skyblue",
	            		width : 1
	            	}
	        	},
	            axisLabel : {
	            	fontSize : 15,
	            	fontWeight : "bolder",
	            	color : "black"
	            }
	        }
	    ],
	    series: [
	        {
	            name: '产品销量',
	            type: 'bar',
	            barWidth: '35%',
	            itemStyle : {
	            	barBorderRadius : [10,10,0,0]
	            	
	            },
	            color : function(params){
	            	//echarts传入的params是柱子对象，里面有很多属性
	            	//有一个需要重点关注的属性：dataIndex，每一个柱子都会有一个索引，从0开始，到data.length-1结束
	            	var Color = ["#C23531","orange","yellow","green","skyblue","gray","purple","#bce672","#057748"];
	            	var index = params.dataIndex;
	            	return Color[index];
	            },
	        }
	    ]
	};

	
	$.ajax({
	  type : "post",
      url : "product_loadAll.action",
      data : {},
      dataType : "json", //返回数据形式为json
      success : function(result) {
    	  if (result) {
            //初始化option.xAxis[0]中的data
             option.xAxis[0].data=[];
             for(var i=0;i<result.length;i++){
               option.xAxis[0].data.push(result[i].name);
             }
             //初始化option.series[0]中的data
             option.series[0].data=[];
             for(var i=0;i<result.length;i++){
               option.series[0].data.push(result[i].inventory_out);
             }
             
             myChart.setOption(option);
          }
       }
   });  
	
	$("#reload_pnChart").linkbutton({
		iconCls : "icon-reload",
		plain : true,
		size : "large",
		onClick : function(){
			$.ajax({
				  type : "post",
			      url : "product_loadAll.action",
			      data : {},
			      dataType : "json", //返回数据形式为json
			      success : function(result) {
			    	  if (result) {
			            //初始化option.xAxis[0]中的data
			             option.xAxis[0].data=[];
			             for(var i=0;i<result.length;i++){
			               option.xAxis[0].data.push(result[i].name);
			             }
			             //初始化option.series[0]中的data
			             option.series[0].data=[];
			             for(var i=0;i<result.length;i++){
			               option.series[0].data.push(result[i].inventory_out);
			             }
			             
			             myChart.setOption(option);
			          }
			       }
			}); 
		}
	});
});



