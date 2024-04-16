package JDBC02;

import java.util.Scanner;

public class Book_Insert {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		BookDTO bdto = new BookDTO();
		
		System.out.print("제목을 입력하세요 : ");
		bdto.setSubject(sc.nextLine());
		System.out.print("출판연도를 입력하세요 : ");
		bdto.setMakeyear(Integer.parseInt(sc.nextLine()));
		System.out.print("입고가격을 입력하세요 : ");
		bdto.setInprice(Integer.parseInt(sc.nextLine()));
		System.out.print("대여가격을 입력하세요 : ");
		bdto.setRentprice(Integer.parseInt(sc.nextLine()));
		
		BookDAO bdao = new BookDAO();
		int result = bdao.insertBook(bdto);
		
		if(result == 1) System.out.println("추가 성공");
		else System.out.println("추가 실패");

	}

}
