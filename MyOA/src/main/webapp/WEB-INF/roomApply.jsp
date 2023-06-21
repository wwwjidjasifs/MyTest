<%--
  Created by IntelliJ IDEA.
  User: 2425417001
  Date: 2023/6/16
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>会议室申请</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <!--注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的-->
</head>
<body>
<%--搜索框--%>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px" id="btn">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">会议标题</label>
                            <div class="layui-input-inline">
                                <!--注意此处input标签里的id-->
                                <input class="layui-input" name="keyword" id="demoReload" autocomplete="off">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <!--注意此处button标签里的type属性-->
                            <button type="button" class="layui-btn layui-btn-primary" lay-submit data-type="reload"
                                    lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>
        <!--注意此处table标签里的id-->
        <%--数据表格--%>
        <div style="padding: 16px;">
            <table class="layui-hide" id="test" lay-filter="test"></table>
        </div>
    </div>
</div>
<%--工具栏--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button lay-event="add" class="layui-btn layui-btn-radius" data-type="add" id="add"><i
                class="layui-icon layui-icon-add-1" style="font-size:20px;"></i>会议室申请
        </button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="update">修改</a>
</script>
<%--新增修改模态框--%>
<form class="layui-form layui-form-panel" id="add-form" name="add-form" style="display: none;" method="post"
      lay-filter="first1">

    <div class="layui-form-item" style="display: none;">
        <label class="layui-form-label">申请表ID：</label>
        <div class="layui-input-inline">
            <input type="text" name="comid" id="comid" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">会议室</label>
        <div class="layui-input-inline">
            <select id="selectRoom" lay-filter="mySelect" name="roomid">
                <option value="0" disabled>---请选择---</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">申请人：</label>
        <div class="layui-input-inline">
            <input type="text" name="userid" id="userid" lay-verify="required"
                   autocomplete="off" class="layui-input" value="${user.userid}" style="display: none;">
            <input type="text" name="username" id="username" lay-verify="required"
                   autocomplete="off" class="layui-input" value="${user.username}" disabled="disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">会议标题：</label>
        <div class="layui-input-inline">
            <input type="text" name="title" id="title" lay-verify="required" placeholder="请输入会议标题："
                   lay-verType="tips" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">日期范围</label>
            <div class="layui-inline" id="test-range">
                <div class="layui-input-inline">
                    <input type="text" id="starttime" name="starttime" class="layui-input" placeholder="开始日期">
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline">
                    <input type="text" id="endtime" name="endtime" class="layui-input" placeholder="结束日期">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="save" type="button">确定</button>
        </div>
    </div>
</form>
<%--新增修改模态框结束--%>
<%--编辑模态框结束--%>
<script type="text/html" id="state">
    {{# if(d.state ==0){ }}
    <input type="checkbox" name="switch" lay-skin="switch" value={{d.userid}} lay-filter="switchTest" lay-text="已通过|未审核"
           disabled="disabled">
    {{# } else { }}
    <input type="checkbox" name="switch" lay-skin="switch" value={{d.userid}} lay-filter="switchTest" lay-text="已通过|未审核"
           checked disabled="disabled">
    {{# } }}
</script>
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.use(['table', 'layer', 'form', 'laydate', 'jquery'], function () {
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;
        var laydate = layui.laydate;
        // var $ = layui.jquery;
        //渲染日期组件
        laydate.render({
            elem: '#test-range'
            , type: 'datetime'
            , range: ['#starttime', '#endtime']
            , done: function (value) {
            }
        });
        table.render({
            elem: '#test'
            , id: 'testReload'//此ID用于绑定搜索后表单重载
            , url: 'communicate_selectMine'
            , toolbar: '#toolbarDemo'
            , title: '会议申请数据表'
            , height:'full'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'comid', title: '申请表ID', fixed: 'left', sort: true}
                , {field: 'roomname', title: '会议室'}
                , {field: 'username', title: '申请人'}
                , {field: 'title', title: '会议标题'}
                , {field: 'starttime', title: '起始日期'}
                , {field: 'endtime', title: '结束日期'}
                , {field: 'state', title: '状态', templet: '#state'}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
            ]]
            , page: true
        });
        var indexs;
        var $ = layui.$;
        var userid;
        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            userid = obj.data.userid;
            if (layEvent == "update") {
                if (obj.data.state == 1) {
                    layer.msg("由于您的申请已处理，不可操作");
                } else {
                    openUpdateWindow(data);//开启添加数据的表单
                    select_room();
                }
            }
        });
        //工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    openAddWindow();//开启添加菜单的表单
                    //加载下拉菜单并显示
                    select_room();
                    break;
            }
            ;
        });
        //以下是搜索框进行监测
        var $ = layui.$, active = {
            reload: function () {
                var demoReload = $('#demoReload');
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        keyword: demoReload.val()
                    }
                });
            }
        };
        //清空搜索栏
        $('#btn .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
            $('#demoReload').val('');
        });
        var url;
        var mainindex;

        //添加菜单信息
        function openAddWindow() {
            mainindex = layer.open({
                type: 1,//弹出层的类型
                title: '申请会议信息',
                area: ['600px', '600px'],
                content: $("#add-form"),//引用的窗口代码
                success: function () {
                    //清空表单数据
                    $("#add-form")[0].reset();
                    url = "communicate_insert"

                }
            });
        }

        //打开修改菜单信息
        function openUpdateWindow(data) {
            mainindex = layer.open({
                type: 1,//弹出层的类型
                title: '会议申请信息',
                area: ['600px', '600px'],
                content: $("#add-form"),//引用的窗口代码
                success: function () {
                    form.val("first1", data);
                    url = "communicate_update";
                }
            });
        }

        //save按钮监听事件
        form.on("submit(save)", function (data) {
            $.ajax({
                url: url,
                data: JSON.stringify(data.field),
                type: 'post',
                dataType: 'json',
                contentType: "application/json;charset=UTF-8",
                success: function (res) {
                    console.log(res);
                    if (res.code == 200) {
                        layer.alert('成功', {icon: 1});
                        table.reload("testReload");
                        layer.close(mainindex);
                    } else {
                        layer.alert('失败', {icon: 2});
                    }
                },
                error: function () {
                    console.log("无返回值");
                }
            })
        })
    });
    //查询所有可以的会议室
    function select_room() {
        $("#selectRoom").empty();
        $("#selectRoom").append('<option disabled  value=0>---请选择---</option>');
        $.ajax({
            type: "post",
            url: "room_selectRoom",
            data: "",
            success: function (res) {

                for (var i = 0; i < res.data.length; i++) {
                    var roomid = res.data[i].roomid;
                    var roomname = res.data[i].roomname;
                    $("#selectRoom").append("<option value=" + roomid + ">" + roomname + "</option>");
                }
                //渲染下拉列表框
                layui.form.render('select');
            }
        });
    }


</script>
</body>
</html>
