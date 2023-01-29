package utilities;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.omg.CORBA.Request;

public class RestAssuredEngine extends AuthenticationToken {

    private RequestSpecBuilder builder = new RequestSpecBuilder();

    public RestAssuredEngine() {

    }

    /**
     * RestAssuredEngine constructor to pass the initial settings for the the following method
     * @param token
     */
    public RestAssuredEngine(String token) {
        builder.addHeader("Authorization", "Bearer "+token);
        builder.addHeader("accept", "application/json");
    }

    public void getDataFromResponse(Response response) {
        System.out.println(response.getBody());
    }
    public RequestSpecification printRequest(RequestSpecification request) {
        return request.log().all();
    }

    public String printResponse(Response response) {
        return response.body().asString();
    }



    /**
     * Execute API Methods GET/POST/DELETE
     * @return ResponseOptions<Response>
     */
    private ResponseOptions<Response> executeAPIMethods(String methodName,String serviceEndpoint,Map<String,Object> pathParamMap) {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        //Setting up content type
        request.contentType(ContentType.JSON);
//        request.contentType(ContentType.ANY);
        request.spec(requestSpecification);
        if(methodName.equalsIgnoreCase(APIConstant.ApiMethods.POST)) {



            return request.post(serviceEndpoint);
        }


        else if(methodName.equalsIgnoreCase(APIConstant.ApiMethods.DELETE)) {
            if(! pathParamMap.isEmpty()) {
                serviceEndpoint=serviceEndpoint.replace("{{content}}",pathParamMap.get("content").toString());
                serviceEndpoint=serviceEndpoint.replace("{{userId}}", pathParamMap.get("userId").toString());
            }
            return request.delete(serviceEndpoint);
        }

        else if(methodName.equalsIgnoreCase(APIConstant.ApiMethods.GET)) {
            if(pathParamMap.get("userId") != null) {
                serviceEndpoint=serviceEndpoint+pathParamMap.get("userId");
            }
            if(pathParamMap.get("image_file_key") != null) {
                serviceEndpoint=serviceEndpoint.replace("{{file_key}}", pathParamMap.get("image_file_key").toString());
            }
            //System.out.println(serviceEndpoint);
            //this.printRequest(request);
            return request.get(serviceEndpoint);
        }


        else if(methodName.equalsIgnoreCase(APIConstant.ApiMethods.PUT)) {
            //printRequest(request);  // error checking method to check request
            //System.out.println("-----------------");
            return request.put(serviceEndpoint);
            // response=req	uest
        }
        return null;
    }

    /**
     * ExecuteWithPathParams
     * @return
     */
    public ResponseOptions<Response> executeMethod(String methodName, String serviceEndpoint, Map<String, String> headerMap,Map<String, String> queryPathMap, Map<String, Object> pathParamMap,String email_ID) {

        //System.out.println(",token);
        //headerMap.put("Authorization",token);
        builder.addHeaders(headerMap);
        //builder.addH
        //System.out.println(headerMap);
    	/*
        if(!headerMap.isEmpty()) {
        	System.out.println("YES");
        	builder.addHeaders(headerMap);
        }*/


        if(!queryPathMap.isEmpty())
            builder.addQueryParams(queryPathMap);
        if(!pathParamMap.isEmpty())
            builder.addPathParams(pathParamMap);
        //System.out.println("ExecuteMethod fine!!");
        //Set Body for POST Method
        // if("POST".equals(methodName))
        //builder.setBody(object, mapper)
        return executeAPIMethods(methodName,serviceEndpoint,null);
    }
    /**
     * ExecuteWithPathParams
     * @return
     */
    public ResponseOptions<Response> execute(String methodName, String serviceEndpoint) {
        return executeAPIMethods(methodName,serviceEndpoint,null);
    }
//
//
//    /**
//     * Executing API with query params being passed as the input of it
//     * @param queryPath
//     * @return Reponse
//     */
//    public ResponseOptions<Response> executeWithQueryParams(Map<String, String> queryPath) {
//        builder.addQueryParams(queryPath);
//        return executeAPIMethods();
//    }

    /**
     * ExecuteWithPathParams
     * @param pathParams
     * @return
     */
//    public ResponseOptions<Response> executeWithPathParams(Map<String, String> pathParams) {
//        builder.addPathParams(pathParams);
//        return executeAPIMethods();
//    }



    /**
     * ExecuteWithPathParamsAndBody
     //   * @param pathParams
     * @param body
     * @return
     * @throws ParseException
     * @throws JSONException
     */
    public ResponseOptions<Response> executeWithPathParamsAndBody(String methodName, String serviceEndpoint, JSONObject body,Map<String,Object> pathParamMap) {
        //System.out.println(body != );
        String token=getAuthenticationToken();
        String authorization="Bearer " + token;

        builder.addHeader("Authorization",authorization);
        //builder.addHeader("Content-Type", "ContentType.JSON");
        //builder.addHeader("Accept", "ContentType.JSON");


        if (body !=null)
            builder.setBody(body);
        if(body.get("file") != null) {
            builder.addHeader("Content-Type","multipart/form-data");
            builder.addMultiPart("file", (File) body.get("file"));
        }
        if(pathParamMap != null) {
            return executeAPIMethods(methodName,serviceEndpoint,pathParamMap);
        }
//        builder.addPathParams(pathParams);
        return executeAPIMethods(methodName,serviceEndpoint,null);
    }



}