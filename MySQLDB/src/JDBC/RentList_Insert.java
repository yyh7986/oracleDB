package JDBC;

import java.util.Scanner;

public class RentList_Insert {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		RentDao rdao = RentDao.getInstance();
		
		System.out.println("===== 대여목록 추가 =====");
		
		String membernum;
		while(true) {
			System.out.print("대여할 회원의 회원번호를 입력하세요 : ");
			membernum = sc.nextLine();
			int result = rdao.memberOk(membernum);	// 입력한 멤버번호가 존재하면 1, 없으면 0을 리턴
			if(result == 0) {
				System.out.println("존재하지 않는 회원번호입니다. 다시 입력하세요");
				continue;
			}else {
				break;
			}
		}
		System.out.println("입력된 회원 번호 : " + membernum + "\n");
		
		String booknum;
		while(true) {
			System.out.print("대여할 도서의 도서번호를 입력하세요 : ");
			booknum = sc.nextLine();
			int result = rdao.bookOk(booknum);	// 입력한 도서번호가 존재하면 1, 없으면 0을 리턴
			if(result == 0) {
				System.out.println("존재하지 않는 도서번호입니다. 다시 입력하세요");
				continue;
			}else {
				break;
			}
		}
		System.out.println("입력된 도서 번호 : " + booknum + "\n");
		
		System.out.print("할인금액을 입력하세요 : ");
		int discount = Integer.parseInt(sc.nextLine());
		
		RentDto rdto = new RentDto();
		rdto.setMnum(Integer.parseInt(membernum));
		rdto.setBnum(Integer.parseInt(booknum));
		rdto.setDiscount(discount);
		
		int result = rdao.insertRent(rdto);
		if(result == 1) System.out.println("추가 성공");
		else System.out.println("추가 실패");
		
	}
}
