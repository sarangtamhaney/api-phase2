package pojo_models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChildDetails {

    private String fullname;
    private int age;
    private String gender;
    private String height;

    private String weight;
    private String complexion;

    private String clothing;
    @JsonProperty("birth_signs")
    private String birthSigns;

    @JsonProperty("other_details")
    private String otherDetails;

    @JsonProperty("image_file_key")
    private String imageFileKey;

    @JsonProperty("nickname")
    private String nickName;
}
