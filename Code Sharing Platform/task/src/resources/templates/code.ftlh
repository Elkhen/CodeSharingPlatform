<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Code</title>
    <link href="/main.css" rel="stylesheet" />
    <link rel="stylesheet"
       href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <span id="load_date">${code.getDate()}</span><br>
    <#if code.isViewsRestricted()>
    <span id="views_restriction"><span style="text-decoration: underline;">${code.getViewsLeft()}</span> more views allowed</span><br>
    </#if>
    <#if code.isTimeRestricted()>
    <span id="time_restriction">The code will be available for <span style="text-decoration: underline;">${code.getTimeLeft()}</span> seconds</span>
    </#if>
    <pre class="codebox" id="code_snippet"><code>${code.getCode()}</code></pre>
</body>
</html>