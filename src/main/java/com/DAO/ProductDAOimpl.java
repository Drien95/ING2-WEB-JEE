package com.DAO;

import com.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOimpl implements  ProductDAO{
    private Connection conn;

    public ProductDAOimpl(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public boolean addProduct(Product product) {
        boolean f = false;

        try{
            String sql = "insert into products(name,cat,price,quantity,user_id,image) values (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getCat());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getUser_id());
            ps.setString(6, product.getImage());

            int i = ps.executeUpdate();

            if(i==1){
                f = true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return f;
    }

    @Override
    public List<Product> getAllProduct() {

        List<Product> list_product = new ArrayList<Product>();
        Product product = null;

        try{
            String sql = "select * from products";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setCat(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setQuantity(rs.getInt(5));
                product.setUser_id(rs.getInt(6));
                product.setImage(rs.getString(7));

                list_product.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list_product;
    }

    @Override
    public List<Product> getProductByUser(int id_user) {
        List<Product> list_product = new ArrayList<Product>();
        Product product = null;

        System.out.println(id_user);

        try{
            String sql = "select * from products where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id_user);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setCat(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setQuantity(rs.getInt(5));
                product.setUser_id(rs.getInt(6));
                product.setImage(rs.getString(7));

                list_product.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list_product;
    }

    @Override
    public Product getProduct(int id) {
        Product product = null;

        try{
            String sql = "select * from products where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setCat(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setQuantity(rs.getInt(5));
                product.setUser_id(rs.getInt(6));
                product.setImage(rs.getString(7));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public boolean updateEditProduct(Product p) {
        boolean f = false;

        try{
            String sql = "update products set name=?,cat=?,price=?,quantity=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getCat());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setInt(5, p.getId());

            int i = ps.executeUpdate();

            if(i==1){
                f = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return f;
    }

    @Override
    public void updateQuantity(Product p){
        try{
            String sql = "update products set quantity=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getQuantity());
            ps.setInt(2, p.getId());

            int i = ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean f = false;

        try{
            String sql = "delete from products where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            int i = ps.executeUpdate();

            if(i==1){
                f = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public List<Product> getProductByCat(String cat) {
        List<Product> list_product = new ArrayList<Product>();
        Product product = null;

        try{

            String sql = "select * from products where cat=? and quantity>0";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cat);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setCat(rs.getString(3));
                product.setPrice(rs.getDouble(4));
                product.setQuantity(rs.getInt(5));
                product.setUser_id(rs.getInt(6));
                product.setImage(rs.getString(7));

                list_product.add(product);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return list_product;
    }

    @Override
    public List<String> getAllCat() {
        List<String> list_cat = new ArrayList<String>();
        String cat = null;

        try{
            String sql = "SELECT DISTINCT cat FROM products ORDER BY cat;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                cat = rs.getString(1);
                list_cat.add(cat);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list_cat;
    }
}
