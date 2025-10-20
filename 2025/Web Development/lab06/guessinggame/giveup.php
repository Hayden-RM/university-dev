<?php
session_start();

if (isset($_SESSION['number'])) {
    $number = $_SESSION['number'];
    echo "<p>The number was: <strong>$number</strong></p>";
    echo '<p><a href="startover.php">Start Over</a></p>';
} else {
    echo "<p>No game in progress. <a href='guessinggame.php'>Start a new game</a></p>";
}
?>
