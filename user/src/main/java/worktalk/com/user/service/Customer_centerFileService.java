package worktalk.com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.user.domain.Customer_center;
import worktalk.com.user.repository.Customer_centerDAO;
import worktalk.com.user.repository.Customer_centerFAO;

@Service
public class Customer_centerFileService {
	private static final Logger logger = LoggerFactory.getLogger(Customer_centerFileService.class);
	
	@Autowired
	Customer_centerFAO fao;
	
	public Customer_centerFileService() {
		logger.info("Customer_centerFileService()...");
	}

	public Customer_center getVO(Customer_center customer_center) {
		logger.info("getVO()...");
		return fao.getVO(customer_center);//return 1을 받고 controller에 1보내줌
	}
	
}
