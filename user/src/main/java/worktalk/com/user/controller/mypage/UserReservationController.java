package worktalk.com.user.controller.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import worktalk.com.user.domain.Reservation;
import worktalk.com.user.service.ReservationService;

/**
 * Handles requests for the application mypage.
 */
@Controller
public class UserReservationController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserReservationController.class);
	
	@Autowired
	HttpSession session;
	@Autowired
	ReservationService myReserveServiceDAO;
	
	
	/**
	 * request for user reservation page
	 */
	@RequestMapping(value = {"/mypage/reservation.do"}, method = RequestMethod.GET)
	public String leave(Reservation reservation, Model model) {
		logger.info("Welcome mypage/reservation.do!");
		logger.info("{}", reservation);
		
		reservation.setName((String)session.getAttribute("user_name"));
		logger.info("name: {}", reservation.getName());
		
		List<Reservation> list = myReserveServiceDAO.findReservationByName(reservation);
		logger.info("list: {}", list);
		
		model.addAttribute("reservation_list", list);
		return "home";
	}
	
	/**
	 * request for user reservation page and sort the list by status/p_status
	 */
	@RequestMapping(value = {"/mypage/findByStatus.do"}, method = RequestMethod.GET)
	public String findByStatus(Reservation reservation) {
		logger.info("Welcome mypage/findByStatus.do!");
		logger.info("{}", reservation);
		
		reservation.setName((String)session.getAttribute("user_name"));
		logger.info("name: {}", reservation.getName());
		
		reservation.setName("사용자");
		logger.info("name: {}", reservation.getName());
		
		List<Reservation> list = myReserveServiceDAO.findReservationByStatus(reservation);
		logger.info("list: {}", list);
		return "home";
	}
	
	
}
