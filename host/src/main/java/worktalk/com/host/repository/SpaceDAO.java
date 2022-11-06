package worktalk.com.host.repository;

import java.util.List;

import worktalk.com.host.domain.Qna;
import worktalk.com.host.domain.Review;
import worktalk.com.host.domain.Room;
import worktalk.com.host.domain.Space;

public interface SpaceDAO {
	public int insert(Space space);

	public int update(Space space);

	public int delete(Space space);

	public Space selectOne(long space_num);

	public List<Space> selectAll();

	public List<Space> searchList(String searchWord);

	public List<Room> findRoom(long space_num);// 공간상세페이지 내 세부공간리스트 출력

	public List<Qna> findQna(long space_num);// 공간상세페이지 내 QnA리스트 출력
	
	public List<Review> findReview(long space_num);// 공간상세페이지 내 후기리스트 출력

}
