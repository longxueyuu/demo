package database.jdbc.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.jdbc.util.MysqlDBHandler;
import database.jdbc.util.OracleDBHandler;

public class BatchExample {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = OracleDBHandler.OpenConnection();
			// ���������Զ��ύ
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql1 = "insert into emp (id, name)" +
					"values (seq_emp.nextval, 'wangnima')";
			String sql2 = "insert into emp (id, name)" +
			"values (seq_emp.nextval, 'wangmitao')";
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			int[] result = stmt.executeBatch();
			conn.commit();
			for(int i : result)
			{
				System.out.println("result:" + i);
			}
			
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		} finally{
			stmt.close();
			try {
				OracleDBHandler.closeConnection(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

}
