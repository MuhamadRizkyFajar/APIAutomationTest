package com.axiatadigitallabs.apitest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.Scanner;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestAPI_ALL {
	
	String endpoint = "https://gorest.co.in/public/v1/users";
	String name = "Ranja";
	String email = "ranja@adita.com";
	String gender = "male";
	String status = "inactive";
	
	
	@Test(priority = 0)
	public void test01_post() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", name);
		request.put("email", email);
		request.put("gender", gender);
		request.put("status", status);

		System.out.println(request.toJSONString());
		
		given()
		.header("Content-Type", "application/json")
		.auth().oauth2("e221554952903d729a2d28689f91410d92233f975106e5bc2532b1634d92cf96")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post(endpoint) //POST
		.then()
		.statusCode(201)
		.body("data.name", equalTo(name))
		.log().all();
	}
	
	@Test(priority = 1)
	public void test_01_get() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", name);
		request.put("email", email);
		request.put("gender", gender);
		request.put("status", status);
		
		System.out.println(request.toJSONString());
		
		given()
		.header("Content-Type", "application/json")
		.auth().oauth2("e221554952903d729a2d28689f91410d92233f975106e5bc2532b1634d92cf96")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.get(endpoint)
		.then()
		.statusCode(200)
		.body("data.name[0]", equalTo(name));
	}
	
	@Test(priority = 2)
	public void test01_put() {
		
		JSONObject request = new JSONObject();
		
		String updategender="female";
		String updatestatus="active";
		
		request.put("gender", updategender);
		request.put("status", updatestatus);
		
		String id =id_user();
		
//		System.out.println(request);
		System.out.println(request.toJSONString());
		
		given()
		.header("Content-Type", "application/json")
		.auth().oauth2("e221554952903d729a2d28689f91410d92233f975106e5bc2532b1634d92cf96")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.patch(endpoint.concat(id)) //PUT
		.then()
		.statusCode(200)
		.body("data.gender", equalTo(updategender))
		.log().all();
	}
	
	@Test(priority = 3)
	public void test01_delete() {
		
		String id =id_user();
		
		given()
		.header("Content-Type", "application/json")
		.auth().oauth2("e221554952903d729a2d28689f91410d92233f975106e5bc2532b1634d92cf96")
		.when()
		.delete(endpoint.concat(id)) //DELETE
		.then()
		.statusCode(204)
		.log().all();
	}
	
	public String id_user() {
		Scanner input = new Scanner(System.in);
		System.out.print("Whats your id : ");
		String id = input.nextLine();
		return "/"+id;
	}
	
	

}
