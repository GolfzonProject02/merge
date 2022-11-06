package worktalk.com.host.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.Room;

@Repository
public class RoomDAOimpl implements RoomDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomDAOimpl.class);

	@Autowired
	SqlSession sqlSession;

	public RoomDAOimpl() {
		logger.info("RoomDAOimpl()....");
	}
		
	@Override
	public int insert(Room vo) {
		logger.info("insert()....");
		logger.info("{}", vo);
		int flag = sqlSession.insert("SQL_INSERT_ROOM", vo);
		return flag;
	}
	
//	@Override
//	public int insert(Room room) {
//		logger.info("insert()....");
//		logger.info("{}", room);
//		int flag = sqlSession.insert("SQL_INSERT_ROOM", room);
//		return flag;
//	}
	

	@Override
	public int update(Room room) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Room room) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Room selectOne(Room room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> selectAll(long space_num) {
		logger.info("selectAll()...");
		List<Room> room_list = sqlSession.selectList("SQL_SELECT_ALL_ROOM",space_num);
		logger.info("{}", room_list);
		return room_list;
	}

//	@Override
//	public int insert(Map<String, Object> map) {
//		logger.info("insert()....");
//		int flag = sqlSession.insert("SQL_MULTI_INSERT_ROOM", map);
//		logger.info("{}", map);
//		return flag;
//	}

}
