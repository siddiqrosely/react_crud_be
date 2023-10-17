package net.crud_be.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "product_code")	
	private String productCode;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_category")
	private String productCategory;
	
	@Column(name = "product_brand")
	private String productBrand;
	
	
	@Column(name = "product_type")
	private String productType;
	
	@Column(name = "product_desc")
	private String productDesc;
		
	public Product() {		
	}
	
	public Product(String productCode, String productName, String productCategory, String productBrand,
			String productType, String productDesc) {		
		this.productCode = productCode;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productBrand = productBrand;
		this.productType = productType;
		this.productDesc = productDesc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	
	

}
