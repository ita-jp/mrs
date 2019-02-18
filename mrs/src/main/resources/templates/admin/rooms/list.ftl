<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="utf-8">
    <title>MRS - Rooms</title>
</head>
<body>

<div>
    <a href="/admin/">Back<a>
        <a href="/admin/rooms/new">New</a>
</div>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Room Name</th>
    </tr>
    </thead>
    <tbody>
  <#list roomList as room>
  <tr>
      <td>${room.roomId}</td>
      <td><a href="/admin/rooms/${room.roomId}">${room.roomName}</a></td>
  </tr>
  </#list>
    </tbody>
</table>

</body>
</html>