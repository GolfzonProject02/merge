package worktalk.com.host.repository;

import java.util.List;
import java.util.Map;

import worktalk.com.host.domain.Room;

public interface RoomDAO {
	
	public int insert(Room vo); // 사무공간 세부공간 등록

	public int update(Room room); // 사무공간 세부공간 수정

	public int delete(Room room); // 사무공간 세부공간 삭제

	public Room selectOne(Room room); // 사무공간 세부공간 선택

	public List<Room> selectAll(long space_num); // 해당 사무공간의 세부공간 출력
}
