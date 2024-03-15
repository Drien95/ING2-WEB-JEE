package com.DAO;

import com.entity.Product;

import java.util.List;

public interface ProductDAO {
    public boolean addProduct(Product product);
    public List<Product> getAllProduct();
    public List<Product> getProductByUser(int id_user);
    public Product getProduct(int id);
    public boolean updateEditProduct(Product p);
    public void updateQuantity(Product p);
    public boolean deleteProduct(int id);
    public List<Product> getProductByCat(String cat);
    public List<String> getAllCat();
}
