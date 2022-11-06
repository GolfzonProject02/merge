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
public class Space implements Serializable{
	
	private long space_num;
	private String host;
	private String space_name;
	private String space_detail;
	private String postcode;
	private String address;
	private String detail_address;
	private String reg_code;
	private int space_type;
	private String space_status;
	private String space_img;
	private MultipartFile multipartFile;
	
	
}
