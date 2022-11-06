package worktalk.com.user.domain;


import org.springframework.web.multipart.MultipartFile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Room {

	private long room_num;
	private long space_num;
//	private Roomtype enum_room_type;
	private String room_type;
	private String room_name;
	private int room_price;
	private String work_start;
	private String work_end;
	private String room_detail;
	private String room_img;
	private MultipartFile multipartFile;

}
