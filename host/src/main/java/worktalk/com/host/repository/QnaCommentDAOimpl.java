package worktalk.com.host.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.QnaComment;
import worktalk.com.host.domain.Space;

@Repository
public class QnaCommentDAOimpl implements QnaCommentDAO {
	private static final Logger logger = LoggerFactory.getLogger(QnaCommentDAOimpl.class);

	@Autowired
	SqlSession sqlSession;
	
	public QnaCommentDAOimpl() {
		logger.info("QnaCommentDAOimpl()....");
	}
	
	@Override
	public int insert(QnaComment qnacomment) {
		logger.info("insert()....");
		logger.info("{}", qnacomment);
		int flag = sqlSession.insert("SQL_INSERT_QNA_COMMENT", qnacomment);

		return flag;
	}

	
	@Override
	public List<QnaComment> selectAll(long q_num) {
		logger.info("selectAll()....");
		List<QnaComment> qnacomment_list = sqlSession.selectList("SQL_SELECT_ALL_QNA_COMMENT",q_num);

		return qnacomment_list;
	}

	@Override
	public int update(QnaComment qnacomment) {
		logger.info("insert()....");
		logger.info("{}", qnacomment);
		int flag = sqlSession.update("SQL_UPDATE_QNA_COMMENT", qnacomment);

		return flag;
	}

	@Override
	public int delete(QnaComment qnacomment) {
		logger.info("insert()....");
		logger.info("{}", qnacomment);
		int flag = sqlSession.delete("SQL_DELETE_QNA_COMMENT", qnacomment);

		return flag;
	}

	@Override
	public QnaComment selectOne(long q_num) {
		logger.info("selectOne()...");
		QnaComment q_num2 = sqlSession.selectOne("SQL_SELECT_ONE_SPACE", q_num);
		logger.info("{}", q_num);

		return q_num2;
	}


}