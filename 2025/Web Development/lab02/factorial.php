<?php

include 'mathfunctions.php';

if(isset($_GET['number'])){

    $input = $_GET['number'];
    $number = (int)$input;
    $validation = is_valid($input);

    if($validation === true){
        
        $result = factorial($number);

    }else{ 
        echo "<p>$input is an invalid input to compute its factorial ";
    }

}else{
    echo "<p> No number was provided <p>";
}

?>