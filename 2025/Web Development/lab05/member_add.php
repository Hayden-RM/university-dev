<?php
     
     // Pass db credentials
            require_once('settings.php');

            // Connect to database, parsing db credentials
            $conn = new mysqli($sql_host, $sql_user, $sql_pass, $sql_db);

            // Checks if connection is successful
            if($conn->connect_error){
                die("<p class='boxed'>Connection failed: ".$conn->connect_error."</p></div>");
            }


            // CREATE TABLE 
            $createTableSQL = "CREATE TABLE IF NOT EXISTS vipmember (
                                    member_id INT AUTO_INCREMENT PRIMARY KEY,
                                    fname VARCHAR(40) NOT NULL,
                                    lname VARCHAR(40),
                                    gender VARCHAR(40),
                                    email VARCHAR(40),
                                    phone VARCHAR(40)
                                    );
                                    ";

            // Parse script to MySQL db
            $conn->query($createTableSQL);


            //Get and validate form data
    
                $fname = $_POST["fname"] ?? '';
                $lname = $_POST["lname"] ?? '';
                $gender = $_POST["gender"] ?? '';
                $email = $_POST["email"] ?? '';
                $phone = $_POST["phone"] ?? '';

            // Validate first name
            if (!preg_match('/^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$/', $fname)) {
                echo "<p class='boxed'>Invalid first name. Only alphabetic characters, spaces or hyphens allowed.</p>";
                echo "<p><a href='vip_member.php' class='btn'>Return to Add Member Form</a></p>";
                exit();
            }

            // Validate last name
            if (!preg_match('/^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$/', $lname)) {
                echo "<p class='boxed'>Invalid last name. Only alphabetic characters, spaces or hyphens allowed.</p>";
                echo "<p><a href='vip_member.php' class='btn'>Return to Add Member Form</a></p>";
                exit();
            }

            // Validate gender (must be 'M' or 'F')
            if (!in_array(strtoupper($gender), ['M', 'F'])) {
                echo "<p class='boxed'>Invalid gender. Only 'M' or 'F' allowed.</p>";
                echo "<p><a href='vip_member.php' class='btn'>Return to Add Member Form</a></p>";
                exit();
            }


            // Validate email
            if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
                echo "<p class='boxed'>Invalid email format. Please try again.</p>";
                echo "<p><a href='vip_member.php' class='btn'>Return to Add Member Form</a></p>";
                exit();
            }

            // Validate phone number (allow digits, spaces, dashes, parentheses)
            if (!preg_match('/^[0-9\-\(\) ]+$/', $phone)) {
                echo "<p class='boxed'>Invalid phone number format. Only digits, spaces, dashes, and parentheses are allowed.</p>";
                echo "<p><a href='vip_member.php' class='btn'>Return to Add Member Form</a></p>";
                exit();
            }

            // Insert status form into MySQL db
                $insertQuery = "INSERT INTO vipmember (fname, lname, gender, email, phone) VALUES (?, ?, ?, ?, ?)";
                $stmt = $conn->prepare($insertQuery);
                $stmt->bind_param("sssss", $fname, $lname, $gender, $email, $phone);
                
                // Confirm the status form has been uploaded succesfully 
                if($stmt->execute()){
                    echo "<p class='boxed'>VIP Member upload successful! </p>";
                    echo "<p><a href='vip_member.php' class='btn'>Return to Home </a></p>";
                    echo "</div>";
                    $stmt->close();
                    $conn->close();
                    exit();
                }else{
                    //Display error message if there is an error saving to db
                    echo '<p class="boxed">Error saving VIP Member. Please try again later :(</p>';
                    echo '<p><a href="vip_member.php" class="btn">Return to Add Member Form</a><br/ ><a href="vip_member.php" class="btn">Return to Home</a></p>';
                    $stmt->close();
                    $conn->close();
                    exit();
                }

                echo "<br /><a href='vipmember.php>Return to home page</a>";

?>