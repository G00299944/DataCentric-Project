package com.shops;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.mongodb.MongoWriteException;

@ManagedBean
@SessionScoped
public class HeadOfficeController {

	MongoDAO mdao;
	ArrayList<HeadOffice> headOffices;

	public HeadOfficeController() {
		super();
		try {
			mdao = new MongoDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<HeadOffice> getHeadOffices() {
		return headOffices;
	}

	public void loadHeadOffices() {
		try {
			headOffices = mdao.loadHeadOffices();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String addHeadOffice(HeadOffice headOffice) {
		try {
			mdao.addHeadOffice(headOffice);
			return "ManageHeadOfficesPage";
		} catch (MongoWriteException e) {
			FacesMessage message = new FacesMessage("Error: Store ID: " + headOffice.getId() + " already has location");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}

}
