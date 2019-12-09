package com.shops;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;


public class DAO {

	private DataSource mysqlDS;

	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
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

		// process result set
		while (myRs.next()) {
			Store store = new Store();
			store.setId(myRs.getInt("id"));
			store.setName(myRs.getString("name"));
			store.setFounded(myRs.getString("founded"));
			
			stores.add(store);
		}
		
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

		// process result set
		while (myRs.next()) {
			Product product = new Product();
			product.setId(myRs.getInt(1));
			product.setSid(myRs.getInt(2));
			product.setName(myRs.getString(3));
			product.setPrice(myRs.getDouble(4));
			
			products.add(product);
		}
		
		return products;
	}
	
	public ArrayList<StoreProducts> loadStoreProducts() throws Exception {
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from product";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);
		
		ArrayList<StoreProducts> storeProducts = new ArrayList<StoreProducts>();

		// process result set
		while (myRs.next()) {
			StoreProducts sp = new StoreProducts();
			sp.setPid(myRs.getInt(1));
			sp.setSid(myRs.getInt(2));
			sp.setProdName(myRs.getString(3));
			sp.setPrice(myRs.getDouble(4));
			sp.setStoreName(myRs.getString(6));
			
			storeProducts.add(sp);
		}
		
		return storeProducts;
	}
	
//	public ArrayList<StoreProduct> loadStoreProducts(int sid) throws Exception {
//		
//		Connection myConn = null;
//		Statement myStmt = null;
//		ResultSet myRs = null;
//		
//		myConn = mysqlDS.getConnection();
//
//		String sql = "select p.pid, p.prodName, p.price, s.id, s.name, s.founded "
//				+ "from product p "
//				+ "inner join store s on p.sid = s.id "
//				+ "where p.sid = " + sid; 
//
//		myStmt = myConn.createStatement();
//
//		myRs = myStmt.executeQuery(sql);
//		
//		ArrayList<StoreProduct> storeProducts = new ArrayList<StoreProduct>();
//
//		// process result set
//		while (myRs.next()) {
//			StoreProduct sp = new StoreProduct();
//			
//			sp.setProductId(myRs.getInt(1));
//			sp.setProductName(myRs.getString(2));
//			sp.setPrice(myRs.getDouble(3));
//			sp.setSid(myRs.getInt(4));
//			sp.setStoreName(myRs.getString(5));
//			sp.setProductName(myRs.getString(6));
//			
//			storeProducts.add(sp);
//		}
//		
//		return storeProducts;
//	}

	
	public void addStore(Store store) throws Exception { 
		Connection myConn = null;
		java.sql.PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into store values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, store.getId());
		myStmt.setString(2, store.getName());
		myStmt.setString(3, store.getFounded());
		myStmt.execute();	
		
	}

}
