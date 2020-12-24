package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class MainController {

	Connection con;
	Statement st;
	ResultSet rs;
	
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
	@FXML
	private Button UserOwnPageButton;
	@FXML
	private Button BiddingPageButton;
	
	//user own page
	@FXML
	private AnchorPane UserAnchorPane;
	@FXML
	private TableView<Member> userTableView;
	@FXML
	private TableColumn<Member, Integer> noColumn;
	@FXML
	private TableColumn<Member, String> room_typeColumn;
	@FXML
	private TableColumn<Member, String> nameColumn;
	@FXML
	private TableColumn<Member, Float> my_bidding_priceColumn;
	@FXML
	private TableColumn<Member, String> winning_bidColumn;
	@FXML
	private Button BackButtonInUser;
	@FXML
	private TextField UserNameTextField;
	
	//bidding page
	@FXML
	private AnchorPane BiddingAnchorPane;
	@FXML
	private TableView<Reservation> BiddingTableView;
	@FXML
	private TableColumn<Reservation, Integer> noColumn2;
	@FXML
	private TableColumn<Reservation, String> room_typeColumn2;
	@FXML
	private TableColumn<Reservation, Integer> maximumColumn;
	@FXML
	private TableColumn<Reservation, String> nameColumn2;
	@FXML
	private TableColumn<Reservation, Float> present_auction_priceColumn;
	@FXML
	private TableColumn<Reservation, Float> recommended_retail_priceColumn;
	@FXML
	private TableColumn<Reservation, String> the_highest_bidderColumn;
	@FXML
	private Button BackButtonInBidding;
	@FXML
	private TextField UserNameTextField2;
	@FXML
	private TextField ProductNoTextField;
	@FXML
	private TextField HowMuchTextField;
	@FXML
	private Button BidButton;
	
	
//	ObservableList<Member> listM;
	
	
	
	public void initialize() {
		
		ObservableList<Reservation> listM2;
		noColumn2.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("no"));
		room_typeColumn2.setCellValueFactory(new PropertyValueFactory<Reservation, String>("room_type"));
		maximumColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("maximum"));
		nameColumn2.setCellValueFactory(new PropertyValueFactory<Reservation, String>("name"));
		present_auction_priceColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Float>("present_auction_price"));
		recommended_retail_priceColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Float>("recommended_retail_price"));
		the_highest_bidderColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("the_highest_bidder"));
		
		listM2 = MySQLConnect.getDataReservation();
		BiddingTableView.setItems(listM2);;
		
	}

	public void updateTable(String mem) {

		ObservableList<Reservation> listM2;
		noColumn2.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("no"));
		room_typeColumn2.setCellValueFactory(new PropertyValueFactory<Reservation, String>("room_type"));
		maximumColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("maximum"));
		nameColumn2.setCellValueFactory(new PropertyValueFactory<Reservation, String>("name"));
		present_auction_priceColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Float>("present_auction_price"));
		recommended_retail_priceColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Float>("recommended_retail_price"));
		the_highest_bidderColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("the_highest_bidder"));
		
		listM2 = MySQLConnect.getDataReservation();
		BiddingTableView.setItems(listM2);;
		
		ObservableList<Member> listM;
		noColumn.setCellValueFactory(new PropertyValueFactory<Member, Integer>("no"));
		room_typeColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("room_type"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
		my_bidding_priceColumn.setCellValueFactory(new PropertyValueFactory<Member, Float>("my_bidding_price"));
		winning_bidColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("winning_bid"));
		
		listM = MySQLConnect.getDataMember(mem);
		userTableView.setItems(listM);;
		
	}
	
	
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
			String mem = memberID.getText();
			
			if(rs.next()) {
				if(memberPW.getText().equals(rs.getString(1))){
					SelectAnchorPane.setVisible(true);
					LoginAnchorPane.setVisible(false);
					memberID.setText("");
					memberPW.setText("");
					
					ObservableList<Member> listM;
					noColumn.setCellValueFactory(new PropertyValueFactory<Member, Integer>("no"));
					room_typeColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("room_type"));
					nameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
					my_bidding_priceColumn.setCellValueFactory(new PropertyValueFactory<Member, Float>("my_bidding_price"));
					winning_bidColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("winning_bid"));
					
					listM = MySQLConnect.getDataMember(mem);
					userTableView.setItems(listM);;
					
					UserNameTextField.setText(mem);
					UserNameTextField2.setText(mem);
					
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
			UserNameTextField.setText("");
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
	
	public void UserOwnPageButton(ActionEvent event) {
		try
		{
			UserAnchorPane.setVisible(true);
			SelectAnchorPane.setVisible(false);
		} catch (Exception e) {
			System.out.println("UserOwnPageButton eroor : "+e.getMessage());
		}
	}
	
	public void BackButtonInUser(ActionEvent event) {
		try
		{
			SelectAnchorPane.setVisible(true);
			UserAnchorPane.setVisible(false);
		} catch (Exception e) {
			System.out.println("UserOwnPageButton eroor : "+e.getMessage());
		}
	}
	
	public void BiddingPageButton(ActionEvent event) {
		try
		{
			BiddingAnchorPane.setVisible(true);
			SelectAnchorPane.setVisible(false);
		} catch (Exception e) {
			System.out.println("BiddingPageButton eroor : "+e.getMessage());
		}
	}
	
	public void BackButtonInBidding(ActionEvent event) {
		try
		{
			SelectAnchorPane.setVisible(true);
			BiddingAnchorPane.setVisible(false);
		} catch (Exception e) {
			System.out.println("BackButtonInBidding eroor : "+e.getMessage());
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
	
	public void bidding() {

		try {
			String productNoStr = ProductNoTextField.getText();
			int productNo = Integer.parseInt(productNoStr);
			String howMuchStr = HowMuchTextField.getText();
			float howMuch = Float.parseFloat(howMuchStr);
			String user = UserNameTextField2.getText();
			
			StringBuilder sb = new StringBuilder();
			String SQLPresent = sb.append("select present_auction_price from reservation where no = ")
					.append(productNo)
					.toString();
			
			rs = st.executeQuery(SQLPresent);
			
			ProductNoTextField.setText("");
			HowMuchTextField.setText("");
			
			
			while(rs.next()) {
				
				if (howMuch > rs.getFloat(1)) {			
					
					if (howMuch < recommended(productNo)) {
					
						try
						{
							StringBuilder sb2 = new StringBuilder();
							String SQLBidding = sb2.append("UPDATE RESERVATION SET PRESENT_AUCTION_PRICE = ")
									.append(howMuch)
									.append("WHERE NO = ")
									.append(productNo)
									.toString();
							st.executeUpdate(SQLBidding);
							the_highest_bidder(productNo, user);
							record(productNo, howMuch, user, "");
							updateTable(user);
							AlertTextField.setText("Successfully bid");
							AlertAnchorPane.setVisible(true);
							break;
						}
						catch (Exception e) 
						{
							System.out.println("데이터베이스 연결오류(biddingMoney): " + e.getMessage());

						} 
						
					} else if (howMuch >= recommended(productNo)) {
						
						winningBid(productNo, howMuch, user);
						updateTable(user);
						AlertTextField.setText("You won the auction!");
						AlertAnchorPane.setVisible(true);
						break;
						
					}
					
				} else if(howMuch < rs.getFloat(1)) { 
					System.out.println("Your input number is smaller than present auction price.");
					AlertTextField.setText("Your input number is smaller than present auction price.");
					AlertAnchorPane.setVisible(true);
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









