/**
 * 初始化SweetAlert2插件
 */
const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: true,
    timer: 3000
});

//加载数据，这个函数作用：
// 1、页面初始化渲染，
// 2、点击分页时调用。
// ------------------------------------分类-------------------------------------------
function loadSortData(sortPageNum, sortPageSize) {
    var url = "/getPageInfo/SortPageInfo";
    var data = {
        'sortPageNum' : sortPageNum,
        'sortPageSize' : sortPageSize
    }
    $.ajax({
        // url: "{:url('/getPageInfo/SortPageInfo')}?pageNum=" + pageNum + "&pageSize=" + pageSize,
        url: url,
        type: "GET",
        data: data,
        dataType: 'json',
        success: function (res) {
            var success = res.resultCode;
            console.log(success);
            var allSort = res.data.allSort
            var sortPageInfo = res.data.sortPageInfo;
            debugger;
            //paginateFactory函数是渲染页面的核心，稍后会做介绍。
            showAllSort(allSort);
            showSortPageInfo(sortPageInfo);
        }, error: function (res) {
            console.log(res);
        }
    })
}

function showAllSort(allSort) {
    //可以不加id不写loadXMLDoc()直接在这写一个empty()清空上一次的数据代码更可观
    $("#ok").empty();
    var test=0;
    $.each(allSort, function(index, item) {
        var sort_id = $("<td></td>").attr("id", "sort_id"+test+"").append(item.id);
        var sort_name = $("<td></td>").attr("id", "sort_name"+test+"").append(item.sortName);
        // var update_sort_url = $("<a></a>").attr("href", "/admin/update/sort?sortId=" + item.id).attr("style", "color: white; text-decoration: none").append("修改");
        // var update_sort_url = $("<a></a>").attr("href", "javascript:;").attr("style", "color: white; text-decoration: none").append("修改");
        var udapte_sort_hiddeninput = $("<input />").attr("type", "hidden").attr("id", item.id).attr("value", item.id);
        // var delete_sort_url = $("<a></a>").attr("href", "/admin/delete/sort?sortId=" + item.id).attr("style", "color: white; text-decoration: none").append("删除");
        // var delete_sort_url = $("<a></a>").attr("href", "javascript:;").attr("style", "color: white; text-decoration: none").append("删除");
        var delete_sort_hiddeninput = $("<input />").attr("type", "hidden").attr("id", item.id).attr("value", item.id);
        var btn_update = $("<button></button>").attr("id", "btn_update"+test+"")
            .attr("class", "btn btn-success btn-sm sort-update-btn")
            .attr("value", item.id)
            .attr("onclick", "modifySort(" + item.id +")")
            // .append(update_sort_url)
            .append(udapte_sort_hiddeninput)
            .append("修改");
        var btn_delete = $("<button></button>").attr("id", "btn_delete"+test+"")
            .attr("class", "btn btn-danger btn-sm sort-delete-btn")
            .attr("value", item.id)
            .attr("onclick", "deleteSort(" + item.id +")")
            // .append(delete_sort_url)
            .append(delete_sort_hiddeninput)
            .append("删除");
        var btn_grouop = $("<div></div>").attr("class", "btn-group").append(btn_update).append(btn_delete);
        var btns = $("<td></td>").attr("id","btns"+test+"").append(btn_grouop);
        var show = $("<tr></tr>").append(sort_id).append(sort_name).append(btns).appendTo("#ok");
        test++;
    });
}

function showSortPageInfo(sortPageInfo){
    // $("#pagehelp").text("当前第" + sortPageInfo.pageNum + "页,总共" + sortPageInfo.pageSize
    //     + "页,总记录数" + sortPageInfo.total);

    $("#okSortPage").empty();
    //首页
    var index_a = $("<a></a>")
        .attr("aria-label", "Previous")
        // .attr("href", "/getPageInfo/SortPageInfo?sortPageNum=1&sortPageSize=3")
        .append("首页");
    var index_li = $("<li></li>").append(index_a).appendTo("#okSortPage");
    index_li.click(function(){
        loadSortData(1,3);
    })
    //上一页
    var pre_span = $("<span></span>").attr("aria-hidden", "true").append("&laquo;");
    var pre_a = $("<a></a>")
        .attr("aria-label", "Previous")
        // .attr("href", "/getPageInfo/SortPageInfo?sortPageNum=" + sortPageInfo.prePage + "&sortPageSize=3")
        .append(pre_span);
    var pre_li = $("<li></li>").append(pre_a).appendTo("#okSortPage");
    if (sortPageInfo.hasPreviousPage == true) {
        pre_li.click(function () {
            loadSortData(sortPageInfo.prePage,3);
        });
    }

// <li th:each="npn : ${labelPageInfo.getNavigatepageNums()}">-->
//     <a href="#" th:href="@{/admin/class_label(sortPageNum=${sortPageInfo.getPageNum()}, sortPageSize=3, labelPageNum=${npn}, labelPageSize=3)}" th:text="${npn}" th:if="${npn != labelPageInfo.getPageNum()}">1</a>-->
//     <span style="font-weight: bold;background: #6faed9;" th:if="${npn == labelPageInfo.getPageNum()}" th:text="${npn}" ></span>-->
// </li>
    //显示当前状态
    $.each(sortPageInfo.navigatepageNums, function (index, item) {
        if (item == sortPageInfo.pageNum) {
            var span = $("<span></span>").attr("style", "font-weight: bold;background: #6faed9;");
            var current_li = $("<li></li>")
                .append(span.append(item))
                .appendTo("#okSortPage");
            current_li.click(function () {
                loadSortData(item,3);
            });
        } else {
            var current_li = $("<li></li>").append($("<a></a>").append(item))
                .appendTo("#okSortPage");
            current_li.click(function () {
                loadSortData(item,3);
            });
        }
    });
    //下一页
    var next_span = $("<span></span>").attr("aria-hidden", "true").append("&raquo;");
    var next_a = $("<a></a>")
        .attr("aria-label", "Previous")
        .append(next_span);
    var next_li = $("<li></li>").append(next_a).appendTo("#okSortPage");
    if (sortPageInfo.hasNextPage == true) {
        next_li.click(function () {
            loadSortData(sortPageInfo.nextPage,3);
        });
    }

    //尾页
    var last = sortPageInfo.pages;
    var last_a = $("<a></a>")
        .attr("aria-label", "Previous")
        // .attr("href", "/getPageInfo/SortPageInfo?sortPageNum=1&sortPageSize=3")
        .append("尾页");
    var last_li = $("<li></li>").append(last_a).appendTo("#okSortPage");
    last_li.click(function () {
        loadSortData(last,3);
    });
}

// ------------------------------------标签-------------------------------------------
function loadLabelData(labelPageNum, labelPageSize) {
    var url = "/getPageInfo/LabelPageInfo";
    var data = {
        'labelPageNum' : labelPageNum,
        'labelPageSize' : labelPageSize
    }
    $.ajax({
        url: url,
        type: "GET",
        data: data,
        dataType: 'json',
        success: function (res) {
            var success = res.resultCode;
            console.log(success);
            var allLabel = res.data.allLabel
            var labelPageInfo = res.data.labelPageInfo;
            debugger;
            //paginateFactory函数是渲染页面的核心，稍后会做介绍。
            showAllLabel(allLabel);
            showLabelPageInfo(labelPageInfo);
        }, error: function (res) {
            console.log(res);
        }
    })
}

function showAllLabel(allLabel) {
    //可以不加id不写loadXMLDoc()直接在这写一个empty()清空上一次的数据代码更可观
    $("#ok2").empty();
    var test=0;
    $.each(allLabel, function(index, item) {
        var label_id = $("<td></td>").attr("id", "label-id"+test+"").append(item.id);
        var label_name = $("<td></td>").attr("id", "label_name"+test+"").append(item.labelName);
        // var update_label_url = $("<a></a>").attr("href", "/admin/update/label?labelId=" + item.id).attr("style", "color: white; text-decoration: none").append("修改");
        // var delete_label_url = $("<a></a>").attr("href", "/admin/delete/label?labelId=" + item.id).attr("style", "color: white; text-decoration: none").append("删除");
        var btn_update = $("<button></button>").attr("id", "btn_update"+test+"")
            .attr("class", "btn btn-success btn-sm")
            .attr("onclick", "modifyLabel(" + item.id +")")
            // .append(update_label_url);
            .append("修改");
        var btn_delete = $("<button></button>").attr("id", "btn_delete"+test+"")
            .attr("class", "btn btn-danger btn-sm")
            .attr("onclick", "deleteLabel(" + item.id +")")
            // .append(delete_label_url);
            .append("删除");
        var btn_grouop = $("<div></div>").attr("class", "btn-group").append(btn_update).append(btn_delete);
        var btns = $("<td></td>").attr("id","btns"+test+"").append(btn_grouop);
        var show = $("<tr></tr>").append(label_id).append(label_name).append(btns).appendTo("#ok2");
        test++;
    });
}

function showLabelPageInfo(labelPageInfo){
    $("#ok2LabelPage").empty();
    //首页
    var index_a = $("<a></a>")
        .attr("aria-label", "Previous")
        .append("首页");
    var index_li = $("<li></li>").append(index_a).appendTo("#ok2LabelPage");
    index_li.click(function(){
        loadLabelData(1,3);
    })
    //上一页
    var pre_span = $("<span></span>").attr("aria-hidden", "true").append("&laquo;");
    var pre_a = $("<a></a>")
        .attr("aria-label", "Previous")
        .append(pre_span);
    var pre_li = $("<li></li>").append(pre_a).appendTo("#ok2LabelPage");
    if (labelPageInfo.hasPreviousPage == true) {
        pre_li.click(function () {
            loadLabelData(labelPageInfo.prePage,3);
        });
    }

    //显示当前状态
    $.each(labelPageInfo.navigatepageNums, function (index, item) {
        if (item == labelPageInfo.pageNum) {
            var span = $("<span></span>").attr("style", "font-weight: bold;background: #6faed9;");
            var current_li = $("<li></li>")
                .append(span.append(item))
                .appendTo("#ok2LabelPage");
            current_li.click(function () {
                loadLabelData(item,3);
            });
        } else {
            var current_li = $("<li></li>").append($("<a></a>").append(item))
                .appendTo("#ok2LabelPage");
            current_li.click(function () {
                loadLabelData(item,3);
            });
        }
    });
    //下一页
    var next_span = $("<span></span>").attr("aria-hidden", "true").append("&raquo;");
    var next_a = $("<a></a>")
        .attr("aria-label", "Previous")
        .append(next_span);
    var next_li = $("<li></li>").append(next_a).appendTo("#ok2LabelPage");
    if (labelPageInfo.hasNextPage == true) {
        next_li.click(function () {
            loadLabelData(labelPageInfo.nextPage,3);
        });
    }

    //尾页
    var last = labelPageInfo.pages;
    var last_a = $("<a></a>")
        .attr("aria-label", "Previous")
        .append("尾页");
    var last_li = $("<li></li>").append(last_a).appendTo("#ok2LabelPage");
    last_li.click(function () {
        loadLabelData(last,3);
    });
}

/*
    * 定义替换的方法
*/
// function loadXMLDoc(sortPageNum) {
//     debugger;
//     var url = "/getPageInfo/SortPageInfo";
//     var data = {
//         'sortPageNum' : sortPageNum,
//         'sortPageSize' : 3
//     }
//     $.ajax({
//         type: "GET",
//         url: "url",
//         data: "data",
//         success: function (res) {
//             var success = res.resultCode;
//             console.log(success);
//             var allSort = res.data.allSort
//             var sortPageInfo = res.data.sortPageInfo;
//             debugger;
//             showAllSort(allSort);
//             showSortPageInfo(sortPageInfo);
//         }
//     });
// }

/**
 * 判断字符串是否为空
 * @param str
 * @returns {boolean}
 */
function isNull(str) {
    return str === null || str === undefined || str.trim() === '';
}

function validateSortInput() {
    var sortName = $("#input-sort-name").val();

    if (isNull(sortName)) {
        Toast.fire({
            icon: 'error',
            title: '拜托，请写上类别名~'
        });
        return false;
    }

    return true;
}

function validateLabelInput() {
    var labelName = $("#input-label-name").val();

    if (isNull(labelName)) {
        Toast.fire({
            icon: 'error',
            title: '拜托，请写上标签名~'
        });
        return false;
    }

    return true;
}

/**
 * Ajax来发起修改分类请求
 * @param sortId
 */
function modifySort(sortId){
    var url = "/update/sort";
    var data = {
        'sortId': sortId,
        'sortName': sortName
    };
    console.log(sortId);
    console.log(url);
}

/**
 * Ajax发起删除类别请求
 * @param sortId
 */
function deleteSort(sortId){
    var url = "/delete/sort";
    var data = {
        'sortId': sortId
    };
    // 获取当前页，方便后面删除成功后立即加载当前页数据
    var currentPage = $("#okSortPage > li > span").text();
    debugger;
    console.log(currentPage);
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        success: function (result) {
            debugger;
            if (result.resultCode == 200) {
                Toast.fire({
                    icon: 'success',
                    title: '成功删除分类'
                });
                loadSortData(currentPage,3);
            }
            else {
                Toast.fire({
                    icon: 'error',
                    title: '删除分类失败！'
                });
            }
        }
    })
    console.log(sortId);
    console.log(url);
}

/**
 * Ajax发起修改标签请求
 * @param labelId
 */
function modifyLabel(labelId){
    var url = "/update/label";

    Swal.fire({
        title: '修改标签',
        input: 'text',
        inputAttributes: {
            autocapitalize: 'off'
        },
        showCancelButton: true,
        confirmButtonText: 'Look up',
        showLoaderOnConfirm: true,
        preConfirm: (labelName) => {
            var data = {
                'labelId': labelId,
                'labelName': labelName
            };
            // fetch()请求，和ajax()请求差不多
            return fetch(url,{
                    method: 'POST',
                    body: JSON.stringify(data),
                    headers: new Headers({
                        'Content-Type': 'application/json'
                    })
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(response.statusText)
                    }
                    console.log(response.json());
                    debugger;
                    return response.json()
                })
                .catch(error => {
                    Swal.showValidationMessage(
                        `Request failed: ${error}`
                    )
                })
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.value) {
            Swal.fire({
                title: `${result.value.login}'s avatar`,
                imageUrl: result.value.avatar_url
            })
        }
    })
    console.log(labelId);
    console.log(url);
}

/**
 * Ajax发起删除标签请求
 * @param labelId
 */
function deleteLabel(labelId){
    var url = "/delete/label";
    var data = {
        'labelId': labelId
    };
    // 获取当前页，方便后面删除成功后立即加载当前页数据
    var currentPage = $("#ok2LabelPage > li > span").text();
    debugger;
    console.log(currentPage);
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        success: function (result) {
            debugger;
            if (result.resultCode == 200) {
                Toast.fire({
                    icon: 'success',
                    title: '成功删除标签'
                });
                loadLabelData(currentPage,3);
            }
            else {
                Toast.fire({
                    icon: 'error',
                    title: '删除标签失败！'
                });
            }
        }
    })
    console.log(labelId);
    console.log(url);
}

$(function(){
    /**
     * 测试ajax分页数据的获取
     * @type {string}
     */
    var url = "/getPageInfo/SortPageInfo";
    var data = {
        'sortPageNum' : 1,
        'sortPageSize' : 3
    }
    console.log(data);
    $('#getPageInfo').click(function (e) {
        e.preventDefault();
        debugger;
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (result) {
                var allSort = result.data.allSort;
                debugger;
                console.log(allSort);
                console.log(result.resultCode);
                console.log(result.data.allSort[0].id);
                console.log(result.data.allSort[0].sortName);
                console.log(result.data.allSort[1].id);
                console.log(result.data.allSort[1].sortName);
                console.log(result.data.allSort[2].id);
                console.log(result.data.allSort[2].sortName);
                console.log(result.data.sortPageInfo.total);
            }
        })
    });


    /**
     * 点击添加分类的按钮实现Ajax请求
     */
    $("#addSortAjax").click(function (e) {
        e.preventDefault();
        if (validateSortInput()) {
            var sortName = $("#input-sort-name").val();
            var url = "/add/sort";
            var data = {
                'sortName' : sortName,
            };
            // 获取当前页，方便后面添加成功后立即加载当前页数据
            var currentPage = $("#okSortPage > li > span").text();
            $.ajax({
                type: "POST",
                url: url,
                data: data,
                dataType: 'json',
                success: function (result) {
                    if (result.resultCode == 200) {
                        // 现在更新需要.fire()方法来使用
                        // swal.fire({
                        //     title: swlMessage,
                        //     type: 'success',
                        //     showCancelButton: true,
                        // });
                        debugger;
                        Toast.fire({
                            icon: 'success',
                            title: '成功添加分类'
                        });
                        loadSortData(currentPage, 3);
                    }
                    else {
                        Toast.fire({
                            icon: 'error',
                            title: '添加分类失败！'
                        });
                    }
                }
            });
        }
    });

    /**
     * 点击添加标签的按钮实现Ajax请求
     */
    $("#addLabelAjax").click(function (e) {
        e.preventDefault();
        if (validateLabelInput()) {
            var labelName = $("#input-label-name").val();
            var url = "/add/label";
            var data = {
                'labelName' : labelName,
            };
            // 获取当前页，方便后面添加成功后立即加载当前页数据
            var currentPage = $("#ok2LabelPage > li > span").text();
            $.ajax({
                type: "POST",
                url: url,
                data: data,
                dataType: 'json',
                success: function (result) {
                    if (result.resultCode == 200) {
                        // 现在更新需要.fire()方法来使用
                        // swal.fire({
                        //     title: swlMessage,
                        //     type: 'success',
                        //     showCancelButton: true,
                        // });
                        debugger;
                        Toast.fire({
                            icon: 'success',
                            title: '成功添加标签'
                        });
                        loadLabelData(currentPage, 3);
                    }
                    else {
                        Toast.fire({
                            icon: 'error',
                            title: '添加标签失败！'
                        });
                    }
                }
            });
        }
    });


    // 页面加载时读取分页数据
    loadSortData(1,3);
    loadLabelData(1,3);
});