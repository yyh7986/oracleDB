package JDBC;

import java.util.Scanner;

public class RentList_Delete {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		RentDao rdao = RentDao.getInstance();
		
		System.out.print("수정할 대여목록의 대여번호를 입력하세요 : ");
		int numseq = Integer.parseInt(sc.nextLine());
		RentDto rdto = rdao.getRentInfo(numseq);
		if(rdto == null) {
			System.out.println("존재하지 않는 대여번호입니다. 프로그램을 종료합니다.");
			return;
		}
		int result = rdao.deleteRent(rdto);
		if(result ==1) System.out.println("삭제 성공");
		else System.out.println("삭제 실패");
	}

}
