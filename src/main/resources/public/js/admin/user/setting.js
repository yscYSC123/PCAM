layui.use(['form', 'layer','formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var formSelects = layui.formSelects;

    var sex = $("[name = 'sex']").val();
    $('#'+sex+'').attr("checked", true);//设置属性为true
    form.render();//表单重新渲染一遍,最重要的,否则无效

    /**
     * 表单submit监听
     */
    form.on('submit(addOrUpdateUser)',function (data) {
        //加载层
        var index = top.layer.msg("提交数据中，请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        });

        //得到所有的表单元素
        var formData = data.field;

        //判断用户id是否为空
        if ($("[name = 'id']").val()){
            url = ctx + "/user/update";
        }

        $.post(url,formData,function (result) {
            //判断操作是否添加成功
            if (result.code == 200){
                top.layer.msg("操作成功！",{icon:6});
                parent.location.reload();
            }else{
                layer.msg(result.msg,{icon:5});
            }
        });
        //阻止表单提交
        return false;
    });
});