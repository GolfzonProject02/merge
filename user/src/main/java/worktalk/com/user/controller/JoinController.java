package worktalk.com.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import worktalk.com.user.domain.User;
import worktalk.com.user.repository.UserDAO;
import worktalk.com.user.service.MailSenderService;
import worktalk.com.user.service.JoinService;

/**
 * author: Juhee Fred Lee (이주희)
 * controller class for join functions (join, checkDuplicated, mailCheck)
 */
@Controller
public class JoinController {
	
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired
	JoinService service;
	@Autowired
	MailSenderService mailSender;
	@Autowired
	UserDAO dao;
	
	/**
	 * Requesting for join form page
	 * @return user/join.jsp
	 */
	
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String join() {
		logger.info("Welcome join!");
		return "login/login";
	}
	
	/**
	 * Add User data to MEMBER table
	 * @param user
	 * @return redirection to home page when join succeed, redirection to join page when failed
	 */
	
	@RequestMapping(value = "/joinOK.do", method = RequestMethod.POST)
	public String join(User user) {
		logger.info("Welcome joinOK.do!");
		user.setTel("010");
		logger.info("{}", user);
		
		int flag = service.join(user);
		
		if (flag == 0) {
			return "redirect:login.do";
		} else {
			logger.info("flag = {}", flag);
			return "redirect:login.do";
		}
	}
	
	/**
	 * Compare user's name to Member Table to check whether the name is duplicated or not
	 * @param User
	 * @return 1 if there's duplicated name on Member Table, 0 if there's no duplicated name
	 */
	
	@RequestMapping(value = "/checkDuplicatedName.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkDuplicated(User user) {
		logger.info("Welcome checkDuplicatedName.do!");
		
		int result = service.checkDuplicatedName(user);
		
		logger.info("result: {}", result);
		
		return String.valueOf(result);
	}
	
	
	/**
	 * Searching for duplicated email 
	 * create a random number code for mail validation
	 * send an email to user's email address
	 * @param String email
	 * @return validation code for mail check, returns 0 when the mail address is duplicated
	 */
	
	@RequestMapping(value = "/mailCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheck(String email) {
		logger.info("Welcome mailCheck.do!");
		logger.info("Request for email validation!");
		logger.info("email address for validation : {}", email);
		
		return mailSender.joinMail(email);
	}
	
}
