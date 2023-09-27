package api_tests;

import java.util.HashMap;
import java.util.Map;
// static import of a class where the members are static
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import utils.RestApiUtils;
import utils.TestDataReader;

public class CraterApiTests {
	
	String baseurl = "http://crater.primetech-apps.com/";

	Response response;
	RestApiUtils apiutils;
	String token;
	int item_id;
	
	
	@Test
	public void login() {
		String endpoind = "api/v1/auth/login";
		String userEmail = TestDataReader.getProperty("email");
		String userPassword = TestDataReader.getProperty("password");
		
		// string request body looks like this
		String body = "{\n"
				+ "    \"username\": \""+userEmail+"\",\n"
				+ "    \"password\": \""+userPassword+"\",\n"
				+ "    \"device_name\": \"mobile_app\"\n"
				+ "}";
		
		// map request body looks like this - not being used in this request.
		
		// serialize and de-serialize in RestAssured.
		// serialize - meaning that converting the java objects into JSON objects.
		// de-serialize - meaning that converting the JSON objects into Java objects.
		
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("username", userEmail);
		requestBody.put("password", userPassword);
		requestBody.put("device_name", "mobile_app");
		
		
		// simple child look way of providing header 
//		response = RestAssured.given()
//				.accept("application/json")
//				.contentType("application/json")
//				.header("company", "1")
//				.body(body)
//				.when()
//				.post(baseurl + endpoind);
		
		// what if we put those headers into a map and use it in the requets? 
		// how does that look like?
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("Accept", "application/json");
		requestHeaders.put("company", "1");
		
		response = 
				given()
				.headers(requestHeaders)
				.body(requestBody)
				.when()
				.post(baseurl + endpoind);
		
		response.then().statusCode(200).contentType("application/json");
		response.prettyPrint();
		
		// get the token from the response and assign it to token String
		token = response.path("token");
		assertEquals(response.path("type").toString(), "Bearer");
	}
	
	@Test(dependsOnMethods = "login")
	public void create_an_item() {
		String endpoint = "api/v1/items";
		
		Map<String, Object> requestHeader = new HashMap<>();
//		 "Authorization": "Bearer {YOUR_AUTH_TOKEN}",
//		    "Content-Type": "application/json",
//		    "Accept": "application/json",
//		    "company": "1",
		
		// faking data using JAVA Faker class
		Faker faker = new Faker();
		String requestBody = "{\n"
				+ "    \"name\": \""+faker.commerce().productName()+"\",\n"
				+ "    \"price\": "+faker.commerce().price()+"\n"
				+ "}";
		
		response = given()
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.header("company", "1")
				.header("Authorization", "Bearer "+token)
				.body(requestBody)
				.when()
				.post(baseurl + endpoint);
		
		response.prettyPrint();
		item_id = response.path("data.id");
		
	}
	
	@Test(dependsOnMethods = "create_an_item")
	public void delete_the_item() {
		String endpoint = "api/v1/items/delete";
		
		String body = "{\n"
				+ "    \"ids\": [\n"
				+ "        "+item_id+"\n"
				+ "    ]\n"
				+ "}";
		
		response = given()
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.header("company", "1")
				.header("Authorization", "Bearer "+token)
				.body(body)
				.when()
				.post(baseurl + endpoint);
		
		response.prettyPrint();
		response.then().statusCode(200).contentType("application/json");
		assertTrue(response.path("success"));
	}
	
	@Test(dependsOnMethods = "delete_the_item")
	public void logout() {
		String endpoint = "api/v1/auth/logout";
		response = given()
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.header("company", "1")
				.header("Authorization", "Bearer "+token)
				.when()
				.post(baseurl + endpoint);
		
		response.prettyPrint();
		response.then().statusCode(200).contentType("application/json");
	}
	
	
	@Test
	public void fakerTest() {
		Faker faker = new Faker();
		
		System.out.println(faker.name().fullName());
		System.out.println(faker.name().lastName());
		System.out.println(faker.name().nameWithMiddle());
		
		System.out.println(faker.cat().name());
		
		System.out.println(faker.address().streetAddress());
		System.out.println(faker.address().fullAddress());
		
		System.out.println(faker.commerce().productName());
		System.out.println(faker.commerce().price());
		System.out.println(faker.commerce().material());
	}

}
