<?php 
    include("inc_connect_mysql.php");
?>

<?php 
    include("show_active_event.php");
    ?>

<?php 


$query= ("SELECT roundphaseenrollment.eventId, roundphaseenrollment.phaseId, roundphaseenrollment.roundId, roundphaseenrollment.sequence, roundenrollment.startNumber, roundenrollment.polePosition, climbers.firstname, climbers.lastname, climbers.sex".
        " FROM roundphaseenrollment JOIN roundenrollment ON (roundphaseenrollment.eventId=roundenrollment.eventId AND roundphaseenrollment.roundId=roundenrollment.roundId AND roundphaseenrollment.phaseId=roundenrollment.phaseId) ".
        " JOIN eventenrollment ON (eventenrollment.startNumber=roundenrollment.startNumber) ".
        " JOIN climbers ON (eventenrollment.climberId=climbers.climberId)".
		" WHERE (roundphaseenrollment.eventId='".getActiveEventId()."' AND roundphaseenrollment.phaseId='".getActivePhaseId()."') ");

echo $query;
$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());




// ============================
?>

<html>
<head>
<title>Active phase overview</title>
</head>

<body>
<!-- eerst de kolomkoppen voor de tabel in plain HTML schrijven -->
<table border="1" width="80%" align="center">
<tr>
	<td colspan="5"><h2 align="center">Climbers</h2></td>
</tr>
<tr>
    <th>Sequence</th>
	<th>Poleposition</th>
	<th>Startnumber</th>
	<th>First name</th>
	<th>Last name</th>
    <th>Sex</th>
</tr>
<!-- Vanaf hier de PHP while()-lus. Elke lusdoorgang schrijft
	een tabelrij naar het scherm -->
<?php while ($rij = mysql_fetch_array($result)){
		echo ("<tr><td>" . $rij['sequence']   . " </td> " .
		          "<td>" . $rij['polePosition']   . " </td> " . 
		          "<td>" . $rij['startNumber']   . " </td> " . 
			      "<td>" . $rij['firstname']   . " </td> " . 				  
                  "<td>" . $rij['lastname']    . " </td> " .
			      "<td>" . $rij['sex']         . " </td> " .                               
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

