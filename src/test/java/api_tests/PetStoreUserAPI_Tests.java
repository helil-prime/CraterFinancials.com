package api_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RestApiUtils;

public class PetStoreUserAPI_Tests {
	RestApiUtils utils = new RestApiUtils();
	Faker faker = new Faker();
	Response response;
	String baseURL = "https://petstore.swagger.io/v2";
	
	int id;
	String username;
	String firstName;
	String lastName;
	String email;
	String password;
	String phone;
	
	
	@Test
	public void createNewUser() {
		String endpoint = "/user";
		id = utils.randomNumber();
		username = faker.name().username();
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		email = firstName+lastName+"@gmail.com";
		password = "Pass123";
		phone = faker.phoneNumber().toString();
		
		String body = "{\n"
				+ "  \"id\": "+id+",\n"
				+ "  \"username\": \""+username+"\",\n"
				+ "  \"firstName\": \""+firstName+"\",\n"
				+ "  \"lastName\": \""+lastName+"\",\n"
				+ "  \"email\": \""+email+"\",\n"
				+ "  \"password\": \""+password+"\",\n"
				+ "  \"phone\": \""+phone+"\",\n"
				+ "  \"userStatus\": 0\n"
				+ "}";
		
		response = RestAssured.given()
			.accept(ContentType.JSON)
			.contentType("application/json")
			.body(body)
		.when()
			.post(baseURL+endpoint);
		
		response.then()
			.contentType("application/json")
			.statusCode(200);
		System.out.println(username);
		System.out.println(password);
		response.prettyPrint();
	}
	
	@Test (dependsOnMethods = "createNewUser")
	public void getAUserWithUserName() {
		String endpoint = "/user/";
		
		response = RestAssured.given()
//			.header("accept", "application/json")
			.accept("application/json")
//			.accept(ContentType.JSON)
		.when()
			.get(baseURL+endpoint+username);
		
		response.then()
			.statusCode(200)
			.contentType("application/json");
		
		response.prettyPrint();
		
		Assert.assertEquals(Integer.parseInt(response.path("id").toString()), id);
		Assert.assertEquals(response.jsonPath().get("username"), username);
		Assert.assertEquals(response.path("firstName").toString(), firstName);
		Assert.assertEquals(response.path("lastName").toString(), lastName);
		Assert.assertEquals(response.path("email").toString(), email);
		Assert.assertEquals(response.path("password").toString(), password);
		Assert.assertEquals(response.path("phone").toString(), phone);
	}
	
	@Test
	public void userNotFound() {
		String endpoint = "/user/";
		
		response = RestAssured.given()
			.accept("application/json")
		.when()
			.get(baseURL+endpoint+"fakeUserName");
		
		response.then()
			.statusCode(404)
			.contentType("application/json");
		
		response.prettyPrint();
		
		Assert.assertEquals(response.path("type").toString(), "error");
		Assert.assertEquals(response.path("message").toString(), "User not found");
	}
	
	@Test (dependsOnMethods = "createNewUser")
	public void loginNewUser() {
		String endpoint = "/user/login";
		
		response = RestAssured.given()
			.header("Accept", "application/json")
			.param(username)
			.param(password)
		.when()
			.get(baseURL+endpoint);
		
		response.then()
			.contentType("application/json")
			.statusCode(200);
		
		response.prettyPrint();
		
		Assert.assertNotNull(response.jsonPath().get("message").toString());
	}
}
