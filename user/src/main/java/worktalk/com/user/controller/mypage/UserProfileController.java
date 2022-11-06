package worktalk.com.user.controller.mypage;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import worktalk.com.user.domain.User;
import worktalk.com.user.repository.UserProfileFAO;
import worktalk.com.user.service.UserProfileService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserProfileController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);
	
	@Autowired
	HttpSession session;
	@Autowired
	UserProfileService profileService;
	@Autowired
	UserProfileFAO profileFAO;
	
	/**
	 * Requesting for a certain user's mypage
	 */
	@RequestMapping(value = {"/mypage.do"}, method = RequestMethod.GET)
	public String mypage(User user, Model model) {
		logger.info("Welcome mypage.do!");
		logger.info("{}", user);
		user.setName((String)session.getAttribute("user_name"));
		logger.info("name: {}", user.getName());
		
		User user1 = profileService.findByName(user);
		logger.info("{}", user1);
		model.addAttribute("User_profile", user1);
		
		
		return "mypage/profill";
	}
	
//	/**
//	 * Updating profile information
//	 */
//	@RequestMapping(value = {"/mypage/update.do"}, method = RequestMethod.POST)
//	@ResponseBody
//	public String update(User user, String searchKey) {
//		logger.info("Welcome update.do!");
//		logger.info("{}", user);
//		
//		if (searchKey.equals("imgname")) {
//			user = profileFAO.getUser(user);
//			logger.info("{}", user);
//		}
//		
//		int flag = profileService.update(searchKey, user);
//		
//		return String.valueOf(flag);
//	}
	
	/**
	 * Updating profile information
	 */
	@RequestMapping(value = {"/mypage/update.do"}, method = RequestMethod.POST)
	@ResponseBody
	public String update(User user) {
		logger.info("Welcome update.do!");
		logger.info("{}", user);
		
		if (user.getMultipartFile() != null) {
			user = profileFAO.getUser(user);
			logger.info("{}", user);
		}
		
		int flag = profileService.update(user);
		
		return String.valueOf(flag);
	}
	
	
	/**
	 * Leave Service => delete Profile
	 */
	@RequestMapping(value = {"/mypage/leave.do"}, method = RequestMethod.GET)
	@ResponseBody
	public String leave(User user) {
		logger.info("Welcome leave.do!");
		logger.info("{}", user);
		
		user.setName((String)session.getAttribute("user_name"));
		logger.info("name: {}", user.getName());
		
		int flag = profileService.leave(user);
		
		return String.valueOf(flag);
	}
	
}
