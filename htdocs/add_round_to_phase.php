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
            

        $query="INSERT INTO roundphaseenrollment (eventId, phaseId, roundId, sequence) VALUES ('".getActiveEventId()."', '".getActivePhaseId()."', '".$_POST["roundId"]."', '".$_POST["sequence"]."');";
        $result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
		echo $query;
        echo ("Added to phase");

            

    }else{
        // pagina heeft zichzelf nog niet aangeroepen, 
        // formulier tonen om gegevens te bewerken
        
 
   
   
		$query="SELECT * FROM rounds WHERE roundId=" . $_GET["roundId"];
		$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
        echo $query;
    ?>
<html>
<head>
<title>Add round to phase</title>
</head>
<body>
<h2>Add this round to the active phase?</h2>
<?php
    // gegevens ophalen en toekennen aan tijdelijke variabelen
    while ($rij = mysql_fetch_array($result)){
        $roundId   = $rij['roundId']; 
        $name    = $rij['name'];
             
    }?>
<form action="<?php echo($_SERVER["PHP_SELF"]);?>" method="post">
<input type="hidden" name="confirmed" value="1">
Id                    : <input type="text" name="roundId"      value="<?php echo($roundId);?>"      size="3"  readonly="readonly"><br>
Name                  : <input type="text" name="name"         value="<?php echo($name);?>"         size="15"  readonly="readonly"><br>
Sequence (0=first)    : <input type="text" name="sequence"     value="<?php echo($sequence);?>" size="2"><br>
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
