<#import "/spring.ftl" as spring/>
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
</div>

<div>
  <span>${room.roomId}</span>
  <span>${room.roomName}</span>
</div>

</body>
</html>