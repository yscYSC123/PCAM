layui.use(['upload', 'element','form', 'layer'], function () {
    var form = layui.form
        ,upload = layui.upload
        ,element = layui.element
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var sex = $("[name = 'sex']").val();
    $('#'+sex+'').attr("checked", true);//设置属性为true
    var a = $("[name='level']").val();
    $('#'+a+'').attr("selected","selected");
    form.render();//表单重新渲染一遍,最重要的,否则无效

    var uploadInst = upload.render({
        elem: '#test1'
        ,url: ctx+'/adminClient/upload' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
            });

            element.progress('demo', '0%'); //进度条复位
            layer.msg('上传中', {icon: 16, time: 0});
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功的一些操作
            //……
            $('#demoText').html(''); //置空上传失败的状态
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
        //进度条
        ,progress: function(n, elem, e){
            element.progress('demo', n + '%'); //可配合 layui 进度条元素使用
            if(n == 100){
                layer.msg('上传完毕', {icon: 1});
            }
        }
    });

    //监听表单事件
    form.on('submit(addOrUpdateSaleChance)',function (data) {
        //加载层
        var index = layer.msg("提交数据中，请稍后...",{
            icon:16,
            time:false,
            shade:0.8
        });

        //发送ajax请求
        var url = ctx + "/adminClient/add";

        //通过用户的id判断当前需要执行添加操作是否为修改操作
        var clientId = $("[name='id']").val();
        console.log(clientId);
        if (clientId != null && clientId != ''){
            url = ctx + "/adminClient/update";
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