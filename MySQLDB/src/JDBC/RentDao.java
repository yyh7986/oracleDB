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

	public int memberOk(int membernum) {
		int result = 0;
		con = Dbman.getConnection();
//		String sql = "SELECT COUNT(*) AS CNT FROM memberlist WHERE membernum=?";
		String sql = "SELECT * FROM memberlist WHERE membernum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, membernum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
//				result = rs.getInt("CNT");
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		return result;
	}

	public int bookOk(int booknum) {
		int result = 0;
		con = Dbman.getConnection();
		String sql = "SELECT * FROM booklist WHERE booknum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, booknum);
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

	public RentDto getRentInfo(int rentNum) {
		RentDto rdto = null;
		con = Dbman.getConnection();
		String sql = "SELECT * FROM rentDetail WHERE numseq=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rentNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rdto = new RentDto();
				rdto.setAmount(rs.getInt("amount"));
				rdto.setBnum(rs.getInt("bnum"));
				rdto.setDiscount(rs.getInt("discount"));
				rdto.setMnum(rs.getInt("mnum"));
				rdto.setName(rs.getString("name"));
				rdto.setNumseq(rs.getInt("numseq"));
				rdto.setRentdate(rs.getString("rentdate"));
				rdto.setRentprice(rs.getInt("rentprice"));
				rdto.setSubject(rs.getString("subject"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Dbman.close(con, pstmt, rs);
		return rdto;
	}

	public int updateRent(RentDto rdto) {
		int result = 0;
		con = Dbman.getConnection();
		String sql = "UPDATE rentlist SET rentdate=str_to_date(concat('', ?, ''), '%Y-%m-%d'), "
				+ " bnum=?, mnum=?, discount=? WHERE numseq=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rdto.getRentdate());
			pstmt.setInt(2, rdto.getBnum());
			pstmt.setInt(3, rdto.getMnum());
			pstmt.setInt(4, rdto.getDiscount());
			pstmt.setInt(5, rdto.getNumseq());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		return result;
	}

	public int deleteRent(RentDto rdto) {
		int result = 0;
		con = Dbman.getConnection();
		String sql = "DELETE FROM rentlist WHERE numseq=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rdto.getNumseq());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
		return result;
	}
}
