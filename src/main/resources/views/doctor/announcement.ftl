<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
        <div class="layui-col-md12">
            <div class="layui-form">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <ul class="lay-scroll" id="announcement">
                        </ul>
                    </div>
                </div>
            </div>
        </div>


    <script>
        layui.use(['jquery','form'], function() {
            var $ = layui.jquery,form = layui.form;

            $.ajax({
                type: "post",
                url: ctx + "/announcement/show",
                dataType: "json",
                success:function (data) {
                    var ul = document.getElementById("announcement");
                    for (var i = data.length-1; i > 1; i--) {
                        var li = document.createElement("li");
                        var h3 = document.createElement("h3");
                        var p = document.createElement("p");
                        var span = document.createElement("span");
                        var hr = document.createElement("hr");
                        h3.innerHTML = data[i];
                        p.innerHTML = data[--i];
                        span.innerHTML = data[--i];
                        h3.style.color = "#999";
                        span.style.paddingRight = "350px";
                        span.style.paddingBottom = "5px";
                        span.style.fontSize = "18px";
                        li.style.paddingBottom = "10px";
                        li.appendChild(span);
                        li.appendChild(p);
                        li.appendChild(h3);
                        li.appendChild(hr);
                        ul.appendChild(li);
                    }
                }
            });
        });
    </script>

</body>
</html>