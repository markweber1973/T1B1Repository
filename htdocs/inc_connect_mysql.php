<?php 
// variabelen initialiseren:
// $username = "uw_gebruikersnaam";
// $password = "uw_wachtwoord";
$host="localhost";
$username="mark";
$password="mark";
$dbnaam="T1B1";
$db=mysql_connect($host, $username, $password) or die (mysql_error());
mysql_select_db($dbnaam, $db) or die (mysql_error());
?>