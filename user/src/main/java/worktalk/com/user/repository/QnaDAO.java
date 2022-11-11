package worktalk.com.user.repository;

import java.util.List;

import worktalk.com.user.domain.Qna;

public interface QnaDAO {

	public int insert(Qna qna); //Q&A 등록

	public int update(Qna qna); //Q&A 수정

	public int delete(Qna qna); //Q&A 삭제

	public List<Qna> findQnaByspaceNum(long space_num); //해당 사무공간의 Q&A 목록 출력

	public List<Qna> findQnaByName(String name); //사용자가 작성한 Q&A 목록 출력
}