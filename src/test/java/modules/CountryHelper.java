package modules;

import java.util.Map;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredEngine;

public class CountryHelper {
	
	public ResponseOptions<Response> getPlaceNameDetails(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap, Map<String, Object> pathParamMap, RestAssuredEngine restAssuredEngine, String requestBody){
		//RestAssuredEngine restAssuredEngine = new RestAssuredEngine("token");
		return restAssuredEngine.executeMethod(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, requestBody);
	}
	
}
