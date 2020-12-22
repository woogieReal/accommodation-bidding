package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	//common page
	@FXML
	private AnchorPane AlertAnchorPane;
	@FXML
	private TextField AlertTextField;
	@FXML
	private Button AlertOKButton;
	
	//login page
	@FXML
	private AnchorPane LoginAnchorPane;
	@FXML
	private Label LoginLabel;
	@FXML
	private TextField memberID;
	@FXML
	private PasswordField memberPW;	
	@FXML
	private Button LoginButton;
	@FXML
	private Button SignUpButton;
	
	//sign up page
	@FXML
	private AnchorPane SignUpAnchorPane;
	@FXML
	private Label SignUpLabel;
	@FXML
	private TextField NewMemberID;
	@FXML
	private PasswordField NewMemberPW;	
	@FXML
	private PasswordField ReNewMemberPW;	
	@FXML
	private Button SignUpButtonInSignUp;
	@FXML
	private Button CancelButton;
	
	//select page
	@FXML
	private AnchorPane SelectAnchorPane;
	@FXML
	private Button LogOutButton;
	
	
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
	
	public void createEachTable(String newMember) {
		try
		{
			StringBuilder sb = new StringBuilder();
			String SQLCreateTable = sb.append("CREATE TABLE ")
					.append(newMember)
					.append("(no INT NOT NULL,")
					.append("room_type VARCHAR(30) NOT NULL,")
					.append("name VARCHAR(30) NOT NULL,")
					.append("my_bidding_price FLOAT NOT NULL,")
					.append("winning_bid VARCHAR(10))")
					.append("DEFAULT CHARACTER SET = utf8")
					.toString();
			st.execute(SQLCreateTable);
		} catch (Exception e)
		{
			System.out.println("createEachTable error : " + e.getMessage());
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
					SelectAnchorPane.setVisible(true);
					LoginAnchorPane.setVisible(false);
					memberID.setText("");
					memberPW.setText("");
					
				} else{
					AlertTextField.setText("You've entered wrong password.");
					AlertAnchorPane.setVisible(true);
				}
			} else {AlertTextField.setText("You've entered wrong ID.");
			AlertAnchorPane.setVisible(true);}
			
		}
		catch (Exception e)
		{
			System.out.println("데이터베이스 검색오류 logIn : "+e.getMessage());

		}
	}
	
	public void logOut(ActionEvent event) {
		try
		{
			LoginAnchorPane.setVisible(true);
			SelectAnchorPane.setVisible(false);
		} catch (Exception e)
		{
			System.out.println("Log out error : "+e.getMessage());

		}
	}
	
	public void signUpButton(ActionEvent event) {
		try
		{
			SignUpAnchorPane.setVisible(true);
			LoginAnchorPane.setVisible(false);
			memberID.setText("");
			memberPW.setText("");
		} catch (Exception e)
		{
			System.out.println("Sign up button error : "+e.getMessage());

		}
	}
	
	public void cancelInSignUp(ActionEvent event) {
		try
		{
			LoginAnchorPane.setVisible(true);
			SignUpAnchorPane.setVisible(false);
			NewMemberID.setText("");
			NewMemberPW.setText("");
			ReNewMemberPW.setText("");
		} catch (Exception e)
		{
			System.out.println("cancel button error : "+e.getMessage());

		}
	}
	
	public void signUp(ActionEvent event) {
		try
		{
			if(NewMemberPW.getText().equals(ReNewMemberPW.getText())) {
			String newMember = NewMemberID.getText();
			StringBuilder sb = new StringBuilder();
			String SQLSignUp = sb.append("INSERT INTO ALL_MEMBER VALUES(")
					.append("'"+NewMemberID.getText()+"',")
					.append("'"+NewMemberPW.getText()+"')")
					.toString();
			st.executeUpdate(SQLSignUp);
			AlertTextField.setText("Your membership registration is complete.");
			AlertAnchorPane.setVisible(true);
			SignUpAnchorPane.setVisible(false);
			NewMemberID.setText("");
			NewMemberPW.setText("");
			ReNewMemberPW.setText("");
			LoginAnchorPane.setVisible(true);
			System.out.println("Your membership registration is complete.");
			createEachTable(newMember);
			} else {
				AlertTextField.setText("Please enter the same password");
				AlertAnchorPane.setVisible(true);
			}
		} catch (Exception e) {
			System.out.println("sign up error : "+e.getMessage());
			AlertTextField.setText("You've enterd duplicate information.");
			AlertAnchorPane.setVisible(true);
		}
    }
	
	public void cancelAlertButton(ActionEvent event) {
		try
		{
			AlertAnchorPane.setVisible(false);
			AlertTextField.setText("");		
		} catch (Exception e) {
			System.out.println("cancelAlertButton eroor : "+e.getMessage());
		}
	}
	
}









