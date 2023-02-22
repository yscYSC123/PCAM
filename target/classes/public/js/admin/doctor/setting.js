layui.use(['upload','element','form', 'layer','formSelects'], function () {
    var form = layui.form
        ,upload = layui.upload
        ,element = layui.element
        ,layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var formSelects = layui.formSelects;

    var sex = $("[name = 'sex']").val();
    $('#'+sex+'').attr("checked", true);//设置属性为true
    form.render();//表单重新渲染一遍,最重要的,否则无效

    var uploadInst = upload.render({
        elem: '#test1'
        ,url: ctx+'/doctor/upload' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
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
            url = ctx + "/doctor/update";
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