/*
Name: Hayden Richard-Marsters
Email: qjn4504@autuni.ac.nz
SID: 21152003

File: admin.js
Description: - Handles admin search (by reference number or unassigned bookings in next 2 hours)
            - Sends AJAX POST requests to admin.php
            - Displays results table and handles taxi assignment
*/

document.addEventListener("DOMContentLoaded", function () {
  // Get reference to the search button and results div
  const searchBtn = document.getElementById("sbutton");
  const resultsDiv = document.getElementById("results");

  searchBtn.addEventListener("click", function () {
    console.log("Search button clicked");
    const bsearch = document.getElementById("bsearch").value.trim();

    //Validate if non-empty: must match format "BRN00001"
    if (bsearch !== "" && !/^BRN\d{5}$/.test(bsearch)) {
      resultsDiv.innerHTML =
        "<p style='color:red;'>Invalid booking reference format</p>";
      return;
    }

    const params = new URLSearchParams();
    params.append("bsearch", bsearch);

    // Send AJAX to admin.php
    fetch("admin.php", {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: params.toString(),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.success) {
          //Build table if search is successful
          let html = "<table class='admin-table'>";

          // Table headers
          html +=
            "<tr><th>Booking Ref</th><th>Customer Name</th><th>Phone</th><th>Pickup Suburb</th><th>Dest Suburb</th><th>Pickup Date & Time</th><th>Status</th><th>Assign</th></tr>";

          //Add one row per booking
          data.bookings.forEach((booking) => {
            html += `<tr>
                        <td>${booking.booking_ref}</td>
                        <td>${booking.customer_name}</td>
                        <td>${booking.phone}</td>
                        <td>${booking.suburb}</td>
                        <td>${booking.destination_suburb}</td>
                        <td>${booking.pickup_date} ${booking.pickup_time}</td>
                        <td>${booking.status}</td>
                        <td>${
                          booking.status === "unassigned"
                            ? `<button onclick="assignBooking('${booking.booking_ref}')">Assign</button>`
                            : ""
                        }</td>
                    </tr>`;
          });

          html += "</table>";
          resultsDiv.innerHTML = html;
        } else {
          resultsDiv.innerHTML = `<p style='color:red;'>${data.message}</p>`;
        }
      })
      .catch((err) => {
        resultsDiv.innerHTML =
          "<p style='color:red;'>Error loading bookings</p>";
      });
  });
});

// Function to assign a booking (called when assign button clicked)
function assignBooking(booking_ref) {
  const params = new URLSearchParams();
  params.append("assign", booking_ref);

  // Send AJAX POST request to admin.php to assign booking
  fetch("admin.php", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: params.toString(),
  })
    .then((response) => response.json())
    .then((data) => {
      alert(data.message);

      //Auto-reload search to update status
      document.getElementById("sbutton").click();
    })
    .catch((err) => {
      alert("Error assigning booking");
    });
}
