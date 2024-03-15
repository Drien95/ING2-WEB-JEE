package com.DAO;

import com.entity.Product;
import com.entity.User;

import java.util.List;

public interface UserDAO {
    public boolean userRegister(User user);
    public boolean checkUser(String email);
    public User login(String email, String pwd);
    public List<User> getAllUser();

    public String doHashPassword(String pwd);

    public boolean updateEditUser(User user);
    public boolean updateEditUserRole(User user);

    boolean updateEditUserMail(User user);

    boolean updateEditUserPwd(User user);

    public boolean deleteUser(int id);
    public boolean addFidelityPoint(int id, double fp);
}
