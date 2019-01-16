package database.jdbc.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDBMain2 {

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
			Connection conn = DriverManager.getConnection(url, user, password);
			// 3�����������
			Statement stmt = conn.createStatement();
			// 4�����������󣬲����ؽ��
			String insertsql = "insert into emp (id, name, email) values (1000, 'xiaoyujiaxue', 'xyz@163.com')";
			String querysql = "select id, name, registertime, email from emp order by id";
			// stmt.execute(insertsql);
			//����resultset�а�������¼��
			stmt.setMaxRows(5);
			ResultSet rs = stmt.executeQuery(querysql);
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date registertime = rs.getDate("registertime");//���ｨ������java.sql.Date��
				String email = rs.getString("email");
				System.out.println(id + " " + name + " " + registertime + " " + email);
			}
			
			// 5���ر���Դ
			rs.close();
			stmt.close();
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
