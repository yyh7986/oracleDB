package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RentDao {
	
	private RentDao() {}
	private static RentDao itc= new RentDao();
	public static RentDao getInstance() {
		return itc;
	}

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<RentDto> selectRent() {
		ArrayList<RentDto> list = new ArrayList<RentDto>();
		con = Dbman.getConnection();
	
		String sql = "";
		
		Dbman.close(con, pstmt, rs);
		return list;
	}
}
