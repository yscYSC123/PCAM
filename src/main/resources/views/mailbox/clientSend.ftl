<!DOCTYPE html>
<html>
    <head>
        <title>发信箱</title>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" >
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="userName" class="layui-input searchVal" placeholder="用户昵称" />
                        </div>
                        <div class="layui-input-inline">
                            <select name="level"  id="level">
                                <option value="" >角色</option>
                                <option value="0">管理员</option>
                                <option value="1" >咨询师</option>
                                <option value="2" >来访者</option>
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
                <a class="layui-btn layui-btn-xs" id="send" lay-event="send">发送信息</a>
            </script>
        </form>
        <script type="text/html" id="imgtmp">
            <img src="images/{{d.img}}" style="width: 50px" height="50px"/>
        </script>
    <script type="text/javascript" src="${ctx}/js/mailbox/clientSend.js"></script>
    </body>
</html>