package com.shops;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
			storeProducts = dao.loadStoreProducts(id);
			return "StoreProductsPage";
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Can't communicate with DB");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<StoreProducts> getStoreProducts() {
		return storeProducts;
	}

}
