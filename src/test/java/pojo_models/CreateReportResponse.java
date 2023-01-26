package pojo_models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateReportResponse {

    private String status;
    private String message;
    private int content;
}
