package JDBC02;

import java.util.ArrayList;

public class Book_Select {

	public static void main(String[] args) {

		// DAO : Data Access Object
		BookDAO bdao = new BookDAO();
		
		// DTO : Data Transfer Object
		ArrayList<BookDTO> list = bdao.getBooklist();
		
		System.out.println("도서번호\t출판년도\t입고가격\t대여가격\t제목");
		System.out.println("-------------------------------------------------------");
		for(BookDTO bdto : list) {
			System.out.printf("%5d\t%d\t%d\t%d\t%s\n",
					bdto.getBooknum(), bdto.getMakeyear(), bdto.getInprice(), bdto.getRentprice(), bdto.getSubject());
		}
	}

}
