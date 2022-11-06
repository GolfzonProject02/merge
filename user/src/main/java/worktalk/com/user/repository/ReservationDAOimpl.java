package worktalk.com.user.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.user.domain.Reservation;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Repository class for Reservation data CRUD
 *
 */

@Repository
public class ReservationDAOimpl implements ReservationDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ReservationDAOimpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	public ReservationDAOimpl() {
		logger.info("ReservationDAOimpl()....");
	}


	/**
	 * returns Reservation when reservation succeed
	 * returns null when reservation failed
	 */
	
	@Override
	public Reservation reserve(Reservation reservation) {
		logger.info("reserve()....");
		logger.info("{}", reservation);
		
		int flag = sqlSession.insert("SQL_RESERVE", reservation);
		
		if (flag == 0) {
			return null;
		} else {
			Reservation result = (Reservation) sqlSession.selectList("SQL_FIND_RESERVATION_BY_NAME", reservation).get(0);
			logger.info("result : {}", result);
			return result;
		}
	}

	
	/**
	 * returns Reservation when cancellation succeed
	 * returns null when cancellation failed
	 */
	
	@Override
	public int cancel(Reservation reservation) {
		logger.info("cancel()....");
		logger.info("{}", reservation);

		return sqlSession.delete("SQL_CANCEL", reservation);
	}
	
	
	/**
	 * Have Reservation class for parameter
	 * (includes room number and date & time information)
	 * returns List<Reservation> of booked reservation
	 * returns null when there's no reservation
	 */
	
	@Override
	public List<Reservation> isBooked(Reservation reservation) {
		logger.info("isBooked()....");
		logger.info("{}", reservation);

		return sqlSession.selectList("SQL_IS_BOOKED", reservation);
	}
	
	/**
	 * Find singular reservation class that matches r_num
	 * Return singular Reservation or null.
	 */
	
	@Override
	public Reservation findReservationByNum(Reservation reservation) {
		logger.info("findReservationByNum()....");
		logger.info("{}", reservation);

		return sqlSession.selectOne("SQL_FIND_RESERVATION_BY_NUM", reservation);
	}
	
	/**
	 * Find multiple reservation class that matches name
	 * returns List<Reservation> that has same name value
	 * returns null when there's no reservation that has same name
	 */
	
	@Override
	public List<Reservation> findReservationByName(Reservation reservation) {
		logger.info("findReservationByName()....");
		logger.info("{}", reservation);

		return sqlSession.selectList("SQL_FIND_RESERVATION_BY_NAME", reservation);
	}
	
	/**
	 * Find multiple reservation class that matches name/status/p_status
	 * returns List<Reservation> for certain user's reservation that has same status/p_status value
	 * returns null when there's no reservation that has same status
	 */

	@Override
	public List<Reservation> findReservationByStatus(Reservation reservation) {
		logger.info("findReservationByStatus()....");
		logger.info("{}", reservation);
		List<Reservation> list = sqlSession.selectList("SQL_FIND_RESERVATION_BY_STATUS", reservation);
		
		return list;
	}


	@Override
	public int delete(Reservation reservation) {
		logger.info("delete()....");
		logger.info("{}", reservation);
		return sqlSession.delete("SQL_RESERVATION_DELETE", reservation);
	}


	@Override
	public long findSpaceNum(Reservation reservation) {
		logger.info("findSpaceNum()....");
		logger.info("{}", reservation);
		return sqlSession.selectOne("SQL_FIND_SPACE_NUM", reservation);
	}

}
