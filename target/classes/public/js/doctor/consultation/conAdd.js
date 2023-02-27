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
        , url: ctx + '/doctorArchive/list'
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
            ,{field: 'clientId', title: '来访者姓名',align:'center'}
            ,{field: 'expectTime', title: '预约时间',align:'center'}
            ,{field: 'subPlace', title: '咨询地点',align:'center'}
            ,{field: 'clientDescription', title: '情况描述',align:'center'}
            ,{field: 'status', title: '状态',align:'center',templet: function (d) {
                    //调用函数，返回格式化的结果
                    return formatState(d.status);
            }}
            , {title: '操作', templet: '#userListBar', field: 'right', align: 'center', minWidth: 150}
        ]]
    });

    function formatState(status) {
        if(status == 1){
            return "<div style='color: yellow'>预约中<div/>"
        }else if (status == 2){
            return "<div style='color: green'>预约成功<div/>"
        } else if(status == 0){
            return "<div style='color: red'>预约失败<div/>"
        }
    }

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

    }

    //打开预约页面
    function openToBooking(data){

    }

})