<?php 
    echo ("hiero");
    include("inc_connect_mysql.php");
?>

<?php

// controleren of pagina zichzelf heeft aangeroepen
// via hidden-field uit het formulier
if (isset($_POST["confirmed"])){
	// query samenstellen
	$query="UPDATE events SET
		name          = '". $_POST["name"]          . "', 
		date          = '". $_POST["date"]          . "', 
		place         = '". $_POST["place"]         . "', 
        location      = '". $_POST["location"]      . "',    
		international = '". $_POST["international"] . "' 
		WHERE eventId=" .$_POST["eventId"];
	$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
	echo("De volgende opdracht is uitgevoerd: <b>$query</b><br>\n");
	if ($result){
		echo ("Record number " .$_POST["eventId"] . " is modified<br>\n");
		echo ("<a href=\"events_overview.php\">Back to overview</a>");
	}
}else{
	// pagina heeft zichzelf nog niet aangeroepen, 
	// formulier tonen om gegevens te bewerken
		$query="SELECT * FROM events WHERE eventId=" . $_GET["eventId"];
		$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
?>
<html>
<head>
	<title>Modify event</title>
</head>
<body>
<h2>Modify this data:</h2>
<?php
// gegevens ophalen en toekennen aan tijdelijke variabelen
while ($rij = mysql_fetch_array($result)){
	$name         = $rij['name']; 
	$date         = $rij['date'];
	$place        = $rij['place'];
    $location      = $rij['location'];
    $international = $rij['international'];

}?>
<form action="<?php echo($_SERVER["PHP_SELF"]);?>" method="post">
	<input type="hidden" name="confirmed" value="1">
	<input type="hidden" name="eventId" value="<?php echo($_GET["eventId"]);?>">
Name:	        <input type="text" name="name"          value="<?php echo($name);?>"          size="20"><br>
Date:	        <input type="text" name="date"          value="<?php echo($date);?>"          size="20"><br>
Place:          <input type="text" name="place"         value="<?php echo($place);?>"         size="20"><br>
Location:       <input type="text" name="location"      value="<?php echo($location);?>"      size="20"><br>
International:  <input type="text" name="international" value="<?php echo($international);?>" size="1""><br>
<hr>
<input type="Submit" value="Modify">
<input type="Button" value="Cancel" onclick="javascript:history.back();">
</form>
<?php
// else-blok correct afsluiten
}
?>
</body>
</html>
