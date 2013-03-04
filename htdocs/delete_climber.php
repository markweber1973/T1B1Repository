<?php 
    include("inc_connect_mysql.php");
?>

<?php
// controleren of pagina zichzelf heeft aangeroepen
// via hidden-field uit het formulier
if (isset($_POST["confirmed"])){
	$query="DELETE FROM climbers WHERE climberId=" .$_POST["climberId"];
	$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
	echo("De volgende opdracht is uitgevoerd: <b>$query</b><br>\n");
	if ($result){
		echo ("Climber with id " .$_POST["climberId"] . " has been deleted<br>\n");
		echo ("<a href=\"climbers_overview.php\">Terug naar het overzicht</a>");
	}
}else{
	// pagina heeft zichzelf nog niet aangeroepen, 
	// eerst om bevestiging vragen
    
	$query="SELECT * FROM climbers WHERE climberId=" .$_GET["climberId"];
	$result = mysql_query($query, $db) or die ("ERROR: " . mysql_error());
?>
<html>
<head>
	<title>Delete climber</title>
</head>
<body>
<h2>Do you want to delete this data?</h2>
<?php
while ($rij = mysql_fetch_array($result)){
	echo("ID = "         . $rij['climberId']   . "<br>\n");
	echo("Name = "       . $rij['firstname']   . " " . $rij['lastname'] . "<br>\n");
	echo("Nationality = ". $rij['nationality'] . "<br>\n");
	echo("Sex = "        . $rij['sex']         . "<br><hr>\n");
}?>
<form action="<?php echo($_SERVER["PHP_SELF"]);?>" method="post">
	<input type="hidden" name="confirmed" value="1">
	<input type="hidden" name="climberId" value="<?php echo($_GET["climberId"]);?>">
	<input type="Submit" value="Delete">
	<input type="Button" value="Cancel" onclick="javascript:history.back();">
</form>
<?php
// else-blok correct afsluiten
}
?>
</body>
</html>
