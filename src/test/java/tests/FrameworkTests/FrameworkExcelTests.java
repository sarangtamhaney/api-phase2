package tests.FrameworkTests;




import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
import java.util.Map;
//import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
//import org.testng.annotations.Factory;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.JsonNode;

//import debugging_tests.Debugging_tests;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import modules.Add_Report_helper;

import modules.CountryHelper;
import modules.DeleteReportHelper;
import modules.DownloadFilehelper;
import modules.EmailSignupAutomationHelper;
import modules.FileUpload;

import modules.ResetPasswordhelper;
import modules.VerifyOTPHelper;
import modules.deleteUserHelper;
import modules.getReportHelper;
import modules.loginHelper;

import tests.BaseTest;

import utilities.ExcelUtility;
import utilities.GenerateDataFaker;


public class FrameworkExcelTests extends BaseTest {

    public static Boolean isPasswordReset=false;

    CountryHelper countryhelper = new CountryHelper();
    DownloadFilehelper download_file_helper=new DownloadFilehelper();
    FileUpload file_upload_helper=new FileUpload();
    VerifyOTPHelper otphelper=new VerifyOTPHelper();
    loginHelper login_help=new loginHelper();
    getReportHelper getReporthelp=new getReportHelper();
    ResetPasswordhelper reset_passhelper=new ResetPasswordhelper();
    DeleteReportHelper delete_report_help=new DeleteReportHelper();

    deleteUserHelper delele_helper=new deleteUserHelper();
    GenerateDataFaker generate_data=new GenerateDataFaker();
    Add_Report_helper create_report_helper= new Add_Report_helper();
//    Debugging_tests test=new Debugging_tests();
    ExcelUtility excel_util=new ExcelUtility();
    EmailSignupAutomationHelper emailHelper=new EmailSignupAutomationHelper();
    SoftAssert softAssert = new SoftAssert();
    ExcelUtility excelutit=new ExcelUtility();


    @Test(dataProvider="getAPI_POSTdata",dataProviderClass=ExcelUtility.class,testName="verifyEmailSignup_Automation",priority=1)
    public void verifyEmailSignup_Automation(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,String email) throws IOException, ParseException {
        JSONObject body=new JSONObject();
        body.put("email_id", email);
        ResponseOptions<Response> response=emailHelper.createSignupEmail(methodName,serviceEndpoint,headerMap,queryParamMap,pathParamMap,statusCode,responseMessage,body);
        System.out.println(response.statusCode());
        otphelper.putPOSTData(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode()==201, true);
    }


    @Test(dataProvider="getAPI_POSTdata",dataProviderClass=ExcelUtility.class,testName="verifyOTP_verifyOTP",priority=2)
    public void verifyOTP_verifyOTP(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,String email,String full_name,String phone_number,String password) throws ParseException, FileNotFoundException, IOException {
        JSONObject body=new JSONObject();
        body.put("email_id", email);
        body.put("full_name",full_name);
        body.put("phone_number", phone_number);
        body.put("password", password);
        JSONObject otp=otphelper.getPOSTData();
        //System.out.println("obtained OTP "+otp.get("otp").toString());
        body.put("otp", otp.get("otp").toString());
        ResponseOptions<Response> verify_otp_response=otphelper.verifyOTP(methodName,serviceEndpoint,headerMap,queryParamMap,pathParamMap,statusCode,responseMessage,body);
        //String otp1=otphelper.readOTP();
        //System.out.println("newOTP "+otp1);
        Assert.assertEquals(verify_otp_response.getStatusCode()==200,true);

    }

    @Test(dataProvider="getAPI_POSTdata",dataProviderClass=ExcelUtility.class,testName="login_Testlogin",priority=3)
    public void login_Testlogin(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,String email,String password) throws ParseException, IOException  {
        JSONObject body=new JSONObject();
        body.put("email_id", email);
        body.put("password", password);

        //System.out.println(isPasswordReset);
        System.out.println("body --> "+body);
        ResponseOptions<Response> login_response=login_help.login(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, statusCode, responseMessage, body);
        loginHelper.store_userId(login_response.getBody().asString());
        Assert.assertEquals(login_response.getStatusCode()==200, true);
    }

    @Test(dataProvider="getAPI_POSTdata",dataProviderClass=ExcelUtility.class,testName="resetPassword_TestresetPassword",priority=4)
    public void resetPassword_TestresetPassword(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,String email,String reset_password) {
        JSONObject body=new JSONObject();
        body.put("email_id", email);
        body.put("password", reset_password);
        ResponseOptions<Response> reset_password_response=reset_passhelper.resetPassword(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, statusCode, responseMessage, body);
        Assert.assertEquals(reset_password_response.getStatusCode()==200,true);
        isPasswordReset=true;
        System.out.println(isPasswordReset);
    }

    @Test(dataProvider="getAPI_POSTdata",dataProviderClass=ExcelUtility.class,testName="resetPassword_TestresetPassword",priority=11)
    public void deleteUser_TestdeleteUser(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,String email){
        JSONObject body=new JSONObject();
        body.put("email_id", email);
        ResponseOptions<Response> delete_user_response=delele_helper.deleteUser(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, statusCode, responseMessage, body);
        Assert.assertEquals(delete_user_response.getStatusCode()==200,true);
    }



    @Test(dataProvider="getAPI_POSTdata" ,dataProviderClass=ExcelUtility.class,testName="addReport_TestaddReport",/*dependsOnMethods="login_Testlogin", alwaysRun=true,*/priority=6)
    public void addReport_TestaddReport(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage,
                                        String request_id,String report_date,String reporter_fullname,String reporter_age,
                                        String reporter_gender,String reporter_relation,String parenting_type,
                                        String contact_address_type,String contact_address_line_1,String contact_address_line_2,
                                        String pincode,String country,String primary_countryCode,String primary_contact_number,
                                        String secondary_countryCode,String secondary_contact_number,String communication_language,
                                        String status,String child_fullname,String child_age,String gender,String height,String weight,
                                        String complexion,String clothing,String birth_signs,String other_details,
                                        String image_file_key,String nickname,String incident_date,String incident_brief,
                                        String location,String landmark,String nearbyPoliceStation,String nearbyNGO,
                                        Boolean allow_Police_connectNGO,Boolean self_verification,Boolean community_terms) throws FileNotFoundException, IOException, ParseException {

        JSONObject root=new JSONObject();
        JSONObject reporter_details=new JSONObject();
        JSONObject rep_details=new JSONObject();
        boolean isNumericRequest_id= request_id.chars().allMatch( Character::isDigit );
        if(isNumericRequest_id) {
            rep_details.put("request_id",Integer.parseInt(request_id));
        }
        else {
            rep_details.put("request_id",request_id);
        }

        JSONObject userId=loginHelper.getUserId();
        //System.out.println(userId.get("userid"));
        String userId_string=userId.get("userid").toString();
        boolean isNumericUserId = userId_string.chars().allMatch( Character::isDigit );
        //System.out.println(isNumericUserId);
        if(isNumericUserId) {
            rep_details.put("user_id",Integer.parseInt(userId_string));
        }
        else {
            rep_details.put("user_id",userId_string);
        }
        //System.out.println("REP_DETAILS --> "+rep_details);

        rep_details.put("report_date",report_date);
        rep_details.put("reporter_fullname",reporter_fullname);
        boolean isNumericAge = reporter_age.chars().allMatch( Character::isDigit );
        if(isNumericAge) {
            rep_details.put("reporter_age",Integer.parseInt(reporter_age));
        }
        else {
            rep_details.put("reporter_age",reporter_age);
        }

        //rep_details.put("reporter_age",Integer.parseInt(reporter_age));
        rep_details.put("reporter_gender",reporter_gender);
        rep_details.put("reporter_relation",reporter_relation);
        rep_details.put("parenting_type",parenting_type);
        rep_details.put("contact_address_type",contact_address_type);
        rep_details.put("contact_address_line_1",contact_address_line_1);
        rep_details.put("contact_address_line_2",contact_address_line_2);
        rep_details.put("pincode",pincode);
        rep_details.put("country",country);
        rep_details.put("primary_country_code",primary_countryCode);
        rep_details.put("primary_contact_number",primary_contact_number);
        rep_details.put("secondary_country_code",secondary_countryCode);
        rep_details.put("secondary_contact_number",secondary_contact_number);
        rep_details.put("communication_language",communication_language);
        rep_details.put("status",status);

        //reporter_details.put("reporter_details", rep_details)
        root.put("reporter_details", rep_details);

        JSONObject child_details =new JSONObject();
        JSONObject child_det=new JSONObject();


        child_det.put("fullname", child_fullname);
        boolean isNumericChild_Age = child_age.chars().allMatch( Character::isDigit);
        if(isNumericChild_Age) {
            child_det.put("age",Integer.parseInt(child_age));
        }
        else {
            child_det.put("age",child_age);
        }

        child_det.put("gender",gender);
        child_det.put("height",height);

        child_det.put("weight",weight);
        child_det.put("complexion",complexion);
        child_det.put("clothing",clothing);
        child_det.put("birth_signs",birth_signs);
        child_det.put("other_details",other_details);
        child_det.put("image_file_key",null);
        child_det.put("nickname",nickname);

        root.put("child_details",child_det);

        //child_details.put("child_details",child_det);

        JSONObject incident_details=new JSONObject();
        JSONObject incident_det=new JSONObject();

        incident_det.put("incident_date", incident_date);
        incident_det.put("incident_brief", incident_brief);
        incident_det.put("location", location);
        incident_det.put("landmark_signs", landmark);
        incident_det.put("nearby_police_station", nearbyPoliceStation);
        incident_det.put("nearby_NGO", nearbyNGO);
        incident_det.put("allow_connect_police_NGO", allow_Police_connectNGO);
        incident_det.put("self_verification", self_verification);
        incident_det.put("community_terms", community_terms);

        //incident_details.put("incident_details", incident_det);
        root.put("incident_details", incident_det);

        //System.out.println("ROOT JSONOBJECT --> "+root);



        ResponseOptions<Response> add_report_response=create_report_helper.add_report(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, statusCode, responseMessage, root);
        System.out.println(add_report_response.getStatusCode());

        Add_Report_helper.mapSuccessResponse_to_POJO(add_report_response.getBody().asString());
        Assert.assertEquals(add_report_response.getStatusCode()==200,true);
    }

    @Test(dataProvider="getAPI_POSTdata" ,dataProviderClass=ExcelUtility.class,testName="getReport_TestgetReport",priority=7/*,dependsOnMethods="addReport_TestaddReport" ,alwaysRun=true*/)
    public void getReport_TestgetReport(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage) throws FileNotFoundException, IOException, ParseException {
        JSONObject userid=login_help.getUserId();
        String userID_string=userid.get("userid").toString();
        pathParamMap.put("userId", userID_string);
        JSONObject body=new JSONObject();
        ResponseOptions<Response> getReport_response=getReporthelp.get_report(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, statusCode, responseMessage,body);
        //System.out.println(getReport_response.getStatusCode());
        Assert.assertEquals(getReport_response.getStatusCode()==200,true);
    }

    @Test(dataProvider="getAPI_POSTdata" ,dataProviderClass=ExcelUtility.class,testName="deleteReport_TestdeleteReport"/*dependsOnMethods="addReport_TestaddReport"*/, alwaysRun=true,priority=8)
    public void deleteReport_TestdeleteReport(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage) throws FileNotFoundException, IOException, ParseException {
        JSONObject userid_obj=loginHelper.getUserId();
        JSONObject report_content=Add_Report_helper.get_Report_Content();
        JSONObject body=new JSONObject();
        pathParamMap.put("content", report_content.get("content").toString());
        pathParamMap.put("userId", userid_obj.get("userid").toString());
        ResponseOptions<Response> deleteReport_response=delete_report_help.deleteReport(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, statusCode, responseMessage, body);
        Assert.assertEquals(deleteReport_response.getStatusCode()==200, true);
    }

    @Test(dataProvider="getAPI_POSTdata" ,dataProviderClass=ExcelUtility.class,testName="uploadFile_TestuploadFile",priority=9)
    public void uploadFile_TestuploadFile(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage) throws ParseException, IOException{
        File returnedFile=file_upload_helper.returnFile();
        System.out.println(returnedFile);
        JSONObject body=new JSONObject();

        body.put("file", returnedFile);
        ResponseOptions<Response> uploadFile_response=file_upload_helper.uploadFile(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, statusCode, responseMessage, body);
        //System.out.println(uploadFile_response.getStatusCode());
        file_upload_helper.storeImage_File_key(uploadFile_response);
        Assert.assertEquals(uploadFile_response.getStatusCode()==201,true);

    }

    @Test(dataProvider="getAPI_POSTdata" ,dataProviderClass=ExcelUtility.class,testName="downloadFile_TestdownloadFile",priority=10)
    public void downloadFile_TestdownloadFile(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage) throws FileNotFoundException, IOException, ParseException {
        JSONObject file_key=file_upload_helper.readFile();
        if(file_key.get("image_file_key").toString() != null) {
            pathParamMap.put("image_file_key", file_key.get("image_file_key").toString());
        }
        JSONObject body=new JSONObject();
        ResponseOptions<Response> download_file_response=download_file_helper.downloadFile(methodName, serviceEndpoint, headerMap, queryParamMap, pathParamMap, statusCode, responseMessage,body);
        Assert.assertEquals(download_file_response.getStatusCode()==200, true);
    }





}