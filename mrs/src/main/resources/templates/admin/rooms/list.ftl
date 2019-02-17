<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="ja">
<head>
   <meta charset="utf-8">
   <title>MRS - Rooms</title>
</head>
<body>

<div>
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
      <td>${room.roomName}</td>
    </tr>
  </#list>
  </tbody>
</table>

</body>
</html>