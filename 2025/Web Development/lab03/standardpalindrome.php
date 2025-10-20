<?php
    if (!isset($_POST['text'])) {
        echo "No input received. <a href='standardpalindromeform.html'>Go back</a>";
        exit;
    }

    $original = $_POST['text'];
    
    // Normalize input: remove non-alphanumeric characters and convert to lowercase
    $cleaned = preg_replace('/[^a-zA-Z0-9]/', '', $original);
    $normalized = strtolower($cleaned);

    // Reverse the cleaned string
    $reversed = strrev($normalized);

    if (strcmp($normalized, $reversed) === 0) {
        echo "<h2>Result</h2><p>'{$original}' <strong> is</strong> a perfect palindrome.</p>";
    } else {
        echo "<h2>Result</h2><p>'{$original}' <strong> is not</strong> a perfect palindrome.</p>";
    }

    echo "<p><a href='standardpalindromeform.html'>Try another</a></p>";
?>
