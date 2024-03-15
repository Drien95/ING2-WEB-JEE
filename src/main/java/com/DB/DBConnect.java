package com.DB;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DBConnect {
    private static java.sql.Connection conn = null;
    public static final String URL = "jdbc:mysql://localhost:3306/market_place_jee_ing2";
    public static final String USER = "root";
    public static final String PASSWORD = "password&2";
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static java.sql.Connection getConn() throws ClassNotFoundException, SQLException{
        if(conn == null){

            try{
                System.out.println("ok");
                Class.forName(DRIVER_CLASS);
                System.out.println("ok2");

                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }catch (Exception e){
                System.out.println("eefefe");
                e.printStackTrace();
            }
        }

        return conn;
    }
}
