layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    /**
     * 表单submit提交
     *  form.on('submit(按扭的lay-filter属性值)', function(data)
     */
    form.on('submit(login)', function(data){

        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}

        //表单元素非空校验

        //发送ajax请求，传递用户姓名与密码，执行用户操作
        $.ajax({
            type:"post",
            url:ctx + "/user/login",
            data:{
                userName:data.field.username,
                userPwd:data.field.password
            },
            success:function (result) {//回调函数，用来接收返回后的数据
                console.log(result);
                //判断是否登录成功
                if(result.code == 200){
                    //成功
                    /**
                     * 设置用户登录成功状态
                     * 1. 利用session会话
                     *      保存用户信息，如果会话存在，则用户是登录状态
                     *      缺点：服务器关闭或浏览器关闭，则会话失效
                     * 2. 利用cookie存储
                     *      保存用户信息，cookie未失效，则用户是登录状态
                     */
                    layer.msg("登录成功！",function (){
                        $.cookie("userIdStr",result.result.userIdStr);
                        $.cookie("userName",result.result.userName);
                        $.cookie("trueName",result.result.trueName);
                        switch (result.result.level) {
                            case 0:window.location.href = ctx + "/main"; break;
                            case 1:window.location.href = ctx + "/main1"; break;
                            case 2:window.location.href = ctx + "/main2"; break;
                        }

                    })

                }else {
                    //失败
                    layer.msg(result.msg,{icon:5})
                }
            }
        })

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    
});