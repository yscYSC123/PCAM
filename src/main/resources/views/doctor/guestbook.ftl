<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
        <div class="layui-col-md12">
            <div class="layui-form">
                <div class="layui-form">
                    <div class="layui-input-block">
                        <textarea lay-verify="required" style="margin-top: 20px;margin-bottom:10px;width: 800px" name="context" id="context"  value="${(guestbook.context)!}" placeholder="请输入内容" class="layui-textarea">${(guestbook.context)!}</textarea>
                    </div>
                </div>

                <div class="layui-form-item" style="overflow: hidden;">
                    <div class="layui-input-block layui-input-right">
                        <button class="layui-btn" lay-submit="" lay-filter="formDemo">发表</button>
                    </div>
                </div>
                <div class="layui-card">
                    <div class="layui-card-body">
                        <ul class="lay-scroll" id="guestbook">
                        </ul>
                    </div>
                </div>
            </div>
        </div>


    <script>
        layui.use(['jquery','form'], function() {
            var $ = layui.jquery,form = layui.form;

            $.ajax({
                type: "post",
                url: ctx + "/guestbook/show",
                dataType: "json",
                success:function (data) {
                    var ul = document.getElementById("guestbook");
                    for (var i = data.length-1; i > 1; i--) {
                        var li = document.createElement("li");
                        var h3 = document.createElement("h3");
                        var p = document.createElement("p");
                        var span = document.createElement("span");
                        h3.innerHTML = data[i];
                        p.innerHTML = data[--i];
                        span.innerHTML = data[--i];
                        span.style.color = "#999";
                        span.style.paddingRight = "350px";
                        span.style.paddingBottom = "5px";
                        h3.style.fontSize = "18px";
                        li.style.paddingBottom = "10px";
                        li.appendChild(span);
                        li.appendChild(p);
                        li.appendChild(h3);
                        ul.appendChild(li);
                    }
                }
            });

            form.on('submit(formDemo)', function(data){
                var index = layer.msg("提交数据中，请稍后...",{
                    icon:16,
                    time:false,
                    shade:0.8
                });

                //发送ajax请求
                var url = ctx + "/guestbook/add";

                $.post(url,data.field,function (result) {
                    //判断操作是否添加成功
                    if (result.code == 200){
                        layer.msg("操作成功！",{icon:6});
                        //关闭加载层
                        layer.close(index);
                        //关闭弹出层
                        layer.closeAll("iframe");
                        //刷新父窗口
                        parent.location.reload();
                    }else{
                        layer.msg(result.msg,{icon:5});
                    }
                });
                return false;
            });
        });
    </script>

</body>
</html>