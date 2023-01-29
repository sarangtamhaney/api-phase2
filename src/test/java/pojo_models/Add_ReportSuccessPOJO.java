package pojo_models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Add_ReportSuccessPOJO {
    private String status;
    private String message;
    private String content;

    public Add_ReportSuccessPOJO(String status,String message,String content) {
        super();
        this.status=status;
        this.message=message;
        this.content=content;
    }

}