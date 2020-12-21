package application;

import java.sql.*;
import java.util.Scanner;

public class DBConnection {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	Scanner scan = new Scanner(System.in);
	
	public DBConnection() {
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accommodation?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "barames123");
		st = con.createStatement();
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류(DBConnection()) : " + e.getMessage());
		}
	}
	
	public void createEachTable(String newID) {
		try
		{
			StringBuilder sb = new StringBuilder();
			String SQLCreateTable = sb.append("CREATE TABLE ")
					.append(newID)
					.append("(no INT NOT NULL,")
					.append("room_type VARCHAR(30) NOT NULL,")
					.append("name VARCHAR(30) NOT NULL,")
					.append("my_bidding_price FLOAT NOT NULL,")
					.append("winning_bid VARCHAR(10))")
					.append("DEFAULT CHARACTER SET = utf8")
					.toString();
			st.execute(SQLCreateTable);
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류 : " + e.getMessage());
		}
	}
	
	public void signUp() {
		try
		{
			System.out.println("Please input your new ID");
			String newID = scan.next();
			System.out.println("Please input your new Password");
			String newPW = scan.next();
			StringBuilder sb = new StringBuilder();
			String SQLSignUp = sb.append("INSERT INTO ALL_MEMBER VALUES(")
					.append("'"+newID+"',")
					.append("'"+newPW+"')")
					.toString();
			st.executeUpdate(SQLSignUp);
			createEachTable(newID);
			System.out.println("Your membership registration is complete.");
			
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류 : " + e.getMessage());
		}
	}
	
	public void logIn (String memberID, String memberPW) {
		try
		{
			StringBuilder sb = new StringBuilder();
			String SQLLogIn = sb.append("SELECT PASSWORD FROM ALL_MEMBER WHERE ID = ")
						.append("'"+memberID+"'")
						.toString();
			rs = st.executeQuery(SQLLogIn);
			if(rs.next()) {
				if(memberPW.equals(rs.getString(1))){
					System.out.println("Logged in successfully");
				} else{
					System.out.println("Login failed");
					System.exit(0);
				}
			}
			
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 검색오류"+e.getMessage());

		}
		
	}
	
	public void userAllRecord(String memberID) {
		try
		{
			StringBuilder sb = new StringBuilder();
			String SQL = sb.append("SELECT * FROM ")
					.append(memberID)
					.toString();
			
			rs = st.executeQuery(SQL);
			System.out.printf("%-4s %-15s\t %-20s\t %-20s %-10s", "no", "room_type", "name", "my_bidding_price", "winning_bid");
			while(rs.next()) {
				
				System.out.println();
				System.out.printf("%-5s", rs.getString(1));
				System.out.printf("%-15s\t", rs.getString(2));
				System.out.printf("%-30s\t", rs.getString(3));
				System.out.printf("%-15s\t", rs.getString(4));
				System.out.printf("%-20s\t", rs.getString(5));
				
			}
			System.out.println();
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류(userAllRecord): " + e.getMessage());
		}
		System.out.println();
	}
	
	public void userWonRecord(String memberID) {
		try
		{
			StringBuilder sb = new StringBuilder();
			String SQL = sb.append("SELECT * FROM ")
					.append(memberID)
					.append(" WHERE winning_bid = 'won'")
					.toString();
			
			rs = st.executeQuery(SQL);
			System.out.printf("%-4s %-15s\t %-20s\t %-20s %-10s", "no", "room_type", "name", "my_bidding_price", "winning_bid");
			while(rs.next()) {
				System.out.println();
				System.out.printf("%-5s", rs.getString(1));
				System.out.printf("%-15s\t", rs.getString(2));
				System.out.printf("%-30s\t", rs.getString(3));
				System.out.printf("%-15s\t", rs.getString(4));
				System.out.printf("%-20s\t", rs.getString(5));
				
			}
			System.out.println();
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류(userAllRecord): " + e.getMessage());
		}
		System.out.println();
	}
	
	public void all_accommo() {
		try
		{
			String SQL = "SELECT * FROM all_accommo" ;
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				System.out.println();
				System.out.printf("%-20s\t", rs.getString(1));
				System.out.printf("%-10s\t", rs.getString(2));
				System.out.printf("%-10s\t", rs.getString(3));
				System.out.printf("%-10s\t", rs.getString(4));
			}
			System.out.println();
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류" + e.getMessage());
		}
	}
	
	public void reservation() {
		try
		{
			String SQL = "SELECT * FROM reservation";
			rs = st.executeQuery(SQL);
			System.out.println();
			System.out.printf("%-3s %-16s %-17s\t %-20s\t %-30s\t %-29s %-15s\t","no", "type", "maximum", "name", "present_auction_price", "recommended_retail_price", "the_highest_bidder");
			while(rs.next()) {
				System.out.println();
				System.out.printf("%-3s", rs.getString(1));
				System.out.printf("%-20s\t", rs.getString(2));
				System.out.printf("%-10s\t", rs.getString(3));
				System.out.printf("%-30s\t", rs.getString(4));
				System.out.printf("%-30s\t", rs.getString(5));
				System.out.printf("%-24s\t", rs.getString(6));
				System.out.printf("%-15s\t", rs.getString(7));
			}
			System.out.println();
		}
		catch (Exception e) {
			System.out.println("데이터베이스 연결오류" + e.getMessage());
		}
	}
	
	public float present() {
		try
		{
			System.out.println("please input number you want");
			int no = scan.nextInt();
			StringBuilder sb = new StringBuilder();
			String SQLPresent = sb.append("select present_auction_price from reservation where no = ")
					.append(no)
					.toString();
			rs = st.executeQuery(SQLPresent);
			while(rs.next()) {
				return rs.getFloat(1);
			}
			
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류" + e.getMessage());

		}
		return 0.0f;

	}
	
	public float recommended(int no) {
		try
		{

			StringBuilder sb = new StringBuilder();
			String SQLPresent = sb.append("select recommended_retail_price from reservation where no = ")
					.append(no)
					.toString();
			rs = st.executeQuery(SQLPresent);
			while(rs.next()) {
				return rs.getFloat(1);
			}
			return 0.0f;
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류(recommended)" + e.getMessage());
			return 0.0f;
		}
	}
	
	public void the_highest_bidder(int no, String memberID) {
		try
		{
			StringBuilder sb = new StringBuilder();
			String SQLBiddingCheck = sb.append("UPDATE RESERVATION SET THE_HIGHEST_BIDDER = ")
					.append("'"+memberID+"'")
					.append("WHERE NO = ")
					.append(no)
					.toString();
			st.executeUpdate(SQLBiddingCheck);
			System.out.println("Successfully bid");
			reservation();
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류: " + e.getMessage());
		}
	}
	
	public void biddingCancel(int no) {
		try
		{
			StringBuilder sb = new StringBuilder();
			String SQLBiddingCheck = sb.append("UPDATE RESERVATION SET THE_HIGHEST_BIDDER = NULL WHERE NO = ")
					.append(no)
					.toString();
			st.executeUpdate(SQLBiddingCheck);
			System.out.println("Successfully canceled");
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류: " + e.getMessage());
		}
	}
	
	public void record(int no, float biddingMoney, String memberID, String strWin) {
		
		try
		{
			StringBuilder sb = new StringBuilder();
			String SQLRecord = sb.append("SELECT room_type, name FROM RESERVATION WHERE NO =")
					.append(no)
					.toString();
			rs = st.executeQuery(SQLRecord);
			if(rs.next()) {
				String roomType = rs.getString(1);
				String companyName = rs.getString(2);
				try
				{
					StringBuilder sb2 = new StringBuilder();
					String SQLInsert = sb2.append("INSERT INTO ")
							.append(memberID)
							.append(" VALUES(")
							.append(no+",")
							.append("'"+roomType+"',")
							.append("'"+companyName+"',")
							.append(biddingMoney+",")
							.append("'"+strWin+"')")
							.toString();
					st.executeUpdate(SQLInsert);
					System.out.println("성공");

				}
				catch (Exception e)
				{
					System.out.println("데이터베이스 연결오류(record): "+e.getMessage());
				}
			} else {}
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류: " + e.getMessage());
		}
	}

	public void winningBid(int no, float biddingMoney, String memberID) {
		try
		{
			String strWin = "won";
			record(no, biddingMoney, memberID, strWin);
			StringBuilder sb = new StringBuilder();
			String SQLWinningBid = sb.append("DELETE FROM RESERVATION WHERE no = ")
					.append(no)
					.toString();
			st.executeUpdate(SQLWinningBid);
			System.out.println("You won the auction!");
			
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류(winningBid): " + e.getMessage());
		}
	}
	
	public void bidding(int no, float biddingMoney, String memberID) {

		try {
			StringBuilder sb = new StringBuilder();
			
			String SQLPresent = sb.append("select present_auction_price from reservation where no = ")
					.append(no)
					.toString();
			
			rs = st.executeQuery(SQLPresent);
			
			
			while(rs.next()) {
				
				if (biddingMoney > rs.getFloat(1)) {			
					
					if (biddingMoney < recommended(no)) {
					
						try
						{
							StringBuilder sb2 = new StringBuilder();
							String SQLBidding = sb2.append("UPDATE RESERVATION SET PRESENT_AUCTION_PRICE = ")
									.append(biddingMoney)
									.append("WHERE NO = ")
									.append(no)
									.toString();
							st.executeUpdate(SQLBidding);
							the_highest_bidder(no, memberID);
							record(no, biddingMoney, memberID, "");
							break;
						}
						catch (Exception e) 
						{
							System.out.println("데이터베이스 연결오류(biddingMoney): " + e.getMessage());

						} 
						
					} else if (biddingMoney >= recommended(no)) {
						
						winningBid(no, biddingMoney, memberID);
						break;
						
					}
					
				} else if(biddingMoney < rs.getFloat(1)) { 
					System.out.println("Your input number is smaller than present auction price.");
					break;
				} 
			}

		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류(biddingMoney2): "+e.getMessage());
		}
		
	}

}











