package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.*;

public class AddressDAO extends DAO {
	// get the given address id from database
	// if not exists, add it into database and return id
	private static List<Province> provinces;

	public static int getAddressID(String street, String city, String province, String zip) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		PreparedStatement p2 = null;
		ResultSet r2 = null;
		try {
			String sql = "SELECT id FROM Address WHERE street=? AND city=? AND province=? AND zip=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, street);
			p.setString(2, city);
			p.setString(3, province);
			p.setString(4, zip);
			r = p.executeQuery();
			if (r.next()) {
				return r.getInt(1);
			} else {
				String insertSQL = "INSERT INTO Address (street, city, province, zip) VALUES (?,?,?,?)";
				p2 = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				p2.setString(1, street);
				p2.setString(2, city);
				p2.setString(3, province);
				p2.setString(4, zip);
				p2.executeUpdate();
				r2 = p2.getGeneratedKeys();
				if (r2.next()) {
					return r2.getInt(1);
				} else {
					throw new Exception("This address cannot be added into database!");
				}
			}
		} finally {
			r.close();
			p.close();
			close(r2, p2, con);
		}

	}

	public static int getAddressID(Address address) throws Exception {
		return getAddressID(address.getStreet(), address.getCity(), address.getProvince(), address.getZip());
	}

	public static Address getAddress(int id) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			String sql = "SELECT * FROM Address WHERE id=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setInt(1, id);
			r = p.executeQuery();
			if (r.next()) {
				String street = r.getString("street");
				String city = r.getString("city");
				String province = r.getString("province");
				String zip = r.getString("zip");
				return new Address(id, street, city, province, zip);
			} else {
				throw new Exception("This address id is not in the database!");
			}
		} finally {
			close(r, p, con);
		}
	}

	public static List<Province> getAllProvince() throws Exception {
		if (provinces == null || provinces.isEmpty()) {
			provinces = new ArrayList<>();
			Connection con = null;
			PreparedStatement p = null;
			ResultSet r = null;
			try {
				String sql = "SELECT * FROM Province";
				con = getConnection();
				p = con.prepareStatement(sql);
				r = p.executeQuery();
				while (r.next()) {
					String province = r.getString("id");
					String fullName = r.getString("name");
					provinces.add(new Province(province, fullName));
				}
			} finally {
				close(r, p, con);
			}
		}
		return provinces;
	}
}
