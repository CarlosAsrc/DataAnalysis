package com.carlosasrc.jtscore.temafinal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carlosasrc.jtscore.temafinal.model.Customer;

public class CustomerController {
	private static CustomerController instance;
	private List<Customer> customers;

	public List<Customer> getCostumers() {
		return customers;
	}

	public CustomerController() {
		customers = new ArrayList<Customer>();
	}

	public static synchronized CustomerController getInstance() {
		if (instance == null) {
			instance = new CustomerController();
		}
		return instance;
	}

	public void reset() {
		instance = new CustomerController();
	}

	public boolean add(String[] lineData) {
		String cnpj = lineData[1];
		if (findByCnpj(cnpj).isPresent()) {
			return false;
		}
		Customer customer = new Customer();
		customer.setCnpj(lineData[1]);
		customer.setName(lineData[2]);
		customer.setBusinessArea(lineData[3]);
		this.customers.add(customer);
		return true;
	}

	public Optional<Customer> findByCnpj(String cnpj) {
		Optional<Customer> customerOptional = Optional.empty();
		for (Customer customer : customers) {
			if (customer.getCnpj().equals(cnpj)) {
				customerOptional = Optional.of(customer);
			}
		}
		return customerOptional;
	}
}
