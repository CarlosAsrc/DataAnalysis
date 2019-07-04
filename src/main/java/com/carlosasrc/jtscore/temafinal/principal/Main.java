package com.carlosasrc.jtscore.temafinal.principal;
import java.util.Scanner;

import com.carlosasrc.jtscore.temafinal.controller.CustomerController;
import com.carlosasrc.jtscore.temafinal.controller.SaleController;
import com.carlosasrc.jtscore.temafinal.controller.SalesmanController;

public class Main {
	
	private static Scanner in = new Scanner(System.in);
	private static DataAnalysis dataAnalysis = DataAnalysis.getInstance();
	
	public static void main(String[] args) {
		dataAnalysis.start();
		menu();
	}
	
	public static void menu() {
		String option = "";
		while(true) {
			printOptions();
			option = in.nextLine();
			switch (option) {
			case "1":
				printData();
				break;
			case "2":
				System.out.println("\nData in output file: \n\n"+dataAnalysis.getOutputData());
				break;
			default:
				System.out.println("\nInvalid option!");
				break;
			}
		}
	}
	
	public static void printOptions() {
		System.out.println(
				"\n\n\n\n-----------DATA ANALYSIS SYSTEM-----------"+
				"\n\nSelect one of the two viewing options:"+
				"\n\n1 - View all data from the input files."+
				"\n2 - View all data from the output file."
		);
	}
	
	public static void printData() {
		System.out.println("\nAll sellers: ");
		SalesmanController.getInstance().getSellers().forEach((o) -> {
			System.out.println(o.toString());
		});
		System.out.println("\nAll customers: ");
		CustomerController.getInstance().getCostumers().forEach((o) -> {
			System.out.println(o.toString());
		});
		System.out.println("\nAll sales: ");
		SaleController.getInstance().getSales().forEach((o) -> {
			System.out.println(o.toString());
		});
	}
}
