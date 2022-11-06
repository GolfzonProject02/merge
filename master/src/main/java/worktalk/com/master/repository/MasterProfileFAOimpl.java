package worktalk.com.master.repository;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.master.domain.Master;

@Repository
public class MasterProfileFAOimpl implements MasterProfileFAO {
	private static final Logger logger = LoggerFactory.getLogger(MasterProfileFAOimpl.class);
	
	@Autowired
	ServletContext context;

	public MasterProfileFAOimpl() {
		logger.info("MasterProfileFAOimpl()....");
	}

	@Override
	public Master getMaster(Master master) {
		if (master.getMultipartFile().getSize() > 0) {
			logger.info("{}", master.getMultipartFile().getOriginalFilename());
			master.setImgname(master.getMultipartFile().getOriginalFilename());

			String dir_path = context.getRealPath("resources/upload");
			logger.info(dir_path);

			File saveFile = new File(dir_path + "/", master.getImgname());

			String formatName = master.getImgname().substring(master.getImgname().lastIndexOf(".") + 1);

			logger.info("formatName: {}", formatName);

			try {
				master.getMultipartFile().transferTo(saveFile); // saving original image

//				/* create thumbnail image */
//				BufferedImage original_buffer_img = ImageIO.read(saveFile);
//				BufferedImage thumb_buffer_img = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
//				Graphics2D graphic = thumb_buffer_img.createGraphics();
//				graphic.drawImage(original_buffer_img, 0, 0, 100, 100, null);
//
//				File thumb_file = new File(dir_path, "thumb_" + master.getImgname());
//				ImageIO.write(thumb_buffer_img, formatName, thumb_file);

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			if (master.getImgname() == null) {
				master.setImgname("profil.jpg");
			}
		}
		
		return master;
	}

}
