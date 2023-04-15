<!DOCTYPE html>
<html>
    <head>
        <#include "../../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <!-- 设置id的隐藏域 -->
            <input type="hidden" name="id" value="${(topic.id)!}">
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">试题内容</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="content" id="content"  value="${(topic.content)!}" placeholder="请输入试题内容">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">参考答案</label>
                <div class="layui-input-block">
                    <textarea lay-verify="required" name="answer" id="answer"  value="${(topic.answer)!}" placeholder="请输入参考答案" class="layui-textarea">${(topic.answer)!}</textarea>
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
    <script>
        layui.use(['form', 'layer'], function () {
            var form = layui.form
            layer = parent.layer === undefined ? layui.layer : top.layer,
                $ = layui.jquery;

            //监听表单事件
            form.on('submit(addOrUpdateSaleChance)',function (data) {
                //加载层
                var index = layer.msg("提交数据中，请稍后...",{
                    icon:16,
                    time:false,
                    shade:0.8
                });

                //发送ajax请求
                var url = ctx + "/topic/add";

                //通过用户的id判断当前需要执行添加操作是否为修改操作
                var id = $("[name='id']").val();
                if (id != null && id != ''){
                    url = ctx + "/topic/update";
                }

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