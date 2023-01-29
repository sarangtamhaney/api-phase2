package modules;

import java.util.Map;

import org.json.simple.JSONObject;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredEngine;

public class ResetPasswordhelper {

    public ResponseOptions<Response> resetPassword(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,JSONObject body) {
        RestAssuredEngine restAssuredEngine = new RestAssuredEngine();
        return restAssuredEngine.executeWithPathParamsAndBody(methodName, serviceEndpoint, body,null);
    }


}