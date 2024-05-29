package steps;

import java.util.List;

import org.json.simple.JSONObject;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.DataProvider;

public class AddUserTest 
{
	RequestSpecification req;
	Response res;
	JSONObject obj;
	JsonPath path;
	String expJ;
	String expN;
	
//	@DataProvider(name="createUser")
//	public Object[][] getData()
//	{
//		Object[][] data=new Object[3][2];
//		data[0][0]="deepa";
//		data[0][1]="consultant";
//		data[1][0]="Kunal";
//		data[1][1]="consultant";
//		data[2][0]="Suraj";
//		data[2][1]="Sr.Engg";
//		return data;
//	}
	
	
//------------------------Scenario 1----------------------------------------
	
	@Given("User is on reqres URL")
	public void user_is_on_reqres_url() {
	    // Write code here that turns the phrase above into concrete actions
	    RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given();
	    System.out.println("Given step");
	}

	@When("^user enters the (.*) and (.*)$")
	public void user_enters_the_and(String name, String job) {
	    // Write code here that turns the phrase above into concrete actions
	    obj = new JSONObject();
	    obj.put("name", name);
	    obj.put("job", job);
	    expJ=job;
	    expN=name;
	    req.header("Content-Type", "application/json");
	    System.out.println(obj);
	    System.out.println("When step");
	}

	@And("users hit the users API")
	public void users_hit_the_users_api() {
	    // Write code here that turns the phrase above into concrete actions
	    res=req.body(obj.toJSONString()).post("api/users");
	    System.out.println("And step");
	}

	@Then("users are added to list")
	public void users_are_added_to_list() {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println(res.asPrettyString());
	    path=res.jsonPath();
	    String job1=path.getString("job");
	    System.out.println(job1);
	    Assert.assertEquals(job1, expJ);
	    
	}

//----------------------------Scenario 2---------------------------------------
	
	
	@When("User enters name & job")
	public void user_enters_name_job(DataTable data) {
	    List<List<String>> udata=data.asLists(String.class);
	    String name=udata.get(0).get(0);
	    String job=udata.get(0).get(1);
	
		obj=new JSONObject();
		obj.put("name",name);
		obj.put("job",job);
		expJ=job;
		expN=name;
		req.header("Content-Type","application/json");
		
		System.out.println(obj);
		System.out.println("When step");
	}
	@And("user hits the Api")
	public void user_hits_the_api() {
	    // Write code here that turns the phrase above into concrete actions
		res=req.body(obj.toJSONString()).put("api/users/2");
	    System.out.println("And step");
	}

	@Then("user data is updated")
	public void user_data_is_updated() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println(res.asPrettyString());
	    path=res.jsonPath();
	    String job1=path.getString("job");
	    System.out.println(job1);
	    Assert.assertEquals(job1, expJ);
	}
}
