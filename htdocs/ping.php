<?php 
    include("inc_connect_mysql.php");
    include("globals.php");
    ?>

<?php 
$response = array();
echo("OK dan\n"); 
    // required field is missing
    $response["success"] = 1;
  

    // echoing JSON response
    echo json_encode($response);
?>