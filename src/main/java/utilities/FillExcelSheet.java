package utilities;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class FillExcelSheet {
    static int j=1;

    ExcelUtility utility=new ExcelUtility();
    GenerateDataFaker generate_data=new GenerateDataFaker();
    public void fillExcelSheet() throws IOException {

        Map<String,Object[]> data=new TreeMap<String,Object[]>();

        data.put(String.valueOf(j),new Object[] {
                "Email_id",
                "fullName",
                "phone_number",
                "password",
                "reset-password",
                "request_id",
                "report_date",
                "reporter_fullName",
                "reporter_age",
                "reporter_gender",
                "reporter_relation",
                "parenting_type",
                "contact_address_type",
                "contact_address_line_1",
                "contact_address_line_2",
                "pincode",
                "country",
                "primary_countryCode",
                "primary_contactNumber",
                "secondary_countryCode",
                "secondary_contactNumber",
                "communication_language",
                "status",
                "child_fullName",
                "child_age",
                "child_gender",
                "child_height",
                "child_weight",
                "child_complexion",
                "child_clothing",
                "child_birthSigns",
                "child_OtherDetails",
                "Image_file_key",
                "child_nickName",
                "incident_date",
                "incident_brief",
                "location",
                "landmarkSigns",
                "nearby_Police_station",
                "nearby_NGO",
                "allow_Police_connectNGO",
                "self_verification",
                "community_terms"});
        // "2022-09-01T01:37:30Z",
        j++;

        for(int i=0;i < 12;i++) {
            data.put(String.valueOf(j),new Object[] {
                    generate_data.getEmail(),
                    generate_data.getFullName(),
                    generate_data.getContactNumber(),
                    generate_data.getPassword(),
                    generate_data.getPassword(),
                    generate_data.getRequestId(),
                    generate_data.getDate(),
                    generate_data.getFullName(),
                    generate_data.getAge(),
                    generate_data.getGender(),
                    generate_data.getRelation(),
                    "Own Child",
                    "Home",
                    generate_data.getStreetAddress(),
                    generate_data.getAddress(),
                    generate_data.getPinCode(),
                    generate_data.getCountry(),
                    generate_data.getCountryCode(),
                    generate_data.getContactNumber(),
                    generate_data.getCountryCode(),
                    generate_data.getContactNumber(),
                    "english",
                    "INCOMPLETE",
                    generate_data.getFullName(),
                    generate_data.getAge(),
                    generate_data.getGender(),
                    "5ft",
                    "75kg",
                    "fair",
                    "red top yellow shirt",
                    "mark on left ear",
                    "wears spectatle",
                    "null",
                    generate_data.getNickName(),
                    generate_data.getDate(),
                    "Child went missing near the school",
                    generate_data.getLocation(),
                    generate_data.getLandMarkSigns(),
                    "city police station",
                    "Samrudi NGO",
                    generate_data.getBooleanValue(),
                    generate_data.getBooleanValue(),
                    generate_data.getBooleanValue()});


            j++;
        }
        utility.writeToExcel(data);




    }
    public static void main(String args[]) throws IOException {
        FillExcelSheet obj=new FillExcelSheet();
        obj.fillExcelSheet();
    }


}