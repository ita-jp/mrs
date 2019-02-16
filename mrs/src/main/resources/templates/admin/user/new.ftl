<#import "/spring.ftl" as spring/>
<#import "../common.ftl" as common/>
<!DOCTYPE html>
<html lang="ja">
<head>
   <meta charset="utf-8">
   <title>Messages</title>
   <!-- https://material.io/develop/web/docs/getting-started/ -->
   <link rel="stylesheet" href="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css">
</head>
<body class="mdc-typography">

<form action="/admin/user" method="POST">
  <@common.myCsrf />
  <@common.myTextInput "userId" "User ID"/>
  <@common.myTextInput "password" "Password"/>
  <@common.myTextInput "firstName" "First Name"/>
  <@common.myTextInput "lastName" "Last Name"/>
  <@common.mySelect "Role" "roleName" roleList/>
  <@common.myButton "Add"/>
</form>

<script type="text/javascript" src="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js"></script>
<script type="text/javascript">
  window.mdc.autoInit();
</script>
</body>
</html>