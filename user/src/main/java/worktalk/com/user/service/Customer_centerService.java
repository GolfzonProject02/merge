package worktalk.com.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.user.domain.Customer_center;
import worktalk.com.user.repository.Customer_centerDAO;

@Service
public class Customer_centerService {
	private static final Logger logger = LoggerFactory.getLogger(Customer_centerService.class);
	
	@Autowired
	Customer_centerDAO dao;
	
	public Customer_centerService() {
		logger.info("Customer_centerService()...");
	}

	public int insert(Customer_center customer_center) {
		logger.info("insert()...");
		return dao.insert(customer_center);
	}
	public int update(Customer_center customer_center) {
		logger.info("update()...");
		return dao.update(customer_center);
	}
	public int delete(Customer_center customer_center) {
		logger.info("delete()...");
		return dao.delete(customer_center);
	}
	public List<Customer_center> selectAll() {
		logger.info("selectAll()...");
		return dao.selectAll();
	}	
	public List<Customer_center> searchList(String searchKey, String searchWord) {
		logger.info("searchList()...");
		return dao.searchList(searchKey, searchWord);
	}
}
