<!DOCTYPE html>
<html>
<head>
  <#include "../common.ftl">
</head>


<body class="childrenBody">

<div class="header">
    <img src="images/welcome.jpg" style="width:100%;height: 850px"/>
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
                    span.style.paddingLeft = "350px";
                    h3.style.fontSize = "18px";
                    li.style.paddingBottom = "10px";
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
                    var a = document.createElement("a");
                    a.href = "javascript:;";
                    a.className = "layui-btn layui-btn-xs";
                    a.innerHTML = "回复";
                    h3.innerHTML = data[i];
                    p.innerHTML = data[++i];
                    span.innerHTML = data[++i];
                    span.style.color = "#999";
                    span.style.paddingRight = "350px";
                    span.style.paddingBottom = "5px";
                    h3.style.fontSize = "18px";
                    li.style.paddingBottom = "10px";
                    li.appendChild(h3);
                    li.appendChild(p);
                    li.appendChild(span);
                    li.appendChild(a);
                    ul.appendChild(li);
                }
            }
        });

    });
</script>
</body>
</html>
