$(function() {
	/**
	 * 初始化Select2插件
	 */
	var category = $('#categoryId').select2();
	var tag = $('#tagId').select2();

	/**
	 * 初始化SweetAlert2插件
	 */
	const Toast = Swal.mixin({
		toast: true,
		position: 'top-end',
		showConfirmButton: true,
		timer: 3000
	});

	$('#categoryId').select2({
		// 设置提示语placeholder
		placeholder: "选择分类(必选)...."
	});

	$('#tagId').select2({
		// 设置提示语placeholder
		placeholder: "选择标签...."
	});

	//初始化editormd
	var blogMDEditor;
	blogMDEditor = editormd("md-editor", {
		width: "100%",
		height: 650,
		syncScrolling: "single",
		path: "plugins/editor.md/lib/",
		onload: function() {
			var keyMap = {
				"Ctrl-S": function(cm) {
					$('#isPublish').val(false);
					if (validateBlogForm()) {
						$.ajax({
							url: '',
							type: 'POST',
							data: $('#blogForm').serialize(),
							dataType: 'json',
							success: function(data) {

							},
							error: function(error) {

							}
						});
					}
				},
			};
			this.addKeyMap(keyMap);
		}
	});

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @returns {boolean}
	 */
	function isNull(str) {
		return str === null || str === undefined || str.trim() === '';
	}


	/**
	 * 校验博客表单，标题、分类、内容不能为空
	 * @returns {boolean}
	 */
	function validateBlogForm() {
		var blogTitle = $('#title').val();
		var blogCategoryId = $('#categoryId').val();
		var tagId = $('#tagId').val();
		var blogContent = blogMDEditor.getMarkdown();

		if (isNull(blogTitle)) {
			Toast.fire({
				icon: 'error',
				title: '博客标题不能为空！'
			});
			return false;
		}

		if (isNull(blogCategoryId)) {
			Toast.fire({
				icon: 'error',
				title: '请选择博客分类！'
			});
			return false;
		}


		if (isNull(blogContent)) {
			Toast.fire({
				icon: 'error',
				title: '博客内容不能为空！'
			});
			return false;
		}

		return true;
	}

	/**
	 * 发布按钮事件
	 */
	$('#publishBtn').on('click', function() {
		if (validateBlogForm()) {
			$('#isPublish').val(true);
			$('#blogForm').submit();
		}
		return false;
	});

	/**
	 * 保存按钮事件
	 */
	$('#saveBtn').on('click', function() {
		if (validateBlogForm()) {
			$('#isPublish').val(false);
			$('#blogForm').submit();
		}
		return false;
	});
	
	
})
