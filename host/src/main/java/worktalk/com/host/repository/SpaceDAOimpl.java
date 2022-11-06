package worktalk.com.host.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.Qna;
import worktalk.com.host.domain.Review;
import worktalk.com.host.domain.Room;
import worktalk.com.host.domain.Space;

@Repository
public class SpaceDAOimpl implements SpaceDAO {
	private static final Logger logger = LoggerFactory.getLogger(SpaceDAOimpl.class);

	@Autowired
	SqlSession sqlSession;

	public SpaceDAOimpl() {
		logger.info("SpaceDAOimpl()....");
	}

	@Override
	public int insert(Space space) {
		logger.info("insert()....");
		logger.info("{}", space);
		int flag = sqlSession.insert("SQL_INSERT_SPACE", space);
		return flag;
	}

	@Override
	public int update(Space space) {
		logger.info("update()....");
		logger.info("{}", space);
		int flag = sqlSession.update("SQL_UPDATE_SPACE", space);
		return flag;
	}

	@Override
	public int delete(Space space) {
		logger.info("delete()....");
		logger.info("{}", space);
		int flag = sqlSession.delete("SQL_DELETE_SPACE", space);
		return flag;
	}

	@Override
	public Space selectOne(long space_num) {
		logger.info("selectOne()...");
		Space space2 = sqlSession.selectOne("SQL_SELECT_ONE_SPACE", space_num);
		logger.info("{}", space2);

		return space2;
	}

	@Override
	public List<Space> selectAll() {
		logger.info("selectAll()...");
		List<Space> space_list = sqlSession.selectList("SQL_SELECT_ALL_SPACE");

		return space_list;
	}

	@Override
	public List<Space> searchList(String searchWord) {
		logger.info("searchList()....");
//		logger.info("searchKey:{}",searchKey);
		logger.info("searchWord:{}",searchWord);
		
		Map<String,String> map = new HashMap<String, String>();
//		map.put("searchKey", searchKey);
		map.put("searchWord", "%"+searchWord+"%");
		
		List<Space> space_list = sqlSession.selectList("SQL_SEARCH_LIST_SPACE",map);
		
		return space_list;
	}

	@Override
	public List<Room> findRoom(long space_num) {
		logger.info("findRoom()...");
		List<Room> room_list = sqlSession.selectList("SQL_FIND_ROOM", space_num);
		logger.info("{}", space_num);

		return room_list;
	}

	@Override
	public List<Qna> findQna(long space_num) {
		logger.info("findQna()...");
		List<Qna> qna_list = sqlSession.selectList("SQL_FIND_QNA", space_num);
		logger.info("{}", space_num);

		return qna_list;
	}

	@Override
	public List<Review> findReview(long space_num) {
		logger.info("findReview()...");
		List<Review> review_list = sqlSession.selectList("SQL_FIND_REVIEW", space_num);
		logger.info("{}", review_list);

		return review_list;
	}

	
}
