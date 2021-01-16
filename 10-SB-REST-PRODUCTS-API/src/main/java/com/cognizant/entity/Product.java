package com.cognizant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
@Data
@Entity
@Table(name="PRODUCT")
@XmlRootElement
public class Product {
	@Id
	@GeneratedValue
	@Column(name="PRODUCT_ID")
	private Integer productId;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="PRODUCT_PRICE")
	private String productPrice;
	public Product() {
		
	}
	
	public Product(Integer productId, String productName, String productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
}
