package com.example.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Product implements Serializable {
	private static final long serialVersionUID = 3316076651716569539L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull(message = "請輸入名稱")
	@Size(min = 1)
	@Column(name = "name")
	private String name;
	// JoinColumn refers to the column name in this table (book)
	@ManyToOne
	@JoinColumn(name = "type")
	public ProductCategory productCategory;
	private int inventory = 0;
	@NotNull(message = "請輸入產品品牌")
	@Size(min = 1)
	private String brand;
	@Min(value = 1, message = "必須大於0")
	private int price;
	@Min(value = 1, message = "必須大於0")
	private int sale;
	@NotNull(message = "請輸入優惠日期")
	@Column(name = "saledate")
	private Date saledate;

	@NotNull(message = "請輸入產地")
	@Size(min = 1)
	private String location;

	@NotNull(message = "請輸入製造商")
	@Size(min = 1)
	private String firm;

	@OneToMany(mappedBy = "product")
	private List<Productdetail> productdetails;

	// @Transient
	private transient MultipartFile photoFile;

	private String photo;

	@Column(name = "undersaledate")
	private String underdate;

	private int count = 0;
	private int now = 0;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public Date getSaledate() {
		return saledate;
	}

	public void setSaledate(Date saledate) {
		this.saledate = saledate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public int getInventory() {
		this.setInventory();
		return inventory;
	}

	public void setInventory() {
		try {
			this.getProductdetails();
			this.productdetails.toArray();
			int n = productdetails.size();
			int temp = 0;
			for (int i = 0; i < n; i++) {
				if (productdetails.get(i).getIsreach() == 1) {
					temp += productdetails.get(i).getInventory();
				}
			}
			inventory = temp;
		} catch (Exception ex) {
			this.inventory = 0;
		}
	}

	public void setProductdetails(List<Productdetail> productdetails) {
		this.productdetails = productdetails;
	}

	public Iterable<Productdetail> getProductdetails() {
		return productdetails;
	}

	public MultipartFile getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(MultipartFile photoFile) {
		this.photoFile = photoFile;
	}

	public void setPhoto() {
		this.photo = photoFile.getOriginalFilename();
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setUnderdate() {
		java.util.Date date = new java.util.Date();
		java.sql.Date sd = new java.sql.Date(date.getTime());

		if (sd.before(saledate)) {
			this.underdate = "是";
		} else {
			this.underdate = "否";
		}
	}

	public String getUnderdate() {
		return underdate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getNow() {
		return now;
	}

	public void setNow() {
		if (this.getUnderdate().equals("是")) {
			this.now = this.sale;
		} else {
			this.now = this.price;
		}
	}
}
