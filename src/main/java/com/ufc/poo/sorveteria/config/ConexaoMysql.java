package com.ufc.poo.sorveteria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cristiano
 */
public class ConexaoMysql {
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static final String url = "jdbc:mysql://localhost:3306/sorveteria";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    
    public static Connection openConnection() throws SQLException, ClassNotFoundException{
        Class.forName(driver);
        return DriverManager.getConnection(url, USUARIO, SENHA);
    }
}
