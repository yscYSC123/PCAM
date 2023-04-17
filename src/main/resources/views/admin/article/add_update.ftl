<!DOCTYPE html>
<html>
    <head>
        <#include "*/common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <!-- 设置用户id的隐藏域 -->
            <input type="hidden" name="id" value="${(article.id)!}">
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">文章类型</label>
                <div class="layui-input-block">
                    <select name="label"  id="label">
                        <option name="label" value="${(article.label)!}">请选择文章类型</option>
                        <option value="0" id="0" >生活</option>
                        <option value="1" id="1">爱情</option>
                        <option value="2" id="2">事业</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">文章标题</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="title" value="${(article.title)!}" placeholder="请输入文章标题">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">文章内容</label>
                <div class="layui-input-block">
                    <textarea lay-verify="required" name="content" value="${(article.content)!}" placeholder="请输入内容" class="layui-textarea">${(article.content)!}</textarea>
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">作者</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="author" value="${(article.author)!}" placeholder="请输入作者">
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
    <script type="text/javascript" src="${ctx}/js/admin/article/add_update.js"></script>
    </body>
</html>