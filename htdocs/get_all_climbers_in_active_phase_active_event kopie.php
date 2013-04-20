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
     
// ============================
?>

<?php
$response = array();

$getallroundsquery= ("SELECT roundphaseenrollment.roundId, roundphaseenrollment.sequence, rounds.name".
        " FROM roundphaseenrollment JOIN rounds ON (roundphaseenrollment.roundId=rounds.roundId) ".
		" WHERE (roundphaseenrollment.eventId='".getActiveEventId()."' AND roundphaseenrollment.phaseId='".getActivePhaseId()."') ");

$result = mysql_query($getallroundsquery, $db) or die ("FOUT: " . mysql_error());
if (mysql_num_rows($result) > 0) 
{	


	while ($row = mysql_fetch_array($result)) 
    {

    }
    
}
    $response["roundDefinition"] = array();
    
    array_push($response["roundDefinition"], $roundenrollment); 
    
    while ($row = mysql_fetch_array($result)) 
    {

$query= ("SELECT roundphaseenrollment.roundId, roundphaseenrollment.sequence, roundenrollment.startNumber, roundenrollment.polePosition, climbers.firstname, climbers.lastname".
        " FROM roundphaseenrollment JOIN roundenrollment ON (roundphaseenrollment.eventId=roundenrollment.eventId AND roundphaseenrollment.roundId=roundenrollment.roundId AND roundphaseenrollment.phaseId=roundenrollment.phaseId) ".
        " JOIN eventenrollment ON (eventenrollment.startNumber=roundenrollment.startNumber) ".
        " JOIN climbers ON (eventenrollment.climberId=climbers.climberId)".
		" WHERE (roundphaseenrollment.eventId='".getActiveEventId()."' AND roundphaseenrollment.phaseId='".getActivePhaseId()."') ");

// array for JSON response

// get all round enrollments
$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());


// check for empty result
if (mysql_num_rows($result) > 0) {

    // looping through all results
    // products node
    $response["roundDefinition"] = array();
    
    while ($row = mysql_fetch_array($result)) 
    {
 
        $roundenrollment = array();
        $startnr = $row["startNumber"];
        $polepos = $row["polePosition"];
        $fname   = $row["firstname"];
        $lname   = $row["lastname"];
        $roundenrollment["startnumber"] = $startnr;
        $roundenrollment["poleposition"] = $polepos;         
        $roundenrollment["firstName"] = $fname;
        $roundenrollment["lastName"] = $lname; 
        $roundenrollment["sequence"] = $row["sequence"];
        $roundenrollment["roundId"] = $row["roundId"];

        array_push($response["roundDefinition"], $roundenrollment);
    }
    // success
    $response["success"] = 1;

    $response["activephasedescription"] = $activephasedescription;
    $response["activeeventdescription"] = $activeeventdescription;
    $response["activephaseid"] = $activephaseid;
    $response["activeeventid"] = $activeeventid;
    // echoing JSON response
    echo json_encode($response);
   
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No elements in roundenrollment found";

    // echo no users JSON
    echo json_encode($response);
}
?>
