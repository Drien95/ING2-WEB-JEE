package com.DAO;

import com.DB.DBConnect;
import com.entity.Order;
import com.entity.User;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Connection conn;

    public UserDAOImpl(Connection conn){
        super();
        this.conn = conn;
    }

    @Override
    public boolean userRegister(User user) {
        boolean f=false;

        try{
            String sql = "insert into user(name,email,password) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            System.out.println(ps);

            int i = ps.executeUpdate();
            if(i==1){
                f=true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public boolean checkUser(String email) {
        boolean f = true;

        try{
            String sql = "select * from user where email=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                f = false;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return f;
    }

    @Override
    public User login(String email, String pwd) {
        User user = null;
        System.out.println(pwd);
        try{
            String sql = "select * from user where email=? and password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                user = new User();
                user.setId(Integer.parseInt(rs.getString(1)));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setAdress(rs.getString(5));
                user.setCity(rs.getString(6));
                user.setPostal(rs.getInt(7));
                user.setRole(rs.getString(8));
                user.setFidelity_point(Double.parseDouble(rs.getString("fidelity_point")));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> list_user = new ArrayList<User>();
        User user = null;

        try{
            String sql = "select * from user";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setAdress(rs.getString(5));
                user.setCity(rs.getString(6));
                user.setPostal(rs.getInt(7));
                user.setRole(rs.getString(8));

                list_user.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list_user;
    }
    @Override
    public String doHashPassword(String pwd){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(pwd.getBytes());
            byte[] rbt = md.digest();
            StringBuilder sb = new StringBuilder();

            for(byte b : rbt){
                sb.append(String.format("%02x",b));
            }

            return sb.toString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateEditUser(User user) {
        boolean f = false;


        try{
            String sql = "update user set name=?,email=?,password=?,adress=?,city=?,postal=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAdress());
            ps.setString(5, user.getCity());
            ps.setInt(6, user.getPostal());
            ps.setInt(7, user.getId());

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
    public boolean updateEditUserRole(User user) {
        boolean f = false;


        try{
            String sql = "update user set role=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getRole());
            ps.setInt(2, user.getId());

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
    public boolean updateEditUserMail(User user) {
        boolean f = false;


        try{
            String sql = "update user set email=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setInt(2, user.getId());

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
    public boolean updateEditUserPwd(User user) {
        boolean f = false;


        try{
            String sql = "update user set password=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setInt(2, user.getId());

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
    public boolean deleteUser(int id) {
        boolean f = false;

        try{
            System.out.println("id user = "+id);
            OrderDAOImpl dao_order = new OrderDAOImpl(DBConnect.getConn());
            CartDAOImpl dao_cart = new CartDAOImpl(DBConnect.getConn());
            List<Order> l_order = dao_order.getOrderUser(id);
            System.out.println(l_order);

            for(Order o : l_order){
                System.out.println(o);
                dao_cart.deleteItem(o.getId());
                dao_order.deleteOrder(id);
            }

            String sql = "delete from user where id=?";
            String sql_2 = "delete from products where user_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps_2 = conn.prepareStatement(sql_2);

            ps.setInt(1,id);
            ps_2.setInt(1,id);

            int j = ps_2.executeUpdate();
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
    public boolean addFidelityPoint(int id, double fp) {
        boolean f = false;
        try{
            String sql = "update user set fidelity_point = fidelity_point + ? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, fp);
            ps.setInt(2, id);

            int i = ps.executeUpdate();

            if(i==1){
                f = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
