package com.shops;

import java.util.ArrayList;

public class StoreProductsController {

	DAO dao;
	ArrayList<StoreProducts> storeProducts;
	
	public StoreProductsController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadStoreProducts() {
		try {
			storeProducts = dao.loadStoreProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<StoreProducts> getStoreProducts() {
		return storeProducts;
	}
}
