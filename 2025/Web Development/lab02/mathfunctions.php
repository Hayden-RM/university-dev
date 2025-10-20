<?php 

function factorial($number){

    $validation = is_valid($number);

    if($validation === true){

        $result =1;
        for ($i = 1; $i <= $number; $i++){
            $result *= $i;
        }
        echo "<p> The factorial of $number is $result</p>";

    }else{
        echo "<p> $number is an invalid input because $validation </p>";
    }


}

# Function to check if the integer is non-negative, integer
function is_valid($number) {
    $reasons = [];

    // Check if it's numeric and a whole number
    if (!is_numeric($number) || (int)$number != $number) {
        $reasons[] = "it is not an integer";
    }

    // Check if it's negative
    if ($number < 0) {
        $reasons[] = "it is negative";
    }

    if (empty($reasons)) {
        return true;
    } else {
        return implode(" and ", $reasons);
    }
}

# factorial(3); #Valid input
# factorial(12); #Valid input
# factorial(-3); #Invalid input (negative integer)
# factorial(-4.5); #Invalid input (negative and non-integer)

?>