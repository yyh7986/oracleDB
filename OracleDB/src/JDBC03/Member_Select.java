package JDBC03;

import java.util.ArrayList;

public class Member_Select {

	public static void main(String[] args) {
		
		MemberDAO mdao = new MemberDAO();
		ArrayList <MemberDTO> list = mdao.selectMember();
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("회원번호\t이름\t전화번호\t\t생년월일\t\t성별\t나이\t사은포인트");
		System.out.println("------------------------------------------------------------------------");
		for(MemberDTO mdto : list) {
			System.out.printf("%d\t%s\t%s\t%s\t%s\t%d\t%d\n", 
					mdto.getMembernum(), mdto.getName(), mdto.getPhone(), mdto.getBirth(),
					mdto.getGender(), mdto.getAge(), mdto.getBpoint());			
		}
		
		// %s 에는 String, int, double, Date 형식 모두 출력이 가능하다
	}

}
