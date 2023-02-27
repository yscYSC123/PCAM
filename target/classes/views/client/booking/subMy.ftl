<!DOCTYPE html>
<html>
    <head>
        <title>我的预约</title>
        <#include "../../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" >
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="doctorName" class="layui-input searchVal" placeholder="咨询师姓名" />
                        </div>
                        <div class="layui-input-inline">
                            <select name="status"  id="status">
                                <option value="" >状态</option>
                                <option value="1">预约中</option>
                                <option value="2" >预约成功</option>
                                <option value="0" >预约失败</option>
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
        </form>
        <script type="text/javascript" src="${ctx}/js/client/booking/subMy.js"></script>
    </body>
</html>