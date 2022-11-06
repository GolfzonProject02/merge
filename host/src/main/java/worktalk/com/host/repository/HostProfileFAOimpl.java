package worktalk.com.host.repository;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.Host;

@Repository
public class HostProfileFAOimpl implements HostProfileFAO {
	private static final Logger logger = LoggerFactory.getLogger(HostProfileFAOimpl.class);
	
	@Autowired
	ServletContext context;

	public HostProfileFAOimpl() {
		logger.info("HostProfileFAOimpl()....");
	}

	@Override
	public Host getHost(Host host) {
		if (host.getMultipartFile().getSize() > 0) {
			logger.info("{}", host.getMultipartFile().getOriginalFilename());
			host.setImgname(host.getMultipartFile().getOriginalFilename());

			String dir_path = context.getRealPath("resources/upload");
			logger.info(dir_path);

			File saveFile = new File(dir_path + "/", host.getImgname());

			String formatName = host.getImgname().substring(host.getImgname().lastIndexOf(".") + 1);

			logger.info("formatName: {}", formatName);

			try {
				host.getMultipartFile().transferTo(saveFile); // saving original image

//				/* create thumbnail image */
//				BufferedImage original_buffer_img = ImageIO.read(saveFile);
//				BufferedImage thumb_buffer_img = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
//				Graphics2D graphic = thumb_buffer_img.createGraphics();
//				graphic.drawImage(original_buffer_img, 0, 0, 100, 100, null);
//
//				File thumb_file = new File(dir_path, "thumb_" + host.getImgname());
//				ImageIO.write(thumb_buffer_img, formatName, thumb_file);

			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			if (host.getImgname() == null) {
				host.setImgname("profill.png");
			}
		}
		
		return host;
	}

}
