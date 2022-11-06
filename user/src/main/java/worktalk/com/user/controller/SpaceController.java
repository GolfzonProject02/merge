package worktalk.com.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import worktalk.com.user.domain.Qna;
import worktalk.com.user.domain.Review;
import worktalk.com.user.domain.Room;
import worktalk.com.user.domain.Space;
import worktalk.com.user.repository.QnaDAO;
import worktalk.com.user.repository.ReviewDAO;
import worktalk.com.user.repository.SpaceDAO;
import worktalk.com.user.service.SpaceService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SpaceController {

	private static final Logger logger = LoggerFactory.getLogger(SpaceController.class);
	
	@Autowired
	HttpSession session;
	
	@Autowired
	SpaceService service;
	
	@Autowired
	SpaceDAO dao;
	
	@Autowired
	QnaDAO q_dao;
	
	@Autowired
	ReviewDAO rv_dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// 공간전체목록페이지
		@RequestMapping(value = "/space_selectAll.do", method = RequestMethod.GET)
		public String selectAll(Model model) {
			logger.info("Welcome selectAll!");

			logger.info("session {}", session.getAttribute("user_name"));
			
			List<Space> space_list = service.selectAll();
			logger.info("space_list.size() : {}", space_list.size());

			model.addAttribute("space_list", space_list);

			return "spacepage/spacepage";
//			return "backoffice/space/space_selectAll";
		}

		// 공간상세페이지
		@RequestMapping(value = "/space_selectOne.do", method = RequestMethod.GET)
		public String selectOne(Space space, long space_num, Model model) {
			logger.info("Welcome selectOne!");

			Space space2 = service.selectOne(space);
			logger.info("{}", space2);

			List<Room> room_list = dao.findRoom(space_num);
			logger.info("room_list.size() : {}", room_list.size());
			logger.info("room_list : {}", room_list);
			
			List<Qna> qna_list = q_dao.findQnaByspaceNum(space_num);
			logger.info("qna_list.size() : {}", qna_list.size());
			logger.info("qna_list : {}", qna_list);
			
//			List<Review> review_list = rv_dao.findBySpaceNum(space_num);
//			logger.info("review_list.size() : {}", review_list.size());
//			logger.info("review_list : {}", review_list);
			
			model.addAttribute("space2", space2);
			model.addAttribute("room_list", room_list);
			model.addAttribute("qna_list", qna_list);
//			model.addAttribute("review_list", review_list);

			return "spacepage/spacedetail";
		}
		
		@RequestMapping(value = {"/space_searchList.do"}, method = RequestMethod.GET)
		public String searchList(Model model, String searchKey, String searchWord) {
			logger.info("Welcome space_searchList!");
			logger.info("session {}", session.getAttribute("user_name"));

			
			List<Space> space_list = dao.searchList(searchKey, searchWord);
			logger.info("space_list.size() : {}", space_list.size());
			logger.info("space_list : {}", space_list);

			model.addAttribute("space_list", space_list);

			
			return "spacepage/spacepage";
		}
	
}
