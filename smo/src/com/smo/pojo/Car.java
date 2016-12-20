package com.smo.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Car {
	private Integer id;
	private String name;
	
	//�ѱ����ַ���ת��Ϊ��������
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date saleDate;
	private Double price;
	public Car() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
