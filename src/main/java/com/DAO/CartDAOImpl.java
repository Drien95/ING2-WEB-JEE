package com.DAO;

import com.entity.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    private Connection conn;

    public CartDAOImpl(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public boolean addToCart(Cart c, int user_id) {
        boolean f = false;

        int orderId = getActiveOrderId(user_id);
        System.out.println(orderId+"=apres activeOrder");

        if (orderId == -1) {
            System.out.println("ma commande existe pas");
            orderId = createOrder(user_id);
            System.out.println(orderId +"= apres createOrder");
        }

        // Vérifier si le produit existe déjà dans le panier
        if (productExistsInCart(c, orderId)) {
            System.out.println("existe déja produit panier");
            // Mettre à jour la quantité du produit existant
//            incrementQuantityInCart(c, orderId);
            boolean quantity = incrementQuantity(c,user_id);
            if(quantity){
                f = true;
            }
        } else {
            try {

                System.out.println("j'existe pas panier");
                System.out.println(c);
                String sql = "INSERT INTO cart(order_id, product_id, quantity) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, orderId);
                ps.setInt(2, c.getProduct_id());
                ps.setInt(3, c.getQuantity());

                int i = ps.executeUpdate();

                if (i>0) {
                    System.out.println("j'existe pas panier et j'ai été ajouté correctement");
                    f = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return f;
    }

    @Override
    public int getActiveOrderId(int userId) {
        int orderId = -1;
        try {
            String sql = "SELECT id FROM orders WHERE user_id = ? AND state = 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                orderId = rs.getInt("id");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return orderId;
    }

    @Override
    public int createOrder(int userId) {
        int orderId = -1;

        try {
            System.out.println("je crée la commande");
            String sql = "INSERT INTO orders(user_id, order_date, state) VALUES (?, CURRENT_DATE, 1)";
            PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            System.out.println("userId = "+userId);
            System.out.println("Exécution de la requête SQL");
            int affectedRows = ps.executeUpdate();
            System.out.println("Nombre de lignes affectées : " + affectedRows);

            if (affectedRows > 0) {
                System.out.println("La commande a été créée avec succès");

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                        System.out.println("ID de la commande généré : " + orderId);
                    } else {
                        System.out.println("Échec de récupération de l'ID généré");
                    }
                }
            } else {
                System.out.println("Échec de la création de la commande");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }

    @Override
    public boolean productExistsInCart(Cart c, int orderId) {
        boolean exists = false;

        try {
            System.out.println("je check si j'existe dans panier");
            String sql = "SELECT * FROM cart WHERE order_id = ? AND product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, c.getProduct_id());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("j'existe et j'en suis sur");
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public List<Cart> getCartItems(int user_id) {
        List<Cart> cartItems = new ArrayList<>();

        try {
            String sql = "SELECT c.id, c.quantity, p.id AS product_id, p.price, p.image " +
                    "FROM cart c " +
                    "JOIN products p ON c.product_id = p.id " +
                    "JOIN orders o ON c.order_id = o.id " +
                    "WHERE o.user_id = ? AND o.state = 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("quantity");
                int product_id = rs.getInt("product_id");
                double price = rs.getDouble("price");
                String image = rs.getString("image");

                Cart cartItem = new Cart(id, product_id, quantity, price, image);
                cartItems.add(cartItem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    @Override
    public void deleteCartItem(Cart cart, int orderId) {
        try{
            String sql = "DELETE FROM cart WHERE product_id = ? AND order_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cart.getProduct_id());
            ps.setInt(2, orderId);

            int i = ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean decrementQuantity(Cart cart, int user_id) {
        boolean f = false;

        try {
            int orderId = getActiveOrderId(user_id);

            if (orderId != -1) {
                int currentQuantity = getQuantityInCart(cart.getProduct_id(),orderId);

                if ( 1 < currentQuantity) {
                    int newQuantity = currentQuantity - 1;
                    updateQuantityInCart(cart.getProduct_id(), orderId, newQuantity);
                    f = true;
                }else{
                    deleteCartItem(cart,orderId);
                    f = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    @Override
    public boolean incrementQuantity(Cart cart, int user_id) {
        boolean f = false;

        try {
            int orderId = getActiveOrderId(user_id);

            if (orderId != -1) {
                int maxQuantity = getQuantityInProduct(cart.getProduct_id());
                int currentQuantity = getQuantityInCart(cart.getProduct_id(),orderId);

                if ( maxQuantity > currentQuantity) {
                    int newQuantity = currentQuantity + 1;
                    updateQuantityInCart(cart.getProduct_id(), orderId, newQuantity);
                    f = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }



    @Override
    public void updateQuantityInCart(int productId, int orderId, int newQuantity) {
        try {
            System.out.println(productId+" "+ " "+orderId+" "+newQuantity);
            String sql = "UPDATE cart SET quantity = ? WHERE order_id = ? AND product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, newQuantity);
            ps.setInt(2, orderId);
            ps.setInt(3, productId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getQuantityInCart(int productId, int orderId) {
        int quantity = 0;

        try {
            String sql = "SELECT quantity FROM cart WHERE order_id = ? AND product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return quantity;
    }


    @Override
    public int getQuantityInProduct(int productId) {
        int quantity = 0;

        try {
            String sql = "SELECT quantity FROM products WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return quantity;
    }

    @Override
    public List<Cart> getCart(int orderId) {
        List<Cart> l_cart = new ArrayList<Cart>();
        Cart cart = null;
        try {
            String sql = "SELECT c.id, c.quantity, p.id AS product_id, p.price, p.image " +
                    "FROM cart c " +
                    "JOIN products p ON c.product_id = p.id " +
                    "JOIN orders o ON c.order_id = o.id " +
                    "WHERE o.id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("quantity");
                int product_id = rs.getInt("product_id");
                double price = rs.getDouble("price");
                String image = rs.getString("image");

                cart = new Cart(id, product_id, quantity, price, image);
                l_cart.add(cart);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return l_cart;
    }

    @Override
    public void deleteItem(int orderId) {
        try{
            String sql = "DELETE FROM cart WHERE order_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);

            int i = ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
