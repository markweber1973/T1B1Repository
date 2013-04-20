<?php 
    include("inc_connect_mysql.php");
   
  
    
    
$query= "SELECT * FROM activeevent;"; 
$activeeventresult = mysql_query($query, $db);  
$rij = mysql_fetch_array($activeeventresult);    
$activeeventid = $rij['eventId'];

$query="SELECT * FROM events WHERE eventId =". $activeeventid;
$activeeventresult = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
$rij = mysql_fetch_array($activeeventresult);
$activeeventdescription = $rij['name'];


$query= "SELECT * FROM activephase;"; 
$activephaseresult = mysql_query($query, $db);  
$rij = mysql_fetch_array($activephaseresult);    
$activephaseid = $rij['phaseId'];

$query="SELECT * FROM phases WHERE phaseId =". $activephaseid;
$activephaseresult = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
$rij = mysql_fetch_array($activephaseresult);
$activephasedescription = $rij['name'];

$response = array();
$response["phaseId"] = $activephaseid;
$response["eventId"] = $activeeventid;
$response["phaseName"] = $activephasedescription;
$response["eventName"] = $activeeventdescription;

echo json_encode($response);

?>
