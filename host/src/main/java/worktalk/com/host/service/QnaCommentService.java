package worktalk.com.host.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.host.domain.QnaComment;
import worktalk.com.host.domain.Space;
import worktalk.com.host.repository.QnaCommentDAO;

@Service
public class QnaCommentService {
	private static final Logger logger = LoggerFactory.getLogger(QnaCommentService.class);
	
	@Autowired
	QnaCommentDAO dao;
	
	public QnaCommentService() {
		logger.info("QnaCommentService()...");
	}
	
	public int insert(QnaComment qnacomment) {
		logger.info("insert()...");
		return dao.insert(qnacomment);
	}
	
	public int update(QnaComment qnacomment) {
		logger.info("update()...");
		return dao.update(qnacomment);
	}
	
	public int delete(QnaComment qnacomment) {
		logger.info("delete()...");
		return dao.delete(qnacomment);
	}
	
	public List<QnaComment> selectAll(long q_num) {
		logger.info("selectAll(q_num)...");
		return dao.selectAll(q_num);
	}
	
	public QnaComment selectOne(long q_num) {
		logger.info("selectOne(q_num)...");
		return dao.selectOne(q_num);
	}

}
