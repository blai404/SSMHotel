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
            <label>房间类型名称：</label><input id="search-name" class="wu-text" style="width:100px">
            <label>状态：</label>
            <select id="search-status" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<option value="0">房型已满</option>
            	<option value="1">可预订可入住</option>
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
                <td width="60" align="right">图片预览:</td>
                <td>
					<img id="preview-photo" style="float:left;" src="/HotelManagerSystem/resources/admin/easyui/images/userphoto.jpg" width="100px">
					<a style="float:left;margin-top:40px;" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadPhoto()" plain="true">上传图片</a>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">房间图片:</td>
                <td>
                	<input type="text" id="add-photo" name="photo" value="/HotelManagerSystem/resources/admin/easyui/images/userphoto.jpg" readonly="readonly" class="wu-text " />
                </td>
            </tr>
        	<tr>
                <td align="right">房型名称:</td>
                <td><input type="text" id="add-name" name="name" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写房型名称'" /></td>
            </tr>
            <tr>
                <td align="right">房间价格:</td>
                <td><input type="text" id="add-price" name="price" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写房间价格'" /></td>
            </tr>
            <tr>
                <td align="right">可住人数:</td>
                <td><input type="text" id="add-liveNum" name="liveNum" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写可住人数'" /></td>
            </tr>
            <tr>
                <td align="right">床位数:</td>
                <td><input type="text" id="add-bedNum" name="bedNum" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写床位数'" /></td>
            </tr>
            <tr>
                <td align="right">房间总数:</td>
                <td><input type="text" id="add-roomNum" name="roomNum" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写房间总数'" /></td>
            </tr>
            <tr>
                <td align="right">房间状态:</td>
                <td>
	                <select id="add-status" name="status" class="easyui-combobox" panelHeight="auto" style="width:268px">
		            	<option value="0">房型已满</option>
		            	<option value="1">可预订可入住</option>
            		</select>
                </td>
            </tr>
            <tr>
                <td valign="top" align="right">房间类型备注:</td>
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
                <td width="60" align="right">图片预览:</td>
                <td valign="middle">
					<img id="edit-preview-photo" style="float:left;" src="/HotelManagerSystem/resources/admin/easyui/images/userphoto.jpg" width="100px">
					<a style="float:left;margin-top:40px;" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadPhoto()" plain="true">上传图片</a>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">房间图片:</td>
                <td>
                	<input type="text" id="edit-photo" name="photo" value="/HotelManagerSystem/resources/admin/easyui/images/userphoto.jpg" readonly="readonly" class="wu-text " />
                </td>
            </tr>
            <tr>
                <td align="right">房型名称:</td>
                <td><input type="text" id="edit-name" name="name" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写房型名称'" /></td>
            </tr>
            <tr>
                <td align="right">房间价格:</td>
                <td><input type="text" id="edit-price" name="price" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写房间价格'" /></td>
            </tr>
            <tr>
                <td align="right">可住人数:</td>
                <td><input type="text" id="edit-liveNum" name="liveNum" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写可住人数'" /></td>
            </tr>
            <tr>
                <td align="right">床位数:</td>
                <td><input type="text" id="edit-bedNum" name="bedNum" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写床位数'" /></td>
            </tr>
            <tr>
                <td align="right">房间总数:</td>
                <td><input type="text" id="edit-roomNum" name="roomNum" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写房间总数'" /></td>
            </tr>
            <tr>
                <td align="right">房间状态:</td>
                <td>
	                <select id="edit-status" name="status" class="easyui-combobox" panelHeight="auto" style="width:268px">
		            	<option value="0">房型已满</option>
		            	<option value="1">可预订可入住</option>
            		</select>
                </td>
            </tr>
            <tr>
                <td valign="top" align="right">房间类型备注:</td>
                <td><textarea id="edit-remark" name="remark" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<!-- 上传图片进度条弹窗 -->
<div id="process-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-upload',title:'正在上传图片'" style="width:450px; padding:10px;">
	<div id="p" class="easyui-progressbar" style="width:400px;" data-options="text:'正在上传中...'">
	</div>
</div>
<input type="file" id="photo-file" style="display:none;" onchange="upload()">
<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>

<!-- End of easyui-dialog -->
<script type="text/javascript">

	/**
	* 上传头像图片
	*/
	function start(){
		var value = $('#p').progressbar('getValue');
		if (value < 100){
			value += Math.floor(Math.random() * 10);
			$('#p').progressbar('setValue', value);
		}else{
			$('#p').progressbar('setValue',0)
		}
	};
	function upload(){
		if($("#photo-file").val() == '')return;
		var formData = new FormData();
		formData.append('photo', document.getElementById("photo-file").files[0]);
		$("#process-dialog").dialog('open');
		var interval = setInterval(start,200);
		$.ajax({
			url:'../user/upload_photo',
			type:'post',
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				if(data.type == 'success'){
					$("#preview-photo").attr('src', data.filepath);
					$("#add-photo").val(data.filepath);
					$("#edit-preview-photo").attr('src', data.filepath);
					$("#edit-photo").val(data.filepath);
				}else{
					$.messager.alert("消息提醒",data.msg,"warning");
				}
			},
			error:function(){
				clearInterval(interval);
				$("#process-dialog").dialog('close');
				$.messager.alert("消息提醒","上传失败！","warning");
			}
		});
	}
	
	function uploadPhoto(){
		$("#photo-file").click();
	}

	/**
	* 修改房间类型信息
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
	* 添加房间类型信息
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
					$("#add-remark").val('');
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
            title: "编辑房间类型信息",
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
            	$("#edit-price").val(item.price);
            	$("#edit-liveNum").val(item.liveNum);
            	$("#edit-bedNum").val(item.bedNum);
            	$("#edit-roomNum").val(item.roomNum);
            	$("#edit-status").combobox("setValue", item.status);
            	$("#edit-remark").val(item.remark);
            	$("#edit-preview-photo").attr('src',item.photo);
            	$("#edit-photo").val(item.photo);
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
            title: "添加房间类型信息",
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
			{ field:'price',title:'价格',width:100,sortable:true},
			{ field:'liveNum',title:'可住人数',width:100,sortable:true},
			{ field:'bedNum',title:'床位数',width:100,sortable:true},
			{ field:'roomNum',title:'房间数',width:100,sortable:true},
			{ field:'avilableNum',title:'可用房间数',width:100,sortable:true},
			{ field:'bookNum',title:'已预订数',width:100,sortable:true},
			{ field:'livedNum',title:'已入住数',width:100,sortable:true},
			{ field:'status',title:'房间状态',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return '房型已满'
					}
					case 1:{
						return '可预订入住'
					}
				}
				return value;
			}},
			{ field:'remark',title:'房间类型备注',width:200},
		]],
	});
</script>