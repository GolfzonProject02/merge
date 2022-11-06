package worktalk.com.user.repository;

import java.util.List;

import worktalk.com.user.domain.Qna;
import worktalk.com.user.domain.Review;
import worktalk.com.user.domain.Room;
import worktalk.com.user.domain.Space;

public interface SpaceDAO {
	public Space selectOne(Space space);// 사무공간 상세페이지

	public List<Space> selectAll();// 사무공간 전체 리스트

	public List<Space> searchList(String searchKey, String searchWord);
	
	public List<Qna> findQna(long space_num);//사무공간 상세페이지 QnA리스트 출력

	public List<Room> findRoom(long space_num);//사무공간 상세페이지 세부공간리스트 출력
	
	public List<Review> findReview(long space_num);// 사무공간 상세페이지 내 후기리스트 출력

	public List<Space> recommendRegion(); //지역추천 리스트
	
}
