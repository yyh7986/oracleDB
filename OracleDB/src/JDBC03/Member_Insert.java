package JDBC03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Member_Insert {

	public static void main(String[] args) throws ParseException {

		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름을 입력하세요 -> ");
		String name = sc.nextLine();
		mdto.setName(name);
		
		System.out.print("전화번호을 입력하세요 -> ");
		mdto.setPhone(sc.nextLine());
		
		System.out.println("성별을 입력하세요(M/F) -> ");
		mdto.setGender(sc.nextLine());
		
		// bpoint 는 default 값이 0이므로 별도 입력 받지 않는다.
		
		// 생년월일 입력 - simpleDateFormat 과 Exception 을 이용하여 입력을 받을 예정
		// String 으로 입력 받아서 java.util.Date 형식으로 변환 후 java.sql.Date로 변환
		// 그래야 memberlist 테이블의 birth(Date 자료형)필드에 insert가 가능하다
		
		// SimpleDateFormat class : 날짜 데이터(java.util.Date)의 형식을 지정하여 표현해주는 클래스
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// sdf 객체에 날짜데이터 하나를 적용하면 "yyyy-MM-dd" 양식으로 날짜데이터를 설정하여 사용할 수 있다
		// 또한 SimpleDateFormat 클래스의 parse 메서드는 String 양식의 날짜데이터를
		// 실제 java.util.Date 로 변환해 준다
		
		System.out.print("생년월일을 입력하세요(YYYY-MM-DD) -> ");
		String input = sc.nextLine();
		java.util.Date d = sdf.parse(input);
		
		// java.util.Date 를 java.sql.Date 로 변환
		// d.getTime() 을 java.sql.Date 의 생성자의 전달인수로 넣는다
		java.sql.Date birth = new java.sql.Date(d.getTime());
		mdto.setBirth(birth);
		
		Calendar today = Calendar.getInstance();	// 오늘 날짜
		Calendar c = Calendar.getInstance();
		c.setTime(d);	// java.util.Date -> Calendar 변환
		int age = today.get(Calendar.YEAR) - c.get(Calendar.YEAR);
		mdto.setAge(age);
		
		int result = mdao.insertMember(mdto);
		if(result == 1) System.out.println("레코드 추가 성공");
		else System.out.println("레코드 추가 실패");
	}

}
