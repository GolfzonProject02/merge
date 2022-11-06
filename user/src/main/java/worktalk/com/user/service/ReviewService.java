package worktalk.com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.user.domain.Review;
import worktalk.com.user.repository.ReviewDAO;

@Service
public class ReviewService {
	private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);
	
	@Autowired
	ReviewDAO dao;
	
	public ReviewService() {
		logger.info("ReviewService()...");
	}

	public int insert(Review review) {
		logger.info("insert()...");
		return dao.insert(review);
	}
	public int update(Review review) {
		logger.info("update()...");
		return dao.update(review);
	}
	public int delete(Review review) {
		logger.info("delete()...");
		return dao.delete(review);
	}

}
