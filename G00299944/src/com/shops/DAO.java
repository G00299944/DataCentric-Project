package com.shops;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	private DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

	public ArrayList<Store> loadStores() throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from store";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Store> stores = new ArrayList<Store>();

		while (myRs.next()) {
			Store store = new Store();
			store.setId(myRs.getInt("id"));
			store.setName(myRs.getString("name"));
			store.setFounded(myRs.getString("founded"));
			stores.add(store);
		}

		myConn.close();
		myStmt.close();

		return stores;
	}

	public ArrayList<Product> loadProducts() throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from product";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Product> products = new ArrayList<Product>();

		while (myRs.next()) {
			Product product = new Product();
			product.setId(myRs.getInt(1));
			product.setSid(myRs.getInt(2));
			product.setName(myRs.getString(3));
			product.setPrice(myRs.getDouble(4));
			products.add(product);
		}

		myConn.close();
		myStmt.close();

		return products;
	}

	public ArrayList<StoreProducts> loadStoreProducts(int id) throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select p.pid, p.prodName, p.price, s.name, s.founded, s.id from product p inner join store s on p.sid = s.id where p.sid like "
				+ id;

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<StoreProducts> storeProducts = new ArrayList<StoreProducts>();

		while (myRs.next()) {
			StoreProducts sp = new StoreProducts();
			sp.setPid(myRs.getInt("pid"));
			sp.setSid(myRs.getInt("id"));
			sp.setProdName(myRs.getString("prodName"));
			sp.setPrice(myRs.getDouble("price"));
			sp.setStoreName(myRs.getString("name"));
			sp.setFounded(myRs.getString("founded"));
			storeProducts.add(sp);
		}

		myConn.close();
		myStmt.close();

		return storeProducts;
	}

	public void addStore(Store store) throws Exception {
		Connection myConn = null;
		java.sql.PreparedStatement myStmt = null;

		myConn = mysqlDS.getConnection();
		String sql = "insert into store values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, store.getId());
		myStmt.setString(2, store.getName());
		myStmt.setString(3, store.getFounded());
		myStmt.execute();
	}

	public void deleteStore(int id) throws Exception {

		Connection myConn = null;
		java.sql.PreparedStatement myStmt = null;

		myConn = mysqlDS.getConnection();
		String sql = "delete from store where id like " + id;
		myStmt = myConn.prepareStatement(sql);
		myStmt.execute();
	}

	public void deleteProduct(int id) throws Exception {

		Connection myConn = null;
		java.sql.PreparedStatement myStmt = null;

		myConn = mysqlDS.getConnection();
		String sql = "delete from product where pid like " + id;
		myStmt = myConn.prepareStatement(sql);
		myStmt.execute();
	}

}
