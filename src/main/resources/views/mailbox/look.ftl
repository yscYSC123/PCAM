<!DOCTYPE html>
<html>
    <head>
        <#include "*/common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <!-- 设置id的隐藏域 -->
            <input type="hidden" name="receiver" value="${(message.sender)!}">
            <input type="hidden" name="senderName" value="${(message.receiverName)!}">
            <input type="hidden" name="sender" value="${(message.receiver)!}">
            <input type="hidden" name="receiverId" value="${(message.senderId)!}">
            <input type="hidden" name="senderId" value="${(message.receiverId)!}">
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">发送者</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="receiverName" id="receiverName"  value="${(message.senderName)!}">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-block">
                    <textarea lay-verify="required" name="context" id="context" class="layui-textarea">${(message.context)!}</textarea>
                </div>
            </div>
        </form>
    <script>
        layui.use(['form', 'layer'], function () {
            var form = layui.form
            layer = parent.layer === undefined ? layui.layer : top.layer,
                $ = layui.jquery;
            //关闭弹出层
            $("#closeBtn").click(function () {
                //当你在iframe页面关闭自身时
                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //再执行关闭
            });
        });
    </script>
    </body>
</html>