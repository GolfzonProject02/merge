package worktalk.com.host.repository;

import java.util.List;

import worktalk.com.host.domain.Qna;
import worktalk.com.host.domain.Review;
import worktalk.com.host.domain.Room;
import worktalk.com.host.domain.Space;

public interface SpaceDAO {
	
	public int insert(Space space); //사무공간 등록하기

	public int update(Space space); //사무공간 정보수정하기

	public int delete(Space space); //사무공간 삭제하기

	public Space selectOne(long space_num); //사무공간 선택

	public List<Space> selectAll(String host); //접속자(호스트)가 등록한 사무공간 출력

	public List<Space> searchList(String searchWord); //사무공간 검색하기(주소, 사무공간명, 세부사무공간명)

	public List<Room> findRoom(long space_num);// 공간상세페이지 내 세부공간리스트 출력

	public List<Qna> findQna(long space_num);// 공간상세페이지 내 QnA리스트 출력

	public List<Review> findReview(long space_num);// 공간상세페이지 내 후기리스트 출력

}
