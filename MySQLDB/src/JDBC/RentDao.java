package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sql = "SELECT * FROM rentDetail ORDER BY numseq DESC";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RentDto rdto = new RentDto();
				rdto.setNumseq(rs.getInt("numseq"));
				rdto.setRentdate(rs.getString("rentdate"));
				rdto.setBnum(rs.getInt("bnum"));
				rdto.setSubject(rs.getString("subject"));
				rdto.setMnum(rs.getInt("mnum"));
				rdto.setName(rs.getString("name"));
				rdto.setRentprice(rs.getInt("rentprice"));
				rdto.setDiscount(rs.getInt("discount"));
				rdto.setAmount(rs.getInt("amount"));
				list.add(rdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);			
		}
		return list;
	}

	public int memberOk(String membernum) {
		int result = 0;
		con = Dbman.getConnection();
//		String sql = "SELECT COUNT(*) AS CNT FROM memberlist WHERE membernum=?";
		String sql = "SELECT * FROM memberlist WHERE membernum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(membernum));
			rs = pstmt.executeQuery();
			if(rs.next()) {
//				result = rs.getInt("CNT");
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dbman.close(con, pstmt, rs);
		return result;
	}

	public int bookOk(String booknum) {
		int result = 0;
		con = Dbman.getConnection();
		String sql = "SELECT * FROM booklist WHERE booknum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(booknum));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dbman.close(con, pstmt, rs);
		return result;
	}

	public int insertRent(RentDto rdto) {
		int result = 0;
		con = Dbman.getConnection();
		String sql = "INSERT INTO rentlist(mnum, bnum, discount) "
				+ " VALUES(?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rdto.getMnum());
			pstmt.setInt(2, rdto.getBnum());
			pstmt.setInt(3, rdto.getDiscount());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		return result;
	}
}
