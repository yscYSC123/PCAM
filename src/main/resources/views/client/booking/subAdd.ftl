<!DOCTYPE html>
<html>
<head>
    <title>可预约的咨询师</title>
    <#include "*/common.ftl">
</head>
<body class="childrenBody" style="height: 400px">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
        <legend>可预约的咨询师</legend>
    </fieldset>
    <table id="userList" class="layui-table"  lay-filter="users"></table>
    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" lay-event="toMessage">发送信息</a>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="toBooking">预约申请</a>
    </script>
    <script type="text/html" id="imgtmp">
        <img src="images/{{d.img}}" style="width: 50px" height="50px"/>
    </script>
    <script type="text/javascript" src="${ctx}/js/client/booking/subAdd.js"></script>
</body>
</html>