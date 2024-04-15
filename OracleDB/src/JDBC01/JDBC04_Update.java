package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC04_Update {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			Scanner sc = new Scanner(System.in);
			
			System.out.print("수정할 회원의 번호를 입력하세요");
			int num = Integer.parseInt(sc.nextLine());
			
			System.out.print("무엇을 수정할까요? (1: 이메일, 2: 전화번호)");
			int select = Integer.parseInt(sc.nextLine());
			
			String sql = "";
			String email = "";
			String phone = "";
			if(select == 1) {
				System.out.print("수정할 이메일을 입력하세요");
				email = sc.nextLine();
				sql = "UPDATE CUSTOMER SET EMAIL=? WHERE NUM=?";
			}else {
				System.out.print("수정할 전화번호를 입력하세요");
				phone = sc.nextLine();
				sql = "UPDATE CUSTOMER SET TEL=? WHERE NUM=?";
			}
			pstmt = con.prepareStatement(sql);
			
			if(select ==1) pstmt.setString(1, email);
			else pstmt.setString(1, phone);
			
			pstmt.setInt(2, num);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) System.out.println("수정 성공");
			else System.out.println("수정 실패");
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(con != null) con.close();
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
