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
		//System.out.println(this.getClass().getName() + ".loadHeadOffices()");
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
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
