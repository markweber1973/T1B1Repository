<?php 
    include("inc_connect_mysql.php");
?>

<?php

// controleren of pagina zichzelf heeft aangeroepen
// via hidden-field uit het formulier
if (isset($_POST["confirmed"])){
	// query samenstellen
	$query="UPDATE climbers SET
		firstname   = '". $_POST["firstname"]   . "', 
		lastname    = '". $_POST["lastname"]    . "', 
		nationality = '". $_POST["nationality"] . "', 
		sex         = '". $_POST["sex"]         . "' 
		WHERE climberId=" .$_POST["climberId"];
	$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
	echo("De volgende opdracht is uitgevoerd: <b>$query</b><br>\n");
	if ($result){
		echo ("Record number " .$_POST["climberId"] . " is modified<br>\n");
		echo ("<a href=\"climbers_overview.php\">Back to overview</a>");
	}
}else{
	// pagina heeft zichzelf nog niet aangeroepen, 
	// formulier tonen om gegevens te bewerken
		$query="SELECT * FROM climbers WHERE climberId=" . $_GET["climberId"];
		$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
?>
<html>
<head>
	<title>Modify climber data</title>
</head>
<body>
<h2>Modify this data?</h2>
<?php
// gegevens ophalen en toekennen aan tijdelijke variabelen
while ($rij = mysql_fetch_array($result)){
	$firstname   = $rij['firstname']; 
	$lastname    = $rij['lastname'];
	$nationality = $rij['nationality'];
	$sex         = $rij['sex'] ;
}?>
<form action="<?php echo($_SERVER["PHP_SELF"]);?>" method="post">
	<input type="hidden" name="confirmed" value="1">
	<input type="hidden" name="climberId" value="<?php echo($_GET["climberId"]);?>">
Firstname:	 <input type="text" name="firstname"   value="<?php echo($firstname);?>"   size="20"><br>
Lastname:	 <input type="text" name="lastname"    value="<?php echo($lastname);?>"    size="20"><br>
Nationality: <input type="text" name="nationality" value="<?php echo($nationality);?>" size="3"><br>
Sex:         <input type="text" name="sex"         value="<?php echo($sex);?>"         size="1""><br>
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
