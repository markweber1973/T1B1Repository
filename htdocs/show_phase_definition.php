<?php 
    include("inc_connect_mysql.php");
?>

<?php 
    include("show_active_event.php");
    ?>

<?php 


$query= ("SELECT roundphaseenrollment.roundId, roundphaseenrollment.sequence, rounds.name".
        " FROM roundphaseenrollment JOIN rounds ON (roundphaseenrollment.roundId=rounds.roundId) ".
		" WHERE (roundphaseenrollment.eventId='".getActiveEventId()."' AND roundphaseenrollment.phaseId='".getActivePhaseId()."') ");

echo $query;
$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());




// ============================
?>

<html>
<head>
<title>Active phase definition</title>
</head>

<body>
<!-- eerst de kolomkoppen voor de tabel in plain HTML schrijven -->
<table border="1" width="80%" align="center">
<tr>
	<td colspan="5"><h2 align="center">Rounds in current phase</h2></td>
</tr>
<tr>
	<th>name</th>
	<th>sequence</th>

</tr>
<!-- Vanaf hier de PHP while()-lus. Elke lusdoorgang schrijft
	een tabelrij naar het scherm -->
<?php while ($rij = mysql_fetch_array($result)){
		echo ("<tr><td>" . $rij['name']   . " </td> " .	
		          "<td>" . $rij['sequence']   . " </td> " .                                
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

