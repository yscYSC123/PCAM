<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;" lay-filter="example">
    <!-- 设置用户id的隐藏域 -->
    <input type="hidden" name="archivesId">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">来访者</label>
        <div class="layui-input-block">
            <input readonly="true" type="text" class="layui-input" name="clientName">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" id="test4" placeholder="HH:mm:ss" name="startDatetime" value="${(booking.startDatetime)!}" placeholder="请输入开始时间">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">结束时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" id="test5" placeholder="HH:mm:ss" name="endDatetime" value="${(booking.endDatetime)!}" placeholder="请输入结束时间">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">咨询地点</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="subPlace" value="${(booking.subPlace)!}" placeholder="请输入咨询地点">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="add">确认 </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/doctor/consultation/toAgree.js"></script>
</body>
</html>