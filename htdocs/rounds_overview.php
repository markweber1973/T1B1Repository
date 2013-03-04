<?php 
    include("inc_connect_mysql.php");
?>

<?php 
    include("show_active_event.php");
?>

<?php 
// variabelen initialiseren
//=============================
$query= "SELECT * FROM rounds ORDER BY roundId;";
$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());;

$query= "SELECT * FROM activeround;"; 
$activeroundresult = mysql_query($query, $db);  
$rij = mysql_fetch_array($activeroundresult);    

$activeroundid = $rij['roundId'];
$query="SELECT * FROM rounds WHERE roundId =". $activeroundid;
$activeroundresult = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
$rij = mysql_fetch_array($activeroundresult);
$activerounddescription = $rij['name'];
    
// ============================
?>

<html>
<head>
<title>Rounds overview</title>
</head>

<body>
<!-- eerst de kolomkoppen voor de tabel in plain HTML schrijven -->
<table border="1" width="80%" align="center">
<tr>
<td colspan="6"><h2 align="center">Rounds. Currently active:  <?php echo $activerounddescription ?> </h2></td>
</tr>
<tr>
	<th>ID</th>
	<th>Name</th>
</tr>
<!-- Vanaf hier de PHP while()-lus. Elke lusdoorgang schrijft
	een tabelrij naar het scherm -->
<?php while ($rij = mysql_fetch_array($result)){
		echo ("<tr><td>" . $rij['roundId']   . " </td> " .
			      "<td>" . $rij['name']   . " </td> " . 
			      "<td><a href=\"delete_round.php?roundId=" .$rij['roundId'] . "\">Delete</a></td>" .
			      "<td><a href=\"modify_round.php?roundId=" .$rij['roundId'] . "\">Modify</a>" .
                  "<td><a href=\"activate_round.php?roundId=" .$rij['roundId'] . "\">Activate</a>" .
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

