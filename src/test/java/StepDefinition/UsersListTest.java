package StepDefinition;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsersListTest 
{
	RequestSpecification req;
	Response res;
	
	@Given("user is on reqres URL")
	public void user_is_on_regres_url() {
	    // Write code here that turns the phrase above into concrete actions
	    RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given();
	    System.out.println("Given step");
	}

	@When("user hits the Users API")
	public void user_hits_the_users_api() {
	    // Write code here that turns the phrase above into concrete actions
	    res=req.get("api/users?page=2");
	    System.out.println("When step");
	}

	@Then("all the users is displayed")
	public void all_the_users_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    String data=res.asPrettyString(); // result/output
	    JsonPath path=res.jsonPath();
	    String id=path.getString("data[1].id");
	    Assert.assertEquals(id,"8","id matched");
	    System.out.println("Then Step");
	}
	
	@Given("user is on reqres URL")
	public void user_is_on_reqres_url() {
	    // Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given();
	    System.out.println("Given step");
	}

	@When("user hits the Single User API")
	public void user_hits_the_single_user_api() {
	    // Write code here that turns the phrase above into concrete actions
		res=req.get("api/users/2");
		System.out.println("When step");
		
	}

	@Then("single user is displayed")
	public void single_user_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		@SuppressWarnings("unused")
		String data=res.asPrettyString(); // result/output
		JsonPath path=res.jsonPath();
		String first_name=path.getString("data.first_name");
		Assert.assertEquals(first_name,"Janet","first_name");
		System.out.println("Then step");
	}

}
