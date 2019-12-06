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
public class StoreController {

	DAO dao;
	ArrayList<Store> stores;
	
	public StoreController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadStores() {
		System.out.println("test()");
		try {
			stores = dao.loadStores();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String addStore(Store s) {
		System.out.println(s.getId() + " " + s.getName());
		try {
			dao.addStore(s);
			return "index";
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = 
					new FacesMessage("Error: Store Name already exists");
					FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (CommunicationsException e) {
			FacesMessage message = 
					new FacesMessage("Error: Can't communicate with DB");
					FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			FacesMessage message = 
					new FacesMessage("Error: " + e.getMessage());
					FacesContext.getCurrentInstance().addMessage(null, message);

			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Store> getStores() {
		return stores;
	}
}
