package worktalk.com.master.repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.master.domain.Master;

/**
 * 
 * @author Juhee Fred Lee(이주희)
 * DAO class for login data CRUD
 *
 */

@Repository
public class MasterLoginDAOimpl implements MasterLoginDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(MasterLoginDAOimpl.class);

	@Autowired
	SqlSession sqlSession;

	public MasterLoginDAOimpl() {
		logger.info("UserLoginDAOimpl()....");
	}
	
	/*
	 * method for verifying email and pw
	 * returns 1 whens table has same email and pw
	 */
	@Override
	public Master logIn(Master master) {
		logger.info("logIn()....");
		logger.info("{}", master);
		
		Master result = sqlSession.selectOne("SQL_LOG_IN", master);
		
		return result;
	}


} // end class
