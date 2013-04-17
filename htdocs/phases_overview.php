<?php 
    include("inc_connect_mysql.php");
?>

<?php 
    include("show_active_event.php");
?>

<?php 
// variabelen initialiseren
//=============================
$query= "SELECT * FROM phases ORDER BY phaseId;";
$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());;
echo $query;

$query= "SELECT * FROM activephase;"; 
$activephaseresult = mysql_query($query, $db);  
$rij = mysql_fetch_array($activephaseresult);    
echo $query;

$activephaseid = $rij['phaseId'];
$query="SELECT * FROM phases WHERE phaseId =". $activephaseid;
echo $query;
$activephaseresult = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
$rij = mysql_fetch_array($activephaseresult);
$activephasedescription = $rij['name'];
echo $query;
// ============================
?>

<html>
<head>
<title>Phases overview</title>
</head>

<body>
<!-- eerst de kolomkoppen voor de tabel in plain HTML schrijven -->
<table border="1" width="80%" align="center">
<tr>
<td colspan="6"><h2 align="center">Phases. Currently active:  <?php echo $activephasedescription ?> </h2></td>
</tr>
<tr>
	<th>ID</th>
	<th>Name</th>
</tr>
<!-- Vanaf hier de PHP while()-lus. Elke lusdoorgang schrijft
	een tabelrij naar het scherm -->
<?php while ($rij = mysql_fetch_array($result)){
		echo ("<tr><td>" . $rij['phaseId']   . " </td> " .
			      "<td>" . $rij['name']   . " </td> " . 
			      "<td><a href=\"delete_phase.php?phaseId=" .$rij['phaseId'] . "\">Delete</a></td>" .
			      "<td><a href=\"modify_phase.php?phaseId=" .$rij['phaseId'] . "\">Modify</a>" .
                  "<td><a href=\"activate_phase.php?phaseId=" .$rij['phaseId'] . "\">Activate</a>" .		
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

