package worktalk.com.host.repository;

import java.util.List;

import worktalk.com.host.domain.Qna;

public interface QnaDAO {

	public List<Qna> findQnaBySpaceNum(long space_num);

	public List<Qna> findQnaByName(String name);
}