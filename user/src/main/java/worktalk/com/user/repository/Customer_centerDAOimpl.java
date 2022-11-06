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

@Repository
public class Customer_centerDAOimpl implements Customer_centerDAO {
	private static final Logger logger = LoggerFactory.getLogger(Customer_centerDAOimpl.class);

	@Autowired
	SqlSession sqlSession;
	
	public Customer_centerDAOimpl() {
		logger.info("Customer_centerDAOimpl()....");
	}
	
	@Override
	public int insert(Customer_center customer_center) {
		logger.info("insert()....");
		logger.info("{}", customer_center);
		int flag = sqlSession.insert("SQL_INSERT_CC", customer_center);

		return flag;
	}

	@Override
	public int update(Customer_center customer_center) {
		logger.info("updateOK()....");
		logger.info("{}", customer_center);

		int flag = sqlSession.update("SQL_UPDATE_CC", customer_center);

		return flag;
	}

	@Override
	public int delete(Customer_center customer_center) {
		logger.info("deleteOK()....");
		logger.info("{}", customer_center);

		int flag = sqlSession.delete("SQL_DELETE_CC", customer_center);
		logger.info("{}",flag);

		return flag;
	}

	@Override
	public List<Customer_center> selectAll() {
		logger.info("selectAll()....");
		List<Customer_center> cc_boardlist = sqlSession.selectList("SQL_SELECT_ALL_CC");

		return cc_boardlist;
	}

	@Override
	public List<Customer_center> searchList(String searchKey, String searchWord) {
		logger.info("searchList()....");
		logger.info("searchKey:{}", searchKey);
		logger.info("searchWord:{}", searchWord);

		Map<String,String> map = new HashMap<String, String>();
		map.put("searchKey", searchKey);
		map.put("searchWord", "%"+searchWord+"%");
		
		List<Customer_center> cc_boardlist = sqlSession.selectList("SQL_SEARCH_LIST_CC",map);
		return cc_boardlist;
	}

}
