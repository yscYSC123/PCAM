layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     *表单的submit监听
     */
    form.on('submit(saveBtn)',function (data) {
        //所有表单元素的值
        console.log(data.field);

        //发送ajax请求
        $.ajax({
            type:"post",
            url:ctx + "/user/updatePwd",
            data:{
                oldPwd:data.field.old_password,
                newPwd:data.field.new_password,
                repeatPwd:data.field.again_password
            },
            success:function (result){
                //判断是否修改成功
                if (result.code == 200){
                    //修改密码成功后清空cookie，跳转到登录页面
                    layer.msg("用户密码修改成功，系统将在3秒后退出...",function (){
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/pcam"});
                        $.removeCookie("userName",{domain:"localhost",path:"/pcam"});
                        $.removeCookie("name",{domain:"localhost",path:"/pcam"});

                        //跳转到父窗口登录页面
                        window.parent.location.href = ctx + "/index";
                    })
                }else {
                    layer.msg(result.msg,{icon:5});
                }
            }
        });
    });

});