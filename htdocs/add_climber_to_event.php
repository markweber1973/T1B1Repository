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
        // query samenstellen
        $query="SELECT * FROM eventenrollment WHERE eventId=".getActiveEventId()." AND climberId=  ".$_POST["climberId"];
        $result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
        if (mysql_num_rows($result) == 0) 
        {
            $query="INSERT INTO eventenrollment (eventId, climberId, startNumber) VALUES ('".getActiveEventId()."', '".$_POST["climberId"]."', '".$_POST["startNumber"]."');";
            $result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
            echo ("Added to event");
        }
        else
        {
            echo ("Already present in event");
        }
            

    }else{
        // pagina heeft zichzelf nog niet aangeroepen, 
        // formulier tonen om gegevens te bewerken
		$query="SELECT * FROM climbers WHERE climberId=" . $_GET["climberId"];
		$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
        echo getActiveEventId();
    ?>
<html>
<head>
<title>Add climber to event</title>
</head>
<body>
<h2>Add this climber to the active event?</h2>
<?php
    // gegevens ophalen en toekennen aan tijdelijke variabelen
    while ($rij = mysql_fetch_array($result)){
        $firstname   = $rij['firstname']; 
        $lastname    = $rij['lastname'];
        $nationality = $rij['nationality'];
        $sex         = $rij['sex'] ;
    }?>
<form action="<?php echo($_SERVER["PHP_SELF"]);?>" method="post">
<input type="hidden" name="confirmed" value="1">
<input type="hidden" name="climberId" value="<?php echo($_GET["climberId"]);?>">
Firstname:	 <input type="text" name="firstname"   value="<?php echo($firstname);?>"   size="20" readonly="readonly"><br>
Lastname:	 <input type="text" name="lastname"    value="<?php echo($lastname);?>"    size="20" readonly="readonly"><br>
Nationality: <input type="text" name="nationality" value="<?php echo($nationality);?>" size="3"  readonly="readonly"><br>
Sex:         <input type="text" name="sex"         value="<?php echo($sex);?>"         size="1"  readonly="readonly"><br>
Startnumber: <input type="text" name="startNumber" value="<?php echo($startNumber);?>" size="3"><br>
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
