package com.carlosasrc.jtscore.temafinal.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Sale {
	private static final String DATA_TYPE = "003";
	private int id;
	private String salesmanName;
	private Map<Item, Integer> itens;

	public Sale() {
		itens = new LinkedHashMap<Item, Integer>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public Map<Item, Integer> getItens() {
		return itens;
	}

	public void setItens(Map<Item, Integer> itens) {
		this.itens = itens;
	}

	public static String getDataType() {
		return DATA_TYPE;
	}

	public double getSaleValue() {
		int value = 0;
		for (Item item : itens.keySet()) {
			value += itens.get(item) * item.getPrice();
		}
		return value;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", salesmanName=" + salesmanName + ", itens=" + itens + "]";
	}
}
