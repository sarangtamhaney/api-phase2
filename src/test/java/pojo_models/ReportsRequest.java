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
    private ReporterDetails reporterDetails;

    @JsonProperty("child_details")
    private ChildDetails childDetails;

    @JsonProperty("incident_details")
    private IncidentDetails incidentDetails;
}
