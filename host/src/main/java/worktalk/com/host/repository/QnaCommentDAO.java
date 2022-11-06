package worktalk.com.host.repository;

import java.util.List;

import worktalk.com.host.domain.Qna;
import worktalk.com.host.domain.QnaComment;

public interface QnaCommentDAO {

	public int insert(QnaComment qnacomment);

	public int update(QnaComment qnacomment);

	public int delete(QnaComment qnacomment);

	public List<QnaComment> selectAll(long q_num);
	
	public QnaComment selectOne(long q_num);
}