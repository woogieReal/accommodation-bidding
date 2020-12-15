package com.github.woogieReal;

import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		DBConnection connection = new DBConnection();
		
		System.out.println();
		System.out.println();
		
		//sign up
		//connection.signUp();
		
		//LogIn
		System.out.println("Please input your ID");
		String memberID = scan.next();
		System.out.println("Please input your Password");
		String memberPW = scan.next();
		connection.logIn(memberID, memberPW);
		
		//Showing user table;
		connection.userAllRecord(memberID);
		
		//Showing user won record;
		//connection.userWonRecord(memberID);
		
		//Showing All Product
		System.out.println("Here are some products you can bid now");
		connection.reservation();
		
		//Bidding
		System.out.println();
		System.out.println("Please input number you want to bid");
		int no = scan.nextInt();
		System.out.println("How much do you want to bid?");
		float biddingMoney = scan.nextFloat();
		connection.bidding(no, biddingMoney, memberID);
		
		
	

		

	}

}
