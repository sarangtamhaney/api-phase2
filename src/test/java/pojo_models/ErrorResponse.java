package pojo_models;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.simple.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    String status;
    LinkedHashMap<String,ArrayList<JSONObject>> errors=new LinkedHashMap<String,ArrayList<JSONObject>>();

}