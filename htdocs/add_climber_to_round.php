<?php 
    include("inc_connect_mysql.php");
    ?>

<?php 
    include("show_active_event.php");
    ?>

<?php
    // controleren of pagina zichzelf heeft aangeroepen
    // via hidden-field uit het formulier
    if (isset($_POST["confirmed"])){
            

        $query="INSERT INTO roundenrollment (eventId, startNumber, roundId, polePosition) VALUES ('".getActiveEventId()."', '".$_POST["startNumber"]."', '".getActiveRoundId(). "', '".$_POST["polePosition"]."');";
        $result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
        echo ("Added to round");

            

    }else{
        // pagina heeft zichzelf nog niet aangeroepen, 
        // formulier tonen om gegevens te bewerken
        
        $startNumberQuery="SELECT * FROM eventenrollment WHERE climberId=" . $_GET["climberId"] ." AND eventId=  ".getActiveEventId();
        $startNumberResult = mysql_query($startNumberQuery, $db) or die ("FOUT: " . mysql_error());
        if (mysql_num_rows($startNumberResult) == 0) 
        {
        	echo ("Climber not enrolled in event");
        }
        else 
        {
        	echo ("Climber is enrolled in event");
        	$rij = mysql_fetch_array($startNumberResult);
        	$startNumber = $rij['startNumber'] ;
        	echo $startNumber;
        }
   
   
		$query="SELECT * FROM climbers WHERE climberId=" . $_GET["climberId"];
		$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
   
    ?>
<html>
<head>
<title>Add climber to round</title>
</head>
<body>
<h2>Add this climber to the active round?</h2>
<?php
    // gegevens ophalen en toekennen aan tijdelijke variabelen
    while ($rij = mysql_fetch_array($result)){
        $firstname   = $rij['firstname']; 
        $lastname    = $rij['lastname'];
        $nationality = $rij['nationality'];
        $sex         = $rij['sex'] ;
        $climberId   = $rij['climberId'] ;
       
    }?>
<form action="<?php echo($_SERVER["PHP_SELF"]);?>" method="post">
<input type="hidden" name="confirmed" value="1">
Id           : <input type="text" name="climberId"    value="<?php echo($climberId);?>"    size="3"  readonly="readonly"><br>
Startnumber  : <input type="text" name="startNumber"  value="<?php echo($startNumber);?>"  size="3"  readonly="readonly"><br>
Firstname    : <input type="text" name="firstname"    value="<?php echo($firstname);?>"    size="20" readonly="readonly"><br>
Lastname     : <input type="text" name="lastname"     value="<?php echo($lastname);?>"     size="20" readonly="readonly"><br>
Nationality  : <input type="text" name="nationality"  value="<?php echo($nationality);?>"  size="3"  readonly="readonly"><br>
Sex          : <input type="text" name="sex"          value="<?php echo($sex);?>"          size="1"  readonly="readonly"><br>
Poleposition : <input type="text" name="polePosition" value="<?php echo($polePosition);?>" size="3"><br>
<hr>
<input type="Submit" value="Add">
<input type="Button" value="Cancel" onclick="javascript:history.back();">
</form>
<?php
    // else-blok correct afsluiten
    }
    ?>
</body>
</html>
