package database.jdbc.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class OracleDBHandler {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    static{
        Properties prop = new Properties();
        try {
            // 从属性文件中读取配置文件
            prop.load(OracleDBHandler.class.getClassLoader()
                    .getResourceAsStream("oracledatabase.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(null != prop)
        {
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        }

        // 装载并注册数据库驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }





    public OracleDBHandler() {

    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static Connection OpenConnection() throws SQLException
    {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     *
     * @param connn
     * @throws SQLException
     */
    public static void closeConnection (Connection connn) throws SQLException
    {
        if(null != connn)
        {
            connn.close();
        }
    }


    /**
     *
     * @param user
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection(String user, String password) throws ClassNotFoundException, SQLException
    {
        if(null == conn)
        {
            // 1、注册驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2、通过驱动管理器获得数据库连接
            String url = "jdbc:oracle:thin:@127.0.0.1:1521:FM";
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

    /**
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static Statement getStatement(Connection conn) throws SQLException
    {
        if(null != conn)
        {
            stmt = conn.createStatement();
            return stmt;
        }
        return null;
    }

    /**
     *
     * @throws SQLException
     */
    public static void close() throws SQLException
    {
        if(null != stmt)
        {
            stmt.close();
        }
        if(null != conn)
        {
            conn.close();
        }
    }

    public static void main(String[] args){
        System.out.print("hello");
    }
}
