package com.shops;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
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
	
	public String loadStoreProducts(int id) {
		try {
			System.out.println("StoreProductsController.loadStoreProducts()");
			storeProducts = dao.loadStoreProducts(id);
			return "StoreProductsPage";
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("testtest");
		return null;
	}
	
	public ArrayList<StoreProducts> getStoreProducts() {
		return storeProducts;
	}
//
//	public void setStoreProducts(ArrayList<StoreProducts> storeProducts) {
//		this.storeProducts = storeProducts;
//	}
}
