package com.github.woogieReal;

import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		DBConnection connection = new DBConnection();

		zero: while (true) {
			System.out.println("Sign up : 1 / LogIn : 2 / exit : 0");
			int signOrLog = scan.nextInt();

			if (signOrLog > 2 || signOrLog < 0) {
				System.out.println("Please input number 1 or 2");
			} else if (signOrLog == 0) {
				System.out.println("See you again");
				System.exit(0);
			}
			while (signOrLog == 1) {
				connection.signUp();
				signOrLog++;
			}
			// LogIn
			System.out.println("Please input your ID");
			String memberID = scan.next();
			System.out.println("Please input your Password");
			String memberPW = scan.next();
			connection.logIn(memberID, memberPW);

			first: while (true) {
				System.out.println("User own page : 1 / Bidding page : 2 / exit : 0");
				int ownOrBidding = scan.nextInt();
				switch (ownOrBidding) {
				case 1:
					while (true) {
						System.out.println("All records : 1 / Winning bid records : 2 / exit : 0");
						int allOrWinning = scan.nextInt();
						if (allOrWinning == 1) {
							// Showing user all records
							connection.userAllRecord(memberID);
						} else if (allOrWinning == 2) {
							// Showing user winning bid records;
							connection.userWonRecord(memberID);
						} else if (allOrWinning == 0) {
							continue first;
						} else {
							System.out.println("Please input number 1 or 2");
						}
					}
				case 2:
					System.out.println("Here are some products you can bid now");
					// Showing All Product
					connection.reservation();
					System.out.println();
					while (true) {
						// Bidding
						System.out.println("Please input number you want to bid / exit : 0");
						int bidOrExit = scan.nextInt();
						if (bidOrExit == 0) {
							continue first;
						}
						System.out.println("How much do you want to bid?");
						float biddingMoney = scan.nextFloat();
						connection.bidding(bidOrExit, biddingMoney, memberID);
					}
				case 0:
					continue zero;
				}
			}
		}

	}

}
