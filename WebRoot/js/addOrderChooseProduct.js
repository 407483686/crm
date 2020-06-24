
$(function(){
	$("#order-search-product").datagrid({
		url : '/CRM/product_loadByPage.action',
		striped : true,
		fitColumns : true,
		rownumbers : true,
		border : false,
		queryParams : {
			kuCun : true
		},
		// 分页工具栏
		pagination : true,
		// 设置初始页面记录数，不设置的话初始是10
		pageSize : 10,
		// 设置页面分页选项
		pageList : [ 10, 20, 30, 40, 50 ],
		toolbar : "#order_tool-product",
		columns : [ [ {
			field : 'id',
			title : '自动编号',
			width : 80,
			hidden : true
		},{
			field : 'sn',
			title : '产品编号',
			width : 80,
		},{
			field : 'name',
			title : '产品名称',
			width : 100,
		},{
			field : 'unit',
			title : '计量单位',
			width : 70,
		},{
			field : 'sell_price',
			title : '出售价(元)',
			width : 80,
		},{
			field : 'inventory',
			title : '库存',
			width : 80,
		},{
			field : 'number',
			title : '选择数量',
			width : 80,
			formatter : function(value,row){
				//在这里设置最大最小值，下面无法获取库存
				return "<input type='text'class='order-number' min=0 max=" + row.inventory + "/>";
			}
		},{
			field : 'select',
			title : '选择产品',
			width : 70,
			formatter : function(value,row,index){
				return "<a href='javascript:void(0)' rowid=" + row.id + " rowSn=" + row.sn + " rowName=" + row.name +" rowUnit=" + row.unit + " rowSell_price=" + row.sell_price + " rowInventory=" + row.inventory + " rowIndex=" + index + " class='selectproduct-button' style='height:18px;margin-left:5px;'>选择</a>";
			}
		}]],
		//加载完之后给formatter定义的超链接加载图标
		onLoadSuccess : function(){
			//给产品数量设置为数字框
			$(".order-number").numberbox({
				prompt : 0
			});
			
			//给选择产品列添加图标
			$(".selectproduct-button").linkbutton({
				iconCls : "icon-tick",
				plain : true
			});
			//给选择产品列添加点击事件
			$(".selectproduct-button").click(function(){
				var rowid = $(this).attr("rowid");
				var rowSn = $(this).attr("rowSn");
				var rowName = $(this).attr("rowName");
				var rowUnit = $(this).attr("rowUnit");
				var rowSell_price = $(this).attr("rowSell_price");
				var rowInventory = $(this).attr("rowInventory");
				var rowIndex = $(this).attr("rowIndex");
				var rowNumber = $(".order-number").eq(rowIndex).val();
				
				//判断输入框的数字是否大于0 
				if(rowNumber <= 0){
					$.messager.alert("警告操作","添加订单的数量必须大于零！");
				}else{
					//利用Jquery的方法判断产品是否已经被添加,没找到返回-1，找到返回索引值
					if($.inArray(rowid,ids) >= 0){
						$.messager.alert("警告操作","此产品已经被添加！");
					}else{
						//给数据列表添加数据
						$("#order-product-list").datagrid("appendRow",{
							id : rowid,
							sn : rowSn,
							name : rowName,
							unit : rowUnit,
							sell_price : rowSell_price,
							inventory : rowInventory,
							number : rowNumber
						});
						//把id添加进行数组
						ids.push(rowid);
						//对原始总价格进行计算
						original_price = ((original_price * 100) + (rowSell_price * rowNumber * 100)) / 100;
						//给真正要传入数据库的原始价格参数赋值
						$("#order-original-price").val(original_price.toFixed(2));
						//让原始总价格显示在页面上
						$("#order-add-original").text('￥' + original_price.toFixed(2));
						//关闭对话框
						$("#order_products_dialog").dialog("close");
					}
				}
			});
		},
		//让点击无法选中
		onClickCell : function(index){
			$("#order-search-product").datagrid("selectRow",index);										
		}
	});
		
	// 给工具条的重置查询按钮绑定点击事件
	$("#order_tool_undoBtn-product").click(
			function() {
				// 清空关键字
				$("#order-search-keywords-product").textbox("clear");
				// 利用jquery的函数触发查询按钮的点击事件
				$('#order_tool_searchBtn-product').trigger('click');
	});
	
	
	//设置关键字
	$("#order-search-keywords-product").textbox({
		prompt : "产品名称|编号"
	});
	
	// 设置查询按钮
	$("#order_tool_searchBtn-product").click(
		function() {
			if ($("#order_tool-product").form("validate")) {
				$("#order-search-product").datagrid(
					"load",
					{
						// datagrid的load方法通常用于额外传递参数进行查询
						//这里虽然只有根据关键字进行查询，但是其他不写有可能会空指针
						"queryCriteria.keywords" : $("#order-search-keywords-product").textbox("getValue"),
						"queryCriteria.type" : "",
						"queryCriteria.dateType" : "",
						"queryCriteria.dateFrom" : "",
						"queryCriteria.dateTo" : "",
						"kuCun" : true
					});
			}
	});
});
