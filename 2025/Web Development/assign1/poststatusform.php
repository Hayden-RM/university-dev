<?php
$defaultDate = date("d/m/Y");
?>

<html>
    <head>
        <title>Post Status Page</title>
        <link rel="stylesheet" href="styles.css" />
    </head> 
        <body>
        <div class="content">
            <h1>Status Posting System</h1>
            <form action="poststatusprocess.php" method="POST">
                <!-- implement hint class for hovering over form functionality -->
                <!-- Status code -->
                <div class="form-group">
                    <label for="stcode">Status Code:</label>
                    <input type="text" name="stcode" id="stcode" required>
                    <small class="hint">
                        Status Code Format: e.g. S0001 (unique, uppercase 'S' + four digits)
                    </small><br />
                </div>

                <br /> 
                
                <!-- Status -->
                <div class="form-group">
                    <label for="st">Status:</label>
                    <input type="text" name="st" id="st" required>
                    <small class="hint">
                        Status Format: Only letters, numbers, spaces, comma, period, !, ?.
                    </small>
                </div>
                
                <!--- Share options --> 
                <p>
                    <label>Share:</label>
                    <input type="radio" name="share" value="University" required> University
                    <input type="radio" name="share" value="Class"> Class
                    <input type="radio" name="share" value="Private"> Private
                </p>
                
                <!-- Date -->
                <div class="form-group">
                    <label for="date">Date:</label>
                    <input type="text" id="date" name="date" value="<?php echo $defaultDate; ?>" required>
                    <small class="hint">
                        Date Format: DD/MM/YYYY.
                    </small>
                </div>

                <!-- check boxes / permissions -->
                <p>
                    <label>Permission:</label>
                    <input type="checkbox" name="permission[]" value="Allow Like">Allow like
                    <input type="checkbox" name="permission[]" value="Allow Comments">Allow comment
                    <input type="checkbox" name="permission[]" value="Allow Share">Allow share
                </p>

                <!-- Submit button -->
                <p>
                    <input type="submit" value="Submit"> 
                </p>

            </form>
            
            <p><a href="index.html" class="btn">Return to Home Page</a></P>
            
        </div>
        </body>
    
</html>
    