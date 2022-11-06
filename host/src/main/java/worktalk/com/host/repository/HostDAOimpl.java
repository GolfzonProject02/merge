package worktalk.com.host.repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.Host;

@Repository
public class HostDAOimpl implements HostDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(HostDAOimpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	public HostDAOimpl() {
		logger.info("HostDAOimpl()....");
	}
	
	/*
	 * Add Member Data to MEMBER Table
	 * return 1 when procedure succeed
	 */
	@Override
	public int join(Host host) {
		logger.info("join()....");
		logger.info("{}", host);
		
		return sqlSession.insert("SQL_INSERT", host);
	}
	
	/*
	 * Checks whether Member Table has the same name
	 * Returns 1 when duplicated name exists
	 */
	@Override
	public int checkDuplicatedName(Host host) {
		logger.info("checkDuplicatedName()....");
		logger.info("{}", host);
		
		Host result = sqlSession.selectOne("SQL_FIND_BY_NAME", host);
		logger.info("{}", result);
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
	public int checkDuplicateMail(Host host) {
		logger.info("checkDuplicateMail()....");
		logger.info("{}", host);
		
		Host result = sqlSession.selectOne("SQL_FIND_BY_MAIL", host);
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
//	public int update(String updateKey, Host host) {
//		logger.info("update()....");
//		logger.info("{}", updateKey);
//		logger.info("{}", host);
//		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("updateKey", updateKey);
////		map.put("updateWord", host.get);
//		map.put("num", String.valueOf(host.getNum()));
//		return sqlSession.update("SQL_UPDATE", map);
//	}
	
	/*
	 * Update data to MEMBER Table
	 * update certain singular column according to updateKey
	 * return 1 when procedure succeed
	 */
	@Override
	public int update(Host host) {
		logger.info("update_test()....");
		logger.info("{}", host);
		
		return sqlSession.update("SQL_UPDATE", host);
	}
	
	/*
	 * Delete data from Member Table
	 * return 1 when procedure succeed	
	 */
	@Override
	public int leave(Host host) {
		logger.info("leave()....");
		logger.info("{}", host);
		return sqlSession.delete("SQL_DELETE", host);
	}
	
	/*
	 * Select certain singular row that matches number
	 * returns Member when exists
	 */
	@Override
	public Host findByName(Host host) {
		logger.info("selectOne()....");
		logger.info("{}", host);
		return sqlSession.selectOne("SQL_FIND_BY_NAME", host);
	}

}
