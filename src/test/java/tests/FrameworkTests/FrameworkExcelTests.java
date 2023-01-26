//	package tests.FrameworkTests;
//import java.util.Map;
//
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//
//import io.restassured.response.Response;
//import io.restassured.response.ResponseOptions;
//import modules.CountryHelper;
//import utilities.ExcelUtility;
//
//public class FrameworkExcelTests {
//	CountryHelper countryhelper = new CountryHelper();
//	SoftAssert softAssert = new SoftAssert();
//
//	@Test(dataProvider = "getApiEndPointData", dataProviderClass = ExcelUtility.class,testName="verifyPlaceNameFramework_CountryTest")
//    public void verifyPlaceNameFramework_CountryTest(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage) {
//		ResponseOptions<Response> response = a.getPlaceNameDetails(methodName,serviceEndpoint,headerMap,queryParamMap,pathParamMap);
//
//
//		softAssert.assertEquals(response.getStatusCode(), 200,"Status code failed");
//		softAssert.assertEquals(response.body().jsonPath().getList("places.'place name'").get(0), responseMessage,"Place name not matched");
//		softAssert.assertAll();
//    }
//}
