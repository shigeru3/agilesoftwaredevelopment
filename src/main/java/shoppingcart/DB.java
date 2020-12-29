package shoppingcart;

import java.sql.*;
import java.util.LinkedList;

public class DB {
	private static Connection con;

	public static void init() throws Exception {
		Class.forName("org.h2.Driver");
		con = DriverManager.getConnection("jdbc:h2:mem:sfdcjdbc");
		createTable();
	}

	private static void createTable() throws SQLException {
		PreparedStatement s = con.prepareStatement(
				"CREATE TABLE products (sku VARCHAR, name VARCHAR , price INT);"
		);
		executeStatement(s);
		s = con.prepareStatement(
				"CREATE TABLE items (orderid INT, quantity INT, sku VARCHAR );"
		);
		executeStatement(s);
		s = con.prepareStatement(
				"CREATE TABLE orders (orderid INT, cusid VARCHAR );"
		);
		executeStatement(s);
	}

	public static void clear() {

	}

	public static void close() throws Exception {
		con.close();
	}

	private static void executeStatement(PreparedStatement s) throws SQLException {
		s.executeUpdate();
		s.close();
	}

	public static void store(ProductData pd) throws Exception {
		PreparedStatement s = buildInsertionStatement(pd);
		executeStatement(s);
	}

	private static PreparedStatement buildInsertionStatement(ProductData pd) throws SQLException {
		PreparedStatement s = con.prepareStatement(
				"INSERT INTO products VALUES (?, ?, ?);"
		);
		s.setString(1, pd.sku);
		s.setString(2, pd.name);
		s.setInt(3, pd.price);
		return s;
	}

	public static ProductData getProductData(String sku) throws Exception {
		PreparedStatement s = buildProductQueryStatement(sku);
		ResultSet rs = executeQueryStatement(s);
		if (rs.getRow() == 0) return null;
		ProductData pd = extractProductDataFromResultSet(rs);
		rs.close();
		s.close();
		return pd;
	}

	private static ProductData extractProductDataFromResultSet(ResultSet rs) throws SQLException {
		ProductData pd = new ProductData();
		pd.sku = rs.getString(1);
		pd.name = rs.getString(2);
		pd.price = rs.getInt(3);
		return pd;
	}

	private static ResultSet executeQueryStatement(PreparedStatement s) throws SQLException {
		ResultSet rs = s.executeQuery();
		rs.next();
		return rs;
	}

	private static PreparedStatement buildProductQueryStatement(String sku) throws Exception {
		PreparedStatement s = con.prepareStatement(
				"SELECT * FROM products WHERE sku = ?;"
		);
		s.setString(1, sku);
		return s;
	}

	public static void store(ItemData id) throws Exception {
		PreparedStatement s = buildItemInsersionStatement(id);
		executeStatement(s);
	}

	private static PreparedStatement buildItemInsersionStatement(ItemData id) throws SQLException {
		PreparedStatement s = con.prepareStatement("INSERT INTO items(orderid, quantity, sku) VALUES (?, ?, ?)");
		s.setInt(1, id.orderId);
		s.setInt(2, id.qty);
		s.setString(3, id.sku);
		return s;
	}

	public static ItemData[] getItemsForOrder(int orderId) throws Exception {
		PreparedStatement s = buildItemsForOrderQueryStatement(orderId);
		ResultSet rs = s.executeQuery();
		ItemData[] id = extractItemDataFromResultSet(rs);
		rs.close();
		s.close();
		return id;
	}

	private static ItemData[] extractItemDataFromResultSet(ResultSet rs) throws SQLException {
		LinkedList<ItemData> l = new LinkedList<>();
		for (int row = 0; rs.next(); row++) {
			ItemData id = new ItemData();
			id.orderId = rs.getInt("orderid");
			id.qty = rs.getInt("quantity");
			id.sku = rs.getString("sku");
			l.add(id);
		}
		return (ItemData[]) l.toArray(new ItemData[l.size()]);
	}

	private static PreparedStatement buildItemsForOrderQueryStatement(int orderId) throws SQLException {
		PreparedStatement s = con.prepareStatement("SELECT * FROM items WHERE orderid = ?");
		s.setInt(1, orderId);
		return s;
	}

	public static OrderData newOrder(String customerId) throws Exception {
		int newMaxOrderId = getMaxOrderId() + 1;
		PreparedStatement s = con.prepareStatement("INSERT INTO orders(orderid, cusid) VALUES (?, ?);");
		s.setInt(1, newMaxOrderId);
		s.setString(2, customerId);
		executeStatement(s);
		return new OrderData(newMaxOrderId, customerId);
	}

	private static int getMaxOrderId() throws SQLException {
		Statement qs = con.createStatement();
		ResultSet rs = qs.executeQuery("SELECT MAX(orderid) FROM orders");
		rs.next();
		int maxOrderId = rs.getInt(1);
		rs.close();
		return maxOrderId;
	}

	public static OrderData getOrderData(int orderId) throws SQLException {
		PreparedStatement s = con.prepareStatement("SELECT cusid FROM orders WHERE orderid = ?;");
		s.setInt(1, orderId);
		ResultSet rs = s.executeQuery();
		OrderData od = null;
		if (rs.next()) {
			od = new OrderData(orderId, rs.getString("cusid"));
		}
		rs.close();
		s.close();
		return od;
	}
}
