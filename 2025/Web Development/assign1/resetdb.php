<!DOCTYPE html>
    <head> 
        <meta http-equiv="content-type" content="text/html; charset=utd-8" />
        <title>Reset Database Result</title>
        <link rel="stylesheet" href="styles.css" />
    </head> 
    <body>
        <div class="content">
        <?php

            // Pass db credentials
            require_once('sqlinfo.inc.php');

            // Connect to database, parsing db credentials
            $conn = new mysqli($sql_host, $sql_user, $sql_pass, $sql_db);

            // Checks if connection is successful
            if($conn->connect_error){
                die("<p class='boxed'>Connection failed: ".$conn->connect_error."</p></div>");
            }

            // Drop table from MySQL db
            $dropQuery = "DROP TABLE IF EXISTS status";
            if($conn->query($dropQuery) === TRUE){
                echo '<p class="boxed">Database has been reset. The "status" table has been dropped';
            }else{
                echo '<p class="boxed">Error resetting database: '.htmlspecialchars($conn->error).'</p>';
            }
            // Button to return to About Page and Home Page
            echo '<p><a href="about.html" class="btn">Back to About Page</a><br/ ><a href="index.html" class="btn">Return to Home Page</a></p>';

            $conn->close();
            echo '</div>';

        ?>
        </div>
    </body>
</html>
        
  