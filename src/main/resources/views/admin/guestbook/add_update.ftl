<!DOCTYPE html>
<html>
    <head>
        <#include "../../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <!-- 设置id的隐藏域 -->
            <input type="hidden" name="id" value="${(guestbook.id)!}">
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">留言内容</label>
                <div class="layui-input-block">
                    <textarea lay-verify="required" name="context" id="context"  value="${(guestbook.context)!}" placeholder="请输入内容" class="layui-textarea">${(guestbook.context)!}</textarea>
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
    <script type="text/javascript" src="${ctx}/js/admin/guestbook/add_update.js"></script>
    </body>
</html>