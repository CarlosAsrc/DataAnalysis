package com.carlosasrc.jtscore.temafinal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carlosasrc.jtscore.temafinal.model.Item;
import com.carlosasrc.jtscore.temafinal.model.Sale;
import com.carlosasrc.jtscore.temafinal.util.Constants;

public class SaleController {
	private static SaleController instance;
	private List<Sale> sales;

	public SaleController() {
		sales = new ArrayList<Sale>();
	}

	public static synchronized SaleController getInstance() {
		if (instance == null) {
			instance = new SaleController();
		}
		return instance;
	}

	public void reset() {
		instance = new SaleController();
	}

	public boolean add(String[] lineData) {
		try {
			int id = Integer.parseInt(lineData[1]);

			if (findById(id).isPresent()) {
				return false;
			}

			Sale sale = new Sale();
			sale.setId(id);

			String itens[] = lineData[2].split(Constants.LIST_ITEM_DELIMITER.value);
			int itemId, itemQuantity;
			double itemPrice;
			for (int i = 1; i < itens.length; i++) {
				itemId = Integer.parseInt(itens[i]);
				i++;
				itemQuantity = Integer.parseInt(itens[i]);
				i++;
				itemPrice = Double.parseDouble(itens[i]);
				Item item = new Item(itemId, itemPrice);
				sale.getItens().put(item, itemQuantity);
			}
			sale.setSalesmanName(lineData[3]);
			this.sales.add(sale);
			return true;
		} catch (NullPointerException e) {
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public Optional<Sale> findById(int id) {
		Optional<Sale> salesOptional = Optional.empty();
		for (Sale sale : sales) {
			if (sale.getId() == id) {
				salesOptional = Optional.of(sale);
			}
		}
		return salesOptional;
	}

	public List<Sale> getSales() {
		return sales;
	}
}
