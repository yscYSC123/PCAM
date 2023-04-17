<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/html">
    <head>
        <title>记录管理</title>
        <#include "*/common.ftl">
    </head>
    <body class="childrenBody">
    <div class="content whisper-content">
                    <table width="75%" border="0" align="center">
                        <tr>
                            <td>
                                <form id="quiz-form" class="layui-form" action="#" method="post">
                                    <span>评分策略：得1分,得5分,得10分,等方式评测</span>
                                    <#list questions as question>
                                        <div class="layui-form-item">
                                            <span>${question.content}</span>
                                            <ol>
                                                <input type="radio" name="${question.id}${question.userOp}"  value="1">
                                                <label style="margin-right: 30px;margin-left: -20px">A:是</label>
                                                <input type="radio" name="${question.id}${question.userOp}" value="5">
                                                <label style="margin-right: 30px;margin-left: -20px">B:不确定</label>
                                                <input type="radio" name="${question.id}${question.userOp}" value="10">
                                                <label style="margin-right: 30px;margin-left: -20px">C:不是</label>
                                            </ol>
                                        </div>
                                        <hr>
                                    </#list>
                                    <hr>
                                    <input class="btns" type="button" name="submit" value="开始评分" onclick="scorequiz()">
                                    <input type="reset" name="reset" value="重新测试">
                                    你的测评成绩分析：<input id="inputValue" type="text" value="测试后看结果分析" style="color: red"/>
                                </form>
                            </td>
                        </tr>
                    </table>
    </div>
    <script>
        layui.use(['element', 'laypage', 'form'], function() {
            element = layui.element, laypage = layui.laypage, form = layui.form,$ = layui.jquery;

        });
    </script>
    <script>
        function scorequiz() {
            var arrayObj = new Array([9]);
            arrayObj[0] = $("input[name='1admin']:checked").val();
            arrayObj[1] = $("input[name='2admin']:checked").val();
            arrayObj[2] = $("input[name='3admin']:checked").val();
            arrayObj[3] = $("input[name='4admin']:checked").val();
            arrayObj[4] = $("input[name='5admin']:checked").val();
            arrayObj[5] = $("input[name='6admin']:checked").val();
            arrayObj[6] = $("input[name='7admin']:checked").val();
            arrayObj[7] = $("input[name='8admin']:checked").val();
            arrayObj[8] = $("input[name='9admin']:checked").val();
            arrayObj[9] = $("input[name='10admin']:checked").val();

            var count = 0;
            for (var i = 0; i < arrayObj.length; i++) {
                count += parseInt(arrayObj[i]);
            }
            console.log("------------>" + count);
            //设置文本
            if (count >= 90) {
                $(":text").val("分数：" + count + "分," + "典型的外向性格");
            } else if (count > 70 && count < 90) {
                $(":text").val("分数：" + count + "分," + "稍外向性格");
            } else if (count > 50 && count < 70) {
                $(":text").val("分数：" + count + "分," + "外内混合型性格");
            } else if (count > 30 && count < 50) {
                $(":text").val("分数：" + count + "分," + "典型的内向性格");
            } else if (count >= 0 && count < 30 ) {
                $(":text").val("分数：" + count + "分," + "极端的内向性格");
            }else {
                $(":text").val("你有未完成的题目，请完成后提交测试！");
            }
            if (count >= 0) {
                //发送ajax请求
                $.ajax({
                    url: ctx + '/pgtest/add?count='+count,
                    type: 'GET',
                    success: function (result) {
                        if (result.status = true) {
                            layer.closeAll();
                            layer.msg('测试成功', {time: 1 * 1000}, function () {
                            });
                        } else {
                            layer.closeAll();
                            layer.msg('测试失败', {time: 1 * 1000}, function () {
                            });
                        }
                    },
                    error: function (errorMsg) {
                        alert("数据异常！" + errorMsg);
                    },
                });

            }

        }
    </script>
    </body>
</html>