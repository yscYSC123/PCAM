<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <!-- 设置id的隐藏域 -->
            <input type="hidden" name="receiver" value="${(message.receiver)!}">
            <input type="hidden" name="senderName" value="${(message.senderName)!}">
            <input type="hidden" name="sender" value="${(message.sender)!}">
            <input type="hidden" name="receiverId" value="${(message.receiverId)!}">
            <input type="hidden" name="senderId" value="${(message.senderId)!}">
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">接收者</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="receiverName" id="receiverName"  value="${(message.receiverName)!}">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-block">
                    <textarea lay-verify="required" name="context" id="context"  value="${(message.context)!}" placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
            </div>
            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="addOrUpdateSaleChance">确认 </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>
        </form>
    <script type="text/javascript" src="${ctx}/js/mailbox/toSendPage1.js"></script>
    </body>
</html>