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
            <label>预订姓名：</label><input id="search-name" class="wu-text" style="width:100px">
            <label>身份证号：</label><input id="search-idCard" class="wu-text" style="width:100px">
            <label>手机号码：</label><input id="search-phoneNum" class="wu-text" style="width:100px">
            <label>客户：</label>
            <select id="search-customer" class="easyui-combobox" panelHeight="auto" style="width:100px">
            	<option value="-1">全部</option>
            	<c:forEach items="${customerList }" var="customer">
            		<option value="${customer.id }">${customer.name }</option>
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
            	<option value="0">预订中</option>
            	<option value="1">已入住</option>
            	<option value="2">已离店</option>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>

<!-- Begin of easyui-dialog -->
<!-- 添加弹窗 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
        	<tr>
                <td align="right">客户:</td>
                <td>
                	<select id="add-customerId" name="customerId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true,missingMessage:'请选择客户'">
		            	<c:forEach items="${customerList }" var="customer">
            				<option value="${customer.id }" real-name="${customer.realName }" id-card="${customer.idCard }" mobile="${customer.phoneNum }">${customer.name }</option>
            			</c:forEach>
            		</select>
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
            </tr>
            <tr>
                <td align="right">入住人姓名:</td>
                <td><input type="text" id="add-name" name="name" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写入住姓名'" /></td>
            </tr>
            <tr>
                <td align="right">身份证号:</td>
                <td><input type="text" id="add-idCard" name="idCard" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请输入身份证号'" /></td>
            </tr>
            <tr>
                <td align="right">手机号码:</td>
                <td><input type="text" id="add-phoneNum" name="phoneNum" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写手机号'" /></td>
            </tr>
            <tr>
                <td align="right">入住日期:</td>
                <td><input type="text" id="add-arrivedDate" name="arrivedDate" class="wu-text easyui-datebox easyui-validatebox"/></td>
            </tr>
            <tr>
                <td align="right">离店日期:</td>
                <td><input type="text" id="add-leaveDate" name="leaveDate" class="wu-text easyui-datebox easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">订单状态:</td>
                <td>
	                <select id="add-status" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true,missingMessage:'请选择状态'">
		            	<option value="0">预订中</option>
		            	<option value="1">已入住</option>
		            	<option value="2">已离店</option>
            		</select>
                </td>
            </tr>
            <tr>
                <td valign="top" align="right">订单备注:</td>
                <td><textarea id="add-remark" name="remark" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<!-- 修改弹窗 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
		<input type="hidden" name="id" id="edit-id" />
        <table>
            <tr>
                <td align="right">客户:</td>
                <td>
                	<select id="edit-customerId" name="customerId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true,missingMessage:'请选择客户'">
		            	<c:forEach items="${customerList }" var="customer">
            				<option value="${customer.id }" real-name="${customer.realName }" id-card="${customer.idCard }" mobile="${customer.phoneNum }">${customer.name }</option>
            			</c:forEach>
            		</select>
                </td>
            </tr>
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
		            	<option value="0">预订中</option>
		            	<option value="1">已入住</option>
		            	<option value="2">已离店</option>
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
	* Name 删除记录
	*/
	function remove(){
		var item = $('#data-datagrid').datagrid('getSelected');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要删除的数据！','info');	
			return;
		}
		$.messager.confirm('信息提示','确定要删除选择记录？', function(result){
			if(result){
				var ids = '';
				for(var i=0;i<item.length;i++){
					ids += item[i].id + ',';
				}
				$.ajax({
					url:'delete',
					dataType:'json',
					type:'post',
					data:{id:item.id},
					success:function(data){
						if(data.type == 'success'){
							$.messager.alert('信息提示','删除成功！','info');
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
            title: "编辑客户信息",
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
            	$("#edit-remark").val(item.remark);
            	$("#edit-status").combobox("setValue", item.status);
            	$("#edit-customerId").combobox("setValue", item.customerId);
            	$("#edit-roomTypeId").combobox("setValue", item.roomTypeId);
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
            title: "添加客户信息",
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
		var customerId = $("#search-customer").combobox('getValue');
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
	
	$("#add-customerId").combobox({
		onSelect:function(data){
			var option = $("#add-customerId option[value='" + data.value + "']");
			$("#add-name").val(option.attr('real-name'));
			$("#add-idCard").val(option.attr('id-card'));
			$("#add-phoneNum").val(option.attr('mobile'));
		}
	})
	$("#edit-customerId").combobox({
		onSelect:function(data){
			var option = $("#edit-customerId option[value='" + data.value + "']");
			$("#edit-name").val(option.attr('real-name'));
			$("#edit-idCard").val(option.attr('id-card'));
			$("#edit-phoneNum").val(option.attr('mobile'));
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
			{ field:'customerId',title:'客户',width:100,formatter:function(value,row,index){
				var customerList = $("#search-customer").combobox('getData');
				for(var i=0;i<customerList.length;i++){
					if(customerList[i].value == value)return customerList[i].text;
				}
				return value;
			}},
			{ field:'roomTypeId',title:'房间类型',width:130,formatter:function(value,row,index){
				var roomTypeList = $("#search-roomType").combobox('getData');
				for(var i=0;i<roomTypeList.length;i++){
					if(roomTypeList[i].value == value){
						return roomTypeList[i].text + '(￥:' + $("#search-roomType option[value='" + value + "']").attr('price') + ')';
					}
				}
				return value;
			}},
			{ field:'name',title:'预定人',width:80,sortable:true},
			{ field:'idCard',title:'身份证号',width:130,sortable:true},
			{ field:'phoneNum',title:'手机号',width:100,sortable:true},
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
</script>