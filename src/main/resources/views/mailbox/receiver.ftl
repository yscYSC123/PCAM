<!DOCTYPE html>
<html>
    <head>
        <title>用户管理</title>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" >
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="senderName" class="layui-input searchVal" placeholder="发送者姓名" />
                        </div>
                        <div class="layui-input-inline">
                            <select name="isRead"  id="isRead">
                                <option value="" >读取状态</option>
                                <option value="0">未读</option>
                                <option value="1" >已读</option>
                            </select>
                        </div>
                        <a class="layui-btn search_btn" data-type="reload">
                            <i class="layui-icon">&#xe615;</i>
                            搜索
                        </a>
                    </div>
                </form>
            </blockquote>

            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                        <i class="layui-icon">&#xe608;</i>
                        批量删除
                    </a>
                </div>
            </script>

            <table id="userList" class="layui-table"  lay-filter="users"></table>

        </form>
    <script type="text/javascript" src="${ctx}/js/mailbox/receiver.js"></script>
    </body>
</html>