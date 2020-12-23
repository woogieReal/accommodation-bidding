package application;

public class Member {

	int no;
	String room_type, name;
	float my_bidding_price;
	String winning_bid;
	
	public Member(int no, String room_type, String name, float my_bidding_price, String winning_bid) {
		super();
		this.no = no;
		this.room_type = room_type;
		this.name = name;
		this.my_bidding_price = my_bidding_price;
		this.winning_bid = winning_bid;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getMy_bidding_price() {
		return my_bidding_price;
	}
	public void setMy_bidding_price(float my_bidding_price) {
		this.my_bidding_price = my_bidding_price;
	}
	public String getWinning_bid() {
		return winning_bid;
	}
	public void setWinning_bid(String winning_bid) {
		this.winning_bid = winning_bid;
	}
	
	
	
}
