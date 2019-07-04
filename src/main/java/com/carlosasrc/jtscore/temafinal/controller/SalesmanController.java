package com.carlosasrc.jtscore.temafinal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carlosasrc.jtscore.temafinal.model.Salesman;

public class SalesmanController {
	private static SalesmanController instance;
	private List<Salesman> sellers;

	public List<Salesman> getSellers() {
		return sellers;
	}

	public SalesmanController() {
		sellers = new ArrayList<Salesman>();
	}

	public static synchronized SalesmanController getInstance() {
		if (instance == null) {
			instance = new SalesmanController();
		}
		return instance;
	}

	public void reset() {
		instance = new SalesmanController();
	}

	public boolean add(String[] lineData) {
		try {
			String cpf = lineData[1];

			if (findByCnpj(cpf).isPresent()) {
				return false;
			}
			Salesman salesman = new Salesman();
			salesman.setCpf(cpf);
			salesman.setName(lineData[2]);
			salesman.setSalary(Double.parseDouble(lineData[3]));
			this.sellers.add(salesman);
			return true;
		} catch (NullPointerException e) {
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public Optional<Salesman> findByCnpj(String cpf) {
		Optional<Salesman> salesmanOptional = Optional.empty();
		for (Salesman salesman : sellers) {
			if (salesman.getCpf().equals(cpf)) {
				salesmanOptional = Optional.of(salesman);
			}
		}
		return salesmanOptional;
	}

	public Optional<Salesman> findByName(String name) {
		Optional<Salesman> salesmanOptional = Optional.empty();
		for (Salesman salesman : sellers) {
			if (salesman.getName().equals(name)) {
				salesmanOptional = Optional.of(salesman);
			}
		}
		return salesmanOptional;
	}
}
