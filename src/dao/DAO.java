package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public abstract class DAO {
	
	public static Connection getConnection() throws Exception {
		return DBUtil.getInstance().getConnection();
	}

	public static void close(ResultSet r, PreparedStatement p, Connection con) throws SQLException {
		if (r != null) {
			r.close();
		}
		if (p != null) {
			p.close();
		}
		if (con != null) {
			con.close();
		}
	}
	
	public static void close(PreparedStatement p, Connection con) throws SQLException {
		close(null, p, con);
	}
}
