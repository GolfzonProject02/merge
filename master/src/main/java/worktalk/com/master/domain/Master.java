package worktalk.com.master.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode @ToString @Getter @Setter 
public class Master implements Serializable{
	
	private long num;
	private String email;
	private String pw;
	private String name;
	private String tel;
	private int role;
	private int penalty;
	private String imgname;
	private MultipartFile multipartFile;

}
