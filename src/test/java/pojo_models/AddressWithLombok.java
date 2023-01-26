package pojo_models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressWithLombok {

	private String streetName;
	private int houseNo;

}
