<html>
<head>
	<title>Add score</title>
</head>

<body>
<h2>Add score</h2>
<form method="post" action="update_score_use_get_extended.php">
Eventid:  <input type="Text" name="eventId"          	size="4"><br>
PhaseId:  <input type="Text" name="phaseId"          	size="4"><br>
RoundId:  <input type="Text" name="roundId"          	size="4"><br>
Bouldernumber:  <input type="Text" name="boulderNumber"          	size="4"><br>
Startnumber:    <input type="Text" name="startNumber"          		size="4"><br>
Finished:       <input type="Text" name="finished"         		size="4"><br>
Topped:       	<input type="Text" name="topped"      			size="1"><br>
Top Attempts:  	<input type="Text" name="topAttempts" 			size="2"><br>
Bonussed:       <input type="Text" name="bonussed"         		size="1"><br>
Bonus Attempts: <input type="Text" name="bonusAttempts"      		size="2"><br>
Attempts:  	<input type="Text" name="attempts" 			size="2"><br>
<hr>
<input type="submit" value="Insert"><input type="Reset" value="Empty"><br>
<input type="Button" value="Cancel" onclick="javascript:history.back();">
</form>
</body>
</html>
