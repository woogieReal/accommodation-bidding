package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	@FXML
	private Label LoginLabel;
	@FXML
	private TextField memberID;
	@FXML
	private PasswordField memberPW;	
	@FXML
	private Button LoginButton;
	
	
	
	public MainController() {
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
	
	
	public void logIn(ActionEvent event) throws Exception{
		try
		{
			StringBuilder sb = new StringBuilder();
			String SQLLogIn = sb.append("SELECT PASSWORD FROM ALL_MEMBER WHERE ID = ")
						.append("'"+memberID.getText()+"'")
						.toString();
			rs = st.executeQuery(SQLLogIn);
			if(rs.next()) {
				if(memberPW.getText().equals(rs.getString(1))){
					LoginLabel.setText("Welcome!");
					System.out.println("Logged in successfully");
				} else{
					System.out.println("Login failed");
					System.exit(0);
				}
			}
			
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 검색오류 logIn "+e.getMessage());

		}
	}
	
}
