package com.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

    private static BaseDao baseDao;
    private Connection connection;


    private BaseDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/operation","root","123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static class BaseDaoHelp {
        private static final BaseDao BASE_DAO = new BaseDao();
    }

    public static synchronized BaseDao getBaseDao() {
        baseDao = BaseDaoHelp.BASE_DAO;
        return baseDao;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
