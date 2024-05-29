package com.restAssured.RestAssuredDemo;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteUser_Del {

	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://reqres.in/";
		RequestSpecification req=RestAssured.given();
		
		JSONObject obj=new JSONObject();
//		obj.put("name", "Ajay");
//		obj.put("job", "Developer");
		
		req.body(obj.toJSONString());
		Response res=req.delete("api/users/2");
		System.out.println(res.asPrettyString());
		System.out.println(res.getStatusLine());
	}
}
