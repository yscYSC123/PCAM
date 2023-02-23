<!DOCTYPE html>
<html>
    <head>
        <#include "../../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <!-- 设置用户id的隐藏域 -->
            <input type="hidden" name="id" value="${(doctor.id)!}">
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="test1" style="margin-left: 190px">上传图片</button>
                <div class="layui-upload-list">
                    <input type="hidden" name="img" id="img" value="${(doctor.img)!}">
                    <img class="layui-upload-img" id="demo1" src="images/${(doctor.img)!}" style="margin-left: 190px">
                    <p id="demoText"></p>
                </div>
                <div style="width: 95px;margin-left: 190px;margin-bottom: 10px">
                    <div class="layui-progress layui-progress-big" lay-showpercent="yes" lay-filter="demo">
                        <div class="layui-progress-bar" lay-percent=""></div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">咨询师昵称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="userName" id="userName"  value="${(doctor.userName)!}" placeholder="请输入咨询师昵称">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">咨询师密码</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="pwd" id="pwd"  value="${(doctor.pwd)!}" placeholder="请输入咨询师密码">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="name"  lay-verify="required"  value="${(doctor.name)!}" placeholder="请输入真实姓名">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input name="sex" type="hidden" value="${(doctor.sex)!}"/>
                    <input type="radio" name="sex" id="男" value="男" title="男">
                    <input type="radio" name="sex" id="女" value="女" title="女">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">年龄</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="age" value="${(doctor.age)!}" placeholder="请输入年龄">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">联系邮箱</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="email" value="${(doctor.email)!}" placeholder="请输入联系邮箱">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">联系电话</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-verify="required" name="phone" value="${(doctor.phone)!}" id="phone" placeholder="请输入联系电话">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">证书</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="skill" value="${(doctor.skill)!}" id="skill" placeholder="请输入证书">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">工作地点</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="place" value="${(doctor.place)!}" id="place" placeholder="请输入工作地点">
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
        <script type="text/javascript" src="${ctx}/js/admin/doctor/add_update.js"></script>
    </body>
</html>