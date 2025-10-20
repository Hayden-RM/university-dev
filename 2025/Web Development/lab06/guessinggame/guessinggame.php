<?php
session_start();

// Generate a new number if it doesn't exist yet
if (!isset($_SESSION['number'])) {
    $_SESSION['number'] = rand(0, 100);
    $_SESSION['guesses'] = 0;
}

$message = "";
$guess = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $guess = $_POST['guess'] ?? '';

    if (!is_numeric($guess) || $guess < 0 || $guess > 100) {
        $message = "Please enter a valid number between 0 and 100.";
    } else {
        $_SESSION['guesses']++;

        if ($guess < $_SESSION['number']) {
            $message = "Your guess is too low.";
        } elseif ($guess > $_SESSION['number']) {
            $message = "Your guess is too high.";
        } else {
            $message = "Congratulations! You guessed the hidden number {$_SESSION['number']} in {$_SESSION['guesses']} attempts.";
        }
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Guessing Game</title>
</head>
<body>
    <h1>Guess the Number (0 - 100)</h1>

    <form method="post" action="guessinggame.php">
        <label for="guess">Enter your guess (0-100):</label>
        <input type="number" name="guess" id="guess" min="0" max="100" value="<?= htmlspecialchars($guess) ?>" required />
        <input type="submit" value="Guess" />
    </form>

    <p><strong>Number of attempts:</strong> <?= $_SESSION['guesses'] ?></p>
    <p><?= $message ?></p>

    <p><a href="giveup.php">Give Up</a> | <a href="startover.php">Start Over</a></p>
</body>
</html>
