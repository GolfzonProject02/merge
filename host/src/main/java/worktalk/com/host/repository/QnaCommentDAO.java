package worktalk.com.host.repository;

import java.util.List;

import worktalk.com.host.domain.Qna;
import worktalk.com.host.domain.QnaComment;

public interface QnaCommentDAO {

	public int insert(QnaComment qnacomment); // Q&A 답글 달기

	public int update(QnaComment qnacomment); // Q&A 답글 수정

	public int delete(QnaComment qnacomment); // Q&A 답글 삭제

	public List<QnaComment> selectAll(long q_num); // Q&A 답글 목록 출력
	
	public QnaComment selectOne(long q_num); // Q&A 답글 선택
}