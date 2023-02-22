<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <#include "common.ftl">
    <link rel="stylesheet" href="${ctx}/css/register.css" media="all">
</head>
<body>
    <div class="layui-container">
        <div class="admin-login-background">
            <div class="layui-form login-form">
                <form class="layui-form" action="">
                    <div class="layui-form-item logo-title">
                        <h1>心理咨询预约系统注册</h1>
                    </div>
                    <form class="layui-form" style="width:80%;">
                        <!-- 设置用户id的隐藏域 -->
                        <input type="hidden" name="id" value="${(user.id)!}">
                        <div class="layui-form-item layui-row layui-col-xs12">
                            <label class="layui-form-label">用户昵称</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" lay-verify="required" name="userName" id="userName"  value="${(user.userName)!}" placeholder="请输入用户昵称">
                            </div>
                        </div>
                        <div class="layui-form-item layui-row layui-col-xs12">
                            <label class="layui-form-label">用户密码</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" lay-verify="required" name="pwd" id="pwd"  value="${(user.pwd)!}" placeholder="请输入用户密码">
                            </div>
                        </div>
                        <div class="layui-form-item layui-row layui-col-xs12">
                            <label class="layui-form-label">真实姓名</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="name"  lay-verify="required"  value="${(user.name)!}" placeholder="请输入真实姓名">
                            </div>
                        </div>
                        <div class="layui-form-item layui-row layui-col-xs12">
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-block">
                                <input name="sex" type="hidden" value="${(user.sex)!}"/>
                                <input type="radio" name="sex" id="男" value="男" title="男">
                                <input type="radio" name="sex" id="女" value="女" title="女">
                            </div>
                        </div>
                        <div class="layui-form-item layui-row layui-col-xs12">
                            <label class="layui-form-label">年龄</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="age" value="${(user.age)!}" placeholder="请输入年龄">
                            </div>
                        </div>
                        <div class="layui-form-item layui-row layui-col-xs12">
                            <label class="layui-form-label">电子邮箱</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="email" value="${(user.email)!}" placeholder="请输入电子邮箱">
                            </div>
                        </div>
                        <div class="layui-form-item layui-row layui-col-xs12">
                            <label class="layui-form-label">联系电话</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" lay-verify="required" name="phone" value="${(user.phone)!}" id="phone" placeholder="请输入联系电话">
                            </div>
                        </div>
                        <br/>
                        <div class="layui-form-item layui-row layui-col-xs12">
                            <div class="layui-input-block">
                                <button class="layui-btn layui-btn-lg" lay-submit=""  lay-filter="register">注册 </button>
                            </div>
                        </div>
                    </form>
                </form>
            </div>
        </div>
    </div>
<script src="${ctx}/js/register.js" charset="utf-8"></script>
</body>
</html>