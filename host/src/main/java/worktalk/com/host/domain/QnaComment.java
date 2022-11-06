package worktalk.com.host.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString
@EqualsAndHashCode
public class QnaComment implements Serializable{
	private long q_num;
	private String host;
	private String qc_comment;
	private Timestamp qc_date;

}
