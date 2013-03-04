<?php 
    include("inc_connect_mysql.php");
    ?>

<?php
    
    // controleren of pagina zichzelf heeft aangeroepen
    // via hidden-field uit het formulier
    if (isset($_POST["confirmed"])){
        // query samenstellen
        $query="DELETE FROM activeevent";
        $result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());

        $query="INSERT INTO activeevent (eventId) ";
        $query .= "VALUES ('"; // let op positie van de enkele aanhalingstekens          
        $query .= $_POST["eventId"] ."');" ;        
        
        $result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
        echo("De volgende opdracht is uitgevoerd: <b>$query</b><br>\n");
        if ($result){
            echo ("Record number " .$_POST["eventId"] . " is modified<br>\n");
            echo ("<a href=\"events_overview.php\">Back to overview</a>");
        }
    }else{
        // pagina heeft zichzelf nog niet aangeroepen, 
        // formulier tonen om gegevens te bewerken
		$query="SELECT * FROM activeevent WHERE eventId=" . $_GET["eventId"];
		$result = mysql_query($query, $db) or die ("FOUT: " . mysql_error());
        ?>
<html>
<head>
<title>Activate event</title>
</head>
<body>
<h2>Do you want to activate this event?</h2>

<form action="<?php echo($_SERVER["PHP_SELF"]);?>" method="post">
<input type="hidden" name="confirmed" value="1">
<input type="hidden" name="eventId" value="<?php echo($_GET["eventId"]);?>">
<input type="Submit" value="Activate">
<input type="Button" value="Cancel" onclick="javascript:history.back();">
</form>
<?php
    // else-blok correct afsluiten
    }
    ?>
</body>
</html>