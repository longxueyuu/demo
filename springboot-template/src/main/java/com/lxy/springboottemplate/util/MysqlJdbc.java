package com.lxy.springboottemplate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlJdbc {

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            System.out.println("无法加在驱动");
        }
        try {

            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/lxy",
                    "lxy", "123456");
            System.out.println("数据库连接成功");
            if (!conn.isClosed()) {
                System.out.println("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
