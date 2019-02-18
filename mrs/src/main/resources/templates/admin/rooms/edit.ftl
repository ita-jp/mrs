<#import "/spring.ftl" as spring/>
<#import "../common.ftl" as common/>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <title>MRS > Room > New</title>
    <!-- https://material.io/develop/web/docs/getting-started/ -->
    <link rel="stylesheet" href="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css">
</head>
<body class="mdc-typography">

<div>
    <a href="/admin/rooms/${room.roomId}">Back</a>
</div>

<form action="/admin/rooms/${room.roomId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
  <@common.myCsrf />
  <@common.myTextInput "roomName" "Room Name" "${room.roomName}"/>
  <@common.myButton "Edit"/>
</form>

<script type="text/javascript"
        src="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js"></script>
<script type="text/javascript">
    window.mdc.autoInit();
</script>
</body>
</html>