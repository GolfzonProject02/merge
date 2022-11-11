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

import worktalk.com.user.domain.Customer_center;
import worktalk.com.user.domain.Review;
import worktalk.com.user.repository.Customer_centerDAO;
import worktalk.com.user.repository.QnaDAO;
import worktalk.com.user.repository.ReviewDAO;
import worktalk.com.user.service.Customer_centerFileService;
import worktalk.com.user.service.Customer_centerService;

/**
 * 작성자 : 최수연 
 * 유저 1대1문의 관리페이지 구현 (1대1문의 CRUD)
 */
@Controller
public class MypageController {

	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	HttpSession session;
	
	@Autowired
	Customer_centerService service;

	@Autowired
	Customer_centerFileService service_file;
	
	@Autowired
	Customer_centerDAO dao;
	
	@Autowired
	QnaDAO q_dao;
	
	@Autowired
	ReviewDAO rv_dao;
	
	//1대1문의관리 페이지
	@RequestMapping(value = "/mypage/customercenter.do", method = RequestMethod.GET)
	public String selectAllCC(String writer, Model model) {
		logger.info("Welcome selectAll");
		
		writer = (String) session.getAttribute("user_name");

		if (writer == null) {
			return "login/login";
		} else {
		logger.info("writer : {}", writer);
		List<Customer_center> cc_boardlist = dao.findByWriter(writer);
		logger.info("cc_boardlist.size() : {}",cc_boardlist.size());
		
		model.addAttribute("cc_boardlist",cc_boardlist);

		return "customerCenter/customerCenter";
		}
	}
	
	//문의 작성
	@RequestMapping(value = "/mypage/customercenter_insert.do", method = RequestMethod.POST)
	public String insert(Customer_center customer_center) {
		logger.info("Welcome insertOK...");
		logger.info("{}", customer_center);
		customer_center = service_file.getVO(customer_center);
		
		int result = service.insert(customer_center);
		logger.info("result : {}", result);
		if (result == 1) {
			return "redirect:customercenter.do";
		} else {
			return "redirect:customercenter_insert.do";
		}
	}
	
	//문의 수정
	@RequestMapping(value = "/mypage/customercenter_update.do", method = RequestMethod.POST)
	public String update(Customer_center customer_center) {
		logger.info("Welcome updateOK");
		
		customer_center = service_file.getVO(customer_center);
		logger.info("{}",customer_center);
		
		int result = service.update(customer_center);
		logger.info("result : {}", result);
		if (result == 1) {
			return "redirect:customercenter.do";
		} else {
			return "customercenter_update.do";
		}
   }
   
   //문의 삭제
   @RequestMapping(value = "/mypage/customercenter_delete.do", method = RequestMethod.GET)
   public String delete(Customer_center customer_center) {
      logger.info("Welcome deleteOK");
      int result = service.delete(customer_center);
      logger.info("result : {}", result);

      return "redirect:customercenter.do";
   }
   
   @RequestMapping(value = "/mypage/customercenter_searchList.do", method = RequestMethod.GET)
   public String searchList(Model model, String searchKey, String searchWord) {
      logger.info("Welcome searchList");
      logger.info("searchKey:{}", searchKey);
      logger.info("searchWord:{}", searchWord);

		List<Customer_center> cc_boardlist = service.searchList(searchKey, searchWord);
		logger.info("result() : {}", cc_boardlist.size());
		model.addAttribute("cc_boardlist", cc_boardlist);
		
		return "customerCenter/customerCenter";
	}

	//Q&A관리 페이지
			@RequestMapping(value = "/mypage/reviewqna.do", method = RequestMethod.GET)
			public String review(Model model, String writer) {
				logger.info("Welcome review");
				
				writer = (String) session.getAttribute("user_name");

				if (writer == null) {
					return "login/login";
				} else {
				logger.info("writer : {}", writer);
				List<Review> review_list = rv_dao.findByName(writer);
				logger.info("review_list.size() : {}",review_list.size());
				
				model.addAttribute("review_list",review_list);
				
				return "reviewqna/reviewqna";
				}
			}
}