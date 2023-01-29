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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import pojo_models.Add_ReportSuccessPOJO;
import utilities.RestAssuredEngine;

public class Add_Report_helper {
    private static  int i=0;


    static ArrayList<Add_ReportSuccessPOJO> arr=new ArrayList<Add_ReportSuccessPOJO>();
    static Map<String,ArrayList<Add_ReportSuccessPOJO>> map = new LinkedHashMap<String,ArrayList<Add_ReportSuccessPOJO>>();


    public ResponseOptions<Response> add_report(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,JSONObject body) {
        RestAssuredEngine restAssuredEngine = new RestAssuredEngine();

        return restAssuredEngine.executeWithPathParamsAndBody(methodName, serviceEndpoint, body,null);
    }

    public static void mapSuccessResponse_to_POJO(String response) throws ParseException, IOException {
        ObjectMapper mapper=new ObjectMapper();
        JSONParser parser=new JSONParser();
        JSONObject response_obj=(JSONObject) parser.parse(response);

        if(response_obj.get("status").toString().equalsIgnoreCase("Success")){
            Add_ReportSuccessPOJO obj=mapper.readValue(response.toString(), Add_ReportSuccessPOJO.class);

            obj.setStatus(response_obj.get("status").toString());
            obj.setMessage(response_obj.get("message").toString());
            obj.setContent(response_obj.get("content").toString());

            arr.add(new Add_ReportSuccessPOJO(response_obj.get("status").toString(),response_obj.get("message").toString(),response_obj.get("content").toString()));
            //System.out.println(report_content_arr);


        }
        else {
            arr.add(new Add_ReportSuccessPOJO(response_obj.get("status").toString(),"Error!","Invalid Data!"));
        }
        saveFile(arr);



    }

    public static  void saveFile(ArrayList<Add_ReportSuccessPOJO> arr) throws JsonGenerationException, JsonMappingException, IOException {
        map.put("AddReportSuccessPOJO", arr);
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(new File(System.getProperty("user.dir")+"/src/test/resources/addReportSucessPOJO.json"), map);
    }


    public static JSONObject get_Report_Content() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser=new JSONParser();
        JSONObject report_content=new JSONObject();
        JSONObject jo=(JSONObject) parser.parse(new FileReader(System.getProperty("user.dir")+"/src/test/resources/addReportSucessPOJO.json"));
        JSONArray array=(JSONArray) jo.get("AddReportSuccessPOJO");
        //System.out.println(array.get(0));

        while(i < array.size()) {
            JSONObject ob=(JSONObject) array.get(i);
            report_content.put("content", ob.get("content"));
            break;
        }
        i++;
        return report_content;
    }

}