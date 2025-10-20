*** PROJECT SUMMARY

simple web-based taxi booking system called CabsOnline. 
It allows passengers to book taxi services from any of their internet
connected computers or mobile phones.

Two components (booking and admin):
- Booking system (public-facing) - lets users submit bookings
- Admin System (Back-end) - Lets admins view and assign bookings

*** Files: 

1. booking.html
    - Taxi booking form (client-side)
    - Uses booking.js to handle form validation and AJAX submission
    - Sends booking data to booking.php (server-side)

2. booking.js
    - Handles client-side validation of form fields
    - Sends AJAX POST request to booking.php
    - Displays confirmation message on success

3. booking.php
    - Server-side PHP to handle booking requests
    - Validates input, generates booking reference, inserts data into MySQL
    - Returns JSON confirmation to client-side

4. admin.html
    - Admin interface to search and assign bookings
    - Uses admin.js to handle search and assign operations

5. admin.js
    - Handles admin search (by reference number or unassigned bookings in next 2 hours)
    - Sends AJAX POST requests to admin.php
    - Displays results table and handles taxi assignment

6. admin.php
    - Server-side PHP to process admin search and assign requests
    - Queries MySQL for matching bookings
    - Updates status for assigned bookings

7. styles2.css
    - Common stylesheet used by both booking.html and admin.html
    - Styles form layout, admin table, buttons, etc.

8. mysqlcommand.txt
    - Contains MySQL commands used to create the database and table:
      - booking_requests table with required fields

*** Usage: 
1. Booking Component:
    - Opening Book a Taxi (booking.html) and fill out form
    - Click send request
    - Recieve confirmation message with
        - Booking reference number
        - Pickup date
        - Pickup time

2. Admin Component:
    - Option 1: Search for exact booking with reference number
    - Option 2: Empty-search to return all unassigned bookings booked for a pickup within 2 hours

    - Click "Assign"  button to assign a booking -> status will update and confirmation will show

*** Notes:
1. MySQL table is automatically created in booking.php if it does not exist already