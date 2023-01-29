package modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.xml.internal.Parser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

//import debugging_tests.Debugging_tests;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo_models.PostOTPData;
import pojo_models.ResponseData;
import utilities.RestAssuredEngine;

public class VerifyOTPHelper {
    private int i=0;

    private static ArrayList<PostOTPData> arr=new ArrayList<PostOTPData>(); ;//= new ArrayList<PostOTPData>();
    Map<String,ArrayList<PostOTPData>> map=new LinkedHashMap<String, ArrayList<PostOTPData>>(); //= new LinkedHashMap<String, ArrayList<PostOTPData>>();

    public ResponseOptions<Response> verifyOTP(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,JSONObject body){
        RestAssuredEngine restAssuredEngine = new RestAssuredEngine();
        return restAssuredEngine.executeWithPathParamsAndBody(methodName, serviceEndpoint, body,null);

    }

    public void putPOSTData(String response) throws FileNotFoundException, IOException, ParseException  {
        ObjectMapper obj=new ObjectMapper();
        JSONParser parser=new JSONParser();
        System.out.println(response);
        JSONObject json_response=(JSONObject) parser.parse(response);
        //System.out.println("Response "+response);
        //System.out.println(response.length());
        if(json_response.get("status").toString().equalsIgnoreCase("Success")) {
            ResponseData data=obj.readValue(response, ResponseData.class);
            if(data.content != null) {
                System.out.println(data.getContent().get("otp").toString());
                //arr.add(new PostOTPData(data.getContent().get("otp").toString()));
                arr.add(new PostOTPData(data.getContent().get("otp").toString()));


            }
        }
        else {
            arr.add(new PostOTPData("xyz"));
            System.out.println("INSIDE INVALUD OTP "+arr);
        }
        this.saveFile();


    }


    public void saveFile() throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper obj=new ObjectMapper();
        System.out.println(arr);
        map.put("OTP", arr);
        //obj.writeValue(new File("/Users/shreyas.bhide/Documents/APIDemo/src/test/resources/jsonFile.json"), map);
        obj.writeValue(new File(System.getProperty("user.dir")+"/src/test/resources/jsonFile.json"), map);
    }


    public JSONObject getPOSTData() throws FileNotFoundException, IOException, ParseException {
        JSONObject otp_data=new JSONObject();
        JSONParser parser = new JSONParser();

        //JSONObject jo=(JSONObject) parser.parse(new FileReader("/Users/shreyas.bhide/Documents/APIDemo/src/test/resources/jsonFile.json"));
        JSONObject jo=(JSONObject) parser.parse(new FileReader(System.getProperty("user.dir")+"/src/test/resources/jsonFile.json"));
        JSONArray otp_array=(JSONArray)jo.get("OTP");
        //System.out.println("otp-array -->" +otp_array);
        while(i < otp_array.size()) {
            otp_data=(JSONObject) otp_array.get(i);
            break;
        }

        i++;
        return otp_data;
    }






}