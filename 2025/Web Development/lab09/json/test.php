<?php
session_start();

$action = $_GET['action'];
$isbn = $_GET['isbn'] ?? null;

if (!isset($_SESSION['Cart'])) {
    $_SESSION['Cart'] = array();
}

if ($action == "Add" && $isbn) {
    if (!isset($_SESSION['Cart'][$isbn])) {
        $_SESSION['Cart'][$isbn] = 1;
    } else {
        $_SESSION['Cart'][$isbn]++;
    }
} elseif ($action == "Remove" && $isbn) {
    if (isset($_SESSION['Cart'][$isbn])) {
        $_SESSION['Cart'][$isbn]--;
        if ($_SESSION['Cart'][$isbn] <= 0) {
            unset($_SESSION['Cart'][$isbn]);
        }
    }
} elseif ($action == "Empty") {
    $_SESSION['Cart'] = array(); // Clear entire cart
}

// Return updated cart as JSON
echo json_encode($_SESSION['Cart']);
?>
