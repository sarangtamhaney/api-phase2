package pojo_models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Incident_details {

    @JsonProperty("incident_date")
    private String incidentDate;

    @JsonProperty("incident_brief")
    private String incidentBrief;

    private String location;

    @JsonProperty("landmark_signs")
    private String landmarkSigns;

    @JsonProperty("nearby_police_station")
    private String nearby_police_station;

    @JsonProperty("nearby_NGO")
    private String nearbyNGO;

    @JsonProperty("allow_connect_police_NGO")
    private String allowConnect;

    @JsonProperty("self_verification")
    private String selfVerification;

    @JsonProperty("community_terms")
    private String communityTerms;

}
