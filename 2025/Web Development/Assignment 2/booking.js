/*
Name: Hayden Richard-Marsters
SID: 21152003
emai: qjn4504@autuni.ac.nz

File: booking.js
Description:  - Handles client-side validation of form fields
            - Sends AJAX POST request to booking.php
            - Displays confirmation message on success

*/

document.addEventListener("DOMContentLoaded", function () {
  // Get the booking form and reference message container
  const form = document.getElementById("bookingForm");
  const referenceDiv = document.getElementById("reference");
  console.log("booking.js is loaded");
  form.addEventListener("submit", function (e) {
    e.preventDefault();
    referenceDiv.innerHTML = "";
    referenceDiv.style.color = "black";

    // Collect form data into FormData object
    const data = new FormData(form);

    // Trim and extract individual form fields from FormData
    const cname = data.get("cname").trim();
    const phone = data.get("phone").trim();
    const unumber = data.get("unumber").trim();
    const snumber = data.get("snumber").trim();
    const stname = data.get("stname").trim();
    const sbname = data.get("sbname").trim();
    const dsbname = data.get("dsbname").trim();
    const date = data.get("date").trim();
    const time = data.get("time").trim();

    // Client-side validation

    //check if cname is not null
    if (cname === "") {
      referenceDiv.innerHTML =
        "<p style='color:red;'>Customer name is required</p>";
      return;
    }

    // Check if phone number is null and is 10-12 digits, numeric only
    if (phone === "" || !/^\d{10,12}$/.test(phone)) {
      referenceDiv.innerHTML =
        "<p style='color:red;'>Phone number is required and must be 10 to 12 digits, numerical only</p>";
      return;
    }

    // unumber can be null (optional) and any format (alphanetical + numerical)
    // Hence, no validation required

    // Check street number is not null and numeric
    if (snumber === "" || isNaN(snumber)) {
      referenceDiv.innerHTML =
        "<p style='color:red;'>Street number is required and must be numeric</p>";
      return;
    }

    // Check street name is null
    if (stname === "") {
      referenceDiv.innerHTML =
        "<p style='color:red;'>Street name is required</p>";
      return;
    }

    // suburb is optional and any format (alphabetical + numerical)
    // Hence, no validaiotn is required

    // Parse pickup date and time to Date object
    let pickup;
    let now;

    try {
      const [dd, mm, yyyy] = date.split("/");
      const [hh, min] = time.split(":");
      pickup = new Date(`${yyyy}-${mm}-${dd}T${hh}:${min}`);
      now = new Date();
    } catch (err) {
      referenceDiv.innerHTML =
        "<p style='color:red;'>Error parsing date/time</p>";
      return;
    }

    // Validate that the new constructed date is valid and readable
    if (isNaN(pickup.getTime())) {
      referenceDiv.innerHTML =
        "<p style='color:red;'>Invalid pickup date or time format</p>";
      return;
    }

    // Ensure pickup time is in the future
    if (pickup < now) {
      referenceDiv.innerHTML =
        "<p style='color:red;'>Pickup date and time must not be in the past</p>";
      return;
    }

    const params = new URLSearchParams();
    for (const [key, value] of data.entries()) {
      params.append(key, value.trim());
    }

    fetch("booking.php", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: params.toString(),
    })
      .then((response) => response.json())
      .then((result) => {
        if (result.success) {
          referenceDiv.innerHTML = `
        Thank you for your booking!<br>
        Booking reference number: ${result.booking_ref}<br>
        Pickup time: ${result.pickup_time}<br>
        Pickup date: ${result.pickup_date}
      `;
        } else {
          referenceDiv.innerHTML = `Error: ${result.message}`;
        }
      })
      .catch((err) => {
        referenceDiv.innerHTML =
          "<p style='color:red;'>Error: Unable to process your booking.</p>";
        console.error(err);
      });
  });
});
