package application;

public class Reservation {

	int no;
	String room_type;
	int maximum;
	String name;
	float present_auction_price;
	float recommended_retail_price;
	String the_highest_bidder;
	
	public Reservation(int no, String room_type, int maximum, String name, float present_auction_price,
			float recommended_retail_price, String the_highest_bidder) {
		super();
		this.no = no;
		this.room_type = room_type;
		this.maximum = maximum;
		this.name = name;
		this.present_auction_price = present_auction_price;
		this.recommended_retail_price = recommended_retail_price;
		this.the_highest_bidder = the_highest_bidder;
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

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPresent_auction_price() {
		return present_auction_price;
	}

	public void setPresent_auction_price(float present_auction_price) {
		this.present_auction_price = present_auction_price;
	}

	public float getRecommended_retail_price() {
		return recommended_retail_price;
	}

	public void setRecommended_retail_price(float recommended_retail_price) {
		this.recommended_retail_price = recommended_retail_price;
	}

	public String getThe_highest_bidder() {
		return the_highest_bidder;
	}

	public void setThe_highest_bidder(String the_highest_bidder) {
		this.the_highest_bidder = the_highest_bidder;
	}
	
	
	
}
