package JDBC;

import java.util.ArrayList;

public class RentList_Select {

	public static void main(String[] args) {
		
		RentDao rdao = RentDao.getInstance();
		ArrayList<RentDto> list = rdao.selectRent();
	}

}
