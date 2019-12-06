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
			store.setFounded(myRs.getDate("founded"));
			
			stores.add(store);
		}
		
		return stores;
	}
	
	public void addStore(Store store) throws Exception { // https://stackoverflow.com/questions/20088808/data-truncation-incorrect-datetime-value
		Connection myConn = null;
		java.sql.PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into store values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, store.getId());
		myStmt.setString(2, store.getName());
		myStmt.setString(3, store.getFounded().toString());
		myStmt.execute();			
	}

}
