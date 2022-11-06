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

import worktalk.com.user.domain.Customer_center;

@Repository
public class Customer_centerFAOimpl implements Customer_centerFAO {

	private static final Logger logger = LoggerFactory.getLogger(Customer_centerFAOimpl.class);

	@Autowired
	private ServletContext context;

	@Override
	public Customer_center getVO(Customer_center customer_center) {

		logger.info("getVO....");
		logger.info("{}", customer_center);

		// vo에 맵핑을 위한 빈등록-root-context.xml << multipartResolver
		logger.info("{} byte", customer_center.getMultipartFile().getSize());

		if (customer_center.getMultipartFile().getSize() > 0) { 
			String originFilename = customer_center.getMultipartFile().getOriginalFilename();
			logger.info("{}", originFilename);
			customer_center.setImgname(originFilename);

			String dir_path = context.getRealPath("resources/upload/customer_center");
			logger.info(dir_path);

			File saveFile = new File(dir_path, customer_center.getImgname());

			try {
				customer_center.getMultipartFile().transferTo(saveFile);// 원본이미지 저장

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (customer_center.getImgname() == null)
				customer_center.setImgname("0");
		}
		return customer_center;
	}

}
