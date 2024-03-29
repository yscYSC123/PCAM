layui.use(['table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //加载数据表格
    var tableIns = table.render({
        id: 'userTable'
        //容器的id
        , elem: '#userList'
        //容器的高度full-差值
        , height: 'full-125'
        //单元格的最小宽度
        , cellMinWidth: 95
        //访问数据的url，后台的数据接口
        , url: ctx + '/clientArchive/list'
        //开启分页
        , page: true
        //每页显示的数量
        , limit: 10
        //每页页数的可选项
        , limits: [10, 20, 30, 40, 50]
        //开启头部工具栏
        , toolbar: '#toolbarDemo'
        //表头
        , cols: [[
            //field:要求field属性值与返回值的数据在对应的属性字段名一致
            //title:设置列的标题
            //sort:是否排序
            //fixed:固定列
            {type:'space'}
            ,{field: 'img', title: '咨询师照片',align:'center',templet:'#imgtmp',width: 100,style: 'height: 50px;'}
            , {field: 'name', title: '咨询师姓名', align: 'center'}
            , {field: 'sex', title: '性别', align: 'center'}
            , {field: 'age', title: '年龄', align: 'center'}
            , {field: 'email', title: '联系邮箱', align: 'center'}
            , {field: 'phone', title: '联系电话', align: 'center'}
            ,{field: 'skill', title: '证书',align:'center'}
            ,{field: 'place', title: '工作地点',align:'center'}
            , {title: '操作', templet: '#userListBar', field: 'right', align: 'center', minWidth: 150}
        ]]
    });

    /**
     * 监听行工具栏
     */
    table.on('tool(users)',function (data) {
        if (data.event == "toMessage"){
            openToMessage(data.data.id);
        }else if (data.event == "toBooking"){
            openToBooking(data);
        }
    });

    //打开发送信息页面
    function openToMessage(id){
        var url = ctx + "/message/toSendPage1";
        //判断id是否为空
        if (id != null && id != ''){
            var title = "<h3>发送信息</h3>";
            url += '?id=' + id;
        }
        //iframe层
        layui.layer.open({
            type: 2,
            title: title,
            area: ['500px', '300px'],
            content: url, //iframe的url
            maxmin:true
        });
    }

    //打开预约页面
    function openToBooking(data){
        var data = data.data;
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

})