package com.testNG;
 
import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
 
 
public class TestParameters1 {
	RequestSpecification req;
	Response res;
	@BeforeTest
	public void init()
	{
		RestAssured.baseURI="https://reqres.in/";
	}
  @Test(priority=-1)
  public void getData() 
  {
	  req=RestAssured.given();
	  res=req.get("api/users?page=2");
	  System.out.println(res.asString());
	  JsonPath data=res.jsonPath();
	  String email=data.getString("data[1].email");
	  System.out.println(email);
	  Assert.assertEquals(email, "lindsay.ferguson@reqres.in");
	  String first_name=data.getString("data[0].first_name");
	  System.out.println(first_name);
	  Assert.assertEquals(first_name, "Michael");
	  String id=data.getString("data[0].id");
	  System.out.println(id);
	  Assert.assertEquals(id, "7");
	  String page=data.getString("page");
	  System.out.println(page);
	  Assert.assertEquals(page, "2");
	  }
  @Test
  @Parameters({"name","job"})
  public void addData(String name,String job)
  {
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