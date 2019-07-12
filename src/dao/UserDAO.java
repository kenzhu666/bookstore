package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;

public class UserDAO extends DAO {
	public static User getUserByID(int uid) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			String sql = "SELECT * FROM USER WHERE UID=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setInt(1, uid);
			r = p.executeQuery();
			User user = null;
			if (r.next()) {
				String username = r.getString("username");
				String password = r.getString("password");
				String fname = r.getString("fname");
				String lname = r.getString("lname");
				int address = r.getInt("address");
				String phone = r.getString("phone");
				String type = r.getString("type");
				user = new User(uid, username, password, fname, lname, address, phone, type);
			} else {
				throw new Exception("Could not find user with id: " + uid);
			}
			return user;
		} finally {
			close(r, p, con);
		}

	}

	public static User getUserByUsername(String username) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			String sql = "SELECT * FROM USER WHERE username=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, username);
			r = p.executeQuery();
			User user = null;
			if (r.next()) {
				int uid = r.getInt("UID");
				String password = r.getString("password");
				String fname = r.getString("fname");
				String lname = r.getString("lname");
				int address = r.getInt("address");
				String phone = r.getString("phone");
				String type = r.getString("type");
				user = new User(uid, username, password, fname, lname, address, phone, type);
			} else {
				throw new Exception("Could not find user with username: " + username);
			}
			return user;
		} finally {
			close(r, p, con);
		}
	}

	public static boolean validate(String username, String password) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			String sql = "SELECT username, password from USER WHERE username=? AND password=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, username);
			p.setString(2, password);
			r = p.executeQuery();
			return r.next();
		} finally {
			close(r, p, con);
		}
	}

	public static boolean usernameAvailable(String username) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			String sql = "SELECT username FROM USER WHERE username=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, username);
			r = p.executeQuery();
			return !r.next();
		} finally {
			close(p, con);
		}
	}

	public static void addUser(String username, String password, String fname, String lname, int addr, String phone,
			String type) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		try {
			String sql = "INSERT INTO USER (username, password, fname, lname, address, phone, TYPE) VALUES (?,?,?,?,?,?,?)";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, username);
			p.setString(2, password);
			p.setString(3, fname);
			p.setString(4, lname);
			p.setInt(5, addr);
			p.setString(6, phone);
			p.setString(7, type);
			p.executeUpdate();
		} finally {
			close(p, con);
		}

	}

	public static boolean isPartner(int id) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		try {
			String sql = "SELECT TYPE FROM USER WHERE UID=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				String type = r.getString(1);
				return type.equals("PARTNER");
			}
		} catch (Exception e) {
			return false;
		} finally {
			close(p, con);
		}
		return false;
	}
	
	public static boolean isAdmin(int id) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		try {
			String sql = "SELECT TYPE FROM USER WHERE UID=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				String type = r.getString(1);
				return type.equals("ADMIN");
			}
		} catch (Exception e) {
			return false;
		} finally {
			close(p, con);
		}
		return false;
	}
}
