<?php 
    include("inc_connect_mysql.php");
    ?>

<?php


    $response = array();
   
    $eventId = $_POST['eventId'];
    $phaseId = $_POST['phaseId']; 
    $roundId = $_POST['roundId'];
    
    $boulderNumber = $_POST['boulderNumber'];
    $startNumber = $_POST['startNumber'];
    $finished = $_POST['finished'];
    $topped = $_POST['topped'];
    $topAttempts = $_POST['topAttempts'];
    $bonussed = $_POST['bonussed'];
    $bonusAttempts = $_POST['bonusAttempts'];
    $attempts = $_POST['attempts'];
     
    $query = "UPDATE scores SET finished = '$finished', topped = '$topped', topAttempts = '$topAttempts', bonussed = '$bonussed', ".
             "bonusAttempts = '$bonusAttempts', attempts = '$attempts' WHERE ((eventId = $eventId) AND (phaseId = $phaseId) AND (roundId = $roundId)".
             "AND (boulderNumber = $boulderNumber) AND (startNumber = $startNumber))";  
   
    
    $result = mysql_query($query) or die ("error");
    if (mysql_affected_rows() == 0)
    {      
        $query = "INSERT INTO scores (eventId, phaseId, roundId, boulderNumber, startNumber, finished, topped, topAttempts, bonussed, bonusAttempts, attempts) ".
    	                      " VALUES ('$eventId', '$phaseId', '$roundId', '$boulderNumber', '$startNumber', '$finished', '$topped', '$topAttempts', ".
    	                      "'$bonussed', '$bonusAttempts', '$attempts') ";     
      
    	$result = mysql_query($query);
    }
    // check if row inserted or not
    if ($result) 
    {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Score successfully updated.";    
    } 
    else 
    {  	   	
        // successfully updated
        $response["success"] = 0;
        $response["message"] = "Score not successfully updated.";
    }
    echo json_encode($response);
?>
