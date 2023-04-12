layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome1"></iframe>')
    layuimini.initTab();

    /**
     * 用户退出
     */
    $(".login-out").click(function () {
        //  弹出提示框
        layer.confirm('确认退出系统吗?', {icon: 3, title: '系统提示'}, function (index) {
            //关闭询问框
            layer.close(index);
            $.ajax({
                url: 'user/logout', // 后端接口，用于注销会话
                type: 'POST',
                success: function (result) {
                    // 跳转到登录页面
                    window.parent.location.href = ctx + "/index";
                },
                error: function () {
                    // 处理错误情况
                    layer.msg(result.msg, {icon: 5})
                }
            });
        })
    })
});