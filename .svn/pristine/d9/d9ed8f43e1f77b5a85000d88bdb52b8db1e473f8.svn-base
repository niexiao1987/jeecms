/**
 * 用户添加部门所用js
 */

initDepartment = function initDepartment() {
	// 部门对话框
	$("#departmentDialog").dialog(
			{
				autoOpen : false,
				modal : true,
				width : 380,
				height : 400,
				position : [ "center", 20 ],
				buttons : {
					"OK" : function() {
						// 给文本框赋值
						var departmentId = $("#departmentDialog input:checked")
								.val();
						var departmentName = $(
								"#departmentDialog input:checked+span").html();
						$("#inputDepartmentId").val(departmentId);
						$("#inputDepartmentName").val(departmentName);
						$(this).dialog("close");
					}
				}
			});

	// 给部门选择按钮注册事件
	$('#departmentSelect').click(function() {
		$('#departmentDialog').dialog('open');
		return false;
	});

	// 生成树
	$("#browser").treeview({
		url : "v_tree.do"
	});
}

function validate() {

	var selfAdmin = $(":radio[name=selfAdmin]:checked").val();
	var superAdmin = $(":radio[name=superAdmin]:checked").val();

	$(":radio[name=selfAdmin]").attr("disabled", false);
	$(":radio[name=superAdmin]").attr("disabled", false);

	if (selfAdmin == "true") {
		$(":radio[name=superAdmin]").attr("disabled", true);
	}

	if (superAdmin == "true") {
		$(":radio[name=selfAdmin]").attr("disabled", true);
	}
}
