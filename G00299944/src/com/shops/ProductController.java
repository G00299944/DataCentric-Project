package com.shops;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ProductController {

	DAO dao;
	ArrayList<Product> products;

	public ProductController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadProducts() {
		try {
			products = dao.loadProducts();
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Can't communicate with DB");
			FacesContext.getCurrentInstance().addMessage(null, message);
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
}
