layui.use(['table','layer'],function(){
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
        ,url: ctx + '/guestbook/list'
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
            {type:'checkbox'}
            ,{field: 'id', title: '编号', sort: true}
            ,{field: 'context', title: '留言内容',align:'center'}
            ,{field: 'createrId', title: '创建者Id',align:'center'}
            ,{field: 'createTime', title: '创建时间',align:'center'}
            ,{title: '操作',templet:'#userListBar',field: 'right',align: 'center',minWidth:150}
        ]]
    });

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
                createrId: $("[name='createrId']").val()//公告编号
                ,context: $("[name='context']").val()//公告标题
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

    /**
     * 监听头部工具栏
     */
    table.on('toolbar(users)',function (data) {
        if (data.event == "add"){
            openAddOrUpdateUserDialog();
        }else if (data.event == "del"){
            //获取被选中的数据的信息
            var checkStatus = table.checkStatus(data.config.id);
            deleteUsers(checkStatus.data);
        }
    });

    /**
     * 删除多条用户记录
     * @param userData
     */
    function deleteUsers(userData) {
        //判断用户是否选择的记录
        if (userData.length == 0){
            layer.msg("请选择要删除的记录！",{icon:5});
            return;
        }

        //询问是否删除
        layer.confirm('您确定要删除选中的记录吗？',{icon:3,title:'公告管理'},function (index) {
            layer.close(index);
            //传递的参数是数组
            var ids = "ids=";
            //循环选中的行记录数据
            for (var i = 0;i < userData.length;i ++){
                if (i < userData.length-1){
                    ids = ids + userData[i].id + "&ids=";
                }else {
                    ids = ids + userData[i].id;
                }
            }

            //发送ajax请求，执行删除操作
            $.ajax({
                type:"post",
                url:ctx + "/guestbook/delete",
                data:ids,
                success:function (result) {
                    //判断删除结果
                    if(result.code == 200){
                        layer.msg("删除成功！",{icon:6});
                        //刷新表格
                        tableIns.reload();
                    }else {
                        layer.msg(result.msg,{icon: 5});
                    }
                }
            });
        });
    }

    /**
     * 监听行工具栏
     */
    table.on('tool(users)',function (data) {
        if (data.event == "edit"){
            openAddOrUpdateUserDialog(data.data.id);
        }else if (data.event == "del"){
            deleteUser(data.data.id);
        }
    });

    /**
     * 删除单条用户记录
     * @param id
     */
    function deleteUser(id) {
        layer.confirm('您确定要删除该记录吗？',{icon:3,title:"公告管理"},function (index) {
            layer.close(index);
            $.ajax({
                type:"post",
                url:ctx + "/guestbook/delete",
                data:{
                    ids:id
                },
                success:function (result) {
                    //判断删除结果
                    if(result.code == 200){
                        layer.msg("删除成功！",{icon:6});
                        //刷新父窗口
                        tableIns.reload();
                    }else {
                        layer.msg(result.msg,{icon: 5});
                    }
                }
            });
        });
    }

    //打开添加或修改用户页面
    function openAddOrUpdateUserDialog(id) {
        var title = "<h3>添加留言</h3>";
        var url = ctx + "/guestbook/toPage";
        //判断id是否为空
        if (id != null && id != ''){
            title = "<h3>更新留言</h3>";
            url += '?id=' + id;
        }
        //iframe层
        layui.layer.open({
            type: 2,
            title: title,
            area: ['520px', '300px'],
            content: url, //iframe的url
            maxmin:true
        });
    }

});