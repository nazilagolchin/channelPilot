package com.channelpilot.productconnect.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PRODUCT")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "X", nullable = false)
	private String x;
	
	@Column(name = "y", nullable = false)
	private String y;
	
	@Column(name = "z", nullable = false)
	private String z;
	
	@Column(name = "a")
	private String a;
	@Column(name = "b")
	private String b;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getx() {
		return x;
	}

	public void setx(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	};

	@Override
	public String toString() {
		return "Product {id=" + id + ", x=" + x + ", y=" + y + ", z=" + z
				+ ((a == null || a.equals("")) ? "" : ",a=" + a) + ((b == null || b.equals("")) ? "" : ",b=" + b) + "}";
	}

}
