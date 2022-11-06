package worktalk.com.host.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.Reservation;

/**
 * 
 * @author Juhee Fred Lee(이주희)
 * Repository class for reservation CRUD
 *
 */

@Repository
public class ReservationDAOimpl implements ReservationDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(ReservationDAOimpl.class);

	@Autowired
	SqlSession sqlSession;

	public ReservationDAOimpl() {
		logger.info("ReservationDAOimpl()....");
	}

	@Override
	public int updateStatus(Reservation reservation) {
		logger.info("updateStatus()....");
		logger.info("{}", reservation);
		
		return sqlSession.update("SQL_UPDATE_STATUS", reservation);
	}
	
	@Override
	public int cancel(Reservation reservation) {
		logger.info("cancel()....");
		logger.info("{}", reservation);
		
		return sqlSession.update("SQL_UPDATE_CANCEL", reservation);
	}

	@Override
	public Reservation findReservationByNum(Reservation reservation) {
		logger.info("findReservationByNum()....");
		logger.info("{}", reservation);		
		return sqlSession.selectOne("SQL_FIND_RESERVATION_BY_NUM", reservation);
	}


	@Override
	public List<Reservation> findReservationByName(Reservation reservation) {
		logger.info("findReservationByName()....");
		logger.info("{}", reservation);		
		return sqlSession.selectList("SQL_FIND_RESERVATION_BY_NAME", reservation);
	}

	@Override
	public List<Reservation> findReservationByKeywords(Reservation reservation) {
		logger.info("findReservationByStatus()....");
		logger.info("{}", reservation);		
		return sqlSession.selectList("SQL_FIND_RESERVATION_BY_KEYWORD", reservation);
	}

}
