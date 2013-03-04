<?php 
    include("inc_connect_mysql.php");
    ?>

<?php
    
        $query="SELECT value FROM globals WHERE name='activeevent'";
	$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
	$rij = mysql_fetch_array($result); 
      	$activeevent = $rij['value']; 
       

        $query="SELECT value FROM globals WHERE name='activeround'";
	$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
	$rij = mysql_fetch_array($result); 
      	$activeround = $rij['value']; 
      
?>

