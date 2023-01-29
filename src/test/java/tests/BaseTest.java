package tests;

import java.util.Map;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.AuthenticationToken;
import utilities.ExcelUtility;
import utilities.RestAssuredEngine;

public class BaseTest extends AuthenticationToken {

    public ResponseOptions<Response> response;
    RestAssuredEngine restAssuredEngine;
    protected String secureToken = "";
    private RequestSpecBuilder builder = new RequestSpecBuilder();

    // AUTHENICATION TOKEN CHECK FOR TESTCASE NAME CELL IN
    //EXCEL SHEET NOT MATCHING.
    //ERROR


    @Test(dataProvider="getApiEndPointData",dataProviderClass=ExcelUtility.class,testName="getToken_TestgetToken" )
    public void getToken_TestgetToken(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage){
        String tokenstr = getAuthenticationToken();
        builder.addHeader("Authorization","Bearer "+tokenstr);



        //builder.addHeader("Authorization", "bWFoZXNoLmJoYW5kaWdhcmU6bWFoZXNoMTIz");
        builder.addHeader("accept", "application/json");
        restAssuredEngine = new RestAssuredEngine();
    }


}