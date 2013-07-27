<?php 
    include("inc_connect_mysql.php");
    ?>

<?php


function encodeErrorInfoAndDie()
{
	global $response;
	$response["errorcode"] = mysql_errno();   	
    $response["success"] = 0;
    $response["errormessage"] = "Invalid query: ".$ifexistquery." sql error:".mysql_error();	 
    echo json_encode($response);    	
    die;
}  

function checkQuery($queryresult, $queryToCheck)
{
	global $response;
    if (mysql_errno() != 0)
    {
    	encodeErrorInfoAndDie();   	
    }
    else if (!$queryresult) 
    {
    	$response["success"] = 0;
    	$response["errorcode"] = 0;
    	$response["errormessage"] = "No results for query:".$queryToCheck;    	
	    $queryresult = "Invalid query: ".$queryToCheck." sql error:".mysql_error();
	    echo json_encode($response);	    
	    die;    
    }		
    else 
    {
    	$response["success"] = 1;
    	$response["errormessage"] = "Score successfully updated.";    
    	echo json_encode($response);  	   	
    }
}

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
 
    
    $ifexistquery = "SELECT 1 FROM scores WHERE ((eventId = $eventId) AND (phaseId = $phaseId) AND ".
    	"(roundId = $roundId) AND (boulderNumber = $boulderNumber) AND (startNumber = $startNumber))";
    $ifexistqueryresult = mysql_query($ifexistquery);
    if (mysql_errno() == 0)
    {
    	if (mysql_num_rows($ifexistqueryresult) == 0)
	    {
	        $query = "INSERT INTO scores (eventId, phaseId, roundId, boulderNumber, startNumber, finished, topped, topAttempts, bonussed, bonusAttempts, attempts) ".
	    	                      " VALUES ('$eventId', '$phaseId', '$roundId', '$boulderNumber', '$startNumber', '$finished', '$topped', '$topAttempts', ".
	    	                      "'$bonussed', '$bonusAttempts', '$attempts') "; 
	       
	    	$result = mysql_query($query);
	    	checkQuery($result, $query);   	
	    }
	    else
	    {
	        $query = "UPDATE scores SET finished = '$finished', topped = '$topped', topAttempts = '$topAttempts', bonussed = '$bonussed', ".
	             "bonusAttempts = '$bonusAttempts', attempts = '$attempts' WHERE ((eventId = $eventId) AND (phaseId = $phaseId) AND (roundId = $roundId)".
	             "AND (boulderNumber = $boulderNumber) AND (startNumber = $startNumber))";     	
	          
	    	$result = mysql_query($query);
	        checkQuery($result, $query);  	    	
	    }        	 	
    }   
    else 
    {    
		encodeErrorInfoAndDie();
    }

    echo json_encode($response);
?>
