package database.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MysqlDBHandler {

	private static final String url = "jdbc:mysql://ip:port/lxy";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String user = "caches";
	private static final String password = "test";
	
	private Connection con;
	private PreparedStatement ps;
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	MysqlDBHandler(String sql) throws ClassNotFoundException, SQLException
	{
		con = DriverManager.getConnection(url, user, password);
		ps = con.prepareStatement(sql);
	}

	public void close() throws SQLException
	{
		if(con != null)
		{
			con.close();
		}
		if(ps != null)
		{
			ps.close();
		}
	}
	
	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}
}




















