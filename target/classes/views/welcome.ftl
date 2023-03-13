<!DOCTYPE html>
<html>
<head>
  <#include "common.ftl">
</head>


<body class="childrenBody">

<div class="layui-row layui-col-space15">

    <div class="layui-col-sm6 layui-col-md3">
        <div class="layui-card">
            <div class="layui-card-header">
                管理员
                <span class="layui-badge layui-bg-green">人</span>
            </div>
            <div class="layui-card-body">
                <p style="margin-top:10px; height: 40px; font-size: 36px; color: grey; font-weight: bold;" id="userCount"></p>
            </div>
        </div>
    </div>
    <div class="layui-col-sm6 layui-col-md3">
        <div class="layui-card">
            <div class="layui-card-header">
                咨询师
                <span class="layui-badge layui-bg-green">人</span>
            </div>
            <div class="layui-card-body">
                <p style="margin-top:10px; height: 40px; font-size: 36px; color: grey; font-weight: bold;" id="doctorCount"></p>
            </div>
        </div>
    </div>
    <div class="layui-col-sm6 layui-col-md3">
        <div class="layui-card">
            <div class="layui-card-header">
                来访者
                <span class="layui-badge layui-bg-green">人</span>
            </div>
            <div class="layui-card-body">

                <p style="margin-top:10px; height: 40px; font-size: 36px; color: grey; font-weight: bold;" id="clientCount"></p>
            </div>
        </div>
    </div>
    <div class="layui-col-sm6 layui-col-md3">
        <div class="layui-card">
            <div class="layui-card-header">
                现在时间
            </div>
            <div class="layui-card-body">

                <p style="margin-top:10px; height: 40px; font-size: 36px; color: grey; font-weight: bold;" id="time"></p>
            </div>
        </div>
    </div>
    <div class="layui-col-sm4">
        <div class="layui-card">
            <div class="layui-card-header">最新留言</div>
            <div class="layui-card-body">
                <ul class="layuiadmin-card-status layuiadmin-home2-usernote">
                    <li>
                        <h3>张三</h3>
                        <p>作为 layui 官方推出的后台模板，从初版的饱受争议，到后续的埋头丰富，逐步占据了国内后台系统应用的主要市场。</p>
                        <span>5月30日 00:00</span>
                        <a href="javascript:;" layadmin-event="replyNote" data-id="7" class="layui-btn layui-btn-xs layuiadmin-reply">回复</a>
                    </li>
                    <li>
                        <h3>诸葛亮</h3>
                        <p>皓首匹夫！苍髯老贼！你枉活九十有六，一生未立寸功，只会摇唇鼓舌！助曹为虐！一条断脊之犬，还敢在我军阵前狺狺狂吠，我从未见过有如此厚颜无耻之人！</p>
                        <span>5月02日 00:00</span>
                        <a href="javascript:;" layadmin-event="replyNote" data-id="5" class="layui-btn layui-btn-xs layuiadmin-reply">回复</a>
                    </li>
                    <li>
                        <h3>张三</h3>
                        <p>你以为只要长得漂亮就有男生喜欢？你以为只要有了钱漂亮妹子就自己贴上来了？你以为学霸就能找到好工作？我告诉你吧，这些都是真的！</p>
                        <span>5月11日 00:00</span>
                        <a href="javascript:;" layadmin-event="replyNote" data-id="6" class="layui-btn layui-btn-xs layuiadmin-reply">回复</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="layui-col-sm8">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-sm6">
                <div class="layui-card">
                    <div class="layui-card-header">最新公告</div>
                    <div class="layui-card-body"></div>
                </div>
            </div>
            <div class="layui-col-sm6">
                <div class="layui-card">
                    <div class="layui-card-header">最新消息</div>
                    <div class="layui-card-body">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['jquery'], function(){
        var $ = layui.jquery;

        setInterval(function(){
            var now = new Date();
            var timeStr = now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate() + ' ' + now.getHours() + ':' + now.getMinutes() + ':' + now.getSeconds();
            $('#time').text(timeStr);
        }, 1000);

        $.ajax({
            type: "post",
            url: ctx + "/user/count",
            dataType: "json",
            success:function (data) {
                var userCount = document.getElementById("userCount");
                userCount.innerText=data.userCount;
                var doctorCount = document.getElementById("doctorCount");
                doctorCount.innerText=data.doctorCount;
                var clientCount = document.getElementById("clientCount");
                clientCount.innerText=data.clientCount;
            }
        });

    });
</script>
</body>
</html>
