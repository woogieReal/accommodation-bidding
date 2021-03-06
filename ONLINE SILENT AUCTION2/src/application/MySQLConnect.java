package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MySQLConnect {

	Connection conn = null;

	public static Connection connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/accommodation?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "barames123");
			return conn;
		} catch (Exception e) {
			System.out.println("데이터베이스 연결오류(DBConnection()) : " + e.getMessage());
			return null;
		}
	}

	public static ObservableList<Member> getDataMember(String member) {

		Connection conn = connectDB();
		ObservableList<Member> list = FXCollections.observableArrayList();
		try {
			String SQL = "SELECT * FROM " + member;
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				list.add(new Member(Integer.parseInt(rs.getString("no")), rs.getString("room_type"),
						rs.getString("name"), Float.parseFloat(rs.getString("my_bidding_price")),
						rs.getString("winning_bid")));

			}
		} catch (Exception e) {
			System.out.println(member + e.getMessage());
		}

		return list;
	}

	public static ObservableList<Reservation> getDataReservation() {

		Connection conn = connectDB();
		ObservableList<Reservation> list = FXCollections.observableArrayList();
		try {
			String SQL = "SELECT * FROM reservation";
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				list.add(new Reservation(Integer.parseInt(rs.getString("no")), rs.getString("room_type"),
						Integer.parseInt(rs.getString("maximum")), rs.getString("name"),
						Float.parseFloat(rs.getString("present_auction_price")),
						Float.parseFloat(rs.getString("recommended_retail_price")),
						rs.getString("the_highest_bidder")));

			}
		} catch (Exception e) {
			System.out.println("Reservation" + e.getMessage());
		}

		return list;
	}

}
