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
     
// ============================
?>

<?php

$query= ("SELECT roundenrollment.startnumber, eventenrollment.climberId, climbers.firstname, climbers.lastname, climbers.sex, roundenrollment.polePosition".
        " FROM roundenrollment INNER JOIN eventenrollment ON (roundenrollment.startnumber=eventenrollment.startnumber) ".
        " INNER JOIN climbers ON (eventenrollment.climberId=climbers.climberId) ".
        " WHERE (roundenrollment.eventId='".getActiveEventId()."' AND roundenrollment.roundId='".getActiveRoundId()."') ".
        " ORDER BY roundenrollment.startnumber");

// array for JSON response
$response = array();
// get all round enrollments
$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
//$result = mysql_query($query) or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {

    // looping through all results
    // products node
    $response["roundDefinition"] = array();
    
    while ($row = mysql_fetch_array($result)) 
    {
 
        $roundenrollment = array();
        $startnr = $row["startnumber"];
        $polepos = $row["polePosition"];
        $fname   = $row["firstname"];
        $lname   = $row["lastname"];
        $roundenrollment["startnumber"] = $startnr;
        $roundenrollment["poleposition"] = $polepos;         
        $roundenrollment["firstName"] = $fname;
        $roundenrollment["lastName"] = $lname; 

        array_push($response["roundDefinition"], $roundenrollment);
    }
    // success
    $response["success"] = 1;

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
