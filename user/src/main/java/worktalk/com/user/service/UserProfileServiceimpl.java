package worktalk.com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.user.domain.User;
import worktalk.com.user.repository.UserDAO;

@Service
public class UserProfileServiceimpl implements UserProfileService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceimpl.class);
	
	@Autowired
	UserDAO dao;
	
	public UserProfileServiceimpl() {
		logger.info("ProfileServiceimpl()....");
	}

//	@Override
//	public int update(String updateKey, User user) {
//		logger.info("update()....");
//		logger.info("key: {}, user: {}", updateKey, user);
//		
//
//		return dao.update(updateKey, user);
//	}
//	
	@Override
	public int update(User user) {
		logger.info("update()....");
		
		return dao.update(user);
	}

	@Override
	public int leave(User user) {
		logger.info("leave()....");
		logger.info("user: {}",user);

		return dao.leave(user);
	}
	
	/*
	 * Select certain singular row that matches number
	 * returns dao.selectOne
	 */
	@Override
	public User findByName(User user) {
		logger.info("findByName()....");
		logger.info("{}", user);
		
		return dao.findByName(user);
	}

}
