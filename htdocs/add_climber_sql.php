<?php 
    include("inc_connect_mysql.php");
?>

<?php


         
	// OK, Query opbouwen met variabelen in $_POST
	// in het 'echt' eerst valideren of het formulier wel
	// geldige waarden bevat.
	$query="INSERT INTO climbers (firstname, lastname, nationality, sex) ";
	$query .= "VALUES ('"; // let op positie van de enkele aanhalingstekens 
	$query .= $_POST["firstname"] ."', '" ;
	$query .= $_POST["lastname"] ."', '" ;
	$query .= $_POST["nationality"] ."', '" ;
	$query .= $_POST["sex"] ."');" ;
         echo $query;
         echo $db;
	$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
         echo $result;

?>
<html>
<head>
	<title>Adding a climber</title>
</head>
<body>
<?php

	echo("<hr><a href=\"add_climber_form.php\">Add another climber</a> |
		<a href=\"climbers_overview.php\">Overview off all climbers</a>");
?>
</body>
</html>