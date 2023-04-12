<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <!-- 设置用户id的隐藏域 -->
            <input type="hidden" name="id" value="${(article.id)!}">
            <div class="layui-form-item layui-row layui-col-xs12">
                <p align= "center"><span class="title" style="font-size: large">${(article.title)!}</span> &nbsp;|&nbsp; <span id="label" style="font-size: large" value="${(article.label)!}"></span></p>
                <br>
                <p align= "center"><span class="label" style="color: red">${(article.author)!}</span></p>
                <br>
                <p align= "center"><span class="label" style="padding-top: 10px">${(article.content)!}</span></p>
            </div>
        </form>
        <script>
            if (${(article.label)!}===0)
                document.getElementById("label").innerText="类型：生活";
            else if(${(article.label)!}===1)
                document.getElementById("label").innerText="类型：爱情";
            else
                document.getElementById("label").innerText="类型：事业";
        </script>
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