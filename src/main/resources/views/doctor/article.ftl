<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>

<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="title" class="layui-input searchVal" placeholder="文章标题" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="author" class="layui-input searchVal" placeholder="作者" />
                </div>
                <div class="layui-input-inline">
                    <select name="label"  id="label">
                        <option value="" >文章类型</option>
                        <option value="0">生活</option>
                        <option value="1" >爱情</option>
                        <option value="2" >事业</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i>
                    搜索
                </a>
            </div>
        </form>
    </blockquote>

    <table id="userList" class="layui-table"  lay-filter="users"></table>

    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">查看</a>
    </script>
</form>
<script>
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
            ,url: ctx + '/article/list'
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
                ,{field: 'title', title: '文章标题',align:'center'}
                ,{field: 'label', title: '文章类型',align:'center',templet: function (d) {
                        //调用函数，返回格式化的结果
                        return formatState(d.label);
                    }}
                ,{field: 'content', title: '文章内容',align:'center'}
                ,{field: 'author', title: '作者',align:'center'}
                ,{field: 'createTime', title: '创建时间',align:'center'}
                ,{title: '操作',templet:'#userListBar',field: 'right',align: 'center',minWidth:150}
            ]]
        });

        function formatState(label) {
            if(label == 0){
                return "<div>生活<div/>"
            }else if (label == 1){
                return "<div>爱情<div/>"
            }else {
                return "<div>事业<div/>"
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
                    label: $("[name='label']").val(),
                    title: $("[name='title']").val(),
                    author: $("[name='author']").val(),
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
            if (data.event == "edit"){
                openAddOrUpdateUserDialog(data.data.id);
            }
        });

        //打开添加或修改用户页面
        function openAddOrUpdateUserDialog(id) {
            var title = "<h3>查看文章</h3>";
            var url = ctx + "/userArticle/toPage";
            //判断id是否为空
            if (id != null && id != ''){
                url += '?id=' + id;
            }
            //iframe层
            layui.layer.open({
                type: 2,
                title: title,
                area: ['500px', '450px'],
                content: url, //iframe的url
                maxmin:true
            });
        }

    });
</script>
</body>

</html>