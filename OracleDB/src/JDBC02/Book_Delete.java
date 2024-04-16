package JDBC02;

import java.util.Scanner;

public class Book_Delete {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 도서의 도서번호를 입력하세요");
		int booknum = Integer.parseInt(sc.nextLine());
		BookDAO bdao = new BookDAO();
		BookDTO bdto = bdao.getBook(booknum);
		if(bdto == null) {
			System.out.println("입력한 도서번호의 도서가 존재하지 않습니다\n프로그램을 종료합니다");
			return;
		}
		int result = bdao.deleteBook(booknum);

		if(result == 1) System.out.println("삭제 성공");
		else System.out.println("삭제 실패");
	}

}
