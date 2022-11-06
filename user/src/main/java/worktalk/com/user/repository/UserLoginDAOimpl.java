package worktalk.com.user.repository;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.user.domain.User;

/**
 * 
 * @author Juhee Fred Lee(이주희)
 * DAO class for login data CRUD
 *
 */

@Repository
public class UserLoginDAOimpl implements UserLoginDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginDAOimpl.class);

	@Autowired
	SqlSession sqlSession;

	public UserLoginDAOimpl() {
		logger.info("UserLoginDAOimpl()....");
	}
	
	/*
	 * method for verifying email and pw
	 * returns 1 whens table has same email and pw
	 */
	@Override
	public User logIn(User user) {
		logger.info("logIn()....");
		logger.info("{}", user);
		
		User result = sqlSession.selectOne("SQL_LOG_IN", user);
		
		return result;
	}

} // end class
