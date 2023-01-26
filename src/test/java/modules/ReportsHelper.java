package modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo_models.ReportsRequest;
import utilities.FakerUtility;
import utilities.RestAssuredEngine;

import java.util.Map;

public class ReportsHelper {

    private final ObjectMapper mapper = new ObjectMapper();
    private FakerUtility fakerUtility = new FakerUtility();

    public ResponseOptions<Response> createReport(String methodName, String serviceEndpoint, Map<String, String> headerMap, Map<String, String> queryParamMap, Map<String, Object> pathParamMap, RestAssuredEngine restAssuredEngine, String requestBody) throws JsonProcessingException {
        //RestAssuredEngine restAssuredEngine = new RestAssuredEngine("token");
        requestBody = modifyRequestBody(requestBody);
        return restAssuredEngine.executeMethod(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, requestBody);
    }

    public String modifyRequestBody(String requestBody) throws JsonProcessingException {
        ReportsRequest reportsRequest = mapper.readValue(requestBody, ReportsRequest.class);

        reportsRequest.getReporterDetails().setRequestId(fakerUtility.getId());
        return mapper.writeValueAsString(reportsRequest);
    }
}
