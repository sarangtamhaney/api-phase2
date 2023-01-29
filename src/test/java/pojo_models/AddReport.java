package pojo_models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter
public class AddReport {
    Reporter_details reported_detail;
    Child_details child_detail;
    Incident_details incident_detail;

}