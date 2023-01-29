package modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo_models.PostOTPData;
import pojo_models.ResponseData;
import utilities.RestAssuredEngine;

public class loginHelper {
    private static int i=0;
    static ArrayList<JSONObject>arr=new ArrayList<JSONObject>();
    static LinkedHashMap<String,ArrayList<JSONObject>> map=new LinkedHashMap<String,ArrayList<JSONObject>>();

    public ResponseOptions<Response> login(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,JSONObject body){
        RestAssuredEngine restAssuredEngine = new RestAssuredEngine();
        return restAssuredEngine.executeWithPathParamsAndBody(methodName, serviceEndpoint, body,null);
    }

    public static void store_userId(String response) throws ParseException, IOException {

        System.out.println(response);

        JSONParser parser=new JSONParser();
        JSONObject json_response=(JSONObject) parser.parse(response);
        System.out.println("userID "+response);
        ObjectMapper mapper=new ObjectMapper();
        ResponseData obj=mapper.readValue(response, ResponseData.class);
        if(json_response.get("status").toString().equalsIgnoreCase("Success")) {

            obj.setStatus(json_response.get("status").toString());
            obj.setMessage(json_response.get("message").toString());
            obj.setContent((JSONObject)json_response.get("content"));
            JSONObject userid=new JSONObject();
            userid.put("userid", obj.getContent().get("userId").toString());
            arr.add(userid);

        }
        else {
            obj.setStatus(json_response.get("status").toString());
            JSONObject errorMessage=new JSONObject();
            errorMessage.put("message", "Check your Credentials!");
            obj.setMessage(errorMessage.toString());
            //JSONObject errorUSerid=new JSONObject();
            //errorUSerid.put("userId", "xyz");
            //obj.setContent(errorUSerid);
            JSONObject userid=new JSONObject();
            userid.put("userid", "xyz");
            arr.add(userid);

        }
        saveData();

    }
    public static  void saveData() throws JsonGenerationException, JsonMappingException, IOException {
        map.put("userId", arr);
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(new File(System.getProperty("user.dir")+"/src/test/resources/userIdJsonFile.json"), map);

    }

    public  static JSONObject getUserId() throws FileNotFoundException, IOException, ParseException {
        JSONObject UserId=new JSONObject();
        JSONParser parser = new JSONParser();
        JSONObject jo=(JSONObject) parser.parse(new FileReader(System.getProperty("user.dir")+"/src/test/resources/userIdJsonFile.json"));
        JSONArray userId=(JSONArray)jo.get("userId");
        while(i < userId.size()) {
            UserId=(JSONObject) userId.get(i);
            break;
        }
        i++;
        System.out.println(UserId);
        return UserId;
    }

}