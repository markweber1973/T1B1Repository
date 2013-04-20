<?php 
    include("inc_connect_mysql.php");
?>

<?php 
    include("show_active_event.php");
    ?>
    
<html>
<head>
<title>T1B1 Database</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h2>Bouldering competition database</h2>
<table width="90%" border="1">
  <tr>
    <td><p><strong>Make your selection</strong></p>
      <ul>
        <li><a href="add_climber_form.php">Add climber</a></li>
        <li><a href="male_climbers_overview.php">Male: Overview / modify climbers</a></li>
        <li><a href="female_climbers_overview.php">Female: Overview / modify climbers</a></li>
        <li><a href="add_event_form.php">Add event</a></li>
        <li><a href="events_overview.php">Overview / modify events</a></li>
        <li><a href="rounds_overview.php">Overview / modify rounds</a></li>
        <li><a href="phases_overview.php">Overview / modify phases</a></li>
	    <li><a href="add_score_form.php">Add a score</a></li>
	    <li><a href="globals.php">Globals</a></li>
	    <li><a href="active_round_overview.php">Active round overview</a></li>
	    <li><a href="active_event_overview.php">Active event overview</a></li>
	    <li><a href="active_phase_overview.php">Active phase overview</a></li>
	    <li><a href="get_all_climbers_in_active_round_active_event.php">Overview of active round</a></li>
	    <li><a href="show_phase_definition.php">Overview of active phase definition</a></li>
      </ul>
</td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>
