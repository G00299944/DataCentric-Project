package com.shops;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Store {
	
	private int id;
	private String name;
	private Date founded;

	
	public Date getFounded() {
		return founded;
	}
	public void setFounded(Date date) {
		this.founded = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
