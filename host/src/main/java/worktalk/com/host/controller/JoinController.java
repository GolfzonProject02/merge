package worktalk.com.host.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import worktalk.com.host.domain.Host;
import worktalk.com.host.repository.HostDAO;
import worktalk.com.host.service.JoinService;
import worktalk.com.host.service.MailSenderService;

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
	
	/**
	 * Requesting for join form page
	 * @return host/join.jsp
	 */
	
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String join() {
		logger.info("Welcome join!");
		return "join/mailTest";
	}
	
	/**
	 * Add User data to MEMBER table
	 * @param host
	 * @return redirection to home page when join succeed, redirection to join page when failed
	 */
	
	@RequestMapping(value = "/joinOK.do", method = RequestMethod.POST)
	public String join(Host host) {
		logger.info("Welcome joinOK.do!");
		logger.info("{}", host);
		
		int flag = service.join(host);
		
		if (flag == 0) {
			return "redirect:login.do";
		} else {
			logger.info("flag = {}", flag);
			return "redirect:login.do";
		}
		
	}
	
	/**
	 * Compare host's name to Member Table to check whether the name is duplicated or not
	 * @param User
	 * @return 1 if there's duplicated name on Member Table, 0 if there's no duplicated name
	 */
	
	@RequestMapping(value = "/checkDuplicatedName.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkDuplicated(Host host) {
		logger.info("Welcome checkDuplicatedName.do!");
		
		int result = service.checkDuplicatedName(host);
		
		return String.valueOf(result);
	}
	
	
	/**
	 * Searching for duplicated email 
	 * create a random number code for mail validation
	 * send an email to host's email address
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
