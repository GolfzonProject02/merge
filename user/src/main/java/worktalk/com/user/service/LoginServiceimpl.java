package worktalk.com.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.user.domain.User;
import worktalk.com.user.repository.UserLoginDAO;

@Service
public class LoginServiceimpl implements LoginService{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceimpl.class);
	
	@Autowired
	UserLoginDAO loginDao;
	
	public LoginServiceimpl() {
		logger.info("UserLogInServiceDAOimpl()....");
	}

	@Override
	public User login(User user) {
		logger.info("UserLogInServiceDAOimpl()....");
		return loginDao.logIn(user);
	}

}
