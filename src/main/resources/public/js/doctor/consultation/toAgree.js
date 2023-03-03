function child(dataFromFather) {
    layui.use(['form', 'layer', 'laydate'], function () {
        var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery
            , laydate = layui.laydate;

        //常规用法
        laydate.render({
            elem: '#test-limit2'
            ,min: 0
            ,max: 15
        });
        if (dataFromFather.doctorName==undefined) {
            // 获取数据对象
            var da = {doctorName: dataFromFather.name, name: 'Layui'};
            // 将数据对象与表单绑定
            form.val('example', da);
        }else{
            var da = {doctorName: dataFromFather.doctorName, name: 'Layui'};
            form.val('example', da);
        }

        form.on('submit(add)', function (data) {
            //加载层
            var index = layer.msg("提交数据中，请稍后...", {
                icon: 16,
                time: false,
                shade: 0.8
            });
            if (dataFromFather.doctorId==undefined) {
                data.field.doctorId = dataFromFather.id;
            }else{
                data.field.doctorId = dataFromFather.doctorId;
            }
            //发送ajax请求
            var url = ctx + "/clientArchive/add";
            $.post(url, data.field, function (result) {
                //判断操作是否添加成功
                if (result.code == 200) {
                    layer.msg("操作成功！", {icon: 6});
                    //关闭加载层
                    layer.close(index);
                    //关闭弹出层
                    layer.closeAll("iframe");
                    //刷新父窗口
                    parent.location.reload();
                } else {
                    layer.msg(result.msg, {icon: 5});
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

    })
}