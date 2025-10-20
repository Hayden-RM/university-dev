<?php

            // Pass db credentials
            require_once('sqlinfo.inc.php');

            // Connect to database, parsing db credentials
            $conn = new mysqli($sql_host, $sql_user, $sql_pass, $sql_db);

            // Checks if connection is successful
            if($conn->connect_error){
                die("<p class='boxed'>Connection failed: ".$conn->connect_error."</p></div>");
            }


            // CREATE TABLE 
            $createTableSQL = "CREATE TABLE IF NOT EXISTS car (
                                    car_id INT AUTO_INCREMENT PRIMARY KEY,
                                    make VARCHAR(50) NOT NULL,
                                    model VARCHAR(50) NOT NULL,
                                    price INT NOT NULL,
                                    yom YEAR NOT NULL
                                    )";

            // Parse script to MySQL db
            $conn->query($createTableSQL);

            // Validate table creation with user 
            echo "<p><strong>'car'</strong> table created or already exists</p>";

            // INSERT 10 RECORDS
            // Create and insert 10 records into the table
            $insert_10_recordsSQL = "INSERT INTO car (make, model, price, yom) VALUES
                                    ('Toyota', 'Corolla', 12500, 2015),
                                    ('Honda', 'Civic', 13500, 2016),
                                    ('Ford', 'Focus', 9800, 2014),
                                    ('Mazda', '3', 11000, 2015),
                                    ('Hyundai', 'Elantra', 10200, 2017),
                                    ('Volkswagen', 'Golf', 14500, 2018),
                                    ('Nissan', 'Sentra', 9000, 2013),
                                    ('Chevrolet', 'Cruze', 8700, 2012),
                                    ('Kia', 'Forte', 9700, 2016),
                                    ('Subaru', 'Impreza', 12800, 2017);
                                    ";
        
            // Insert 10 records 
            $conn->query($insert_10_recordsSQL);
            echo "<p>10 Records inserted into '<strong>car</strong>' table</p>";

            // TASK 2 - SQL QUERIES

            // 1. Retrieve all records
            echo "<h3>1. All Records:</h3>";
            $SQL1 = "SELECT * FROM car";
            $result1 = $conn->query($SQL1);
            if ($result1->num_rows > 0) {
                while ($row = $result1->fetch_assoc()) {
                    echo "ID: {$row['car_id']}, Make: {$row['make']}, Model: {$row['model']}, Price: {$row['price']}, Year: {$row['yom']}<br />";
                }
            } else {
                echo "No records found.";
            }
            echo "<hr />";

            // 2. Make, model, and price, sorted by make and model
            echo "<h3>2. Make, Model, and Price (Sorted):</h3>";
            $SQL2 = "SELECT make, model, price FROM car ORDER BY make ASC, model ASC";
            $result2 = $conn->query($SQL2);
            while ($row = $result2->fetch_assoc()) {
                echo "Make: {$row['make']}, Model: {$row['model']}, Price: \${$row['price']}<br />";
            }
            echo "<hr />";

            // 3. Cars priced at $20,000 or more
            echo "<h3>3. Cars Priced ≥ \$20,000:</h3>";
            $SQL3 = "SELECT make, model FROM car WHERE price >= 20000";
            $result3 = $conn->query($SQL3);
            if ($result3->num_rows > 0) {
                while ($row = $result3->fetch_assoc()) {
                    echo "Make: {$row['make']}, Model: {$row['model']}<br />";
                }
            } else {
                echo "No cars found above \$20,000.";
            }
            echo "<hr />";

            // 4. Cars priced below $15,000
            echo "<h3>4. Cars Priced < \$15,000:</h3>";
            $SQL4 = "SELECT make, model FROM car WHERE price < 15000";
            $result4 = $conn->query($SQL4);
            while ($row = $result4->fetch_assoc()) {
                echo "Make: {$row['make']}, Model: {$row['model']}<br />";
            }
            echo "<hr />";

            // 5. Average price of cars grouped by model
            echo "<h3>5. Average Price by Model:</h3>";
            $SQL5 = "SELECT model, AVG(price) AS average_price FROM car GROUP BY model";
            $result5 = $conn->query($SQL5);
            while ($row = $result5->fetch_assoc()) {
                echo "Model: {$row['model']}, Average Price: \$" . number_format($row['average_price'], 2) . "<br />";
            }
            echo "<hr />";

            // For sake of lab example: Reset db and drop table at the end
            // Drop table from MySQL db
            /* 
            $dropQuery = "DROP TABLE IF EXISTS car";
            if($conn->query($dropQuery) === TRUE){
                echo '<p class="boxed">Database has been reset. The "car" table has been dropped';
            }else{
                echo '<p class="boxed">Error resetting database: '.htmlspecialchars($conn->error).'</p>';
            }
            */
                    
?>