package worktalk.com.host.repository;

import java.util.List;
import java.util.Map;

import worktalk.com.host.domain.Room;

public interface RoomDAO {
//	public int insert(Map<String, Object> map);
	public int insert(Room vo);
	public int update(Room room);
	public int delete(Room room);
	public Room selectOne(Room room);
	public List<Room> selectAll(long space_num);
}
