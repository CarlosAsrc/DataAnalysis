package com.carlosasrc.jtscore.temafinal.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.carlosasrc.jtscore.temafinal.controller.CustomerController;
import com.carlosasrc.jtscore.temafinal.controller.SaleController;
import com.carlosasrc.jtscore.temafinal.controller.SalesmanController;
import com.carlosasrc.jtscore.temafinal.dao.DataAnalysisDao;
import com.carlosasrc.jtscore.temafinal.principal.DataAnalysis;

public class DataAnalysisTest {

	private DataAnalysis dataAnalysis;
	private DataAnalysisDao dataAnalysisDao;
	
	@Before
    public void start() {
		dataAnalysis = DataAnalysis.getInstance();
		dataAnalysisDao = DataAnalysisDao.getInstance();
		dataAnalysisDao.setOutPath(System.getProperty("user.home") + "/data/in/");
		String data = 	"001ç1234567891234çDiegoç50000\n" + 
						"001ç3245678865434çRenatoç40000.99\n" + 
						"002ç2345675434544345çJose da SilvaçRural\n" + 
						"002ç2345675433444345çEduardoPereiraçRural\n" + 
						"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego\n" + 
						"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato";	
		dataAnalysisDao.writeFile("test.dat", data);
		dataAnalysisDao.setOutPath(System.getProperty("user.home") + "/data/out/");
		dataAnalysis.start();
    }
	
	@Test
	public  void shouldLoadAllDataTest() {
		DataAnalysis.getInstance().start();
		int count = SalesmanController.getInstance().getSellers().size();
		assertEquals(2, count);
		count = CustomerController.getInstance().getCostumers().size();
		assertEquals(2, count);
		count = SaleController.getInstance().getSales().size();
		assertEquals(2, count);
	}

	@After
	public void after() {
		File[] files = new File(dataAnalysisDao.getInPath()).listFiles();
		for (File file : files) {
			file.delete();
		}
		files = new File(dataAnalysisDao.getOutPath()).listFiles();
		for (File file : files) {
			file.delete();
		}
	}
}
