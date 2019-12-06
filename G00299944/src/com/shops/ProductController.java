package com.shops;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ProductController {
	
	DAO dao;
	ArrayList<Product> products;
	
	public ProductController() {
		super();
		try {
			dao = new DAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadProducts() {
		System.out.println("DEBUG - loadProducts()");	
		try {
			products = dao.loadProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}

}
