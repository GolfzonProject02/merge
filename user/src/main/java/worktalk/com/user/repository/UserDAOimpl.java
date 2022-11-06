package worktalk.com.user.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.user.domain.User;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Repository class for user data CRUD
 *
 */
@Repository
public class UserDAOimpl implements UserDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOimpl.class);
	
	public UserDAOimpl() {
		logger.info("UserDAOimpl()....");
	}
	
	/*
	 * Add Member Data to MEMBER Table
	 * return 1 when procedure succeed
	 */
	@Override
	public int join(User user) {
		logger.info("join()....");
		logger.info("{}", user);
		
		return sqlSession.insert("SQL_INSERT", user);
	}
	
	/*
	 * Checks whether Member Table has the same name
	 * Returns 1 when duplicated name exists
	 */
	@Override
	public int checkDuplicatedName(User user) {
		logger.info("checkDuplicatedName()....");
		logger.info("{}", user);
		
		User result = sqlSession.selectOne("SQL_FIND_BY_NAME", user);
		logger.info("result: {}", result);
		if (result == null) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/*
	 * Checks whether Member Table has the same email address
	 * Returns 1 when duplicated eamil exists
	 */
	@Override
	public int checkDuplicateMail(User user) {
		logger.info("checkDuplicateMail()....");
		logger.info("{}", user);
		
		User result = sqlSession.selectOne("SQL_FIND_BY_MAIL", user);
		logger.info("{}", result);
		if (result == null) {
			return 0;
		} else {
			return 1;
		}
	}
	
//	/*
//	 * Update data to MEMBER Table
//	 * update certain singular column according to updateKey
//	 * return 1 when procedure succeed
//	 */
//	@Override
//	public int update(String updateKey, User user) {
//		logger.info("update()....");
//		logger.info("{}", updateKey);
//		logger.info("{}", user);
//		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("updateKey", updateKey);
////		map.put("updateWord", user.get);
//		map.put("num", String.valueOf(user.getNum()));
//		return sqlSession.update("SQL_UPDATE", map);
//	}
	
	/*
	 * Update data to MEMBER Table
	 * update certain singular column according to updateKey
	 * return 1 when procedure succeed
	 */
	@Override
	public int update(User user) {
		logger.info("update_test()....");
		logger.info("{}", user);
		
		return sqlSession.update("SQL_UPDATE", user);
	}
	
	/*
	 * Delete data from Member Table
	 * return 1 when procedure succeed
	 */
	@Override
	public int leave(User user) {
		logger.info("leave()....");
		logger.info("{}", user);
		return sqlSession.delete("SQL_DELETE", user);
	}
	
	/*
	 * Select certain singular row that matches number
	 * returns Member when exists
	 */
	@Override
	public User findByName(User user) {
		logger.info("selectOne()....");
		logger.info("{}", user);
		return sqlSession.selectOne("SQL_FIND_BY_NAME", user);
	}

}
