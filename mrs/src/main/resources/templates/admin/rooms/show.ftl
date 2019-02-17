<#import "/spring.ftl" as spring/>
<#import "../common.ftl" as common/>
<!DOCTYPE html>
<html lang="ja">
<head>
   <meta charset="utf-8">
   <title>MRS - Rooms</title>
</head>
<body>

<div>
  <a href="/admin/rooms/">Back<a>
  <a href="/admin/rooms/${room.roomId}/edit">Edit</a>
  <form action="/admin/rooms/${room.roomId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <@common.myCsrf />
    <@common.myButton "Delete" />
  </form>
</div>

<div>
  <span>${room.roomId}</span>
  <span>${room.roomName}</span>
</div>

</body>
</html>