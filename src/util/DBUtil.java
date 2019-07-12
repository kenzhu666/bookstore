package util;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtil {
	private static final String JNDI = "java:comp/env/jdbc/EECS";
	private static DBUtil instance;
	private DataSource ds;

	public static DBUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new DBUtil();
		}
		return instance;
	}

	private DBUtil() throws Exception {
		ds = (DataSource) (new InitialContext()).lookup(JNDI);
	}

	public Connection getConnection() throws Exception {
		return ds.getConnection();
	}


//	public static Connection getConnection() throws Exception {
//		Properties p = new Properties();
//
//		p.load(DBUtil.class.getResourceAsStream("/resources/connection.properties"));
//		String driver = p.getProperty("driver");
//		Class.forName(driver);
//
//		String user = p.getProperty("user");
//		String password = p.getProperty("password");
//		String url = p.getProperty("url");
//		
//		return DriverManager.getConnection(url, user, password);
//	}
}
