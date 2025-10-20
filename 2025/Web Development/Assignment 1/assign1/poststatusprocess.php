<!DOCTYPE html>
    <head> 
        <meta http-equiv="content-type" content="text/html; charset=utd-8" />
        <title>Post Status Result</title>
        <link rel="stylesheet" href="styles.css" />
    </head> 
    <body>
        <div class="content">
        <?php
                //Pass db credentials
                require_once('sqlinfo.inc.php');

                // Connect to db
                $conn = new mysqli($sql_host, $sql_user, $sql_pass, $sql_db);

                // Checks if connection is successful
                if($conn->connect_error){
                    die("<p class='boxed'>Connection failed: ".$conn->connect_error."</p></div>");
                }
            
                //script to create table if does not exist already
                $createTableSQL = "CREATE TABLE IF NOT EXISTS status (
                                    stcode VARCHAR(5) PRIMARY KEY,
                                    status TEXT NOT NULL,
                                    share VARCHAR(20),
                                    date DATE NOT NULL,
                                    permission TEXT
                                    )";

                // Parse script to MySQL db
                $conn->query($createTableSQL);

                //Get and validate form data
                $stcode = $_POST["stcode"] ?? '';
                $status = $_POST["st"] ?? '';
                $share = $_POST["share"] ?? '';
                $date = $_POST["date"] ?? '';
                $permissionsArray = $_POST["permission"] ?? [];
                
                // Validation processes

                //Status code (stcode) validation (format, unique) 
                if(!preg_match("/^S\d{4}$/", $stcode)){
                    echo "<p class='boxed'>Invalid format! The code must start with \"S\" followed by four digits - e.g. \"S0001\".</p>";
                    echo "<a href='poststatusform.php' class='btn'>Return to Post Status</a><br/ ><a href='index.html' class='btn'>Return to Home Page</a>";
                    echo "</div>";
                    exit();
                    
                    
                }

                // stcode validation (unique) - check if given stcode is unique
                $checkQuery = "SELECT stcode FROM status WHERE stcode = ?";
                $stmt = $conn->prepare($checkQuery);
                $stmt->bind_param("s", $stcode);
                $stmt->execute();
                $stmt->store_result();
                
                // Displays error message if the user inputted a stcode that already exists witin db
                if($stmt->num_rows > 0){
                    echo "<p class='boxed'>The status code $stcode already exists within the database, please try another one (\"S\" followed by four digits e.g. SXXXX)";
                    echo "<p><a href='poststatusform.php' class='btn'>Return to Post Status Page</a><br/ ><a href='index.html' class='btn'>Return to Home Page</a></p>";
                    echo "</div>";
                    exit();
                }
                $stmt->close();

                //Validate status input
                if(!preg_match('/^[A-Za-z0-9 ,.?!]+$/', $status) || trim($status) === ''){
                    echo "<p class='boxed'>Invalid status format! The status can only contain alphanumericals, comma, period, exclamation point, and question mark and cannot be blank. </p>";
                    echo "<p><a href='poststatusform.php' class='btn'>Return to Post Status Page</a><br/ ><a href='index.html' class='btn'>Home</a></p>";
                    echo "</div>";
                    exit();
                }

                //Validate date input
                try{ 
                    $dateObj = DateTime::createFromFormat('d/m/Y', $date);
                    if(!$dateObj || $dateObj->format('d/m/Y') !== $date){
                        throw new Exception("Invalid date format.");
                    }
                    $formattedDate = $dateObj->format('Y-m-d'); //reformat for MySQL handling
                } catch (Exception $e){
                        echo "<p class='boxed'> Invalid date format! Please use dd/mm/yyyy. </p>";
                        echo "<p><a href='poststatusform.php' class='btn'>Return to Post Status Page</a><br/ ><a href='index.html' class='btn'>Home</a></p>";
                        echo "</div>"; 
                        exit();
                }
                
                // Permissions handling when > 1
                $permissionString = implode(", ", $permissionsArray);

                //Confirm submitted form data to user
                echo "<p class='form'> 
                            <strong>Form submission -</strong><br />
                            
                            <br/><strong>Status Code: </strong>$stcode 
                            <br/><strong>Status: </strong>$status 
                            <br/><strong>Share: </strong>$share
                            <br/><strong>Date: </strong>$date
                            <br/><strong>Permission(s): </strong>$permissionString
                            
                            </p>";

                // Insert status form into MySQL db
                $insertQuery = "INSERT INTO status (stcode, status, share, date, permission) VALUES (?, ?, ?, ?, ?)";
                $stmt = $conn->prepare($insertQuery);
                $stmt->bind_param("sssss", $stcode, $status, $share, $formattedDate, $permissionString);
                
                // Confirm the status form has been uploaded succesfully 
                if($stmt->execute()){
                    echo "<p class='boxed'>Status upload successful! </p>";
                    echo "<p><a href='index.html' class='btn'>Return to Home </a></p>";
                    echo "</div>";
                    $stmt->close();
                    $conn->close();
                    exit();
                }else{
                    //Display error message if there is an error saving to db
                    echo '<p class="boxed">Error saving status. Please try again later :(</p>';
                    echo '<p><a href="poststatusform.php" class="btn">Return to Post Status Form</a><br/ ><a href="index.html" class="btn">Return to Home</a></p>';
                    $stmt->close();
                    $conn->close();
                    exit();
                }
        ?>    
        </div>
    </body>
</html>
        
  