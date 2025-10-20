<?php

$values_to_test = [33.56, 10, -20, "sf234"];
$position_counter = 1;


function check_if_integer($value){

    global $position_counter;

    echo "<br />Value [$position_counter] in Array:";

    if(is_int($value)){
        echo "{$value} is an integer";
        if($value % 2 === 0){
            echo " and {$value} is also even";
        }else{
            echo " but {$value} is not even";
        }
    }else{
        echo "{$value} is not an integer";
    }

    $position_counter++; 
    
}

foreach ($values_to_test as $value){

    check_if_integer($value);

}

?> 