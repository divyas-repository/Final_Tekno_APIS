package Teknotrait_employee.Teknotrait_employee;
	import java.util.HashMap;
	import static io.restassured.RestAssured.*;
	import java.util.Map;

	import org.json.simple.JSONObject;
	import org.testng.annotations.Test;

	import io.restassured.http.ContentType;

	public class Public_login {

//Login to the application
		@Test
		public void publicLogin() {

			// Map<String, Object> map = new HashMap<String, Object>();			
			// map.put("name", "Divya"); map.put("job", "Qa"); System.out.println(map);
			

			JSONObject request =new JSONObject();

			request.put("email", "croissance.abhik@gmail.com ");
			request.put("password", "123456");

			System.out.println(request);
			System.out.println(request.toJSONString());

			given()
			.header("Content-type", "Application/json")
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString())
			
			.when()  
			
			.post("https://dev.teknotrait.com/public/api/login")
			
			.then().statusCode(200).log().all();
			

		}

	}



