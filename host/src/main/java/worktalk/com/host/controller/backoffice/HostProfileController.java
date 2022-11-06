package worktalk.com.host.controller.backoffice;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import worktalk.com.host.domain.Host;
import worktalk.com.host.repository.HostProfileFAO;
import worktalk.com.host.service.HostProfileService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HostProfileController {
	
	private static final Logger logger = LoggerFactory.getLogger(HostProfileController.class);
	
	@Autowired
	HttpSession session;
	@Autowired
	HostProfileService profileService;
	@Autowired
	HostProfileFAO profileFAO;
	
	/**
	 * Requesting for a certain host's mypage
	 */
	@RequestMapping(value = {"/mypage.do"}, method = RequestMethod.GET)
	public String mypage(Host host, Model model) {
		logger.info("Welcome mypage.do!");
		logger.info("{}", host);
		host.setName((String)session.getAttribute("host_name"));
		logger.info("name: {}", host.getName());
		
		Host host1 = profileService.findByName(host);
		logger.info("{}", host1);
		model.addAttribute("Host_profile", host1);
		
		
		return "mypage/profill";
	}
	
//	/**
//	 * Updating profile information
//	 */
//	@RequestMapping(value = {"/mypage/update.do"}, method = RequestMethod.POST)
//	@ResponseBody
//	public String update(Host host, String searchKey) {
//		logger.info("Welcome update.do!");
//		logger.info("{}", host);
//		
//		if (searchKey.equals("imgname")) {
//			host = profileFAO.getHost(host);
//			logger.info("{}", host);
//		}
//		
//		int flag = profileService.update(searchKey, host);
//		
//		return String.valueOf(flag);
//	}
	
	/**
	 * Updating profile information
	 */
	@RequestMapping(value = {"/mypage/update.do"}, method = RequestMethod.POST)
	@ResponseBody
	public String update(Host host) {
		logger.info("Welcome update.do!");
		logger.info("{}", host);
		
		if (host.getMultipartFile() != null) {
			host = profileFAO.getHost(host);
			logger.info("{}", host);
		}
		
		int flag = profileService.update(host);
		
		return String.valueOf(flag);
	}
	
	
	/**
	 * Leave Service => delete Profile
	 */
	@RequestMapping(value = {"/mypage/leave.do"}, method = RequestMethod.GET)
	@ResponseBody
	public String leave(Host host) {
		logger.info("Welcome leave.do!");
		logger.info("{}", host);
		
		host.setName((String)session.getAttribute("host_name"));
		logger.info("name: {}", host.getName());
		
		int flag = profileService.leave(host);
		
		return String.valueOf(flag);
	}
	
}
