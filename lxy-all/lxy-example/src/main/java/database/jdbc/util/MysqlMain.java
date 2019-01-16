package database.jdbc.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String sql = "select i.* from input i where i.ivalue > ?";
		MysqlDBHandler dbhandler = new MysqlDBHandler(sql);
		PreparedStatement ps = dbhandler.getPs();
		ps.setDouble(1, 2000);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			String inputid = rs.getString("inputid");
			String ivalue = rs.getString("ivalue");
			String receipt = rs.getString("receipt");
			String idate = rs.getString("idate");
			String childtype = rs.getString("childtype");
			String inflag = rs.getString("inflag");
			String postscript = rs.getString("postscript");
			System.out.println(inputid + " " + ivalue + " " 
					+ receipt + " " + idate + " " + childtype + " "
					+ inflag + " " + postscript);
		}
		
	}
}
