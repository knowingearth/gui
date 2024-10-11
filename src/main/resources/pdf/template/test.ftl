<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>测试模板</title>
        <style>
            .banner {
                width: 100%;
                height: 150px;
                text-align: center;
                font: normal normal bold 50px "微软雅黑";
                line-height: 150px;
                color: #ffb171;
                /*background-color: #dd9999;*/
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
                background: #dd9999 url("data:image/jpg;base64,${bgImg}") no-repeat fixed 50% 10%;
            }
            .content {
                padding-top: 20px;
            }
            .bg-color {
                background-color: #ffdddd;
            }
            
            table {
                border-collapse: collapse;
                border-spacing: 0;
                margin: 0;
                padding: 0;
                border: none;
                width: 100%;
            }
            table th,td {
                padding: 3px;
                text-align: center;
                border: 1px solid #aaa;
            }
            table th {
                font-weight: bold;
                background-color: #dd3333;
                color: white;
                border-color: #ffdddd;
            }

        </style>
    </head>
    <body>
        <div class = "banner">
            <img src="data:image/png;base64,${img}" alt="LOGO" width="45" height="45" />
            中国企业股份有限公司
        </div>
        <div class = "content">
            <table>
                <tr>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>地址</th>
                </tr>
                <tr>
                    <td>${name}</td>
                    <td>${gender}</td>
                    <td>${age}</td>
                    <td>${address}</td>
                </tr>
            </table>
            <div style="padding-top: 20px;"><b>数据表格</b></div>
            <table>
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>列1</th>
                        <th>列2</th>
                        <th>列3</th>
                        <th>列4</th>
                        <th>列5</th>
                        <th>列6</th>
                        <th>列7</th>
                        <th>列8</th>
                        <th>列9</th>
                    </tr>
                </thead>
                <tbody>
                    <#if listDatas?has_content>
                        <#list listDatas as data>
                            <tr <#if (data_index + 1)%3==0>class="bg-color"</#if>>
                                <td>${data_index + 1}</td>
                                <td>${data.a!"-"}</td>
                                <td>${data.b!"-"}</td>
                                <td>${data.c!"-"}</td>
                                <td>${data.d!"-"}</td>
                                <td>${data.e!"-"}</td>
                                <td>${data.f!"-"}</td>
                                <td>${data.g!"-"}</td>
                                <td>${data.h!"-"}</td>
                                <td>${data.i!"-"}</td>
                            </tr>
                        </#list>
                    </#if>
                </tbody>
                <tfoot>
                    <tr>
                        <td>总数</td>
                        <td colspan="9" style="text-align: left;">${listDatasTotal}</td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </body>
</html>