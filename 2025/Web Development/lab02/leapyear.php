<?php

    function is_leapyear($year){
        if(!is_numeric($year)){
            return false;
        }

        $year = (int)$year;

        if ($year % 4 === 0) {
            if ($year % 100 !== 0) {
                return true;
            } elseif ($year % 400 === 0) {
                return true;
            } else {
                return false; 
            }
        } else {
            return false;
        }


    }


    $year = $_GET['year'];

    if(!isset($year)) {
        echo 'No year provided. <a href="leapyearform.hmtl">Go back</a>.';
        exit(); 
    }elseif(!is_numeric($year)){
        echo "Error: '{$year}' is not a number. <a href='leapyearform.html'>Go back</a>.";
        exit();
    }elseif($year < 0){
        echo "Invalid input: '{$year}'. A year cannot be negative";
    }

    if(!is_leapyear($year)){
        echo "{$year} is a standard year. <a href='leapyearform.html'>Go back</a>.";
    }elseif(is_leapyear($year)){
        echo "{$year} is a leap year. <a href='leapyearform.html'>Go back</a>."; 
    }else { 
        echo "Error";
    }
        
?> 