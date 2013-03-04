<?php 
    include("inc_connect_mysql.php");
    include("globals.php");
    ?>

<?php

    $response = array();
   
    $boulderNumber = $_POST['boulderNumber'];
    $startNumber = $_POST['startNumber'];
    $finished = $_POST['finished'];
    $topped = $_POST['topped'];
    $topAttempts = $_POST['topAttempts'];
    $bonussed = $_POST['bonussed'];
    $bonusAttempts = $_POST['bonusAttempts'];
    $attempts = $_POST['attempts'];
  
    // mysql update row with matched pid
    $query = "UPDATE scores SET finished = '$finished', topped = '$topped', topAttempts = '$topAttempts', bonussed = '$bonussed', ".
             "bonusAttempts = '$bonusAttempts', attempts = '$attempts' WHERE ((eventId = $activeevent) AND (roundId = $activeround) ".
             "AND (boulderNumber = $boulderNumber) AND (startNumber = $startNumber))";
    
    echo($query);
    $result = mysql_query($query) or die ("error");
    if (mysql_affected_rows() == 0)
    {
        echo("Nothing updated, try to insert new one");
        $query = "INSERT INTO scores (eventId, roundId, boulderNumber, startNumber, finished, topped, topAttempts, bonussed, bonusAttempts, attempts) ".
    	                      " VALUES ('$activeevent', '$activeround', '$boulderNumber', '$startNumber', '$finished', '$topped', '$topAttempts', ".
    	                      "'$bonussed', '$bonusAttempts', '$attempts') ";
    	                    
        echo $query;
    	$result = mysql_query($query);
    }
    // check if row inserted or not
    if ($result) 
    {
	    echo $result;
  	    echo("OK dan\n"); 
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Score successfully updated.";
        
        // echoing JSON response
        echo json_encode($response);
    } 
    else 
    {  	   	
        echo $result;
        echo("Helaas!\n"); 
    }

?>
