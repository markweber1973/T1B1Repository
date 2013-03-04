<?php 
    include("inc_connect_mysql.php");
?>

<?php 
    include("show_active_event.php");
?>

<?php 
// variabelen initialiseren
//=============================
$query= "SELECT * FROM events ORDER BY eventId;";
$result = mysql_query($query, $db);

$query= "SELECT * FROM activeevent;"; 
$activeeventresult = mysql_query($query, $db);  
$rij = mysql_fetch_array($activeeventresult);    

$activeeventid = $rij['eventId'];
$query="SELECT * FROM events WHERE eventId =". $activeeventid;
$activeeventresult = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
$rij = mysql_fetch_array($activeeventresult);
$activeeventdescription = $rij['name'];
    
// ============================
?>

<html>
<head>
<title>Events overview</title>
</head>

<body>
<!-- eerst de kolomkoppen voor de tabel in plain HTML schrijven -->
<table border="1" width="80%" align="center">
<tr>
<td colspan="6"><h2 align="center">Events. Currently active:  <?php echo $activeeventdescription ?> </h2></td>
</tr>
<tr>
	<th>ID</th>
	<th>Name</th>
	<th>Date</th>
	<th>Place</th>
    <th>Location</th>
    <th>International</th>
	<th>Delete</th>
	<th>Modify</th>
    <th>Activate</th>
</tr>
<!-- Vanaf hier de PHP while()-lus. Elke lusdoorgang schrijft
	een tabelrij naar het scherm -->
<?php while ($rij = mysql_fetch_array($result)){
		echo ("<tr><td>" . $rij['eventId']   . " </td> " .
			      "<td>" . $rij['name']   . " </td> " . 
                  "<td>" . $rij['date']    . " </td> " .
			      "<td>" . $rij['place'] . " </td> " .
                  "<td>" . $rij['location'] . " </td> " .
			      "<td>" . $rij['international']         . " </td> " .
			      "<td><a href=\"delete_event.php?eventId=" .$rij['eventId'] . "\">Delete</a></td>" .
			      "<td><a href=\"modify_event.php?eventId=" .$rij['eventId'] . "\">Modify</a>" .
                  "<td><a href=\"activate_event.php?eventId=" .$rij['eventId'] . "\">Activate</a>" .
			      "</td></tr>\n");
	}
?>
<!-- Einde van de lus, tabel afsluiten -->
</table>
<hr>
<!-- Eventueel rest van de pagina -->
<input type="Button" value="Cancel" onclick="javascript:history.back();">
</body>
</html>

