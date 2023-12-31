<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示角色信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<%--表格--%>
<table class="layui-hide" id="test" lay-filter="test"></table>
<%--添加模态框开始--%>
<form class="layui-form layui-form-panel" id="add-form" name="add-form" style="display: none;" method="post"
      lay-filter="first1">
    <div class="layui-form-item">
        <label class="layui-form-label">菜单id：</label>
        <div class="layui-input-inline">
            <input type="text" name="roleid" id="roleid" lay-verify="required" required placeholder="请输入角色id"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称：</label>
        <div class="layui-input-inline">
            <input type="text" name="rolename" id="rolename" lay-verify="required" required placeholder="请输入角色名称"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="doSubmit" type="button">确定</button>
        </div>
    </div>
</form>
<%--添加模态框结束--%>
<%--编辑模态框开始--%>
<div id="edit-main" style="display: none;">
    <form class="layui-form" id="edit-form">
        <div class="layui-form-item">
            <label class="layui-form-label">菜单名称</label>
            <div class="layui-input-inline">
                <input type="text" name="rolename" id="rolenamed" lay-verify="required" required placeholder="请输入角色名称"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限分配</label>
            <div class="layui-input-inline">
                <div id="test12" class="demo-tree-more"></div>
            </div>
        </div>
        <!--权限分配-->



    </form>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn btns" lay-submit lay-demo="getChecked" lay-filter="save">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary" id="closeBtn">重置</button>
        </div>
    </div>
</div>
<%--编辑模态框结束--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button lay-event="addMenu" class="layui-btn layui-btn-radius" data-type="add" id="add"><i
                class="layui-icon layui-icon-add-1" style="font-size:20px;"></i>新增角色
        </button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    var roleids;
    layui.use(['table', 'layer', 'form'], function () {
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;
        //温馨提示：默认由前端自动合计当前行数据。从 layui 2.5.6 开始： 若接口直接返回了合计行数据，则优先读取接口合计行数据。
        //详见：https://www.layui.com/doc/modules/table.html#totalRow
        table.render({
            elem: '#test'
            , url: 'role_selectRoleAlls'
            , toolbar: '#toolbarDemo'
            , title: '角色信息表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'roleid', title: '角色id', fixed: 'left', sort: true}
                , {field: 'rolename', title: '角色名'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150}
            ]]
            , page: true
        });
        //工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'addMenu':
                    openAddWindow();
                    break;
            }
            ;
        });
        var indexs;
        //监听行工具事件
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'detail') {
                layer.msg('查看操作');
            } else if (layEvent === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    //obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        url: 'role_deleteRole',
                        type: 'post',
                        data: {roleid: data.roleid},//向服务器端发送要删除的id
                        success: function (res) {
                            if (res.code == 200) {
                                layer.msg("删除成功", {icon: 6});
                                table.reload('test');
                            } else {
                                layer.msg("删除失败", {icon: 2});
                            }
                        },
                        error: function () {
                            console.log("无返回值");
                        }
                    });//向服务端发送删除指令

                });
            } else if (layEvent === 'edit') {
                roleids = obj.data.roleid;
                layui.use(['tree', 'util'], function () {
                    //获取所有菜单信息
                    function getData() {
                        var data = [];
                        $.ajax({
                            url: "${pageContext.request.contextPath}/menu/menu_selects",    //后台数据请求地址
                            type: "post",
                            data: "roleid=" + roleids,
                            async: false,
                            success: function (msg) {
                                data = msg;
                            }
                        });
                        return data;
                    }

                    //设置角色名输入框的值
                    $("#rolenamed").val(obj.data.rolename);//获取当前行数据的name

                    var tree = layui.tree
                        , layer = layui.layer
                        , util = layui.util
                        //拿到数据
                        , data = getData()
                    //基本演示
                    tree.render({
                        elem: '#test12'//树组件容器id
                        , data: data //数据
                        , showCheckbox: true  //是否显示复选框
                        , id: 'demoId1'
                        , isJump: false // // 禁止跳转链接
                        , click: function (obj) {
                            var data = obj.data;  //获取当前点击的节点数据
                            layer.msg('状态：' + obj.state + '<br>节点数据：' + JSON.stringify(data));
                        }
                    });
                    // 按钮事件
                    util.event('lay-demo', {
                        getChecked: function (othis) {
                            var checkedData = tree.getChecked('demoId1'); //获取选中节点的数据 todo
                            var menusjosn = JSON.stringify(checkedData);
                            var rolename = $("#rolenamed").val();
                            $.ajax({
                                type: 'post',
                                url: "role_update",
                                data: {roleid: roleids, rolename: rolename, menusjosn: menusjosn},//发送json字符串的时候mvc要加上@requestBody
                                success: function (msg) {
                                    layer.alert(msg);
                                    table.reload('test');
                                    layer.close(indexs);
                                },
                                error: function () {
                                    console.log("无返回值");
                                }
                            });
                        }
                    });
                });

                indexs = layer.open({
                    type: 1,//弹出层的类型
                    title: '修改角色信息',
                    area: ['800px', '400px'],
                    content: $("#edit-main")//引用的窗口代码
                });
            }
        });
        var url;
        var indexs;

        //添加角色信息
        function openAddWindow() {
            indexs = layer.open({
                type: 1,//弹出层的类型
                title: '添加角色信息',
                area: ['400px', '300px'],
                content: $("#add-form"),//引用的窗口代码
                success: function () {
                    //清空表单数据
                    $("#add-form")[0].reset();
                    url = "role_insertRole";
                }
            });
        }

        //save按钮监听事件
        form.on("submit(doSubmit)", function (data) {
            $.ajax({
                url: url,
                data: JSON.stringify(data.field),
                type: 'post',
                dataType: 'json',
                contentType: "application/json;charset=UTF-8",
                success: function (res) {
                    if (res.code == 200) {
                        layer.alert('添加成功', {icon: 1});
                        table.reload('test');
                        layer.close(indexs);
                    } else {
                        layer.alert('error', {icon: 2});
                    }
                },
                error: function () {
                    console.log("无返回值");
                }
            })
        })
    });

</script>
</body>
</html>

