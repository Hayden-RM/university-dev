<?php
    if (!isset($_POST['text'])) {
        echo "No input received. <a href='perfectpalindrome.html'>Go back</a>";
        exit;
    }

    $original = $_POST['text'];
    $normalized = strtolower($original);
    $reversed = strrev($normalized);

    if (strcmp($normalized, $reversed) === 0) {
        echo "<h2>Result</h2><p>'{$original}' <strong> is</strong> a perfect palindrome.</p>";
    } else {
        echo "<h2>Result</h2><p>'{$original}' <strong> is not</strong> a perfect palindrome.</p>";
    }

    echo "<p><a href='perfectpalindrome.html'>Try another</a></p>";
?>
