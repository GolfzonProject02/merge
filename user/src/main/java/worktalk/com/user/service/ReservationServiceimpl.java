package worktalk.com.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.user.domain.Reservation;
import worktalk.com.user.domain.Reservation_status;
import worktalk.com.user.repository.ReservationDAO;

@Service
public class ReservationServiceimpl implements ReservationService {
	
	private static final Logger logger = LoggerFactory.getLogger(ReservationServiceimpl.class);

	@Autowired
	ReservationDAO dao;
	
	public ReservationServiceimpl() {
		logger.info("ReservationServiceDAOimpl()....");
	}
	
	/*
	 * Add reservation data to RESERVATION Table
	 */
	@Override
	public Reservation reserve(Reservation reservation) {
		logger.info("reserve()....");
		logger.info("{}", reservation);
		
		reservation.setStatus(Reservation_status.예약완료.toString());
		
		
		/*
		 * // creating dummy data for timestamp data SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy/MM/dd HH:mm");
		 * 
		 * String ts = sdf.format(System.currentTimeMillis());
		 * 
		 * logger.info("ts: {}", ts);
		 * 
		 * reservation.setR_start(ts); reservation.setR_end(ts);
		 * reservation.setR_date(ts); // creating dummy data for timestamp data
		 */		 		 		
		
		return dao.reserve(reservation);
	}
	
	/*
	 * check the date before cancellation
	 */
	@Override
	public Reservation cancel(Reservation reservation) {
		logger.info("cancel()....");
		logger.info("{}", reservation);
		
		reservation.setStatus(Reservation_status.이용자취소.toString());
		
		int flag = dao.cancel(reservation);
		
		if (flag == 0) {
			return null;
		} else {
			return dao.findReservationByNum(reservation);
		}
	}

	@Override
	public List<Reservation> isBooked(Reservation reservation) {
		logger.info("isBooked()....");
		logger.info("{}", reservation);

		return dao.isBooked(reservation);
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
	public List<Reservation> findReservationByStatus(Reservation reservation) {
		logger.info("findReservationByStatus()....");
		logger.info("{}", reservation);

		return dao.findReservationByStatus(reservation);
	}

	@Override
	public int delete(Reservation reservation) {
		logger.info("findReservationByStatus()....");
		logger.info("{}", reservation);
		return dao.delete(reservation);
	}

	@Override
	public long findSpaceNum(Reservation reservation) {
		logger.info("findReservationByStatus()....");
		logger.info("{}", reservation);
		return dao.findSpaceNum(reservation);
	}

}
