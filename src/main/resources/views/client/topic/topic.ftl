<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
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
                                            <span>${question.title}</span>
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
        layui.use(['form'], function() {
            var form = layui.form,$ = layui.jquery;

        });
    </script>
    </body>
</html>