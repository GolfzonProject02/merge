package worktalk.com.user.domain;

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
public class Qna implements Serializable{
	private Long q_num;
	private Long space_num;
	private String writer;
	private String type;
	private String content;
	private Timestamp q_date;
	
	private String host;
	private String qc_comment;
	private Timestamp qc_date;

}
