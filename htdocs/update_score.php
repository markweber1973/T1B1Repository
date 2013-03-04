<?php 
    include("inc_connect_mysql.php");
    include("globals.php");
    ?>

<?php

/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['boulderNumber']) && isset($_POST['startNumber']) && isset($_POST['finished']) && isset($_POST['topped']) && isset($_POST['topAttempts']) && isset($_POST['bonussed']) && isset($_POST['bonusAttempts']) && isset($_POST['attempts'])) {
    
    echo("Daar gaat ie dan\n");

    $boulderNumber = $_POST['boulderNumber'];
    $startNumber = $_POST['startNumber'];
    $finished = $_POST['finished'];
    $topped = $_POST['topped'];
    $topAttempts = $_POST['topAttempts'];
    $bonussed = $_POST['bonussed'];
    $bonusAttempts = $_POST['bonusAttempts'];
    $attempts = $_POST['attempts'];

    $query = "UPDATE scores SET finished = '$finished', topped = '$topped', topAttempts = '$topAttempts', bonussed = '$bonussed', bonusAttempts = '$bonusAttempts', attempts = '$attempts' WHERE ((eventId = $activeevent) AND (roundId = $activeround) AND (boulderNumber = $boulderNumber) AND (startNumber = $startNumber))";
    echo $query;

    // mysql update row with matched pid
    $result = mysql_query("UPDATE scores SET finished = '$finished', topped = '$topped', topAttempts = '$topAttempts', bonussed = '$bonussed', bonusAttempts = '$bonusAttempts', attempts = '$attempts' WHERE ((eventId = $activeevent) AND (roundId = $activeround) AND (boulderNumber = $boulderNumber) AND (startNumber = $startNumber))");

    // check if row inserted or not
    if ($result) {
	echo $result;
  	echo("OK dan\n"); 
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";
        
        // echoing JSON response
        echo json_encode($response);
    } else {
echo $result;
       echo("Helaas!\n"); 
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
