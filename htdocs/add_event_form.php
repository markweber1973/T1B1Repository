<html>
<head>
	<title>Add event</title>
</head>

<body>
<h2>Add event</h2>
<form method="post" action="add_event_sql.php">
Name:           <input type="Text" name="name"          size="20"><br>
Date:           <input type="Text" name="date"          size="20"><br>
Place:          <input type="Text" name="place"         size="20"><br>
Location:       <input type="Text" name="location"      size="20"><br>
International:  <input type="Text" name="international" size="1"><br>
<hr>
<input type="submit" value="Insert"><input type="Reset" value="Empty"><br>
<input type="Button" value="Cancel" onclick="javascript:history.back();">
</form>
</body>
</html>
