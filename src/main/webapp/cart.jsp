<%@ page import="java.util.List" %>
<%@ page import="com.entity.Cart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.entity.Cart" %>
<%@ page import="com.entity.Product" %>
<%@ page import="java.util.List" %>

<%
  ProductDAOimpl dao = new ProductDAOimpl(DBConnect.getConn());
%>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="style/style.css">
  <link rel="stylesheet" type="text/css" href="style/cart.css">
  <title>Mon Panier</title>
  <%@include file="/style/boot.jsp"%>
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
  <script src="/js/AddToCart.js"></script>
  <style>
    .square-btn {
      width: 50px; /* Ajustez la largeur selon vos préférences */
      height: 40px; /* Ajustez la hauteur selon vos préférences */
      padding: 0; /* Supprimez le remplissage pour un aspect carré */
    }
  </style>
</head>
<header>
  <%@include file="/components/navbar.jsp"%>
</header>
<body>
  <div class="container mt-4">
    <h2>Mon Panier</h2>

    <table class="table">
      <thead>
      <tr>
        <th>Produit</th>
        <th>Quantité</th>
        <th>Prix unitaire</th>
        <th>Total</th>
        <th>Supprime</th>
      </tr>
      </thead>
      <tbody>
      <% List<Cart> cartItems = (List<Cart>) request.getAttribute("cartItems");
        for (Cart cartItem : cartItems) { %>
      <tr>
        <td>
          <img src="<%= cartItem.getImage() %>" alt="<%= cartItem.getProduct_id() %>">
          <%= cartItem.getProduct_id() %>
        </td>
        <td>
          <div class="input-group">

            <!-- Bouton d'incrémentation -->
            <button type="button" class="btn btn-outline-secondary square-btn" onclick="addToCart('increment', <%= cartItem.getProduct_id() %>, 1)">+</button>
            <!-- Liste déroulante pour la quantité -->
            <select class="form-select select-quantity" style="height: 40px;width: 20px;margin: 0;padding: 5px;" id="quantity<%= cartItem.getId() %>" onchange="updateQuantity(<%= cartItem.getProduct_id() %>, this.value)">
              <% for (int i = 1; i <= dao.getProduct(cartItem.getProduct_id()).getQuantity(); i++) { %>
              <option value="<%= i %>"<%= (i == cartItem.getQuantity()) ? " selected" : "" %>><%= i %></option>
              <% } %>
            </select>
            <!-- Bouton de décrémentation -->
            <button type="button" class="btn btn-outline-secondary square-btn" style="margin: 0;padding: 0;" onclick="addToCart('decrement', <%= cartItem.getProduct_id() %>, 1)">-</button>
          </div>
        </td>
        <td><%= cartItem.getPrice() %></td>
        <td><%= cartItem.getQuantity() * cartItem.getPrice() %></td>
        <td> <!-- Bouton de suppression -->
          <button type="button" class="btn btn-danger square-btn" style="width: 80px" onclick="deleteItem('delete',<%= cartItem.getProduct_id() %>)">Delete</button>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>
    <div>
      <a href="/panier/validation" class="btn btn-primary">Valider panier</a>
    </div>
  </div>
</body>
</html>