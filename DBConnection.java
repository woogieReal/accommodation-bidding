package com.guthub.woogieReal;

import java.sql.*;

public class DBConnection {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnection() {
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accommodation?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "barames123");
		st = con.createStatement();
		System.out.println("데이터베이스 연결완료");
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 연결오류 : " + e.getMessage());
		}
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
			System.out.printf("%-3s %-16s %-17s\t %-20s\t %-30s\t %-30s\t","no", "type", "maximum", "name", "present_auction_price", "recommended_retail_price");
			while(rs.next()) {
				System.out.println();
				System.out.printf("%-3s", rs.getString(1));
				System.out.printf("%-20s\t", rs.getString(2));
				System.out.printf("%-10s\t", rs.getString(3));
				System.out.printf("%-30s\t", rs.getString(4));
				System.out.printf("%-30s\t", rs.getString(5));
				System.out.printf("%-30s\t", rs.getString(6));
			}
			System.out.println();
		}
		catch (Exception e) {
			System.out.println("데이터베이스 연결오류" + e.getMessage());
		}
	}
}
