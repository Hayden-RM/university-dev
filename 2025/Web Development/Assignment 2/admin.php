<?php
    header('Content-Type: application/json');
    //Pass db credentials
    require_once('sqlinfo2.inc.php');

    // Connect to db
    $conn = new mysqli($sql_host, $sql_user, $sql_pass, $sql_db);

    // Checks if connection is successful
    if($conn->connect_error){
         echo json_encode(['success' => false, 'message' => 'Database Connection failed']);
         exit();
    }


    //Assign request
    if(isset($_POST['assign'])){
        $assign_ref= trim($_POST['assign']);
        $stmt = $conn->prepare("UPDATE booking_requests SET status='assigned' WHERE booking_ref = ?");
        $stmt->bind_param("s", $assign_ref);

        if($stmt->execute()){
            echo json_encode(['success'=>true, 'message'=>"Booking request $assign_ref has been assigned!"]);
        }else{
            echo json_encode(['success'=>false, 'message'=>'Failed to assign booking']);
        }

        $stmt->close();
        $conn->close();
        exit();

    }

    // Search request functionality
    $bsearch = isset($_POST['bsearch']) ? trim($_POST['bsearch']) : "";

    //Build query
    if($bsearch !== ""){
        // Exact booking ref search
        $stmt = $conn->prepare("SELECT * FROM booking_requests WHERE booking_ref=?");
    $stmt->bind_param("s", $bsearch);
    } else {
        // Unassigned within 2 hours search
        $stmt = $conn->prepare("SELECT * FROM booking_requests WHERE status='unassigned' AND CONCAT(pickup_date, ' ', pickup_time) <= DATE_ADD(NOW(), INTERVAL 2 HOUR)");
    }

    $stmt->execute();
    $result = $stmt->get_result();

    $bookings = [];
    while($row = $result->fetch_assoc()){
        $bookings[] = $row;
    }

    if(count($bookings) > 0){
        echo json_encode(['success'=>true, 'bookings'=> $bookings]);

    }else{
        echo json_encode(['success'=>false, 'message'=>'No unassigned bookings within next 2 hrs found']);

    }

    $stmt->close();
    $conn->close();
    
?>