package com.guthub.woogieReal;

import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		DBConnection connection = new DBConnection();
		
		System.out.println("Welcome to our website!");
		System.out.println("Here are some products you can bid now");
		connection.reservation();
				
		System.out.println();
		System.out.println("Please input number you want to bid");
		int no = scan.nextInt();
		System.out.println("How much do you want to bid?");
		float biddingMoney = scan.nextFloat();
		
		connection.bidding(no, biddingMoney);
		connection.reservation();
	
		
		
		
		

	}

}
