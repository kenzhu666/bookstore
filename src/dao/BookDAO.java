package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Book;

public class BookDAO extends DAO {

	public static List<String> getCategories() throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			List<String> categories = new ArrayList<>();
			String sql = "SELECT DISTINCT category FROM Book";
			con = getConnection();
			p = con.prepareStatement(sql);
			r = p.executeQuery();
			while (r.next()) {
				String category = r.getString("category");
				categories.add(category);
			}
			return categories;
		} finally {
			close(r, p, con);
		}
	}

	public static List<Book> getAllBooks() throws Exception {
		return getBookByTitle("");
	}

	public static List<Book> getBooksByCategory(String category) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			List<Book> books = new ArrayList<>();
			String sql = "SELECT * FROM Book WHERE category=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, category);
			r = p.executeQuery();
			while (r.next()) {
				String bid = r.getString("bid");
				String title = r.getString("title");
				int price = r.getInt("price");
				String author = r.getString("author");
				String description = r.getString("description");
				String img = r.getString("img");
				Date release = new Date(r.getTimestamp("release").getTime());
				Book book = new Book(bid, title, price, category, author, release, description, img);
				books.add(book);
			}
			return books;
		} finally {
			close(r, p, con);
		}

	}

	public static Book getBookByID(String bid) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			String sql = "SELECT * FROM Book WHERE bid=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, bid);
			r = p.executeQuery();
			Book book = null;
			if (r.next()) {
				String title = r.getString("title");
				int price = r.getInt("price");
				String category = r.getString("category");
				String author = r.getString("author");
				String description = r.getString("description");
				String img = r.getString("img");
				Date release = new Date(r.getTimestamp("release").getTime());
				book = new Book(bid, title, price, category, author, release, description, img);
			} else {
				throw new Exception("Could not find book id: " + bid);
			}
			return book;
		} finally {
			close(r, p, con);
		}

	}

	public static String getBookTitle(String bid) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			String sql = "SELECT title FROM Book WHERE bid=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, bid);
			r = p.executeQuery();
			if (r.next()) {
				return r.getString("title");
			}
			return null;
		} finally {
			close(r, p, con);
		}
	}

	public static List<Book> getBookByTitle(String title) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			List<Book> books = new ArrayList<>();
			String sql = "SELECT * FROM Book WHERE TITLE LIKE ?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, "%" + title + "%");
			r = p.executeQuery();
			while (r.next()) {
				String bid = r.getString("bid");
				String bookTitle = r.getString("title");
				int price = r.getInt("price");
				String category = r.getString("category");
				String author = r.getString("author");
				String description = r.getString("description");
				String img = r.getString("img");
				Date release = new Date(r.getTimestamp("release").getTime());
				Book book = new Book(bid, bookTitle, price, category, author, release, description, img);
				books.add(book);
			}
			return books;
		} finally {
			close(r, p, con);
		}
	}
}
