package modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo_models.FriendWithLombok;
import utilities.RestAssuredEngine;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

public class FriendsHelper {
	
	public ResponseOptions<Response> addFriends(String methodName, String serviceEndpoint, Object body){
		RestAssuredEngine restAssuredEngine = new RestAssuredEngine("token");
		return restAssuredEngine.executeWithPathParamsAndBody(methodName, serviceEndpoint, body);
	}

	public FriendWithLombok getFriendsPayload() {
		try {
			// create object mapper instance
			ObjectMapper mapper = new ObjectMapper();
			// convert a JSON string to a Book object
			FriendWithLombok friend = mapper.readValue(Paths.get("src/test/resources/friend.json").toFile(), FriendWithLombok.class);
			friend.setId("Ani");
			return friend;
		} catch (Exception e) {
		}
		return null;
	}
}
