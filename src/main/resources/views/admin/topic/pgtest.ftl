<!DOCTYPE html>
<html>
    <head>
        <title>记录管理</title>
        <#include "../../common.ftl">
    </head>
    <body class="childrenBody">
    <form class="layui-form" >
        <blockquote class="layui-elem-quote quoteBox">
            <form class="layui-form">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" name="result" class="layui-input searchVal" placeholder="测评结果" />
                    </div>
                    <div class="layui-input-inline">
                        <input type="text" name="userOp" class="layui-input searchVal" placeholder="答题人" />
                    </div>
                    <a class="layui-btn search_btn" data-type="reload">
                        <i class="layui-icon">&#xe615;</i>
                        搜索
                    </a>
                </div>
            </form>
        </blockquote>

        <table id="userList" class="layui-table"  lay-filter="users"></table>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                    <i class="layui-icon">&#xe608;</i>
                    删除记录
                </a>
            </div>
        </script>

        <!--操作-->
        <script id="userListBar" type="text/html">
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
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
                ,url: ctx + '/pgtest/list'
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
                    ,{field: 'result', title: '测评结果',align:'center'}
                    ,{field: 'score', title: '测评分数',align:'center'}
                    ,{field: 'pgtestTime', title: '创建时间',align:'center'}
                    ,{field: 'userOp', title: '操作者',align:'center'}
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
                        result: $("[name='result']").val(),//公告编号
                        userOp: $("[name='userOp']").val()//公告编号
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
               if (data.event == "del") {
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
                        url:ctx + "/pgtest/delete",
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
                if (data.event == "del"){
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
                        url:ctx + "/pgtest/delete",
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
        });
    </script>
    </body>
</html>