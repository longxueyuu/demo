package database.jdbc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

public class PreparedStatementExample {
	
	private static Connection conn;
	private static PreparedStatement preStmt;
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
			
			String sql = "insert into emp values(?, ?, ?, ?)";
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, 12);
			preStmt.setString(2, "test");
			preStmt.setDate(3, new java.sql.Date(new Date().getTime()));
			preStmt.setString(4, "test@163.com");
			int x = preStmt.executeUpdate();
			if(x == 1)
			{
				System.out.println("����" + x + "��");
			}else{
				System.out.println("����ʧ�ܣ�");
			}
			
			/*
			 * preparedstatement��ѯ����
			String sql = "select id, name, registertime hiredate, email from emp where id = ? or name = ? order by id";
			// ��ȡPreparedStatement����
			preStmt = conn.prepareStatement(sql);
			// 1:��һ������ 8����һ���ʺŵ�ֵ
			preStmt.setInt(1, 8);
			// 2:�ڶ�������"xiaoyujiaxue"���ڶ����ʺŵ�ֵ
			preStmt.setString(2, "xiaoyujiaxue");
			
			rs = preStmt.executeQuery();
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
			*/
			
			
			
			
			
			
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			// 5���ر���Դ
			try {
				preStmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
