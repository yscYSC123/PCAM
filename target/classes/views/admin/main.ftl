<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>心理咨询管理系统</title>
    <#include "*/common.ftl">
</head>
<body class="layui-layout-body layuimini-all">1
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
                <h1>管理员</h1>
        </div>
        <a>
            <div class="layuimini-tool">
                <i title="展开" class="fa fa-outdent" data-side-fold="1"></i>
                <h2>心理咨询管理系统</h2>
            </div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(user.userName)!""}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toSettingPage" data-title="基本资料" data-icon="fa fa-gears">基本资料</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toPasswordPage" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" class="login-out">退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips" data-tab="adminClient/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 用户管理</span></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips" data-tab="adminDoctor/index" target="_self"><i class="fa fa-user-circle-o"></i><span class="layui-left-nav"> 咨询师管理</span></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="layui-icon layui-icon-form"></i><span class="layui-left-nav"> 心理测评</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-1" data-tab="topic/topicView" target="_self"><i class="layui-icon layui-icon-tabs"></i><span class="layui-left-nav"> 试题</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-2" data-tab="pgtest/pgtestView" target="_self"><i class="layui-icon layui-icon-list "></i><span class="layui-left-nav"> 记录</span></a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-commenting"></i><span class="layui-left-nav"> 消息</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-1" data-tab="message/index1" target="_self"><i class="fa fa-envelope-open"></i><span class="layui-left-nav"> 收件箱</span></a>
                                </dd>
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-2" data-tab="message/index2" target="_self"><i class="fa fa-envelope "></i><span class="layui-left-nav"> 发件箱</span></a>
                                </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips" data-tab="article/index" target="_self"><i class="layui-icon layui-icon-read"></i><span class="layui-left-nav"> 文章管理</span></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips" data-tab="announcement/index" target="_self"><i class="fa fa-bell"></i><span class="layui-left-nav"> 公告管理</span></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips" data-tab="guestbook/index" target="_self"><i class="fa fa-edit"></i><span class="layui-left-nav"> 留言管理</span></a>
                    </li>
                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>

            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/js/admin/main.js"></script>
</body>
</html>
