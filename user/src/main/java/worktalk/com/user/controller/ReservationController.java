package worktalk.com.user.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
import com.siot.IamportRestClient.response.Payment;

import worktalk.com.user.domain.Pay;
import worktalk.com.user.domain.Reservation;
import worktalk.com.user.service.PayService;
import worktalk.com.user.service.ReservationService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ReservationController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	HttpSession session;
	@Autowired
	ReservationService reserveService;
	@Autowired
	PayService payService;
	
	/**
	 * Making reservation
	 */
	@ResponseBody
	@RequestMapping(value = {"/reservation/reserve.do"}, method = RequestMethod.POST)
	public String reserve(Reservation reservation, Pay pay) {
		logger.info("Welcome reserve.do!");
		logger.info("reservation: {}", reservation);
		logger.info("pay: {}", pay);
		
		Reservation result_reservation = reserveService.reserve(reservation);
		logger.info("result_reservation: {}", result_reservation);
		
		if (result_reservation == null) {
			logger.info("reservation insert failed....");
			if (reservation.getRoom_num() != 0) {
				logger.info("cancelling => redirecting to space_selectOne()....");
				long space_num = reserveService.findSpaceNum(reservation);
				return "payment/cancel_page.do?imp_uid="+pay.getImp_uid()+"&space_num="+space_num;
			} else {
				logger.info("cancelling => redirecting to space_selectAll()....");
				return "payment/cancel_page.do?imp_uid="+pay.getImp_uid();
			}
		} else {
			pay.setR_num(result_reservation.getR_num());
			Pay result_pay = payService.insert(pay);
			if (result_pay == null) {
				logger.info("pay insert failed....");
				int flag = reserveService.delete(result_reservation);
				logger.info("reservation delete result: {}", flag);
				return "payment/cancel_page.do?imp_uid="+pay.getImp_uid()+"&space_num="+result_reservation.getSpace_num();
			} else {
				logger.info("headding findByNum....");
				String result = "reservation/findByNum.do?r_num="+result_reservation.getR_num();
				logger.info(result);
				return result;
			}
		}
	}
	
	/**
	 * returns list of reservation on specific room and date
	 */
	@RequestMapping(value = {"/reservation/isBooked.do"}, method = RequestMethod.GET)
	@ResponseBody
	public List<Reservation> isBooked(Reservation reservation, String date) {
		logger.info("Welcome isBooked.do!");
		logger.info("date: {}", date);
		
		Timestamp start = Timestamp.valueOf(date);
		Timestamp end = Timestamp.valueOf(date);
		
		logger.info("{}, {}", start, end);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(end);
		cal.add(Calendar.DATE, 1);
		
		end.setTime(cal.getTime().getTime());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String r_start = sdf.format(start);
		String r_end = sdf.format(end);
		
		logger.info("{}, {}", r_start, r_end);
		
		reservation.setR_start(r_start);
		reservation.setR_end(r_end);
		
		logger.info("{}", reservation);
		
		List<Reservation> list = reserveService.isBooked(reservation);
		logger.info("result: {}, size : {}", list, list.size());
		
		return list;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 * @throws IamportResponseException 
	 */
	@RequestMapping(value = {"/reservation/cancel.do"}, method = RequestMethod.POST)
	@ResponseBody
	public Reservation cancel(Reservation reservation) throws IamportResponseException, IOException {
		logger.info("Welcome cancel.do!");
		logger.info("reservation: {}", reservation);
		
		Reservation result = reserveService.cancel(reservation);
		logger.info("flag: {}", result);
		Timestamp current_time = new Timestamp(System.currentTimeMillis());
		
		if (result == null) {
			return null;
		} else {
			Pay pay = new Pay();
			pay.setR_num(result.getR_num());
			pay.setReserve_date(result.getR_date());
			
			Pay pay1 = payService.calRefund(pay, current_time);
			logger.info("{}", pay1);
			
			Pay p_result = payService.cancelByUid_partial(pay1);
			result.setAmount(p_result.getP_amount());
			
			logger.info("result: {}", result);
			
			return result;
		}
	}
	
	/**
	 * request for user specific reservation page
	 */
	@RequestMapping(value = {"/reservation/findByNum.do"}, method = RequestMethod.GET)
	public String findByNum(Reservation reservation, Model model) {
		logger.info("Welcome findByNum.do!");
		logger.info("{}", reservation);
		
		Reservation result = reserveService.findReservationByNum(reservation);
		logger.info("result: {}", result);
		
		model.addAttribute("reservation", result);
		return "reservation/reservationPage";
	}
	
	/**
	 * request for user specific reservation page
	 */
	@RequestMapping(value = {"/reservation/findByName.do"}, method = RequestMethod.GET)
	public String findByName(Reservation reservation, Model model) {
		logger.info("Welcome findByName.do!");
		reservation.setName((String)session.getAttribute("user_name"));
		logger.info("{}", reservation);
		
		List<Reservation> result = reserveService.findReservationByName(reservation);
		logger.info("reservation_list: {}", result);
		
		model.addAttribute("reservation_list", result);
		return "reservation/reservationList";
	}
	
	@ResponseBody
	@RequestMapping(value = {"/reservation/findByStatus.do"}, method = RequestMethod.GET)
	public List<Reservation> findByStatus(Reservation reservation) {
		logger.info("Welcome findByStatus.do!");
		reservation.setName((String)session.getAttribute("user_name"));
		logger.info("{}", reservation);
		
		List<Reservation> result = reserveService.findReservationByName(reservation);
		logger.info("reservation_list: {}", result);
		
		return result;
	}
	
}
