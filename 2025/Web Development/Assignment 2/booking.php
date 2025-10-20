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
            
                //script to create table if does not exist already
                $createTableSQL = "CREATE TABLE IF NOT EXISTS booking_requests (
                                id INT AUTO_INCREMENT PRIMARY KEY, 
                                booking_ref VARCHAR(10) NOT NULL UNIQUE,
                                customer_name VARCHAR(100) NOT NULL,
                                phone VARCHAR(12) NOT NULL,
                                unit_number VARCHAR(10), 
                                street_number VARCHAR(10) NOT NULL,
                                street_name VARCHAR(100) NOT NULL,
                                suburb VARCHAR(100), 
                                destination_suburb VARCHAR(100),
                                pickup_date DATE NOT NULL,
                                pickup_time TIME NOT NULL,
                                booking_date DATETIME NOT NULL,
                                status VARCHAR(20) DEFAULT 'unassigned'
                            )";

                // Parse script to MySQL db
                $conn->query($createTableSQL);

                function getParam($key){
                    return isset($_POST[$key]) ? trim($_POST[$key]) : '';
                }

                $cname = getParam('cname');
                $phone = getParam('phone');
                $unumber = getParam('unumber');
                $snumber = getParam('snumber');
                $stname = getParam('stname');
                $sbname = getParam('sbname');
                $dsbname = getParam('dsbname');
                $date = getParam('date');
                $time = getParam('time');

                // Validate required fields
                if ($cname === '' || $phone ==='' || $snumber === '' || $stname ==='' || $date ==='' || $time === ''){
                    echo json_encode(['success' => false, 'message' => 'Required fields missing']);
                    exit();
                }

                // Convert date to yyy-mm-dd for MySQL
                $dateParts =explode('/', $date);
                if(count($dateParts) !== 3){
                    echo json_encode(['success' => false, 'message' => 'Invalid date format']);
                    exit();
                }
                // Rearrange dateParts to yyyy-mm-dd format
                $pickup_date = "{$dateParts[2]}-{$dateParts[1]}-{$dateParts[0]}";

                // Create booking reference number
                $result = $conn->query("SELECT booking_ref FROM booking_requests ORDER BY id DESC LIMIT 1");
                if ($result && $row = $result->fetch_assoc()) {
                $lastRef = $row['booking_ref']; // e.g., BRN00025
                $lastNum = intval(substr($lastRef, 3)); // get 25
                $nextNum = $lastNum + 1;
                } else {
                $nextNum = 1; // first booking
                }
                $booking_ref = 'BRN' . str_pad($nextNum, 5, '0', STR_PAD_LEFT);

                // Current booking datetime
                $booking_datetime = date('Y-m-d H:i:s');

                // Insert query into MySQL table
                $stmt = $conn->prepare("INSERT INTO booking_requests (
                booking_ref, customer_name, phone, unit_number, street_number, street_name,
                suburb, destination_suburb, pickup_date, pickup_time, booking_date, status
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'unassigned')
                ");

                if($stmt === false){
                    echo json_encode(['success'=>false, 'message'=>'SQL prepare failed']);
                    exit();
                }

                // Bind parameters to query
                $stmt->bind_param(
                    "sssssssssss",
                    $booking_ref, $cname, $phone, $unumber, $snumber, $stname, $sbname,
                    $dsbname, $pickup_date, $time, $booking_datetime

                );

                if($stmt->execute()){
                    //Formate pickup date and time back to DD/MM/YYYY and HH:MM
                    $display_date = "{$dateParts[0]}/{$dateParts[1]}/{$dateParts[2]}";
                    echo json_encode(['success'=> true,
                    'booking_ref'=>$booking_ref,
                    'pickup_date'=> $display_date,
                    'pickup_time' => $time
                ]);
                
                }else{
                    echo json_encode(['success'=>false, 'message'=>'Failed to insert request into database']);
                }

                $stmt->close();
                $conn->close();

?> 
        