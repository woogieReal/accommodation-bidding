package com.guthub.woogieReal;

import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		DBConnection connection = new DBConnection();
		
		connection.all_accommo();
		connection.reservation();
		
	
		
		
		
		

	}

}
