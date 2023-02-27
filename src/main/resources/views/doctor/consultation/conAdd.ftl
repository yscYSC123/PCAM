<!DOCTYPE html>
<html>
<head>
    <title>咨询申请</title>
    <#include "../../common.ftl">
</head>
<body class="childrenBody" style="height: 400px">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
        <legend>咨询申请</legend>
    </fieldset>
    <table id="userList" class="layui-table"  lay-filter="users"></table>
    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" lay-event="toMessage">同意</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="toBooking">拒绝</a>
    </script>
    <script type="text/javascript" src="${ctx}/js/doctor/consultation/conAdd.js"></script>
</body>
</html>