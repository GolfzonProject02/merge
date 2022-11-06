package worktalk.com.master.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import worktalk.com.master.domain.Master;
import worktalk.com.master.domain.Penalty;
import worktalk.com.master.service.PenaltyService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PenaltyController {
	
	private static final Logger logger = LoggerFactory.getLogger(PenaltyController.class);
	
	@Autowired
	PenaltyService service;
	
	/**
	 * Give User or host a penalty
	 * @return 
	 */
	
	@RequestMapping(value = "/penalty/findAll.do", method = RequestMethod.GET)
	public String insert(Model model) {
		logger.info("Welcome findAll.do!");
		
		List<Master> member_list = service.findAll();
		
		logger.info("size: {}", member_list.size());
		logger.info("{}", member_list);
		
		model.addAttribute("member_list", member_list);
		
		return "home";
	}
	
	/**
	 * Give User or host a penalty
	 * @return 
	 */
	
	@RequestMapping(value = "/penalty/insert.do", method = RequestMethod.POST)
	@ResponseBody
	public String insert(Penalty penalty) {
		logger.info("Welcome insert.do!");
		
		int result = service.insert(penalty);
		
		return String.valueOf(result);
	}
	
	
	/**
	 * Delete penalty that were given to User or host
	 * @param String email
	 * @return 
	 */
	
	@RequestMapping(value = "/penalty/delete.do", method = RequestMethod.GET)
	@ResponseBody
	public String delete(Penalty penalty) {
		logger.info("Welcome delete.do!");
		
		int result = service.delete(penalty);
		
		return String.valueOf(result);
	}
	
}
