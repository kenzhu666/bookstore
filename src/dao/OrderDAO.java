package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import bean.POItem;
import rest.Address;
import rest.Item;
import rest.ListWrapper;
import rest.Order;

public class OrderDAO extends DAO {
	public static int addPO(int uid, String shipName, String shipTel, int shipAddress, String billName, String billTel,
			int billAddress) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			String sql = "INSERT INTO PO (UID, status, shipName, shipTel, shipAddress, billName, billTel, billAddress) VALUES (?,?,?,?,?,?,?,?)";
			con = getConnection();
			p = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			p.setInt(1, uid);
			p.setString(2, "ORDERED");
			p.setString(3, shipName);
			p.setString(4, shipTel);
			p.setInt(5, shipAddress);
			p.setString(6, billName);
			p.setString(7, billTel);
			p.setInt(8, billAddress);
			p.executeUpdate();
			r = p.getGeneratedKeys();
			if (r.next()) {
				return r.getInt(1);
			} else {
				throw new Exception("Cannot add this order into database!");
			}
		} finally {
			close(r, p, con);
		}
	}

	public static void addPOItem(POItem po) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		try {
			String sql = "INSERT INTO POItem VALUES (?,?,?)";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setInt(1, po.getId());
			p.setString(2, po.getBid());
			p.setInt(3, po.getQuantity());
			p.executeUpdate();
		} finally {
			close(p, con);
		}
	}

	public static void changeStatus(int id, String status) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		try {
			String sql = "UPDATE PO SET status=? WHERE id=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setString(1, status);
			p.setInt(2, id);
			p.executeUpdate();
		} finally {
			close(p, con);
		}
	}

	public static List<POItem> getItemsInOrder(int id) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			List<POItem> list = new ArrayList<>();
			String sql = "SELECT * FROM POItem WHERE id=?";
			con = getConnection();
			p = con.prepareStatement(sql);
			p.setInt(1, id);
			r = p.executeQuery();
			while (r.next()) {
				String bid = r.getString(2);
				int quantity = r.getInt(3);
				POItem poItem = new POItem(id, bid, quantity);
				list.add(poItem);
			}
			return list;
		} finally {
			close(r, p, con);
		}
	}

	public static ListWrapper getAllOrder() throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			String sql = "SELECT PO.id, date, status, Book.bid, title, price, quantity, billName, billTel, "
					+ "bill.street AS billStreet, bill.city AS billCity, bill.province AS billProvince, "
					+ "bill.zip AS billZip, shipName, shipTel, ship.street AS shipStreet, ship.city AS shipCity, "
					+ "ship.province AS shipProvince, ship.zip AS shipZip "
					+ "FROM PO JOIN POItem ON PO.id = POItem.id " + "JOIN USER ON PO.UID = USER.UID "
					+ "JOIN Book ON POItem.bid = Book.bid " + "JOIN Address AS bill ON PO.billAddress = bill.id "
					+ "JOIN Address AS ship ON PO.shipAddress = ship.id" + " ORDER BY PO.id";
			con = getConnection();
			p = con.prepareStatement(sql);
			r = p.executeQuery();
			ListWrapper lw = new ListWrapper();
			while (r.next()) {
				int id = r.getInt("id");
				if (!lw.has(id)) {
					String status = r.getString("status");
					Date orderDate = new Date(r.getTimestamp("date").getTime());
					Address bill = new Address(r.getString("billName"), r.getString("billTel"),
							r.getString("billStreet"), r.getString("billCity"), r.getString("billProvince"),
							r.getString("billZip"));
					Address ship = new Address(r.getString("shipName"), r.getString("shipTel"),
							r.getString("shipStreet"), r.getString("shipCity"), r.getString("shipProvince"),
							r.getString("shipZip"));
					lw.add(new Order(id, orderDate, status, bill, ship));
				}

				Item item = new Item(r.getString("bid"), r.getString("title"), r.getInt("quantity"), r.getInt("price"));
				lw.get(id).add(item);
			}
			return lw;
		} finally {
			close(r, p, con);
		}
	}

	public static Map<String, List<POItem>> getMonthReport() throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			Map<String, List<POItem>> report = new TreeMap<>();
			String sql = "SELECT YEAR(date) as year, MONTH(date) as month, Book.bid, title, SUM(quantity) as quantity "
					+ "FROM PO JOIN POItem ON PO.id = POItem.id JOIN Book ON POItem.bid = Book.bid "
					+ "WHERE status = 'PROCESSED' "
					+ "GROUP BY bid, MONTH(date), YEAR(date) ORDER BY month ASC, quantity DESC";
			con = getConnection();
			p = con.prepareStatement(sql);
			r = p.executeQuery();
			while (r.next()) {
				int year = r.getInt("year");
				int month = r.getInt("month");
				String bid = r.getString("bid");
				String title = r.getString("title");
				int quantity = r.getInt("quantity");
				String date = String.format("%4d-%02d", year, month);
				if (!report.containsKey(date)) {
					report.put(date, new ArrayList<>());
				}
				POItem poItem = new POItem(bid, title, quantity);
				report.get(date).add(poItem);
			}
			return report;
		} finally {
			close(r, p, con);
		}
	}

	public static List<POItem> getTopSeller() throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet r = null;
		try {
			String sql = "SELECT Book.bid, title, SUM(quantity) AS quantity FROM PO JOIN POItem ON PO.id = POItem.id"
					+ " JOIN Book ON POItem.bid = Book.bid WHERE status = 'PROCESSED' GROUP BY Book.bid ORDER BY quantity DESC";
			con = getConnection();
			p = con.prepareStatement(sql);
			r = p.executeQuery();
			List<POItem> topItems = new ArrayList<>();
			while (r.next()) {
				POItem poItem = new POItem(r.getString("bid"), r.getString("title"), r.getInt("quantity"));
				topItems.add(poItem);
			}
			return topItems;
		} finally {
			close(r, p, con);
		}
	}
}
