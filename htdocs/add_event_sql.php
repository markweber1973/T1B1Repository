<?php 
    include("inc_connect_mysql.php");
?>

<?php

if (empty($_POST)){
	// eerst controleren of pagina wel is aangeroepen vanuit formulier
	echo("Vul eerst <a href=\"oef_1201.php\">gegevens 
		voor de werknemer</a> in");
		exit();
}else{
         
	// OK, Query opbouwen met variabelen in $_POST
	// in het 'echt' eerst valideren of het formulier wel
	// geldige waarden bevat.
	$query="INSERT INTO events (name, date, place, location, international) ";
	$query .= "VALUES ('"; // let op positie van de enkele aanhalingstekens 
	$query .= $_POST["name"] ."', '" ;
	$query .= $_POST["date"] ."', '" ;
	$query .= $_POST["place"] ."', '" ;
	$query .= $_POST["location"] ."', '" ;         
	$query .= $_POST["international"] ."');" ;
         echo $query;
         echo $db;
	$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
         echo $result;
}
?>
<html>
<head>
	<title>Adding an event</title>
</head>
<body>
<?php

	echo("<hr><a href=\"add_event_form.php\">Add another event</a> |
		<a href=\"events_overview.php\">Overview off all events</a>");
?>
</body>
</html>