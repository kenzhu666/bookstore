package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.Review;

public class ReviewDAO extends DAO {
	public static String getAvgRate(String bid) throws Exception {
		List<Review> reviews = getReviews(bid);
		double avg = 0;
		for (Review review : reviews) {
			avg += review.getRate();
		}
		return reviews.isEmpty() ? "0.0" : String.format("%.1f", avg / reviews.size());
	}

	public static List<Review> getReviews(String bid) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			List<Review> reviews = new ArrayList<>();
			String sql = "SELECT * FROM Review WHERE BID=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, bid);
			r = p.executeQuery();
			while (r.next()) {
				int id = r.getInt("id");
				int uid = r.getInt("uid");
				Timestamp day = r.getTimestamp("day");
				int rate = r.getInt("rate");
				String description = r.getString("description");
				Review review = new Review(id, bid, uid, day, rate, description);
				reviews.add(review);
			}
			return reviews;
		} finally {
			close(r, p, con);
		}

	}

	public static void addReview(Review review) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		try {
			String sql = "INSERT INTO Review (bid, uid, rate, description) VALUES (?,?,?,?)";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, review.getBid());
			p.setInt(2, review.getUid());
			p.setInt(3, review.getRate());
			p.setString(4, review.getDescription());
			p.executeUpdate();
		} finally {
			close(p, con);
		}
	}
}
