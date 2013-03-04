<?php 
    include("inc_connect_mysql.php");
?>

<?php 
    include("show_active_event.php");
    ?>

<?php 
// variabelen initialiseren
//=============================
$query= "SELECT * FROM climbers ORDER BY climberId;";
$result = mysql_query($query, $db);
// ============================
?>

<html>
<head>
<title>Climbers overview</title>
</head>

<body>
<!-- eerst de kolomkoppen voor de tabel in plain HTML schrijven -->
<table border="1" width="80%" align="center">
<tr>
	<td colspan="5"><h2 align="center">Climbers</h2></td>
</tr>
<tr>
	<th>ID</th>
	<th>First name</th>
	<th>Last name</th>
	<th>Nationality</th>
    <th>Sex</th>
	<th>Delete</th>
	<th>Modify</th>
    <th>Event</th>
    <th>Round</th>
</tr>
<!-- Vanaf hier de PHP while()-lus. Elke lusdoorgang schrijft
	een tabelrij naar het scherm -->
<?php while ($rij = mysql_fetch_array($result)){
		echo ("<tr><td>" . $rij['climberId']   . " </td> " .
			      "<td>" . $rij['firstname']   . " </td> " . 
                  "<td>" . $rij['lastname']    . " </td> " .
			      "<td>" . $rij['nationality'] . " </td> " .
			      "<td>" . $rij['sex']         . " </td> " .
			      "<td><a href=\"delete_climber.php?climberId=" . $rij['climberId'] ."\">Delete</a></td>" .
			      "<td><a href=\"modify_climber.php?climberId=" .$rij['climberId'] . "\">Modify</a>" .
                  "<td><a href=\"add_climber_to_event.php?climberId=" .$rij['climberId'] . "\">Add to</a>" . " / " . 
                      "<a href=\"remove_climber_from_event.php?climberId=" .$rij['climberId'] . "\">Remove from</a>".
                  "<td><a href=\"add_climber_to_round.php?climberId=" .$rij['climberId'] . "\">Add to</a>" . " / " .
                      "<a href=\"remove_from_round_climber.php?climberId=" .$rij['climberId'] . "\">Remove from</a>".             
			      "</td></tr>\n");
	}
?>
<!-- Einde van de lus, tabel afsluiten -->
</table>
<hr>
<!-- Eventueel rest van de pagina -->
<input type="Button" value="Cancel" onclick="javascript:history.back();">
<a href="T1B1_main.php">Main menu</a>
</body>
</html>

