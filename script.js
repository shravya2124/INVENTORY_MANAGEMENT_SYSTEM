// Function to add a product
function addProduct() {
    var productName = document.getElementById("productName").value;
    var productQuantity = document.getElementById("productQuantity").value;
    var productPrice = document.getElementById("productPrice").value;

    var productList = document.getElementById("productList");
    var listItem = document.createElement("li");
    listItem.innerHTML = "<strong>" + productName + "</strong><br>Quantity: " + productQuantity + "<br>Price: $" + productPrice;
    productList.appendChild(listItem);

   
    document.getElementById("productName").value = "";
    document.getElementById("productQuantity").value = "";
    document.getElementById("productPrice").value = "";
}

// Function to update a product
function updateProduct() {
    var productId = document.getElementById("productIdToUpdate").value;
    var productName = document.getElementById("productNameToUpdate").value;
    var productQuantity = document.getElementById("newQuantity").value;
    var productPrice = document.getElementById("newPrice").value;

 
    var productDetails = "<strong>" + productName + "</strong><br>Quantity: " + productQuantity + "<br>Price: $" + productPrice;
    var listItem = document.getElementById("productItem_" + productId);

    if (listItem) {
        listItem.innerHTML = productDetails;
        updateProductInDatabase(productId, productName, productQuantity, productPrice);
    } else {
        alert("Product not found with the given ID.");
    }
}

// Function to delete a product
function deleteProduct() {
    var productIdToDelete = document.getElementById("productIdToDelete").value;
    var listItem = document.getElementById("productItem_" + productIdToDelete);
    if (listItem) {
        listItem.remove();
        deleteProductFromDatabase(productIdToDelete);
    } else {
        alert("Product not found with the given ID.");
    }
}

// Function to confirm exit and perform exit logic
function exit() {
    var confirmExit = confirm("Are you sure you want to exit?");
    if (confirmExit) {
        alert("Exiting the application...");
    }
}

