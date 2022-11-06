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
public class Review implements Serializable{
	private long rv_num;
	private long r_num;
	private String review;
	private double grade;
	private Timestamp rv_date;
	private String imgname;
	private MultipartFile multipartFile;
}
