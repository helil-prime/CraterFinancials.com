package api_tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.RestApiUtils;
import utils.TestDataReader;

public class CraterCustomersAPITests {
	RestApiUtils utils = new RestApiUtils();
	Faker faker = new Faker();
	Response response;
	String token;
	String firstName;
	String lastName;
	String fullName;
	String email;
	
	String id;
	
	
	String baseURL = "http://crater.primetech-apps.com/";

	
	@Test (priority = 0)
	public void craterLogin() {
		String endpoint = "api/v1/auth/login";
		String username = TestDataReader.getProperty("email");
		String password = TestDataReader.getProperty("password");
		
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("accept", "application/json");
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("company", "1");
		
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("username", username);
		requestBody.put("password", password);
		requestBody.put("device_name", "mobile_app");
		
		response = RestAssured.given()
			.headers(requestHeaders)
			.body(requestBody)
		.when()
			.post(baseURL+endpoint);
		
		response.then()
			.statusCode(200)
			.contentType("application/json");
		
//		response.prettyPrint();
		
		token = response.path("token").toString();
		
		Assert.assertEquals(response.path("type").toString(), "Bearer");
	}
	
	@Test (dependsOnMethods = "craterLogin", priority = 1)
       	public void listAllCustomers() {
		String endpoint = "api/v1/customers";
		
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("accept", "application/json");
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("company", "1");
		requestHeaders.put("Authorization", "Bearer "+token);
		
		response = RestAssured.given()
			.param("limit", "15")
			.headers(requestHeaders)
		.when()
			.get(baseURL+endpoint);
		
		response.then()
			.statusCode(200)
			.contentType("application/json");
		
		response.prettyPrint();
		
		Assert.assertNotNull(response.path("data").toString());
	}
	
	@Test (dependsOnMethods = "craterLogin", priority = 2)
	public void createNewCustomer() {
		String endpoint = "api/v1/customers";
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		fullName = firstName + " " + lastName;
		email = firstName + lastName + "@gmail.com";
		
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("accept", "application/json");
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("company", "1");
		requestHeaders.put("Authorization", "Bearer "+token);
		
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("name", fullName);
		requestBody.put("email", email);
		requestBody.put("enable_portal", true);
		
		response = RestAssured.given()
			.headers(requestHeaders)
			.body(requestBody)
		.when()
			.post(baseURL+endpoint);
		
		response.prettyPrint();
		
		response.then()
			.statusCode(200)
			.contentType("application/json");
		

		id = response.path("data.id").toString();
		
		Assert.assertEquals(response.path("data.name").toString(), fullName);
		Assert.assertEquals(response.path("data.email").toString(), email);
		Assert.assertTrue(response.path("data.enable_portal"));
	}
	
	@Test (dependsOnMethods = "createNewCustomer")
	public void retrieveCustomerWithId() {
		String endpoint = "api/v1/customers/" + id;
		
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("accept", "application/json");
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("company", "1");
		requestHeaders.put("Authorization", "Bearer "+token);
		
		response = RestAssured.given()
			.headers(requestHeaders)
		.when()
			.get(baseURL+endpoint);
		
		response.prettyPrint();
		
		response.then()
			.statusCode(200)
			.contentType("application/json");
		
		Assert.assertEquals(response.path("data.name").toString(), fullName);
		Assert.assertEquals(response.path("data.email").toString(), email);
		Assert.assertTrue(response.path("data.enable_portal"));
	}
	
	@Test (dependsOnMethods = "createNewCustomer")
	public void updateExisitingCustomer() {
		String endpoint = "api/v1/customers/"+id;
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		fullName = firstName + " " + lastName;
		email = firstName + lastName + "@gmail.com";
		
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("accept", "application/json");
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("company", "1");
		requestHeaders.put("Authorization", "Bearer "+token);
		
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("name", fullName);
		requestBody.put("email", email);
		requestBody.put("enable_portal", false);
		
		response = RestAssured.given()
			.headers(requestHeaders)
			.body(requestBody)
		.when()
			.put(baseURL+endpoint);
		
		response.prettyPrint();
		
		Assert.assertEquals(response.path("data.name").toString(), fullName);
		Assert.assertEquals(response.path("data.email").toString(), email);
		Assert.assertFalse(response.path("data.enable_portal"));
	}
}
