<?php 
    include("inc_connect_mysql.php");
    include("globals.php");
?>

<?php

// array for JSON response
$response = array();
// get all round enrollments
$result = mysql_query("SELECT * FROM roundenrollment") or die(mysql_error());

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
        $startNumber = $row["startNumber"];
        //echo("startNumber = <b>$startNumber</b><br>\n");
  
        $polePosition = $row["polePosition"];
        //echo("polePosition = <b>$polePosition</b><br>\n");
        $roundenrollment["startnumber"] = $startNumber;
        $roundenrollment["poleposition"] = $polePosition;

        $eventErollmentResult = mysql_query("SELECT * FROM eventenrollment WHERE startNumber = $startNumber") or die(mysql_error());
        if (mysql_num_rows($eventErollmentResult) > 0) 
        {
        	$eventEnrollmentRow = mysql_fetch_array($eventErollmentResult);
        	$climberId = $eventEnrollmentRow["climberId"];
        	//echo("climberId = <b>$climberId</b><br>\n");
        	$climberNameResult = mysql_query("SELECT * FROM climbers WHERE climberId = $climberId") or die(mysql_error());
        	if (mysql_num_rows($climberNameResult) > 0)
        	{
        		$climberNameRow = mysql_fetch_array($climberNameResult);
        		$climberFirstName = $climberNameRow["firstname"];
        		$climberLastName = $climberNameRow["lastname"];
        		$roundenrollment["firstName"] = $climberFirstName;
        		$roundenrollment["lastName"] = $climberLastName;
        		//echo("climberFirstName = <b>$climberFirstName</b><br>\n");
        		//echo("climberLastName = <b>$climberLastName</b><br>\n");
        	}
        	else
        	{
                $response["success"] = 0;
                $response["message"] = "No climber matches climberId $climberId";
        		break;
        	}
        }
        else 
        {
        	echo("No elements in eventenrollment found for startNumber $startNumber");
        	break;
        }

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
