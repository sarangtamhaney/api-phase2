package pojo_models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class FriendWithLombok {
	
	private String firstname;
	private String lastname;
	private String id;
	private int age;
	private List<AddressWithLombok> address;

}
