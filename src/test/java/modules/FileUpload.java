package modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import pojo_models.ErrorResponse;
import pojo_models.ImageFileKey;
import utilities.RestAssuredEngine;

public class FileUpload {
    private int i=0;

    File folder=new File(System.getProperty("user.dir")+"/src/test/resources/file-upload");
    File[] files=folder.listFiles();
    File returnFile;
    ArrayList<JSONObject> arr=new ArrayList<JSONObject>();
    LinkedHashMap<String,ArrayList<JSONObject>> map=new LinkedHashMap<String,ArrayList<JSONObject>>();





    public ResponseOptions<Response> uploadFile(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,JSONObject body) {
        RestAssuredEngine restAssuredEngine = new RestAssuredEngine();
        return restAssuredEngine.executeWithPathParamsAndBody(methodName, serviceEndpoint, body,null);
    }



    public void storeImage_File_key(ResponseOptions<Response>response) throws ParseException, IOException {
        //System.out.println(response);
        JSONObject ob=new JSONObject();
        JSONObject json_response=new JSONObject();
        ObjectMapper mapper=new ObjectMapper();
        JSONParser parser=new JSONParser();
        if(response.getStatusCode() != 413) {
            json_response=(JSONObject) parser.parse(response.getBody().asString());
        }

        if(response.getStatusCode()==201) {
            ImageFileKey obj=mapper.readValue(response.getBody().asString(), ImageFileKey.class);
            obj.setImage_file_key(json_response.get("image_file_key").toString());
            ob.put("image_file_key", json_response.get("image_file_key").toString());
            arr.add(ob);
        }
        else if(response.getStatusCode() != 413) {
            ErrorResponse obj=mapper.readValue(response.getBody().asString(),ErrorResponse.class);
            ob.put("image_file_key", "INVALID IMAGE KEY!");
            ob.put("status", "Error!");
            ob.put("message", json_response.get("message").toString());
            arr.add(ob);
        }
        else {
            ob.put("image_file_key", response.getBody().asString());
            arr.add(ob);
        }
        this.saveFile(arr);

    }

    public void saveFile(ArrayList<JSONObject> arr) throws JsonGenerationException, JsonMappingException, IOException {
        map.put("ImageFileKey", arr);
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(new FileWriter(System.getProperty("user.dir")+"/src/test/resources/ImageFileKey.json"), map);
    }

    public File returnFile() {
        while(i < files.length) {
            returnFile=files[i];
            break;
        }
        i++;
        return returnFile;
    }

    public JSONObject readFile() throws FileNotFoundException, IOException, ParseException {
        ObjectMapper mapper=new ObjectMapper();
        JSONObject file_key=new JSONObject();
        JSONParser parser=new JSONParser();
        JSONObject image_file_key=(JSONObject) parser.parse(new FileReader(System.getProperty("user.dir")+"/src/test/resources/ImageFileKey.json"));
        JSONArray arr =(JSONArray)image_file_key.get("ImageFileKey");
        while(i < arr.size()) {
            file_key=(JSONObject) arr.get(i);
            break;
        }
        i++;
        return file_key;
    }

}