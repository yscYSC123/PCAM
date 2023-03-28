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
        ,url: ctx + '/adminClient/list2'
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
            ,{field: 'img', title: '用户照片',align:'center',templet:'#imgtmp',width: 100,style: 'height: 50px;'}
            ,{field: 'userName', title: '用户昵称',align:'center'}
            ,{field: 'name', title: '真实姓名',align:'center'}
            ,{field: 'sex', title: '性别',align:'center'}
            ,{field: 'level', title: '角色',align:'center',templet: function (d) {
                    //调用函数，返回格式化的结果
                    return formatState(d.level);
                }}
            ,{title: '操作',templet:'#userListBar',field: 'right',align: 'center',minWidth:150}
        ]]
    });

    function formatState(level) {
        if(level == 0){
            return "<div style='color: red'>管理员<div/>"
        }else{
            return "<div style='color: orange'>咨询师<div/>"
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
                userName: $("[name='userName']").val()//用户名称
                ,level:$("[name='level']").val()//角色
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
        if (data.event == "send"){
            toSend(data.data.id);
        }
    });

    //打开添加或修改用户页面
    function toSend(id) {
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

});