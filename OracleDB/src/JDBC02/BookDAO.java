package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//DAO : Data Access Object
public class BookDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String id = "scott";
	String pw = "tiger";

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void close() {
		try {
			if (con != null)
				con.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// booklist 테이블의 모든 데이터를 리턴해주는 메서드
	// BookDTO에 레코드 하나씩 저장 -> BookDTO 들이 ArrayList에 저장되서 리턴
	public ArrayList<BookDTO> getBooklist() {

		// 데이터베이스에 연결
		con = getConnection();
		ArrayList<BookDTO> list = new ArrayList<>();

		// 테이블내용 조회
		String sql = "SELECT * FROM booklist ORDER BY booknum DESC";

		// 조회내용을 정리해서 리턴
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BookDTO bdto = new BookDTO();
				int booknum = rs.getInt("booknum");
				bdto.setBooknum(booknum);
				bdto.setSubject(rs.getString("subject"));
				bdto.setMakeyear(rs.getInt("makeyear"));
				bdto.setInprice(rs.getInt("inprice"));
				bdto.setRentprice(rs.getInt("rentprice"));
				list.add(bdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 데이터베이스 연결 종료
		close();

		return list;
	}

	public int insertBook(BookDTO bdto) {
		int result = 0;
		con = getConnection();
		String sql = "INSERT INTO booklist(booknum, subject, makeyear, inprice, rentprice) "
				+ " VALUES (book_seq.nextVal,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getSubject());
			pstmt.setInt(2, bdto.getMakeyear());
			pstmt.setInt(3, bdto.getInprice());
			pstmt.setInt(4, bdto.getRentprice());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public BookDTO getBook(int booknum) {
		BookDTO bdto = null;
		con = getConnection();
		String sql = "SELECT * FROM booklist WHERE booknum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, booknum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bdto = new BookDTO();
				bdto.setBooknum(rs.getInt("booknum"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setMakeyear(rs.getInt("makeyear"));
				bdto.setInprice(rs.getInt("inprice"));
				bdto.setRentprice(rs.getInt("rentprice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return bdto;
	}

	public int updateBook(BookDTO bdto) {
		int result = 0; 
		con = getConnection();	
		String sql = "UPDATE booklist SET subject=?, makeyear=?, inprice=?, rentprice=? "
				+ " WHERE booknum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getSubject());
			pstmt.setInt(2, bdto.getMakeyear());
			pstmt.setInt(3, bdto.getInprice());
			pstmt.setInt(4, bdto.getRentprice());
			pstmt.setInt(5, bdto.getBooknum());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		close();
		return result;
	}

	public int deleteBook(int booknum) {
		int result = 0;
		con = getConnection();
		String sql = "DELETE FROM booklist WHERE booknum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, booknum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		
		return result;
	}

}
