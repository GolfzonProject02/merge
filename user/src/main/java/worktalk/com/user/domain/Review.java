package worktalk.com.user.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@ToString
@EqualsAndHashCode
public class Review implements Serializable{
	private long rv_num;
	private long r_num;
	private String writer;
	private String review;
	private double grade;
	private Timestamp rv_date;
	private String imgname;
	private MultipartFile multipartFile;
	
	private String host;
	private String rc_comment;
	private Timestamp rc_date;
}
