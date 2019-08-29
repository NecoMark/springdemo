<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>邮件模板</title>
        <style>
            td, th { border-width:1px; border-style: solid; }
        </style>
    </head>
    <body>
        <p></p>
        <table style="background: #E5E5E5; border-collapse: collapse;">
            <tr>
                <th>严重程度</th>
                <td>${ severity }</td>
                <th>优先级</th>
                <td>${ priority }</td>
                <th>工单处理人</th>
                <td>${ person }</td>
            </tr>
            <tr>
                <th>相关资产</th>
                <td>${ asset }</td>
                <th>资产所属人</th>
                <td>${ owner }</td>
                <th>工单状态</th>
                <td>${ status }</td>
            </tr>
            <tr>
                <th>问题详细描述</th>
                <td  colspan="5">${ desc }</td>
            </tr>
            <tr>
                <th>处理意见</th>
                <td colspan="5">${ solution }</td>
            </tr>
            <tr>
                <th>操作历史</th>
                <td colspan="5">${ operate }</td>
            </tr>
        </table>
    </body>
</html>