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
            <label>客户名称：</label><input id="search-name" class="wu-text" style="width:100px">
            <label>真实姓名：</label><input id="search-realName" class="wu-text" style="width:100px">
            <label>身份证号：</label><input id="search-idCard" class="wu-text" style="width:100px">
            <label>手机号码：</label><input id="search-phoneNum" class="wu-text" style="width:100px">
            <label>状态：</label>
            <select id="search-status" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<option value="0">可用</option>
            	<option value="1">冻结</option>
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
                <td align="right">名称:</td>
                <td><input type="text" id="add-name" name="name" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写用户名称'" /></td>
            </tr>
            <tr>
                <td align="right">密码:</td>
                <td><input type="password" id="add-password" name="password" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写用户密码'" /></td>
            </tr>
            <tr>
                <td align="right">真实姓名:</td>
                <td><input type="text" id="add-realName" name="realName" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">身份证号:</td>
                <td><input type="text" id="add-idCard" name="idCard" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">手机号码:</td>
                <td><input type="text" id="add-phoneNum" name="phoneNum" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">联系地址:</td>
                <td><input type="text" id="add-address" name="address" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">房间状态:</td>
                <td>
	                <select id="add-status" class="easyui-combobox" panelHeight="auto" style="width:120px">
		            	<option value="0">可用</option>
		            	<option value="1">冻结</option>
            		</select>
                </td>
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
                <td align="right">名称:</td>
                <td><input type="text" id="edit-name" name="name" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写用户名称'" /></td>
            </tr>
            <tr>
                <td align="right">密码:</td>
                <td><input type="password" id="edit-password" name="password" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写用户密码'" /></td>
            </tr>
            <tr>
                <td align="right">真实姓名:</td>
                <td><input type="text" id="edit-realName" name="realName" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">身份证号:</td>
                <td><input type="text" id="edit-idCard" name="idCard" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">手机号码:</td>
                <td><input type="text" id="edit-phoneNum" name="phoneNum" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">联系地址:</td>
                <td><input type="text" id="edit-address" name="address" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">房间状态:</td>
                <td>
	                <select id="edit-status" class="easyui-combobox" panelHeight="auto" style="width:120px">
		            	<option value="0">可用</option>
		            	<option value="1">冻结</option>
            		</select>
                </td>
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
            	$("#edit-password").val(item.password);
            	$("#edit-realName").val(item.realName);
            	$("#edit-idCard").val(item.idCard);
            	$("#edit-phoneNum").val(item.phoneNum);
            	$("#edit-address").val(item.address);
            	$("#edit-status").combobox("setValue", item.status);
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
		var option = {name:$("#search-name").val()}
		if(status != -1){
			option.status = status;
		}
		option.realName = $("#search-realName").val();
		option.idCard = $("#search-idCard").val();
		option.phoneNum = $("#search-phoneNum").val();
		$('#data-datagrid').datagrid('reload',option);
	});
	
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
			{ field:'name',title:'名称',width:100,sortable:true},
			{ field:'password',title:'密码',width:100,sortable:true},
			{ field:'realName',title:'真实姓名',width:100,sortable:true},
			{ field:'idCard',title:'身份证号',width:100,sortable:true},
			{ field:'phoneNum',title:'手机号',width:100,sortable:true},
			{ field:'address',title:'地址',width:100,sortable:true},
			{ field:'status',title:'状态',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return '可用'
					}
					case 1:{
						return '冻结'
					}
				}
				return value;
			}}
		]],
	});
</script>