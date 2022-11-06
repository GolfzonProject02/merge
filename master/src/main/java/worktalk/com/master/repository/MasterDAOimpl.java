package worktalk.com.master.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.master.domain.Master;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Repository class for user master CRUD
 *
 */

@Repository
public class MasterDAOimpl implements MasterDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MasterDAOimpl.class);
	
	@Autowired
	SqlSession sqlSession;

	public MasterDAOimpl() {
		logger.info("MasterDAOimpl()....");
	}

	@Override
	public int join(Master master) {
		logger.info("join()....");
		logger.info("{}", master);
		
		return sqlSession.insert("SQL_INSERT", master);
	}

	@Override
	public int update(Master master) {
		logger.info("update()....");
		logger.info("{}", master);
		
		return sqlSession.update("SQL_UPDATE", master);
	}
	
	@Override
	public int update_penalty(Master master) {
		logger.info("update_penalty()....");
		logger.info("{}", master);
		
		return sqlSession.update("SQL_UPDATE_PENALTY", master);
	}

	@Override
	public int leave(Master master) {
		logger.info("leave()....");
		logger.info("{}", master);
		
		return sqlSession.delete("SQL_DELETE", master);
	}

	@Override
	public Master findByName(Master master) {
		logger.info("findByName()....");
		logger.info("{}", master);
		
		return sqlSession.selectOne("SQL_FIND_BY_NAME", master);
	}
	@Override
	public Master findByMail(Master master) {
		logger.info("findByName()....");
		logger.info("{}", master);
		
		return sqlSession.selectOne("SQL_FIND_BY_MAIL", master);
	}

	@Override
	public List<Master> findAll() {
		logger.info("findAll()....");
		
		return sqlSession.selectList("SQL_FIND_ALL");
	}

}
