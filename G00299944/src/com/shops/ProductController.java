package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;

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
	
	public String deleteProduct(int id) {
		try {
			dao.deleteProduct(id);
			return "ManageProductsPage";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
//	public String deleteStore(int id) {		
//		try {
//			dao.deleteStore(id);
//			return "index";
//		} catch (SQLIntegrityConstraintViolationException e) {
//			FacesMessage message = 
//					new FacesMessage("Error: Store Name already exists"); // TODO FIX
//					FacesContext.getCurrentInstance().addMessage(null, message);
//		} catch (CommunicationsException e) {
//			FacesMessage message = 
//					new FacesMessage("Error: Can't communicate with DB");
//					FacesContext.getCurrentInstance().addMessage(null, message);
//		}catch (Exception e) {
//			FacesMessage message = 
//					new FacesMessage("Error: " + e.getMessage());
//					FacesContext.getCurrentInstance().addMessage(null, message);
//
//			e.printStackTrace();
//		}
//		return null;
//	}

}
