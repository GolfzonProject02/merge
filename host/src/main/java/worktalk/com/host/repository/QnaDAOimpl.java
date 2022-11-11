package worktalk.com.host.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.Qna;

@Repository
public class QnaDAOimpl implements QnaDAO {
	private static final Logger logger = LoggerFactory.getLogger(QnaDAOimpl.class);

	@Autowired
	SqlSession sqlSession;
	
	public QnaDAOimpl() {
		logger.info("QNAimpl()....");
	}
	
	@Override
	public List<Qna> findQnaBySpaceNum(long space_num) {
		logger.info("selectAll()....");
		List<Qna> qna_list = sqlSession.selectList("SQL_SELECT_ALL_QNA",space_num);

		return qna_list;
	}

	@Override
	public List<Qna> findQnaByName(String name) {
		logger.info("selectAll()....");
		List<Qna> qna_list = sqlSession.selectList("SQL_SELECT_ALL_BYNAME",name);

		return qna_list;
	}


}