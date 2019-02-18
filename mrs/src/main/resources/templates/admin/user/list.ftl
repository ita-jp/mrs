<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <title>Messages</title>
</head>
<body>

<div>
    <a href="/admin/user/new">New</a>
</div>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>first name</th>
        <th>last name</th>
        <th>role name</th>
    </tr>
    </thead>
    <tbody>
  <#list userList as user>
  <tr>
      <td>${user.userId}</td>
      <td>${user.firstName}</td>
      <td>${user.lastName}</td>
      <td>${user.roleName}</td>
  </tr>
  </#list>
    </tbody>
</table>

</body>
</html>