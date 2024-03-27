package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class ExcelREaderData {

	
	@Test
	public HashMap<String,String> loginCred() throws IOException {
		
		String path="/Users/anushakarthick/NumpyNinja/Anusha_Team8_APIArchitects_RestAssured/src/test/resources/TestData/Team_08_API Architects_LMSTestData.xlsx";
		File excel=new File(path);
		FileInputStream fis=new FileInputStream(excel);
		XSSFWorkbook work=new XSSFWorkbook(fis);
		XSSFSheet sht=work.getSheet("Login");
		Cell c1 = sht.getRow(1).getCell(0);
		Cell c2=sht.getRow(1).getCell(1);
		
		HashMap<String,String> hm=new HashMap();
		hm.put("username", c1.getStringCellValue());
		hm.put("password", c2.getStringCellValue());
		return hm;
		
		
	}
}
