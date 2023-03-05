<!DOCTYPE html>
<html>
    <head>
        <title>咨询中</title>
        <#include "../../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" >
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="clientName" class="layui-input searchVal" placeholder="来访者姓名" />
                        </div>
                        <a class="layui-btn search_btn" data-type="reload">
                            <i class="layui-icon">&#xe615;</i>
                            搜索
                        </a>
                    </div>
                </form>
            </blockquote>

            <table id="userList" class="layui-table"  lay-filter="users"></table>
        </form>
        <script type="text/javascript" src="${ctx}/js/doctor/consultation/conMy.js"></script>
    </body>
</html>