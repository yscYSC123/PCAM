layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on('submit(register)', function(data){
        var index = layer.msg("提交数据中，请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        });
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        //发送ajax请求，数据，执行用户操作
        $.ajax({
            type:"post",
            url:ctx + "/user/toRegister",
            data:data.field,
            success:function (result) {//回调函数，用来接收返回后的数据
                console.log(result);
                //判断是否登录成功
                if (result.code == 200) {
                    layer.msg("注册成功！返回登录界面", function () {
                        window.location.href = ctx + "/index";
                    })
                } else {
                    //失败
                    layer.msg(result.msg, {icon: 5})
                }
            }
        })
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});