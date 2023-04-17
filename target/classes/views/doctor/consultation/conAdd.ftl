<!DOCTYPE html>
<html>
<head>
    <title>咨询申请</title>
    <#include "*/common.ftl">
</head>
<body class="childrenBody" style="height: 400px">
    <div class="layui-form">
        <blockquote class="layui-elem-quote quoteBox">
            <form class="layui-form">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" name="clientName" class="layui-input searchVal" placeholder="来访者姓名" />
                    </div>
                    <a class="layui-btn search_btn" data-type="reload">
                        <i class="layui-icon">&#xe615;</i>
                        搜索
                    </a>
                </div>
            </form>
        </blockquote>
        <table id="userList" class="layui-table"  lay-filter="users"></table>
    </div>
    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" lay-event="agree">同意</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="refuse">拒绝</a>
    </script>
    <script type="text/javascript" src="${ctx}/js/doctor/consultation/conAdd.js"></script>
</body>
</html>