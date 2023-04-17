<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
    <form class="layui-form" style="width:80%;" lay-filter="example">
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">咨询师</label>
            <div class="layui-input-block">
                <input readonly="true" type="text" class="layui-input" name="doctorName">
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">咨询时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="test-limit2" placeholder="yyyy-MM-dd" name="expectTime" value="${(booking.expectTime)!}" placeholder="请输入咨询时间">
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">咨询地点</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-verify="required" name="expectPlace" value="${(booking.expectPlace)!}" placeholder="请输入期望咨询地点">
            </div>
        </div>
        <div class="layui-form-item layui-form-text layui-row layui-col-xs12">
            <label class="layui-form-label">情况描述</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" class="layui-textarea" lay-verify="required" name="clientDescription" value="${(booking.clientDescription)!}"></textarea>
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
    <script type="text/javascript" src="${ctx}/js/client/booking/toBooking.js"></script>
    </body>
</html>