package com.carlosasrc.jtscore.temafinal.principal;

import java.util.Optional;

import com.carlosasrc.jtscore.temafinal.controller.CustomerController;
import com.carlosasrc.jtscore.temafinal.controller.SaleController;
import com.carlosasrc.jtscore.temafinal.controller.SalesmanController;
import com.carlosasrc.jtscore.temafinal.dao.DataAnalysisDao;
import com.carlosasrc.jtscore.temafinal.exception.DataNotFoundException;
import com.carlosasrc.jtscore.temafinal.model.Sale;
import com.carlosasrc.jtscore.temafinal.model.Salesman;
import com.carlosasrc.jtscore.temafinal.util.Constants;

public class DataAnalysis {

	private static DataAnalysis instance;
	private DataAnalysisDao dataAnalysisDao;
	private DirectoryWatcher directoryWatcher;
	private static String inPath = System.getProperty("user.home") + "/data/in";;

	public DataAnalysis() {
		dataAnalysisDao = DataAnalysisDao.getInstance();
		dataAnalysisDao.createDirectories();
		directoryWatcher = DirectoryWatcher.getInstance();
		start();
	}

	public static synchronized DataAnalysis getInstance() {
		if (instance == null) {
			instance = new DataAnalysis();
		}
		return instance;
	}

	public void start() {
		dataAnalysisDao.readAllFiles();
		directoryWatcher.start(inPath);
	}

	public void refreshData() {
		dataAnalysisDao.readAllFiles();
		dataAnalysisDao.writeFile(Constants.OUTPUT_FILE_NAME.value, generateOutputData());
	}

	public String getOutputData() {
		try {
			return dataAnalysisDao.readOutputFile();
		} catch (DataNotFoundException e) {
			return "";
		}
	}

	public String generateOutputData() {
		String outputData = "Amount of clients:" + getAmountOfClients() + "\nAmount of salesman:" + getAmountOfSalesman()
				+ "\nID of the most expensive sale:" + getMostExpensiveSaleId() + "\nWorst salesman ever:"
				+ getWorstSalesmanEver();
		return outputData;
	}

	public int getAmountOfClients() {
		return CustomerController.getInstance().getCostumers().size();
	}

	public int getAmountOfSalesman() {
		return SalesmanController.getInstance().getSellers().size();
	}

	public int getMostExpensiveSaleId() {
		double valueMostExpensiveSale = 0;
		int idMostExpensiveSale = 0;
		for (Sale sale : SaleController.getInstance().getSales()) {
			if (sale.getSaleValue() > valueMostExpensiveSale) {
				valueMostExpensiveSale = sale.getSaleValue();
				idMostExpensiveSale = sale.getId();
			}
		}
		return idMostExpensiveSale;
	}

	public String getWorstSalesmanEver() {
		double totalSalesValue = 0, saleValue = 0;
		Optional<Salesman> worstSalesman = Optional.empty();
		for (Salesman salesman : SalesmanController.getInstance().getSellers()) {
			for (Sale sale : SaleController.getInstance().getSales()) {
				if (salesman.getName().equalsIgnoreCase(sale.getSalesmanName())) {
					saleValue += sale.getSaleValue();
				}
			}
			if (saleValue > totalSalesValue) {
				totalSalesValue = saleValue;
				worstSalesman = Optional.of(salesman);
			}
			saleValue = 0;
		}

		if (worstSalesman.isPresent()) {
			return worstSalesman.get().toString();
		}
		return "";
	}
}