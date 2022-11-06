package worktalk.com.user.repository;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.user.domain.Review;

@Repository
public class ReviewFAOimpl implements ReviewFAO {

	private static final Logger logger = LoggerFactory.getLogger(ReviewFAOimpl.class);

	@Autowired
	private ServletContext context;

	@Override
	public Review getVO(Review review) {

		logger.info("getVO....");
		logger.info("{}", review);

		// vo에 맵핑을 위한 빈등록-root-context.xml << multipartResolver
		logger.info("{} byte", review.getMultipartFile().getSize());

		if (review.getMultipartFile().getSize() > 0) { 
			String originFilename = review.getMultipartFile().getOriginalFilename();
			logger.info("{}", originFilename);
			review.setImgname(originFilename);

			String dir_path = context.getRealPath("resources/upload/review");
			logger.info(dir_path);

			File saveFile = new File(dir_path, review.getImgname());

			try {
				review.getMultipartFile().transferTo(saveFile);// 원본이미지 저장

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (review.getImgname() == null)
				review.setImgname("0");
		}
		return review;
	}

}
