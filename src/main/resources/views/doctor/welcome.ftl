<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
    <style>
        * {
            margin: 0;
            padding: 0;
            border: 0;
            list-style: none;/* 去除li的点 */
        }
        body {
            margin: 0;
            padding: 0;
            font-family: "微软雅黑";
            font-size: 20px;
            overflow-x: hidden;/* 隐藏左右滚动条 */
        }
        #flash {/* id选择符前缀符号 */
            position: relative;
            width: 1200px;
            height: 500px;
            margin: 20px auto;
            border: 1px solid black;
        }
        #flash img {
            position: relative;
            width: 100%;
            height: 100%;
            left: 0px;
            top: 0px;
            display: none;
        }
        #flash ul {
            position: relative;
            width: 150px;
            height: 25px;
            border-radius: 20px;
            background-color: rgba(255, 255, 255, 0.5);
            left: 44%;
            bottom: 10%;
        }
        #flash ul li {
            width: 12px;
            height: 12px;
            margin-top: 5px;
            background-color: #fff;
            border-radius: 50%;
            margin-left: 15px;
            float: left;
        }
        #flash ul .li_1 {
            background-color: #ff0000;
        }
        #flash .span-r {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            position: absolute;
            right: 2%;
            top: 45%;
            background-color: rgba(255, 255, 255, 0.5);
        }
        #flash .span-r span {
            width: 100%;
            height: 100%;
            color: rgba(0, 0, 0, 0.5);
            font-size: xx-large;
            font-weight: 700;
            line-height: 50px;
            margin-left: 15px;
            cursor: pointer;
        }
        #flash .span-l {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            position: absolute;
            left: 2%;
            top: 45%;
            background-color: rgba(255, 255, 255, 0.5);
        }
        #flash .span-l span {
            width: 100%;
            height: 100%;
            color: rgba(0, 0, 0, 0.5);
            font-size: xx-large;
            font-weight: 700;
            line-height: 50px;
            margin-left: 15px;
            cursor: pointer;
        }
    </style>
    <script>
        window.onload = function() {
            var div = document.getElementById('flash');
            var img = div.getElementsByTagName('img'); /*选中div下所有的图片*/
            var li = point.getElementsByTagName('li');
            var div_r = flash.getElementsByTagName('div')[0];
            var div_l = flash.getElementsByTagName('div')[1];
            var len = img.length;
            var count = 0; /*设置count来显示当前图片的序号*/
            function run() { /*将定时器里的函数提取到外部*/
                count++;
                count = count == 5 ? 0 : count; /*当图片加载到最后一张时，使其归零*/
                for (var i = 0; i < len; i++) {
                    img[i].style.display = 'none'; /*利用for循环使除当前count位其他图片隐藏*/
                }
                img[count].style.display = 'block'; /*显示当前count的值所代表的图片*/
                for (var i = 0; i < li.length; i++) {
                    li[i].style.backgroundColor = "#fff"; /*原理同上*/
                }
                li[count].style.backgroundColor = "#f40";
            }
            var timer = setInterval(run, 2000); /*定义定时器，使图片每隔1s更换一次*/
            div.onmouseover = function() {
                clearInterval(timer);
            }
            div.onmouseleave = function() { /*定义鼠标移出事件，当鼠标移出div区域，轮播继续*/
                timer = setInterval(run, 2000);
            }
            for (var i = 0; i < len; i++) {
                li[i].index = i; /*定义index记录当前鼠标在li的位置*/
                li[i].onmouseenter = function() { /*定义鼠标经过事件*/
                    for (var i = 0; i < len; i++) { /*通过for循环将所有图片隐藏，圆点背景设为白色*/
                        li[i].style.background = '#fff';
                        img[i].style.display = 'none';
                    }
                    this.style.background = '#f40'; /*设置当前所指圆点的背景色*/
                    img[this.index].style.display = 'block'; /*使圆点对应的图片显示*/
                }
            }
            div_r.onclick = function() { /*因为span没有设置宽高，直接把事件添加到他的父级*/
                run(); /*直接调用现成的run函数*/
            }

            function reverse() {
                count--;
                count = count == -1 ? 4 : count;
                for (var i = 0; i < len; i++) {
                    img[i].style.display = 'none'; /*利用for循环使除当前count位其他图片隐藏*/
                }
                img[count].style.display = 'block'; /*显示当前count的值所代表的图片*/
                for (var i = 0; i < li.length; i++) {
                    li[i].style.backgroundColor = "#fff"; /*原理同上*/
                }
                li[count].style.backgroundColor = "#f40";
            }
            div_l.onclick = function() {
                reverse(); /*重新设置函数*/
            }
        }
    </script>
</head>


<body class="childrenBody">
<div class="banner">
    <div id="flash">
        <img src="images/1.jpg" alt="" style="display: block">
        <img src="images/2.jpg" alt="">
        <img src="images/3.jpg" alt="">
        <img src="images/4.jpg" alt="">
        <img src="images/5.jpg" alt="">
        <ul id="point">
            <li class="li_1"></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
        <div class="span-r" id="span-r">
            <span>></span>
        </div>
        <div class="span-l" id="span-l">
            <span><</span>
        </div>
    </div>
</div>
<div class="footer-wrap">
    <div class="footer">
        <div class="container" style="display: flex;margin-left: 700px;margin-top: 50px">
            <img src="images/1676968847401.png">
            <div class="text" style="margin-left: 20px">
                <h4 class="title">网站客服联系方式</h4>
                <p>微信<span class="WeChat">ysc</span></p>
                <p>手机<span class="iphone">XXXXXXXXXXX</span></p>
                <p>邮箱<span class="email">719122203@qq.com</span></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
