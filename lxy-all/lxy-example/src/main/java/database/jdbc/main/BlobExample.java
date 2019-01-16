package database.jdbc.main;

import database.jdbc.util.OracleDBHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlobExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insert();
		select();
	}

	/**
	 * 
	 */
	public static void select() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = OracleDBHandler.OpenConnection();
			conn.setAutoCommit(false);
			String sql = "select id, name, photo from student_blob " +
					"where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 53);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				InputStream is = rs.getBinaryStream("photo");
				FileOutputStream fos = new FileOutputStream(new File("resources/photo.jpg"));
				byte[] buffer = new byte[4 * 1024];
				int length = 0;
				while( -1 != (length = is.read(buffer)))
				{
					fos.write(buffer, 0, length);
				}
				fos.flush();
				fos.close();
				is.close();
			}
			
			System.out.println("��ѯ�ɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	public static void insert() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = OracleDBHandler.OpenConnection();
			conn.setAutoCommit(false);
			String sql = "insert into student_blob (id, name, photo)" +
					"values (seq_emp.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "caches");
			File photo = new File("resources/IMG_0701.JPG");
			FileInputStream fis = new FileInputStream(photo);
			pstmt.setBinaryStream(2, fis, (int)photo.length());
			int result = pstmt.executeUpdate();
			conn.commit();
			System.out.println("����" + result + " " + "һ��");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
