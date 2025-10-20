var xHRObject = false;
if (window.XMLHttpRequest) {
  xHRObject = new XMLHttpRequest();
} else if (window.ActiveXObject) {
  xHRObject = new ActiveXObject("Microsoft.XMLHTTP");
}
function getData() {
  if (xHRObject.readyState === 4 && xHRObject.status === 200) {
    const cartDiv = document.getElementById("cart");

    const serverResponse = xHRObject.responseText
      ? JSON.parse(xHRObject.responseText)
      : null;

    let cartHTML = `
      <h2>Shopping Cart</h2>
      <button onclick="emptyCart()">Empty Cart</button><br><br>
    `;

    if (serverResponse && Object.keys(serverResponse).length > 0) {
      let total = 0;

      for (const isbn in serverResponse) {
        const quantity = serverResponse[isbn];

        const titleElement = document.getElementById(`book-${isbn}`);
        const priceElement = document.getElementById(`price-${isbn}`);

        if (titleElement && priceElement) {
          const title = titleElement.innerText;
          const priceValue = parseFloat(
            priceElement.innerText.replace("$", "")
          );

          const itemTotal = priceValue * quantity;
          total += itemTotal;

          cartHTML += `${title} x ${quantity} = $${itemTotal.toFixed(2)} 
            <a href='#' onclick='AddRemoveItem("Remove", "${isbn}");'>Remove</a><br><br>`;
        }
      }

      cartHTML += `<hr><strong>Total: $${total.toFixed(2)}</strong>`;
    } else {
      cartHTML += `<p>No items in cart.</p>`;
    }

    cartDiv.innerHTML = cartHTML;
  }
}

function emptyCart() {
  xHRObject.open(
    "GET",
    "test.php?action=Empty&value=" + Number(new Date()),
    true
  );

  xHRObject.onreadystatechange = getData;
  xHRObject.send(null);
}

function AddRemoveItem(action, isbn) {
  var book = document.getElementById(`book-${isbn}`).innerHTML;

  xHRObject.open(
    "GET",
    "test.php?action=" +
      action +
      "&book=" +
      encodeURIComponent(book) +
      "&isbn=" +
      encodeURIComponent(isbn) +
      "&value=" +
      Number(new Date()),
    true
  );

  console.log("isbn: " + isbn + " book: " + book + " action: " + action);

  xHRObject.onreadystatechange = getData;
  xHRObject.send(null);
}
