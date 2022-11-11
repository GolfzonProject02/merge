package worktalk.com.host.repository;

import java.util.List;

import worktalk.com.host.domain.Qna;

public interface QnaDAO {

	public List<Qna> findQnaBySpaceNum(long space_num); // 해당 사무공간의 Q&A 목록 출력

	public List<Qna> findQnaByName(String name); // 유저의 Q&A 목록 출력
}