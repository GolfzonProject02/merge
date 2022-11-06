package worktalk.com.host.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.host.domain.Reservation;
import worktalk.com.host.domain.Reservation_status;
import worktalk.com.host.repository.ReservationDAO;

/**
 * 
 * @author Juhee Fred Lee (이주희) Service class for host reservation data
 *         management service
 *
 */

@Service
public class ReservationServiceimpl implements ReservationService {
	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceimpl.class);

	@Autowired
	ReservationDAO dao;

	public ReservationServiceimpl() {
		logger.info("HostReservationServiceimpl()....");
	}

	@Override
	public Reservation updateStatus(Reservation reservation) {
		logger.info("updateStatus()....");
		
		logger.info("{}", reservation);

		int flag = dao.updateStatus(reservation);
		logger.info("flag: {}", flag);

		if (flag == 0) {
			return null;
		} else {
			return dao.findReservationByNum(reservation);
		}
	}

	@Override
	public Reservation cancel(Reservation reservation) {
		logger.info("cancel()....");
		logger.info("{}", reservation);
		
		reservation.setStatus(Reservation_status.호스트취소.toString());
		
		int flag = dao.cancel(reservation);
		logger.info("flag: {}", flag);
		
		if (flag == 0) {
			return null;
		} else {
			return dao.findReservationByNum(reservation);
		}
	}
	
	@Override
	public Reservation findReservationByNum(Reservation reservation) {
		logger.info("findReservationByNum()....");
		logger.info("{}", reservation);
		return dao.findReservationByNum(reservation);
	}

	@Override
	public List<Reservation> findReservationByName(Reservation reservation) {
		logger.info("findReservationByName()....");
		logger.info("{}", reservation);
		return dao.findReservationByName(reservation);
	}

	@Override
	public List<Reservation> findReservationByKeywords(Reservation reservation) {
		logger.info("findReservationByStatus()....");
		logger.info("{}", reservation);
		return dao.findReservationByKeywords(reservation);
	}


}
