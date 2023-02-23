<!DOCTYPE html>
<html>
    <head>
        <#include "../../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <!-- 设置用户id的隐藏域 -->
            <input type="hidden" name="id" value="${(client.id)!}">
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">用户昵称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="userName" id="userName"  value="${(client.userName)!}" placeholder="请输入用户昵称">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">用户密码</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="pwd" id="pwd"  value="${(client.pwd)!}" placeholder="请输入用户密码">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="name"  lay-verify="required"  value="${(client.name)!}" placeholder="请输入真实姓名">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input name="sex" type="hidden" value="${(client.sex)!}"/>
                    <input type="radio" name="sex" id="男" value="男" title="男">
                    <input type="radio" name="sex" id="女" value="女" title="女">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">年龄</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="age" value="${(client.age)!}" placeholder="请输入年龄">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">电子邮箱</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="email" value="${(client.email)!}" placeholder="请输入电子邮箱">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">联系电话</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="phone" value="${(client.phone)!}" id="phone" placeholder="请输入联系电话">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-block">
                    <select name="level"  id="level">
                        <option name="level" value="${(client.level)!}">请选择角色</option>
                        <option value="0" id="0" >管理员</option>
                        <option value="1" id="1">咨询师</option>
                        <option value="2" id="2">来访者</option>
                    </select>
                </div>
            </div>
            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="addOrUpdateSaleChance">确认 </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>
        </form>
    <script type="text/javascript" src="${ctx}/js/admin/client/add_update.js"></script>
    </body>
</html>