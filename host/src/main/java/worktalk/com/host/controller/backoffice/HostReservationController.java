package worktalk.com.host.controller.backoffice;

import java.io.IOException;
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

import com.siot.IamportRestClient.exception.IamportResponseException;

import worktalk.com.host.domain.Pay;
import worktalk.com.host.domain.Reservation;
import worktalk.com.host.domain.Reservation_status;
import worktalk.com.host.service.PayService;
import worktalk.com.host.service.ReservationService;

/**
 * author: Juhee Fred Lee (이주희)
 * controller class for join functions (join, checkDuplicated, mailCheck)
 */
@Controller
public class HostReservationController {
	
	private static final Logger logger = LoggerFactory.getLogger(HostReservationController.class);
	
	@Autowired
	HttpSession session;
	@Autowired
	ReservationService reserveService;
	@Autowired
	PayService payService;
	
	/**
	 * Requesting for host home page
	 * @return home.jsp
	 */
	
	@RequestMapping(value = "/backoffice/reservation/findByName.do", method = RequestMethod.GET)
	public String findByName(Reservation reservation, Model model) {
		logger.info("Welcome findByName!");
		
		reservation.setName((String) session.getAttribute("host_name"));
		
		logger.info("{}", reservation);
		
		List<Reservation> host_reservation_list =  reserveService.findReservationByName(reservation);
		logger.info("host_reservation_list : {}", host_reservation_list);
		logger.info("host_reservation_list.size : {}",host_reservation_list.size());
		
		model.addAttribute("host_reservation_list", host_reservation_list);
		
		return "reservation/reservationList";
	}
	
	@RequestMapping(value = "/backoffice/reservation/findByStatus.do", method = RequestMethod.GET)
	@ResponseBody
	public List<Reservation> findByStatus(Reservation reservation) {
		logger.info("Welcome findByStatus!");
		
		reservation.setName((String) session.getAttribute("host_name"));
		
		logger.info("{}", reservation);
		
		List<Reservation> reservation_list_status =  reserveService.findReservationByKeywords(reservation);
		logger.info("reservation_list_status : {}",reservation_list_status);
		logger.info("reservation_list.size : {}",reservation_list_status.size());
		
		
		return reservation_list_status;
	}
	
	@RequestMapping(value = "/backoffice/reservation/end.do", method = RequestMethod.GET)
	@ResponseBody
	public Reservation end(Reservation reservation) {
		logger.info("Welcome end!");
		
		reservation.setStatus(Reservation_status.이용완료.toString());
		Reservation end_result = reserveService.updateStatus(reservation);
		logger.info("{}", end_result);

		
		return end_result;
	}
	
	
	@RequestMapping(value = "/backoffice/reservation/cancel.do", method = RequestMethod.POST)
	@ResponseBody
	public Reservation cancel(Reservation reservation) throws IamportResponseException, IOException {
		logger.info("Welcome cancel!");
		logger.info("reservation : {}", reservation);
		
		Reservation cancel_result = reserveService.cancel(reservation);
		logger.info("cancel_result : {}", cancel_result);
		
		if (cancel_result == null) {
			return null;
		} else {
			Pay pay = new Pay();
			pay.setR_num(reservation.getR_num());
			payService.cancelByUid(pay);
		}
		return cancel_result;
	}
	
}
