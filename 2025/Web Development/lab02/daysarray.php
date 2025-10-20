<?php

$days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

echo "<h3>The days of the week in English are: <br>";

$last_index = count($days) - 1;

foreach($days as $index => $day){
    echo $day;
    echo ($index == $last_index) ? ".":", ";
}

echo "<br>";

$days_in_french = ["Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Venredi", "Samedi"];

echo "<h3>The days of the week in French are: <br>";

$last_idnex_french = count($days_in_french) - 1; 

foreach($days_in_french as $index => $french_day){
    echo $french_day; 
    echo ($index == $last_index) ? ".":", ";

}

?>