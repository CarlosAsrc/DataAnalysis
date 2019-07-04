package com.carlosasrc.jtscore.temafinal.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.carlosasrc.jtscore.temafinal.controller.CustomerController;
import com.carlosasrc.jtscore.temafinal.controller.SaleController;
import com.carlosasrc.jtscore.temafinal.controller.SalesmanController;
import com.carlosasrc.jtscore.temafinal.exception.DataNotFoundException;
import com.carlosasrc.jtscore.temafinal.exception.InvalidFileFormatException;
import com.carlosasrc.jtscore.temafinal.util.Constants;

public class DataAnalysisDao {

	private static DataAnalysisDao instance;
	private String delimiter;
	private Scanner in;
	private String inPath;
	private String outPath;

	public DataAnalysisDao() {
		inPath = System.getProperty("user.home") + "/data/in/";
		outPath = System.getProperty("user.home") + "/data/out/";
	}

	public static synchronized DataAnalysisDao getInstance() {
		if (instance == null) {
			instance = new DataAnalysisDao();
		}
		return instance;
	}

	public void createDirectories() {
		File file = new File(inPath);
		file.mkdirs();
		file = new File(outPath);
		file.mkdirs();
	}

	public boolean readFile(String fileName) {
		try {
			if (!fileName.matches(Constants.FILE_NAME_VALIDATOR.value)) {
				throw new InvalidFileFormatException("File format is invalid.");
			}

			FileReader file = new FileReader(inPath + fileName);
			in = new Scanner(file);
			String line;
			in.useDelimiter(Constants.DATA_LINE_VALIDATOR.value);
			while (in.hasNext()) {
				line = in.next().trim();
				if (!line.isEmpty()) {
					this.delimiter = line.charAt(3) + "";
					String[] lineData = line.split(delimiter);
					saveObject(lineData);
				}
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Input directory not found!");
			return false;
		}
	}

	public String readOutputFile() {
		try {
			FileReader file = new FileReader(outPath + Constants.OUTPUT_FILE_NAME.value);
			in = new Scanner(file);
			in.useDelimiter("\\Z");
			return in.next();

		} catch (FileNotFoundException e) {
			throw new DataNotFoundException("No file found!");
		}
	}

	public void readAllFiles() {
		resetDataInMemory();
		File[] files = new File(inPath).listFiles();
		for (File file : files) {
			readFile(file.getName());
		}
	}

	private void resetDataInMemory() {
		CustomerController.getInstance().reset();
		SalesmanController.getInstance().reset();
		SaleController.getInstance().reset();
	}

	public void writeFile(String fileName, String data) {
		try {
			FileWriter fileWriter = new FileWriter(outPath + fileName);
			fileWriter.write(data);
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Output directory not found!");
		}
	}

	public void saveObject(String[] lineData) {
		String dataType = lineData[0];
		
		switch (dataType) {
			case "001":
				SalesmanController.getInstance().add(lineData);
				break;
			case "002":
				CustomerController.getInstance().add(lineData);
				break;
			case "003":
				SaleController.getInstance().add(lineData);
				break;
			}
	}

	public String getWorstSalesmanEver(String fileName) {
		try {
			FileReader file = new FileReader(outPath + fileName);
			in = new Scanner(file);
			String line;
			while (in.hasNextLine()) {
				line = in.nextLine();
				String[] data = line.split(":");
				if (data[0].equals("Worst salesman ever")) {
					return data[1];
				}
			}
			throw new DataNotFoundException("Worst salesman not found.");
		} catch (FileNotFoundException e) {
			throw new DataNotFoundException("No file found!");
		}
	}
	
	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}
	
	public String getInPath() {
		return inPath;
	}

	public void setInPath(String inPath) {
		this.inPath = inPath;
	}
}