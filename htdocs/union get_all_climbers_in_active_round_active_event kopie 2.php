<?php 
    include("inc_connect_mysql.php");
?>

<?php 
    include("show_active_event.php");
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
    	//echo("mysql_fetch_array");
        // temp user array
        $roundenrollment = array();
        $roundenrollment["startnumber"] = $row["startnumber"];
        $roundenrollment["poleposition"] = $row["polePosition"];         
        $roundenrollment["firstName"] = $row["firstname"];
        $roundenrollment["lastName"] = $row["lastname"]; 

        
        echo $roundenrollment;

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
