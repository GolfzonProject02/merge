package worktalk.com.host.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import worktalk.com.host.domain.Pay;
import worktalk.com.host.service.PayService;

/**
 * author: Juhee Fred Lee (이주희)
 * Controller class for login and logout.
 */
@Controller
public class PaymentController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	HttpSession session;
	@Autowired
	PayService payService;
	
	/**
	 * requesting login form page
	 */
	
	@RequestMapping(value = "/backoffice/payment/findByName.do", method = RequestMethod.GET)
	public String findByName(Pay pay, Model model) {
		logger.info("Welcome findByName.do!");
		pay.setP_name((String)session.getAttribute("host_name"));
		logger.info("{}", pay);
		
		List<Pay> pay_list = payService.findHistoryByName(pay);
		int calPaid = payService.calPaid(pay);
		int calRefund = payService.calRefund(pay);
		
		logger.info("pay_list.size() : {}", pay_list.size());
		logger.info("pay_list : {}", pay_list);
		logger.info("calPaid : {}, calRefund : {}", calPaid, calRefund);
		
		model.addAttribute("pay_list", pay_list);
		model.addAttribute("calPaid", calPaid);
		model.addAttribute("calRefund", calRefund);
		
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value = "/backoffice/payment/findByStatus.do", method = RequestMethod.GET)
	public List<Pay> findHistoryByName(Pay pay, Model model) {
		logger.info("Welcome findHistoryByName.do!");
		pay.setP_name((String)session.getAttribute("host_name"));
		logger.info("{}", pay);
		
		List<Pay> pay_list_status = payService.findHistoryByStatus(pay);
		logger.info("pay_list_status.size() : {}", pay_list_status.size());
		logger.info("pay_list_status : {}",pay_list_status);
		
		
		return pay_list_status;
	}
	
}
