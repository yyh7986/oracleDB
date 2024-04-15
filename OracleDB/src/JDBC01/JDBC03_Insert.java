package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC03_Insert {

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
			
			System.out.print("번호를 입력하세요");
			int num = Integer.parseInt(sc.nextLine());
			System.out.print("이름을 입력하세요");
			String name = sc.nextLine();
			System.out.print("이메일을 입력하세요");
			String email = sc.nextLine();
			System.out.print("전화번호를 입력하세요");
			String phone = sc.nextLine();
			
			// String sql = "INSERT INTO CUSTOMER VALUES(6,'김하나','abc5@abc.com','010-4444-5555')";
			// 구식
			// String sql = "INSERT INTO CUSTOMER VALUES(" + num + ", '" + name + "', '" + email + "', '" + phone + "')";
			// 신식
			String sql = "INSERT INTO CUSTOMER VALUES(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			// 변수들이 물음표 순서에 맞게 매칭되어 구성된다.
			pstmt.setInt(1, num);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, phone);
			// setInt와 setString이 자동으로 필요한 따옴표('')를 붙여서 sql 명령을 구성해준다.
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) System.out.println("레코드 추가 성공");
			else System.out.println("레코드 추가 실패");
			
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