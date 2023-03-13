layui.use(['table','layer','jquery'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //加载数据表格
    var tableIns = table.render({
        id:'userTable'
        //容器的id
        ,elem: '#userList'
        //容器的高度full-差值
        ,height: 'full-125'
        //单元格的最小宽度
        ,cellMinWidth:95
        //访问数据的url，后台的数据接口
        ,url: ctx + '/doctorArchive/list1'
        //开启分页
        ,page: true
        //每页显示的数量
        ,limit: 10
        //每页页数的可选项
        ,limits:[10,20,30,40,50]
        //开启头部工具栏
        ,toolbar:'#toolbarDemo'
        //表头
        ,cols: [[
            //field:要求field属性值与返回值的数据在对应的属性字段名一致
            //title:设置列的标题
            //sort:是否排序
            //fixed:固定列
            {type:'space'}
            ,{field: 'clientName', title: '来访者姓名',align:'center'}
            ,{field: 'expectTime', title: '预约时间',align:'center'}
            ,{field: 'startDatetime', title: '开始时间',align:'center'}
            ,{field: 'endDatetime', title: '结束时间',align:'center'}
            ,{field: 'subPlace', title: '咨询地点',align:'center'}
            ,{field: 'clientDescription', title: '情况描述',align:'center'}
            ,{field: 'status', title: '状态',align:'center',templet: function (d) {
                    //调用函数，返回格式化的结果
                    return formatState(d.status);
            }}
            ,{field: 'docPath', title: '咨询建议',align:'center',event: 'setEva', style:'cursor: pointer;'}
        ]]
    });

    function formatState(status) {
        if(status == 2){
            return "<div style='color: green'>咨询中<div/>"
        }
    }

    /**
     * 搜索按钮的点击事件
     */
    $(".search_btn").click(function () {

        /**
         * 表格重载
         *  多条件查询
         */
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                //通过文本框的值获得参数
                clientName: $("[name='clientName']").val()//用户名称
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    /**
     * 监听行工具栏
     */
    table.on('tool(users)',function (data) {
        if (data.event == "toAgain"){
            openAgain(data);
        }else if (data.event == "toCancel"){
            openCancel(data);
        }
    });

    //打开预约申请页面
    function openAgain(data){
        var data = data.data;
        console.log(data);
        var title = "<h3>预约申请</h3>";
        var url = ctx + "/clientArchive/toBooking";
        //iframe层
        layui.layer.open({
            type: 2,
            title: title,
            area: ['660px', '500px'],
            content: url, //iframe的url
            maxmin:true
            ,success:function (layero, index) {
                // 获取子页面的iframe
                var iframe = window['layui-layer-iframe'+index];
                // 向子页面的全局函数child传参
                iframe.child(data);
            }
        });
    }
    //打开取消理由页面
    function openCancel(data) {
        console.log(data.data);
        layer.confirm('确认取消预约吗?', {icon: 3, title: '系统提示'}, function (index) {
            //关闭询问框
            layer.close(index);
            $.ajax({
                url: ctx + "/clientArchive/cancel", // 后端接口
                type: 'POST',
                data: data.data,
                success:function (result) {
                    //判断删除结果
                    if(result.code == 200){
                        layer.msg("取消成功！",{icon:6});
                        //刷新父窗口
                        parent.location.reload();
                    }else {
                        layer.msg(result.msg,{icon: 5});
                    }
                }
            });
        })
    }

    //监听单元格事件
    table.on('tool(users)', function(obj){
        var date = obj.data;
        if(obj.event === 'setEva'){
            layer.prompt({
                formType: 2
                ,title: '咨询建议'
                ,value: date.evaluation
            }, function(value, index){
                layer.close(index);
                date.evaluation = value;

                //这里一般是发送修改的Ajax请求
                $.ajax({
                    url: ctx + "/doctorArchive/update", // 后端接口
                    type: 'POST',
                    data: date,
                    success:function (result) {
                        //判断更新结果
                        if(result.code == 200){
                            layer.msg("评价成功！",{icon:6});
                            //刷新表格
                            tableIns.reload();
                        }else {
                            layer.msg(result.msg,{icon: 5});
                        }
                    }
                });

                //同步更新表格和缓存对应的值
                obj.update({
                    evaluation: value
                });
            });
        }
    });

});