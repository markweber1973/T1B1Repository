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
    
function getActivePhaseId()
{
    global $activephaseid;
    return $activephaseid;
}   
     
function checkQuery($queryresult, $queryToCheck)
{
	global $response;
    if (!$queryresult) 
    {
	    $queryresult = "Invalid query: ".$queryToCheck." sql error:".mysql_error();
	    $response["queryresult"] = $queryresult;	
	    echo json_encode($response);	    
	    die;    
    }	
    else if (mysql_num_rows($queryresult) == 0)
    {
    	$response["queryresult"] = "No results for query:".$queryToCheck;	
    	echo json_encode($response);  	
		die;    	
    }
}

// ============================
?>

<?php

mysql_query('SET CHARACTER SET utf8');

$response = array();

$getallroundsquery= ("SELECT roundphaseenrollment.roundId, roundphaseenrollment.sequence, rounds.name, rounds.nrofboulders, rounds.boulderprefix".
        " FROM roundphaseenrollment JOIN rounds ON (roundphaseenrollment.roundId=rounds.roundId) ".
		" WHERE (roundphaseenrollment.eventId='".getActiveEventId()."' AND roundphaseenrollment.phaseId='".getActivePhaseId()."') ");

$result = mysql_query($getallroundsquery, $db);
checkQuery($result, $getallroundsquery);

$queryresult = "OK";
$response["queryresult"] = $queryresult;
$response["activeeventid"] = $activeeventid;
$response["activephaseid"] = $activephaseid;
$response["activephasedescription"] = $activephasedescription;
$response["activeeventdescription"] = $activeeventdescription;
$response["phasedefinition"] = array();

	
while ($row = mysql_fetch_array($result)) 
{
	// Read the roundId from the query result
	$roundId = $row["roundId"];    	
    	
	// Create new array for storing a round
	$rounddefinition = array();    	
    	
	// Fill the meta data of this round
	$rounddefinition["roundid"] = $roundId;
	$rounddefinition["roundname"] = $row["name"];
	$rounddefinition["roundsequence"] = $row["sequence"];  
	$rounddefinition["roundnrofboulders"] = $row["nrofboulders"];
	$rounddefinition["roundboulderprefix"] = $row["boulderprefix"];      	  	

  	// Define the query for retrieving all round enrollments   	
	$getroundcontentquery= ("SELECT roundenrollment.startNumber, roundenrollment.polePosition, climbers.firstname, climbers.lastname".
		" FROM roundphaseenrollment JOIN roundenrollment ON (roundphaseenrollment.eventId=roundenrollment.eventId AND roundphaseenrollment.roundId=roundenrollment.roundId AND roundphaseenrollment.phaseId=roundenrollment.phaseId) ".
		" JOIN eventenrollment ON (eventenrollment.startNumber=roundenrollment.startNumber) ".
		" JOIN climbers ON (eventenrollment.climberId=climbers.climberId)".
		" WHERE (roundenrollment.roundId='".$roundId."' AND roundenrollment.eventId='".getActiveEventId()."' AND roundenrollment.phaseId='".getActivePhaseId()."') ");

	$innerresult = mysql_query($getroundcontentquery, $db);
	checkQuery($innerresult, $getroundcontentquery);
		
	// Create an array to store the round enrollments
	$rounddefinition["roundenrollments"] = array();
	while ($innerrow = mysql_fetch_array($innerresult)) 
	{	       	  		
		$roundenrollment = array();   		
		$roundenrollment["startnumber"] = $innerrow["startNumber"];
		$roundenrollment["poleposition"] = $innerrow["polePosition"];
		$roundenrollment["firstname"] = $innerrow["firstname"];
		$roundenrollment["lastname"] = $innerrow["lastname"];     
		array_push($rounddefinition["roundenrollments"], $roundenrollment);  		
	}	
	array_push($response["phasedefinition"], $rounddefinition);   	
}    

echo json_encode($response);

?>
