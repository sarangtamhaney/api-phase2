package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;



public class ExcelUtility {

    static int cellId=0;
    static XSSFWorkbook workbook = new XSSFWorkbook();
    static XSSFSheet excel_spreadsheet = workbook.createSheet("proxy");
    static int rowId=0;

    static DataFormatter formatter = new DataFormatter();
    IteraterOperations iterater_ops=new IteraterOperations();


    @DataProvider(name = "getApiEndPointData")
    public Iterator<Object[]> getApiTestData(Method method) throws IOException {
        return excelAPIDataReader(0, "./src/test/resources/APIDetailsSheet.xlsx",method.getName());
    }

    @DataProvider(name = "getApiEndPointData2")
    public Iterator<Object[]> getApiTestData2(Method method) throws IOException {
        return excelAPIDataReader2(0, "./src/test/resources/APIDetailsSheet.xlsx",method.getName());
    }
    @DataProvider(name="getAPIEndPointData3")
    public Iterator<Object[]> getAPItestData3(Method method) throws IOException{
        //return excelPOST_ReqDataReader("./src/test/resources/POST_REQUEST_BODY.xlsx",method.getName());
        return excelPOST_ReqDataReader("./src/test/resources/demoExcelFile.xlsx",method.getName());
    }




    @DataProvider(name="getAPI_POSTdata")
    public Iterator<Object[]> getAPI_POSTdata(Method method) throws IOException{
        Iterator<Object[]> itr1=getApiTestData(method);
        Iterator<Object[]> itr2=getAPItestData3(method);
        return this.mergeIterators(itr1, itr2);
    }

    public Iterator<Object[]> mergeIterators(Iterator<Object[]> itr1,Iterator<Object[]>itr2) {

        return iterater_ops.combine_iteratorObject(itr1, itr2);
    }

    /**
     *
     * @param sheetNumber
     * @param filepath
     * @param testCaseName
     * @return
     * @throws IOException
     */
    public static final Iterator<Object[]> excelAPIDataReader(Integer sheetNumber, String filepath,String testCaseName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        File f = new File(filepath);
        FileInputStream file = new FileInputStream(f);
        Workbook workbook = new XSSFWorkbook(file);
        System.out.println(testCaseName);
        String sheetName = (testCaseName.split("_"))[1].replace("Test", "");
        System.out.println("SheetNAME "+sheetName);
        //System.out.println(workbook.getSheetName(0));
        Sheet selectedSheet =  workbook.getSheet(sheetName);
        //System.out.println(selectedSheet.getSheetName());
        ArrayList<Object[]> apiRowData = new ArrayList<Object[]>();
        //System.out.println(selectedSheet.getLastRowNum());
        //Getting row data for test case
        for (int i=0; i <= selectedSheet.getLastRowNum(); i++) {
            String[] queryParamNameArray = null;
            String[] pathParamNameArray = null;
            String[] pathValueArray = null;
            String[] queryValueArray = null;
            String []  headerNameArray = null;
            Map<String, String> headerMap = new LinkedHashMap<String, String>();
            Map<String, String> queryParamMap = new LinkedHashMap<String, String>();
            Map<String, Object> pathParamMap = new LinkedHashMap<String, Object>();
            String [] headerValueArray = null;
            String SERVICE_ENDPOINT;
            String baseURI, endpoint;
            if(selectedSheet.getRow(i).getRowNum() == 0)
                continue;
            Cell testCaseNameCell = selectedSheet.getRow(i).getCell(1);
            Cell baseURlCell = selectedSheet.getRow(i).getCell(2);
            Cell uriCell = selectedSheet.getRow(i).getCell(3);
            Cell methodName = selectedSheet.getRow(i).getCell(4);
            Cell queryParamNameCell = selectedSheet.getRow(i).getCell(5);
            Cell queryParamValueCell = selectedSheet.getRow(i).getCell(6);
            Cell pathParamNameCell = selectedSheet.getRow(i).getCell(7);
            Cell pathParamValueCell = selectedSheet.getRow(i).getCell(8);
            Cell headerNameCell = selectedSheet.getRow(i).getCell(9);
            Cell headerValueCell = selectedSheet.getRow(i).getCell(10);
            Cell statusCodeCell = selectedSheet.getRow(i).getCell(11);
            Cell messageCell = selectedSheet.getRow(i).getCell(12);
            //Checking test case name and returning that data
            //System.out.println("testcaseCellName "+testCaseNameCell.getStringCellValue());
            if(testCaseName.equalsIgnoreCase(testCaseNameCell.getStringCellValue())) {
                //System.out.println(baseURlCell);
                //Set URL with URI
                baseURI = baseURlCell.getStringCellValue();
                endpoint = uriCell.getStringCellValue();

                if (!("NA".equalsIgnoreCase(queryParamNameCell.getStringCellValue())) && !("NA".equalsIgnoreCase(queryParamValueCell.getStringCellValue()))) {
                    queryParamNameArray = formatter.formatCellValue(queryParamNameCell).split("\\|");
                    queryValueArray = formatter.formatCellValue(queryParamValueCell).split("\\|");
                    for(int j=0; j < queryParamNameArray.length; j++) {
                        queryParamMap.put(queryParamNameArray[j],queryValueArray[j]);
                    }
                }
                if (!("NA".equalsIgnoreCase(pathParamNameCell.getStringCellValue())) && !("NA".equalsIgnoreCase(pathParamValueCell.getStringCellValue())) ) {
                    pathParamNameArray = formatter.formatCellValue(pathParamNameCell).split("\\|");
                    pathValueArray = formatter.formatCellValue(pathParamValueCell).split("\\|");
                    for(int l=0; l < pathParamNameArray.length; l++) {
                        pathParamMap.put(pathParamNameArray[l],pathValueArray[l]);
                    }
                }
                if (!("NA".equalsIgnoreCase(headerNameCell.getStringCellValue())) && !("NA".equalsIgnoreCase(headerValueCell.getStringCellValue()))) {
                    headerNameArray = formatter.formatCellValue(headerNameCell).split("\\|");
                    headerValueArray = formatter.formatCellValue(headerValueCell).split("\\|");
                    for(int k=0; k < headerNameArray.length; k++) {
                        headerMap.put(headerNameArray[k],headerValueArray[k]);
                    }
                }

                SERVICE_ENDPOINT = baseURI+endpoint;
                //System.out.println("service endpoint "+SERVICE_ENDPOINT);

                Object ob[] = {methodName.getStringCellValue(),SERVICE_ENDPOINT, headerMap, queryParamMap, pathParamMap, (int)statusCodeCell.getNumericCellValue(),messageCell.getStringCellValue()};
                apiRowData.add(ob);
                //System.out.println(apiRowData.size());
            }
        }
        workbook.close();
        //System.out.println("Inside excel utility "+baseURI);
        return apiRowData.iterator();
    }



    /**
     *
     * @param sheetNumber
     * @param filepath
     * @param testCaseName
     * @return
     * @throws IOException
     */
    public static final Iterator<Object[]> excelAPIDataReader2(Integer sheetNumber, String filepath,String testCaseName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        File f = new File(filepath);
        FileInputStream file = new FileInputStream(f);
        Workbook workbook = new XSSFWorkbook(file);
        String sheetName = (testCaseName.split("_"))[1].replace("Test", "");
        Sheet selectedSheet =  workbook.getSheet(sheetName);
        ArrayList<Object[]> apiRowData = new ArrayList<Object[]>();
        //Getting row data for test case
        for (int i=0; i <= selectedSheet.getLastRowNum(); i++) {
            String SERVICE_ENDPOINT;
            String baseURI, endpoint, payload;

            if(selectedSheet.getRow(i).getRowNum() == 0)
                continue;
            Cell testCaseNameCell = selectedSheet.getRow(i).getCell(1);
            Cell baseURlCell = selectedSheet.getRow(i).getCell(2);
            Cell uriCell = selectedSheet.getRow(i).getCell(3);
            Cell methodName = selectedSheet.getRow(i).getCell(4);
            Cell payloadCell = selectedSheet.getRow(i).getCell(5);
            Cell statusCodeCell = selectedSheet.getRow(i).getCell(6);
            //Checking test case name and returning that data
            if(testCaseName.equalsIgnoreCase(testCaseNameCell.getStringCellValue())) {
                //Set URL with URI
                baseURI = baseURlCell.getStringCellValue();
                endpoint = uriCell.getStringCellValue();
                payload = payloadCell.getStringCellValue();
                SERVICE_ENDPOINT = baseURI+endpoint;
                Object ob[] = {methodName.getStringCellValue(),SERVICE_ENDPOINT, payload, (int)statusCodeCell.getNumericCellValue()};
                apiRowData.add(ob);

            }
        }
        workbook.close();
        return apiRowData.iterator();
    }




    public static final Iterator<Object[]> excelPOST_ReqDataReader(String filepath,String testCaseName) throws IOException{
        File f=new File(filepath);
        FileInputStream file = new FileInputStream(f);
        Workbook workbook = new XSSFWorkbook(file);
        //Sheet selectedSheet =  workbook.getSheet("Sheet1");
        Sheet selectedSheet =  workbook.getSheet("proxy");
        //System.out.println(selectedSheet.getLastRowNum());
        ArrayList<Object[]> apiRowData = new ArrayList<Object[]>();

        //System.out.println(selectedSheet.getLastRowNum());

        switch(testCaseName) {
            case "verifyEmailSignup_Automation":
                for(int i=0;i <= selectedSheet.getLastRowNum();i++) {
                    if(selectedSheet.getRow(i).getRowNum()==0) {
                        continue;
                    }
                    Cell emailIdCell=selectedSheet.getRow(i).getCell(0);
                    //System.out.println(emailIdCell.getStringCellValue());
                    Object ob[]= {emailIdCell.getStringCellValue()};
                    apiRowData.add(ob);


                }
                break;
            case "verifyOTP_verifyOTP":

                for(int i=0;i <= selectedSheet.getLastRowNum();i++) {
                    if(selectedSheet.getRow(i).getRowNum()==0) {
                        continue;
                    }
                    Cell emailIdCell=selectedSheet.getRow(i).getCell(0);
                    Cell full_nameCell=selectedSheet.getRow(i).getCell(1);
                    Cell phone_numberCell=selectedSheet.getRow(i).getCell(2);
                    Cell passwordCell=selectedSheet.getRow(i).getCell(3);
                    //System.out.println(emailIdCell.getStringCellValue());
                    Object ob[]= {(String) formatter.formatCellValue(emailIdCell),(String) formatter.formatCellValue(full_nameCell),
                            (String) formatter.formatCellValue(phone_numberCell),(String) formatter.formatCellValue(passwordCell)};
                    apiRowData.add(ob);


                }
                break;
            case "login_Testlogin":
                for(int i=0;i <= selectedSheet.getLastRowNum();i++) {
                    if(selectedSheet.getRow(i).getRowNum()==0) {
                        continue;
                    }
                    Cell emailIdCell=selectedSheet.getRow(i).getCell(0);
                    Cell passwordCell=selectedSheet.getRow(i).getCell(3);
                    //Cell resetPasswordCell=selectedSheet.getRow(i).getCell(4);
                    Object ob[]= {emailIdCell.getStringCellValue(),
                            passwordCell.getStringCellValue(),
                    };
                    apiRowData.add(ob);


                }
                break;

            case "resetPassword_TestresetPassword":
                for(int i=0;i <= selectedSheet.getLastRowNum();i++) {
                    if(selectedSheet.getRow(i).getRowNum()==0) {
                        continue;
                    }
                    Cell emailIdCell=selectedSheet.getRow(i).getCell(0);
                    Cell reset_passwordCell=selectedSheet.getRow(i).getCell(4);
                    Object ob[]= {emailIdCell.getStringCellValue(),reset_passwordCell.getStringCellValue()};
                    apiRowData.add(ob);


                }
                break;

            case "getReport_TestgetReport":
                for(int i=0;i <= selectedSheet.getLastRowNum();i++) {
                    Object ob[]= {};
                    apiRowData.add(ob);
                }
                break;

            case "deleteReport_TestdeleteReport":
                for(int i=0;i <= selectedSheet.getLastRowNum();i++) {
                    Object ob[]= {};
                    apiRowData.add(ob);
                }
                break;

            case "uploadFile_TestuploadFile":
                for(int i=0;i <= selectedSheet.getLastRowNum();i++) {
                    Object ob[]= {};
                    apiRowData.add(ob);
                }
                break;

            case "downloadFile_TestdownloadFile":
                for(int i=0;i <= selectedSheet.getLastRowNum();i++) {
                    Object ob[]= {};
                    apiRowData.add(ob);
                }
                break;


            case "deleteUser_TestdeleteUser":
                for(int i=0;i <= selectedSheet.getLastRowNum();i++) {
                    if(selectedSheet.getRow(i).getRowNum()==0) {
                        continue;
                    }
                    Cell emailIdCell=selectedSheet.getRow(i).getCell(0);
                    Object ob[]= {emailIdCell.getStringCellValue()};
                    apiRowData.add(ob);


                }
                break;

            case "addReport_TestaddReport":
                for(int i=0;i <= selectedSheet.getLastRowNum();i++) {
                    if(selectedSheet.getRow(i).getRowNum()==0) {
                        continue;
                    }
                    Cell requestIDCell=selectedSheet.getRow(i).getCell(5);
                    Cell reportDateCell=selectedSheet.getRow(i).getCell(6);
                    Cell reporter_fullNameCell=selectedSheet.getRow(i).getCell(7);
                    Cell reporter_ageCell=selectedSheet.getRow(i).getCell(8);
                    Cell reporter_genderCell=selectedSheet.getRow(i).getCell(9);
                    Cell reporter_relationCell=selectedSheet.getRow(i).getCell(10);
                    Cell parentingType_Cell=selectedSheet.getRow(i).getCell(11);
                    Cell Contact_addressTypeCell=selectedSheet.getRow(i).getCell(12);
                    Cell contact_address_1_Cell=selectedSheet.getRow(i).getCell(13);
                    Cell contact_address_2_Cell=selectedSheet.getRow(i).getCell(14);
                    Cell pincodeCell=selectedSheet.getRow(i).getCell(15);
                    Cell countryCell=selectedSheet.getRow(i).getCell(16);
                    Cell primary_countryCode_Cell=selectedSheet.getRow(i).getCell(17);
                    Cell primary_contact_numberCell=selectedSheet.getRow(i).getCell(18);
                    Cell secondary_countryCode_Cell=selectedSheet.getRow(i).getCell(19);
                    Cell secondary_contact_numberCell=selectedSheet.getRow(i).getCell(20);
                    Cell communication_languageCell=selectedSheet.getRow(i).getCell(21);
                    Cell statusCell=selectedSheet.getRow(i).getCell(22);

                    Cell child_fullname_Cell=selectedSheet.getRow(i).getCell(23);
                    Cell child_age_Cell=selectedSheet.getRow(i).getCell(24);
                    Cell child_gender_Cell=selectedSheet.getRow(i).getCell(25);
                    Cell child_height_Cell=selectedSheet.getRow(i).getCell(26);
                    Cell child_weight_Cell=selectedSheet.getRow(i).getCell(27);
                    Cell child_complextion_Cell=selectedSheet.getRow(i).getCell(28);
                    Cell child_clothing_Cell=selectedSheet.getRow(i).getCell(29);
                    Cell child_birthSigns_Cell=selectedSheet.getRow(i).getCell(30);
                    Cell child_otherDetails_Cell=selectedSheet.getRow(i).getCell(31);
                    Cell child_ImageFileKey_Cell=selectedSheet.getRow(i).getCell(32);
                    Cell child_nickname_Cell=selectedSheet.getRow(i).getCell(33);

                    Cell incident_dateCell=selectedSheet.getRow(i).getCell(34);
                    Cell incident_briefCell=selectedSheet.getRow(i).getCell(35);
                    Cell locationCell=selectedSheet.getRow(i).getCell(36);
                    Cell landMarkCell=selectedSheet.getRow(i).getCell(37);
                    Cell PoliceStationCell=selectedSheet.getRow(i).getCell(38);
                    Cell nearByNGO_Cell=selectedSheet.getRow(i).getCell(39);
                    Cell allow_connectPolice_Cell=selectedSheet.getRow(i).getCell(40);
                    Cell self_Verification_Cell=selectedSheet.getRow(i).getCell(41);
                    Cell community_terms_Cell=selectedSheet.getRow(i).getCell(42);





                    Object ob[]= {
                            (String)formatter.formatCellValue(requestIDCell),
                            (String)formatter.formatCellValue(reportDateCell),
                            //reportDateCell.getDateCellValue(),
                            //reporter_fullNameCell.getStringCellValue(),
                            (String)formatter.formatCellValue(reporter_fullNameCell),
                            (String)formatter.formatCellValue(reporter_ageCell),
                            //reporter_ageCell.getNumericCellValue(),

                            //reporter_genderCell.getStringCellValue(),
                            (String)formatter.formatCellValue(reporter_genderCell),
                            reporter_relationCell.getStringCellValue(),
                            parentingType_Cell.getStringCellValue(),
                            Contact_addressTypeCell.getStringCellValue(),
                            contact_address_1_Cell.getStringCellValue(),
                            contact_address_2_Cell.getStringCellValue(),
                            (String)formatter.formatCellValue(pincodeCell),
                            countryCell.getStringCellValue(),
                            (String)formatter.formatCellValue(primary_countryCode_Cell),
                            (String)formatter.formatCellValue(primary_contact_numberCell),
                            (String)formatter.formatCellValue(secondary_countryCode_Cell),
                            (String)formatter.formatCellValue(secondary_contact_numberCell),
                            communication_languageCell.getStringCellValue(),
                            statusCell.getStringCellValue(),

                            child_fullname_Cell.getStringCellValue(),
                            (String)formatter.formatCellValue(child_age_Cell),

                            child_gender_Cell.getStringCellValue(),
                            child_height_Cell.getStringCellValue(),
                            child_weight_Cell.getStringCellValue(),
                            child_complextion_Cell.getStringCellValue(),
                            child_clothing_Cell.getStringCellValue(),
                            child_birthSigns_Cell.getStringCellValue(),
                            child_otherDetails_Cell.getStringCellValue(),
                            child_ImageFileKey_Cell.getStringCellValue(),
                            child_nickname_Cell.getStringCellValue(),
                            //incident_dateCell.getDateCellValue(),

                            (String)formatter.formatCellValue(incident_dateCell),
                            incident_briefCell.getStringCellValue(),
                            locationCell.getStringCellValue(),
                            landMarkCell.getStringCellValue(),
                            PoliceStationCell.getStringCellValue(),
                            nearByNGO_Cell.getStringCellValue(),
                            Boolean.valueOf(allow_connectPolice_Cell.getStringCellValue()),
                            //(Boolean)formatter.formatCellValue(allow_connectPolice_Cell.getStringCellValue()),
                            //self_Verification_Cell.getBooleanCellValue(),
                            Boolean.valueOf(self_Verification_Cell.getStringCellValue()),
                            Boolean.valueOf(community_terms_Cell.getStringCellValue())
                            //community_terms_Cell.getBooleanCellValue()
                    };
                    apiRowData.add(ob);


                }
                break;

        }

        return apiRowData.iterator();
    }

    // THIS METHOD CAN BE USED FOR WRITING DATA TO EXCEL SHEET
    // FAKER DATA TO EXCEL SHEET
    public void writeToExcel(Map<String,Object[]> map) throws IOException{
        XSSFRow row;
        //rowId=spreadsheet.getFirstRowNum();
        Set<String> keys=map.keySet();
        for(String key: keys) {
            row=excel_spreadsheet.createRow(ExcelUtility.rowId);
            rowId++;
            Object[] arr=map.get(key);
            cellId=0;
            for(Object ob: arr) {
                Cell cell=row.createCell(ExcelUtility.cellId++);
                //System.out.println(ob.toString());
                cell.setCellValue(ob.toString());

            }

        }
        FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"/src/test/resources/demoExcelFile.xlsx"));
        workbook.write(out);
        out.close();


    }


}