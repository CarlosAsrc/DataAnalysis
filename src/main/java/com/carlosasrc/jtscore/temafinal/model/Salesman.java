package com.carlosasrc.jtscore.temafinal.model;

public class Salesman {
	private static final String DATA_TYPE = "001";
	private String cpf;
	private String name;
	private double salary;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public static String getDataType() {
		return DATA_TYPE;
	}

	@Override
	public String toString() {
		return "Salesman [cpf=" + cpf + ", name=" + name + ", salary=" + salary + "]";
	}
}