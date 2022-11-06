package worktalk.com.host.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.Pay;

@Repository
public class PayDAOimpl implements PayDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(PayDAOimpl.class);
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public int insert(Pay pay) {
		logger.info("insert()....");
		logger.info("{}", pay);
		
		return sqlSession.insert("SQL_PAY_INSERT", pay);
	}

	@Override
	public List<Pay> findByName(Pay pay) {
		logger.info("findByName()....");
		logger.info("{}", pay);
		
		return sqlSession.selectList("SQL_PAY_FIND_BY_NAME", pay);
	}
	
	@Override
	public List<Pay> findHistoryByName(Pay pay) {
		logger.info("findHistoryByName()....");
		logger.info("{}", pay);
		
		return sqlSession.selectList("SQL_PAY_HISTORY_BY_NAME", pay);
	}

	@Override
	public List<Pay> findByNum(Pay pay) {
		logger.info("findByNum()....");
		logger.info("{}", pay);
		
		return sqlSession.selectList("SQL_PAY_FIND_BY_NUM", pay);
	}

	@Override
	public List<Pay> findDateUidByRnum(Pay pay) {
		logger.info("findDateUidByNum()....");
		logger.info("{}", pay);
		
		return sqlSession.selectList("SQL_PAY_DATE_UID_BY_RNUM", pay);
	}

	@Override
	public List<Pay> findHistoryByStatus(Pay pay) {
		logger.info("findHistoryByStatus()....");
		logger.info("{}", pay);
		
		return sqlSession.selectList("SQL_PAY_HISTORY_BY_STATUS", pay);
	}

	@Override
	public int calPaid(Pay pay) {
		logger.info("calPaid()....");
		logger.info("{}", pay);
		return sqlSession.selectOne("SQL_PAY_CAL_PAID", pay);
	}

	@Override
	public int calRefund(Pay pay) {
		logger.info("calPaid()....");
		logger.info("{}", pay);
		return sqlSession.selectOne("SQL_PAY_CAL_REFUND", pay);
	}

}
