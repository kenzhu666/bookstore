package bean;

import java.sql.Timestamp;
import java.util.Date;

import dao.ReviewDAO;
import util.StarUtil;

public class Review {
	private int id;
	private String bid;
	private int uid;
	private Timestamp day;
	private int rate;
	private String description;

	public Review() {

	}

	public Review(int id, String bid, int uid, Timestamp day, int rate, String description) {
		super();
		this.id = id;
		this.bid = bid;
		this.uid = uid;
		this.day = day;
		this.rate = rate;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Timestamp getDay() {
		return day;
	}

	public void setDay(Timestamp day) {
		this.day = day;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDate() {
		return new Date(day.getTime());
	}
	
	public void setDate(Date date) {
		day = new Timestamp(date.getTime());
	}
	
	public String getRateStar() {
		return StarUtil.getRateStar(rate);
	}
	
	public String getRateStar(String bid) throws Exception{
		double rate = Double.parseDouble(ReviewDAO.getAvgRate(bid));
		return StarUtil.getRateStar(rate);
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", bid=" + bid + ", uid=" + uid + ", day=" + day + ", rate=" + rate
				+ ", description=" + description + "]";
	}

	
}
