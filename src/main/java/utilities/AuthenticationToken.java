package utilities;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AuthenticationToken {

	public String getAuthenticationToken() {
		File file = new File("./src/test/resources/auth.json");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);
        String token = request.get("http://Fmc-env.eba-5akrwvvr.us-east-1.elasticbeanstalk.com/fmc/token").getBody().asString();
        System.out.println("token"+token);
        return token;
	}
}
