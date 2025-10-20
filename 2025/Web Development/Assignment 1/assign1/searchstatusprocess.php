<!DOCTYPE html>
    <head> 
        <meta http-equiv="content-type" content="text/html; charset=utd-8" />
        <title>Search Status Result</title>
        <link rel="stylesheet" href="styles.css" />
    </head> 
    <body>
        <div class="content">
        <?php

            $input = $_GET['Search'] ?? '';
            $search = trim($input);

            // Validate user input (non-empty string)
            if($search === ''){
                echo '<p class="boxed">Your search query is empty. Please enter a keyword to search.</p>';
                echo '<p><a href="searchstatusform.html" class="btn">Return to Search Form</a><br/ ><a href="index.html" class="btn">Return to Home Page</a></p>';
                echo '</div>'; 
                exit();
            }

            //Pass db credentials
            require_once('sqlinfo.inc.php');

            //Connect to db, parsing db credentials
            $conn = new mysqli($sql_host, $sql_user, $sql_pass, $sql_db);

            // Checks if connection is successful, otherwise displays error message
            if($conn->connect_error){
                die("<p>Connection failed: ".$conn->connect_error."</p></div>");
            }
        
            //check if table status exists, if it doesn't exist exit and display message that no table exists or status table is empty
            // If not found, user is urged to post a status
            $tableCheck = $conn->query("SHOW TABLES LIKE 'status'");
            if(!$tableCheck || $tableCheck->num_rows === 0){
                echo '<p class="boxed">No status found in the system. Please go to the post status page to post one.</p>';
                echo '<p><a href="poststatusform.php" class="btn">Post a Status</a><br/ ><a href="index.html" class="btn">Return to Home Page</a></p>';
                echo '</div>';
                exit();    
            }

            //Perform keyword search
            $match = '%'.strtolower($search).'%';
            
            // Prepare SQL script
            $stmt = $conn->prepare(
                "
                SELECT stcode, status, share, date, permission
                FROM status
                WHERE LOWER(status) LIKE ?
                ORDER BY date ASC
                "
            );
            // Bind parameters to SQL script, parse to MySQL db and execute
            $stmt->bind_param('s', $match);
            $stmt->execute();
            $result = $stmt->get_result();
            $count = $result->num_rows;

            //Handle when no matches are found
            if($count === 0){
                echo '<p class="boxed">No status matching "'.htmlspecialchars($input).'" results found. Please try a different keyword</p>';
                echo '<p><a href="searchstatusform.html" class="btn">Search for Another Status</a><br/ ><a href="index.html" class="btn">Return to Home Page</a></p>';
                echo '</div>';
                exit();

            }

            //Display matches and match count
            echo '<h1>Status Information</h3>';
            echo '<h3>Search Results for: '.htmlspecialchars($search).' ('.$count.' matches)</h3><hr>';
            while($row = $result->fetch_assoc()){

                //format date from YYYY-MM-DD to e.g. August 22, 2017
                $formatDate = date('F d, Y', strtotime($row['date']));
                echo "<p class='form'> 
                            <strong>Status Code: </strong>".htmlspecialchars($row['stcode']). 
                            "<br/><strong>Status: </strong>".htmlspecialchars($row['status']). 
                            "<br/><strong>Share: </strong>".htmlspecialchars($row['share']).
                            "<br/><strong>Date: </strong>".$formatDate.
                            "<br/><strong>Permission(s): </strong>".htmlspecialchars($row['permission']).
                            "</p>";
            }
            echo '<hr />';
            // Buttons to return to search and home page
            echo '<p><a href="searchstatusform.html" class="btn">Search for Another Status</a><br/ ><a href="index.html" class="btn">Return to Home Page</a></p>';

            $stmt->close();
            $conn->close();
            echo '</div>';

        ?> 
        </div>
    </body>
</html>
        


