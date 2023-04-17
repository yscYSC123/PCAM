<!DOCTYPE html>
<html>
<head>
  <#include "*/common.ftl">
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
    <div class="layui-col-sm6">
        <div class="layui-card">
            <div class="layui-card-header">最新留言</div>
            <div class="layui-card-body">
                <ul class="lay-scroll" id="guestbook">
                </ul>
            </div>
        </div>
    </div>
    <div class="layui-col-sm6">
        <div class="layui-card">
            <div class="layui-card-header">最新公告</div>
            <div class="layui-card-body">
                <ul class="lay-scroll" style="height: 500px" id="announce">
                </ul>
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

        $.ajax({
            type: "post",
            url: ctx + "/announcement/show",
            dataType: "json",
            success:function (data) {
                var ul = document.getElementById("announce");
                for (var i = 0; i < data.length; i++) {
                    var li = document.createElement("li");
                    var h3 = document.createElement("h3");
                    var p = document.createElement("p");
                    var span = document.createElement("span");
                    h3.innerHTML = data[i];
                    p.innerHTML = data[++i];
                    span.innerHTML = data[++i];
                    span.style.color = "#999";
                    span.style.paddingRight = "350px";
                    span.style.paddingBottom = "5px";
                    h3.style.fontSize = "18px";
                    li.style.paddingBottom = "10px";
                    ul.style.height = "500px"
                    li.appendChild(h3);
                    li.appendChild(p);
                    li.appendChild(span);
                    ul.appendChild(li);
                    ul.style.overflowY = "scroll";
                }
            }
        });

        $.ajax({
            type: "post",
            url: ctx + "/guestbook/show",
            dataType: "json",
            success:function (data) {
                var ul = document.getElementById("guestbook");
                for (var i = 0; i < data.length; i++) {
                    var li = document.createElement("li");
                    var h3 = document.createElement("h3");
                    var p = document.createElement("p");
                    var span = document.createElement("span");
                    h3.innerHTML = data[i];
                    p.innerHTML = data[++i];
                    span.innerHTML = data[++i];
                    span.style.color = "#999";
                    span.style.paddingRight = "350px";
                    span.style.paddingBottom = "5px";
                    h3.style.fontSize = "18px";
                    li.style.paddingBottom = "10px";
                    ul.style.height = "500px"
                    li.appendChild(h3);
                    li.appendChild(p);
                    li.appendChild(span);
                    ul.appendChild(li);
                    ul.style.overflowY = "scroll";
                }
            }
        });

    });
</script>
</body>
</html>
