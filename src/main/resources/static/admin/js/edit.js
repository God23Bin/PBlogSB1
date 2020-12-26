$(function () {
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
    var blogEditor;
    $(function () {
        blogEditor = editormd("md-editor", { //<div class="form-group" id="md-editor">的id
            width: "100%",
            height: 640,
            syncScrolling: "single",
            path: "/admin/plugins/editor.md/lib/",
            toolbarModes: 'full',
            codeFold : true,				// 开启代码折叠
            //syncScrolling : false,
            saveHTMLToTextarea : true,    	// 保存 HTML 到 Textarea
            // searchReplace : true,
            //watch : false,                // 关闭实时预览
            // htmlDecode : "style,script,iframe|on*",  	// 开启 HTML 标签解析，为了安全性，默认不开启
            //toolbar  : false,             // 关闭工具栏
            //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
            // emoji : true,					// 表情
            // taskList : true,				// 代办，任务列表
            // tocm: true,         			// 使用目录生成
            // tex : true,                   	// 开启科学公式TeX语言支持，默认关闭
            // flowChart : true,             	// 开启流程图支持，默认关闭
            // sequenceDiagram : true,			// 开启顺序图
            imageUpload: true,				// 开启图片上传
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"], //图片上传格式
            imageUploadURL: "/admin/blog/md/upload_img",				//图片上传的后端路径
            onload: function (obj) { 		// 加载完成事件处理，即加载editor.md时执行该方法
                initPasteDragImg(this);		// 初始化粘贴图片功能
                blogEditor.config("tocDropdown", true);
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
        var blogContent = blogEditor.getMarkdown();

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
            // blogId 有两种情况
            var blogId = $('#blogId').val();
            var blogTitle = $('#title').val();
            var blogCategoryId = $('#categoryId').val();
            var blogTagIds = $('#tagId').val();
            var blogContent = blogEditor.getMarkdown();
            var url = "/admin/blog/add";
            var swlMessage = '发布成功';
            var data = {
                "blogId": blogId,
                "blogTitle": blogTitle,
                "blogCategoryId": blogCategoryId,
                "blogTagIds": blogTagIds,
                "blogContent": blogContent,

            };
            debugger
            // if (blogId > 0) {
            //     url = '/admin/blogs/update';
            //     swlMessage = '修改成功';
            //     data = {
            //         "blogId": blogId,
            //         "blogTitle": blogTitle,
            //         "blogSubUrl": blogSubUrl,
            //         "blogCategoryId": blogCategoryId,
            //         "blogTags": blogTags,
            //         "blogContent": blogContent,
            //         "blogCoverImage": blogCoverImage,
            //         "blogStatus": blogStatus,
            //         "enableComment": enableComment
            //     };
            // }
            console.log(data);
            $.ajax({
                type: 'POST',   // 方法类型
                url: url,       // 后端保存请求路径
                data: data,     // 传入后端的数据
                success: function (result) {
                    console.log(result);
                    debugger
                    if (result.resultCode == 200) {
                        // $('#articleModal').modal('hide');
                        // 现在更新需要.fire()方法来使用
                        swal.fire({
                            title: swlMessage,
                            type: 'success',
                            showCancelButton: false,
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '返回博客列表',
                            confirmButtonClass: 'btn btn-success',
                            buttonsStyling: false
                        }).then(function () {
                            window.location.href = "/admin/my_articles";
                        })
                    }
                    else {
                        // $('#articleModal').modal('hide');
                        swal.fire(result.message, {
                            icon: "error",
                        });
                    }
                    ;
                },
                error: function () {
                    swal.fire("操作失败", {
                        icon: "error",
                    });
                }
            });
            // $('#blogForm').submit();
        }
        // return false;
    });

    /**
     * 保存按钮事件
     */
    $('#saveBtn').on('click', function() {
        if (validateBlogForm()) {
            $('#isPublish').val(false);
            // blogId 两种情况
            var blogId = $('#blogId').val();
            var blogTitle = $('#title').val();
            var blogCategoryId = $('#categoryId').val();
            var blogTagIds = $('#tagId').val();
            var blogContent = blogEditor.getMarkdown();
            var url = "/admin/blog/save";
            var swlMessage = '保存成功';
            var data = {
                "blogId": blogId,
                "blogTitle": blogTitle,
                "blogCategoryId": blogCategoryId,
                "blogTagIds": blogTagIds,
                "blogContent": blogContent,

            };
            debugger
            // if (blogId > 0) {
            //     url = '/admin/blogs/update';
            //     swlMessage = '修改成功';
            //     data = {
            //         "blogId": blogId,
            //         "blogTitle": blogTitle,
            //         "blogSubUrl": blogSubUrl,
            //         "blogCategoryId": blogCategoryId,
            //         "blogTags": blogTags,
            //         "blogContent": blogContent,
            //         "blogCoverImage": blogCoverImage,
            //         "blogStatus": blogStatus,
            //         "enableComment": enableComment
            //     };
            // }
            console.log(data);
            $.ajax({
                type: 'POST',   // 方法类型
                url: url,       // 后端保存请求路径
                data: data,     // 传入后端的数据
                success: function (result) {
                    console.log(result);
                    debugger
                    if (result.resultCode == 200) {
                        // $('#articleModal').modal('hide');
                        // 现在更新需要.fire()方法来使用
                        swal.fire({
                            title: swlMessage,
                            type: 'success',
                            showCancelButton: false,
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: '返回博客列表',
                            confirmButtonClass: 'btn btn-success',
                            buttonsStyling: false
                        }).then(function () {
                            window.location.href = "/admin/my_articles";
                        })

                        // swal.fire({
                        //     title: '<i>HTML</i> <u>示例</u>',
                        //     type: 'info',
                        //     html:
                        //         '你可以使用<b>粗体文本</b>, ' +
                        //         '<a href="//github.com">链接</a> ' +
                        //         '和其它的HTML标签',
                        //     showCloseButton: true,
                        //     showCancelButton: true,
                        //     confirmButtonText:
                        //         '<i class="fa fa-thumbs-up"></i> 太棒了！',
                        //     cancelButtonText:
                        //         '<i class="fa fa-thumbs-down"></i>'
                        // })
                    }
                    else {
                        // $('#articleModal').modal('hide');
                        swal.fire(result.message, {
                            icon: "error",
                        });
                    }
                    ;
                },
                error: function () {
                    swal.fire("操作失败", {
                        icon: "error",
                    });
                }
            });
            // $('#blogForm').submit();
        }
        // return false;
    });


})