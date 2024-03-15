function addToCart(action, productId, quantity) {
    // Effectue la requête AJAX à la servlet
    $.post("/panier", { productId: productId, quantity: quantity, action: action }, function (data) {
        if (action != "add"){
            location.reload();
        }else{
            handleCartResponse(data);
        }
    }, "json");
}

function handleCartResponse(data) {
    // Afficher un message (peut être supprimé si non nécessaire)
    alert(data.message);
    location.reload();
}

function deleteItem(action, productId){
    $.post("/panier", { productId: productId, action: action, quantity: 0 }, function (data) {
        if (action != "add"){
            location.reload();
        }else{
            handleCartResponse(data);
        }
    }, "json");
}

function updateQuantity(productId, quantity){
    $.post("/panier", { productId: productId, action: 'updateQuantity', quantity: quantity }, function (data) {
            location.reload();
    }, "json");
}