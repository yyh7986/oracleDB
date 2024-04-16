package JDBC03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Member_Update {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("수정할 회원의 번호를 입력하세요 : ");
		int memberNum = Integer.parseInt(sc.nextLine());
		
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = mdao.getMember(memberNum);
		
		if(mdto == null) {
			System.out.println("입력하신 번호의 회원이 존재하지 않습니다\n프로그램을 종료합니다.");
			return;
		}

		String input = "";
		
		System.out.printf("이름 : %s \n수정할 이름을 입력하세요(수정하지 않으려면 Enter입력) ", mdto.getName());
		input = sc.nextLine();
		if(!input.equals("")) mdto.setName(input);
		
		System.out.printf("전화번호 : %s\n", mdto.getPhone());
		System.out.println("수정할 전화번호을 입력하세요(수정하지 않으려면 Enter입력)");
		input = sc.nextLine();
		if(!input.equals("")) mdto.setPhone(input);
		
		System.out.printf("성별 : %s\n", mdto.getGender());
		System.out.println("수정할 성별을 입력하세요(수정하지 않으려면 Enter입력)");
		input = sc.nextLine();
		if(!input.equals("")) mdto.setGender(input);
		
		System.out.printf("사은포인트 : %d\n", mdto.getBpoint());
		System.out.println("수정할 사은포인트를 입력하세요(수정하지 않으려면 Enter입력)");
		int bpoint = Integer.parseInt(sc.nextLine());
		if(!input.equals("")) mdto.setBpoint(bpoint);
		
		System.out.printf("생년월일 : %s\n", mdto.getBirth());
		System.out.println("수정할 생일을 입력하세요(YYYY-MM-DD) (수정하지 않으려면 Enter입력)");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		input = sc.nextLine();
		if(!input.equals("")) {
			java.util.Date d = sdf.parse(input);
			java.sql.Date birth = new java.sql.Date(d.getTime());
			mdto.setBirth(birth);
			Calendar c = Calendar.getInstance();
			Calendar today = Calendar.getInstance();
			c.setTime(d);
			int age = today.get(Calendar.YEAR) - c.get(Calendar.YEAR);
			mdto.setAge(age);
		}
		
		int result = mdao.updateMember(mdto);
		if(result == 1) System.out.println("레코드 수정 성공");
		else System.out.println("레코드 수정 실패");
		

	}

}
