package worktalk.com.user.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import worktalk.com.user.domain.Review;
import worktalk.com.user.repository.ReviewDAO;
import worktalk.com.user.service.ReviewFileService;
import worktalk.com.user.service.ReviewService;

/**
 * 작성자 : 최수연 
 * 유저 리뷰 작성 구현
 */
@Controller
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	HttpSession session;

	@Autowired
	ReviewService service;

	@Autowired
	ReviewFileService service_file;

	@Autowired
	ReviewDAO rv_dao;


	// 이용완료한 사무공간에 대해 후기 작성
	@RequestMapping(value = "/mypage/review_insert.do", method = RequestMethod.POST)
	public String insert(String writer, Review review) {
		logger.info("Welcome insertOK...");
		logger.info("{}", review);
		writer = (String) session.getAttribute("user_name");

		if (writer == null) {
			return "login/login";
		} else {
			logger.info("writer : {}", writer);
			review = service_file.getVO(review);

			int result = service.insert(review);
			logger.info("result : {}", result);
			return "redirect:reviewqna.do";

		}
	}
}
