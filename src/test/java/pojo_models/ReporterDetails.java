package pojo_models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReporterDetails {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("report_date")
    private String reportDate;

    @JsonProperty("reporter_fullname")
    private String reporterFullname;

    @JsonProperty("reporter_age")
    private int reporterAge;

    @JsonProperty("reporter_gender")
    private String reporterGender;

    @JsonProperty("reporter_relation")
    private String reporterRelation;

    @JsonProperty("parenting_type")
    private String parentingType;

    @JsonProperty("contact_address_type")
    private String contactAddressType;

    @JsonProperty("contact_address_line_1")
    private String contactAddressLineOne;

    @JsonProperty("contact_address_line_2")
    private String contactAddressLineTwo;

    @JsonProperty("pincode")
    private int pincode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("primary_country_code")
    private String primaryCountryCode;

    @JsonProperty("primary_contact_number")
    private String primaryContactNumber;

    @JsonProperty("secondary_country_code")
    private String secondaryCountryCode;

    @JsonProperty("secondary_contact_number")
    private String secondaryContactNumber;

    @JsonProperty("communication_language")
    private String communicationLanguage;

    @JsonProperty("status")
    private String status;

}
