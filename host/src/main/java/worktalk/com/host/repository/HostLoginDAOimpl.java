package worktalk.com.host.repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.Host;

/**
 * 
 * @author Juhee Fred Lee(이주희)
 * DAO class for login data CRUD
 *
 */

@Repository
public class HostLoginDAOimpl implements HostLoginDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(HostLoginDAOimpl.class);

	@Autowired
	SqlSession sqlSession;

	public HostLoginDAOimpl() {
		logger.info("UserLoginDAOimpl()....");
	}
	
	/*
	 * method for verifying email and pw
	 * returns 1 whens table has same email and pw
	 */
	@Override
	public Host logIn(Host host) {
		logger.info("logIn()....");
		logger.info("{}", host);
		
		Host result = sqlSession.selectOne("SQL_LOG_IN", host);
		
		return result;
	}

} // end class
