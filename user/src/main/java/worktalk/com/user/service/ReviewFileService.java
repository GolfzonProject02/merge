package worktalk.com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.user.domain.Review;
import worktalk.com.user.repository.ReviewFAO;

@Service
public class ReviewFileService {
	private static final Logger logger = LoggerFactory.getLogger(ReviewFileService.class);
	
	@Autowired
	ReviewFAO fao;
	
	public ReviewFileService() {
		logger.info("ReviewFileService()...");
	}

	public Review getVO(Review review) {
		logger.info("getVO()...");
		return fao.getVO(review);//return 1을 받고 controller에 1보내줌
	}
	
}
