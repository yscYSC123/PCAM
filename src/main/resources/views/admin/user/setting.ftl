<!DOCTYPE html>
<html>
    <head>
        <#include "../../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:50%;">
            <#-- 用户ID -->
            <input name="id" type="hidden" value="${(user.id)!}"/>
            <div class="layui-form-item">
                <label class="layui-form-label">用户昵称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="userName" id="userName"  value="${(user.userName)!}" placeholder="请输入用户昵称">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input name"
                           lay-verify="required" name="name" id="name" value="${(user.name)!}" placeholder="请输入真实姓名">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input name="sex" type="hidden" value="${(user.sex)!}"/>
                    <input type="radio" name="sex" id="男" value="男" title="男">
                    <input type="radio" name="sex" id="女" value="女" title="女">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">年龄</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input age"
                           lay-verify="required" name="age" id="age" value="${(user.age)!}" placeholder="请输入年龄">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input phone"
                           lay-verify="required" name="phone" value="${(user.phone)!}" id="phone" placeholder="请输入手机号">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input email"
                           lay-verify="email" name="email" value="${(user.email)!}"
                           id="email"
                           placeholder="请输入邮箱">
                </div>
            </div>

            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdateUser">确认
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/admin/user/setting.js"></script>
    </body>
</html>