package JDBC03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {

	DataBaseManager dbm = new DataBaseManager();

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ArrayList<MemberDTO> selectMember() {
		ArrayList<MemberDTO> list = new ArrayList<>();
		con = dbm.getConnection();

		String sql = "SELECT * FROM memberlist";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO mdto = new MemberDTO();
				mdto.setMembernum(rs.getInt("membernum"));
				mdto.setName(rs.getString("name"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setBirth(rs.getDate("birth")); // SQL의 DATE 형식 -> Java.sql.Date
				mdto.setBpoint(rs.getInt("bpoint"));
				mdto.setGender(rs.getString("gender"));
				mdto.setAge(rs.getInt("age"));
				list.add(mdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dbm.close(con, pstmt, rs);
		return list;
	}

	public int insertMember(MemberDTO mdto) {
		int result = 0;
		con = dbm.getConnection();
		String sql = "INSERT INTO memberlist(membernum, name, phone, birth, age, gender) "
				+ " VALUES(member_seq.nextVal, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getPhone());
			pstmt.setDate(3, mdto.getBirth());
			pstmt.setInt(4, mdto.getAge());
			pstmt.setString(5, mdto.getGender());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbm.close(con, pstmt, rs);

		return result;
	}

	public MemberDTO getMember(int memberNum) {
		MemberDTO mdto = null;
		con = dbm.getConnection();
		String sql = "SELECT * FROM memberlist WHERE membernum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mdto = new MemberDTO();
				mdto.setMembernum(rs.getInt("membernum"));
				mdto.setName(rs.getString("name"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setBirth(rs.getDate("birth"));
				mdto.setGender(rs.getString("gender"));
				mdto.setAge(rs.getInt("age"));				
				mdto.setBpoint(rs.getInt("bpoint"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbm.close(con, pstmt, rs);
		return mdto;
	}

	public int updateMember(MemberDTO mdto) {
		int result = 0;
		con = dbm.getConnection();
		String sql = "UPDATE memberlist SET name=?, phone=?, birth=?, age=?, bpoint=?, "
				+ " gender=? WHERE membernum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getPhone());
			pstmt.setDate(3, mdto.getBirth());
			pstmt.setInt(4, mdto.getAge());
			pstmt.setInt(5, mdto.getBpoint());
			pstmt.setString(6, mdto.getGender());
			pstmt.setInt(7, mdto.getMembernum());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbm.close(con, pstmt, rs);
		return result;
	}

	public int deleteMember(int memberNum) {
		int result = 0;
		con = dbm.getConnection();
		String sql = "DELETE FROM memberlist WHERE membernum=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbm.close(con, pstmt, rs);
		return result;
	}

}
