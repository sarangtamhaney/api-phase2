package pojo_models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Token {

    private String accessToken;
    private String tokenType;
    @JsonProperty("expires_in")
    private long expiresIn;
}
