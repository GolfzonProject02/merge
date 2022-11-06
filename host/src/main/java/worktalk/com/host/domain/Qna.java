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
public class Qna implements Serializable{
	private long q_num;
	private long space_num;
	private String writer;
	private String type;
	private String content;
	private Timestamp q_date;
	
	private String host;
	private String qc_comment;
	private Timestamp qc_date;

}
