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

import worktalk.com.user.domain.Space;
import worktalk.com.user.repository.SpaceDAO;
import worktalk.com.user.service.SpaceService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	HttpSession session;
	
	@Autowired
	SpaceService service;
	
	@Autowired
	SpaceDAO dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "main.do", "home.do"}, method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome main!");
		
//		List<Space> recommend_region_list = dao.recommendRegion();
//		logger.info("recommend_region_list.size() : {}", recommend_region_list.size());
//
//		model.addAttribute("recommend_region_list", recommend_region_list);
		
		logger.info("user_name : {}", session.getAttribute("user_name"));
		logger.info("host_name : {}", session.getAttribute("host_name"));

		return "main";
	}
	
}
