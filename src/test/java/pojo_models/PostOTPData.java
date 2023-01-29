package pojo_models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostOTPData {
    private String otp;

    public PostOTPData(String otp) {
        this.otp = otp;
        System.out.println("INSIDE CONSTRUCTOR SETTING OTP"+otp);

    }

}