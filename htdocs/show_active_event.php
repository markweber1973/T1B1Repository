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
    
$query= "SELECT * FROM activephase;"; 
$activephaseresult = mysql_query($query, $db);  
$rij = mysql_fetch_array($activephaseresult);  
    
$activephaseid = $rij['phaseId'];
$query="SELECT * FROM phases WHERE phaseId =". $activephaseid;
$activephaseresult = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
$rij = mysql_fetch_array($activephaseresult);
$activephasedescription = $rij['name'];  

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

function getActivePhaseId()
{
    global $activephaseid;
    return $activephaseid;
} 
// ============================
?>

<html>
<head>
<title><?php echo $activeeventdescription; ?></title>
</head>

<body>
Active event: 
<?php echo $activeeventdescription; ?>
<br>Active round: 
<?php echo $activerounddescription; ?>
<br>Active phase: 
<?php echo $activephasedescription; ?>
</body>
</html>

