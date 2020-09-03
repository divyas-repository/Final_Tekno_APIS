package Teknotrait_employee.Teknotrait_employee;

import static io.restassured.RestAssured.given;

import java.util.Date;

import org.apache.poi.ss.formula.functions.T;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Category_module {

	public static String Token = "eyJ0eXAiOiJKV1QiLCJhbGciOi" + "JSUzI1NiIsImp0aSI6Ijg4ODVlY2FkNGMyYWExZ"
			+ "TkxNWZiNmIzZWVlNWY0ZDllNzQ3YWI5Nzc2YjdhYzE4M" + "zZmODc0ZTdkZWQxNTZjMTRiNWY5ODM5MjE1YzA2NDkwIn0.eyJhdWQ"
			+ "iOiIxIiwianRpIjoiODg4NWVjYWQ0YzJhYTFlOTE1ZmI2YjNlZWU1ZjRk"
			+ "OWU3NDdhYjk3NzZiN2FjMTgzNmY4NzRlN2RlZDE1NmMxNGI1Zjk4MzkyMTVj"
			+ "MDY0OTAiLCJpYXQiOjE1OTg4ODMzNTIsIm5iZiI6MTU5ODg4MzM1MiwiZXhwIjoxNjM"
			+ "wNDE5MzUyLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.DU4IGox48y6eCy4FACy-OYyMuQaxl-L"
			+ "Z8gXrKZZVqRTt3cZ8kUbChkGueYyTyqFBoLpNLClTdgOkIHU6m3OsFV4d_JZb0umRehW-cQ88u1khEoxu"
			+ "672UsxmAtTgdbhAS5caLcaggBTMhLf3NYvR4SoNjPW_H-XqpCJXrn4cnK1ASQx_5tAq6qVPnX5"
			+ "WEUsmcs7Y_cqVCnIIVoY8QYl4123vGZqGeJUC_-RvoJwASwNES-lcO0EDKJYmAP5UdtRE8fuA6"
			+ "bYeynNNqsZY5fuV3M_MW-AKa1K2WOu-t3d-vzoNaDyGCt5tt7-Pc2q"
			+ "LHqUGGwSHC8EAv7MF7Fj9xuvWz-FYwzTh7I-lX4CQ-cMlR5hoUqoB62NSrNJAt"
			+ "WYUNQ2YQmt0Kjb5tV1Mecb6oN1b7EM7RTXwi7qyBcgeauKyNesvsNGP93tB9wC81I"
			+ "6uTgWk0q9_dvCK3rmWvZn-F8ZZek83-3ZGa8I3dcEPRlWGIu49EexrQS3woqUQZ34"
			+ "Tk0j8XWRFYewKqPOI4YUWisz1tEfb60MRzKklUyooELFVHNskeAD5gMPNZVAPH9PRubGC"
			+ "jsbYRgacMp67-Re4i2l3pOxMryrhe15FWdHJX_XnQiLNWILx2lpDfOAomHWAn_NfVzIIaGRqE"
			+ "YwE3U2F1RP-iGDU5E2nI8CzJNjtVJ6vl1l4";

	Long CategoryId;
	String CategoryName;

	// Add
	@Test(priority = 1)
	public void addCategory() {

		JSONObject request = new JSONObject();

		long date = new Date().getTime();
		
		System.out.println(date);
		
		request.put("category_name", "Divya" + date);
		
		request.put("company_id", "3");
		
		System.out.println(request.toJSONString());

		Response response = (Response) given().auth().oauth2(Token)
				.header("Content-type", "Application/json")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(request.toJSONString())
				.when()
				.post("https://dev.teknotrait.com/public/api/addCategory")
				.then().log().all().extract().body();

		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(response.getBody().asString());
			
			CategoryId = (Long) ((JSONObject) json.get("success")).get("id");

			System.out.println("New category id is: " + CategoryId);

		} catch (Exception e) {

		}

	}

	// Get
	@Test(priority = 2)
	public void getCategoryById() {

		JSONObject request = new JSONObject();
		request.put("catID", CategoryId);
		request.put("company_id", "3");
		System.out.println(request.toJSONString());

		Response response = (Response) given().auth().oauth2(Token).header("Content-type", "Application/json")
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).when()
				.post("https://dev.teknotrait.com/public/api/getCategoryById").then().log().all().extract().body();

		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(response.getBody().asString());
			CategoryName = (String) ((JSONObject) json.get("success")).get("category_name");

			System.out.println("New category name is: " + CategoryName);

		} catch (Exception e) {

		}
	}

	// Edit
	@Test(priority = 3)
	public void editCategoryById() {

		JSONObject request = new JSONObject();
		request.put("category_id", CategoryId);
		request.put("category_name", CategoryName + "Divya");
		request.put("company_id", "3");
		System.out.println(request.toJSONString());

		given().auth().oauth2(Token).header("Content-type", "Application/json").contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(request.toJSONString()).when()
				.post("https://dev.teknotrait.com/public/api/editCategory").then().log().all();

	}

	@Test(priority = 4)
	public void deleteCategory() {
		JSONObject request =new JSONObject(); 
		request.put("company_id", "3");
		request.put("category_id", CategoryId);
		System.out.println(request.toJSONString());
		given().auth().oauth2(Token) .header("Content-type", "Application/json")
		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(request.toJSONString()) .when()
		.post("https://dev.teknotrait.com/public/api/deleteCategory")
		.then().log().all();
	 }
	 
}
