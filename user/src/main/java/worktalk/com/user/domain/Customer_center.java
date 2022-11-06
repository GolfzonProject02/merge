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
public class Customer_center implements Serializable{
	private Long cc_num;
	private String writer;
	private String title;
	private String content;
	private String type;
	private Timestamp cc_date;
	private String imgname;
	
//	private Long fnum;
	private MultipartFile multipartFile;
//	private List<MultipartFile> multipartFile;
	
	
}
