package step;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RunWithExcel
{
	RequestSpecification req;
	Response res;
	JSONObject obj;
	JsonPath path;
	File file;
	FileInputStream fis;
	String expJ,expN;
	Workbook w;
	Sheet s;
	
	@Given("User is on the reqres website")
	public void user_is_on_the_reqres_website() {
	    // Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given();
	    System.out.println("Given step");
	}

	@When("User enters the data from the excel file")
	public void user_enters_the_data_from_the_excel_file() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   file=new File("C:\\Users\\RALLANKI\\Desktop\\RestAssuredDemo\\src\\test\\resource\\ExcelData\\Data1.xlsx");
	   fis=new FileInputStream(file);
	   w=new XSSFWorkbook(fis);
	   s=w.getSheet("Userdata");
	   
	   String name=s.getRow(0).getCell(0).toString();
	   String job=s.getRow(0).getCell(1).toString();
	   obj=new JSONObject();
	   obj.put("name", name);
	   obj.put("job", job);
	   expJ=job;
	   expN=name;
	   req.header("Content-Type","application/json");
	   
	}

	@And("User hits the users API")
	public void user_hits_the_users_api() {
	    // Write code here that turns the phrase above into concrete actions
		 res=req.body(obj.toJSONString()).post("api/users");
		    System.out.println("And step");
		}

	@Then("Users are added to the list")
	public void users_are_added_to_the_list() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println(res.asPrettyString());
	    path=res.jsonPath();
	    String job1=path.getString("job");
	    System.out.println(job1);
	    Assert.assertEquals(job1, expJ);
	}

}