package com.carlosasrc.jtscore.temafinal.model;

public class Item {
	private int id;
	private double price;

	public Item(int id, double price) {
		this.id = id;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", price=" + price + "] quantity";
	}

}
