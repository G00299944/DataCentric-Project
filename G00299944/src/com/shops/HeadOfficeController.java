package com.shops;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	
//	public void loadHeadOffices() {
//		System.out.println(this.getClass().getName() + ".loadHeadOffices()");
//		try {
//			
//		}
//	}
}
