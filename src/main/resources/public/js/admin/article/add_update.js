layui.use(['form', 'layer'], function () {
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var a = $("[name='label']").val();
    $('#'+a+'').attr("selected","selected");
    form.render();//表单重新渲染一遍,最重要的,否则无效

    //监听表单事件
    form.on('submit(addOrUpdateSaleChance)',function (data) {
        //加载层
        var index = layer.msg("提交数据中，请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        });

        //发送ajax请求
        var url = ctx + "/article/add";

        //通过用户的id判断当前需要执行添加操作是否为修改操作
        var clientId = $("[name='id']").val();
        console.log(clientId);
        if (clientId != null && clientId != ''){
            url = ctx + "/article/update";
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