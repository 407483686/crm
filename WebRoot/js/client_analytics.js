$(function(){
	var myChart = echarts.init(document.querySelector("#client_analytics_chart"));
		
	var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: '{a} <br/>{b}: {c} ({d}%)'
		    },
		    legend: {
		        orient: 'vertical',
		        left: "10%",
		        top : "15%",
		        itemWidth : 40,
		        itemHeight : 20,
		        textStyle : {
		        	fontSize : 15,
		        	fontWeight : "bolder",
		        	color : "black"
		        }
		    },
		    series: [
		        {
		            name: '客户来源',
		            type: 'pie',
		            radius: ['50%', '70%'],
		            avoidLabelOverlap: false,
		            label: {
		                fontSize : 15,
		            	fontWeight : "bolder",
		            	color : "black",
		                position: 'outside'
		            },
		            labelLine: {
		                show: true,
		                length : 60,
		                length2 : 30,
		                smooth : true
		            }
		        }
		    ]
		};

	
	$.ajax({
	  type : "post",
      url : "client_loadAll.action",
      data : {},
      dataType : "json", //返回数据形式为json
      success : function(result) {
    	  if (result) {
    		 //初始化
    		 option.series[0].data = [];
    		 
    		 //客户有三个来源,设定三个对象
    		 var obj1 = {name : "广告营销" ,value : 0};
    		 var obj2 = {name : "电话营销" ,value : 0};
    		 var obj3 = {name : "主动联系" ,value : 0};
    		 
    		 for(var i=0;i < result.length;i++){
    			 if(result[i].source == "广告营销"){
    				 obj1.value += 1;
    			 }
				 if(result[i].source == "电话营销"){
					 obj2.value += 1;			 
				 }
				 if(result[i].source == "主动联系"){
					 obj3.value += 1;
				 }
				 
    		 }
    		 
    		 option.series[0].data.push(obj1);
    		 option.series[0].data.push(obj2);
    		 option.series[0].data.push(obj3);
    		 
             myChart.setOption(option);
          }
       }
   });  
	
	
	$("#reload_caChart").linkbutton({
		iconCls : "icon-reload",
		plain : true,
		size : "large",
		onClick : function(){
			$.ajax({
				  type : "post",
			      url : "client_loadAll.action",
			      data : {},
			      dataType : "json", //返回数据形式为json
			      success : function(result) {
			    	  if (result) {
			    		 //初始化
			    		 option.series[0].data = [];
			    		 
			    		 //客户有三个来源,设定三个对象
			    		 var obj1 = {name : "广告营销" ,value : 0};
			    		 var obj2 = {name : "电话营销" ,value : 0};
			    		 var obj3 = {name : "主动联系" ,value : 0};
			    		 
			    		 for(var i=0;i < result.length;i++){
			    			 if(result[i].source == "广告营销"){
			    				 obj1.value += 1;
			    			 }
							 if(result[i].source == "电话营销"){
								 obj2.value += 1;			 
							 }
							 if(result[i].source == "主动联系"){
								 obj3.value += 1;
							 }
							 
			    		 }
			    		 
			    		 option.series[0].data.push(obj1);
			    		 option.series[0].data.push(obj2);
			    		 option.series[0].data.push(obj3);
			    		 
			             myChart.setOption(option);
			          }
			       }
			   }); 
		}
	});
});



