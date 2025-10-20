<!DOCTYPE html>
        <html>
            <head>
            <meta http-equiv="content-type" content="text/html; charset=utf-8" />
            <title>Using file functions</title>
            </head>
            <body>
            <h1>Web Development - Lab05 - Task: 1 & 2</h1>
            <?php
            require_once ("settings.php"); //please make sure the path is correct
            // complete your answer here

            // Connect to database, parsing db credentials
            $conn = new mysqli($sql_host, $sql_user, $sql_pass, $sql_db);

            // Checks if connection is successful
            if($conn->connect_error){
                die("<p>Connection failed: ".$conn->connect_error."</p></div>");
            }

            $query = "SELECT car_id, make, model, price
                    FROM car
                    ORDER BY price ASC;
                    ";
            
              // Connect to database, parsing db credentials
            $conn = new mysqli($sql_host, $sql_user, $sql_pass, $sql_db);

            // Checks if connection is successful
            if($conn->connect_error){
                die("<p>Connection failed: ".$conn->connect_error."</p></div>");
            }

            $result = $conn->query($query);

            if ($result->num_rows > 0) {
                echo "<table border='1'>
                        <tr>
                            <th>ID</th>
                            <th>Make</th>
                            <th>Model</th>
                            <th>Price</th>
                        </tr>";
            
                while ($row = $result->fetch_assoc()) {
                    echo "<tr>
                            <td>{$row['car_id']}</td>
                            <td>{$row['make']}</td>
                            <td>{$row['model']}</td>
                            <td>{$row['price']}</td>
                          </tr>";
                }
            
                echo "</table>";
                

            } else {
                echo "No car records found";
                
            }

            ?>
            </body>
            
        </html>








