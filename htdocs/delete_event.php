<?php 
    include("inc_connect_mysql.php");
?>

<?php
// controleren of pagina zichzelf heeft aangeroepen
// via hidden-field uit het formulier
if (isset($_POST["confirmed"])){
	$query="DELETE FROM events WHERE eventId=" .$_POST["eventId"];
	$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
	echo("De volgende opdracht is uitgevoerd: <b>$query</b><br>\n");
	if ($result){
		echo ("Event with id " .$_POST["eventId"] . " has been deleted<br>\n");
		echo ("<a href=\"events_overview.php\">Terug naar het overzicht</a>");
	}
}else{
	// pagina heeft zichzelf nog niet aangeroepen, 
	// eerst om bevestiging vragen
    
	$query="SELECT * FROM events WHERE eventId=" .$_GET["eventId"];
	$result = mysql_query($query, $db) or die ("ERROR: " . mysql_error());
?>
<html>
<head>
	<title>Delete event</title>
</head>
<body>
<h2>Do you want to delete this data?</h2>
<?php
while ($rij = mysql_fetch_array($result)){
	echo("ID = "           . $rij['eventId']        . "<br>\n");
	echo("Name = "         . $rij['name']           . "<br>\n");
    echo("Date = "         . $rij['date']           . "<br>\n");    
    echo("Place = "        . $rij['place']          . "<br>\n");    
    echo("Location = "     . $rij['location']       . "<br>\n");    
	echo("International = ". $rij['international']  . "<br><hr>\n");
}?>
<form action="<?php echo($_SERVER["PHP_SELF"]);?>" method="post">
	<input type="hidden" name="confirmed" value="1">
	<input type="hidden" name="eventId" value="<?php echo($_GET["eventId"]);?>">
	<input type="Submit" value="Delete">
	<input type="Button" value="Cancel" onclick="javascript:history.back();">
</form>
<?php
// else-blok correct afsluiten
}
?>
</body>
</html>
