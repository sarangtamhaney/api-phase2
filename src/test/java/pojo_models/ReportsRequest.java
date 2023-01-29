package pojo_models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReportsRequest {

    @JsonProperty("reporter_details")
    private Reporter_details reporterDetails;

    @JsonProperty("child_details")
    private Child_details childDetails;

    @JsonProperty("incident_details")
    private Incident_details incidentDetails;
}
