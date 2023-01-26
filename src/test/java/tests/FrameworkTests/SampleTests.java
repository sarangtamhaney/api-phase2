package tests.FrameworkTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import modules.ReportsHelper;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo_models.CreateReportResponse;
import tests.BaseTest;
import utilities.ExcelUtility;

import java.util.Map;

public class SampleTests extends BaseTest {

    private ReportsHelper reportsHelper = new ReportsHelper();
    private SoftAssert softAssert = new SoftAssert();

    @Test(dataProvider = "getApiEndPointData", dataProviderClass = ExcelUtility.class, testName = "Verify_ReportsTest")
    public void Verify_ReportsTest(String methodName, String serviceEndpoint, Map<String, String> headerMap, Map<String, String> queryParamMap, Map<String, Object> pathParamMap, int statusCode, String responseMessage, String requestBody) throws JsonProcessingException {
        ResponseOptions<Response> response = reportsHelper.createReport(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, getRestEngine(), requestBody);
        CreateReportResponse responseBody = response.body().as(CreateReportResponse.class);
        softAssert.assertEquals(response.getStatusCode(), statusCode, "Report added successfully");

    }
}


