<html>
<head>
	<title>Add climber</title>
</head>

<body>
<h2>Add climber</h2>
<form method="post" action="add_climber_sql.php">
First name: <input type="Text" name="firstname" size="20"><br>
Last name: <input type="Text" name="lastname" size="20"><br>
Nationality:  <input type="Text" name="nationality" size="3"><br>

<div class="field form-inline radio">
  <label class="radio" for="sex">Sex</label>
  <input class="radio" type="radio" name="sex" value="M" checked /> <span>Male</span>
  <input class="radio" type="radio" name="sex" value="F" /> <span>Female</span>
</div>

<hr>
<input type="submit" value="Insert"><input type="Reset" value="Empty"><br>
<input type="Button" value="Cancel" onclick="javascript:history.back();">
</form>
</body>
</html>
