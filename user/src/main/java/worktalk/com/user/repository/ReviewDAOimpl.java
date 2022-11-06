package worktalk.com.user.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.user.domain.Customer_center;
import worktalk.com.user.domain.Review;

@Repository
public class ReviewDAOimpl implements ReviewDAO {
	private static final Logger logger = LoggerFactory.getLogger(ReviewDAOimpl.class);

	@Autowired
	SqlSession sqlSession;
	
	public ReviewDAOimpl() {
		logger.info("ReviewDAOimpl()....");
	}
	
	@Override
	public int insert(Review review) {
		logger.info("insert()....");
		logger.info("{}", review);
		int flag = sqlSession.insert("SQL_INSERT_RV", review);

		return flag;
	}

	@Override
	public int update(Review review) {
		logger.info("updateOK()....");
		logger.info("{}", review);

		int flag = sqlSession.update("SQL_UPDATE_RV", review);

		return flag;
	}

	@Override
	public int delete(Review review) {
		logger.info("deleteOK()....");
		logger.info("{}", review);

		int flag = sqlSession.delete("SQL_DELETE_RV", review);
		logger.info("{}",flag);

		return flag;
	}


	@Override
	public List<Review> findByName(String writer) {
		logger.info("findByName()....");
		List<Review> review_list = sqlSession.selectList("SQL_FINDWRITER_RV",writer);

		return review_list;
	}

	@Override
	public List<Review> findBySpaceNum(long space_num) {
		logger.info("findBySpaceNum()....");
		List<Review> review_list = sqlSession.selectList("SQL_FINDSPACE_RV",space_num);

		return review_list;
	}

}
