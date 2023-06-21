<%--
  Created by IntelliJ IDEA.
  User: 2425417001
  Date: 2023/6/16
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>会议室审批</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
  <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js" type="text/javascript"></script>
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

<%--<script type="text/html" id="barDemo">--%>
<%--  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
<%--</script>--%>

<%--编辑模态框结束--%>
<script type="text/html" id="state">
  {{# if(d.state ==0){ }}
  <input type="checkbox" name="switch" lay-skin="switch" value={{d.comid}} lay-filter="switchTest" lay-text="已处理|未处理">
  {{# } else { }}
  <input type="checkbox" name="switch" lay-skin="switch" value={{d.comid}} lay-filter="switchTest" lay-text="已处理|未处理"
         checked>
  {{# } }}
</script>
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>

<script>
  layui.use(['table', 'layer', 'form'], function () {
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    table.render({
      elem: '#test'
      ,id:"testReload"
      , url: 'communicate_selectAll'
      , toolbar: '#toolbarDemo'
      , title: '会议审批表'
      , cols: [[
        {type: 'checkbox', fixed: 'left'}
        , {field: 'comid', title: '申请表ID', fixed: 'left', sort: true}
        , {field: 'roomname', title: '会议室名称'}
        , {field: 'username', title: '申请人'}
        , {field: 'title', title: '会议标题'}
        , {field: 'starttime', title: '起始日期'}
        , {field: 'endtime', title: '结束日期'}
        , {field: 'state', title: '状态', templet: '#state'}
        // , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
      ]]
      , page: true
    });
    var $ = layui.$;
    //监听行工具事件
    table.on('tool(test)', function (obj) {
      var data = obj.data //获得当前行数据
              , layEvent = obj.event; //获得 lay-event 对应的值
      if (layEvent == "update") {
        openUpdateWindow(data);
      }
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
    //监听指定开关
    form.on('switch(switchTest)', function (data) {
      // layer.msg('开关checked：' + (this.checked ? 1 : 0), {
      //   offset: '6px'
      // });
      $.ajax({
        url: 'communicate_updateState',
        type: 'post',
        data: {"comid": data.value, "state": this.checked ? 1 : 0},//向服务器端发送要修改的id
        success: function (res) {
          if (res.code == 200) {
            layer.msg(res.msg, {icon: 6});
            table.reload("testReload");
          } else {
            layer.msg(res.msg, {icon: 2});
          }
        },
        error: function () {
          console.log("无返回值");
        }
      });
    });
  });
</script>
</body>
</html>

