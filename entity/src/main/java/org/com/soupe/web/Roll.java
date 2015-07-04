package org.com.soupe.web;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "M_ROLL")
public class Roll implements Serializable {

	private static final long serialVersionUID = -7268159051330064687L;

	@Id
	@Column(name = "ID",nullable=false)
	private Long id;

	/**
	 * 期号
	 */
	@Column(name = "NUMBERFIELD",nullable=false)
	private String numberFiel;
	
	@Column(name="RED",nullable=false)
	private String red ;
	
	@Column(name="BLUE",nullable=false)
	private String blue ;
	
	@Column(name="RED_SUM")
	private  int redSum ;
	
	@Column(name="data_source_type")
	private String dataSourceType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumberFiel() {
		return numberFiel;
	}

	public void setNumberFiel(String numberFiel) {
		this.numberFiel = numberFiel;
	}

	public String getRed() {
		return red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getBlue() {
		return blue;
	}

	public void setBlue(String blue) {
		this.blue = blue;
	}

	public int getRedSum() {
		return redSum;
	}

	public void setRedSum(int redSum) {
		this.redSum = redSum;
	}

	public String getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	
}
