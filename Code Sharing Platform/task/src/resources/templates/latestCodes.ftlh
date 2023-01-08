<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Latest</title>
    <link href="/main.css" rel="stylesheet" />
    <link rel="stylesheet"
       href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <table>
        <#list codes as code>
            <tr>
                <td>
                    <span id="load_date">${code.date}</span>
                    <pre class="codebox" id="code_snippet"><code>${code.code}</code></pre>
                </td>
            </tr>
        </#list>
    </table>
</body>
</html>