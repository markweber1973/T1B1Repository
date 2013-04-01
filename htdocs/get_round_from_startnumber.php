<?php 
    include("inc_connect_mysql.php");
?>

<?php 

$query= "SELECT * FROM activeevent;"; 
$activeeventresult = mysql_query($query, $db);  
$rij = mysql_fetch_array($activeeventresult);    

$activeeventid = $rij['eventId'];
$query="SELECT * FROM events WHERE eventId =". $activeeventid;
$activeeventresult = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
$rij = mysql_fetch_array($activeeventresult);
$activeeventdescription = $rij['name'];
    
    
$query= "SELECT * FROM activeround;"; 
$activeroundresult = mysql_query($query, $db);  
$rij = mysql_fetch_array($activeroundresult);  
    
$activeroundid = $rij['roundId'];
$query="SELECT * FROM rounds WHERE roundId =". $activeroundid;
$activeroundresult = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
$rij = mysql_fetch_array($activeroundresult);
$activerounddescription = $rij['name'];    
    
function getActiveEventId()
{
    global $activeeventid;
    return $activeeventid;
}
    
function getActiveRoundId()
{
    global $activeroundid;
    return $activeroundid;
}   

function getRoundForStartnumber($startNumber, $database)
{
	$query="SELECT * FROM roundenrollment WHERE eventId = ".getActiveEventId()." AND startNumber = ".$startNumber;
	echo $query;
	
	$result = mysql_query($query) or die ("FOUT: " . mysql_error());
	
	$row = mysql_fetch_array($result);
	$tst = $row["roundId"];

	return $tst;
}

// ============================
?>

<?php    
	$roundId = getRoundForStartnumber(10, $db);
	echo $roundId;
?>