package worktalk.com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.user.domain.User;
import worktalk.com.user.repository.UserDAO;

/**
 * 
 * @author Juhee Fred Lee
 * Service class for user data CRUD
 *
 */
@Service
public class JoinServiceImpl implements JoinService {
	private static final Logger logger = LoggerFactory.getLogger(JoinServiceImpl.class);

	@Autowired
	UserDAO dao;
	
	public JoinServiceImpl() {
		logger.info("JoinServiceImpl()....");
	}
	
	/*
	 * Add Member Data to MEMBER Table
	 * return dao.join
	 */
	@Override
	public int join(User user) {
		logger.info("join()....");
		logger.info("{}", user);
		user.setImgname("profill.png");
		return dao.join(user);
	}

	/*
	 * Checks whether Member Table has the same email address
	 * Returns dao.checkDuplicateName
	 */
	@Override
	public int checkDuplicatedName(User user) {
		logger.info("checkDuplicatedName()....");
		logger.info("{}", user);
		return dao.checkDuplicatedName(user);
	}

}
