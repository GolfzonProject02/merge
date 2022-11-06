package worktalk.com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.user.domain.Qna;
import worktalk.com.user.repository.QnaDAO;

@Service
public class QnaService {
	private static final Logger logger = LoggerFactory.getLogger(QnaService.class);
	
	@Autowired
	QnaDAO dao;
	
	public QnaService() {
		logger.info("QnaService()...");
	}

	public int insert(Qna qna) {
		logger.info("insert()...");
		return dao.insert(qna);
	}
	public int update(Qna qna) {
		logger.info("update()...");
		return dao.update(qna);
	}
	public int delete(Qna qna) {
		logger.info("delete()...");
		return dao.delete(qna);
	}

}
