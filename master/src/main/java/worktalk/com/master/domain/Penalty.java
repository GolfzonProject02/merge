package worktalk.com.master.domain;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode @ToString @Getter @Setter
public class Penalty implements Serializable{

	private int p_num;
	private String p_name;
	private String p_reason;
	private int p_type;
	private String p_date;
	
}
