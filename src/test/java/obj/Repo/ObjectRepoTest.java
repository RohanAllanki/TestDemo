package obj.Repo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

public class ObjectRepoTest 
{
	RequestSpecification req;
	Response res;
	File file;
	FileInputStream fis;
	
	@BeforeTest
	public void init()
	{
		RestAssured.baseURI="https://reqres.in/";
	}
	
	@Test
	  public void addData() throws IOException
	  {
		
		file=new File("C:\\Users\\RALLANKI\\Desktop\\RestAssuredDemo\\src\\test\\java\\obj\\Repo\\Postdata.properties");
		fis=new FileInputStream(file);
		Properties p=new Properties();
		p.load(fis);
		String name=p.getProperty("name1");
		String job=p.getProperty("job1");
		
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
