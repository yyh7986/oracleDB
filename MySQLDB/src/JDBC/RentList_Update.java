package JDBC;

import java.util.Scanner;

public class RentList_Update {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		RentDao rdao = RentDao.getInstance();
		RentDto rdto = null;

		// 수정할 대여기록의 번호 입력
		System.out.println("===== 도서 대여 목록 수정 =====");
		System.out.print("수정할 대여목록의 대여번호를 입력하세요 : ");
		int rentNum = Integer.parseInt(sc.nextLine());

		// 해당 번호에 기록이 있는지 확인. 없다면 종료
		rdto = rdao.getRentInfo(rentNum);
		if(rdto == null) {
			System.out.println("해당 번호의 대여기록이 존재하지 않습니다. 프로그램을 종료합니다.");
			return;
		}
		


		// 수정할 대여날짜를 입력
		System.out.printf("날짜 : %s\n수정할 날짜를 입력하세요(yyyy-mm-dd)(수정안함:ENTER) : ", rdto.getRentdate());
		String rentdate = sc.nextLine();
		if(!rentdate.equals("")) {
			rdto.setRentdate(rentdate);
		}

		// 수정할 도서번호를 입력. 입력한 번호의 도서가 없다면 다시 입력
		System.out.printf("도서번호 : %d\n수정할 도서번호를 입력하세요(수정안함:ENTER) : ", rdto.getBnum());
		String booknum = sc.nextLine();
		if(!booknum.equals("")) {
			rdto.setBnum(Integer.parseInt(booknum));
		}

		// 수정할 회원번호를 입력. 입력한 번호의 회원이 없다면 다시 입력
		System.out.printf("회원번호 : %d\n수정할 회원번호를 입력하세요(수정안함:ENTER) : ", rdto.getMnum());
		String membernum = sc.nextLine();
		if(!membernum.equals("")) {
			rdto.setMnum(Integer.parseInt(membernum));
		}

		// 수정할 할인금액 입력
		System.out.printf("할인금액 : %d\n수정할 할인금액을 입력하세요(수정안함:ENTER) : ", rdto.getDiscount());
		String discount = sc.nextLine();
		if(!discount.equals("")) {
			rdto.setDiscount(Integer.parseInt(discount));
		}
		
		// RentDto에 모두담아 updateRent 메서드에 전송
		int result = rdao.updateRent(rdto);
		if (result == 1)
			System.out.println("수정 성공");
		else
			System.out.println("수정 실패");
	}
}