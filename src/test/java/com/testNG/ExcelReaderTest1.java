package com.testNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExcelReaderTest1
{
	RequestSpecification req;
	Response res;
	File file;
	FileInputStream fis;
	XSSFWorkbook w;
	XSSFSheet s;
	
	@BeforeTest
	public void init()
	{
		RestAssured.baseURI="https://reqres.in/";
	}
	
	@Test
	  public void addData() throws IOException
	  {
		
		file=new File("C:\\Users\\RALLANKI\\Desktop\\RestAssuredDemo\\src\\test\\resource\\ExcelData\\Data1.xlsx");
		fis=new FileInputStream(file);
		w=new XSSFWorkbook(fis);
//		s=w.getSheetAt(0);
		s=w.getSheet("Userdata");
		
		int row=s.getPhysicalNumberOfRows();
		System.out.println("Rows: "+row);
		int col=s.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Cols: "+col);
		String name=s.getRow(0).getCell(0).toString();
		String job=s.getRow(0).getCell(1).getStringCellValue();
		

		  req=RestAssured.given();
		  JSONObject obj=new JSONObject();
		  obj.put("name", name);
		  obj.put("job", job);
		  req.header("Content-Type", "application/json");
		  res=req.body(obj.toJSONString()).post("api/users");
		  System.out.println(obj);
		  JsonPath data=res.jsonPath();
		  String job1=data.getString("job");
		  System.out.println(job1);
		  Assert.assertEquals(job1, job);

	  }
	  @AfterTest
	  public void deallocateMem()
	  {
		  req=null;
		  res=null;
	  }
	}
