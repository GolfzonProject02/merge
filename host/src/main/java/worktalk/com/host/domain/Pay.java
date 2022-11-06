package worktalk.com.host.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode @Getter @Setter @ToString
public class Pay {

	private long p_num;
	private String p_name;
	private long r_num;
	private String imp_uid;
	private String merchant_uid;
	private int p_amount;
	private String p_status;
	private String p_date;
	private String reserve_date;
	private String checkin_date;
	private String reserve_status;
	private String room_name; // name for room
	private String space_num; // name for space
	private String space_name; // name for space
	
}
