<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
        	<jsp:include page="../common/menus.jsp" flush="true"></jsp:include>
        </div>
        <div class="wu-toolbar-search">
            <label>入住姓名：</label><input id="search-name" class="wu-text" style="width:100px">
            <label>身份证号：</label><input id="search-idCard" class="wu-text" style="width:100px">
            <label>手机号码：</label><input id="search-phoneNum" class="wu-text" style="width:100px">
            <label>房间：</label>
            <select id="search-room" class="easyui-combobox" panelHeight="auto" style="width:100px">
            	<option value="-1">全部</option>
            	<c:forEach items="${roomList }" var="room">
            		<option value="${room.id }">${room.sn }</option>
            	</c:forEach>
            </select>
            <label>房型：</label>
            <select id="search-roomType" class="easyui-combobox" panelHeight="auto" style="width:100px">
            	<option value="-1">全部</option>
            	<c:forEach items="${roomTypeList }" var="roomType">
            		<option value="${roomType.id }" price="${roomType.price }">${roomType.name }</option>
            	</c:forEach>
            </select>
            <label>状态：</label>
            <select id="search-status" class="easyui-combobox" panelHeight="auto" style="width:100px">
            	<option value="-1">全部</option>
            	<option value="0">入住中</option>
            	<option value="1">已离店</option>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>

<!-- 订单选择弹框 -->
<div id="show-order-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:750px; height:500px; padding:10px;">
	<table id="order-datagrid" class="easyui-datagrid"></table>
</div>

<!-- Begin of easyui-dialog -->
<!-- 添加弹窗 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="add-form" method="post">
        <table>
        	<tr>
                <td align="right">预订订单:</td>
                <td>
                	<input type="text" readonly="readonly" id="add-bookOrderId" name="bookOrderId" class="wu-text " />
                </td>
                <td>
					<a href="#" class="select-order-btn easyui-linkbutton" iconCls="icon-add" plain="true">选择</a>
				</td>
            </tr>
        	<tr>
                <td align="right">房间类型:</td>
                <td>
                	<select id="add-roomTypeId" name="roomTypeId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true,missingMessage:'请选择房型'">
		            	<c:forEach items="${roomTypeList }" var="roomType">
            				<option value="${roomType.id }">${roomType.name }</option>
            			</c:forEach>
            		</select>
                </td>
                <td></td>
            </tr>
        	<tr>
                <td align="right">房间:</td>
                <td>
                	<select id="add-roomId" name="roomId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true,missingMessage:'请选择客户'">
		            	<c:forEach items="${roomList }" var="room">
            				<option value="${room.id }">${room.sn }</option>
            			</c:forEach>
            		</select>
                </td>
                <td></td>
            </tr>
            <tr>
                <td align="right">入住价格:</td>
                <td><input type="text" id="add-checkinPrice" name="checkinPrice" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写入住价格'" /></td>
            	<td></td>
            </tr>
            <tr>
                <td align="right">入住人姓名:</td>
                <td><input type="text" id="add-name" name="name" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写入住姓名'" /></td>
            	<td></td>
            </tr>
            <tr>
                <td align="right">身份证号:</td>
                <td><input type="text" id="add-idCard" name="idCard" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请输入身份证号'" /></td>
            	<td></td>
            </tr>
            <tr>
                <td align="right">手机号码:</td>
                <td><input type="text" id="add-phoneNum" name="phoneNum" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写手机号'" /></td>
            	<td></td>
            </tr>
            <tr>
                <td align="right">入住日期:</td>
                <td><input type="text" id="add-arrivedDate" name="arrivedDate" class="wu-text easyui-datebox easyui-validatebox"/></td>
            	<td></td>
            </tr>
            <tr>
                <td align="right">离店日期:</td>
                <td><input type="text" id="add-leaveDate" name="leaveDate" class="wu-text easyui-datebox easyui-validatebox" /></td>
            	<td></td>
            </tr>
            <tr>
                <td align="right">订单状态:</td>
                <td>
	                <select id="add-status" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true,missingMessage:'请选择状态'">
		            	<option value="0">入住中</option>
            			<option value="1">已离店</option>
            		</select>
                </td>
                <td></td>
            </tr>
            <tr>
                <td valign="top" align="right">订单备注:</td>
                <td><textarea id="add-remark" name="remark" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            	<td></td>
            </tr>
        </table>
    </form>
</div>

<!-- 修改弹窗 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
		<input type="hidden" name="id" id="edit-id" />
		<input type="hidden" name="bookOrderId" id="edit-bookOrderId" />
        <table>
            <tr>
                <td align="right">房间类型:</td>
                <td>
                	<select id="edit-roomTypeId" name="roomTypeId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true,missingMessage:'请选择房型'">
		            	<c:forEach items="${roomTypeList }" var="roomType">
            				<option value="${roomType.id }">${roomType.name }</option>
            			</c:forEach>
            		</select>
                </td>
            </tr>
            <tr>
                <td align="right">房间:</td>
                <td>
                	<select id="edit-roomId" name="roomId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true,missingMessage:'请选择客户'">
		            	<c:forEach items="${roomList }" var="room">
            				<option value="${room.id }">${room.sn }</option>
            			</c:forEach>
            		</select>
                </td>
            </tr>
            <tr>
                <td align="right">入住价格:</td>
                <td><input type="text" id="edit-checkinPrice" name="checkinPrice" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写入住价格'" /></td>
            </tr>
            <tr>
                <td align="right">入住人姓名:</td>
                <td><input type="text" id="edit-name" name="name" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写入住姓名'" /></td>
            </tr>
            <tr>
                <td align="right">身份证号:</td>
                <td><input type="text" id="edit-idCard" name="idCard" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请输入身份证号'" /></td>
            </tr>
            <tr>
                <td align="right">手机号码:</td>
                <td><input type="text" id="edit-phoneNum" name="phoneNum" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写手机号'" /></td>
            </tr>
            <tr>
                <td align="right">入住日期:</td>
                <td><input type="text" id="edit-arrivedDate" name="arrivedDate" class="wu-text easyui-datebox easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">离店日期:</td>
                <td><input type="text" id="edit-leaveDate" name="leaveDate" class="wu-text easyui-datebox easyui-validatebox"/></td>
            </tr>
            <tr>
                <td align="right">订单状态:</td>
                <td>
	                <select id="edit-status" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true,missingMessage:'请选择状态'">
		            	<option value="0">入住中</option>
            			<option value="1">已离店</option>
            		</select>
                </td>
            </tr>
            <tr>
                <td valign="top" align="right">订单备注:</td>
                <td><textarea id="edit-remark" name="remark" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>


<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>

<!-- End of easyui-dialog -->
<script type="text/javascript">

	/**
	* 修改客户信息
	*/
	function edit(){
		var validate = $("#edit-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查您输入的数据！","warning");
			return;
		}
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','修改成功！','info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}

	/**
	* 添加客户信息
	*/
	function add(){
		var validate = $("#add-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查您输入的数据！","warning");
			return;
		}
		var data = $("#add-form").serialize();
		$.ajax({
			url:'add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','添加成功！','info');
					$("#add-name").val('');
					$("#add-password").val('');
					$("#add-realName").val('');
					$("#add-idCard").val('');
					$("#add-phoneNum").val('');
					$("#add-address").val('');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}

	/**
	* 打开编辑窗口
	*/
	function openEdit(){
		var item = $('#data-datagrid').datagrid('getSelected');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要编辑的数据！','info');	
			return;
		}
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "编辑入住信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#edit-id").val(item.id);
            	$("#edit-name").val(item.name);
            	$("#edit-arrivedDate").datebox("setValue", item.arrivedDate);
            	$("#edit-leaveDate").datebox("setValue", item.leaveDate);
            	$("#edit-idCard").val(item.idCard);
            	$("#edit-phoneNum").val(item.phoneNum);
            	$("#edit-checkinPrice").val(item.checkinPrice);
            	$("#edit-bookOrderId").val(item.bookOrderId);
            	$("#edit-remark").val(item.remark);
            	$("#edit-status").combobox("setValue", item.status);
            	$("#edit-roomId").combobox("setValue", item.roomId);
            	$("#edit-roomTypeId").combobox("setValue", item.roomTypeId);
            }
        });
	}
	
	/**
	* 登记退房
	*/
	function openCheckOut(){
		var item = $('#data-datagrid').datagrid('getSelected');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要退房的入住信息！','info');	
			return;
		}
		$.messager.confirm('信息提示','确定要退房？', function(result){
			if(result){
				$.ajax({
					url:'checkout',
					dataType:'json',
					type:'post',
					data:{id:item.id},
					success:function(data){
						if(data.type == 'success'){
							$.messager.alert('信息提示','退房成功！','info');
							$('#delete-dialog').dialog('close');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}
	
	/**
	* 打开添加窗口
	*/
	function openAdd(){
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "登记入住信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* 搜索按钮监听
	*/
	$("#search-btn").click(function(){
		var status = $("#search-status").combobox('getValue');
		var roomTypeId = $("#search-roomType").combobox('getValue');
		var customerId = $("#search-room").combobox('getValue');
		var option = {name:$("#search-name").val()}
		if(status != -1){
			option.status = status;
		}
		if(roomTypeId != -1){
			option.roomTypeId = roomTypeId;
		}
		if(customerId != -1){
			option.customerId = customerId;
		}
		option.idCard = $("#search-idCard").val();
		option.phoneNum = $("#search-phoneNum").val();
		$('#data-datagrid').datagrid('reload',option);
	});
	
	//时间格式转换
	function add0(m){return m<10?'0'+m:m }
	function format(value){
	//shijianchuo是整数，否则要parseInt转换
		var time = new Date(value);
		var y = time.getFullYear();
		var m = time.getMonth()+1;
		var d = time.getDate();
		var h = time.getHours();
		var mm = time.getMinutes();
		var s = time.getSeconds();
		return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
	}
	
	/**
	 * 根据房间类型获取房间
	 */
	$("#add-roomTypeId").combobox({
		onSelect:function(data){
			$("#add-roomId").combobox('clear');
			$("#add-roomId").combobox('reload', 'load_room_list?roomTypeId='+data.value);
		}
	})
	
	$("#edit-roomTypeId").combobox({
		onSelect:function(data){
			$("#edit-roomId").combobox('clear');
			$("#edit-roomId").combobox('reload', 'load_room_list?roomTypeId='+data.value);
		}
	})
	
	/**
	* Name 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'list',	
		rownumbers:true,
		singleSelect:true,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
		treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'roomId',title:'房间',width:60,formatter:function(value,row,index){
				var roomIdList = $("#search-room").combobox('getData');
				for(var i=0;i<roomIdList.length;i++){
					if(roomIdList[i].value == value)return roomIdList[i].text;
				}
				return value;
			}},
			{ field:'roomTypeId',title:'房间类型',width:100,formatter:function(value,row,index){
				var roomTypeList = $("#search-roomType").combobox('getData');
				for(var i=0;i<roomTypeList.length;i++){
					if(roomTypeList[i].value == value){
						return roomTypeList[i].text;
					}
				}
				return value;
			}},
			{ field:'checkinPrice',title:'入住价格',width:80,sortable:true},
			{ field:'name',title:'入住人',width:80,sortable:true},
			{ field:'idCard',title:'身份证号',width:130,sortable:true},
			{ field:'phoneNum',title:'手机号',width:100,sortable:true},
			{ field:'arrivedDate',title:'入住日期',width:100,sortable:true},
			{ field:'leaveDate',title:'离店日期',width:100,sortable:true},
			{ field:'status',title:'状态',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return '入住中'
					}
					case 1:{
						return '已离店'
					}
				}
				return value;
			}},
			{ field:'remark',title:'订单备注',width:120,sortable:true},
			{ field:'createTime',title:'创建时间',width:150,formatter:function(value,row,index){
				return format(value);
			}}
		]],
	});
	
	//选择预订订单
	$(".select-order-btn").click(function(){
		$('#show-order-dialog').dialog({
			closed: false,
			modal:true,
            title: "选择预订订单信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function(){
                	var selectedOrder = $('#order-datagrid').datagrid('getSelected');
            		if(selectedOrder == null || selectedOrder.length == 0){
            			$.messager.alert('信息提示','请选择预订订单！','info');	
            			return;
            		}
            		$("#add-name").val(selectedOrder.name);
                	$("#add-arrivedDate").datebox("setValue", selectedOrder.arrivedDate);
                	$("#add-leaveDate").datebox("setValue", selectedOrder.leaveDate);
                	$("#add-idCard").val(selectedOrder.idCard);
                	$("#add-phoneNum").val(selectedOrder.phoneNum);
                	$("#add-bookOrderId").val(selectedOrder.id);
                	$("#add-remark").val(selectedOrder.remark);
                	$("#add-status").combobox("setValue", selectedOrder.status);
                	$("#add-roomTypeId").combobox("setValue", selectedOrder.roomTypeId);
                	
                	$('#show-order-dialog').dialog('close'); 
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#show-order-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$('#order-datagrid').datagrid({
            		url:'../book_order/list',	
            		rownumbers:true,
            		singleSelect:true,
            		pageSize:20,           
            		pagination:true,
            		multiSort:true,
            		fitColumns:true,
            		idField:'id',
            		treeField:'name',
            		queryParams:{status:0},
            		fit:true,
            		columns:[[
            			{ field:'chk',checkbox:true},
            			{ field:'roomTypeId',title:'房间类型',width:100,formatter:function(value,row,index){
            				var roomTypeList = $("#search-roomType").combobox('getData');
            				for(var i=0;i<roomTypeList.length;i++){
            					if(roomTypeList[i].value == value){
            						return roomTypeList[i].text;
            					}
            				}
            				return value;
            			}},
            			{ field:'name',title:'预定人',width:80,sortable:true},
            			{ field:'arrivedDate',title:'入住日期',width:100,sortable:true},
            			{ field:'leaveDate',title:'离店日期',width:100,sortable:true},
            			{ field:'status',title:'状态',width:100,formatter:function(value,row,index){
            				switch(value){
            					case 0:{
            						return '预订中'
            					}
            					case 1:{
            						return '已入住'
            					}
            					case 2:{
            						return '已离店'
            					}
            				}
            				return value;
            			}},
            			{ field:'remark',title:'订单备注',width:120,sortable:true},
            			{ field:'bookTime',title:'预订时间',width:150,formatter:function(value,row,index){
            				return format(value);
            			}}
            		]],
            	});
            }
        });
	})
</script>