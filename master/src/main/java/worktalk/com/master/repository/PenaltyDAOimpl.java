package worktalk.com.master.repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.master.domain.Penalty;

@Repository
public class PenaltyDAOimpl implements PenaltyDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PenaltyDAOimpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	public PenaltyDAOimpl() {
		logger.info("PenaltyDAOimpl()....");
	}

	@Override
	public int insert(Penalty penalty) {
		logger.info("insert()....");
		logger.info("{}", penalty);
		
		return sqlSession.insert("SQL_INSERT_PENALTY", penalty);
	}

	@Override
	public int delete(Penalty penalty) {
		logger.info("delete()....");
		logger.info("{}", penalty);
		
		return sqlSession.delete("SQL_DELETE_PENALTY", penalty);
	}

}
