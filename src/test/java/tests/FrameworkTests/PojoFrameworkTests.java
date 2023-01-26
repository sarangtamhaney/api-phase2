package tests.FrameworkTests;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import modules.FriendsHelper;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo_models.FriendWithLombok;
import utilities.ExcelUtility;

import java.util.Map;

public class PojoFrameworkTests {
    FriendsHelper friendshelper = new FriendsHelper();
    SoftAssert softAssert = new SoftAssert();

    @Test(dataProvider = "getApiEndPointData", dataProviderClass = ExcelUtility.class, testName = "addFriends_FriendsTest")
    public void addFriends_FriendsTest(String methodName, String serviceEndpoint, Map<String, String> headerMap, Map<String, String> queryParamMap, Map<String, Object> pathParamMap, int statusCode, String responseMessage) {
        Response response = (Response) friendshelper.addFriends(methodName, serviceEndpoint, friendshelper.getFriendsPayload());
        softAssert.assertEquals(response.getStatusCode(), statusCode, "Status code failed");
        softAssert.assertEquals(response.as(FriendWithLombok.class).getId(), friendshelper.getFriendsPayload().getId(), "Incorrect friend Id");
        softAssert.assertAll();
    }

    @Test(dataProvider = "getApiEndPointData", dataProviderClass = ExcelUtility.class, testName = "verifyNegative_NegativeTest")
    public void verifyNegative_NegativeTest(String methodName, String serviceEndpoint, Map<String, String> headerMap, Map<String, String> queryParamMap, Map<String, Object> pathParamMap, int statusCode, String responseMessage, String requestBody) {
        Response response = (Response) friendshelper.addFriends(methodName, serviceEndpoint, requestBody);
        softAssert.assertEquals(response.getStatusCode(), statusCode, "Status code failed");
        softAssert.assertAll();
    }

}
