package worktalk.com.master.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import worktalk.com.master.domain.Master;
import worktalk.com.master.repository.MasterDAO;
import worktalk.com.master.service.JoinService;
import worktalk.com.master.service.MailSenderService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class JoinController {
	
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired
	JoinService service;
	@Autowired
	MailSenderService mailSender;
	@Autowired
	MasterDAO dao;
	
	/**
	 * Requesting for join form page
	 * @return master/join.jsp
	 */
	
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String join() {
		logger.info("Welcome join!");
		return "home";
	}
	
	/**
	 * Add User data to MEMBER table
	 * @param master
	 * @return redirection to home page when join succeed, redirection to join page when failed
	 */
	
	@RequestMapping(value = "/joinOK.do", method = RequestMethod.POST)
	public String join(Master master) {
		logger.info("Welcome joinOK.do!");
		logger.info("{}", master);
		
		int flag = dao.join(master);
		
		if (flag == 0) {
			return "redirect:";
		} else {
			logger.info("flag = {}", flag);
			return "redirect:";
		}
	}
	
	/**
	 * Compare master's name to Member Table to check whether the name is duplicated or not
	 * @param User
	 * @return 1 if there's duplicated name on Member Table, 0 if there's no duplicated name
	 */
	
	@RequestMapping(value = "/checkDuplicatedName.do", method = RequestMethod.GET)
	@ResponseBody
	public String checkDuplicated(Master master) {
		logger.info("Welcome checkDuplicatedName.do!");
		
		int result = service.checkDuplicatedName(master);
		
		return String.valueOf(result);
	}
	
	
	/**
	 * Searching for duplicated email 
	 * create a random number code for mail validation
	 * send an email to master's email address
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
