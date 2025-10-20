<!DOCTYPE html>
<html>
    <head>
        <title>Add Member Form</title>
        <link rel="stylesheet" href="styles.css" />
    </head> 
        <body>
        <div class="content">
            <h1>VIP Member Add Form</h1>
            <form action="member_add.php" method="POST">
                <!-- first name -->
                <div class="form-group">
                    <label for="fname">First Name:</label>
                    <input type="text" name="fname" id="fname" required>
                    <small class="hint">
                        A first name is required
                    </small>
                </div>
                <br />

                <!-- last name -->
                <div class="form-group">
                    <label for="lname">Last Name:</label>
                    <input type="text" name="lname" id="lname">
                    <small class="hint">
                        A last name is not required
                    </small>
                </div>
                <br />

                <!-- gender -->
                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <input type="text" name="gender" id="gender">
                    <small class="hint">
                        Gender is not required
                    </small>
                </div>
                <br />

                <!-- email -->
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" name="email" id="email">
                    <small class="hint">
                        An email is not required but advised
                    </small>
                </div>
                <br />

                <!-- phone -->
                <div class="form-group">
                    <label for="phone">Phone Number:</label>
                    <input type="text" name="phone" id="phone" required>
                    <small class="hint">
                        Phone number is not required but advised
                    </small>
                </div>
                <br />

                <!-- Submit button -->
                <p>
                    <input type="submit" value="Submit"> 
                </p>

            </form>
            
            <p><a href="vip_member.php" class="btn">Return to Home Page</a></P>
            
        </div>
        </body>
    
</html>
    

