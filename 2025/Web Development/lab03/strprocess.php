<?php

    if(!isset($_POST['inputstr'])){
        echo "No input recieved. <a href='strform.html'>Go back </a>";
        exit;
    }

    $input = $_POST['inputstr']; 

    // Validate user input
    if(!preg_match('/^[a-zA-Z\s]+$/', $input)) {
        echo "Error: Input must contain. <a href='strprocessform.html'>Go back</a>";
        exit;
    }

    // Remove all vowels
    $removed = preg_replace('/[aeiouAEIOU]/', '', $input);

    // Output result
    echo "Original string: {$input}";
    echo "</br >Processed string (without vowels): {$removed}";
    echo "</br ><a href='strprocessform.html'>Try Again</a>";

?> 

