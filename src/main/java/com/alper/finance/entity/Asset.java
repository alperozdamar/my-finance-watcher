package com.alper.finance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="asset")
public class Asset {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="boa")
	private int boa;
	
	@Column(name="chase")
	private int chase;
	
	@Column(name="hsbc_tr")
	private int hsbcTr;
	
		
	// define constructors
	
	public Asset() {
		
	}
		

	public Asset(int id, int boa, int chase, int hsbcTr) {
		super();
		this.id = id;
		this.boa = boa;
		this.chase = chase;
		this.hsbcTr = hsbcTr;
	}


	// define getter/setter	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getBoa() {
		return boa;
	}


	public void setBoa(int boa) {
		this.boa = boa;
	}


	public int getChase() {
		return chase;
	}


	public void setChase(int chase) {
		this.chase = chase;
	}


	public int getHsbcTr() {
		return hsbcTr;
	}


	public void setHsbcTr(int hsbcTr) {
		this.hsbcTr = hsbcTr;
	}


	@Override
	public String toString() {
		return "Asset [id=" + id + ", boa=" + boa + ", chase=" + chase + ", hsbcTr=" + hsbcTr + "]";
	}

		
}











