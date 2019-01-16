package database.jdbc.main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatebaseMetaDataMain {
	
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello world!");
		
		try {
			// 1��ע������
			// ��ʽ1
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ��ʽ2
			//Driver drv = new oracle.jdbc.driver.OracleDriver();
			//DriverManager.registerDriver(drv);
			//��ʽ3
			//System.setProperty("jdbc.drivers", "oracle.jdbc.driver.OracleDriver");
			// 2��ͨ������������������ݿ�����
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:FM";
			String user = "dbtest";
			String password = "test";
			conn = DriverManager.getConnection(url, user, password);
			DatabaseMetaData dmd = conn.getMetaData();
			System.out.println(dmd.getDatabaseProductName());
			System.out.println(dmd.getDatabaseProductVersion());
			System.out.println(dmd.getDriverName());
			System.out.println(dmd.getDriverVersion());
			System.out.println(dmd.getURL());
			System.out.println(dmd.getUserName());
			
			stmt = conn.createStatement();
			String sql = "select id, name, registertime hiredate, email from emp";
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			for(int i = 1; i <= colCount; i++)
			{
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.println();
			for(int i = 1; i <= colCount; i++)
			{
				System.out.print(rsmd.getColumnTypeName(i) + "\t");
			}
			System.out.println();
			while(rs.next())
			{
				for(int j = 1; j <= colCount; j++)
				{
					System.out.print(rs.getString(rsmd.getColumnName(j)) + "\t");
				}
				System.out.println();
			}
			
			
			
			
			
			
			// 5���ر���Դ
			//rs.close();
			//stmt.close();
			conn.close();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
