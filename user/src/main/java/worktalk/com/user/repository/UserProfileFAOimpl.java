package worktalk.com.user.repository;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.user.domain.User;

@Repository
public class UserProfileFAOimpl implements UserProfileFAO {
	private static final Logger logger = LoggerFactory.getLogger(UserProfileFAOimpl.class);
	
	@Autowired
	ServletContext context;

	public UserProfileFAOimpl() {
		logger.info("UserProfileFAOimpl()....");
	}

	@Override
	public User getUser(User user) {
		if (user.getMultipartFile().getSize() > 0) {
			logger.info("{}", user.getMultipartFile().getOriginalFilename());
			user.setImgname(user.getMultipartFile().getOriginalFilename());

			String dir_path = context.getRealPath("resources/upload");
			logger.info(dir_path);

			File saveFile = new File(dir_path + "/", user.getImgname());

			String formatName = user.getImgname().substring(user.getImgname().lastIndexOf(".") + 1);

			logger.info("formatName: {}", formatName);

			try {
				user.getMultipartFile().transferTo(saveFile); // saving original image

//				/* create thumbnail image */
//				BufferedImage original_buffer_img = ImageIO.read(saveFile);
//				BufferedImage thumb_buffer_img = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
//				Graphics2D graphic = thumb_buffer_img.createGraphics();
//				graphic.drawImage(original_buffer_img, 0, 0, 100, 100, null);
//
//				File thumb_file = new File(dir_path, "thumb_" + user.getImgname());
//				ImageIO.write(thumb_buffer_img, formatName, thumb_file);

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			if (user.getImgname() == null) {
				user.setImgname("profil.jpg");
			}
		}
		
		return user;
	}

}
